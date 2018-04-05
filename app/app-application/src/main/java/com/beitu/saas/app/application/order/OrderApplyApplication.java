package com.beitu.saas.app.application.order;

import com.beitu.saas.app.application.order.vo.OrderApplicationListVo;
import com.beitu.saas.borrower.client.SaasBorrowerRealInfoService;
import com.beitu.saas.borrower.client.SaasBorrowerService;
import com.beitu.saas.channel.client.SaasChannelService;
import com.beitu.saas.order.client.SaasOrderApplicationService;
import com.beitu.saas.order.client.SaasOrderService;
import com.beitu.saas.order.entity.SaasOrderApplication;
import com.fqgj.common.utils.CollectionUtils;
import com.fqgj.common.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
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
        List<SaasOrderApplication> saasOrderApplicationList = saasOrderApplicationService.selectByParams(new HashMap<String, Object>(4) {{
            put("borrowerCode", borrowerCode);
            put("orderNumb", orderNumb);
            put("deleted", Boolean.FALSE);
        }});
        if (CollectionUtils.isEmpty(saasOrderApplicationList)) {
            return null;
        }
        List<OrderApplicationListVo> results = new ArrayList<>(saasOrderApplicationList.size());
        saasOrderApplicationList.forEach(saasOrderApplication -> results.add(convertSaasOrderApplication2OrderApplicationListVo(saasOrderApplication)));
        return results;
    }

    private OrderApplicationListVo convertSaasOrderApplication2OrderApplicationListVo(SaasOrderApplication saasOrderApplication) {
        if (saasOrderApplication == null) {
            return null;
        }
        OrderApplicationListVo orderApplicationListVo = new OrderApplicationListVo();
        orderApplicationListVo.setCreatedDt(DateUtil.getDate(saasOrderApplication.getGmtCreate()));
        orderApplicationListVo.setRealCapital(saasOrderApplication.getRealCapital().toString());
        orderApplicationListVo.setBorrowingDuration(DateUtil.countDay(saasOrderApplication.getRepaymentDt(), saasOrderApplication.getGmtCreate()) + "天");
        orderApplicationListVo.setBorrowerName(saasBorrowerRealInfoService.getBorrowerRealInfoByBorrowerCode(saasOrderApplication.getBorrowerCode()).getName());
        orderApplicationListVo.setBorrowerMobile(saasBorrowerService.getByMobileAndMerchantCode(saasOrderApplication.getBorrowerCode(), saasOrderApplication.getMerchantCode()).getMobile());
        orderApplicationListVo.setOrderStatus(saasOrderService.getOrderStatusByOrderNumb(saasOrderApplication.getOrderNumb()).getMsg());
        orderApplicationListVo.setRepaymentMethod("一次性还本付息");
        orderApplicationListVo.setChannelName(saasChannelService.getSaasChannelByChannelCode(saasOrderApplication.getChannelCode()).getChannelName());
        return orderApplicationListVo;
    }

}
