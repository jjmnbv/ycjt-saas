package com.beitu.saas.risk.domain.platform;

import com.beitu.saas.common.enums.ProductTypeEnum;
import com.beitu.saas.risk.domain.enums.triple.TripleServiceTypeEnum;

/**
 * @Author 柳朋朋
 * @Create 2017-05-27 11:58
 */
public abstract class TripleServiceBaseWatch {

    public abstract TripleServiceBaseOutput getResult(ProductTypeEnum productTypeEnum, TripleServiceTypeEnum tripleServiceTypeEnum, TripleServiceBaseInput tripleServiceBaseInput) throws Exception;
}
