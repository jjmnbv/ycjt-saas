package com.beitu.saas.app.application.order;

import com.beitu.saas.app.application.SendApplication;
import com.beitu.saas.app.application.borrower.BorrowerApplication;
import com.beitu.saas.app.application.borrower.vo.BorrowerInfoVo;
import com.beitu.saas.app.application.contract.ContractApplication;
import com.beitu.saas.app.application.contract.enums.ContractTypeEnum;
import com.beitu.saas.app.application.contract.thread.GenerateContractThread;
import com.beitu.saas.app.application.order.vo.*;
import com.beitu.saas.app.enums.BorrowerOrderApplyStatusEnum;
import com.beitu.saas.app.enums.H5OrderBillDetailViewTypeEnum;
import com.beitu.saas.app.enums.SaasSmsTypeEnum;
import com.beitu.saas.auth.entity.SaasAdmin;
import com.beitu.saas.auth.service.SaasAdminService;
import com.beitu.saas.borrower.client.SaasBorrowerPersonalInfoService;
import com.beitu.saas.borrower.client.SaasBorrowerRealInfoService;
import com.beitu.saas.borrower.client.SaasBorrowerService;
import com.beitu.saas.borrower.domain.SaasBorrowerRealInfoVo;
import com.beitu.saas.borrower.domain.SaasBorrowerVo;
import com.beitu.saas.channel.client.SaasChannelService;
import com.beitu.saas.channel.consts.ChannelConsts;
import com.beitu.saas.channel.entity.SaasChannelEntity;
import com.beitu.saas.channel.enums.ChannelTypeEnum;
import com.beitu.saas.collection.client.SaasCollectionOrderService;
import com.beitu.saas.common.config.ConfigUtil;
import com.beitu.saas.common.utils.DateUtil;
import com.beitu.saas.common.utils.ShortUrlUtil;
import com.beitu.saas.common.utils.ThreadPoolUtils;
import com.beitu.saas.finance.client.SaasMerchantBalanceInfoService;
import com.beitu.saas.finance.client.SaasMerchantCreditInfoService;
import com.beitu.saas.finance.client.SaasMerchantSmsInfoService;
import com.beitu.saas.finance.entity.SaasMerchantBalanceInfoEntity;
import com.beitu.saas.finance.entity.SaasMerchantCreditInfoEntity;
import com.beitu.saas.finance.entity.SaasMerchantSmsInfoEntity;
import com.beitu.saas.order.client.SaasOrderBillDetailService;
import com.beitu.saas.order.client.SaasOrderLendRemarkService;
import com.beitu.saas.order.client.SaasOrderService;
import com.beitu.saas.order.client.SaasOrderStatusHistoryService;
import com.beitu.saas.order.consts.OrderConsts;
import com.beitu.saas.order.domain.*;
import com.beitu.saas.order.entity.SaasOrder;
import com.beitu.saas.order.entity.SaasOrderStatusHistory;
import com.beitu.saas.order.enums.DashboardTypeEnum;
import com.beitu.saas.order.enums.OrderErrorCodeEnum;
import com.beitu.saas.order.enums.OrderStatusEnum;
import com.beitu.saas.order.vo.DashboardOrderVo;
import com.beitu.saas.order.vo.LoanDataDetailVo;
import com.beitu.saas.order.vo.LoanStateDetailVo;
import com.fqgj.common.api.Page;
import com.fqgj.common.utils.CollectionUtils;
import com.fqgj.common.utils.StringUtils;
import com.fqgj.exception.common.ApplicationException;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author linanjun
 * @create 2018/3/21 下午4:00
 * @description
 */
@Service
public class OrderApplication {

    private static final Log LOG = LogFactory.getLog(OrderApplication.class);

    @Autowired
    private SaasOrderService<SaasOrder> saasOrderService;

    @Autowired
    private SaasOrderBillDetailService saasOrderBillDetailService;

    @Autowired
    private OrderCalculateApplication orderCalculateApplication;

    @Autowired
    private SaasBorrowerRealInfoService saasBorrowerRealInfoService;

    @Autowired
    private SaasOrderStatusHistoryService saasOrderStatusHistoryService;

    @Autowired
    private SaasBorrowerService saasBorrowerService;

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

    @Autowired
    private OrderBillDetailApplication orderBillDetailApplication;

    @Autowired
    private ContractApplication contractApplication;

    @Autowired
    private SendApplication sendApplication;

    @Autowired
    private SaasCollectionOrderService saasCollectionOrderService;

    @Autowired
    private SaasChannelService saasChannelService;

    @Autowired
    private ConfigUtil configUtil;

    @Autowired
    private SaasBorrowerPersonalInfoService saasBorrowerPersonalInfoService;

    @Autowired
    private SaasOrderLendRemarkService saasOrderLendRemarkService;

    public BorrowerOrderApplyStatusEnum getOrderApplyStatus(String borrowerCode, String channelCode) {
        if (StringUtils.isNotEmpty(saasOrderService.getReviewerRefuseOrderNumb(borrowerCode, channelCode))) {
            return BorrowerOrderApplyStatusEnum.REFUSE;
        } else if (saasOrderService.isReviewing(borrowerCode, channelCode)) {
            return BorrowerOrderApplyStatusEnum.REVIEWING;
        }
        return BorrowerOrderApplyStatusEnum.NO_SUBMIT;
    }

