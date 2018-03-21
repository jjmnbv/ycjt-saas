package com.fqgj.youjie.auth.service;
import com.fqgj.common.base.BaseService;
import com.fqgj.common.entity.BaseEntity;
import com.fqgj.youjie.auth.vo.AddAdminLoginLogVo;

/**
* User: jungle
* Date: 2017-12-29
* Time: 11:24:36.159
*/
public interface AdminLoginLogService<T extends BaseEntity> extends BaseService<T> {

    Boolean addAdminLoginLog(AddAdminLoginLogVo addAdminLoginLogVo);

}