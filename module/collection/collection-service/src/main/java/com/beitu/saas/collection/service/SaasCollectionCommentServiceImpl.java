package com.beitu.saas.collection.service;

import com.beitu.saas.channel.client.SaasCollectionCommentService;
import com.beitu.saas.channel.domain.CollectionCommentVo;
import com.beitu.saas.collection.dao.SaasCollectionCommentDao;
import com.beitu.saas.collection.entity.SaasCollectionCommentEntity;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import com.fqgj.log.enhance.Module;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* User: fenqiguanjia
* Date: 2018-03-21
* Time: 20:58:19.353
*/
@Module(value = "订单催收表服务模块")
@NameSpace("com.beitu.saas.collection.dao.impl.SaasCollectionCommentDaoImpl")
@Service
public class SaasCollectionCommentServiceImpl extends AbstractBaseService implements SaasCollectionCommentService {


    @Autowired
    private SaasCollectionCommentDao saasCollectionCommentDao;

    @Override
    public void createCollectionComment(CollectionCommentVo commentVo) {
        SaasCollectionCommentEntity entity=new SaasCollectionCommentEntity();
        BeanUtils.copyProperties(commentVo,entity);
        saasCollectionCommentDao.insert(entity);
    }
}


