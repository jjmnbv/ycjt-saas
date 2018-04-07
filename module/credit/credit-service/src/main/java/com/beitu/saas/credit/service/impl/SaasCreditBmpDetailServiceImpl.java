package com.beitu.saas.credit.service.impl;

import com.beitu.saas.credit.client.SaasCreditBmpDetailService;
import com.beitu.saas.credit.dao.SaasCreditBmpDetailDao;
import com.beitu.saas.credit.domain.SaasCreditBmpDetailVo;
import com.beitu.saas.credit.entity.SaasCreditBmpDetail;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import com.fqgj.common.utils.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.fqgj.log.enhance.Module;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * User: jungle
 * Date: 2018-04-06
 * Time: 18:11:44.620
 */
@Module(value = "电话邦匹配数据详情表服务模块")
@NameSpace("com.beitu.saas.credit.dao.impl.SaasCreditBmpDetailDaoImpl")
@Service
public class SaasCreditBmpDetailServiceImpl extends AbstractBaseService implements SaasCreditBmpDetailService {

    @Autowired
    private SaasCreditBmpDetailDao saasCreditBmpDetailDao;

    @Override
    public SaasCreditBmpDetail addSaasCreditBmpDetail(SaasCreditBmpDetailVo saasCreditBmpDetailVo) {
        return saasCreditBmpDetailDao.insert(SaasCreditBmpDetailVo.convertVOToEntity(saasCreditBmpDetailVo));
    }

    @Override
    public List<SaasCreditBmpDetailVo> listByRecordId(Long recordId) {
        List<SaasCreditBmpDetail> saasCreditBmpDetailList = saasCreditBmpDetailDao.selectByParams(new HashMap<String, Object>(4) {{
            put("recordId", recordId);
            put("deleted", Boolean.FALSE);
        }});
        if (CollectionUtils.isEmpty(saasCreditBmpDetailList)) {
            return null;
        }
        List<SaasCreditBmpDetailVo> results = new ArrayList<>(saasCreditBmpDetailList.size());
        saasCreditBmpDetailList.forEach(saasCreditBmpDetail -> results.add(SaasCreditBmpDetailVo.convertEntityToVO(saasCreditBmpDetail)));
        return results;
    }

}


