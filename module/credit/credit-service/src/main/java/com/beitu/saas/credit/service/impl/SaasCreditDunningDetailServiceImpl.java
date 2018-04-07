package com.beitu.saas.credit.service.impl;
import com.beitu.saas.credit.client.SaasCreditDunningDetailService;
import com.beitu.saas.credit.dao.SaasCreditDunningDetailDao;
import com.beitu.saas.credit.domain.SaasCreditDunningDetailVo;
import com.beitu.saas.credit.enums.CreditDunningDetailTypeEnum;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.fqgj.log.enhance.Module;

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

    }

    @Override
    public List<SaasCreditDunningDetailVo> listByRecordId(Long recordId, CreditDunningDetailTypeEnum dunningDetailTypeEnum) {
        return null;
    }
}


