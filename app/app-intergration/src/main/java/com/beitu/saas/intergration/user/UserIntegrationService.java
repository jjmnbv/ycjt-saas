package com.beitu.saas.intergration.user;

import com.beitu.saas.intergration.user.dto.UserMultiDebitDto;
import com.beitu.saas.intergration.user.dto.UserNameIdNoValidationDto;
import com.beitu.saas.intergration.user.param.UserMultiDebitParam;
import com.beitu.saas.intergration.user.param.UserNameIdNoValidationParam;

public interface UserIntegrationService {

    /**
     * 用户姓名身份证信息匹配校验接口
     *
     * @param param
     * @return
     */
    UserNameIdNoValidationDto userNameMatchIdNo(UserNameIdNoValidationParam param);

    /**
     * 用户借贷行为统计
     *
     * @param param
     * @return
     */
    UserMultiDebitDto userMultiDebit(UserMultiDebitParam param, String merchantCode);

}
