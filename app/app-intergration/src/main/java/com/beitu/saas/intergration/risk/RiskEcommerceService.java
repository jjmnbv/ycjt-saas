package com.beitu.saas.intergration.risk;

import com.beitu.saas.intergration.risk.param.GXBEcommerceCrawlingParam;

/**
 * @author xiaochong
 * @create 2018/4/16 下午4:34
 * @description
 */
public interface RiskEcommerceService {
    String getEcommerceCrawlingUrl(GXBEcommerceCrawlingParam param);
}
