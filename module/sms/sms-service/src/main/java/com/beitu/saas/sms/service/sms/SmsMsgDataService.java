package com.beitu.saas.sms.service.sms;

import com.beitu.saas.sms.ro.BatchSmsSendRquestRO;
import com.beitu.saas.sms.ro.SingleSmsSendRequestRO;
import com.beitu.saas.sms.ro.StateCode;

import java.util.Date;
import java.util.Map;

/**
 * 短信服务业务处理
 */
public interface SmsMsgDataService {
    /**
     * 单条短信发送
     * @param phone
     * @param bizCode
     * @param replaceParam
     * @param type
     * @param sendTime
     * @return
     */
    StateCode doSend(String phone, String bizCode, Map<String, String> replaceParam, Integer type, Date sendTime);

    /**
     * 
     * 单条短信 进队列
     *
     * @name 单条短信 进队列
     * @param single
     * @return
     * @author liting
     * @date: 2017年10月31日 下午3:06:14
     */
    StateCode singleSend(SingleSmsSendRequestRO single);

    /**singleSend
     * 
     * 批量短信 塞队列
     *
     * @name 批量短信 塞队列
     * @param batch
     * @return
     * @author liting
     * @date: 2017年10月31日 下午3:22:22
     */
    StateCode batchSend(BatchSmsSendRquestRO batch);
}
