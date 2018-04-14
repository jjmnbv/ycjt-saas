package com.beitu.saas.risk.helpers;

public class ConstUtil {

    /**
     * @author jared
     * @Description: 数据库相关常量
     * @date Nov 5, 2014 2:34:02 PM
     */
    public static final class DataSource {

        /**
         * @author jared
         * @Description: 分表策略枚举类型
         * @date Nov 5, 2014 2:34:26 PM
         */
        public static enum ShardType {
            MOD, DATA
        }

        public static final String MAIN = "core";
        public static final String USER_BEHAVIOR = "ub";
        public static final String WECHAT = "wc";
        public static final String MESSAGE = "msg";
        public static final String WWW = "www";
    }
}
