package com.beitu.saas.app.application.credit;

import com.beitu.saas.common.handle.carrier.CarrierHandler;
import com.beitu.saas.common.handle.carrier.domain.CarriersVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author linanjun
 * @create 2018/3/21 下午9:48
 * @description
 */
@Service
public class BorrowerCarrierApplication {

    @Autowired
    private CarrierHandler carrierHandler;

    public CarriersVo getUserCarrierVo(String userCode) {
        String carrierUrl = "";
        return carrierHandler.getCarriersVo(carrierUrl);
    }

}
