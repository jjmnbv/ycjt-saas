package com.beitu.saas.app.application.finance;

import com.beitu.saas.finance.client.SaasCreditHistoryService;
import com.beitu.saas.finance.client.SaasMerchantCreditInfoService;
import com.beitu.saas.finance.client.enums.CreditConsumeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xiaochong
 * @create 2018/4/2 下午11:20
 * @description
 */
@Component
public class FinanceApplication {

//    @Autowired
//    private SaasCreditHistoryService saasCreditHistoryService;
//
//    @Autowired
//    private SaasMerchantCreditInfoService saasMerchantCreditInfoService;
//
//    @Transactional(rollbackFor = Exception.class)
//    public Boolean consumeCredit(String merchantCode, String opName, CreditConsumeEnum creditConsumeEnum) {
//        Boolean success = saasMerchantCreditInfoService.decrease(merchantCode, creditConsumeEnum.getNum().longValue());
//        saasCreditHistoryService.addExpenditureCreditHistory(merchantCode, creditConsumeEnum.getNum().longValue(), opName, creditConsumeEnum.getDesc());
//        return success;
//    }
}
