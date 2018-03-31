package com.beitu.saas.order.client;

import com.beitu.saas.order.domain.QuerySaasOrderVo;
import com.beitu.saas.order.domain.SaasOrderVo;
import com.beitu.saas.order.enums.OrderStatusEnum;
import com.fqgj.common.api.Page;
import com.fqgj.common.base.BaseService;
import com.fqgj.common.entity.BaseEntity;

import java.util.List;

/**
 * User: jungle
 * Date: 2018-03-23
 * Time: 15:18:54.744
 */
public interface SaasOrderService<T extends BaseEntity> extends BaseService<T> {

    /**
     * 得到订单状态
     *
     * @param orderNumb 订单号
     * @return
     */
    OrderStatusEnum getOrderStatusByOrderNumb(String orderNumb);

    /**
     * 根据 订单号 得到有效订单列表
     *
     * @param orderNumb 订单号
     * @return order by created_dt
     */
    List<SaasOrderVo> listEffectiveOrderByOrderNumb(String orderNumb);

    /**
     * 是否为审核驳回订单
     *
     * @param borrowerCode 借款人CODE
     * @param channelCode  渠道CODE
     * @return
     */
    Boolean isReviewRefuse(String borrowerCode, String channelCode);

    Boolean isReviewing(String borrowerCode, String channelCode);

    List<SaasOrderVo> listByQuerySaasOrderVoAndPage(QuerySaasOrderVo querySaasOrderVo, Page page);

    SaasOrderVo getByOrderNumb(String orderNumb);

    Boolean updateOrderStatus(Long orderId, Long version, OrderStatusEnum currentOrderStatus, OrderStatusEnum updateOrderStatus);

    Boolean updateOrderRemark(Long orderId, String remark);

    /**
     * 得到 待确认收款 的订单列表
     *
     * @param borrowerCode
     * @return
     */
    List<SaasOrderVo> listAllConfirmReceiptOrderByBorrowerCode(String borrowerCode);

}