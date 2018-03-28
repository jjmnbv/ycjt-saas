package com.beitu.saas.app.application.order;

import com.beitu.saas.app.application.borrower.BorrowerApplication;
import com.beitu.saas.app.application.borrower.vo.BorrowerInfoVo;
import com.beitu.saas.app.application.order.vo.H5OrderListVo;
import com.beitu.saas.app.application.order.vo.OrderDetailVo;
import com.beitu.saas.app.application.order.vo.QueryOrderVo;
import com.beitu.saas.app.application.order.vo.SaasOrderListVo;
import com.beitu.saas.app.enums.BorrowerOrderApplyStatusEnum;
import com.beitu.saas.app.enums.H5OrderBillDetailViewTypeEnum;
import com.beitu.saas.auth.service.SaasAdminService;
import com.beitu.saas.borrower.client.SaasBorrowerRealInfoService;
import com.beitu.saas.borrower.client.SaasBorrowerService;
import com.beitu.saas.borrower.domain.SaasBorrowerRealInfoVo;
import com.beitu.saas.borrower.domain.SaasBorrowerVo;
import com.beitu.saas.channel.client.SaasChannelService;
import com.beitu.saas.common.utils.identityNumber.vo.IdcardInfoExtractor;
import com.beitu.saas.finance.client.SaasMerchantBalanceInfoService;
import com.beitu.saas.finance.client.SaasMerchantCreditInfoService;
import com.beitu.saas.finance.client.SaasMerchantSmsInfoService;
import com.beitu.saas.finance.client.domain.DataDashboardVo;
import com.beitu.saas.finance.entity.SaasMerchantBalanceInfoEntity;
import com.beitu.saas.finance.entity.SaasMerchantCreditInfoEntity;
import com.beitu.saas.finance.entity.SaasMerchantSmsInfoEntity;
import com.beitu.saas.order.client.SaasOrderApplicationService;
import com.beitu.saas.order.client.SaasOrderBillDetailService;
import com.beitu.saas.order.client.SaasOrderService;
import com.beitu.saas.order.client.SaasOrderStatusHistoryService;
import com.beitu.saas.order.domain.QuerySaasOrderVo;
import com.beitu.saas.order.domain.SaasOrderApplicationVo;
import com.beitu.saas.order.domain.SaasOrderBillDetailVo;
import com.beitu.saas.order.domain.SaasOrderVo;
import com.beitu.saas.order.entity.SaasOrder;
import com.beitu.saas.order.entity.SaasOrderBillDetail;
import com.beitu.saas.order.entity.SaasOrderStatusHistory;
import com.beitu.saas.order.enums.OrderErrorCodeEnum;
import com.beitu.saas.order.enums.OrderStatusEnum;
import com.beitu.saas.order.vo.LoanDataDetailVo;
import com.beitu.saas.order.vo.NoRepayOrderVo;
import com.beitu.saas.order.vo.OverdueOrderVo;
import com.fqgj.common.api.Page;
import com.fqgj.common.utils.CollectionUtils;
import com.fqgj.common.utils.DateUtil;
import com.fqgj.common.utils.StringUtils;
import com.fqgj.exception.common.ApplicationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author linanjun
 * @create 2018/3/21 下午4:00
 * @description
 */
@Service
public class OrderApplication {

    @Autowired
    private SaasOrderApplicationService saasOrderApplicationService;

    @Autowired
    private SaasOrderService saasOrderService;

    @Autowired
    private SaasOrderBillDetailService saasOrderBillDetailService;

    @Autowired
    private OrderCalculateApplication orderCalculateApplication;

    @Autowired
    private SaasBorrowerRealInfoService saasBorrowerRealInfoService;

    @Autowired
    private SaasOrderStatusHistoryService saasOrderStatusHistoryService;

    @Autowired
    private SaasChannelService saasChannelService;

    @Autowired
    private SaasAdminService saasAdminService;

    @Autowired
    private BorrowerApplication borrowerApplication;

    @Autowired
    private SaasMerchantCreditInfoService saasMerchantCreditInfoService;

    @Autowired
    private SaasMerchantSmsInfoService saasMerchantSmsInfoService;

    @Autowired
    private SaasMerchantBalanceInfoService saasMerchantBalanceInfoService;

    public BorrowerOrderApplyStatusEnum getOrderApplyStatus(String borrowerCode, String channelCode) {
        if (saasOrderApplicationService.getByBorrowerCode(borrowerCode) != null) {
            return BorrowerOrderApplyStatusEnum.REVIEWING;
        } else if (saasOrderService.isReviewRefuse(borrowerCode, channelCode)) {
            return BorrowerOrderApplyStatusEnum.REFUSE;
        }
        return BorrowerOrderApplyStatusEnum.NO_SUBMIT;
    }

