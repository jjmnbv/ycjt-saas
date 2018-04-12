package com.beitu.saas.app.application.order;

import com.beitu.saas.common.utils.DateUtil;
import com.beitu.saas.common.utils.DecimalUtils;
import com.beitu.saas.order.domain.SaasOrderBillDetailVo;
import com.beitu.saas.order.domain.SaasOrderVo;
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
        Integer lateDt = DateUtil.countDay(new Date(), saasOrderBillDetailVo.getRepaymentDt());
        if (lateDt > 0) {
            lateInterest = saasOrderBillDetailVo.getRealCapital().multiply(saasOrderBillDetailVo.getLateInterestRatio()).multiply(new BigDecimal(lateDt)).divide(new BigDecimal(365), 2, BigDecimal.ROUND_HALF_UP);
        }
        return saasOrderBillDetailVo.getRealCapital().add(saasOrderBillDetailVo.getNeedPayInterest()).add(saasOrderBillDetailVo.getInterest()).add(lateInterest);
    }

    /**
     * 得到应还金额
     *
     * @param saasOrderVo 订单信息
     * @return
     */
    public BigDecimal getAmount(SaasOrderVo saasOrderVo) {
        BigDecimal lateInterest = BigDecimal.ZERO;
        Integer lateDt = DateUtil.countDay(new Date(), saasOrderVo.getRepaymentDt());
        if (lateDt > 0) {
            lateInterest = saasOrderVo.getRealCapital().multiply(saasOrderVo.getLateInterestRatio()).multiply(new BigDecimal(lateDt)).divide(new BigDecimal(365), 2, BigDecimal.ROUND_HALF_UP);
        }
        return saasOrderVo.getRealCapital().add(saasOrderVo.getTotalInterestFee()).add(lateInterest);
    }

    /**
     * 得到 借款利息
     *
     * @param realCapital        本金
     * @param totalInterestRatio 借款年利率
     * @param createdDt          申请日期
     * @param repaymentDt        应还日期
     * @param isExtend           是否展期
     * @return
     */
    public BigDecimal getInterest(BigDecimal realCapital, BigDecimal totalInterestRatio, Date createdDt, Date repaymentDt, Boolean isExtend) {
        BigDecimal interest = BigDecimal.ZERO;
        Integer lendDt = DateUtil.countDay(repaymentDt, createdDt);
        if (isExtend) {
            lendDt += 1;
        }
        if (lendDt > 0) {
            interest = realCapital.multiply(totalInterestRatio).multiply(new BigDecimal(lendDt)).divide(new BigDecimal(365), 2, BigDecimal.ROUND_HALF_UP);
        }
        return interest;
    }

    public String getInterestRatio(BigDecimal interestRatio) {
        return interestRatio.multiply(new BigDecimal("100")).setScale(0) + "%";
    }

}