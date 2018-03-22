package com.beitu.saas.app.application.credit;

import com.beitu.saas.app.application.credit.vo.*;
import org.springframework.stereotype.Service;

/**
 * @author linanjun
 * @create 2018/3/21 下午4:02
 * @description
 */
@Service
public class BorrowerBaseInfoApplication {

    public BorrowerPersonalInfoVo getUserPersonalInfoVo(String userCode) {
        // TODO
        return new BorrowerPersonalInfoVo();
    }

    public BorrowerIdentityInfoVo getUserIdentityInfoVo(String userCode) {
        // TODO
        return new BorrowerIdentityInfoVo();
    }

    public BorrowerWorkInfoVo getUserWorkInfoVo(String userCode) {
        // TODO
        return new BorrowerWorkInfoVo();
    }

    public BorrowerEmergentContactVo getUserEmergentContactVo(String userCode) {
        // TODO
        return new BorrowerEmergentContactVo();
    }

    public BorrowerLivingAreaVo getUserLivingAreaVo(String userCode) {
        // TODO
        return new BorrowerLivingAreaVo();
    }

}
