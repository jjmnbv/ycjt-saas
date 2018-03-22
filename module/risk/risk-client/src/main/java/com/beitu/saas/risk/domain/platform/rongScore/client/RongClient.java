/*
 * 文 件 名:  RongClient.java
 * 修 改 人:  chenchong@rong360.com
 * 修改时间:  2016年2月25日
 * 修改内容:  <修改内容>
 */
package com.beitu.saas.risk.domain.platform.rongScore.client;

import java.util.Map;

/**
 * @author chenchong@rong360.com
 * @date [2016年3月29日]
 */
public interface RongClient {

    String execute(Map<String, String> params) throws Exception;

}
