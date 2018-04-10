package com.beitu.saas.rest.controller.channel.response;

import com.beitu.saas.auth.entity.SaasAdmin;
import com.fqgj.common.api.ResponseData;
import io.swagger.annotations.ApiModel;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/3/22
 * Time: 下午5:13
 */
@ApiModel(value = "机构下的管理员列表")
public class SaasMerchantAdminResponse implements ResponseData {
    private List<SaasAdmin> saasAdmins;

    public SaasMerchantAdminResponse(List<SaasAdmin> saasAdmins) {
        this.saasAdmins = saasAdmins;
    }

    public List<SaasAdmin> getSaasAdmins() {
        return saasAdmins;
    }

    public SaasMerchantAdminResponse setSaasAdmins(List<SaasAdmin> saasAdmins) {
        this.saasAdmins = saasAdmins;
        return this;
    }
}
