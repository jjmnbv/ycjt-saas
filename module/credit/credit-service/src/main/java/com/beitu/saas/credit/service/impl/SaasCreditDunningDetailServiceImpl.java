package com.beitu.saas.credit.service.impl;

import com.beitu.saas.credit.client.SaasCreditDunningDetailService;
import com.beitu.saas.credit.dao.SaasCreditDunningDetailDao;
import com.beitu.saas.credit.domain.SaasCreditDunningDetailVo;
import com.beitu.saas.credit.entity.SaasCreditDunningDetail;
import com.beitu.saas.credit.enums.CreditDunningDetailTypeEnum;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import com.fqgj.common.utils.CollectionUtils;
import com.fqgj.log.enhance.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * User: jungle
 * Date: 2018-04-06
 * Time: 18:11:44.668
 */
@Module(value = "用户催收数据详情表服务模块")
@NameSpace("com.beitu.saas.credit.dao.impl.SaasCreditDunningDetailDaoImpl")
@Service
public class SaasCreditDunningDetailServiceImpl extends AbstractBaseService implements SaasCreditDunningDetailService {

    @Autowired
    private SaasCreditDunningDetailDao saasCreditDunningDetailDao;

    @Override
    public void batchAddSaasCreditDunningDetail(List list) {
        if (CollectionUtils.isNotEmpty(list)) {
            List<SaasCreditDunningDetail> addList = new ArrayList<>(list.size());
            list.forEach(object -> addList.add(SaasCreditDunningDetailVo.convertVOToEntity((SaasCreditDunningDetailVo) object)));
            saasCreditDunningDetailDao.batchAddSaasCreditDunningDetail(addList);
        }
    }

    @Override
    public List<SaasCreditDunningDetailVo> listByRecordId(Long recordId, CreditDunningDetailTypeEnum dunningDetailTypeEnum) {
        List<SaasCreditDunningDetail> saasCreditDunningDetailList = saasCreditDunningDetailDao.selectByParams(new HashMap<String, Object>(4) {{
            put("recordId", recordId);
            if (dunningDetailTypeEnum != null) {
                put("type", dunningDetailTypeEnum.getType());
            }
            put("deleted", Boolean.FALSE);
        }});
        if (CollectionUtils.isEmpty(saasCreditDunningDetailList)) {
            return null;
        }
        List<SaasCreditDunningDetailVo> results = new ArrayList<>(saasCreditDunningDetailList.size());
        saasCreditDunningDetailList.forEach(saasCreditDunningDetail -> results.add(SaasCreditDunningDetailVo.convertEntityToVO(saasCreditDunningDetail)));
        return results;
    }
}


