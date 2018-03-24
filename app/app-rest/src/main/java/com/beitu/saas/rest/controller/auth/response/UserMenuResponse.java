package com.beitu.saas.rest.controller.auth.response;

import com.beitu.saas.auth.vo.FormedMenuVO;
import com.fqgj.common.api.ResponseData;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaochong
 * @create 2018/3/23 上午10:30
 * @description
 */
public class UserMenuResponse implements ResponseData {

    private List<FormedMenuVO.ParentMenu> list = new ArrayList<>();

    public UserMenuResponse(FormedMenuVO formedMenuVO) {
        this.list = formedMenuVO.getList();
    }
}