    @Transactional
    public SaasOrder createOrder(SaasOrderApplicationVo saasOrderApplicationVo, String orderNumb) {
        SaasOrder saasOrder = new SaasOrder();
        BeanUtils.copyProperties(saasOrderApplicationVo, saasOrder);
        saasOrder.setOrderNumb(orderNumb);
        saasOrder.setCreatedDt(saasOrderApplicationVo.getGmtCreate());
        saasOrder.setOrderStatus(OrderStatusEnum.SUBMIT_PRELIMINARY_REVIEW.getCode());
        saasOrder.setExpireDate(DateUtil.addDate(new Date(), 20));
        saasOrder.setTotalInterestFee(orderCalculateApplication.getInterest(saasOrder.getRealCapital(), saasOrder.getTotalInterestRatio(), saasOrder.getCreatedDt(), saasOrder.getRepaymentDt()));
        saasOrderService.create(saasOrder);
        return saasOrder;
    }

    @Transactional
    public SaasOrderBillDetail createOrderBillDetail(SaasOrderVo saasOrderVo) {
        return new SaasOrderBillDetail();
    }

    public List<H5OrderListVo> listH5Order(String borrowerCode, String merchantCode) {
        List<SaasOrderBillDetailVo> saasOrderBillDetailVoList = saasOrderBillDetailService.listByBorrowerCodeAndMerchantCode(borrowerCode, merchantCode);
        if (CollectionUtils.isEmpty(saasOrderBillDetailVoList)) {
            return null;
        }
        List<H5OrderListVo> results = new ArrayList<>(saasOrderBillDetailVoList.size());
        saasOrderBillDetailVoList.forEach(saasOrderBillDetailVo -> {
            H5OrderListVo h5OrderListVo = new H5OrderListVo();
            h5OrderListVo.setAmount(orderCalculateApplication.getAmount(saasOrderBillDetailVo).toString());
            h5OrderListVo.setRepaymentDt(DateUtil.convertDateToString(saasOrderBillDetailVo.getRepaymentDt()));
            h5OrderListVo.setOrderStatus(saasOrderService.getOrderStatusByOrderNumb(saasOrderBillDetailVo.getOrderNumb()).getCode());
            h5OrderListVo.setViewType(getH5ViewTypeByOrderStatus(h5OrderListVo.getOrderStatus()));
            results.add(h5OrderListVo);
        });
        return results;
    }

    private Integer getH5ViewTypeByOrderStatus(Integer orderStatus) {
        if (OrderStatusEnum.HAS_BEEN_DESTROY.getCode().equals(orderStatus) ||
                OrderStatusEnum.HAS_BEEN_PAYMENT.getCode().equals(orderStatus)) {
            return H5OrderBillDetailViewTypeEnum.FINISHED.getCode();
        } else if (OrderStatusEnum.FOR_REIMBURSEMENT.getCode().equals(orderStatus)) {
            return H5OrderBillDetailViewTypeEnum.FOR_REIMBURSEMENT.getCode();
        } else if (OrderStatusEnum.TO_CONFIRM_EXTEND.getCode().equals(orderStatus)) {
            return H5OrderBillDetailViewTypeEnum.TO_CONFIRM_EXTEND.getCode();
        } else if (OrderStatusEnum.OVERDUE.getCode().equals(orderStatus)) {
            return H5OrderBillDetailViewTypeEnum.OVERDUE.getCode();
        }
        return H5OrderBillDetailViewTypeEnum.FOR_REIMBURSEMENT.getCode();
    }

