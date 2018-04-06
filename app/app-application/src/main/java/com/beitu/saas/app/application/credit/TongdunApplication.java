package com.beitu.saas.app.application.credit;

import com.beitu.saas.common.enums.ProductTypeEnum;
import com.beitu.saas.common.enums.RestCodeEnum;
import com.beitu.saas.risk.client.service.TripleSubscriptionService;
import com.beitu.saas.risk.domain.enums.triple.TripleServiceTypeEnum;
import com.beitu.saas.risk.domain.platform.tongdun.TripleTongDunReportQueryOutput;
import com.beitu.saas.risk.domain.platform.tongdun.TripleTongdunReportQueryInput;
import com.beitu.saas.risk.domain.platform.vo.TripleServiceResult;
import com.fqgj.common.utils.CollectionUtils;
import com.fqgj.common.utils.StringUtils;
import com.fqgj.exception.common.ApplicationException;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by baoyinglin on 6/2/2017.
 */
@Component
public class TongdunApplication {

    private static final Log LOGGER = LogFactory.getLog(TongdunApplication.class);

    @Autowired
    private CreditTongdunDetailService creditTongdunDetailService;

    @Autowired
    private CreditTongdunService creditTongdunService;

    @Autowired
    private TripleSubscriptionService tripleSubscriptionService;

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void saveTongdunCreditInfo(CreditQueryInfoVo creditQueryInfoVo) {
        Long userId = creditQueryInfoVo.getUserId();
        CreditTongdunVo creditTongdunVo = creditTongdunService.getLastCreditTongdunByUserId(userId);
        if (creditTongdunVo != null) {
            Boolean isExceedOneDay = DateUtil.isExceedOneDay(creditTongdunVo.getGmtCreate());
            if (isExceedOneDay != null && !isExceedOneDay) {
                CreditTongdunVo addCreditTongdunVo = new CreditTongdunVo(userId, creditQueryInfoVo.getQueryId(), creditTongdunVo.getReportId());
                addCreditTongdunVo.setSuccess(Boolean.TRUE);
                Long recordId = creditTongdunService.addCreditTongdun(addCreditTongdunVo);
                CreditTongdunDetailVo creditTongdunDetailVo = creditTongdunDetailService.getByRecordId(creditTongdunVo.getCreditTongdunId());
                CreditTongdunDetail creditTongdunDetail = new CreditTongdunDetail();
                BeanUtils.copyProperties(creditTongdunDetailVo, creditTongdunDetail);
                creditTongdunDetail.setRecordId(recordId);
                creditTongdunDetailService.create(creditTongdunDetail);
                return;
            }
        }
        TripleTongdunReportIdInput tripleTongdunReportIdInput = new TripleTongdunReportIdInput();
        tripleTongdunReportIdInput.setIdCard(creditQueryInfoVo.getIdentityNo()).setMobile(creditQueryInfoVo.getMobile()).setRealName(creditQueryInfoVo.getName());
        TripleServiceResult response = tripleSubscriptionService.getTripleServiceResult(ProductTypeEnum.YCJT, TripleServiceTypeEnum.TONGDUN_REPORT_ID, tripleTongdunReportIdInput);
        String reportId;
        if (response.isSuccess()) {
            reportId = ((TripleTongdunReportIdOutput) response.getData()).getReportId();
            LOGGER.info("获取同盾数据为{},查询数据:{},reportId:{}", response, creditQueryInfoVo, reportId);
        } else {
            LOGGER.warn("******************************TONGDUN_REPORT_ID error {}", response.getMsg());
            throw new ApplicationException(RestCodeEnum.CARRIEE_REBIND.setMsg(response.getMsg()));
        }
        if (StringUtils.isNotEmpty(reportId)) {
            Long queryId = creditQueryInfoVo.getQueryId();
            creditTongdunService.addCreditTongdun(new CreditTongdunVo(userId, queryId, reportId));
            //异步获取详细报告并更新
            tongdunAsyncService.asyncUpdateDetail(reportId);
        } else {
            // TODO: 17/6/30 抛出异常
        }
    }

