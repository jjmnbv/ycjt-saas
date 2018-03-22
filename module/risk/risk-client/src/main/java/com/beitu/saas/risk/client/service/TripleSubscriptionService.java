package com.beitu.saas.risk.client.service;

import com.beitu.saas.common.enums.ProductTypeEnum;
import com.beitu.saas.risk.domain.enums.triple.TripleServiceTypeEnum;
import com.beitu.saas.risk.domain.platform.TripleServiceBaseInput;
import com.beitu.saas.risk.domain.platform.vo.TripleServiceResult;

/**
 * Created by ykpbean kangping.ying@yuntu-inc.com
 *
 * @description
 * @create 2017-11-15 上午10:33
 */

public interface TripleSubscriptionService {

    /**
     * 三方数据 订阅
     *
     * @param productTypeEnum
     * @param tripleServiceTypeEnum
     * @param tripleServiceBaseInput
     * @return
     */
    TripleServiceResult getTripleServiceResult(ProductTypeEnum productTypeEnum, TripleServiceTypeEnum tripleServiceTypeEnum, TripleServiceBaseInput tripleServiceBaseInput);
}
