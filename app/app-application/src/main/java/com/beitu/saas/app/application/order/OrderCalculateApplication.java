package com.beitu.saas.app.application.order;

import com.beitu.saas.common.utils.DateUtil;
import com.beitu.saas.common.utils.DecimalUtils;
import com.beitu.saas.order.domain.SaasOrderBillDetailVo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author linanjun
 * @create 2018/3/26 下午7:56
 * @description
 */
@Service
public class OrderCalculateApplication {

    /**
     * 得到应还金额
     *
     * @param saasOrderBillDetailVo 账单信息
     * @return
     */
    public BigDecimal getAmount(SaasOrderBillDetailVo saasOrderBillDetailVo) {
        BigDecimal lateInterest = BigDecimal.ZERO;
        Integer lateDt = DateUtil.countDays(new Date(), saasOrderBillDetailVo.getRepaymentDt());
        if (lateDt > 0) {
            lateInterest = saasOrderBillDetailVo.getRealCapital().multiply(saasOrderBillDetailVo.getLateInterestRatio()).multiply(new BigDecimal(lateDt)).divide(new BigDecimal(365), BigDecimal.ROUND_HALF_UP).setScale(2);
        }
        return saasOrderBillDetailVo.getRealCapital().add(saasOrderBillDetailVo.getNeedPayInterest()).add(saasOrderBillDetailVo.getInterest()).add(lateInterest);
    }

    /**
     * 得到 借款利息
     *
     * @param realCapital        本金
     * @param totalInterestRatio 借款年利率
     * @param createdDt          申请日期
     * @param repaymentDt        应还日期
     * @return
     */
    public BigDecimal getInterest(BigDecimal realCapital, BigDecimal totalInterestRatio, Date createdDt, Date repaymentDt) {
        BigDecimal interest = BigDecimal.ZERO;
        Integer lendDt = DateUtil.countDays(repaymentDt, createdDt);
        if (lendDt > 0) {
            interest = realCapital.multiply(totalInterestRatio).multiply(new BigDecimal(lendDt)).divide(new BigDecimal(365), 2, BigDecimal.ROUND_HALF_UP);
        }
        return interest;
    }

}
