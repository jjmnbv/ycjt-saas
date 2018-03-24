package com.beitu.saas.rest.controller.collection;

import com.beitu.saas.channel.client.SaasCollectionOrderService;
import com.beitu.saas.channel.param.CollectionOrderQueryParam;
import com.beitu.saas.channel.vo.CollectionOrderInfoDetailVo;
import com.beitu.saas.rest.controller.collection.request.CollectionOrderQueryRequestParam;
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
    public ModuleResponse collectionDistributeGet(@RequestBody CollectionOrderQueryRequestParam collectionOrderQueryParam, Page page) {
        CollectionOrderQueryParam param = new CollectionOrderQueryParam();
        BeanUtils.copyProperties(collectionOrderQueryParam, param);
        List<CollectionOrderInfoDetailVo> collectionOrderListByPage = saasCollectionOrderService.getCollectionOrderListByPage(param, page);
        return new ModuleResponse<>(new CollectionOrderListResponse(collectionOrderListByPage), page);
    }
}
