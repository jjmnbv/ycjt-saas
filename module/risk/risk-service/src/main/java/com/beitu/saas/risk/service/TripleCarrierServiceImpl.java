package com.beitu.saas.risk.service;

import com.beitu.saas.common.enums.ProductTypeEnum;
import com.beitu.saas.risk.client.service.TripleCarrierService;
import com.beitu.saas.risk.domain.enums.ErrorCodeEnums;
import com.beitu.saas.risk.domain.enums.triple.TripleServiceTypeEnum;
import com.beitu.saas.risk.domain.exception.BizException;
import com.beitu.saas.risk.domain.platform.TripleServiceBaseInput;
import com.beitu.saas.risk.domain.platform.TripleServiceBaseOutput;
import com.beitu.saas.risk.domain.platform.vo.TripleServiceResult;
import com.beitu.saas.risk.service.carrier.CarrierH5Service;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by ykpbean kangping.ying@yuntu-inc.com
 *
 * @description
 * @create 2017-11-17 下午2:15
 */
@Service
public class TripleCarrierServiceImpl implements TripleCarrierService {

    private static final Log LOGGER = LogFactory.getLog(TripleCarrierServiceImpl.class);

    @Autowired
    private CarrierH5Service carrierH5Service;

    @Override
    public TripleServiceResult getCarrierServiceResult(ProductTypeEnum productTypeEnum, TripleServiceTypeEnum tripleServiceTypeEnum, TripleServiceBaseInput tripleServiceBaseInput) {
        TripleServiceResult tripleServiceResult = null;
        UUID uuid = UUID.randomUUID();
        String dealNo = uuid.toString();
        try {
            if (productTypeEnum == null || tripleServiceTypeEnum == null) {
                throw new BizException(ErrorCodeEnums.PLATFORM_SUBSTRIPTION_PARAMS_ERROR, "请选择调用的产品或服务类型~");
            }
            TripleServiceBaseOutput result = null;
            switch (tripleServiceTypeEnum) {
                case CARRIER_REQUEST_URL:
                case CARRIER_TASKID_INFO:
                    result = carrierH5Service.getResult(productTypeEnum, tripleServiceTypeEnum, tripleServiceBaseInput);
                    break;
            }
            tripleServiceResult = TripleServiceResult.ok().setData(result);
            tripleServiceResult.setDealNo(dealNo);
        } catch (BizException e) {
            //对业务异常的统一处理
            tripleServiceResult = TripleServiceResult.error(ErrorCodeEnums.CARRIER_TIMEOUT_ERROR, e.getMessage());
            tripleServiceResult.setDealNo(dealNo);
            LOGGER.info("三方服务调用异常，dealNo:{},productTypeEnum: {}, tripleServiceTypeEnum: {},isSuccess:{},message:{},e:{}", tripleServiceResult.getDealNo(), productTypeEnum.getName(), tripleServiceTypeEnum.getName(), tripleServiceResult.isSuccess(), tripleServiceResult.getMsg(), e);
        } catch (Exception e) {
            tripleServiceResult = TripleServiceResult.error(ErrorCodeEnums.CARRIER_TIMEOUT_ERROR);
            tripleServiceResult.setDealNo(dealNo);
            LOGGER.error("三方服务调用异常，dealNo:{},productTypeEnum: {}, tripleServiceTypeEnum: {},isSuccess:{},message:{},e:", tripleServiceResult.getDealNo(), productTypeEnum.getName(), tripleServiceTypeEnum.getName(), tripleServiceResult.isSuccess(), tripleServiceResult.getMsg(), e);
        }
        return tripleServiceResult;
    }

}