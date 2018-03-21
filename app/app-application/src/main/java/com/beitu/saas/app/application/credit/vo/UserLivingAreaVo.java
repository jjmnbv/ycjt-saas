package com.beitu.saas.app.application.credit.vo;

import com.fqgj.common.api.ResponseData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author linanjun
 * @create 2018/3/21 下午10:17
 * @description
 */
@ApiModel(value = "用户生活区域信息")
public class UserLivingAreaVo implements ResponseData {

    @ApiModelProperty(value = "用户生活区域详情列表")
    private List<UserLivingAreaDetailVo> userLivingAreaDetailVoList;

    public List<UserLivingAreaDetailVo> getUserLivingAreaDetailVoList() {
        return userLivingAreaDetailVoList;
    }

    public void setUserLivingAreaDetailVoList(List<UserLivingAreaDetailVo> userLivingAreaDetailVoList) {
        this.userLivingAreaDetailVoList = userLivingAreaDetailVoList;
    }

}
