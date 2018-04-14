package com.beitu.saas.rest.controller.system.response;

import com.fqgj.common.api.ResponseData;
import com.fqgj.common.api.enums.MsgCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * @author linanjun
 * @create 2018/3/25 下午5:14
 * @description
 */
@ApiModel(value = "枚举信息")
public class EnumResponse implements ResponseData {

    @ApiModelProperty(value = "枚举列表信息")
    private List<EnumVo> enumVoList;

    public EnumResponse(MsgCodeEnum[] msgCodeEnumArray) {
        if (msgCodeEnumArray != null) {
            this.enumVoList = new ArrayList<>(msgCodeEnumArray.length);
            for (int i = 0; i < msgCodeEnumArray.length; i++) {
                this.enumVoList.add(new EnumVo(msgCodeEnumArray[i]));
            }
        }
    }

    public EnumResponse(List<EnumVo> enumVoList) {
        this.enumVoList = enumVoList;
    }

    public List<EnumVo> getEnumVoList() {
        return enumVoList;
    }

    public void setEnumVoList(List<EnumVo> enumVoList) {
        this.enumVoList = enumVoList;
    }

    public static class EnumVo {

        @ApiModelProperty(value = "枚举KEY")
        private Object code;

        @ApiModelProperty(value = "枚举VALUE")
        private String msg;

        public EnumVo() {
        }

        public EnumVo(MsgCodeEnum msgCodeEnum) {
            this.code = msgCodeEnum.getCode();
            this.msg = msgCodeEnum.getMsg();
        }

        public Object getCode() {
            return code;
        }

        public void setCode(Object code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }

}
