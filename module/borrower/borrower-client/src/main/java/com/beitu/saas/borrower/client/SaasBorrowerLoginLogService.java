package com.beitu.saas.borrower.client;

import com.beitu.saas.borrower.domain.SaasBorrowerLoginLogVo;
import com.beitu.saas.borrower.entity.SaasBorrowerLoginLog;
import com.fqgj.common.base.BaseService;
import com.fqgj.common.entity.BaseEntity;

import java.util.List;

/**
 * User: jungle
 * Date: 2018-04-03
 * Time: 15:11:35.421
 */
public interface SaasBorrowerLoginLogService<T extends BaseEntity> extends BaseService<T> {

    String getBorrowerPhoneSystem(String borrowerCode);

    List<SaasBorrowerLoginLogVo> listBorrowerLivingArea(String borrowerCode);

}