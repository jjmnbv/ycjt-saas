package com.beitu.saas.common.utils;

import java.math.BigDecimal;
import java.util.Date;

public class CalculateUtil {

    /**
     * 计算每期还款金额
     * @param amount
     * @return
     */
    public static BigDecimal getDurationAmount(BigDecimal amount, Integer duration) {
        return amount.divide(BigDecimal.valueOf(duration), 2, BigDecimal.ROUND_HALF_UP);
    }


    /**
     * 计算每期应还款金额
     * @param amount
     * @return
     */
    public static BigDecimal getAmount(BigDecimal amount, BigDecimal interest) {
        return amount.add(interest);
    }


    /**
     * 计算最后一期还款金额
     * @param amount
     * @param durationAmount
     * @param duration
     * @return
     */
    public static BigDecimal getLastDurationAmount(BigDecimal amount,BigDecimal durationAmount,Integer duration){
        return amount.subtract(durationAmount.multiply(BigDecimal.valueOf(duration))).add(durationAmount);
    }
    public static BigDecimal getTotalInterestFee(BigDecimal realCapital,BigDecimal totalInterestRatio,int deadline){
        return getTotalInterestFee(realCapital,totalInterestRatio,deadline,null,null);
    }
    /**
     * 复利计算
     * 计算利息：利息=金额*年利率*（期限）/365；计算结果取小数点后2位。
     * @param realCapital
     * @param totalInterestRatio
     * @param deadline
     * @return
     */
    public static BigDecimal getTotalInterestFee(BigDecimal realCapital,BigDecimal totalInterestRatio,int deadline,Integer subDate,BigDecimal interestFee){

        BigDecimal totalInterestFee = new BigDecimal(0);
        int year = deadline/365;
        int date = deadline % 365;
        if (year>0){
            for (int i =0 ; i<year;i++){
                if (subDate!=null&&i==0&&interestFee!=null){
                    BigDecimal v1 = DecimalUtils.mul(realCapital, totalInterestRatio);
                    BigDecimal v2 = DecimalUtils.mul(v1, BigDecimal.valueOf((365-subDate)));
                    BigDecimal fee = DecimalUtils.div(DecimalUtils.round(v2), 365, 2);
                    totalInterestFee = DecimalUtils.add(totalInterestFee, fee);
                    totalInterestFee =DecimalUtils.add(totalInterestFee,interestFee);
                    continue;
                }
                BigDecimal capital = DecimalUtils.add(realCapital, totalInterestFee);
                BigDecimal fee = DecimalUtils.mul(capital, totalInterestRatio);
                totalInterestFee = DecimalUtils.add(totalInterestFee, fee);
            }
            BigDecimal capital = DecimalUtils.add(realCapital, totalInterestFee);
            BigDecimal v1 = DecimalUtils.mul(capital, totalInterestRatio);
            BigDecimal v2 = DecimalUtils.mul(v1, BigDecimal.valueOf(subDate==null?date:date+subDate));
            BigDecimal fee = DecimalUtils.div(DecimalUtils.round(v2), 365, 2);
            totalInterestFee = DecimalUtils.add(totalInterestFee, fee);
        }else {
            BigDecimal v1 = DecimalUtils.mul(realCapital, totalInterestRatio);
            BigDecimal v2 = DecimalUtils.mul(v1, BigDecimal.valueOf(deadline));
            totalInterestFee = DecimalUtils.div(DecimalUtils.round(v2), 365, 2);
        }
        return totalInterestFee;
    }