    public TongdunReportVo queryTongdunReport(Long recordId) {
        CreditTongdunDetailVo creditTongdunDetail = creditTongdunDetailService.getByRecordId(recordId);
        if (creditTongdunDetail == null) {
            return null;
        }

        TongdunReportVo tongdunReportVo = new TongdunReportVo();
        tongdunReportVo.setFinalDecision(creditTongdunDetail.getFinalDecision());
        tongdunReportVo.setFinalScore(creditTongdunDetail.getFinalScore());
        String itemName = creditTongdunDetail.getItemName();
        List<String> itemList = Lists.newArrayList();
        if (StringUtils.isNotEmpty(itemName)) {
            Collections.addAll(itemList, itemName.split("<br>"));
        }
        List<String> blackItems = Lists.newArrayList();
        List<String> threeMonthRelationInfo = Lists.newArrayList();
        List<TongdunReportVo.BorrowRecord> borrowRecords = Lists.newArrayList();
        for (String str : itemList) {
            if (hit(str, CreditConsts.BLACKED_HIT_ITEMS)) {
                blackItems.add(str);
            } else if (hit(str, CreditConsts.BORROW_HIT_ITEMS)) {
                String item = str.substring(0, str.indexOf(":"));
                String details = str.substring(str.indexOf(":") + 1, str.length());
                details = details.substring(1, details.length() - 1);
                details = details.replaceAll("\"", "");
                TongdunReportVo.BorrowRecord borrowRecord = tongdunReportVo.new BorrowRecord();
                List<String> detailList = Arrays.asList(details.split(","));
                Integer count = getDetailCount(detailList);
                borrowRecord.setItem(item.replace("多", count + ""));
                borrowRecord.setDetailCount(count + "个");
                borrowRecord.setDetails(detailList);
                borrowRecord.setSort(getSort(item));
                borrowRecords.add(borrowRecord);
                Collections.sort(borrowRecords, new Comparator<TongdunReportVo.BorrowRecord>() {
                    @Override
                    public int compare(TongdunReportVo.BorrowRecord o1, TongdunReportVo.BorrowRecord o2) {
                        return o1.getSort().compareTo(o2.getSort());
                    }
                });
            } else if (hit(str, CreditConsts.RELATION_HIT_ITEMS)) {
                String details = str.substring(str.indexOf(":") + 1, str.length());
                details = details.substring(1, details.length() - 1);
                details = details.replaceAll("\"", "");
                threeMonthRelationInfo.addAll(Arrays.asList(details.split(",")));
            } else {
                // nothing to do
            }
        }
        tongdunReportVo.setBlacked(blackItems.size() > 0);
        tongdunReportVo.setBlackItems(blackItems);
//        tongdunReportVo.setThreeMonthRelationInfo(threeMonthRelationInfo);
        tongdunReportVo.setBorrowRecords(borrowRecords);
        return tongdunReportVo;
    }

    private boolean hit(String str, List<String> sampleList) {
        for (String sample : sampleList) {
            if (str.indexOf(sample) != -1) {
                return true;
            }
        }
        return false;
    }

    private Integer getSort(String item) {
        if (item.contains("7天")) {
            return 0;
        } else {
            String str = item.substring(0, item.indexOf("个月"));
            return Integer.parseInt(str);
        }
    }

    private Integer getDetailCount(List<String> detailList) {
        int i = 0;
        for (String str : detailList) {
            String cStr = str.substring(str.indexOf(":") + 1, str.length());
            i += Integer.parseInt(cStr);
        }
        return i;
    }

    @Async
    @Transactional(rollbackFor = Exception.class)
    public void asyncUpdateDetail(String reportId) {
        try {
            Thread.sleep(6000);//3秒
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        TripleTongdunReportQueryInput tripleTongdunReportQueryInput = new TripleTongdunReportQueryInput();
        tripleTongdunReportQueryInput.setReportId(reportId);
        TripleServiceResult response = tripleSubscriptionService.getTripleServiceResult(ProductTypeEnum.YCJT, TripleServiceTypeEnum.TONGDUN_REPORT_QUERY, tripleTongdunReportQueryInput);
        TripleTongDunReportQueryOutput tongDunVo;
        if (response.isSuccess()) {
            tongDunVo = (TripleTongDunReportQueryOutput) response.getData();
        } else {
            LOGGER.warn("******************************TONGDUN_REPORT_QUERY error reportId:{}", reportId, response.getMsg());
            throw new ApplicationException(RestCodeEnum.CARRIEE_REBIND.setMsg(response.getMsg()));
        }
        //TongDunVo tongDunVo = platformSubscriptionService.tongDunSubscription(null, reportId);

        if (tongDunVo != null && StringUtils.isNotEmpty(tongDunVo.getFinalScore())) {
            CreditTongdunVo creditTongdunVo = creditTongdunService.getByReportId(reportId);
            Integer tryNumber = 10;
            while (creditTongdunVo == null && tryNumber > 0) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                creditTongdunVo = creditTongdunService.getByReportId(reportId);
                tryNumber--;
            }
            if (creditTongdunVo == null) {
                LOGGER.error("拿取同盾信用记录失败！reportId:{},response:{}", reportId, response);
                return;
            }
            CreditTongdunDetail creditTongdunDetail = transform(tongDunVo, reportId, creditTongdunVo.getCreditTongdunId());
            creditTongdunDetailService.create(creditTongdunDetail);
            creditTongdunVo.setSuccess(Boolean.TRUE);
            CreditTongdun creditTongdun = new CreditTongdun();
            BeanUtils.copyProperties(creditTongdunVo, creditTongdun);
            creditTongdun.setId(creditTongdunVo.getCreditTongdunId());
            creditTongdunService.updateById(creditTongdun);
        } else if (tongDunVo != null) {
            LOGGER.error("reportId:{},查询出的同盾分为null", reportId);
        } else {
            LOGGER.error("reportId:{},查询结果为空", reportId);
        }
    }

    private CreditTongdunDetail transform(TripleTongDunReportQueryOutput tongDunVo, String reportId, Long recordId) {
        CreditTongdunDetail creditTongdunDetail = new CreditTongdunDetail();
        creditTongdunDetail.setReportId(reportId);
        creditTongdunDetail.setFinalScore(tongDunVo.getFinalScore());
        creditTongdunDetail.setFinalDecision(tongDunVo.getFinalDecision());
        if (CollectionUtils.isNotEmpty(tongDunVo.getItemStrList())) {
            creditTongdunDetail.setItemName(String.join("<br>", tongDunVo.getItemStrList()) + "<br>");
        }
        creditTongdunDetail.setReportTime(new Date());
        creditTongdunDetail.setRecordId(recordId);
        return creditTongdunDetail;
    }

}
