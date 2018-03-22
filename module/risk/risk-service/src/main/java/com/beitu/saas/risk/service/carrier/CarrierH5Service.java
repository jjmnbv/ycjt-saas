package com.beitu.saas.risk.service.carrier;

import com.beitu.saas.common.config.ConfigUtil;
import com.beitu.saas.common.enums.ProductTypeEnum;
import com.beitu.saas.risk.handler.carrier.h5.tianji.CarrierH5TianjiHandler;
import com.beitu.saas.risk.handler.carrier.h5.vo.CarrierH5ResultVo;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import com.beitu.saas.risk.domain.carrier.CarrierServiceBaseWatch;
import com.beitu.saas.risk.domain.carrier.h5.enums.CarrierH5TypeEnum;
import com.beitu.saas.risk.domain.carrier.h5.input.CarrierRequestUrlInput;
import com.beitu.saas.risk.domain.carrier.h5.input.CarrierTaskIdInfoInput;
import com.beitu.saas.risk.domain.carrier.h5.output.CarrierRequestUrlOutput;
import com.beitu.saas.risk.domain.carrier.h5.output.CarrierTaskIdInfoOutput;
import com.beitu.saas.risk.domain.enums.ErrorCodeEnums;
import com.beitu.saas.risk.domain.enums.triple.TripleServiceTypeEnum;
import com.beitu.saas.risk.domain.exception.BizException;
import com.beitu.saas.risk.domain.platform.TripleServiceBaseInput;
import com.beitu.saas.risk.domain.platform.TripleServiceBaseOutput;
import com.beitu.saas.risk.handler.carrier.h5.tianji.vo.CarrierTianjiCacheVo;
import com.beitu.saas.risk.handler.carrier.h5.vo.CarrierH5Vo;
import com.beitu.saas.risk.helpers.JSONUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zwh @yuntu-inc.com
 *
 * @description
 * @create 2017/10/30 0030 下午 8:04
 */
@Service
public class CarrierH5Service extends CarrierServiceBaseWatch {

    private static final Log LOGGER = LogFactory.getLog(CarrierH5Service.class);

    @Autowired
    private ConfigUtil configUtil;

    @Autowired
    private CarrierH5TianjiHandler carrierH5TianjiHandler;

    @Override
    public TripleServiceBaseOutput getResult(ProductTypeEnum productTypeEnum, TripleServiceTypeEnum tripleServiceTypeEnum, TripleServiceBaseInput tripleServiceBaseInput) {
        switch (tripleServiceTypeEnum) {
            case CARRIER_REQUEST_URL:
                if (tripleServiceBaseInput instanceof CarrierRequestUrlInput) {
                    CarrierRequestUrlInput carrierRequestUrlInput = (CarrierRequestUrlInput) tripleServiceBaseInput;
                    CarrierH5Vo carrierH5Vo = new CarrierH5Vo();
                    BeanUtils.copyProperties(carrierRequestUrlInput, carrierH5Vo);
                    CarrierH5ResultVo carrierH5ResultVo = requestCarrierUrl(productTypeEnum, carrierH5Vo);
                    CarrierRequestUrlOutput carrierRequestUrlOutput = new CarrierRequestUrlOutput();
                    BeanUtils.copyProperties(carrierH5ResultVo, carrierRequestUrlOutput);
                    return carrierRequestUrlOutput;
                }
                break;
            case CARRIER_TASKID_INFO:
                CarrierTaskIdInfoInput carrierRequestUrlInput = (CarrierTaskIdInfoInput) tripleServiceBaseInput;
                CarrierTianjiCacheVo info = carrierH5TianjiHandler.getCacheByTaskId(carrierRequestUrlInput.getTaskId());
                if (info != null) {
                    CarrierTaskIdInfoOutput output = new CarrierTaskIdInfoOutput();
                    BeanUtils.copyProperties(info, output);
                    return output;
                }
                return null;
        }
        LOGGER.error("运营商参数错误（未发现服务），productTypeEnum: {}, TripleServiceTypeEnum: {}, carrierServiceBaseInput: {}", productTypeEnum, tripleServiceTypeEnum.getName(),
                JSONUtils.obj2jsonNoException(tripleServiceBaseInput));
        throw new BizException(ErrorCodeEnums.CARRIER_PARAMS_ERROR);
    }

    private CarrierH5ResultVo requestCarrierUrl(ProductTypeEnum productTypeEnum, CarrierH5Vo carrierH5Vo) {
        CarrierH5TypeEnum carrierH5TypeEnum = CarrierH5TypeEnum.getEnumbyName(configUtil.getCarrierH5TypeName());
        try {
            CarrierH5ResultVo carrierH5ResultVo = carrierH5TianjiHandler.requestCarrierUrl(carrierH5Vo);
            return carrierH5ResultVo;
        } catch (Exception e) {
            LOGGER.info("========requestCarrierUrl exception. productTypeEnum: {},carrierH5TypeEnum: {}, carrierH5Vo:{}, e:", productTypeEnum.getName(), carrierH5TypeEnum.getName(), JSONUtils.obj2jsonNoException(carrierH5Vo), e);
            throw e;
        }
    }
}
