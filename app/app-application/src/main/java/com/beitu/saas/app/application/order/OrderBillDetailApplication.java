package com.beitu.saas.app.application.order;

import com.beitu.saas.app.application.borrower.BorrowerApplication;
import com.beitu.saas.app.application.borrower.vo.BorrowerInfoVo;
import com.beitu.saas.app.application.order.vo.QueryOrderBillDetailVo;
import com.beitu.saas.app.application.order.vo.SaasOrderBillDetailListVo;
import com.beitu.saas.app.application.order.vo.SaasOrderDetailVo;
import com.beitu.saas.app.enums.QueryRepaymentDtEnum;
import com.beitu.saas.channel.client.SaasChannelService;
import com.beitu.saas.channel.entity.SaasChannelEntity;
import com.beitu.saas.collection.client.SaasCollectionOrderService;
import com.beitu.saas.common.utils.DateUtil;
import com.beitu.saas.order.client.SaasOrderBillDetailService;
import com.beitu.saas.order.client.SaasOrderLendRemarkService;
import com.beitu.saas.order.client.SaasOrderService;
import com.beitu.saas.order.client.SaasOrderStatusHistoryService;
import com.beitu.saas.order.domain.QuerySaasOrderBillDetailVo;
import com.beitu.saas.order.domain.SaasOrderBillDetailVo;
import com.beitu.saas.order.domain.SaasOrderVo;
import com.beitu.saas.order.entity.SaasOrderBillDetail;
import com.beitu.saas.order.enums.OrderStatusEnum;
import com.fqgj.common.api.Page;
import com.fqgj.common.utils.CollectionUtils;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author linanjun
 * @create 2018/3/27 下午10:19
 * @description 账单
 */
@Service
public class OrderBillDetailApplication {

    private static final Log LOGGER = LogFactory.getLog(OrderBillDetailApplication.class);

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

    @Autowired
    private SaasCollectionOrderService saasCollectionOrderService;

    @Autowired
    private SaasOrderLendRemarkService saasOrderLendRemarkService;

