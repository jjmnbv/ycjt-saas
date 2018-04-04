package com.beitu.saas.intergration.esign.init;

import com.beitu.saas.common.config.ConfigUtil;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import com.timevale.esign.sdk.tech.bean.result.Result;
import com.timevale.esign.sdk.tech.service.EsignsdkService;
import com.timevale.esign.sdk.tech.service.factory.EsignsdkServiceFactory;
import com.timevale.tech.sdk.bean.HttpConnectionConfig;
import com.timevale.tech.sdk.bean.ProjectConfig;
import com.timevale.tech.sdk.bean.SignatureConfig;
import com.timevale.tech.sdk.constants.AlgorithmType;
import com.timevale.tech.sdk.constants.HttpType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author linanjun
 * @create 2018/4/3 上午11:09
 * @description
 */
@Component
public class InitEsign {

    private static Log LOG = LogFactory.getLog(InitEsign.class);

    @Autowired
    private ConfigUtil configUtil;

    @PostConstruct
    public void initProject() {
        ProjectConfig proCfg = new ProjectConfig();

        proCfg.setProjectId(configUtil.getESignProjectId());
        proCfg.setProjectSecret(configUtil.getESignProjectSecret());
        proCfg.setItsmApiUrl(configUtil.getApisUrl());

        HttpConnectionConfig httpConCfg = new HttpConnectionConfig();
        httpConCfg.setHttpType(HttpType.HTTPS);
        httpConCfg.setRetry(5);

        SignatureConfig sCfg = new SignatureConfig();
        sCfg.setAlgorithm(AlgorithmType.HMACSHA256);
        sCfg.setEsignPublicKey("");
        sCfg.setPrivateKey("");
        LOG.info("---ESIGN---项目初始化...");
        EsignsdkService sdk = EsignsdkServiceFactory.instance();
        Result result = sdk.init(proCfg, httpConCfg, sCfg);
        if (0 != result.getErrCode()) {
            LOG.error("---ESIGN---项目初始化失败：errCode=" + result.getErrCode() + " msg=" + result.getMsg());
        } else {
            LOG.info("---ESIGN---项目初始化成功！errCode=" + result.getErrCode() + " msg=" + result.getMsg());
        }
    }

}
