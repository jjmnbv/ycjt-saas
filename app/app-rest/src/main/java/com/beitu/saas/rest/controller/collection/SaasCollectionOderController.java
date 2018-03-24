package com.beitu.saas.rest.controller.collection;

import com.beitu.saas.channel.client.SaasCollectionOrderService;
import com.beitu.saas.channel.domain.CollectionOrderQueryVo;
import com.beitu.saas.collection.vo.CollectionOrderInfoDetailVo;
import com.beitu.saas.rest.controller.collection.request.CollectionOrderQueryParam;
import com.beitu.saas.rest.controller.collection.response.CollectionOrderListResponse;
import com.fqgj.common.api.Page;
import com.fqgj.common.response.ModuleResponse;
import io.swagger.annotations.Api;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/3/24
 * Time: 下午3:43
 */
@Api(description = "催收订单相关接口")
@RestController
@RequestMapping("/collection")
public class SaasCollectionOderController {
    @Autowired
    private SaasCollectionOrderService saasCollectionOrderService;

    /**
     * 催收列表
     *
     * @param collectionOrderQueryParam
     * @param page
     * @return
     */
    @RequestMapping("/collectionOrderGet/list")
    public ModuleResponse collectionDistributeGet(@RequestBody CollectionOrderQueryParam collectionOrderQueryParam, Page page) {
        CollectionOrderQueryVo collectionOrderQueryVo=new CollectionOrderQueryVo();
        BeanUtils.copyProperties(collectionOrderQueryParam,collectionOrderQueryVo);
        List<CollectionOrderInfoDetailVo> collectionOrderListByPage = saasCollectionOrderService.getCollectionOrderListByPage(collectionOrderQueryVo, page);
        return new ModuleResponse<>(new CollectionOrderListResponse(collectionOrderListByPage), page);
    }
}
