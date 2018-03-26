package com.beitu.saas.app.application.order;

import com.beitu.saas.app.application.order.vo.H5OrderListVo;
import com.beitu.saas.app.enums.BorrowerOrderApplyStatusEnum;
import com.beitu.saas.app.enums.H5OrderBillDetailViewTypeEnum;
import com.beitu.saas.order.client.SaasOrderApplicationService;
import com.beitu.saas.order.client.SaasOrderBillDetailService;
import com.beitu.saas.order.client.SaasOrderService;
import com.beitu.saas.order.domain.SaasOrderApplicationVo;
import com.beitu.saas.order.domain.SaasOrderBillDetailVo;
import com.beitu.saas.order.domain.SaasOrderVo;
import com.beitu.saas.order.entity.SaasOrder;
import com.beitu.saas.order.entity.SaasOrderBillDetail;
import com.beitu.saas.order.enums.OrderStatusEnum;
import com.fqgj.common.utils.CollectionUtils;
import com.fqgj.common.utils.DateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        //TODO 协议地址
        saasOrder.setTermUrl("");
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
        if (OrderStatusEnum.HAS_BEEN_DESTORY.getCode().equals(orderStatus) ||
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

    public void getOrderDetailByOrderNumb(String orderNumb) {
        List<SaasOrderVo> saasOrderVoList = saasOrderService.listEffectiveOrderByOrderNumb(orderNumb);

    }

}
