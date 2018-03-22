/*
 * 文 件 名:  ClientManager.java
 * 修 改 人:  chenchong@rong360.com
 * 修改时间:  2016年2月25日
 * 修改内容:  <修改内容>
 */
package com.beitu.saas.risk.domain.platform.rongScore.client;


import com.beitu.saas.risk.domain.platform.rongScore.client.impl.DefaultRongClient;

/**
 * @author chenchong@rong360.com
 * @date [2016年3月29日]
 */
public class ClientManager {

    public static RongClient createClient() {
        String url = "https://openapi.rong360.com/gateway";
//		String url = "https://openapisandbox.rong360.com/gateway";

        /**请机构替换**/
//		String appId = "1000205";
        String appId = "2010418";
        /** 暂时只支持json返回格式 */
        String format = "json";
//
        /** 配置创建的RSA私钥   java的必须是pkcs8格式*/
        /**请机构替换**/
//		String privateKey ="MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAKuL2YQS7xS3enJ+JysUVxe0HNKzvIKGqZdvSyd1A291iITtFV1rURNJ/wi9sboOGrk1TLdvEKJ1HyRQOaYxlo/guFOziP2SdLixaEkVosurLG3q9BKePYRRx+qynvSH/et0jykU8zG1b9iGJuFuq09I99mvusTYXkvoFPaekK0JAgMBAAECgYA/ZPgmOcUGl+N1Si95WRPyW4pHR0rDxbYIk4VneHOjjgC8dsztcApPIYpRFaEHS80OYqcOJsoz7ypqBge35h1oRUIKlhJrRlg3Mheut4JMNxON0RGjAvBUzjxIv3dGtu9oSe2os/tjWo2PpYjvt49+paVaNNl1M7fNSFseixMSnQJBANgugC6qSABp615g8B8VMOHgs3ZtaXuRaZEwFNXtxrFXj8TT3qshyAlZ2qYqipMS76zsqvbPKghsnLKUBRinc2cCQQDLJK54H0ojJ80Oi6reofHT/BfuevgcKEvwql17BehxWqxNSrCprqo5oe27s9tO0aFQAGcHGebXi0UE2vXgP4YPAkAPMYV0wib5UHhoU6vMo3KiDWbPhfUJ6DhhKd3tAx8Zy6uhC7r0kBU9AwGo+AhxOb16i8+gNQMhQHDq5BhPmUU1AkAA/4Mj7/NrTMzDH4iNY3pOslRjxZLmgIQvAIdJDXRg6jMihhVsIAGSGuuSGoYfUwRZ4kafUE9EC9kg9vpySZ2dAkBykkHk86u3aHifUN+539rA6snLZkJ7ZKHzf9k6MikIfzxA9Je5ut8AWtMF9iNr3z3PLZS57zSo3tPluCK26vmy";
        String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALkEarKOGKLo+AY4scCp+AlhnLd8PCHtJJ/GuE7/NoU9UqUDb0xGtMqcmj0nj0n3++eXqkAcO7rGhMViKSiTLeu5H36Lp+8kne7H64jt8Ok5UwyDYNukdVszx9FUNnh06bXI2Z6tZQb39rRWA4ikl7PMBxj01PFiwmef6J2Mm3PlAgMBAAECgYBUtbb8ZZ7WCAB3KMIJykhsUyrjv5fwmJkqJlwAn00hGACc2MO+kqj4E1dzJIewgD/vuzsR+1tFmtrzXIwnratZ+Hf8zXrdjscl3R9ympo3VYcAd+Dp/+4CKbYo8PeVN7Y6ejWL0/uuVBpsXYYx1wZnsmHmkNAXhGr6DXdcFwArwQJBAOQcDlog4ah72FIQQa1hC4eiMit3jlCzzC5zqQCn4bxfhFIQ362MXMmPNeDwllRz73Xm0VbebekKnuxXWc9Pcc0CQQDPo4yKQmGcGopRS76b9nwlSqCCLqpMMm5qUslx8TIa2JApTLrkI8qnjAW8ORac/ZK+b+xFd6DfAEx+hdYFB1J5AkAp4sT348XL7EHCf92venxlgXD47lEhnIJHnVFRH4Kh1mAzuYl5EqcPE87u6P6Rr2AvJB0YeGtyOVMJTtjc6leZAkEAgdktUmkg0ZZnWV+c27vQ9yEsLz0vkmcXVbZf87l6M/+qZ6lsVq9rVEvkakjpM1QAKMelYevl1o9BRv47UPm7QQJBAMt+3m2RunomRVjaUafD35wGvDqauJno6Z/8KhgbowUCZqpftwdJI1+Xa5Z2Ad3g6pnPulgeah84ZZxOCbkCPaU=";

        return new DefaultRongClient(url, appId, privateKey, format, "utf-8");
    }
    public static String getAppId() {
        String appId = "2010418";
        return appId;
    }
}