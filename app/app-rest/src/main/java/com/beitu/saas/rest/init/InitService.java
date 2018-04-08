package com.beitu.saas.rest.init;

import com.beitu.saas.common.config.ConfigUtil;
import com.beitu.saas.credit.client.PhoneBookInfoService;
import com.beitu.saas.credit.domain.PhoneBookInfoVo;
import com.beitu.saas.intergration.esign.init.InitEsign;
import com.fqgj.base.services.redis.RedisClient;
import com.fqgj.common.utils.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by linchengyu on 17/6/27.
 */
@Service
public class InitService {

    @Autowired
    private ServletContext servletContext;

    @Autowired
    private PhoneBookInfoService phoneBookInfoService;

    @Autowired
    private ConfigUtil configUtil;

    @Autowired
    private InitEsign initEsign;

    @PostConstruct
    public void init() {

        initEsign.initProject();

        if (!configUtil.shouldPreload()) {
            return;
        }

        try {
            List<PhoneBookInfoVo> phoneBookInfoVoList = phoneBookInfoService.getAll();
            if (CollectionUtils.isNotEmpty(phoneBookInfoVoList)) {
                Map<String, String> phoneBookMap = new HashMap();
                for (PhoneBookInfoVo phoneBookInfoVo : phoneBookInfoVoList) {
                    String phoneHeader = phoneBookInfoVo.getPhoneHeader();
                    String areaCode = phoneBookInfoVo.getAreaCode();
                    String location = phoneBookInfoVo.getProvince() + phoneBookInfoVo.getCity();
                    if (!phoneBookMap.containsKey(phoneHeader)) {
                        phoneBookMap.put(phoneHeader, location);
                    }
                    if (!phoneBookMap.containsKey(areaCode)) {
                        phoneBookMap.put(areaCode, location);
                    }
                }
                servletContext.setAttribute("phoneBookMap", phoneBookMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
