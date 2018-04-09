package com.beitu.saas.rest.controller.collection;

import com.beitu.saas.app.annotations.HasPermission;
import com.beitu.saas.app.annotations.SignIgnore;
import com.beitu.saas.app.annotations.VisitorAccessible;
import com.beitu.saas.app.api.ApiResponse;
import com.beitu.saas.app.application.collection.CollectionApplication;
import com.beitu.saas.app.application.collection.vo.CollectionOrderListVo;
import com.beitu.saas.app.common.RequestLocalInfo;
import com.beitu.saas.collection.client.SaasCollectionCommentService;
import com.beitu.saas.collection.domain.OverdueInfoVo;
import com.beitu.saas.collection.param.CollectionCommentParam;
import com.beitu.saas.collection.param.CollectionOrderQueryParam;
import com.beitu.saas.common.consts.ButtonPermissionConsts;
import com.beitu.saas.rest.controller.collection.request.AddCollectionOrderNoteRequest;
import com.beitu.saas.rest.controller.collection.request.CollectionOrderQueryRequestParam;
import com.beitu.saas.rest.controller.collection.response.CollectionOrderListResponse;
import com.beitu.saas.rest.controller.collection.response.CollectionOverdueInfoResponse;
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
    @Autowired
    private SaasCollectionCommentService saasCollectionCommentService;

    /**
     * 催收列表
     *
     * @param collectionOrderQueryParam
     * @param page
     * @return
     */
    @RequestMapping(value = "/collectionOrderGet/list", method = RequestMethod.POST)
    @ApiOperation(value = "催收列表", response = CollectionOrderListResponse.class)
    public ModuleResponse<CollectionOrderListResponse> collectionDistributeGet(@RequestBody CollectionOrderQueryRequestParam collectionOrderQueryParam, Page page) {
        CollectionOrderQueryParam param = new CollectionOrderQueryParam();
        BeanUtils.copyProperties(collectionOrderQueryParam, param);
        param.setMerchantCode(RequestLocalInfo.getCurrentAdmin().getSaasAdmin().getMerchantCode());

        List<CollectionOrderListVo> collectionOrderList = collectionApplication.getCollectionOrderListByPage(param, page);
        return new ModuleResponse<>(new CollectionOrderListResponse(collectionOrderList), page);
    }

    @RequestMapping(value = "/collectionOrderAdd/note", method = RequestMethod.POST)
    @ApiOperation(value = "新增催收记录", response = ApiResponse.class)
    @HasPermission(permissionKey = ButtonPermissionConsts.ADD_COLLECTION_REMARKS)
    public ApiResponse addNote(@RequestBody AddCollectionOrderNoteRequest req) {
        CollectionCommentParam collectionCommentParam = new CollectionCommentParam();
        BeanUtils.copyProperties(req, collectionCommentParam);
        String followCode = RequestLocalInfo.getCurrentAdmin().getSaasAdmin().getCode();
        String followUp = RequestLocalInfo.getCurrentAdmin().getSaasAdmin().getName();
        saasCollectionCommentService.createCollectionComment(collectionCommentParam, followCode, followUp);
        return new ApiResponse("新增催记成功");
    }


    /**
     * 催收模块下拉栏相关数据
     *
     * @return
     */
    @RequestMapping(value = "/overdueItem/query", method = RequestMethod.POST)
    @ApiOperation(value = "催收模块下拉栏逾期参数查询", response = CollectionOverdueInfoResponse.class)
    public Response getOverdueTimeVoList() {
        List<OverdueInfoVo> overdueTimeVoList = collectionApplication.getOverdueTimeVoList();
        return Response.ok().putData(new CollectionOverdueInfoResponse(overdueTimeVoList));
    }
}
