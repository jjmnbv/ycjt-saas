package com.beitu.saas.app.application.collection;

import com.beitu.saas.app.application.collection.vo.CollectionCommentListVo;
import com.beitu.saas.app.application.collection.vo.CollectionOrderListVo;
import com.beitu.saas.app.application.order.OrderCalculateApplication;
import com.beitu.saas.collection.client.SaasCollectionCommentService;
import com.beitu.saas.collection.client.SaasCollectionOrderService;
import com.beitu.saas.collection.domain.OverdueInfoVo;
import com.beitu.saas.collection.entity.SaasCollectionCommentEntity;
import com.beitu.saas.collection.enums.OverdueTimeEnum;
import com.beitu.saas.collection.param.CollectionOrderQueryParam;
import com.beitu.saas.collection.vo.CollectionOrderInfoDetailVo;
import com.beitu.saas.common.utils.DateUtil;
import com.beitu.saas.common.utils.identityNumber.vo.IdcardInfoExtractor;
import com.beitu.saas.order.client.SaasOrderBillDetailService;
import com.beitu.saas.order.client.SaasOrderStatusHistoryService;
import com.beitu.saas.order.domain.SaasOrderBillDetailVo;
import com.beitu.saas.order.entity.SaasOrderStatusHistory;
import com.fqgj.common.api.Page;
import com.fqgj.common.utils.CollectionUtils;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/3/27
 * Time: 下午8:56
 */
@Component
public class CollectionApplication {

    private static final Log LOGGER = LogFactory.getLog(CollectionApplication.class);

    @Autowired
    private SaasCollectionOrderService saasCollectionOrderService;

    @Autowired
    private SaasOrderBillDetailService saasOrderBillDetailService;

    @Autowired
    private OrderCalculateApplication orderCalculateApplication;

    @Autowired
    private SaasCollectionCommentService saasCollectionCommentService;

    public List<CollectionOrderListVo> getCollectionOrderListByPage(CollectionOrderQueryParam collectionOrderQueryParam, Page page) {
        List<CollectionOrderInfoDetailVo> collectionOrderInfoDetailVos = saasCollectionOrderService.getCollectionOrderListByPage(collectionOrderQueryParam, page);

        collectionOrderInfoDetailVos.stream().forEach(x -> {
            SaasOrderBillDetailVo saasOrderBillDetailVo = saasOrderBillDetailService.getVisibleOrderBillDetailByOrderNumbAndMerchantCode(x.getOrderNo(), collectionOrderQueryParam.getMerchantCode());
            BigDecimal shouldAmount = BigDecimal.ZERO;
            try {
                shouldAmount = orderCalculateApplication.getAmount(saasOrderBillDetailVo);
            } catch (Exception e) {
                LOGGER.info("清算应还金额出错,异常原因:{} ", e);
            }
            x.setShouldRepayCapital(shouldAmount);
            if (x.getOverdueDuration() < 0) {
                x.setOverdueDuration(0);
            }
        });

        List<CollectionOrderListVo> collectionOrderListVos = new ArrayList<>();
        collectionOrderInfoDetailVos.stream().forEach(x -> {
            CollectionOrderListVo collectionOrderListVo = new CollectionOrderListVo();
            BeanUtils.copyProperties(x, collectionOrderListVo);

            collectionOrderListVos.add(collectionOrderListVo);
        });
        return collectionOrderListVos;
    }

    public List<CollectionCommentListVo> getAllCollectionCommentByOrderNumb(String merchantCode, String orderNumb) {
        List<SaasCollectionCommentEntity> saasCollectionCommentEntityList = saasCollectionCommentService.selectByParams(new HashMap<String, Object>(4) {{
            put("orderNo", orderNumb);
            put("deleted", Boolean.FALSE);
        }});
        if (CollectionUtils.isEmpty(saasCollectionCommentEntityList)) {
            return null;
        }
        List<CollectionCommentListVo> results = new ArrayList<>(saasCollectionCommentEntityList.size());
        saasCollectionCommentEntityList.forEach(saasCollectionCommentEntity -> results.add(convertSaasCollectionCommentEntity2CollectionCommentListVo(saasCollectionCommentEntity)));
        return results;
    }

    private CollectionCommentListVo convertSaasCollectionCommentEntity2CollectionCommentListVo(SaasCollectionCommentEntity saasCollectionCommentEntity) {
        if (saasCollectionCommentEntity == null) {
            return null;
        }
        CollectionCommentListVo collectionCommentListVo = new CollectionCommentListVo();
        collectionCommentListVo.setCollectionDate(DateUtil.getDateTime(saasCollectionCommentEntity.getGmtCreate()));
        collectionCommentListVo.setCollectionName(saasCollectionCommentEntity.getName());
        collectionCommentListVo.setCollectionMobile(saasCollectionCommentEntity.getMobile());
        collectionCommentListVo.setRemark(saasCollectionCommentEntity.getContent());
        return collectionCommentListVo;
    }

    /**
     * 获取预期天数参数
     *
     * @return
     */
    public List<OverdueInfoVo> getOverdueTimeVoList() {
        List<OverdueInfoVo> vos = new ArrayList<>();
        for (OverdueTimeEnum enums : OverdueTimeEnum.values()) {
            OverdueInfoVo vo = new OverdueInfoVo();
            vo.setType(enums.getType());
            vo.setDesc(enums.getDesc());
            vos.add(vo);
        }
        return vos;
    }

}
