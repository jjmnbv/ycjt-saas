package com.beitu.saas.app.application.order;

import com.beitu.saas.app.application.borrower.BorrowerApplication;
import com.beitu.saas.app.application.borrower.vo.BorrowerInfoVo;
import com.beitu.saas.app.application.order.vo.QueryOrderBillDetailVo;
import com.beitu.saas.app.application.order.vo.SaasOrderBillDetailListVo;
import com.beitu.saas.app.enums.QueryRepaymentDtEnum;
import com.beitu.saas.channel.client.SaasChannelService;
import com.beitu.saas.common.utils.DateUtil;
import com.beitu.saas.order.client.SaasOrderBillDetailService;
import com.beitu.saas.order.client.SaasOrderService;
import com.beitu.saas.order.client.SaasOrderStatusHistoryService;
import com.beitu.saas.order.domain.QuerySaasOrderBillDetailVo;
import com.beitu.saas.order.domain.SaasOrderBillDetailVo;
import com.fqgj.common.api.Page;
import com.fqgj.common.utils.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author linanjun
 * @create 2018/3/27 下午10:19
 * @description 账单
 */
@Service
public class OrderBillDetailApplication {

    @Autowired
    private SaasOrderBillDetailService saasOrderBillDetailService;

    @Autowired
    private BorrowerApplication borrowerApplication;

    @Autowired
    private SaasChannelService saasChannelService;

    @Autowired
    private SaasOrderService saasOrderService;

    @Autowired
    private OrderCalculateApplication orderCalculateApplication;

    @Autowired
    private SaasOrderStatusHistoryService saasOrderStatusHistoryService;

    public List<SaasOrderBillDetailListVo> listAfterLendManageOrder(QueryOrderBillDetailVo queryVo, Page page) {
        QuerySaasOrderBillDetailVo querySaasOrderBillDetailVo = convertQueryOrderBillDetailVo2QuerySaasOrderBillDetailVo(queryVo);
        if (querySaasOrderBillDetailVo == null) {
            return null;
        }
        if (queryVo.getOrderStatus() != null) {
            querySaasOrderBillDetailVo.setOrderStatusList(Arrays.asList(queryVo.getOrderStatus()));
        }
        List<SaasOrderBillDetailVo> saasOrderBillDetailVoList = saasOrderBillDetailService.listByQueryOrderBillDetailVoAndPage(querySaasOrderBillDetailVo, page);
        if (CollectionUtils.isEmpty(saasOrderBillDetailVoList)) {
            return null;
        }
        List<SaasOrderBillDetailListVo> results = new ArrayList<>(saasOrderBillDetailVoList.size());
        saasOrderBillDetailVoList.forEach(saasOrderBillDetailVo -> results.add(convertSaasOrderBillDetailVo2SaasOrderBillDetailListVo(saasOrderBillDetailVo)));
        return results;
    }

    public List<SaasOrderBillDetailListVo> listOverdueOrder(QueryOrderBillDetailVo queryVo, Page page) {
        QuerySaasOrderBillDetailVo querySaasOrderBillDetailVo = convertQueryOrderBillDetailVo2QuerySaasOrderBillDetailVo(queryVo);
        if (querySaasOrderBillDetailVo == null) {
            return null;
        }
        List<SaasOrderBillDetailVo> saasOrderBillDetailVoList = saasOrderBillDetailService.listByQueryOrderBillDetailVoAndPage(querySaasOrderBillDetailVo, page);
        if (CollectionUtils.isEmpty(saasOrderBillDetailVoList)) {
            return null;
        }
        List<SaasOrderBillDetailListVo> results = new ArrayList<>(saasOrderBillDetailVoList.size());
        saasOrderBillDetailVoList.forEach(saasOrderBillDetailVo -> results.add(convertSaasOrderBillDetailVo2SaasOrderBillDetailListVo(saasOrderBillDetailVo)));
        return results;
    }

