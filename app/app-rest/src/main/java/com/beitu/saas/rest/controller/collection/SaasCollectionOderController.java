package com.beitu.saas.rest.controller.collection;

import com.beitu.saas.app.annotations.SignIgnore;
import com.beitu.saas.app.application.collection.CollectionApplication;
import com.beitu.saas.app.application.collection.vo.CollectionOrderListVo;
import com.beitu.saas.collection.param.CollectionOrderQueryParam;
import com.beitu.saas.collection.vo.CollectionOrderInfoDetailVo;
import com.beitu.saas.rest.controller.collection.request.AddCollectionOrderNoteRequest;
import com.beitu.saas.rest.controller.collection.request.CollectionOrderQueryRequestParam;
import com.beitu.saas.rest.controller.collection.response.CollectionOrderListResponse;
import com.fqgj.common.api.ApiResponse;
import com.fqgj.common.api.Page;
import com.fqgj.common.response.ModuleResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
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
    private CollectionApplication collectionApplication;

    /**
     * 催收列表
     *
     * @param collectionOrderQueryParam
     * @param page
     * @return
     */
    @SignIgnore
    @RequestMapping(value = "/collectionOrderGet/list", method = RequestMethod.POST)
    @ApiOperation(value = "催收列表", response = CollectionOrderListResponse.class)
    public ModuleResponse<CollectionOrderListResponse> collectionDistributeGet(@RequestBody CollectionOrderQueryRequestParam collectionOrderQueryParam, Page page) {
        CollectionOrderQueryParam param = new CollectionOrderQueryParam();
        BeanUtils.copyProperties(collectionOrderQueryParam, param);
        List<CollectionOrderInfoDetailVo> collectionOrderList = collectionApplication.getCollectionOrderListByPage(param, page);
        return new ModuleResponse<>(new CollectionOrderListResponse(Arrays.asList(new CollectionOrderListVo())), page);
    }

    @SignIgnore
    @RequestMapping(value = "/collectionOrderGet/note", method = RequestMethod.POST)
    @ApiOperation(value = "新增催收记录", response = CollectionOrderListResponse.class)
    public ApiResponse addNote(@RequestBody AddCollectionOrderNoteRequest req) {
        return new ApiResponse("新增催记成功");
    }
}
