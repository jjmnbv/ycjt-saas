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
import com.beitu.saas.borrower.client.SaasBorrowerRealInfoService;
import com.beitu.saas.borrower.client.SaasBorrowerService;
import com.beitu.saas.borrower.domain.SaasBorrowerRealInfoVo;
import com.beitu.saas.borrower.domain.SaasBorrowerVo;
import com.beitu.saas.channel.client.SaasChannelService;
import com.beitu.saas.channel.consts.ChannelConsts;
import com.beitu.saas.channel.entity.SaasChannelEntity;
import com.beitu.saas.collection.client.SaasCollectionOrderService;
import com.beitu.saas.common.config.ConfigUtil;
import com.beitu.saas.common.utils.DateUtil;
import com.beitu.saas.common.utils.ThreadPoolUtils;
import com.beitu.saas.finance.client.SaasMerchantBalanceInfoService;
import com.beitu.saas.finance.client.SaasMerchantCreditInfoService;
import com.beitu.saas.finance.client.SaasMerchantSmsInfoService;
import com.beitu.saas.finance.entity.SaasMerchantBalanceInfoEntity;
import com.beitu.saas.finance.entity.SaasMerchantCreditInfoEntity;
import com.beitu.saas.finance.entity.SaasMerchantSmsInfoEntity;
import com.beitu.saas.order.client.SaasOrderBillDetailService;
import com.beitu.saas.order.client.SaasOrderService;
import com.beitu.saas.order.client.SaasOrderStatusHistoryService;
import com.beitu.saas.order.domain.QuerySaasOrderVo;
import com.beitu.saas.order.domain.SaasOrderApplicationVo;
import com.beitu.saas.order.domain.SaasOrderBillDetailVo;
import com.beitu.saas.order.domain.SaasOrderVo;
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

    public BorrowerOrderApplyStatusEnum getOrderApplyStatus(String borrowerCode, String channelCode) {
        if (StringUtils.isNotEmpty(saasOrderService.getReviewerRefuseOrderNumb(borrowerCode, channelCode))) {
            return BorrowerOrderApplyStatusEnum.REFUSE;
        } else if (saasOrderService.isReviewing(borrowerCode, channelCode)) {
            return BorrowerOrderApplyStatusEnum.REVIEWING;
        }
        return BorrowerOrderApplyStatusEnum.NO_SUBMIT;
    }

    @Transactional
    public SaasOrder createOrder(SaasOrderApplicationVo saasOrderApplicationVo, String orderNumb, String channelCode) {
        SaasOrder saasOrder = new SaasOrder();
        BeanUtils.copyProperties(saasOrderApplicationVo, saasOrder);
        saasOrder.setOrderNumb(orderNumb);
        saasOrder.setChannelCode(channelCode);
        saasOrder.setCreatedDt(new Date());
        saasOrder.setOrderStatus(OrderStatusEnum.SUBMIT_PRELIMINARY_REVIEW.getCode());
        saasOrder.setExpireDate(DateUtil.addDate(new Date(), 20));
        saasOrder.setTotalInterestFee(orderCalculateApplication.getInterest(saasOrder.getRealCapital(), saasOrder.getTotalInterestRatio(), saasOrder.getCreatedDt(), saasOrder.getRepaymentDt()));
        saasOrderService.create(saasOrder);

        if (!ChannelConsts.DEFAULT_CHANNEL_CREATOR_CODE.equals(channelCode)) {
            SaasOrder updateSaasOrder = new SaasOrder();
            updateSaasOrder.setId(saasOrder.getId());
            updateSaasOrder.setTermUrl(contractApplication.borrowerDoLoanContractSign(saasOrderApplicationVo.getBorrowerCode(), saasOrder.getId()));
            saasOrderService.updateById(updateSaasOrder);
        }

        String borrowerCode = saasOrderApplicationVo.getBorrowerCode();
        SaasBorrowerVo saasBorrowerVo = saasBorrowerService.getByBorrowerCode(borrowerCode);
        sendApplication.sendNotifyMessage(saasOrderApplicationVo.getMerchantCode(), saasBorrowerVo.getMobile(), null, SaasSmsTypeEnum.SAAS_0004);
        SaasChannelEntity saasChannelEntity = saasChannelService.getSaasChannelByChannelCode(saasOrderApplicationVo.getChannelCode());
        SaasAdmin saasAdmin = saasAdminService.getSaasAdminByAdminCode(saasChannelEntity.getChargePersonCode());
        SaasBorrowerRealInfoVo saasBorrowerRealInfoVo = saasBorrowerRealInfoService.getBorrowerRealInfoByBorrowerCode(borrowerCode);

        sendApplication.sendNotifyMessage(saasOrderApplicationVo.getMerchantCode(), saasAdmin.getMobile(), new HashMap(4) {{
            put("channel_name", saasChannelEntity.getChannelName());
            put("money", saasOrderApplicationVo.getRealCapital());
            put("name", saasBorrowerRealInfoVo.getName());
            put("phone", saasBorrowerVo.getMobile());
        }}, SaasSmsTypeEnum.SAAS_0005);

        return saasOrder;
    }

    public void updateOrderByOrderApplicationVo(SaasOrderApplicationVo saasOrderApplicationVo, String orderNumb) {
        SaasOrderVo saasOrderVo = saasOrderService.getByOrderNumbAndMerchantCode(orderNumb, saasOrderApplicationVo.getMerchantCode());
        if (saasOrderVo == null) {
            throw new ApplicationException(OrderErrorCodeEnum.NO_PERMISSION_OPERATE_ORDER);
        }
        SaasOrder updateSaasOrder = new SaasOrder();
        updateSaasOrder.setRealCapital(saasOrderApplicationVo.getRealCapital());
        updateSaasOrder.setTotalInterestRatio(saasOrderApplicationVo.getTotalInterestRatio());
        updateSaasOrder.setBorrowPurpose(saasOrderApplicationVo.getBorrowPurpose());
        updateSaasOrder.setRepaymentDt(saasOrderApplicationVo.getRepaymentDt());
        updateSaasOrder.setTermUrl(saasOrderApplicationVo.getTermUrl());
        updateSaasOrder.setCreatedDt(new Date());
        updateSaasOrder.setTotalInterestFee(orderCalculateApplication.getInterest(updateSaasOrder.getRealCapital(), updateSaasOrder.getTotalInterestRatio(), updateSaasOrder.getCreatedDt(), updateSaasOrder.getRepaymentDt()));
        saasOrderService.updateById(updateSaasOrder);
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
        List<SaasOrderVo> saasOrderVoList = saasOrderService.listEffectiveOrderByOrderNumb(orderNumb);
        if (CollectionUtils.isEmpty(saasOrderVoList)) {
            return null;
        }
        OrderDetailVo orderDetailVo;
        SaasOrderVo saasOrderVo;
        if (saasOrderVoList.size() == 1) {
            saasOrderVo = saasOrderVoList.get(0);
            orderDetailVo = convertSaasOrderVo2OrderDetailVo(saasOrderVo);
            orderDetailVo.setAmount(orderCalculateApplication.getAmount(saasOrderVo).toString());
        } else {
            saasOrderVo = saasOrderVoList.get(saasOrderVoList.size() - 1);
            if (OrderStatusEnum.TO_CONFIRM_EXTEND.getCode().equals(saasOrderVo.getOrderStatus())) {
                saasOrderVo = saasOrderVoList.get(saasOrderVoList.size() - 2);
                saasOrderVo.setOrderStatus(OrderStatusEnum.TO_CONFIRM_EXTEND.getCode());
            }
            orderDetailVo = convertSaasOrderVo2OrderDetailVo(saasOrderVo);
            SaasOrderBillDetailVo saasOrderBillDetailVo = saasOrderBillDetailService.getVisibleOrderBillDetailByOrderNumbAndMerchantCode(orderNumb, merchantCode);
            orderDetailVo.setAmount(orderCalculateApplication.getAmount(saasOrderBillDetailVo).toString());
            orderDetailVo.setRepaymentDt(DateUtil.getDate(saasOrderBillDetailVo.getRepaymentDt()));
        }
        SaasBorrowerRealInfoVo realInfoVo = saasBorrowerRealInfoService.getBorrowerRealInfoByBorrowerCode(saasOrderVo.getBorrowerCode());
        if (realInfoVo != null) {
            orderDetailVo.setBorrowerName(realInfoVo.getName());
            orderDetailVo.setBorrowerIdentityCode(realInfoVo.getIdentityCode());
        }

        if (OrderStatusEnum.TO_CONFIRM_EXTEND.getCode().equals(orderDetailVo.getOrderStatus())) {
            saasOrderVo = saasOrderVoList.get(saasOrderVoList.size() - 1);
            ExtendOrderDetailVo extendOrderDetailVo = new ExtendOrderDetailVo();
            extendOrderDetailVo.setExtendDuration(DateUtil.countDay(saasOrderVo.getRepaymentDt(), saasOrderVo.getGmtCreate()));
            extendOrderDetailVo.setRepaymentDt(DateUtil.getDate(saasOrderVo.getRepaymentDt()));
            extendOrderDetailVo.setTotalInterestRatio(orderCalculateApplication.getInterestRatio(saasOrderVo.getTotalInterestRatio()));
            extendOrderDetailVo.setExtendTitle("确认展期");
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
        BeanUtils.copyProperties(loanDataDetailVo, loanDataDetailShowVo);

        //账户信息
        SaasMerchantCreditInfoEntity creditInfoByMerchantCode = saasMerchantCreditInfoService.getCreditInfoByMerchantCode(merchantCode);
        SaasMerchantSmsInfoEntity smsInfoByMerchantCode = saasMerchantSmsInfoService.getSmsInfoByMerchantCode(merchantCode);
        SaasMerchantBalanceInfoEntity balanceInfoEntity = saasMerchantBalanceInfoService.getMerchantBalanceInfoByMerchantCode(merchantCode);

        DataDashboardLoanShowVo dataDashboardLoanShowVo = new DataDashboardLoanShowVo()
                .setLoanDataDetailVo(loanDataDetailShowVo)
                .setMerchantBalance(balanceInfoEntity == null ? BigDecimal.ZERO : balanceInfoEntity.getValue())
                .setMerchantCredit(creditInfoByMerchantCode == null ? 0l : creditInfoByMerchantCode.getValue())
                .setMerchantSms(smsInfoByMerchantCode == null ? 0l : smsInfoByMerchantCode.getValue());

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
        if (menuType == DashboardTypeEnum.NO_REPAY.getType()) {
            List<DashboardOrderVo> noRepayOrderVos = saasOrderBillDetailService.getNoRepayOrderListByPage(merchantCode, page);
            dashboardOverdueOrderShowVos = this.getDashboardOrderShowList(noRepayOrderVos);

        }
        if (menuType == DashboardTypeEnum.OVERDUE.getType()) {
            List<DashboardOrderVo> overdueOrderVos = saasOrderBillDetailService.getOverdueOrderListByPage(merchantCode, page);
            dashboardOverdueOrderShowVos = this.getDashboardOrderShowList(overdueOrderVos);
        }

        return dashboardOverdueOrderShowVos;
    }

    //获取数据看板订单信息
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
        orderListVo.setApplyDate(DateUtil.getDate(saasOrderVo.getCreatedDt()));
        orderListVo.setCapital(saasOrderVo.getRealCapital().toString());
        orderListVo.setOrderStatus(OrderStatusEnum.getEnumByCode(saasOrderVo.getOrderStatus()).getMsg());
        orderListVo.setRemark(saasOrderVo.getRemark());
        orderListVo.setBorrowingDuration(DateUtil.countDay(saasOrderVo.getRepaymentDt(), saasOrderVo.getCreatedDt()) + "天");
        BorrowerInfoVo borrowerInfoVo = borrowerApplication.getBorrowerInfoVoByBorrowerCode(saasOrderVo.getMerchantCode(), saasOrderVo.getBorrowerCode());
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
        updateOrderStatus(merchantCode, operatorCode, orderNumb, OrderStatusEnum.PRELIMINARY_REVIEWER_REJECT, null);

        sendApplication.sendNotifyMessageByBorrowerCode(merchantCode, saasOrderVo.getBorrowerCode(), new HashMap(2) {{
            put("channel_url", configUtil.getAddressURLPrefix() + configUtil.getH5AddressURLPrefix() + "?channel=" + saasOrderVo.getChannelCode());
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

        sendApplication.sendNotifyMessageByBorrowerCode(merchantCode, saasOrderVo.getBorrowerCode(), new HashMap(2) {{
            put("channel_url", configUtil.getAddressURLPrefix() + configUtil.getH5AddressURLPrefix() + "?channel=" + saasOrderVo.getChannelCode());
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
        sendApplication.sendNotifyMessageByChannelCode(merchantCode, saasOrderVo.getChannelCode(), new HashMap() {{
            put("name", saasBorrowerRealInfoVo.getName());
            put("phone", saasBorrowerVo.getMobile());
        }}, SaasSmsTypeEnum.SAAS_0009);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void lenderAgree(String merchantCode, String operatorCode, String orderNumb) {
        SaasOrderVo saasOrderVo = saasOrderService.getByOrderNumbAndMerchantCode(orderNumb, merchantCode);
        if (StringUtils.isNotEmpty(saasOrderVo.getLoanLenderCode()) && !operatorCode.equals(saasOrderVo.getLoanLenderCode())) {
            throw new ApplicationException(OrderErrorCodeEnum.NO_PERMISSION_OPERATE_ORDER);
        }
        ThreadPoolUtils.getTaskInstance().execute(new GenerateContractThread(contractApplication, saasOrderService, operatorCode, saasOrderVo.getSaasOrderId(), ContractTypeEnum.LENDER_DO_LOAN_CONTRACT_SIGN));
        if (StringUtils.isNotEmpty(saasOrderVo.getTermUrl())) {
            updateOrderStatus(merchantCode, operatorCode, orderNumb, OrderStatusEnum.FOR_REIMBURSEMENT, null);
            orderBillDetailApplication.createOrderBillDetail(orderNumb, merchantCode);

            sendApplication.sendNotifyMessageByBorrowerCode(merchantCode, saasOrderVo.getBorrowerCode(), new HashMap(2) {{
                put("money", saasOrderVo.getRealCapital());
            }}, SaasSmsTypeEnum.SAAS_0011);
            SaasBorrowerRealInfoVo saasBorrowerRealInfoVo = saasBorrowerRealInfoService.getBorrowerRealInfoByBorrowerCode(saasOrderVo.getBorrowerCode());
            sendApplication.sendNotifyMessageByChannelCode(merchantCode, saasOrderVo.getChannelCode(), new HashMap(2) {{
                put("name", saasBorrowerRealInfoVo.getName());
                put("money", saasOrderVo.getRealCapital());
            }}, SaasSmsTypeEnum.SAAS_0012);
        } else {
            updateOrderStatus(merchantCode, operatorCode, orderNumb, OrderStatusEnum.TO_CONFIRM_RECEIPT, null);

            sendApplication.sendNotifyMessageByBorrowerCode(merchantCode, saasOrderVo.getBorrowerCode(), new HashMap(2) {{
                put("channel_url", configUtil.getAddressURLPrefix() + configUtil.getH5AddressURLPrefix() + "?channel=" + saasOrderVo.getChannelCode());
            }}, SaasSmsTypeEnum.SAAS_0010);
        }
        SaasOrder updateSaasOrder = new SaasOrder();
        updateSaasOrder.setId(saasOrderVo.getSaasOrderId());
        updateSaasOrder.setLoanLenderCode(operatorCode);
        saasOrderService.updateById(updateSaasOrder);
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
        updateSaasOrder.setTermUrl(termUrl);
        saasOrderService.updateById(updateSaasOrder);

        sendApplication.sendNotifyMessageByBorrowerCode(merchantCode, saasOrderVo.getBorrowerCode(), new HashMap(2) {{
            put("money", saasOrderVo.getRealCapital());
        }}, SaasSmsTypeEnum.SAAS_0011);
        SaasBorrowerRealInfoVo saasBorrowerRealInfoVo = saasBorrowerRealInfoService.getBorrowerRealInfoByBorrowerCode(saasOrderVo.getBorrowerCode());
        sendApplication.sendNotifyMessageByChannelCode(merchantCode, saasOrderVo.getChannelCode(), new HashMap(2) {{
            put("name", saasBorrowerRealInfoVo.getName());
            put("money", saasOrderVo.getRealCapital());
        }}, SaasSmsTypeEnum.SAAS_0012);

        orderBillDetailApplication.createOrderBillDetail(orderNumb, merchantCode);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void extendOrder(String merchantCode, String operatorCode, String orderNumb, Date repaymentDt, BigDecimal extendInterestRatio) {
        SaasOrderVo saasOrderVo = saasOrderService.getByOrderNumbAndMerchantCode(orderNumb, merchantCode);

        if (DateUtil.countDay(saasOrderVo.getRepaymentDt(), repaymentDt) > 0
                || DateUtil.countDay(new Date(), repaymentDt) > 0) {
            LOG.warn("......应还日期非法.......展期结束日期:{};订单应还日期:{}", DateUtil.getDate(repaymentDt), DateUtil.getDate(saasOrderVo.getRepaymentDt()));
            throw new ApplicationException(OrderErrorCodeEnum.ILLEGAL_REPAYMENT_DATE);
        }
        OrderStatusEnum nextOrderStatus = OrderStatusEnum.TO_CONFIRM_EXTEND;
        OrderStatusEnum currentOrderStatus = OrderStatusEnum.getEnumByCode(saasOrderVo.getOrderStatus());

        if (Arrays.binarySearch(nextOrderStatus.getCodeArray(), currentOrderStatus.getCode()) < 0 && nextOrderStatus.getNeedForcedToUpdate()) {
            LOG.warn("......订单操作非法.......当前订单状态:{};更新订单状态:{}", currentOrderStatus.getMsg(), nextOrderStatus.getMsg());
            throw new ApplicationException(OrderErrorCodeEnum.ILLEGAL_OPERATION_ORDER_STATUS);
        }
        SaasOrder extendSaasOrder = new SaasOrder();
        BeanUtils.copyProperties(saasOrderVo, extendSaasOrder);
        extendSaasOrder.setTotalInterestRatio(extendInterestRatio);
        extendSaasOrder.setExpireDate(DateUtil.getTodayOverTime());
        if (DateUtil.countDay(new Date(), saasOrderVo.getRepaymentDt()) > 0) {
            extendSaasOrder.setCreatedDt(DateUtil.addDate(new Date(), 1));
        } else {
            extendSaasOrder.setCreatedDt(DateUtil.addDate(saasOrderVo.getRepaymentDt(), 1));
        }
        extendSaasOrder.setRepaymentDt(repaymentDt);
        extendSaasOrder.setTotalInterestFee(orderCalculateApplication.getInterest(extendSaasOrder.getRealCapital(), extendSaasOrder.getTotalInterestRatio(), extendSaasOrder.getCreatedDt(), extendSaasOrder.getRepaymentDt()));
        extendSaasOrder.setOrderStatus(nextOrderStatus.getCode());
        extendSaasOrder.setRelationOrderId(saasOrderVo.getSaasOrderId());
        saasOrderService.create(extendSaasOrder);

        ThreadPoolUtils.getTaskInstance().execute(new GenerateContractThread(contractApplication, saasOrderService, operatorCode, extendSaasOrder.getId(), ContractTypeEnum.LENDER_DO_EXTEND_CONTRACT_SIGN));

        sendApplication.sendNotifyMessageByBorrowerCode(merchantCode, saasOrderVo.getBorrowerCode(), new HashMap(2) {{
            put("channel_url", configUtil.getAddressURLPrefix() + configUtil.getH5AddressURLPrefix() + "?channel=" + saasOrderVo.getChannelCode());
        }}, SaasSmsTypeEnum.SAAS_0014);

        SaasOrderStatusHistory saasOrderStatusHistory = new SaasOrderStatusHistory();
        saasOrderStatusHistory.setOrderId(extendSaasOrder.getId());
        saasOrderStatusHistory.setOrderNumb(extendSaasOrder.getOrderNumb());
        saasOrderStatusHistory.setUpdateOrderStatus(nextOrderStatus.getCode());
        saasOrderStatusHistory.setOperatorCode(operatorCode);
        saasOrderStatusHistoryService.create(saasOrderStatusHistory);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void confirmExtend(String merchantCode, String operatorCode, String orderNumb) {
        SaasOrderVo saasOrderVo = saasOrderService.getByOrderNumbAndMerchantCode(orderNumb, merchantCode);
        if (!OrderStatusEnum.TO_CONFIRM_EXTEND.getCode().equals(saasOrderVo.getOrderStatus())) {
            throw new ApplicationException(OrderErrorCodeEnum.ILLEGAL_OPERATION_ORDER_STATUS);
        }
        updateOrderStatus(merchantCode, operatorCode, orderNumb, OrderStatusEnum.FOR_REIMBURSEMENT, null);

        ThreadPoolUtils.getTaskInstance().execute(new GenerateContractThread(contractApplication, saasOrderService, operatorCode, saasOrderVo.getSaasOrderId(), ContractTypeEnum.BORROWER_DO_EXTEND_CONTRACT_SIGN));

        SaasOrder oldSaasOrder = saasOrderService.selectById(saasOrderVo.getRelationOrderId());
        saasOrderService.updateOrderStatus(oldSaasOrder.getId(), oldSaasOrder.getVersion(), OrderStatusEnum.getEnumByCode(oldSaasOrder.getOrderStatus()), OrderStatusEnum.IN_EXTEND);

        orderBillDetailApplication.createOrderBillDetail(orderNumb, merchantCode);

        saasCollectionOrderService.deleteOrder(orderNumb);

        sendApplication.sendNotifyMessageByBorrowerCode(merchantCode, saasOrderVo.getBorrowerCode(), new HashMap(2) {{
            put("money", saasOrderVo.getRealCapital());
            put("day", DateUtil.countDay(saasOrderVo.getRepaymentDt(), saasOrderVo.getGmtCreate()));
            put("date", saasOrderVo.getRepaymentDt());
        }}, SaasSmsTypeEnum.SAAS_0015);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void destroyOrder(String merchantCode, String operatorCode, String orderNumb) {

        updateOrderStatus(merchantCode, operatorCode, orderNumb, OrderStatusEnum.HAS_BEEN_DESTROY, null);
        orderBillDetailApplication.destroyOrderBillDetail(orderNumb, merchantCode);

        saasCollectionOrderService.closeOrder(orderNumb);

        SaasOrderVo saasOrderVo = saasOrderService.getByOrderNumbAndMerchantCode(orderNumb, merchantCode);
        sendApplication.sendNotifyMessageByBorrowerCode(merchantCode, saasOrderVo.getBorrowerCode(), new HashMap(2) {{
            put("money", saasOrderVo.getRealCapital());
        }}, SaasSmsTypeEnum.SAAS_0016);
    }

}
