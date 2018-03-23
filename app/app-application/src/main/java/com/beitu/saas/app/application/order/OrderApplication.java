package com.beitu.saas.app.application.order;

import com.beitu.saas.app.enums.BorrowerOrderApplyStatusEnum;
import com.beitu.saas.order.domain.SaasOrderApplicationVo;
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

}