    public List<SaasOrderBillDetailListVo> listAfterLendManageOrder(QueryOrderBillDetailVo queryVo, Page page) {
        QuerySaasOrderBillDetailVo querySaasOrderBillDetailVo = convertQueryOrderBillDetailVo2QuerySaasOrderBillDetailVo(queryVo);
        if (querySaasOrderBillDetailVo == null) {
            return null;
        }
        if (queryVo.getOrderStatus() != null) {
            querySaasOrderBillDetailVo.setQueryOrderStatus(queryVo.getOrderStatus());
            if (OrderStatusEnum.TO_CONFIRM_EXTEND.getCode().equals(queryVo.getOrderStatus())) {
                List<String> orderNumbList = saasOrderService.listAllConfirmExtendOrderNumbByMerchantCode(queryVo.getMerchantCode());
                if (CollectionUtils.isEmpty(orderNumbList)) {
                    return null;
                }
                querySaasOrderBillDetailVo.setOrderNumbList(orderNumbList);
            }
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
        querySaasOrderBillDetailVo.setDestroy(Boolean.FALSE);
        List<SaasOrderBillDetailVo> saasOrderBillDetailVoList = saasOrderBillDetailService.listByQueryOrderBillDetailVoAndPage(querySaasOrderBillDetailVo, page);
        if (CollectionUtils.isEmpty(saasOrderBillDetailVoList)) {
            return null;
        }
        List<SaasOrderBillDetailListVo> results = new ArrayList<>(saasOrderBillDetailVoList.size());
        saasOrderBillDetailVoList.forEach(saasOrderBillDetailVo -> {
            SaasOrderBillDetailListVo saasOrderBillDetailListVo = convertSaasOrderBillDetailVo2SaasOrderBillDetailListVo(saasOrderBillDetailVo);
            Integer collectionRows = saasCollectionOrderService.getTotalCollectionOrderCount(saasOrderBillDetailVo.getOrderNumb());
            saasOrderBillDetailListVo.setEntrustCollect(collectionRows > 0 ? false : true);
            results.add(saasOrderBillDetailListVo);
        });
        return results;
    }

    private QuerySaasOrderBillDetailVo convertQueryOrderBillDetailVo2QuerySaasOrderBillDetailVo(QueryOrderBillDetailVo queryVo) {
        QuerySaasOrderBillDetailVo querySaasOrderBillDetailVo = new QuerySaasOrderBillDetailVo();
        querySaasOrderBillDetailVo.setMerchantCode(queryVo.getMerchantCode());
        querySaasOrderBillDetailVo.setChannelCode(queryVo.getChannelCode());
        // 贷后管理 还款时间 查询参数
        if (queryVo.getRepaymentDuration() != null) {
            Date endDate = new Date();
            if (queryVo.getRepaymentEndDate() != null) {
                querySaasOrderBillDetailVo.setRepaymentBeginDt(queryVo.getRepaymentEndDate());
                endDate = queryVo.getRepaymentEndDate();
            }
            querySaasOrderBillDetailVo.setRepaymentEndDt(DateUtil.addDate(endDate, queryVo.getRepaymentDuration()));
        }
        // 逾期管理 还款时间 查询参数
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
        Integer overdueDuration = DateUtil.countDay(new Date(), saasOrderBillDetailVo.getRepaymentDt());
        if (overdueDuration > 0) {
            saasOrderBillDetailListVo.setOverdueDuration(overdueDuration);
        } else {
            saasOrderBillDetailListVo.setOverdueDuration(0);
        }
        BorrowerInfoVo borrowerInfoVo = borrowerApplication.getBorrowerInfoVoByBorrowerCode(saasOrderBillDetailVo.getMerchantCode(), saasOrderBillDetailVo.getBorrowerCode());
        if (borrowerInfoVo != null) {
            saasOrderBillDetailListVo.setBorrowerName(borrowerInfoVo.getBorrowerName());
            saasOrderBillDetailListVo.setBorrowerMobile(borrowerInfoVo.getBorrowerMobile());
        }

        saasOrderBillDetailListVo.setLoanLendRemark(saasOrderLendRemarkService.getLendWayByOrderNumb(saasOrderBillDetailVo.getOrderNumb()));
        SaasChannelEntity saasChannelEntity = saasChannelService.getSaasChannelByChannelCode(saasOrderBillDetailVo.getChannelCode());
        if (saasChannelEntity != null) {
            saasOrderBillDetailListVo.setChannelName(saasChannelEntity.getChannelName());
        }
        OrderStatusEnum orderStatusEnum = saasOrderService.getOrderStatusByOrderNumb(saasOrderBillDetailVo.getOrderNumb());
        if (orderStatusEnum.equals(OrderStatusEnum.IN_EXTEND)) {
            saasOrderBillDetailListVo.setOrderStatus(OrderStatusEnum.FOR_REIMBURSEMENT.getMsg());
        } else {
            saasOrderBillDetailListVo.setOrderStatus(orderStatusEnum.getMsg());
        }

        return saasOrderBillDetailListVo;
    }

    private SaasOrderBillDetailListVo convertSaasOrderVo2SaasOrderBillDetailListVo(SaasOrderVo saasOrderVo) {
        SaasOrderBillDetailListVo saasOrderBillDetailListVo = new SaasOrderBillDetailListVo();
        saasOrderBillDetailListVo.setOrderNumb(saasOrderVo.getOrderNumb());
        saasOrderBillDetailListVo.setCapital(saasOrderVo.getRealCapital().toString());
        saasOrderBillDetailListVo.setAmount(orderCalculateApplication.getAmount(saasOrderVo).toString());
        saasOrderBillDetailListVo.setCreatedDate(DateUtil.getDate(saasOrderVo.getCreatedDt()));
        saasOrderBillDetailListVo.setRepaymentDate(DateUtil.getDate(saasOrderVo.getRepaymentDt()));
        saasOrderBillDetailListVo.setExtend(saasOrderVo.getRelationOrderId() != null);
        saasOrderBillDetailListVo.setOverdueDuration(DateUtil.countDay(new Date(), saasOrderVo.getRepaymentDt()));

        BorrowerInfoVo borrowerInfoVo = borrowerApplication.getBorrowerInfoVoByBorrowerCode(saasOrderVo.getMerchantCode(), saasOrderVo.getBorrowerCode());
        if (borrowerInfoVo != null) {
            BeanUtils.copyProperties(borrowerInfoVo, saasOrderBillDetailListVo);
        }

        saasOrderBillDetailListVo.setLoanLendRemark(saasOrderLendRemarkService.getLendWayByOrderNumb(saasOrderVo.getOrderNumb()));
        SaasChannelEntity saasChannelEntity = saasChannelService.getSaasChannelByChannelCode(saasOrderVo.getChannelCode());
        if (saasChannelEntity != null) {
            saasOrderBillDetailListVo.setChannelName(saasChannelEntity.getChannelName());
        }
        saasOrderBillDetailListVo.setOrderStatus(saasOrderService.getOrderStatusByOrderNumb(saasOrderVo.getOrderNumb()).getMsg());

        return saasOrderBillDetailListVo;
    }

    public void createOrderBillDetail(String orderNumb, String merchantCode) {
        SaasOrderBillDetailVo saasOrderBillDetailVo = saasOrderBillDetailService.getVisibleOrderBillDetailByOrderNumbAndMerchantCode(orderNumb, merchantCode);
        SaasOrderBillDetail addSaasOrderBillDetail = new SaasOrderBillDetail();
        if (saasOrderBillDetailVo != null) {
            BigDecimal amount = orderCalculateApplication.getAmount(saasOrderBillDetailVo);

            SaasOrderBillDetail updateSaasOrderBillDetail = new SaasOrderBillDetail();
            updateSaasOrderBillDetail.setId(saasOrderBillDetailVo.getSaasOrderBillDetailId());
            updateSaasOrderBillDetail.setVisible(Boolean.FALSE);
            updateSaasOrderBillDetail.setAmount(amount);
            BigDecimal lateInterest = amount.subtract(saasOrderBillDetailVo.getRealCapital()).subtract(saasOrderBillDetailVo.getInterest()).subtract(saasOrderBillDetailVo.getNeedPayInterest());
            updateSaasOrderBillDetail.setLateInterest(lateInterest);
            saasOrderBillDetailService.updateById(updateSaasOrderBillDetail);

            addSaasOrderBillDetail.setCreatedDt(saasOrderBillDetailVo.getCreatedDt());
            addSaasOrderBillDetail.setNeedPayInterest(amount.subtract(saasOrderBillDetailVo.getRealCapital()));
            addSaasOrderBillDetail.setRelationOrderBillDetailId(saasOrderBillDetailVo.getSaasOrderBillDetailId());
        }
        SaasOrderVo saasOrderVo = saasOrderService.getByOrderNumbAndMerchantCode(orderNumb, merchantCode);
        addSaasOrderBillDetail.setOrderNumb(orderNumb);
        addSaasOrderBillDetail.setMerchantCode(saasOrderVo.getMerchantCode());
        addSaasOrderBillDetail.setChannelCode(saasOrderVo.getChannelCode());
        addSaasOrderBillDetail.setBorrowerCode(saasOrderVo.getBorrowerCode());
        addSaasOrderBillDetail.setRealCapital(saasOrderVo.getRealCapital());
        addSaasOrderBillDetail.setTotalInterestRatio(saasOrderVo.getTotalInterestRatio());
        addSaasOrderBillDetail.setLateInterestRatio(saasOrderVo.getLateInterestRatio());
        if (addSaasOrderBillDetail.getCreatedDt() == null) {
            addSaasOrderBillDetail.setCreatedDt(new Date());
        }
        addSaasOrderBillDetail.setRepaymentDt(saasOrderVo.getRepaymentDt());
        addSaasOrderBillDetail.setInterest(saasOrderVo.getTotalInterestFee());
        addSaasOrderBillDetail.setServiceFee(saasOrderVo.getServiceFee());
        addSaasOrderBillDetail.setVisible(Boolean.TRUE);
        saasOrderBillDetailService.create(addSaasOrderBillDetail);
    }

    public void destroyOrderBillDetail(String orderNumb, String merchantCode) {
        SaasOrderBillDetailVo saasOrderBillDetailVo = saasOrderBillDetailService.getVisibleOrderBillDetailByOrderNumbAndMerchantCode(orderNumb, merchantCode);
        BigDecimal amount = orderCalculateApplication.getAmount(saasOrderBillDetailVo);
        SaasOrderBillDetail updateSaasOrderBillDetail = new SaasOrderBillDetail();
        updateSaasOrderBillDetail.setId(saasOrderBillDetailVo.getSaasOrderBillDetailId());
        updateSaasOrderBillDetail.setDestroy(Boolean.TRUE);
        updateSaasOrderBillDetail.setActualDestroyDate(new Date());
        updateSaasOrderBillDetail.setActualDestroyDt(new Date());
        updateSaasOrderBillDetail.setAmount(amount);
        BigDecimal lateInterest = amount.subtract(saasOrderBillDetailVo.getRealCapital()).subtract(saasOrderBillDetailVo.getInterest()).subtract(saasOrderBillDetailVo.getNeedPayInterest());
        updateSaasOrderBillDetail.setLateInterest(lateInterest);
        saasOrderBillDetailService.updateById(updateSaasOrderBillDetail);
    }

    public List<SaasOrderDetailVo> getAllOrderBillDetailByOrderNumb(String merchantCode, String orderNumb) {
        List<SaasOrderBillDetailVo> saasOrderBillDetailVoList = saasOrderBillDetailService.listByOrderNumbAndMerchantCode(orderNumb, merchantCode);
        if (CollectionUtils.isEmpty(saasOrderBillDetailVoList)) {
            return null;
        }
        List<SaasOrderDetailVo> results = new ArrayList<>(saasOrderBillDetailVoList.size());
        SaasOrderDetailVo lastSaasOrderDetailVo = null;
        for (int i = 0; i < saasOrderBillDetailVoList.size(); i++) {
            SaasOrderBillDetailVo saasOrderBillDetailVo = saasOrderBillDetailVoList.get(i);
            if (saasOrderBillDetailVo == null) {
                continue;
            }
            SaasOrderDetailVo saasOrderDetailVo = new SaasOrderDetailVo();
            saasOrderDetailVo.setPeriod(i);
            saasOrderDetailVo.setOrderNumb(saasOrderBillDetailVo.getOrderNumb());
            saasOrderDetailVo.setCapital(saasOrderBillDetailVo.getRealCapital().toString());
            saasOrderDetailVo.setServiceFee(saasOrderBillDetailVo.getServiceFee().intValue());
            Date createdDt;
            if (lastSaasOrderDetailVo == null) {
                createdDt = saasOrderBillDetailVo.getGmtCreate();
                saasOrderDetailVo.setBorrowingDuration(DateUtil.countDay(saasOrderBillDetailVo.getRepaymentDt(), createdDt));
            } else {
                Date lastRepaymentDt = DateUtil.getDate(lastSaasOrderDetailVo.getRepaymentDate(), DateUtil.getDatePattern());
                Integer days = DateUtil.countDay(saasOrderBillDetailVo.getGmtCreate(), lastRepaymentDt);
                if (days > 0) {
                    createdDt = DateUtil.addDate(saasOrderBillDetailVo.getGmtCreate(), 1);
                } else {
                    createdDt = DateUtil.addDate(lastRepaymentDt, 1);
                }
                saasOrderDetailVo.setBorrowingDuration(DateUtil.countDay(saasOrderBillDetailVo.getRepaymentDt(), createdDt) + 1);
            }
            saasOrderDetailVo.setCreatedDate(DateUtil.getDate(createdDt));
            saasOrderDetailVo.setRepaymentDate(DateUtil.getDate(saasOrderBillDetailVo.getRepaymentDt()));
            saasOrderDetailVo.setTotalInterestRatio(orderCalculateApplication.getInterestRatio(saasOrderBillDetailVo.getTotalInterestRatio()));
            saasOrderDetailVo.setInterest(saasOrderBillDetailVo.getInterest().toString());
            if (saasOrderBillDetailVo.getAmount() == null || BigDecimal.ZERO.compareTo(saasOrderBillDetailVo.getAmount()) == 0) {
                saasOrderDetailVo.setAmount(orderCalculateApplication.getAmount(saasOrderBillDetailVo).toString());
            } else {
                saasOrderDetailVo.setAmount(saasOrderBillDetailVo.getAmount().toString());
            }
            if (lastSaasOrderDetailVo != null) {
                try {
                    lastSaasOrderDetailVo.setOverdueDuration(DateUtil.countDays(saasOrderDetailVo.getCreatedDate(), lastSaasOrderDetailVo.getRepaymentDate()) - 1);
                } catch (Exception e) {
                    LOGGER.warn(".....订单日期转换失败.....失败原因：{}，订单CODE：{}；订单创建日期：{}；订单应还日期：{}", e.getMessage(), saasOrderDetailVo.getOrderNumb(), saasOrderDetailVo.getCreatedDate(), lastSaasOrderDetailVo.getRepaymentDate());
                }
            }
            if (i == saasOrderBillDetailVoList.size() - 1) {
                Integer overdueDuration;
                if (saasOrderBillDetailVo.getActualDestroyDt() != null) {
                    overdueDuration = DateUtil.countDay(saasOrderBillDetailVo.getActualDestroyDt(), saasOrderBillDetailVo.getRepaymentDt());
                } else {
                    overdueDuration = DateUtil.countDay(new Date(), saasOrderBillDetailVo.getRepaymentDt());
                }
                if (overdueDuration < 0) {
                    overdueDuration = 0;
                }
                saasOrderDetailVo.setOverdueDuration(overdueDuration);
                saasOrderDetailVo.setOrderStatus(saasOrderService.getOrderStatusByOrderNumb(saasOrderBillDetailVo.getOrderNumb()).getMsg());
            }
            results.add(saasOrderDetailVo);
            lastSaasOrderDetailVo = saasOrderDetailVo;
        }
        return results;
    }

}