    private QuerySaasOrderBillDetailVo convertQueryOrderBillDetailVo2QuerySaasOrderBillDetailVo(QueryOrderBillDetailVo queryVo) {
        QuerySaasOrderBillDetailVo querySaasOrderBillDetailVo = new QuerySaasOrderBillDetailVo();
        querySaasOrderBillDetailVo.setMerchantCode(queryVo.getMerchantCode());
        querySaasOrderBillDetailVo.setChannelCode(queryVo.getChannelCode());
        if (queryVo.getRepaymentDuration() != null) {
            Date endDate = new Date();
            if (queryVo.getRepaymentEndDate() != null) {
                querySaasOrderBillDetailVo.setRepaymentEndDt(queryVo.getRepaymentEndDate());
                endDate = queryVo.getRepaymentEndDate();
            }
            querySaasOrderBillDetailVo.setRepaymentBeginDt(DateUtil.addDate(endDate, -queryVo.getRepaymentDuration()));
        }
        QueryRepaymentDtEnum queryRepaymentDtEnum = QueryRepaymentDtEnum.getByCode(queryVo.getQueryRepaymentDtKey());
        if (queryRepaymentDtEnum != null) {
            if (queryRepaymentDtEnum.getBeginParam() != null) {
                querySaasOrderBillDetailVo.setRepaymentBeginDt(DateUtil.addDate(new Date(), queryRepaymentDtEnum.getBeginParam()));
            }
            if (queryRepaymentDtEnum.getEndParam() != null) {
                querySaasOrderBillDetailVo.setRepaymentEndDt(DateUtil.addDate(new Date(), queryRepaymentDtEnum.getEndParam()));
            }
        }
        List<String> borrowerCodeList = borrowerApplication.listBorrowerCodeByMobileAndNameAndIdentityCode(queryVo.getMobile(), queryVo.getUserName(), queryVo.getIdentityCode(), queryVo.getMerchantCode());
        if (borrowerCodeList == null) {
            return null;
        }
        querySaasOrderBillDetailVo.setBorrowerCodeList(borrowerCodeList);
        return querySaasOrderBillDetailVo;
    }

    private SaasOrderBillDetailListVo convertSaasOrderBillDetailVo2SaasOrderBillDetailListVo(SaasOrderBillDetailVo saasOrderBillDetailVo) {
        SaasOrderBillDetailListVo saasOrderBillDetailListVo = new SaasOrderBillDetailListVo();
        saasOrderBillDetailListVo.setOrderNumb(saasOrderBillDetailVo.getOrderNumb());
        saasOrderBillDetailListVo.setCapital(saasOrderBillDetailVo.getRealCapital().toString());
        saasOrderBillDetailListVo.setAmount(orderCalculateApplication.getAmount(saasOrderBillDetailVo).toString());
        saasOrderBillDetailListVo.setCreatedDate(DateUtil.getDate(saasOrderBillDetailVo.getCreatedDt()));
        saasOrderBillDetailListVo.setRepaymentDate(DateUtil.getDate(saasOrderBillDetailVo.getRepaymentDt()));
        saasOrderBillDetailListVo.setExtend(saasOrderBillDetailVo.getRelationOrderBillDetailId() != null);
        saasOrderBillDetailListVo.setOverdueDuration(DateUtil.countDay(new Date(), saasOrderBillDetailVo.getRepaymentDt()));

        BorrowerInfoVo borrowerInfoVo = borrowerApplication.getBorrowerInfoVoByBorrowerCode(saasOrderBillDetailVo.getBorrowerCode());
        BeanUtils.copyProperties(borrowerInfoVo, saasOrderBillDetailListVo);

        saasOrderBillDetailListVo.setLoanLendRemark(saasOrderStatusHistoryService.getLoanLendRemark(saasOrderBillDetailVo.getOrderNumb()));
        saasOrderBillDetailListVo.setChannelName(saasChannelService.getSaasChannelByChannelCode(saasOrderBillDetailVo.getChannelCode()).getChannelName());
        saasOrderBillDetailListVo.setOrderStatus(saasOrderService.getOrderStatusByOrderNumb(saasOrderBillDetailVo.getOrderNumb()).getMsg());

        return saasOrderBillDetailListVo;
    }

}