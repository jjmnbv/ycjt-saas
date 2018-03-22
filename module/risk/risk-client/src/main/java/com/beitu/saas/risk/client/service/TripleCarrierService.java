package com.beitu.saas.risk.client.service;

import com.beitu.saas.common.enums.ProductTypeEnum;
import com.beitu.saas.risk.domain.enums.triple.TripleServiceTypeEnum;
import com.beitu.saas.risk.domain.platform.TripleServiceBaseInput;
import com.beitu.saas.risk.domain.platform.vo.TripleServiceResult;


/**
 * Created by ykpbean kangping.ying@yuntu-inc.com
 *
 * 运营商数据：例如同盾（有能力触及通话记录的特殊三方）
 *
 *
 * @description
 * @create 2017-11-17 下午1:51
 */

public interface TripleCarrierService {

    /**
     * 三方运营商 认证
     *
     * @param productTypeEnum
     * @param tripleServiceTypeEnum
     * @param carrierServiceBaseInput
     * @return
     */
    TripleServiceResult getCarrierServiceResult(ProductTypeEnum productTypeEnum, TripleServiceTypeEnum tripleServiceTypeEnum, TripleServiceBaseInput carrierServiceBaseInput);

}
