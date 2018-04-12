package com.beitu.saas.borrower.dao.impl;
import com.beitu.saas.borrower.BorrowerInfoParam;
import com.beitu.saas.borrower.vo.SaasBorrowerManagerVo;
import com.fqgj.common.api.Page;
import com.fqgj.common.base.AbstractBaseMapper;
import com.beitu.saas.borrower.dao.SaasBorrowerDao;
import com.beitu.saas.borrower.entity.SaasBorrower;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* User: jungle
* Date: 2018-03-22
* Time: 15:56:46.846
*/

@Repository
public class SaasBorrowerDaoImpl extends AbstractBaseMapper<SaasBorrower> implements SaasBorrowerDao {


    @Override
    public List<SaasBorrowerManagerVo> selectBorrowerInfoList(BorrowerInfoParam borrowerInfoParam, Page page) {
        Map<String, Object> map = new HashMap<>();
        map.put("borrowerInfoParam", borrowerInfoParam);
        if (page != null) {
            page.setTotalCount(this.countSelectBorrowerInfoList(borrowerInfoParam));
            map.put("page", page);
        }
        return getSqlSession().selectList(this.getStatement("selectBorrowerInfoList"), map);
    }


    @Override
    public Integer countSelectBorrowerInfoList(BorrowerInfoParam borrowerInfoParam) {
        Map<String, Object> map = new HashMap<>();
        map.put("borrowerInfoParam", borrowerInfoParam);
        return getSqlSession().selectOne(this.getStatement("countSelectBorrowerInfoList"), map);    }
}