package com.beitu.saas.auth.service.impl;

import com.beitu.saas.auth.dao.SaasAdminLoginLogDao;
import com.beitu.saas.auth.entity.SaasAdminLoginLog;
import com.beitu.saas.auth.service.SaasAdminLoginLogService;
import com.beitu.saas.common.utils.NetworkUtil;
import com.beitu.saas.common.utils.location.BDLocationUtils;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import com.fqgj.common.utils.JSONUtils;
import com.fqgj.log.enhance.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
* User: xiaochong
* Date: 2018-03-22
* Time: 15:33:55.839
*/
@Module(value = "管理员登录日志表服务模块")
@NameSpace("com.beitu.saas.auth.dao.impl.SaasAdminLoginLogDaoImpl")
@Service
public class SaasAdminLoginLogServiceImpl extends AbstractBaseService implements SaasAdminLoginLogService {


    @Autowired
    private SaasAdminLoginLogDao saasAdminLoginLogDao;

    @Override
    public Boolean addAdminLoginLog(HttpServletRequest request, String adminCode) {
        SaasAdminLoginLog adminLoginLog = new SaasAdminLoginLog();
        String ip=null;
        try {
             ip = NetworkUtil.getIpAddress(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, String> addressMap = BDLocationUtils.getGeoInfoByIpAddress(ip);
        try {
            adminLoginLog.setLoginIpAddress(JSONUtils.obj2json(addressMap));
        } catch (Exception e) {
            e.printStackTrace();
        }
        adminLoginLog.setLoginIp(ip);
        adminLoginLog.setAdminCode(adminCode);
        return saasAdminLoginLogDao.insert(adminLoginLog)!=null;
    }
}


