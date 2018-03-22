package com.beitu.saas.risk.handler.platform.zmxy;


import com.fgqj.youjie.common.utils.ConfigUtil;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import com.beitu.saas.risk.domain.platform.zmxy.score.TripleZmxyScoreOutput;
import com.beitu.saas.risk.domain.platform.zmxy.watch.TripleZmxyIvsWatchInput;
import com.beitu.saas.risk.domain.platform.zmxy.watch.TripleZmxyIvsWatchOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author 柳朋朋
 * @Create 2016-10-24 23:01
 */
@Component
public class JydZmxyHandler {
    private static String serverUrl = "https://zmopenapi.zmxy.com.cn/openapi.do";
    private static String charset = "UTF-8";
    private Log LOGGER = LogFactory.getLog(JydZmxyHandler.class);

    @Autowired
    private ZmxyData zmxyData;

    @Autowired
    private ConfigUtil configUtil;

    /**
     * 芝麻分  数据获取
     *
     * @return
     */
    public TripleZmxyScoreOutput zmxyScoreSubscription(String openId) throws Exception {
        //芝麻分
        return zmxyData.queryScore(openId, new ZmxyParams(configUtil.getYouqianZmxyAppId(), configUtil.getYouqianZmxyPrivateKey(), configUtil.getYouqianZmxyZhimaPublicKey()));
    }


    /**
     * 芝麻ivs  watch 获取
     *
     * @return
     */
    public TripleZmxyIvsWatchOutput zmxyIvsAndWatchSubscription(TripleZmxyIvsWatchInput zmxyIvsInput) throws Exception {
        return zmxyData.queryIvs(zmxyIvsInput, new ZmxyParams(configUtil.getYouqianZmxyAppId(), configUtil.getYouqianZmxyPrivateKey(), configUtil.getYouqianZmxyZhimaPublicKey()));
    }


    public Boolean zmxyIsAuthorization(String identityNo, String realName) throws Exception {
        return zmxyData.zmxyIsAuthorization(identityNo, realName, new ZmxyParams(configUtil.getYouqianZmxyAppId(), configUtil.getYouqianZmxyPrivateKey(), configUtil.getYouqianZmxyZhimaPublicKey()));
    }

}
