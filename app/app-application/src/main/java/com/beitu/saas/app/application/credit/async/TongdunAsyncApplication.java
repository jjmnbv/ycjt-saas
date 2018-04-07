package com.beitu.saas.app.application.credit.async;

import com.beitu.saas.app.application.credit.TongdunReportApplication;
import com.beitu.saas.borrower.client.SaasBorrowerRealInfoService;
import com.beitu.saas.borrower.client.SaasBorrowerService;
import com.beitu.saas.borrower.domain.SaasBorrowerRealInfoVo;
import com.beitu.saas.borrower.domain.SaasBorrowerVo;
import com.beitu.saas.borrower.enums.BorrowerErrorCodeEnum;
import com.beitu.saas.common.consts.RedisKeyConsts;
import com.beitu.saas.common.consts.TimeConsts;
import com.beitu.saas.common.enums.ProductTypeEnum;
import com.beitu.saas.common.enums.RestCodeEnum;
import com.beitu.saas.common.utils.DateUtil;
import com.beitu.saas.credit.client.SaasCreditTongdunDetailService;
import com.beitu.saas.credit.client.SaasCreditTongdunService;
import com.beitu.saas.credit.domain.SaasCreditTongdunDetailVo;
import com.beitu.saas.credit.domain.SaasCreditTongdunVo;
import com.beitu.saas.credit.entity.SaasCreditTongdun;
import com.beitu.saas.credit.entity.SaasCreditTongdunDetail;
import com.beitu.saas.risk.client.service.TripleSubscriptionService;
import com.beitu.saas.risk.domain.enums.triple.TripleServiceTypeEnum;
import com.beitu.saas.risk.domain.platform.tongdun.TripleTongDunReportQueryOutput;
import com.beitu.saas.risk.domain.platform.tongdun.TripleTongdunReportIdInput;
import com.beitu.saas.risk.domain.platform.tongdun.TripleTongdunReportIdOutput;
import com.beitu.saas.risk.domain.platform.tongdun.TripleTongdunReportQueryInput;
import com.beitu.saas.risk.domain.platform.vo.TripleServiceResult;
import com.fqgj.base.services.redis.RedisClient;
import com.fqgj.common.utils.CollectionUtils;
import com.fqgj.common.utils.StringUtils;
import com.fqgj.exception.common.ApplicationException;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author linanjun
 * @create 2018/4/6 下午9:58
 * @description
 */
@Service
public class TongdunAsyncApplication {

    private static final Log LOGGER = LogFactory.getLog(TongdunReportApplication.class);

    @Autowired
    private SaasCreditTongdunDetailService saasCreditTongdunDetailService;

    @Autowired
    private SaasCreditTongdunService saasCreditTongdunService;

    @Autowired
    private SaasBorrowerService saasBorrowerService;

    @Autowired
    private SaasBorrowerRealInfoService saasBorrowerRealInfoService;

    @Autowired
    private TripleSubscriptionService tripleSubscriptionService;

    @Autowired
    private RedisClient redisClient;

