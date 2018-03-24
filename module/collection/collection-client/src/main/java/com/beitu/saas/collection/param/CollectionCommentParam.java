package com.beitu.saas.collection.param;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/3/23
 * Time: 下午2:10
 */
public class CollectionCommentParam {
    /**
     *订单号
     */
    private String orderNo;
    /**
     *姓名
     */
    private String name;
    /**
     *手机号
     */
    private String mobile;
    /**
     *内容
     */
    private String content;
    /**
     *跟进人ID
     */
    private Integer followId;
    /**
     *跟进人名字
     */
    private String followUp;

    public String getOrderNo() {
        return orderNo;
    }

    public CollectionCommentParam setOrderNo(String orderNo) {
        this.orderNo = orderNo;
        return this;
    }

    public String getName() {
        return name;
    }

    public CollectionCommentParam setName(String name) {
        this.name = name;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public CollectionCommentParam setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getContent() {
        return content;
    }

    public CollectionCommentParam setContent(String content) {
        this.content = content;
        return this;
    }

    public Integer getFollowId() {
        return followId;
    }

    public CollectionCommentParam setFollowId(Integer followId) {
        this.followId = followId;
        return this;
    }

    public String getFollowUp() {
        return followUp;
    }

    public CollectionCommentParam setFollowUp(String followUp) {
        this.followUp = followUp;
        return this;
    }
}
