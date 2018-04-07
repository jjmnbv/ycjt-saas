package com.beitu.saas.app.application.credit.vo;

import com.beitu.saas.credit.domain.SaasCreditDunningDetailVo;
import com.beitu.saas.credit.enums.CreditDunningDetailTypeEnum;
import com.fqgj.common.utils.CollectionUtils;

import java.io.Serializable;
import java.util.List;

/**
 * Created by linchengyu on 17/8/2.
 */
public class CreditDunningDataVo implements Serializable {

    /**
     * 近30个自然日被催收次数
     */
    private String dunning30Days;

    /**
     * 近30-60个自然日被催收次数
     */
    private String dunning30And60Days;

    /**
     * 催收号码数
     */
    private String dunningTotalNum;

    /**
     * 催收呼入/呼出
     */
    private String dunningInAndOut;

    /**
     * 催收主叫/被叫时长
     */
    private String dunningInAndOutDuration;

    public CreditDunningDataVo(List<SaasCreditDunningDetailVo> saasCreditDunningDetailVoList) {
        if (CollectionUtils.isNotEmpty(saasCreditDunningDetailVoList)) {
            Integer dunning30Days = 0;
            Integer dunning30And60Days = 0;
            Integer dunningTotalNum = 0;
            Integer dunningIn = 0;
            Integer dunningOut = 0;
            Integer dunningInDuration = 0;
            Integer dunningOutDuration = 0;
            for (SaasCreditDunningDetailVo saasCreditDunningDetailVo : saasCreditDunningDetailVoList) {
                if (saasCreditDunningDetailVo.getType().equals(CreditDunningDetailTypeEnum.DAYS_30.getType()) ||
                        saasCreditDunningDetailVo.getType().equals(CreditDunningDetailTypeEnum.DAYS_30_NOT_SURE.getType())) {
                    dunning30Days += saasCreditDunningDetailVo.getCallInTimes();
                }
                if (saasCreditDunningDetailVo.getType().equals(CreditDunningDetailTypeEnum.DAYS_30_60.getType()) ||
                        saasCreditDunningDetailVo.getType().equals(CreditDunningDetailTypeEnum.DAYS_30_60_NOT_SURE.getType())) {
                    dunning30And60Days += saasCreditDunningDetailVo.getCallInTimes();
                }
                if (saasCreditDunningDetailVo.getType().equals(CreditDunningDetailTypeEnum.OVER_VIEW.getType()) ||
                        saasCreditDunningDetailVo.getType().equals(CreditDunningDetailTypeEnum.OVER_VIEW_NOT_SURE.getType())) {
                    dunningTotalNum += saasCreditDunningDetailVo.getCallTelTotalNums();
                    dunningIn += saasCreditDunningDetailVo.getCallInTimes();
                    dunningOut += saasCreditDunningDetailVo.getCallOutTimes();
                    dunningInDuration += saasCreditDunningDetailVo.getCallInDuration();
                    dunningOutDuration += saasCreditDunningDetailVo.getCallOutDuration();
                }
            }
            new CreditDunningDataVo(dunning30Days, dunning30And60Days, dunningTotalNum, dunningIn, dunningOut, dunningInDuration, dunningOutDuration);
        }
    }

    private CreditDunningDataVo(Integer dunning30Days, Integer dunning30And60Days, Integer dunningTotalNum, Integer dunningIn, Integer dunningOut, Integer dunningInDuration, Integer dunningOutDuration) {
        this.dunning30Days = dunning30Days + "次";
        this.dunning30And60Days = dunning30And60Days + "次";
        this.dunningTotalNum = dunningTotalNum + "个";
        this.dunningInAndOut = dunningIn + "/" + dunningOut;
        this.dunningInAndOutDuration = (dunningInDuration / 60 + ((dunningInDuration % 60 > 0) ? 1 : 0)) + "分/" +
                (dunningOutDuration / 60 + ((dunningOutDuration % 60 > 0) ? 1 : 0)) + "分";
    }

    public String getDunning30Days() {
        return dunning30Days;
    }

    public void setDunning30Days(String dunning30Days) {
        this.dunning30Days = dunning30Days;
    }

    public String getDunning30And60Days() {
        return dunning30And60Days;
    }

    public void setDunning30And60Days(String dunning30And60Days) {
        this.dunning30And60Days = dunning30And60Days;
    }

    public String getDunningTotalNum() {
        return dunningTotalNum;
    }

    public void setDunningTotalNum(String dunningTotalNum) {
        this.dunningTotalNum = dunningTotalNum;
    }

    public String getDunningInAndOut() {
        return dunningInAndOut;
    }

    public void setDunningInAndOut(String dunningInAndOut) {
        this.dunningInAndOut = dunningInAndOut;
    }

    public String getDunningInAndOutDuration() {
        return dunningInAndOutDuration;
    }

    public void setDunningInAndOutDuration(String dunningInAndOutDuration) {
        this.dunningInAndOutDuration = dunningInAndOutDuration;
    }

}
