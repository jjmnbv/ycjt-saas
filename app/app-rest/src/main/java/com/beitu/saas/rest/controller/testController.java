package com.beitu.saas.rest.controller;

import com.beitu.saas.app.annotations.SignIgnore;
import com.beitu.saas.app.annotations.VisitorAccessible;
import com.beitu.saas.collection.client.SaasCollectionCommentService;
import com.beitu.saas.collection.client.SaasCollectionOrderService;
import com.beitu.saas.collection.param.CollectionCommentParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/3/25
 * Time: 下午2:01
 */
@RestController
public class testController {

    @Autowired
    private SaasCollectionOrderService collectionOrderService;
    @Autowired
    private SaasCollectionCommentService collectionCommentService;

    @RequestMapping("/order")
    @ResponseBody
    @VisitorAccessible
    @SignIgnore
    public String order() {
//        collectionOrderService.createCollectionOrder("20180324");
        collectionOrderService.closeOrder("20180324");
        return "ok";
    }

    @RequestMapping("/comment")
    @ResponseBody
    @VisitorAccessible
    @SignIgnore
    public String comment() {
        CollectionCommentParam collectionCommentParam=new CollectionCommentParam()
                .setName("梁宇")
                .setMobile("18010776363")
                .setOrderNo("20180324")
                .setContent("还钱")
                .setFollowId(12)
                .setFollowUp("admin");
        collectionCommentService.createCollectionComment(collectionCommentParam);
        return "ok";
    }
}
