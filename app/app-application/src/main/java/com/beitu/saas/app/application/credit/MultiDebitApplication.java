package com.beitu.saas.app.application.credit;

import com.beitu.saas.intergration.user.UserIntegrationService;
import com.beitu.saas.intergration.user.dto.UserMultiDebitDto;
import com.beitu.saas.intergration.user.param.UserMultiDebitParam;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/4/16
 * Time: 下午5:44
 */
@Component
public class MultiDebitApplication {
    private static final Log LOGGER = LogFactory.getLog(MultiDebitApplication.class);


    @Autowired
    private UserIntegrationService userIntegrationService;

    /**
     * 用户借贷信息统计
     *
     * @param name
     * @param identityNo
     * @param mobile
     * @return
     */
    public String getMultiDebitInfo(String name, String identityNo, String mobile) {
        UserMultiDebitParam userMultiDebitParam = new UserMultiDebitParam(name, identityNo, mobile);
        UserMultiDebitDto userMultiDebitDto = userIntegrationService.userMultiDebit(userMultiDebitParam);
        return userMultiDebitDto.getResult();
    }

}
