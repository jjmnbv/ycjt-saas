package com.beitu.saas.app.application.order;

import com.beitu.saas.app.enums.BorrowerOrderApplyStatusEnum;
import com.beitu.saas.order.domain.SaasOrderApplicationVo;
import com.beitu.saas.order.entity.SaasOrder;
import org.springframework.stereotype.Service;

/**
 * @author linanjun
 * @create 2018/3/21 下午4:00
 * @description
 */
@Service
public class OrderApplication {

    public BorrowerOrderApplyStatusEnum getOrderApplyStatus(String borrowerCode, String channelCode) {
        return BorrowerOrderApplyStatusEnum.NO_SUBMIT;
    }

    public SaasOrder createOrder(SaasOrderApplicationVo saasOrderApplicationVo, String orderNumb) {
        return new SaasOrder();
    }

}
