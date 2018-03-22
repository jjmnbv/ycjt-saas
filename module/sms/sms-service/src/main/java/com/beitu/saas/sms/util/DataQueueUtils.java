/**
 * yuntu-inc.com Inc.
 * Copyright (c) 2014-2017 All Rights Reserved.
 */
package com.beitu.saas.sms.util;

import com.beitu.saas.sms.ro.SingleSmsSendRequestRO;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 阻塞队列
 *
 * @name 阻塞队列
 * @author liting
 * @version $Id: BlockedQueueUtils.java, v 0.1 2017年9月14日 下午7:54:34 liting Exp $
 */
public class DataQueueUtils {
    /**
     * 存储短信消息队列
     */
    private static ConcurrentLinkedQueue<SingleSmsSendRequestRO> smsQueue = new ConcurrentLinkedQueue<>();

    public static ConcurrentLinkedQueue<SingleSmsSendRequestRO> getSmsInstance(){
        return smsQueue;
    }

    /**
     * 
     * 批量取数据
     *
     * @name 批量取数据
     * @param size
     * @return
     * @author liting
     * @date: 2017年9月14日 下午9:03:44
     */
    public static List<SingleSmsSendRequestRO> batchSmsGetElements(int size){
        if(smsQueue.isEmpty()) {
            return null;
        }
        List<SingleSmsSendRequestRO> elements = Lists.newArrayList();
        for(int i=0;i<size;i++) {
            if(smsQueue.isEmpty()) {
                break;
            }
            elements.add(getSmsInstance().poll());
        }
        return elements;
    }
}