    public OrderDetailVo getOrderDetailVoByOrderNumb(String orderNumb) {
        List<SaasOrderVo> saasOrderVoList = saasOrderService.listEffectiveOrderByOrderNumb(orderNumb);
        if (CollectionUtils.isEmpty(saasOrderVoList)) {
            return null;
        }
        SaasOrderVo saasOrderVo = saasOrderVoList.get(0);
        OrderDetailVo orderDetailVo = new OrderDetailVo();
        BeanUtils.copyProperties(saasOrderVo, orderDetailVo);
        SaasBorrowerRealInfoVo realInfoVo = saasBorrowerRealInfoService.getBorrowerRealInfoByBorrowerCode(saasOrderVo.getBorrowerCode());
        orderDetailVo.setBorrowerName(realInfoVo.getName());
        orderDetailVo.setBorrowerIdentityCode(realInfoVo.getIdentityCode());
        return orderDetailVo;
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void updateOrderStatus(String operatorCode, String orderNumb, OrderStatusEnum updateOrderStatus, String remark) {
        SaasOrderVo saasOrderVo = saasOrderService.getByOrderNumb(orderNumb);

        OrderStatusEnum nextOrderStatus = OrderStatusEnum.getEnumByCode(updateOrderStatus.getCode());
        OrderStatusEnum currentOrderStatus = OrderStatusEnum.getEnumByCode(saasOrderVo.getOrderStatus());

        if (Arrays.binarySearch(nextOrderStatus.getCodeArray(), currentOrderStatus.getCode()) < 0) {
            throw new ApplicationException(OrderErrorCodeEnum.ILLEGAL_OPERATION_ORDER_STATUS);
        }
        updateOrderStatus(operatorCode, saasOrderVo.getSaasOrderId(), saasOrderVo.getVersion(), saasOrderVo.getOrderNumb(), currentOrderStatus, updateOrderStatus, remark);
    }

    private void updateOrderStatus(String operatorCode, Long orderId, Long version, String orderNumb, OrderStatusEnum currentOrderStatus, OrderStatusEnum updateOrderStatus, String remark) {
        SaasOrderStatusHistory saasOrderStatusHistory = new SaasOrderStatusHistory();
        saasOrderStatusHistory.setOrderId(orderId);
        saasOrderStatusHistory.setOrderNumb(orderNumb);
        saasOrderStatusHistory.setCurrentOrderStatus(currentOrderStatus.getCode());
        saasOrderStatusHistory.setUpdateOrderStatus(updateOrderStatus.getCode());
        saasOrderStatusHistory.setRemark(remark);
        saasOrderStatusHistory.setOperatorCode(operatorCode);
        saasOrderStatusHistoryService.create(saasOrderStatusHistory);
        if (!saasOrderService.updateOrderStatus(orderId, version, currentOrderStatus, updateOrderStatus)) {
            throw new ApplicationException(OrderErrorCodeEnum.ORDER_STATUS_UPDATE_FAILURE);
        }
    }

    public List<SaasOrderListVo> listPreliminaryReviewOrder(QueryOrderVo queryOrderVo, Page page) {
        QuerySaasOrderVo querySaasOrderVo = convertQueryOrderVo2QuerySaasOrderVo(queryOrderVo);
        if (querySaasOrderVo == null) {
            page.setTotalCount(0);
            return null;
        }
        if (queryOrderVo.getOrderStatus() != null) {
            querySaasOrderVo.setOrderStatusList(Arrays.asList(queryOrderVo.getOrderStatus()));
        } else {
            if (StringUtils.isNotEmpty(queryOrderVo.getReviewerCode())) {
                querySaasOrderVo.setOrderStatusList(Arrays.asList(OrderStatusEnum.PRELIMINARY_REVIEWER_GET_ORDER.getCode(),
                        OrderStatusEnum.PRELIMINARY_REVIEWER_REJECT.getCode(),
                        OrderStatusEnum.PRELIMINARY_REVIEWER_REFUSE.getCode()));
            } else {
                querySaasOrderVo.setOrderStatusList(Arrays.asList(
                        OrderStatusEnum.SUBMIT_PRELIMINARY_REVIEW.getCode(),
                        OrderStatusEnum.IN_PRELIMINARY_REVIEWER.getCode(),
                        OrderStatusEnum.PRELIMINARY_REVIEWER_REJECT.getCode()));
            }
        }
        if (StringUtils.isNotEmpty(queryOrderVo.getReviewerCode())) {
            querySaasOrderVo.setPreliminaryReviewerCode(queryOrderVo.getReviewerCode());
        }
        List<SaasOrderVo> saasOrderVoList = saasOrderService.listByQuerySaasOrderVoAndPage(querySaasOrderVo, page);
        if (CollectionUtils.isEmpty(saasOrderVoList)) {
            return null;
        }
        List<SaasOrderListVo> orderListVoList = new ArrayList<>(saasOrderVoList.size());
        saasOrderVoList.forEach(saasOrderVo -> orderListVoList.add(
                convertSaasOrderVo2SaasOrderListVo(saasOrderVo)));
        return orderListVoList;
    }

    public List<SaasOrderListVo> listFinalReviewOrder(QueryOrderVo queryOrderVo, Page page) {
        QuerySaasOrderVo querySaasOrderVo = convertQueryOrderVo2QuerySaasOrderVo(queryOrderVo);
        if (querySaasOrderVo == null) {
            page.setTotalCount(0);
            return null;
        }
        if (queryOrderVo.getOrderStatus() != null) {
            querySaasOrderVo.setOrderStatusList(Arrays.asList(
                    queryOrderVo.getOrderStatus()));
        } else {
            if (StringUtils.isNotEmpty(queryOrderVo.getReviewerCode())) {
                querySaasOrderVo.setOrderStatusList(Arrays.asList(
                        OrderStatusEnum.FINAL_REVIEWER_GET_ORDER.getCode(),
                        OrderStatusEnum.FINAL_REVIEWER_REJECT.getCode(),
                        OrderStatusEnum.FINAL_REVIEWER_REFUSE.getCode()));
            } else {
                querySaasOrderVo.setOrderStatusList(Arrays.asList(
                        OrderStatusEnum.SUBMIT_FINAL_REVIEW.getCode(),
                        OrderStatusEnum.IN_FINAL_REVIEWER.getCode(),
                        OrderStatusEnum.FINAL_REVIEWER_REJECT.getCode()));
            }
        }
        if (StringUtils.isNotEmpty(queryOrderVo.getReviewerCode())) {
            querySaasOrderVo.setFinalReviewerCode(queryOrderVo.getReviewerCode());
        }
        List<SaasOrderVo> saasOrderVoList = saasOrderService.listByQuerySaasOrderVoAndPage(querySaasOrderVo, page);
        if (CollectionUtils.isEmpty(saasOrderVoList)) {
            return null;
        }
        List<SaasOrderListVo> orderListVoList = new ArrayList<>(saasOrderVoList.size());
        saasOrderVoList.forEach(saasOrderVo -> orderListVoList.add(convertSaasOrderVo2SaasOrderListVo(saasOrderVo)));
        return orderListVoList;
    }

    public List<SaasOrderListVo> listRefusedOrder(QueryOrderVo queryOrderVo, Page page) {
        QuerySaasOrderVo querySaasOrderVo = convertQueryOrderVo2QuerySaasOrderVo(queryOrderVo);
        if (querySaasOrderVo == null) {
            page.setTotalCount(0);
            return null;
        }
        querySaasOrderVo.setOrderStatusList(Arrays.asList(
                OrderStatusEnum.PRELIMINARY_REVIEWER_REFUSE.getCode(),
                OrderStatusEnum.FINAL_REVIEWER_REFUSE.getCode(),
                OrderStatusEnum.LOAN_LENDER_REFUSE.getCode()));
        List<SaasOrderVo> saasOrderVoList = saasOrderService.listByQuerySaasOrderVoAndPage(querySaasOrderVo, page);
        if (CollectionUtils.isEmpty(saasOrderVoList)) {
            return null;
        }
        List<SaasOrderListVo> orderListVoList = new ArrayList<>(saasOrderVoList.size());
        saasOrderVoList.forEach(saasOrderVo -> orderListVoList.add(convertSaasOrderVo2SaasOrderListVo(saasOrderVo)));
        return orderListVoList;
    }

    public List<SaasOrderListVo> listForLendingOrder(QueryOrderVo queryOrderVo, Page page) {
        QuerySaasOrderVo querySaasOrderVo = convertQueryOrderVo2QuerySaasOrderVo(queryOrderVo);
        if (querySaasOrderVo == null) {
            page.setTotalCount(0);
            return null;
        }
        querySaasOrderVo.setOrderStatusList(Arrays.asList(
                OrderStatusEnum.SUBMIT_LOAN_LENDER.getCode()));
        List<SaasOrderVo> saasOrderVoList = saasOrderService.listByQuerySaasOrderVoAndPage(querySaasOrderVo, page);
        if (CollectionUtils.isEmpty(saasOrderVoList)) {
            return null;
        }
        List<SaasOrderListVo> orderListVoList = new ArrayList<>(saasOrderVoList.size());
        saasOrderVoList.forEach(saasOrderVo -> orderListVoList.add(convertSaasOrderVo2SaasOrderListVo(saasOrderVo)));
        return orderListVoList;
    }

    private QuerySaasOrderVo convertQueryOrderVo2QuerySaasOrderVo(QueryOrderVo queryOrderVo) {
        QuerySaasOrderVo querySaasOrderVo = new QuerySaasOrderVo();
        querySaasOrderVo.setMerchantCode(queryOrderVo.getMerchantCode());
        querySaasOrderVo.setChannelCode(queryOrderVo.getChannelCode());
        if (queryOrderVo.getApplyDuration() != null) {
            Date endDate = new Date();
            if (queryOrderVo.getApplyEndDate() != null) {
                querySaasOrderVo.setCreatedEndDt(queryOrderVo.getApplyEndDate());
                endDate = queryOrderVo.getApplyEndDate();
            }
            querySaasOrderVo.setCreatedBeginDt(DateUtil.addDate(endDate, -queryOrderVo.getApplyDuration()));
        }
        List<String> borrowerCodeList = borrowerApplication.listBorrowerCodeByMobileAndNameAndIdentityCode(queryOrderVo.getMobile(), queryOrderVo.getUserName(), queryOrderVo.getIdentityCode(), queryOrderVo.getMerchantCode());
        if (borrowerCodeList == null) {
            return null;
        }
        querySaasOrderVo.setBorrowerCodeList(borrowerCodeList);
        return querySaasOrderVo;

    /**
     * 数据看板
     *
     * @param merchantCode
     */
    public DataDashboardVo getDataDashboardInfo(String merchantCode, Page page) {
        //放款数据
        LoanDataDetailVo loanDataDetailVo = saasOrderBillDetailService.getLoanDataDetailVo(merchantCode);

        //账户信息
        SaasMerchantCreditInfoEntity creditInfoByMerchantCode = saasMerchantCreditInfoService.getCreditInfoByMerchantCode(merchantCode);
        SaasMerchantSmsInfoEntity smsInfoByMerchantCode = saasMerchantSmsInfoService.getSmsInfoByMerchantCode(merchantCode);
        SaasMerchantBalanceInfoEntity balanceInfoEntity = saasMerchantBalanceInfoService.getMerchantBalanceInfoByMerchantCode(merchantCode);

        DataDashboardVo dataDashboardVo = new DataDashboardVo()
                .setLoanDataDetailVo(loanDataDetailVo)
                .setMerchantBalance(balanceInfoEntity.getValue())
                .setMerchantCredit(creditInfoByMerchantCode.getValue())
                .setMerchantSms(smsInfoByMerchantCode.getValue());

        // TODO: 2018/3/28 BY李楠君 浏览量走势

        //待收的订单列表和逾期的订单列表
        List<NoRepayOrderVo> noRepayOrderVos = saasOrderBillDetailService.getNoRepayOrderListByPage(merchantCode, page);
        List<OverdueOrderVo> overdueOrderVos = saasOrderBillDetailService.getOverdueOrderListByPage(merchantCode, page);
        dataDashboardVo.setNoRepayOrderVos(noRepayOrderVos)
                .setOverdueOrderVos(overdueOrderVos);

        return dataDashboardVo;

    }

    private SaasOrderListVo convertSaasOrderVo2SaasOrderListVo(SaasOrderVo saasOrderVo) {
        SaasOrderListVo orderListVo = new SaasOrderListVo();
        orderListVo.setOrderNumb(saasOrderVo.getOrderNumb());
        orderListVo.setApplyDate(DateUtil.getDate(saasOrderVo.getCreatedDt()));
        orderListVo.setCapital(saasOrderVo.getRealCapital().toString());
        orderListVo.setOrderStatus(OrderStatusEnum.getEnumByCode(saasOrderVo.getOrderStatus()).getMsg());
        orderListVo.setRemark(saasOrderVo.getRemark());
        orderListVo.setBorrowingDuration(DateUtil.countDay(saasOrderVo.getRepaymentDt(), saasOrderVo.getCreatedDt()) + "天");
        BorrowerInfoVo borrowerInfoVo = borrowerApplication.getBorrowerInfoVoByBorrowerCode(saasOrderVo.getBorrowerCode());
        BeanUtils.copyProperties(borrowerInfoVo, orderListVo);
        orderListVo.setChannelName(saasChannelService.getSaasChannelByChannelCode(saasOrderVo.getChannelCode()).getChannelName());
        if (StringUtils.isNotEmpty(saasOrderVo.getPreliminaryReviewerCode())) {
            orderListVo.setPreliminaryReviewer(saasAdminService.getSaasAdminByAdminCode(saasOrderVo.getPreliminaryReviewerCode()).getName());
        }
        if (StringUtils.isNotEmpty(saasOrderVo.getFinalReviewerCode())) {
            orderListVo.setFinalReviewer(saasAdminService.getSaasAdminByAdminCode(saasOrderVo.getFinalReviewerCode()).getName());
        }
        return orderListVo;
    }

}