    @Transactional(rollbackFor = Exception.class)
    public void generateTongdunReport(String merchantCode, String borrowerCode) {
        //用户一分钟只允许生成一次
        if (!redisClient.setnx(RedisKeyConsts.SAAS_TONGDUN_FORBID_REPEAT_SUBMIT, merchantCode, borrowerCode)) {
            throw new ApplicationException(RestCodeEnum.REPEAT_REQUEST);
        }
        redisClient.expire(RedisKeyConsts.SAAS_TONGDUN_FORBID_REPEAT_SUBMIT, TimeConsts.ONE_MINUTE, borrowerCode);
        SaasBorrowerVo saasBorrowerVo = saasBorrowerService.getByBorrowerCodeAndMerchantCode(borrowerCode, merchantCode);
        if (saasBorrowerVo == null) {
            throw new ApplicationException(RestCodeEnum.USER_NOT_EXIST_ERROR);
        }
        SaasBorrowerRealInfoVo saasBorrowerRealInfoVo = saasBorrowerRealInfoService.getBorrowerRealInfoByBorrowerCode(borrowerCode);
        if (saasBorrowerRealInfoVo == null) {
            throw new ApplicationException(BorrowerErrorCodeEnum.USER_PROFILE_REAL_NAME_FAILURE);
        }
        SaasCreditTongdunVo hasSaasCreditTongdunVo = saasCreditTongdunService.getByMerchantCodeAndBorrowerCode(merchantCode, borrowerCode);
        if (hasSaasCreditTongdunVo != null && hasSaasCreditTongdunVo.getSuccess()) {
            return;
        }
        SaasCreditTongdunVo saasCreditTongdunVo = saasCreditTongdunService.getByMobileAndIdentityCode(saasBorrowerVo.getMobile(), saasBorrowerRealInfoVo.getIdentityCode());
        if (saasCreditTongdunVo != null) {
            Boolean isExceedOneDay = DateUtil.isExceedOneDay(saasCreditTongdunVo.getGmtCreate());
            if (isExceedOneDay != null && !isExceedOneDay) {
                SaasCreditTongdun addCreditTongdun = new SaasCreditTongdun();
                addCreditTongdun.setMerchantCode(merchantCode);
                addCreditTongdun.setBorrowerCode(borrowerCode);
                addCreditTongdun.setReportId(saasCreditTongdunVo.getReportId());
                addCreditTongdun.setSuccess(Boolean.TRUE);
                Long recordId = saasCreditTongdunService.create(addCreditTongdun).getId();
                SaasCreditTongdunDetailVo saasCreditTongdunDetailVo = saasCreditTongdunDetailService.getByRecordId(saasCreditTongdunVo.getSaasCreditTongdunId());
                SaasCreditTongdunDetail saasCreditTongdunDetail = SaasCreditTongdunDetailVo.convertVOToEntity(saasCreditTongdunDetailVo);
                saasCreditTongdunDetail.setRecordId(recordId);
                saasCreditTongdunDetailService.create(saasCreditTongdunDetail);
                return;
            }
        }
        TripleTongdunReportIdInput tripleTongdunReportIdInput = new TripleTongdunReportIdInput();
        tripleTongdunReportIdInput.setIdCard(saasBorrowerRealInfoVo.getIdentityCode()).setMobile(saasBorrowerVo.getMobile()).setRealName(saasBorrowerRealInfoVo.getName());
        TripleServiceResult response = tripleSubscriptionService.getTripleServiceResult(ProductTypeEnum.YCJT, TripleServiceTypeEnum.TONGDUN_REPORT_ID, tripleTongdunReportIdInput);
        String reportId;
        if (response.isSuccess()) {
            reportId = ((TripleTongdunReportIdOutput) response.getData()).getReportId();
            LOGGER.info("获取同盾数据为{},查询数据:{},reportId:{}", response, tripleTongdunReportIdInput, reportId);
        } else {
            LOGGER.warn("******************************TONGDUN_REPORT_ID error {}", response.getMsg());
            throw new ApplicationException(RestCodeEnum.CARRIEE_REBIND.setMsg(response.getMsg()));
        }
        if (StringUtils.isNotEmpty(reportId)) {
            SaasCreditTongdun saasCreditTongdun = new SaasCreditTongdun();
            saasCreditTongdun.setMerchantCode(merchantCode);
            saasCreditTongdun.setBorrowerCode(borrowerCode);
            saasCreditTongdun.setReportId(reportId);
            saasCreditTongdunService.create(saasCreditTongdun);
            generateTongdunDetail(reportId, saasCreditTongdun.getId());
        } else {
            // TODO: 17/6/30 抛出异常
        }
    }

    public void generateTongdunDetail(String reportId, Long recordId) {
        TripleTongdunReportQueryInput tripleTongdunReportQueryInput = new TripleTongdunReportQueryInput();
        tripleTongdunReportQueryInput.setReportId(reportId);
        TripleServiceResult response = tripleSubscriptionService.getTripleServiceResult(ProductTypeEnum.YCJT, TripleServiceTypeEnum.TONGDUN_REPORT_QUERY, tripleTongdunReportQueryInput);
        TripleTongDunReportQueryOutput tongDunVo;
        if (response.isSuccess()) {
            tongDunVo = (TripleTongDunReportQueryOutput) response.getData();
        } else {
            LOGGER.warn("******************************TONGDUN_REPORT_QUERY error reportId:{}", reportId, response.getMsg());
            throw new ApplicationException(RestCodeEnum.CARRIEE_REBIND.setMsg(response.getMsg()));
        }
        if (tongDunVo != null && StringUtils.isNotEmpty(tongDunVo.getFinalScore())) {
            SaasCreditTongdunDetail creditTongdunDetail = transform(tongDunVo, reportId, recordId);
            saasCreditTongdunDetailService.create(creditTongdunDetail);
            saasCreditTongdunService.updateSuccess(recordId);
        } else if (tongDunVo != null) {
            LOGGER.error("reportId:{},查询出的同盾分为null", reportId);
        } else {
            LOGGER.error("reportId:{},查询结果为空", reportId);
        }
    }

    private SaasCreditTongdunDetail transform(TripleTongDunReportQueryOutput tongDunVo, String reportId, Long recordId) {
        SaasCreditTongdunDetail saasCreditTongdunDetail = new SaasCreditTongdunDetail();
        saasCreditTongdunDetail.setReportId(reportId);
        saasCreditTongdunDetail.setFinalScore(tongDunVo.getFinalScore());
        saasCreditTongdunDetail.setFinalDecision(tongDunVo.getFinalDecision());
        if (CollectionUtils.isNotEmpty(tongDunVo.getItemStrList())) {
            saasCreditTongdunDetail.setItemName(String.join("<br>", tongDunVo.getItemStrList()) + "<br>");
        }
        saasCreditTongdunDetail.setReportTime(new Date());
        saasCreditTongdunDetail.setRecordId(recordId);
        return saasCreditTongdunDetail;
    }

}
