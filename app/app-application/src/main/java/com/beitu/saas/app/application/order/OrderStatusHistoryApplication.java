package com.beitu.saas.app.application.order;

import com.beitu.saas.app.application.order.vo.OrderStatusHistoryListVo;
import com.beitu.saas.auth.entity.SaasAdmin;
import com.beitu.saas.auth.service.SaasAdminService;
import com.beitu.saas.borrower.client.SaasBorrowerRealInfoService;
import com.beitu.saas.borrower.client.SaasBorrowerService;
import com.beitu.saas.borrower.domain.SaasBorrowerRealInfoVo;
import com.beitu.saas.borrower.domain.SaasBorrowerVo;
import com.beitu.saas.borrower.entity.SaasBorrowerRealInfo;
import com.beitu.saas.order.client.SaasOrderStatusHistoryService;
import com.beitu.saas.order.domain.SaasOrderStatusHistoryVo;
import com.beitu.saas.order.enums.OrderStatusEnum;
import com.fqgj.common.utils.CollectionUtils;
import com.fqgj.common.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author linanjun
 * @create 2018/4/4 上午11:15
 * @description
 */
@Service
public class OrderStatusHistoryApplication {

    @Autowired
    private SaasOrderStatusHistoryService saasOrderStatusHistoryService;

    @Autowired
    private SaasBorrowerRealInfoService saasBorrowerRealInfoService;

    @Autowired
    private SaasAdminService saasAdminService;

    public List<OrderStatusHistoryListVo> listOrderStatusHistory(String orderNumb) {
        List<SaasOrderStatusHistoryVo> saasOrderStatusHistoryVoList = saasOrderStatusHistoryService.listOrderStatusHistoryByOrderNumb(orderNumb);
        if (CollectionUtils.isEmpty(saasOrderStatusHistoryVoList)) {
            return null;
        }
        List<OrderStatusHistoryListVo> results = new ArrayList<>(saasOrderStatusHistoryVoList.size());
        saasOrderStatusHistoryVoList.forEach(saasOrderStatusHistoryVo -> results.add(convertSaasOrderStatusHistoryVo2OrderStatusHistoryListVo(saasOrderStatusHistoryVo)));
        return results;
    }

    private OrderStatusHistoryListVo convertSaasOrderStatusHistoryVo2OrderStatusHistoryListVo(SaasOrderStatusHistoryVo saasOrderStatusHistoryVo) {
        if (saasOrderStatusHistoryVo == null) {
            return null;
        }
        OrderStatusHistoryListVo orderStatusHistoryListVo = new OrderStatusHistoryListVo();
        orderStatusHistoryListVo.setOperatorDate(DateUtil.getDateTime(saasOrderStatusHistoryVo.getGmtCreate()));
        SaasBorrowerRealInfoVo saasBorrowerRealInfoVo = saasBorrowerRealInfoService.getBorrowerRealInfoByBorrowerCode(saasOrderStatusHistoryVo.getOperatorCode());
        if (saasBorrowerRealInfoVo != null) {
            orderStatusHistoryListVo.setOperatorName(saasBorrowerRealInfoVo.getName());
        } else {
            SaasAdmin saasAdmin = saasAdminService.getSaasAdminByAdminCode(saasOrderStatusHistoryVo.getOperatorCode());
            orderStatusHistoryListVo.setOperatorName(saasAdmin.getName());
        }
        StringBuilder operatorContent = new StringBuilder();
        if (saasOrderStatusHistoryVo.getCurrentOrderStatus() != null) {
            operatorContent.append("当前订单状态：")
                    .append(OrderStatusEnum.getEnumByCode(saasOrderStatusHistoryVo.getCurrentOrderStatus()).getMsg())
                    .append("；");
        }
        if (saasOrderStatusHistoryVo.getUpdateOrderStatus() != null) {
            operatorContent.append("更新订单状态：")
                    .append(OrderStatusEnum.getEnumByCode(saasOrderStatusHistoryVo.getUpdateOrderStatus()).getMsg())
                    .append("；");
        }
        if (saasOrderStatusHistoryVo.getRemark() != null) {
            operatorContent.append("备注：").append(saasOrderStatusHistoryVo.getRemark());
        }
        orderStatusHistoryListVo.setOperatorContent(operatorContent.toString());
        return orderStatusHistoryListVo;
    }

}