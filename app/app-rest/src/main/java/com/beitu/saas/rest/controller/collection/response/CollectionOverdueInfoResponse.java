package com.beitu.saas.rest.controller.collection.response;

import com.beitu.saas.collection.domain.OverdueInfoVo;
import com.fqgj.common.api.ResponseData;
import com.fqgj.common.utils.CollectionUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName:
 * @Description:
 * @Author watson
 * @Create 2017/5/8 0008 下午 4:04
 */
@ApiModel(value = "催收参数请求列表")
public class CollectionOverdueInfoResponse implements ResponseData {

    @ApiModelProperty(value = "催收查询参数")
    private List<Item> overdueParamList;

    public CollectionOverdueInfoResponse(List<OverdueInfoVo> overdueInfoVos) {
        if (CollectionUtils.isNotEmpty(overdueInfoVos)) {
            overdueParamList = new ArrayList<>();
            for (OverdueInfoVo param : overdueInfoVos) {
                Item item = new Item();
                item.setValue(param.getType());
                item.setName(param.getDesc());
                overdueParamList.add(item);
            }
        }
    }



    class Item {
        private Integer value;

        private String name;

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
