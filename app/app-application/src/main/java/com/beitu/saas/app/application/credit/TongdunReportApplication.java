package com.beitu.saas.app.application.credit;

import com.beitu.saas.app.application.credit.async.TongdunAsyncApplication;
import com.beitu.saas.app.application.credit.vo.TongdunReportVo;
import com.beitu.saas.common.utils.ThreadPoolUtils;
import com.beitu.saas.credit.client.SaasCreditTongdunDetailService;
import com.beitu.saas.credit.client.SaasCreditTongdunService;
import com.beitu.saas.credit.consts.CreditConsts;
import com.beitu.saas.credit.domain.SaasCreditTongdunDetailVo;
import com.beitu.saas.credit.domain.SaasCreditTongdunVo;
import com.fqgj.common.utils.StringUtils;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by jungle
 */
@Service
public class TongdunReportApplication {

    private static final Log LOGGER = LogFactory.getLog(TongdunReportApplication.class);

    @Autowired
    private SaasCreditTongdunDetailService saasCreditTongdunDetailService;

    @Autowired
    private SaasCreditTongdunService saasCreditTongdunService;

    @Autowired
    private TongdunAsyncApplication tongdunAsyncApplication;

    public TongdunReportVo getTongdunReport(String merchantCode, String borrowerCode) {
        SaasCreditTongdunVo saasCreditTongdunVo = saasCreditTongdunService.getByMerchantCodeAndBorrowerCode(merchantCode, borrowerCode);
        if (saasCreditTongdunVo == null) {
            generateTongdunReport(merchantCode, borrowerCode);
            return null;
        } else if (!Boolean.TRUE.equals(saasCreditTongdunVo.getSuccess())) {
            return null;
        }
        return queryTongdunReport(saasCreditTongdunVo.getSaasCreditTongdunId());
    }

    public void generateTongdunReport(String merchantCode, String borrowerCode) {
        try {
            ThreadPoolUtils.getTaskInstance().submit(() -> tongdunAsyncApplication.generateTongdunReport(merchantCode, borrowerCode));
        } catch (Exception e) {
            LOGGER.error("******* 同盾报告线程启动异常 ******* 异常：", e);
        }
    }

    private TongdunReportVo queryTongdunReport(Long recordId) {
        SaasCreditTongdunDetailVo saasCreditTongdunDetailVo = saasCreditTongdunDetailService.getByRecordId(recordId);
        if (saasCreditTongdunDetailVo == null) {
            return null;
        }
        TongdunReportVo tongdunReportVo = new TongdunReportVo();
        tongdunReportVo.setFinalDecision(saasCreditTongdunDetailVo.getFinalDecision());
        tongdunReportVo.setFinalScore(saasCreditTongdunDetailVo.getFinalScore());
        String itemName = saasCreditTongdunDetailVo.getItemName();
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
                Collections.sort(borrowRecords, (o1, o2) -> o1.getSort().compareTo(o2.getSort()));
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


}