    @Transactional(rollbackFor = Exception.class)
    public SaasOrder createOrder(SaasOrderApplicationVo saasOrderApplicationVo, String orderNumb, String channelCode) {
        SaasOrder saasOrder = new SaasOrder();
        BeanUtils.copyProperties(saasOrderApplicationVo, saasOrder);
        saasOrder.setOrderNumb(orderNumb);
        saasOrder.setChannelCode(channelCode);
        saasOrder.setOrderStatus(OrderStatusEnum.SUBMIT_PRELIMINARY_REVIEW.getCode());
        saasOrder.setExpireDate(getOrderExpireDate(saasOrderApplicationVo.getRepaymentDt()));
        saasOrderService.create(saasOrder);

        String borrowerCode = saasOrderApplicationVo.getBorrowerCode();
        SaasBorrowerVo saasBorrowerVo = saasBorrowerService.getByBorrowerCode(borrowerCode);
        sendApplication.sendNotifyMessage(saasOrderApplicationVo.getMerchantCode(), saasBorrowerVo.getMobile(), null, SaasSmsTypeEnum.SAAS_0004);
        SaasChannelEntity saasChannelEntity = saasChannelService.getSaasChannelByChannelCode(saasOrderApplicationVo.getChannelCode());
        if (ChannelTypeEnum.USER_DEFINED.getType().equals(saasChannelEntity.getChannelType())) {
            SaasAdmin saasAdmin = saasAdminService.getSaasAdminByAdminCode(saasChannelEntity.getChargePersonCode());
            SaasBorrowerRealInfoVo saasBorrowerRealInfoVo = saasBorrowerRealInfoService.getBorrowerRealInfoByBorrowerCode(borrowerCode);
            sendApplication.sendNotifyMessage(saasOrderApplicationVo.getMerchantCode(), saasAdmin.getMobile(), new HashMap<String, String>(8) {{
                put("channel_name", saasChannelEntity.getChannelName());
                put("money", saasOrderApplicationVo.getRealCapital().toString());
                put("name", saasBorrowerRealInfoVo.getName());
                put("phone", saasBorrowerVo.getMobile());
            }}, SaasSmsTypeEnum.SAAS_0005);
        }
        return saasOrder;
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateOrderByOrderApplicationVo(SaasOrderApplicationVo saasOrderApplicationVo, String orderNumb) {
        SaasOrderVo saasOrderVo = saasOrderService.getByOrderNumbAndMerchantCode(orderNumb, saasOrderApplicationVo.getMerchantCode());
        if (saasOrderVo == null) {
            throw new ApplicationException(OrderErrorCodeEnum.NO_PERMISSION_OPERATE_ORDER);
        }
        SaasOrder updateSaasOrder = new SaasOrder();
        updateSaasOrder.setId(saasOrderVo.getSaasOrderId());
        updateSaasOrder.setChannelCode(saasOrderApplicationVo.getChannelCode());
        updateSaasOrder.setRealCapital(saasOrderApplicationVo.getRealCapital());
        updateSaasOrder.setTotalInterestRatio(saasOrderApplicationVo.getTotalInterestRatio());
        updateSaasOrder.setBorrowPurpose(saasOrderApplicationVo.getBorrowPurpose());
        updateSaasOrder.setRepaymentDt(saasOrderApplicationVo.getRepaymentDt());
        updateSaasOrder.setExpireDate(getOrderExpireDate(saasOrderApplicationVo.getRepaymentDt()));
        updateSaasOrder.setBorrowerAuthorizedSignLoan(saasOrderApplicationVo.getBorrowerAuthorizedSignLoan());
        saasOrderService.updateById(updateSaasOrder);

        String borrowerCode = saasOrderApplicationVo.getBorrowerCode();
        SaasBorrowerVo saasBorrowerVo = saasBorrowerService.getByBorrowerCode(borrowerCode);
        sendApplication.sendNotifyMessage(saasOrderApplicationVo.getMerchantCode(), saasBorrowerVo.getMobile(), null, SaasSmsTypeEnum.SAAS_0004);
        SaasChannelEntity saasChannelEntity = saasChannelService.getSaasChannelByChannelCode(saasOrderApplicationVo.getChannelCode());
        if (ChannelTypeEnum.USER_DEFINED.getType().equals(saasChannelEntity.getChannelType())) {
            SaasAdmin saasAdmin = saasAdminService.getSaasAdminByAdminCode(saasChannelEntity.getChargePersonCode());
            SaasBorrowerRealInfoVo saasBorrowerRealInfoVo = saasBorrowerRealInfoService.getBorrowerRealInfoByBorrowerCode(borrowerCode);
            sendApplication.sendNotifyMessage(saasOrderApplicationVo.getMerchantCode(), saasAdmin.getMobile(), new HashMap<String, String>(8) {{
                put("channel_name", saasChannelEntity.getChannelName());
                put("money", saasOrderApplicationVo.getRealCapital().toString());
                put("name", saasBorrowerRealInfoVo.getName());
                put("phone", saasBorrowerVo.getMobile());
            }}, SaasSmsTypeEnum.SAAS_0005);
        }
    }

    private Date getOrderExpireDate(Date repaymentDt) {
        Integer borrowingDuration = DateUtil.countDay(repaymentDt, new Date());
        if (borrowingDuration < 1) {
            throw new ApplicationException(OrderErrorCodeEnum.ILLEGAL_REPAYMENT_DATE);
        } else if (borrowingDuration > OrderConsts.DEFAULT_ORDER_EXPIRE_DURATION) {
            return DateUtil.addDate(new Date(), OrderConsts.DEFAULT_ORDER_EXPIRE_DURATION);
        } else {
            return repaymentDt;
        }
    }

    public List<H5OrderListVo> listH5Order(String borrowerCode, String merchantCode) {
        List<SaasOrderVo> saasOrderVoList = saasOrderService.listAllConfirmReceiptOrderByBorrowerCode(borrowerCode);
        List<H5OrderListVo> results = new ArrayList<>(20);
        if (CollectionUtils.isNotEmpty(saasOrderVoList)) {
            saasOrderVoList.forEach(saasOrderVo -> {
                H5OrderListVo h5OrderListVo = new H5OrderListVo();
                h5OrderListVo.setOrderNumb(saasOrderVo.getOrderNumb());
                h5OrderListVo.setAmount(orderCalculateApplication.getAmount(saasOrderVo).toString());
                h5OrderListVo.setRepaymentDt(DateUtil.getDate(saasOrderVo.getRepaymentDt()));
                h5OrderListVo.setOrderStatus(saasOrderService.getOrderStatusByOrderNumb(saasOrderVo.getOrderNumb()).getCode());
                h5OrderListVo.setViewType(H5OrderBillDetailViewTypeEnum.getByOrderStatus(h5OrderListVo.getOrderStatus()).getCode());
                results.add(h5OrderListVo);
            });
        }
        List<SaasOrderBillDetailVo> saasOrderBillDetailVoList = saasOrderBillDetailService.listByBorrowerCodeAndMerchantCode(borrowerCode, merchantCode);
        if (CollectionUtils.isNotEmpty(saasOrderBillDetailVoList)) {
            saasOrderBillDetailVoList.forEach(saasOrderBillDetailVo -> {
                H5OrderListVo h5OrderListVo = new H5OrderListVo();
                h5OrderListVo.setOrderNumb(saasOrderBillDetailVo.getOrderNumb());
                h5OrderListVo.setAmount(orderCalculateApplication.getAmount(saasOrderBillDetailVo).toString());
                h5OrderListVo.setRepaymentDt(DateUtil.getDate(saasOrderBillDetailVo.getRepaymentDt()));
                OrderStatusEnum orderStatusEnum = saasOrderService.getOrderStatusByOrderNumb(saasOrderBillDetailVo.getOrderNumb());
                if (orderStatusEnum.equals(OrderStatusEnum.IN_EXTEND)) {
                    h5OrderListVo.setOrderStatus(OrderStatusEnum.FOR_REIMBURSEMENT.getCode());
                } else {
                    h5OrderListVo.setOrderStatus(orderStatusEnum.getCode());
                }
                h5OrderListVo.setViewType(H5OrderBillDetailViewTypeEnum.getByOrderStatus(h5OrderListVo.getOrderStatus()).getCode());
                results.add(h5OrderListVo);
            });
        }
        return results;
    }

    public OrderDetailVo getOrderDetailVoByOrderNumbAndMerchantCode(String orderNumb, String merchantCode) {
        SaasOrderVo mainSaasOrderVo = saasOrderService.getMainSaasOrderByOrderNumb(orderNumb);
        if (mainSaasOrderVo == null || !mainSaasOrderVo.getMerchantCode().equals(merchantCode)) {
            return null;
        }
        OrderDetailVo orderDetailVo = convertSaasOrderVo2OrderDetailVo(mainSaasOrderVo);
        OrderStatusEnum orderStatusEnum = saasOrderService.getOrderStatusByOrderNumb(orderNumb);
        orderDetailVo.setOrderStatus(orderStatusEnum.getCode());

        SaasOrderBillDetailVo saasOrderBillDetailVo = saasOrderBillDetailService.getVisibleOrderBillDetailByOrderNumbAndMerchantCode(orderNumb, merchantCode);
        if (saasOrderBillDetailVo != null) {
            orderDetailVo.setAmount(orderCalculateApplication.getAmount(saasOrderBillDetailVo).toString());
            orderDetailVo.setRepaymentDt(DateUtil.getDate(saasOrderBillDetailVo.getRepaymentDt()));
        } else {
            orderDetailVo.setAmount(orderCalculateApplication.getAmount(mainSaasOrderVo).toString());
            orderDetailVo.setRepaymentDt(DateUtil.getDate(mainSaasOrderVo.getRepaymentDt()));
        }
        SaasBorrowerRealInfoVo realInfoVo = saasBorrowerRealInfoService.getBorrowerRealInfoByBorrowerCode(mainSaasOrderVo.getBorrowerCode());
        if (realInfoVo != null) {
            orderDetailVo.setBorrowerName(realInfoVo.getName());
            orderDetailVo.setBorrowerIdentityCode(realInfoVo.getIdentityCode());
        }
        if (OrderStatusEnum.TO_CONFIRM_EXTEND.equals(orderStatusEnum)) {
            SaasOrderVo extendSaasOrderVo = saasOrderService.getConfirmExtendOrderByOrderNumb(orderNumb);
            ExtendOrderDetailVo extendOrderDetailVo = new ExtendOrderDetailVo();
            extendOrderDetailVo.setExtendDuration(DateUtil.countDay(extendSaasOrderVo.getRepaymentDt(), extendSaasOrderVo.getGmtCreate()));
            extendOrderDetailVo.setRepaymentDt(DateUtil.getDate(extendSaasOrderVo.getRepaymentDt()));
            extendOrderDetailVo.setExtendTermUrl(extendSaasOrderVo.getTermUrl());
            extendOrderDetailVo.setTotalInterestRatio(orderCalculateApplication.getInterestRatio(extendSaasOrderVo.getTotalInterestRatio()));
            extendOrderDetailVo.setExtendTitle("确认展期");
            orderDetailVo.setTermUrl(extendSaasOrderVo.getTermUrl());
            orderDetailVo.setExtendOrderDetailVos(Arrays.asList(extendOrderDetailVo));
        }
        return orderDetailVo;
    }

    private OrderDetailVo convertSaasOrderVo2OrderDetailVo(SaasOrderVo saasOrderVo) {
        OrderDetailVo orderDetailVo = new OrderDetailVo();
        orderDetailVo.setRealCapital(saasOrderVo.getRealCapital().toString());
        orderDetailVo.setBorrowingDuration(DateUtil.countDay(saasOrderVo.getRepaymentDt(), saasOrderVo.getCreatedDt()));
        orderDetailVo.setTotalInterestRatio(orderCalculateApplication.getInterestRatio(saasOrderVo.getTotalInterestRatio()));
        orderDetailVo.setRepaymentDt(DateUtil.getDate(saasOrderVo.getRepaymentDt()));
        orderDetailVo.setOrderStatus(saasOrderVo.getOrderStatus());
        orderDetailVo.setBorrowPurpose(saasOrderVo.getBorrowPurpose());
        orderDetailVo.setTermUrl(saasOrderVo.getTermUrl());
        return orderDetailVo;
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void saveOrderRemark(String merchantCode, String operatorCode, String orderNumb, String remark) {
        SaasOrderVo saasOrderVo = saasOrderService.getByOrderNumbAndMerchantCode(orderNumb, merchantCode);
        if (StringUtils.isEmpty(remark)) {
            if (StringUtils.isEmpty(saasOrderVo.getRemark())) {
                return;
            }
        } else {
            remark = remark.trim();
            if (remark.equals(saasOrderVo.getRemark())) {
                return;
            }
        }
        if (!saasOrderService.updateOrderRemark(saasOrderVo.getSaasOrderId(), remark)) {
            return;
        }
        SaasOrderStatusHistory saasOrderStatusHistory = new SaasOrderStatusHistory();
        saasOrderStatusHistory.setOrderId(saasOrderVo.getSaasOrderId());
        saasOrderStatusHistory.setOrderNumb(orderNumb);
        saasOrderStatusHistory.setCurrentOrderStatus(saasOrderVo.getOrderStatus());
        saasOrderStatusHistory.setRemark(remark);
        saasOrderStatusHistory.setOperatorCode(operatorCode);
        saasOrderStatusHistoryService.create(saasOrderStatusHistory);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void updateOrderStatus(String merchantCode, String operatorCode, String orderNumb, OrderStatusEnum updateOrderStatus, String remark) {
        SaasOrderVo saasOrderVo = saasOrderService.getByOrderNumbAndMerchantCode(orderNumb, merchantCode);

        OrderStatusEnum nextOrderStatus = OrderStatusEnum.getEnumByCode(updateOrderStatus.getCode());
        OrderStatusEnum currentOrderStatus = OrderStatusEnum.getEnumByCode(saasOrderVo.getOrderStatus());

        if (nextOrderStatus.getCodeArray() == null || Arrays.binarySearch(nextOrderStatus.getCodeArray(), currentOrderStatus.getCode()) > -1) {
            updateOrderStatus(operatorCode, saasOrderVo.getSaasOrderId(), saasOrderVo.getVersion(), saasOrderVo.getOrderNumb(), currentOrderStatus, updateOrderStatus, remark);
        } else {
            if (nextOrderStatus.getNeedForcedToUpdate()) {
                LOG.warn("......订单操作非法.......当前订单状态:{};更新订单状态:{}", currentOrderStatus.getMsg(), nextOrderStatus.getMsg());
                throw new ApplicationException(OrderErrorCodeEnum.ILLEGAL_OPERATION_ORDER_STATUS);
            }
        }
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
            querySaasOrderVo.setOrderStatusList(Arrays.asList(
                    OrderStatusEnum.SUBMIT_PRELIMINARY_REVIEW.getCode(),
                    OrderStatusEnum.IN_PRELIMINARY_REVIEWER.getCode(),
                    OrderStatusEnum.PRELIMINARY_REVIEWER_REJECT.getCode()));
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
            querySaasOrderVo.setOrderStatusList(Arrays.asList(
                    OrderStatusEnum.SUBMIT_FINAL_REVIEW.getCode(),
                    OrderStatusEnum.IN_FINAL_REVIEWER.getCode(),
                    OrderStatusEnum.FINAL_REVIEWER_REJECT.getCode()));
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
        if (queryOrderVo.getOrderStatus() != null) {
            querySaasOrderVo.setOrderStatusList(Arrays.asList(queryOrderVo.getOrderStatus()));
        } else {
            querySaasOrderVo.setOrderStatusList(Arrays.asList(OrderStatusEnum.SUBMIT_LOAN_LENDER.getCode(),
                    OrderStatusEnum.TO_CONFIRM_RECEIPT.getCode()));
        }
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
    }

    /**
     * 数据看板放款数据
     *
     * @param merchantCode
     */
    public DataDashboardLoanShowVo getDataDashboardInfo(String merchantCode) {
        //放款数据
        LoanDataDetailVo loanDataDetailVo = saasOrderBillDetailService.getLoanDataDetailVo(merchantCode);
        LoanDataDetailShowVo loanDataDetailShowVo = new LoanDataDetailShowVo();
        if (loanDataDetailVo != null) {
            BeanUtils.copyProperties(loanDataDetailVo, loanDataDetailShowVo);
        }

        //账户信息
        SaasMerchantCreditInfoEntity creditInfoByMerchantCode = saasMerchantCreditInfoService.getCreditInfoByMerchantCode(merchantCode);
        SaasMerchantSmsInfoEntity smsInfoByMerchantCode = saasMerchantSmsInfoService.getSmsInfoByMerchantCode(merchantCode);
        SaasMerchantBalanceInfoEntity balanceInfoEntity = saasMerchantBalanceInfoService.getMerchantBalanceInfoByMerchantCode(merchantCode);

        DataDashboardLoanShowVo dataDashboardLoanShowVo = new DataDashboardLoanShowVo()
                .setLoanDataDetailVo(loanDataDetailShowVo)
                .setMerchantBalance(balanceInfoEntity == null ? BigDecimal.ZERO : balanceInfoEntity.getValue())
                .setMerchantCredit(creditInfoByMerchantCode == null ? 0L : creditInfoByMerchantCode.getValue())
                .setMerchantSms(smsInfoByMerchantCode == null ? 0L : smsInfoByMerchantCode.getValue());

        //走势曲线图
        List<LoanStateDetailVo> loanStateDetailVos = saasOrderBillDetailService.getLoanStateDetailList(merchantCode);
        List<LoanStateDetailShowVo> stateDetailShowVos = new ArrayList<>();
        loanStateDetailVos.stream().forEach(x -> {
            LoanStateDetailShowVo loanStateDetailShowVo = new LoanStateDetailShowVo();
            BeanUtils.copyProperties(x, loanStateDetailShowVo);
            stateDetailShowVos.add(loanStateDetailShowVo);
        });
        dataDashboardLoanShowVo.setLoanStateDetailShowVos(stateDetailShowVos);
        return dataDashboardLoanShowVo;
    }

    /**
     * 数据看板逾期信息
     *
     * @param merchantCode
     */
    public List<DashboardOverdueOrderShowVo> getDataDashboardOverdueShowInfo(Integer menuType, String merchantCode, Page page) {
        List<DashboardOverdueOrderShowVo> dashboardOverdueOrderShowVos = new ArrayList<>();
        if (DashboardTypeEnum.NO_REPAY.getType().equals(menuType)) {
            List<DashboardOrderVo> noRepayOrderVos = saasOrderBillDetailService.getNoRepayOrderListByPage(merchantCode, page);
            dashboardOverdueOrderShowVos = this.getDashboardOrderShowList(noRepayOrderVos);

        }
        if (DashboardTypeEnum.OVERDUE.getType().equals(menuType)) {
            List<DashboardOrderVo> overdueOrderVos = saasOrderBillDetailService.getOverdueOrderListByPage(merchantCode, page);
            dashboardOverdueOrderShowVos = this.getDashboardOrderShowList(overdueOrderVos);
        }

        return dashboardOverdueOrderShowVos;
    }

    /**
     * 获取数据看板订单信息
     *
     * @param dashboardOrderVos
     * @return
     */
    private List<DashboardOverdueOrderShowVo> getDashboardOrderShowList(List<DashboardOrderVo> dashboardOrderVos) {
        List<DashboardOverdueOrderShowVo> dashboardOverdueOrderShowVos = new ArrayList<>();
        dashboardOrderVos.stream().forEach(x -> {
            SaasBorrowerVo borrower = saasBorrowerService.getByBorrowerCode(x.getBorrowCode());
            SaasBorrowerRealInfoVo infoVo = saasBorrowerRealInfoService.getBorrowerRealInfoByBorrowerCode(x.getBorrowCode());
            DashboardOverdueOrderShowVo dashboardOverdueOrderShowVo = new DashboardOverdueOrderShowVo();
            dashboardOverdueOrderShowVo.setRealCapital(x.getRealCapital());
            if (borrower != null) {
                dashboardOverdueOrderShowVo.setMobile(borrower.getMobile());
            }
            if (infoVo != null) {
                dashboardOverdueOrderShowVo.setName(infoVo.getName());
            }
            dashboardOverdueOrderShowVos.add(dashboardOverdueOrderShowVo);

        });

        return dashboardOverdueOrderShowVos;
    }

    private SaasOrderListVo convertSaasOrderVo2SaasOrderListVo(SaasOrderVo saasOrderVo) {
        SaasOrderListVo orderListVo = new SaasOrderListVo();
        orderListVo.setOrderNumb(saasOrderVo.getOrderNumb());
        orderListVo.setApplyDate(DateUtil.getDateTime(saasOrderVo.getGmtCreate()));
        orderListVo.setCapital(saasOrderVo.getRealCapital().toString());
        orderListVo.setRepaymentDate(DateUtil.getDate(saasOrderVo.getRepaymentDt()));
        orderListVo.setTotalInterestRatio(orderCalculateApplication.getInterestRatio(saasOrderVo.getTotalInterestRatio()));
        orderListVo.setOrderStatus(OrderStatusEnum.getEnumByCode(saasOrderVo.getOrderStatus()).getMsg());
        orderListVo.setRemark(saasOrderVo.getRemark());
        orderListVo.setBorrowingDuration(DateUtil.countDay(saasOrderVo.getRepaymentDt(), new Date()) + "天");
        BorrowerInfoVo borrowerInfoVo = borrowerApplication.getBorrowerInfoVoByBorrowerCode(saasOrderVo.getMerchantCode(), saasOrderVo.getBorrowerCode());
        if (borrowerInfoVo != null) {
            BeanUtils.copyProperties(borrowerInfoVo, orderListVo);
        }
        SaasChannelEntity saasChannelEntity = saasChannelService.getSaasChannelByChannelCode(saasOrderVo.getChannelCode());
        if (saasChannelEntity != null) {
            orderListVo.setChannelName(saasChannelEntity.getChannelName());
        }
        orderListVo.setZmCreditScore(saasBorrowerPersonalInfoService.getZmCreditScoreByBorrowerCodeAndOrderNumb(saasOrderVo.getBorrowerCode(), saasOrderVo.getOrderNumb()));
        if (StringUtils.isNotEmpty(saasOrderVo.getPreliminaryReviewerCode())) {
            orderListVo.setPreliminaryReviewer(saasAdminService.getSaasAdminByAdminCode(saasOrderVo.getPreliminaryReviewerCode()).getName());
        }
        if (StringUtils.isNotEmpty(saasOrderVo.getFinalReviewerCode())) {
            orderListVo.setFinalReviewer(saasAdminService.getSaasAdminByAdminCode(saasOrderVo.getFinalReviewerCode()).getName());
        }
        if (StringUtils.isNotEmpty(saasOrderVo.getLoanLenderCode())) {
            orderListVo.setLoanLender(saasAdminService.getSaasAdminByAdminCode(saasOrderVo.getLoanLenderCode()).getName());
        }
        return orderListVo;
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void preliminaryReviewerGetOrder(String merchantCode, String operatorCode, String orderNumb) {
        SaasOrderVo saasOrderVo = saasOrderService.getByOrderNumbAndMerchantCode(orderNumb, merchantCode);
        if (StringUtils.isNotEmpty(saasOrderVo.getPreliminaryReviewerCode()) && !operatorCode.equals(saasOrderVo.getPreliminaryReviewerCode())) {
            throw new ApplicationException(OrderErrorCodeEnum.ORDER_BEING_SINGLE);
        }
        if (!saasOrderService.updatePreliminaryReviewerCode(saasOrderVo.getSaasOrderId(), operatorCode)) {
            throw new ApplicationException(OrderErrorCodeEnum.ORDER_BEING_SINGLE);
        }
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void preliminaryReviewerRefuse(String merchantCode, String operatorCode, String orderNumb) {
        SaasOrderVo saasOrderVo = saasOrderService.getByOrderNumbAndMerchantCode(orderNumb, merchantCode);
        if (StringUtils.isNotEmpty(saasOrderVo.getPreliminaryReviewerCode()) && !operatorCode.equals(saasOrderVo.getPreliminaryReviewerCode())) {
            throw new ApplicationException(OrderErrorCodeEnum.NO_PERMISSION_OPERATE_ORDER);
        }
        updateOrderStatus(merchantCode, operatorCode, orderNumb, OrderStatusEnum.PRELIMINARY_REVIEWER_REFUSE, null);
        SaasOrder updateSaasOrder = new SaasOrder();
        updateSaasOrder.setId(saasOrderVo.getSaasOrderId());
        updateSaasOrder.setPreliminaryReviewerCode(operatorCode);
        saasOrderService.updateById(updateSaasOrder);

        sendApplication.sendNotifyMessageByBorrowerCode(merchantCode, saasOrderVo.getBorrowerCode(), null, SaasSmsTypeEnum.SAAS_0006);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void preliminaryReviewerReject(String merchantCode, String operatorCode, String orderNumb) {
        SaasOrderVo saasOrderVo = saasOrderService.getByOrderNumbAndMerchantCode(orderNumb, merchantCode);
        if (StringUtils.isNotEmpty(saasOrderVo.getPreliminaryReviewerCode()) && !operatorCode.equals(saasOrderVo.getPreliminaryReviewerCode())) {
            throw new ApplicationException(OrderErrorCodeEnum.NO_PERMISSION_OPERATE_ORDER);
        }

        if (saasBorrowerRealInfoService.getBorrowerRealInfoByBorrowerCode(saasOrderVo.getBorrowerCode()) == null) {
            throw new ApplicationException(OrderErrorCodeEnum.BORROWER_NEED_REAL_NAME);
        }

        updateOrderStatus(merchantCode, operatorCode, orderNumb, OrderStatusEnum.PRELIMINARY_REVIEWER_REJECT, null);

        sendApplication.sendNotifyMessageByBorrowerCode(merchantCode, saasOrderVo.getBorrowerCode(), new HashMap<String, String>(2) {{
            put("channel_url", ShortUrlUtil.generateShortUrl(configUtil.getH5AddressURL() + "?channel=" + saasOrderVo.getChannelCode()));
        }}, SaasSmsTypeEnum.SAAS_0007);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void preliminaryReviewerAgree(String merchantCode, String operatorCode, String orderNumb) {
        SaasOrderVo saasOrderVo = saasOrderService.getByOrderNumbAndMerchantCode(orderNumb, merchantCode);
        if (StringUtils.isNotEmpty(saasOrderVo.getPreliminaryReviewerCode()) && !operatorCode.equals(saasOrderVo.getPreliminaryReviewerCode())) {
            throw new ApplicationException(OrderErrorCodeEnum.NO_PERMISSION_OPERATE_ORDER);
        }
        updateOrderStatus(merchantCode, operatorCode, orderNumb, OrderStatusEnum.SUBMIT_FINAL_REVIEW, null);
        SaasOrder updateSaasOrder = new SaasOrder();
        updateSaasOrder.setId(saasOrderVo.getSaasOrderId());
        updateSaasOrder.setPreliminaryReviewerCode(operatorCode);
        saasOrderService.updateById(updateSaasOrder);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void finalReviewerGetOrder(String merchantCode, String operatorCode, String orderNumb) {
        SaasOrderVo saasOrderVo = saasOrderService.getByOrderNumbAndMerchantCode(orderNumb, merchantCode);
        if (StringUtils.isNotEmpty(saasOrderVo.getFinalReviewerCode()) && !operatorCode.equals(saasOrderVo.getFinalReviewerCode())) {
            throw new ApplicationException(OrderErrorCodeEnum.ORDER_BEING_SINGLE);
        }
        if (!saasOrderService.updateFinalReviewerCode(saasOrderVo.getSaasOrderId(), operatorCode)) {
            throw new ApplicationException(OrderErrorCodeEnum.ORDER_BEING_SINGLE);
        }
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void finalReviewerRefuse(String merchantCode, String operatorCode, String orderNumb) {
        SaasOrderVo saasOrderVo = saasOrderService.getByOrderNumbAndMerchantCode(orderNumb, merchantCode);
        if (StringUtils.isNotEmpty(saasOrderVo.getFinalReviewerCode()) && !operatorCode.equals(saasOrderVo.getFinalReviewerCode())) {
            throw new ApplicationException(OrderErrorCodeEnum.NO_PERMISSION_OPERATE_ORDER);
        }
        updateOrderStatus(merchantCode, operatorCode, orderNumb, OrderStatusEnum.FINAL_REVIEWER_REFUSE, null);
        SaasOrder updateSaasOrder = new SaasOrder();
        updateSaasOrder.setId(saasOrderVo.getSaasOrderId());
        updateSaasOrder.setFinalReviewerCode(operatorCode);
        saasOrderService.updateById(updateSaasOrder);

        sendApplication.sendNotifyMessageByBorrowerCode(merchantCode, saasOrderVo.getBorrowerCode(), null, SaasSmsTypeEnum.SAAS_0006);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void finalReviewerReject(String merchantCode, String operatorCode, String orderNumb) {
        SaasOrderVo saasOrderVo = saasOrderService.getByOrderNumbAndMerchantCode(orderNumb, merchantCode);
        if (StringUtils.isNotEmpty(saasOrderVo.getFinalReviewerCode()) && !operatorCode.equals(saasOrderVo.getFinalReviewerCode())) {
            throw new ApplicationException(OrderErrorCodeEnum.NO_PERMISSION_OPERATE_ORDER);
        }
        updateOrderStatus(merchantCode, operatorCode, orderNumb, OrderStatusEnum.FINAL_REVIEWER_REJECT, null);

        sendApplication.sendNotifyMessageByBorrowerCode(merchantCode, saasOrderVo.getBorrowerCode(), new HashMap<String, String>(2) {{
            put("channel_url", ShortUrlUtil.generateShortUrl(configUtil.getH5AddressURL() + "?channel=" + saasOrderVo.getChannelCode()));
        }}, SaasSmsTypeEnum.SAAS_0007);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void finalReviewerAgree(String merchantCode, String operatorCode, String orderNumb) {
        SaasOrderVo saasOrderVo = saasOrderService.getByOrderNumbAndMerchantCode(orderNumb, merchantCode);
        if (StringUtils.isNotEmpty(saasOrderVo.getFinalReviewerCode()) && !operatorCode.equals(saasOrderVo.getFinalReviewerCode())) {
            throw new ApplicationException(OrderErrorCodeEnum.NO_PERMISSION_OPERATE_ORDER);
        }
        updateOrderStatus(merchantCode, operatorCode, orderNumb, OrderStatusEnum.SUBMIT_LOAN_LENDER, null);
        SaasOrder updateSaasOrder = new SaasOrder();
        updateSaasOrder.setId(saasOrderVo.getSaasOrderId());
        updateSaasOrder.setFinalReviewerCode(operatorCode);
        saasOrderService.updateById(updateSaasOrder);
        String borrowerCode = saasOrderVo.getBorrowerCode();
        SaasBorrowerVo saasBorrowerVo = saasBorrowerService.getByBorrowerCode(borrowerCode);
        sendApplication.sendNotifyMessage(merchantCode, saasBorrowerVo.getMobile(), null, SaasSmsTypeEnum.SAAS_0008);
        SaasBorrowerRealInfoVo saasBorrowerRealInfoVo = saasBorrowerRealInfoService.getBorrowerRealInfoByBorrowerCode(borrowerCode);
        sendApplication.sendNotifyMessageByChannelCode(merchantCode, saasOrderVo.getChannelCode(), new HashMap<String, String>(4) {{
            put("name", saasBorrowerRealInfoVo.getName());
            put("phone", saasBorrowerVo.getMobile());
        }}, SaasSmsTypeEnum.SAAS_0009);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void lenderAgree(String merchantCode, String operatorCode, String orderNumb, String lendRemark) {
        SaasOrderVo saasOrderVo = saasOrderService.getByOrderNumbAndMerchantCode(orderNumb, merchantCode);
        if (StringUtils.isNotEmpty(saasOrderVo.getLoanLenderCode()) && !operatorCode.equals(saasOrderVo.getLoanLenderCode())) {
            throw new ApplicationException(OrderErrorCodeEnum.NO_PERMISSION_OPERATE_ORDER);
        }
        SaasOrder updateSaasOrder = new SaasOrder();
        updateSaasOrder.setId(saasOrderVo.getSaasOrderId());
        updateSaasOrder.setLoanLenderCode(operatorCode);

        if (saasOrderVo.getBorrowerAuthorizedSignLoan()) {
            updateOrderStatus(merchantCode, operatorCode, orderNumb, OrderStatusEnum.FOR_REIMBURSEMENT, lendRemark);
            updateSaasOrder.setCreatedDt(new Date());
            updateSaasOrder.setExpireDate(DateUtil.addYear(new Date(), 10));
            updateSaasOrder.setTotalInterestFee(orderCalculateApplication.getInterest(saasOrderVo.getRealCapital(), saasOrderVo.getTotalInterestRatio(), new Date(), saasOrderVo.getRepaymentDt(), Boolean.FALSE));
            saasOrderService.updateById(updateSaasOrder);

            orderBillDetailApplication.createOrderBillDetail(orderNumb, merchantCode);

            sendApplication.sendNotifyMessageByBorrowerCode(merchantCode, saasOrderVo.getBorrowerCode(), new HashMap<String, String>(4) {{
                put("money", saasOrderVo.getRealCapital().toString());
            }}, SaasSmsTypeEnum.SAAS_0011);
            SaasBorrowerRealInfoVo saasBorrowerRealInfoVo = saasBorrowerRealInfoService.getBorrowerRealInfoByBorrowerCode(saasOrderVo.getBorrowerCode());
            sendApplication.sendNotifyMessageByChannelCode(merchantCode, saasOrderVo.getChannelCode(), new HashMap<String, String>(4) {{
                put("name", saasBorrowerRealInfoVo.getName());
                put("money", saasOrderVo.getRealCapital().toString());
            }}, SaasSmsTypeEnum.SAAS_0012);

            ThreadPoolUtils.getTaskInstance().execute(new GenerateContractThread(contractApplication, saasOrderService, merchantCode, saasOrderVo.getSaasOrderId(), ContractTypeEnum.LENDER_DO_LOAN_CONTRACT_SIGN));
        } else {
            saasOrderService.updateById(updateSaasOrder);
            updateOrderStatus(merchantCode, operatorCode, orderNumb, OrderStatusEnum.TO_CONFIRM_RECEIPT, lendRemark);

            sendApplication.sendNotifyMessageByBorrowerCode(merchantCode, saasOrderVo.getBorrowerCode(), new HashMap<String, String>(2) {{
                put("channel_url", ShortUrlUtil.generateShortUrl(configUtil.getH5AddressURL() + "?channel=" + saasOrderVo.getChannelCode()));
            }}, SaasSmsTypeEnum.SAAS_0010);
        }

        SaasOrderLendRemarkVo addSaasOrderLendRemarkVo = new SaasOrderLendRemarkVo();
        addSaasOrderLendRemarkVo.setOrderNumb(orderNumb);
        addSaasOrderLendRemarkVo.setLendWay(lendRemark);
        addSaasOrderLendRemarkVo.setLendPersonCode(operatorCode);
        saasOrderLendRemarkService.save(addSaasOrderLendRemarkVo);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void lenderRefuse(String merchantCode, String operatorCode, String orderNumb) {
        SaasOrderVo saasOrderVo = saasOrderService.getByOrderNumbAndMerchantCode(orderNumb, merchantCode);
        if (StringUtils.isNotEmpty(saasOrderVo.getLoanLenderCode()) && !operatorCode.equals(saasOrderVo.getLoanLenderCode())) {
            throw new ApplicationException(OrderErrorCodeEnum.NO_PERMISSION_OPERATE_ORDER);
        }
        updateOrderStatus(merchantCode, operatorCode, orderNumb, OrderStatusEnum.LOAN_LENDER_REFUSE, null);
        SaasOrder updateSaasOrder = new SaasOrder();
        updateSaasOrder.setId(saasOrderVo.getSaasOrderId());
        updateSaasOrder.setLoanLenderCode(operatorCode);
        saasOrderService.updateById(updateSaasOrder);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void confirmReceipt(String merchantCode, String operatorCode, String orderNumb) {
        SaasOrderVo saasOrderVo = saasOrderService.getConfirmReceiptOrderByOrderNumb(orderNumb);
        if (saasOrderVo == null) {
            throw new ApplicationException(OrderErrorCodeEnum.ORDER_FAILURE);
        }
        String borrowerCode = saasOrderVo.getBorrowerCode();
        if (!borrowerCode.equals(operatorCode)) {
            throw new ApplicationException(OrderErrorCodeEnum.NO_PERMISSION_OPERATE_ORDER);
        }
        if (contractApplication.needDoLicenseContractSign(borrowerCode)) {
            //生成授权合同
            ThreadPoolUtils.getTaskInstance().execute(new GenerateContractThread(contractApplication, saasOrderService, borrowerCode, null, ContractTypeEnum.BORROWER_DO_AUTHORIZATION_CONTRACT_SIGN));
        }
        ThreadPoolUtils.getTaskInstance().execute(new GenerateContractThread(contractApplication, saasOrderService, borrowerCode, saasOrderVo.getSaasOrderId(), ContractTypeEnum.BORROWER_DO_LOAN_CONTRACT_SIGN));

        updateOrderStatus(merchantCode, operatorCode, orderNumb, OrderStatusEnum.FOR_REIMBURSEMENT, null);
        String termUrl = contractApplication.borrowerDoLoanContractSign(borrowerCode, saasOrderVo.getSaasOrderId());
        SaasOrder updateSaasOrder = new SaasOrder();
        updateSaasOrder.setId(saasOrderVo.getSaasOrderId());
        updateSaasOrder.setCreatedDt(new Date());
        updateSaasOrder.setExpireDate(DateUtil.addYear(new Date(), 10));
        updateSaasOrder.setTotalInterestFee(orderCalculateApplication.getInterest(saasOrderVo.getRealCapital(), saasOrderVo.getTotalInterestRatio(), new Date(), saasOrderVo.getRepaymentDt(), Boolean.FALSE));
        updateSaasOrder.setTermUrl(termUrl);
        saasOrderService.updateById(updateSaasOrder);

        sendApplication.sendNotifyMessageByBorrowerCode(merchantCode, saasOrderVo.getBorrowerCode(), new HashMap<String, String>(2) {{
            put("money", saasOrderVo.getRealCapital().toString());
        }}, SaasSmsTypeEnum.SAAS_0011);
        SaasBorrowerRealInfoVo saasBorrowerRealInfoVo = saasBorrowerRealInfoService.getBorrowerRealInfoByBorrowerCode(saasOrderVo.getBorrowerCode());
        sendApplication.sendNotifyMessageByChannelCode(merchantCode, saasOrderVo.getChannelCode(), new HashMap<String, String>(4) {{
            put("name", saasBorrowerRealInfoVo.getName());
            put("money", saasOrderVo.getRealCapital().toString());
        }}, SaasSmsTypeEnum.SAAS_0012);

        orderBillDetailApplication.createOrderBillDetail(orderNumb, merchantCode);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void extendOrder(String merchantCode, String operatorCode, String orderNumb, Date repaymentDt, BigDecimal extendInterestRatio) {
        SaasOrderVo saasOrderVo = saasOrderService.getByOrderNumbAndMerchantCode(orderNumb, merchantCode);

        if (DateUtil.countDay(saasOrderVo.getRepaymentDt(), repaymentDt) >= 0
                || DateUtil.countDay(new Date(), repaymentDt) >= 0) {
            LOG.warn("......应还日期非法.......展期结束日期:{};订单应还日期:{}", DateUtil.getDate(repaymentDt), DateUtil.getDate(saasOrderVo.getRepaymentDt()));
            throw new ApplicationException(OrderErrorCodeEnum.ILLEGAL_EXTEND_REPAYMENT_DATE);
        }
        OrderStatusEnum nextOrderStatus = OrderStatusEnum.TO_CONFIRM_EXTEND;
        OrderStatusEnum currentOrderStatus = OrderStatusEnum.getEnumByCode(saasOrderVo.getOrderStatus());

        if (Arrays.binarySearch(nextOrderStatus.getCodeArray(), currentOrderStatus.getCode()) < 0 && nextOrderStatus.getNeedForcedToUpdate()) {
            LOG.warn("......订单操作非法.......当前订单状态:{};更新订单状态:{}", currentOrderStatus.getMsg(), nextOrderStatus.getMsg());
            throw new ApplicationException(OrderErrorCodeEnum.ILLEGAL_OPERATION_ORDER_STATUS);
        }
        SaasOrder extendSaasOrder = new SaasOrder();
        extendSaasOrder.setOrderNumb(saasOrderVo.getOrderNumb());
        extendSaasOrder.setMerchantCode(saasOrderVo.getMerchantCode());
        extendSaasOrder.setChannelCode(saasOrderVo.getChannelCode());
        extendSaasOrder.setBorrowerCode(saasOrderVo.getBorrowerCode());
        extendSaasOrder.setRealCapital(saasOrderVo.getRealCapital());
        extendSaasOrder.setTotalInterestRatio(extendInterestRatio);
        extendSaasOrder.setLateInterestRatio(saasOrderVo.getLateInterestRatio());
        extendSaasOrder.setBorrowPurpose(saasOrderVo.getBorrowPurpose());
        extendSaasOrder.setRepaymentDt(repaymentDt);
        if (DateUtil.countDay(new Date(), saasOrderVo.getRepaymentDt()) > 0) {
            extendSaasOrder.setCreatedDt(DateUtil.addDate(new Date(), 1));
        } else {
            extendSaasOrder.setCreatedDt(DateUtil.addDate(saasOrderVo.getRepaymentDt(), 1));
        }
        extendSaasOrder.setTotalInterestFee(orderCalculateApplication.getInterest(extendSaasOrder.getRealCapital(), extendSaasOrder.getTotalInterestRatio(), extendSaasOrder.getCreatedDt(), extendSaasOrder.getRepaymentDt(), Boolean.TRUE));
        extendSaasOrder.setExpireDate(DateUtil.getTodayOverTime());
        extendSaasOrder.setRelationOrderId(saasOrderVo.getSaasOrderId());
        extendSaasOrder.setOrderStatus(nextOrderStatus.getCode());
        extendSaasOrder.setPreliminaryReviewerCode(saasOrderVo.getPreliminaryReviewerCode());
        extendSaasOrder.setFinalReviewerCode(saasOrderVo.getFinalReviewerCode());
        extendSaasOrder.setLoanLenderCode(saasOrderVo.getLoanLenderCode());
        extendSaasOrder.setRemark(saasOrderVo.getRemark());
        saasOrderService.create(extendSaasOrder);

        sendApplication.sendNotifyMessageByBorrowerCode(merchantCode, saasOrderVo.getBorrowerCode(), new HashMap<String, String>(2) {{
            put("channel_url", ShortUrlUtil.generateShortUrl(configUtil.getH5AddressURL() + "?channel=" + saasOrderVo.getChannelCode()));
        }}, SaasSmsTypeEnum.SAAS_0014);

        SaasOrderStatusHistory saasOrderStatusHistory = new SaasOrderStatusHistory();
        saasOrderStatusHistory.setOrderId(extendSaasOrder.getId());
        saasOrderStatusHistory.setOrderNumb(extendSaasOrder.getOrderNumb());
        saasOrderStatusHistory.setUpdateOrderStatus(nextOrderStatus.getCode());
        saasOrderStatusHistory.setOperatorCode(operatorCode);
        saasOrderStatusHistoryService.create(saasOrderStatusHistory);

        ThreadPoolUtils.getTaskInstance().execute(new GenerateContractThread(contractApplication, saasOrderService, merchantCode, extendSaasOrder.getId(), ContractTypeEnum.LENDER_DO_EXTEND_CONTRACT_SIGN));
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void confirmExtend(String merchantCode, String operatorCode, String orderNumb) {
        SaasOrderVo saasOrderVo = saasOrderService.getByOrderNumbAndMerchantCode(orderNumb, merchantCode);
        if (!OrderStatusEnum.TO_CONFIRM_EXTEND.getCode().equals(saasOrderVo.getOrderStatus())) {
            throw new ApplicationException(OrderErrorCodeEnum.ILLEGAL_OPERATION_ORDER_STATUS);
        }
        updateOrderStatus(merchantCode, operatorCode, orderNumb, OrderStatusEnum.FOR_REIMBURSEMENT, null);

        SaasOrder updateSaasOrder = new SaasOrder();
        updateSaasOrder.setId(saasOrderVo.getSaasOrderId());
        updateSaasOrder.setExpireDate(DateUtil.addYear(saasOrderVo.getCreatedDt(), 10));
        saasOrderService.updateById(updateSaasOrder);

        ThreadPoolUtils.getTaskInstance().execute(new GenerateContractThread(contractApplication, saasOrderService, operatorCode, saasOrderVo.getSaasOrderId(), ContractTypeEnum.BORROWER_DO_EXTEND_CONTRACT_SIGN));

        SaasOrder oldSaasOrder = saasOrderService.selectById(saasOrderVo.getRelationOrderId());
        saasOrderService.updateOrderStatus(oldSaasOrder.getId(), oldSaasOrder.getVersion(), OrderStatusEnum.getEnumByCode(oldSaasOrder.getOrderStatus()), OrderStatusEnum.IN_EXTEND);

        orderBillDetailApplication.createOrderBillDetail(orderNumb, merchantCode);

        saasCollectionOrderService.deleteOrder(orderNumb);

        sendApplication.sendNotifyMessageByBorrowerCode(merchantCode, saasOrderVo.getBorrowerCode(), new HashMap<String, String>(8) {{
            put("money", saasOrderVo.getRealCapital().toString());
            put("day", String.valueOf(DateUtil.countDay(saasOrderVo.getRepaymentDt(), saasOrderVo.getGmtCreate())));
            put("date", DateUtil.getDate(saasOrderVo.getRepaymentDt()));
        }}, SaasSmsTypeEnum.SAAS_0015);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void destroyOrder(String merchantCode, String operatorCode, String orderNumb) {

        updateOrderStatus(merchantCode, operatorCode, orderNumb, OrderStatusEnum.HAS_BEEN_DESTROY, null);
        orderBillDetailApplication.destroyOrderBillDetail(orderNumb, merchantCode);

        saasCollectionOrderService.closeOrder(orderNumb);

        SaasOrderVo saasOrderVo = saasOrderService.getByOrderNumbAndMerchantCode(orderNumb, merchantCode);
        sendApplication.sendNotifyMessageByBorrowerCode(merchantCode, saasOrderVo.getBorrowerCode(), new HashMap<String, String>(2) {{
            put("money", saasOrderVo.getRealCapital().toString());
        }}, SaasSmsTypeEnum.SAAS_0016);
    }

}
