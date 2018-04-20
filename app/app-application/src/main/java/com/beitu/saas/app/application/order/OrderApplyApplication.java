package com.beitu.saas.app.application.order;

import com.beitu.saas.app.application.order.vo.OrderApplicationListVo;
import com.beitu.saas.borrower.client.SaasBorrowerRealInfoService;
import com.beitu.saas.borrower.client.SaasBorrowerService;
import com.beitu.saas.borrower.domain.SaasBorrowerRealInfoVo;
import com.beitu.saas.borrower.domain.SaasBorrowerVo;
import com.beitu.saas.channel.client.SaasChannelService;
import com.beitu.saas.channel.entity.SaasChannelEntity;
import com.beitu.saas.order.client.SaasOrderApplicationService;
import com.beitu.saas.order.client.SaasOrderService;
import com.beitu.saas.order.domain.SaasOrderApplicationVo;
import com.beitu.saas.order.enums.OrderStatusEnum;
import com.fqgj.common.utils.CollectionUtils;
import com.fqgj.common.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author linanjun
 * @create 2018/4/4 上午11:42
 * @description
 */
@Service
public class OrderApplyApplication {

    @Autowired
    private SaasOrderApplicationService saasOrderApplicationService;

    @Autowired
    private SaasBorrowerService saasBorrowerService;

    @Autowired
    private SaasBorrowerRealInfoService saasBorrowerRealInfoService;

    @Autowired
    private SaasChannelService saasChannelService;

    @Autowired
    private SaasOrderService saasOrderService;

    public List<OrderApplicationListVo> listOrderApplicationByBorrowerCodeAndOrderNumb(String borrowerCode, String orderNumb) {
        List<SaasOrderApplicationVo> saasOrderApplicationVoList = saasOrderApplicationService.listByBorrowerCodeAndOrderNumb(borrowerCode, orderNumb);
        if (CollectionUtils.isEmpty(saasOrderApplicationVoList)) {
            return null;
        }
        List<OrderApplicationListVo> results = new ArrayList<>(saasOrderApplicationVoList.size());
        saasOrderApplicationVoList.forEach(saasOrderApplicationVo -> results.add(convertSaasOrderApplicationVo2OrderApplicationListVo(saasOrderApplicationVo)));
        return results;
    }

    private OrderApplicationListVo convertSaasOrderApplicationVo2OrderApplicationListVo(SaasOrderApplicationVo saasOrderApplicationVo) {
        if (saasOrderApplicationVo == null) {
            return null;
        }
        OrderApplicationListVo orderApplicationListVo = new OrderApplicationListVo();
        orderApplicationListVo.setCreatedDt(DateUtil.getDate(saasOrderApplicationVo.getGmtCreate()));
        orderApplicationListVo.setRealCapital(saasOrderApplicationVo.getRealCapital().toString());
        orderApplicationListVo.setBorrowingDuration(DateUtil.countDay(saasOrderApplicationVo.getRepaymentDt(), saasOrderApplicationVo.getGmtCreate()) + "天");
        SaasBorrowerRealInfoVo saasBorrowerRealInfoVo = saasBorrowerRealInfoService.getBorrowerRealInfoByBorrowerCode(saasOrderApplicationVo.getBorrowerCode());
        if (saasBorrowerRealInfoVo != null) {
            orderApplicationListVo.setBorrowerName(saasBorrowerRealInfoVo.getName());
        }
        SaasBorrowerVo saasBorrowerVo = saasBorrowerService.getByBorrowerCodeAndMerchantCode(saasOrderApplicationVo.getBorrowerCode(), saasOrderApplicationVo.getMerchantCode());
        if (saasBorrowerVo != null) {
            orderApplicationListVo.setBorrowerMobile(saasBorrowerVo.getMobile());
        }
        OrderStatusEnum orderStatusEnum = saasOrderService.getOrderStatusByOrderNumb(saasOrderApplicationVo.getOrderNumb());
        if (orderStatusEnum != null) {
            orderApplicationListVo.setOrderStatus(orderStatusEnum.getMsg());
        } else {
            orderApplicationListVo.setOrderStatus("未提交");
        }
        orderApplicationListVo.setRepaymentMethod("一次性还本付息");
        SaasChannelEntity saasChannelEntity = saasChannelService.getSaasChannelByChannelCode(saasOrderApplicationVo.getChannelCode());
        if (saasChannelEntity != null) {
            orderApplicationListVo.setChannelName(saasChannelEntity.getChannelName());
        }
        return orderApplicationListVo;
    }

}
