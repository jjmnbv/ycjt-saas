package com.beitu.saas.risk.service;

import com.beitu.saas.common.enums.ProductTypeEnum;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import com.beitu.saas.risk.client.service.TripleSubscriptionService;
import com.beitu.saas.risk.domain.enums.ErrorCodeEnums;
import com.beitu.saas.risk.domain.enums.triple.TripleServiceTypeEnum;
import com.beitu.saas.risk.domain.exception.BizException;
import com.beitu.saas.risk.domain.platform.TripleServiceBaseInput;
import com.beitu.saas.risk.domain.platform.TripleServiceBaseOutput;
import com.beitu.saas.risk.domain.platform.vo.TripleServiceResult;
import com.beitu.saas.risk.service.platform.TongdunSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by ykpbean kangping.ying@yuntu-inc.com
 *
 * @description
 * @create 2017-11-15 下午2:10
 */
@Service
public class TripleSubscriptionServiceImpl implements TripleSubscriptionService {

    private static final Log LOGGER = LogFactory.getLog(TripleSubscriptionServiceImpl.class);

    @Autowired
    private TongdunSubscriptionService tongdunSubscriptionService;


    @Override
    public TripleServiceResult getTripleServiceResult(ProductTypeEnum productTypeEnum, TripleServiceTypeEnum tripleServiceTypeEnum, TripleServiceBaseInput tripleServiceBaseInput) {
        TripleServiceResult tripleServiceResult = null;
        UUID uuid = UUID.randomUUID();
        String dealNo = uuid.toString();
        try {
            if (productTypeEnum == null || tripleServiceTypeEnum == null) {
                throw new BizException(ErrorCodeEnums.PLATFORM_SUBSTRIPTION_PARAMS_ERROR, "请选择调用的产品或服务类型~");
            }
            //调用服务
            TripleServiceBaseOutput tripleServiceBaseOutput = null;
            switch (tripleServiceTypeEnum) {
                case TONGDUN_REPORT_ID:
                case TONGDUN_REPORT_QUERY:
                    tripleServiceBaseOutput = tongdunSubscriptionService.getResult(productTypeEnum, tripleServiceTypeEnum, tripleServiceBaseInput);
                    break;
            }
            //返回
            tripleServiceResult = TripleServiceResult.ok().setData(tripleServiceBaseOutput);
            tripleServiceResult.setDealNo(dealNo);
        } catch (BizException e) {
            tripleServiceResult = TripleServiceResult.error(e.getErrorCode(), e.getMessage());
            tripleServiceResult.setDealNo(dealNo);
            LOGGER.info("三方调用异常，dealNo:{},productTypeEnum: {}, tripleServiceTypeEnum: {},isSuccess:{},message:{},e:{}", tripleServiceResult.getDealNo(), productTypeEnum, tripleServiceTypeEnum, tripleServiceResult.isSuccess(), tripleServiceResult.getMsg(), e);
        } catch (Exception e) {
            tripleServiceResult = TripleServiceResult.error(ErrorCodeEnums.PLATFORM_SUBSTRIPTION_ERROR, "三方调用异常，请稍后重试~~");
            tripleServiceResult.setDealNo(dealNo);
            LOGGER.error("三方服务调用异常，dealNo:{},productTypeEnum: {}, tripleServiceTypeEnum: {},message:{}, e: ", dealNo, productTypeEnum, tripleServiceTypeEnum, tripleServiceResult.getMsg(), e);
            LOGGER.error("三方服务调用异常", e);
        }
        return tripleServiceResult;
    }

}
