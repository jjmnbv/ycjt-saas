package com.beitu.saas.credit.client;
import com.fqgj.common.base.BaseService;
import com.fqgj.common.entity.BaseEntity;
import com.beitu.saas.credit.domain.PhoneBookInfoVo;

import java.util.List;

/**
* User: linchengyu
* Date: 2017-06-29
* Time: 21:21:33.454
*/
public interface PhoneBookInfoService<T extends BaseEntity> extends BaseService<T> {
    
    /**
     * @param prefixSeven
     * @return
     */
    PhoneBookInfoVo getPhoneBookInfoByPhonePrefix(String prefixSeven);
    
    /**
     * @param areaCode
     * @return
     */
    PhoneBookInfoVo getPhoneBookInfoByAreaCode(String areaCode);

    List<PhoneBookInfoVo> getAll();

}