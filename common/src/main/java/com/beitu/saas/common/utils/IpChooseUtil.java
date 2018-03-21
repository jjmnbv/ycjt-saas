package com.beitu.saas.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Author 柳朋朋
 * @Create 2017-01-09 11:04
 */
public class IpChooseUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(IpChooseUtil.class);

    public static Boolean adminFirstSync() {
        Boolean flag = false;
        try {
            InetAddress ia = null;
            ia = ia.getLocalHost();
            String ip = ia.getHostAddress();
            //正式
            if ("172.16.15.20".equals(ip)) {
                flag = true;
            }
        } catch (UnknownHostException e) {
        }
        return flag;
    }


    public static Boolean adminTestServer() {
        Boolean flag = true;
        try {
            InetAddress ia = null;
            ia = ia.getLocalHost();
            String ip = ia.getHostAddress();
            //正式
            if ("10.47.123.209".equals(ip)) {
                flag = false;
            }
        } catch (UnknownHostException e) {
        }
        return flag;
    }


    public static Boolean notSync() {
        Boolean flag = false;
        try {
            InetAddress ia = null;
            ia = ia.getLocalHost();
            String ip = ia.getHostAddress();
        } catch (UnknownHostException e) {
        }
        return flag;
    }

    public static String getHostAddress()  {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) throws UnknownHostException {
        InetAddress ia = null;
        ia = ia.getLocalHost();
        String ip = ia.getHostAddress();
        System.out.println(ip);

        System.out.println(InetAddress.getLocalHost().getHostAddress());
    }
}
