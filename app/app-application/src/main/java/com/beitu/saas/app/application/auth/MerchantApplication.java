package com.beitu.saas.app.application.auth;

import com.beitu.saas.auth.entity.SaasAdmin;
import com.beitu.saas.auth.entity.SaasMerchant;
import com.beitu.saas.auth.service.SaasAdminService;
import com.beitu.saas.auth.service.SaasMerchantService;
import com.fqgj.common.utils.GenerOrderNoUtil;
import com.fqgj.common.utils.MD5;
import com.fqgj.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

/**
 * @author xiaochong
 * @create 2018/3/26 下午8:56
 * @description
 */
@Component
public class MerchantApplication {

    @Autowired
    private SaasMerchantService saasMerchantService;

    @Autowired
    private SaasAdminService saasAdminService;

    @Autowired
    private RoleApplication roleApplication;

    @Transactional
    public void addMerchant(SaasMerchant saasMerchant,String password){

        //1.保存机构信息
        saasMerchant.setMerchantCode(GenerOrderNoUtil.generateOrderNo());
        saasMerchantService.create(saasMerchant);
        //2.添加登录用户
        SaasAdmin saasAdmin = new SaasAdmin();
        saasAdmin.setMerchantCode(saasMerchant.getMerchantCode());
        saasAdmin.setCode(GenerOrderNoUtil.generateOrderNo());
        saasAdmin.setJob("系统超级管理员");
        saasAdmin.setName(StringUtils.isNotEmpty(saasMerchant.getCompanyName())?saasMerchant.getCompanyName():saasMerchant.getLenderName());
        saasAdmin.setMobile(StringUtils.isNotEmpty(saasMerchant.getCompanyTel())?saasMerchant.getCompanyTel():saasMerchant.getLenderTel());
        saasAdmin.setPassword(MD5.md5(password));
        saasAdmin.setCreateName("system");
        saasAdmin.setDefault(true);
        saasAdmin.setEnable(true);
        saasAdminService.create(saasAdmin);
        //3.添加机构默认角色

        //超级管理员
        Arrays.asList(109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126);
        Arrays.asList(109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126);
        //roleApplication.addRoleAndEmpower("超级管理员","system",saasAdmin.getMerchantCode(),);

        //4.给用户赋权
        //5.添加默认机构配置
    }
}
