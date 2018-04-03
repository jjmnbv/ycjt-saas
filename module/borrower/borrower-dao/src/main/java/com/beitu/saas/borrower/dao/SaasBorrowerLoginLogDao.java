package com.beitu.saas.borrower.dao;

import com.fqgj.common.base.BaseMapper;
import com.beitu.saas.borrower.entity.SaasBorrowerLoginLog;

import java.util.List;

/**
 * User: jungle
 * Date: 2018-04-03
 * Time: 15:11:35.205
 */

public interface SaasBorrowerLoginLogDao extends BaseMapper<SaasBorrowerLoginLog> {

    String selectPhoneSystemByBorrowerCode(String borrowerCode);

    List<SaasBorrowerLoginLog> listBorrowerLivingArea(String borrowerCode);

}