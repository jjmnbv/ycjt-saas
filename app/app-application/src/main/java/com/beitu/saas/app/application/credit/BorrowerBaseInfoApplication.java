package com.beitu.saas.app.application.credit;

import com.beitu.saas.app.application.credit.vo.*;
import com.beitu.saas.borrower.client.SaasBorrowerPersonalInfoService;
import com.beitu.saas.borrower.domain.SaasBorrowerPersonalInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author linanjun
 * @create 2018/3/21 下午4:02
 * @description
 */
@Service
public class BorrowerBaseInfoApplication {

    @Autowired
    private SaasBorrowerPersonalInfoService saasBorrowerPersonalInfoService;

    public BorrowerPersonalInfoVo getUserPersonalInfoVo(String borrowerCode, String orderNumb) {
        SaasBorrowerPersonalInfoVo saasBorrowerPersonalInfoVo = saasBorrowerPersonalInfoService.getByBorrowerCodeAndOrderNumb(borrowerCode, orderNumb);
        // TODO
        return new BorrowerPersonalInfoVo();
    }

    public BorrowerIdentityInfoVo getUserIdentityInfoVo(String borrowerCode, String orderNumb) {
        // TODO
        return new BorrowerIdentityInfoVo();
    }

    public BorrowerWorkInfoVo getUserWorkInfoVo(String borrowerCode, String orderNumb) {
        // TODO
        return new BorrowerWorkInfoVo();
    }

    public BorrowerEmergentContactVo getUserEmergentContactVo(String borrowerCode, String orderNumb) {
        // TODO
        return new BorrowerEmergentContactVo();
    }

    public BorrowerLivingAreaVo getUserLivingAreaVo(String borrowerCode, String orderNumb) {
        // TODO
        return new BorrowerLivingAreaVo();
    }

}
