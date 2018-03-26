package com.beitu.saas.rest.controller.collection;

import com.beitu.saas.app.annotations.SignIgnore;
import com.beitu.saas.app.annotations.VisitorAccessible;
import com.beitu.saas.collection.client.SaasCollectionOrderService;
import com.beitu.saas.collection.param.CollectionOrderQueryParam;
import com.beitu.saas.collection.vo.CollectionOrderInfoDetailVo;
import com.beitu.saas.rest.controller.collection.request.CollectionOrderQueryRequestParam;
import com.beitu.saas.rest.controller.collection.response.CollectionOrderListResponse;
import com.fqgj.common.api.Page;
import com.fqgj.common.api.Response;
import com.fqgj.common.response.ModuleResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    @SignIgnore
    @RequestMapping(value = "/collectionOrderGet/list", method = RequestMethod.POST)
    @ApiOperation(value = "催收列表", response = ModuleResponse.class)
    public ModuleResponse collectionDistributeGet(@RequestBody CollectionOrderQueryRequestParam collectionOrderQueryParam, Page page) {
        CollectionOrderQueryParam param = new CollectionOrderQueryParam();
        BeanUtils.copyProperties(collectionOrderQueryParam, param);
        List<CollectionOrderInfoDetailVo> collectionOrderList = saasCollectionOrderService.getCollectionOrderListByPage(param, page);
        return new ModuleResponse<>(new CollectionOrderListResponse(collectionOrderList), page);
    }
}
