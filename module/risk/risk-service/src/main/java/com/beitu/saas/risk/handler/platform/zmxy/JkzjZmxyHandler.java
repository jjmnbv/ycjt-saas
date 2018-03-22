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
public class JkzjZmxyHandler {
    private static final Log LOGGER = LogFactory.getLog(JkzjZmxyHandler.class);

    private static String serverUrl = "https://zmopenapi.zmxy.com.cn/openapi.do";
    private static String charset = "UTF-8";

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
        return zmxyData.queryScore(openId, new ZmxyParams(configUtil.getJkzjZmxyAppId(), configUtil.getJkzjZmxyPrivateKey(), configUtil.getJkzjZmxyZhimaPublicKey()));
    }


    /**
     * 芝麻ivs  watch 获取
     *
     * @return
     */
    public TripleZmxyIvsWatchOutput zmxyIvsAndWatchSubscription(TripleZmxyIvsWatchInput zmxyIvsInput) throws Exception {
        return zmxyData.queryIvs(zmxyIvsInput, new ZmxyParams(configUtil.getJkzjZmxyAppId(), configUtil.getJkzjZmxyPrivateKey(), configUtil.getJkzjZmxyZhimaPublicKey()));
    }


    /**
     * 芝麻 是否认证
     *
     * @param identityNo
     * @param realName
     * @return
     */
    public Boolean zmxyIsAuthorization(String identityNo, String realName) throws Exception {
        return zmxyData.zmxyIsAuthorization(identityNo, realName, new ZmxyParams(configUtil.getJkzjZmxyAppId(), configUtil.getJkzjZmxyPrivateKey(), configUtil.getJkzjZmxyZhimaPublicKey()));
    }

    public static void main(String[] args) {
        try {
            new JkzjZmxyHandler().zmxyIsAuthorization("341221199302076338", "柳朋朋");

        } catch (Exception e) {
        }
    }
}
