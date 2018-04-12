package com.beitu.saas.borrower.dao;

import com.beitu.saas.borrower.BorrowerInfoParam;
import com.beitu.saas.borrower.vo.SaasBorrowerManagerVo;
import com.fqgj.common.api.Page;
import com.fqgj.common.base.BaseMapper;
import com.beitu.saas.borrower.entity.SaasBorrower;

import java.util.List;

/**
 * User: jungle
 * Date: 2018-03-22
 * Time: 15:56:46.841
 */

public interface SaasBorrowerDao extends BaseMapper<SaasBorrower> {
    List<SaasBorrowerManagerVo> selectBorrowerInfoList(BorrowerInfoParam borrowerInfoParam, Page page);

    Integer countSelectBorrowerInfoList(BorrowerInfoParam borrowerInfoParam);

}