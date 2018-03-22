package com.beitu.saas.risk.domain.carrier;

import com.beitu.saas.common.enums.ProductTypeEnum;
import com.beitu.saas.risk.domain.enums.triple.TripleServiceTypeEnum;
import com.beitu.saas.risk.domain.platform.TripleServiceBaseOutput;
import com.beitu.saas.risk.domain.platform.TripleServiceBaseInput;

/**
 * Created by ykpbean kangping.ying@yuntu-inc.com
 *
 * @description
 * @create 2017-11-17 下午2:17
 */


public abstract class CarrierServiceBaseWatch {

    public abstract TripleServiceBaseOutput getResult(ProductTypeEnum productTypeEnum, TripleServiceTypeEnum tripleServiceTypeEnum, TripleServiceBaseInput tripleServiceBaseInput);
}
