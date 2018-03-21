package com.fqgj.youjie.auth.service.impl;
import com.fqgj.youjie.auth.entity.AdminLoginLog;
import com.fqgj.youjie.auth.service.AdminLoginLogService;
import com.fqgj.youjie.auth.dao.AdminLoginLogDao;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import com.fqgj.youjie.auth.vo.AddAdminLoginLogVo;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.fqgj.log.enhance.Module;

import java.util.Date;

/**
* User: jungle
* Date: 2017-12-29
* Time: 11:24:36.170
*/
@Module(value = "管理员登录日志表服务模块")
@NameSpace("com.fqgj.youjie.auth.dao.impl.AdminLoginLogDaoImpl")
@Service
public class AdminLoginLogServiceImpl extends AbstractBaseService implements AdminLoginLogService {

    @Autowired
    private AdminLoginLogDao adminLoginLogDao;

    @Override
    public Boolean addAdminLoginLog(AddAdminLoginLogVo addAdminLoginLogVo) {
        if(addAdminLoginLogVo == null){
            return null;
        }
        AdminLoginLog adminLoginLog = new AdminLoginLog();
        adminLoginLog.setAdminId(addAdminLoginLogVo.getAdminId());
        adminLoginLog.setLoginIp(addAdminLoginLogVo.getLoginIp());
        adminLoginLog.setLoginIpAddress(addAdminLoginLogVo.getLoginIpAddress());
        adminLoginLog.setLoginDate(new Date());
        adminLoginLogDao.insert(adminLoginLog);
        return Boolean.TRUE;
    }

}