    /**
     * 展期利息，非复利计算
     * 计算利息：利息=金额*年利率*（期限）/365；计算结果取小数点后2位。
     * @param realCapital
     * @param totalInterestRatio
     * @param deadline
     * @return
     */
    public static BigDecimal getExtendTotalInterestFee(BigDecimal realCapital,BigDecimal totalInterestRatio,int deadline) {
        BigDecimal v1 = DecimalUtils.mul(realCapital, totalInterestRatio);
        BigDecimal v2 = DecimalUtils.mul(v1, BigDecimal.valueOf(deadline));
        return DecimalUtils.div(DecimalUtils.round(v2), 365, 2);
    }
    /**
     *计算实际应还金额，如果逾期，加上逾期费
     * @param realCapital
     * @param amount
     * @param lateFeeRatio
     * @param
     * @return
     */
    public static BigDecimal getTotalAmount(BigDecimal realCapital, BigDecimal amount, BigDecimal lateFeeRatio, Date repaymentDt,Date createdDt,BigDecimal totalInterestRatio){

        //逾期天数
        int deadline = DateUtil.countDay(new Date(),repaymentDt);
        if(deadline>0){
            //借款总天数
            int lendDate = DateUtil.countDay(repaymentDt, createdDt);
            //判断是否超过一年，一年后利息计算的本金是上一年的本息
            int year = lendDate / 365;
            int date = lendDate % 365;
            BigDecimal totalInterestFee = new BigDecimal(0);
            if (year>0){
                //上几年的总共利息，做为逾期的本金
                totalInterestFee = CalculateUtil.getTotalInterestFee(realCapital, totalInterestRatio, 365 * year);
            }
            BigDecimal interestFee=  DecimalUtils.sub(DecimalUtils.round(amount),DecimalUtils.round(realCapital));
            BigDecimal lateFee = CalculateUtil.getTotalInterestFee(DecimalUtils.add(realCapital, totalInterestFee)
                    , lateFeeRatio, deadline,date,interestFee);
            //  BigDecimal totalInterestFee = getTotalInterestFee(realCapital, lateFeeRatio, deadline);
            if ((deadline/365)>0){
                return DecimalUtils.add(realCapital,lateFee);
            }else {
                return DecimalUtils.add(amount,lateFee);
            }
        }
        return  amount;
    }

    /**
     * 计算展期应还总金额 主订单应还金额+主订单逾期费+展期订单利息+展期订单逾期费
     * @param realCapital 借款金额
     * @param amount 主订单应还金额
     * @param lateFeeRatio 展期订单逾期利率
     * @param repaymentDt  展期应还日
     * @param lateFee 主订单逾期费
     * @param interest 展期订单利息
     * @param paid 是否已还清
     * @param expendLateFee 展期订单逾期费
     * @return
     */
    public static BigDecimal getExpendTotalAmount(BigDecimal realCapital, BigDecimal amount, BigDecimal lateFeeRatio, Date repaymentDt,BigDecimal lateFee,BigDecimal interest,Boolean paid,BigDecimal expendLateFee){
        if (paid){
            return amount.add(lateFee).add(interest).add(expendLateFee);
        }
        int deadline = DateUtil.countDay(new Date(),repaymentDt);
        BigDecimal totalInterestFee = BigDecimal.ZERO;
        if(deadline>0){
            totalInterestFee = CalculateUtil.getExtendTotalInterestFee(realCapital, lateFeeRatio, deadline);
        }
        return amount.add(lateFee).add(interest).add(totalInterestFee);
    }

    /**
     * 判断解密金额是否合法
     * @param merchantCode
     * @param encrypt 加密字符串
     * @return
     */
    public static Boolean isIllegalAmount(String merchantCode,String encrypt) {
        String decrypt = CryptUtil.Decrypt(encrypt);
        if (!decrypt.contains("_")){
            return Boolean.FALSE;
        }
        String decryptUserId = decrypt.substring(decrypt.indexOf("_") + 1,decrypt.length());
        if (decryptUserId.equals(merchantCode)){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * 得到 未还金额
     * @param realCapital 本金
     * @param totalFee 总利息
     * @param returnCapital 已还金额
     * @return
     */
    public static BigDecimal getUncomeCapital(BigDecimal realCapital, BigDecimal totalFee, BigDecimal returnCapital){
        BigDecimal needReturnCapital = realCapital.add(totalFee);
        return needReturnCapital.subtract(returnCapital);
    }

    public static BigDecimal getBorrowMinAmount(BigDecimal realCapital){
        if(realCapital.compareTo(BigDecimal.valueOf(100))==-1){
            return realCapital;
        }else {
           if (realCapital.multiply(BigDecimal.valueOf(0.1)).compareTo(BigDecimal.valueOf(100))==-1){
               return BigDecimal.valueOf(100);
           }else {
               return realCapital.multiply(BigDecimal.valueOf(0.1));
           }
        }
    }
    public static void main(String[] args){
        //System.out.println(CalculateUtil.getDurationAmount(BigDecimal.valueOf(1000),3));
       // System.out.println(CalculateUtil.getLastDurationAmount(BigDecimal.valueOf(1000),BigDecimal.valueOf(333.33),3));
       // System.out.println(CalculateUtil.getAmount(BigDecimal.valueOf(333.33),BigDecimal.valueOf(25)));
        System.out.println(CalculateUtil.getTotalInterestFee(BigDecimal.valueOf(1000),BigDecimal.valueOf(0.1),165));

    }

}
