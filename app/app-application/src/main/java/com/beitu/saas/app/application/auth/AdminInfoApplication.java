package com.beitu.saas.app.application.auth;

import com.beitu.saas.auth.entity.SaasAdmin;
import com.beitu.saas.auth.entity.SaasAdminToken;
import com.beitu.saas.auth.service.SaasAdminService;
import com.beitu.saas.auth.service.SaasAdminTokenService;
import com.fqgj.common.utils.CollectionUtils;
import com.fqgj.common.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author xiaochong
 * @create 2018/3/22 下午5:33
 * @description
 */
@Component
public class AdminInfoApplication {

    @Autowired
    private SaasAdminTokenService saasAdminTokenService;

    @Autowired
    private SaasAdminService saasAdminService;

    public SaasAdmin getSaasAdminByAccessToken(String token) {
        List<SaasAdminToken> list = saasAdminTokenService.selectByParams(new HashMap<String, Object>(2) {{
            put("token", token);
            put("deleted", false);
        }});
        if (CollectionUtils.isEmpty(list) || TimeUtils.check(new Date(), list.get(0).getExpireDate())) {
            return null;
        }
        SaasAdmin saasAdmin = saasAdminService.getSaasAdminByAdminCode(list.get(0).getAdminCode());
        return saasAdmin;
    }
}
