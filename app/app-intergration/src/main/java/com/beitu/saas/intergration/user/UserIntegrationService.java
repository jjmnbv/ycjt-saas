package com.beitu.saas.intergration.user;

import com.beitu.saas.intergration.user.dto.UserNameIdNoValidationDto;
import com.beitu.saas.intergration.user.param.UserNameIdNoValidationParam;

public interface UserIntegrationService {
    
    /**
     *
     * 用户姓名身份证信息匹配校验接口
     *
     * @param param
     * @return
     */
    UserNameIdNoValidationDto userNameMatchIdNo(UserNameIdNoValidationParam param);
    
}
