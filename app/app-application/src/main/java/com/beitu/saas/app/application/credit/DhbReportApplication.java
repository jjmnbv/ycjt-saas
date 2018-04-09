package com.beitu.saas.app.application.credit;

import com.beitu.saas.app.application.credit.async.CarrierAsyncApplication;
import com.beitu.saas.app.application.credit.vo.CreditBmpMatchVo;
import com.beitu.saas.borrower.client.SaasBorrowerRealInfoService;
import com.beitu.saas.borrower.domain.SaasBorrowerRealInfoVo;
import com.beitu.saas.borrower.entity.SaasBorrowerRealInfo;
import com.beitu.saas.common.config.ConfigUtil;
import com.beitu.saas.common.handle.carrier.CarrierHandler;
import com.beitu.saas.common.handle.carrier.domain.CarriersPhoneCallVo;
import com.beitu.saas.common.handle.carrier.domain.CarriersVo;
import com.beitu.saas.common.handle.dianhua.domain.BmpTelDataVo;
import com.beitu.saas.common.handle.dianhua.domain.BmpTelResultVo;
import com.beitu.saas.common.handle.dianhua.domain.BmpTelTagDataVo;
import com.beitu.saas.common.handle.oss.OSSService;
import com.beitu.saas.common.utils.DateUtil;
import com.beitu.saas.common.utils.StringUtil;
import com.beitu.saas.credit.client.*;
import com.beitu.saas.credit.domain.*;
import com.beitu.saas.credit.enums.CreditBmpDetailTagEnum;
import com.beitu.saas.credit.enums.CreditCarrierRecordTypeEnum;
import com.beitu.saas.credit.enums.CreditErrorCodeEnum;
import com.beitu.saas.finance.client.SaasCreditHistoryService;
import com.beitu.saas.finance.client.enums.CreditConsumeEnum;
import com.fqgj.common.utils.CollectionUtils;
import com.fqgj.common.utils.JSONUtils;
import com.fqgj.common.utils.MD5;
import com.fqgj.exception.common.ApplicationException;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by linchengyu on 17/8/3.
 */
@Component
public class DhbReportApplication {

    private static final Log LOGGER = LogFactory.getLog(DhbReportApplication.class);

    @Autowired
    private SaasCreditDunningService saasCreditDunningService;

    @Autowired
    private SaasCreditBmpService saasCreditBmpService;

    @Autowired
    private SaasCreditBmpDetailService saasCreditBmpDetailService;

    @Autowired
    private SaasCreditCarrierService saasCreditCarrierService;

    @Autowired
    private SaasCreditCarrierRecordService saasCreditCarrierRecordService;

    @Autowired
    private SaasCreditHistoryService saasCreditHistoryService;

    @Autowired
    private CarrierAsyncApplication carrierAsyncApplication;

    @Autowired
    private CarrierHandler carrierHandler;

    @Autowired
    private OSSService ossService;

    @Autowired
    private ConfigUtil configUtil;

    @Autowired
    private SaasBorrowerRealInfoService saasBorrowerRealInfoService;

    public void handleBmpData(String reqStr) {
        BmpTelDataVo dataVo = convertEffectiveReqStr(reqStr);
        String sid = dataVo.getSid();
        SaasCreditDunningVo saasCreditDunningVo = saasCreditDunningService.getBySid(sid);
        if (saasCreditDunningVo == null) {
            LOGGER.error("电话邦金融标签异步通知数据处理异常:CreditDunning不存在");
        }
        String merchantCode = saasCreditDunningVo.getMerchantCode();
        String borrowerCode = saasCreditDunningVo.getBorrowerCode();
        String url = uploadBmpData(borrowerCode, reqStr);
        if (dataVo == null) {
            LOGGER.error("电话邦金融标签异步通知数据处理异常");
            return;
        }
        SaasCreditBmpVo saasCreditBmpVo = new SaasCreditBmpVo();
        saasCreditBmpVo.setMerchantCode(merchantCode);
        saasCreditBmpVo.setBorrowerCode(borrowerCode);
        saasCreditBmpVo.setSid(sid);
        saasCreditBmpVo.setUrl(url);
        saasCreditBmpVo.setSuccess(Boolean.FALSE);
        Long recordId = saasCreditBmpService.addSaasCreditBmp(saasCreditBmpVo).getId();

        SaasBorrowerRealInfoVo saasBorrowerRealInfoVo = saasBorrowerRealInfoService.getBorrowerRealInfoByBorrowerCode(borrowerCode);
        String name = borrowerCode;
        if (saasBorrowerRealInfoVo != null) {
            name = saasBorrowerRealInfoVo.getName();
        }
        saasCreditHistoryService.addExpenditureCreditHistory(merchantCode, name, CreditConsumeEnum.RISK_CARRIER);

        if (recordId == null) {
            throw new ApplicationException(CreditErrorCodeEnum.CREDIT_BMP_GENERATE_ERROR, "credit_bmp插入失败");
        }

        List<SaasCreditBmpDetailVo> list = null;
        if (CollectionUtils.isNotEmpty(dataVo.getTags())) {
            list = convertBmpData(dataVo.getTags(), recordId);
        }
        saasCreditBmpDetailService.batchAddSaasCreditBmpDetailVo(list);
        saasCreditBmpService.updateSuccess(recordId);

        List<SaasCreditBmpDetailVo> saasCreditBmpDetailVoList = saasCreditBmpDetailService.listByRecordId(recordId);
        Map<String, String> merchantAddressMap = new HashMap(saasCreditBmpDetailVoList.size());
        if (CollectionUtils.isNotEmpty(saasCreditBmpDetailVoList)) {
            for (SaasCreditBmpDetailVo saasCreditBmpDetailVo : saasCreditBmpDetailVoList) {
                if (StringUtils.isNotEmpty(saasCreditBmpDetailVo.getTelnum()) && StringUtils.isNotEmpty(saasCreditBmpDetailVo.getName())) {
                    merchantAddressMap.put(saasCreditBmpDetailVo.getTelnum(), saasCreditBmpDetailVo.getName());
                }
            }
        }

        SaasCreditCarrierVo saasCreditCarrierVo = saasCreditCarrierService.getByMerchantCodeAndBorrowerCode(merchantCode, borrowerCode);
        CarriersVo carriersVo = carrierHandler.getCarriersVo(saasCreditCarrierVo.getUrl());
        if (carriersVo != null) {
            List<SaasCreditCarrierRecordVo> addSaasCreditCarrierRecordVoList = new ArrayList<>(list.size());
            if (CollectionUtils.isNotEmpty(carriersVo.getCarriersCallVoList())) {
                List<CreditBmpMatchVo> matchVoList = getMatchList(list);
                for (CreditBmpMatchVo matchVo : matchVoList) {
                    for (CarriersPhoneCallVo callVo : carriersVo.getCarriersCallVoList()) {
                        if (matchVo.getNumberList().contains(callVo.getPeernumber())) {
                            if (StringUtils.isNotBlank(callVo.getCallTime())) {
                                if (callVo.getDialtype().contains("主叫")) {
                                    matchVo.setCallingTime(matchVo.getCallingTime() + 1);
                                    matchVo.setCallingDuration(matchVo.getCallingDuration() + Integer.valueOf(callVo.getCallTime()));
                                }
                                if (callVo.getDialtype().contains("被叫")) {
                                    matchVo.setCalledTime(matchVo.getCalledTime() + 1);
                                    matchVo.setCalledDuration(matchVo.getCalledDuration() + Integer.valueOf(callVo.getCallTime()));
                                }
                                matchVo.setTotalDuration(matchVo.getTotalDuration() + Integer.valueOf(callVo.getCallTime()));
                            }
                            if (StringUtils.isNotBlank(callVo.getCreatetime())) {
                                Date time = DateUtil.getDate(callVo.getCreatetime(), "yyyy-MM-dd HH:mm:ss");
                                if (matchVo.getLastTimeCall() == null || time.compareTo(matchVo.getLastTimeCall()) == 1) {
                                    matchVo.setLastTimeCall(DateUtil.getDate(callVo.getCreatetime(), "yyyy-MM-dd HH:mm:ss"));
                                }
                            }
                        }
                    }
                }

                for (CreditBmpMatchVo matchVo : matchVoList) {
                    CarriersPhoneCallVo phoneCall = new CarriersPhoneCallVo();
                    phoneCall.setCalling(matchVo.getCallingTime());
                    phoneCall.setCalled(matchVo.getCalledTime());
                    phoneCall.setCallingTime(matchVo.getCallingDuration() + "");
                    phoneCall.setCalledTime(matchVo.getCalledDuration() + "");
                    phoneCall.setCallTime(matchVo.getTotalDuration() + "");

                    SaasCreditCarrierRecordVo saasCreditCarrierRecordVo = SaasCreditCarrierRecordVo.getSaasCreditCarrierRecordVo(phoneCall, CreditCarrierRecordTypeEnum.FINANCIAL, saasCreditCarrierVo.getSaasCreditCarrierId());
                    if (StringUtils.isNotEmpty(matchVo.getTypeName())) {
                        saasCreditCarrierRecordVo.setMerchant(matchVo.getTypeName());
                    }
                    saasCreditCarrierRecordVo.setLocation("无");
                    if (matchVo.getLastTimeCall() != null) {
                        saasCreditCarrierRecordVo.setLocation(DateUtil.getDate(matchVo.getLastTimeCall(), "yyyy-MM-dd HH:mm:ss"));
                    }
                    addSaasCreditCarrierRecordVoList.add(saasCreditCarrierRecordVo);
                }
            }
            if (CollectionUtils.isNotEmpty(merchantAddressMap.keySet()) && CollectionUtils.isNotEmpty(carriersVo.getCarriersPhoneCallVoList())) {
                for (CarriersPhoneCallVo carrierCallVo : carriersVo.getCarriersPhoneCallVoList()) {
                    String phoneNumber = carrierCallVo.getPeernumber();
                    String merchantName = merchantAddressMap.get(phoneNumber);
                    if (!StringUtils.isBlank(phoneNumber) && StringUtils.isNotBlank(merchantName)) {
                        carrierCallVo.setPeercarrier(merchantName);
                        SaasCreditCarrierRecordVo saasCreditCarrierRecordVo = SaasCreditCarrierRecordVo.getSaasCreditCarrierRecordVo(carrierCallVo, CreditCarrierRecordTypeEnum.MERCHANT, saasCreditCarrierVo.getSaasCreditCarrierId());
                        saasCreditCarrierRecordVo.setLocation(carrierAsyncApplication.getLocationByPhone(phoneNumber));
                        addSaasCreditCarrierRecordVoList.add(saasCreditCarrierRecordVo);
                    }
                }
            }
            if (CollectionUtils.isNotEmpty(addSaasCreditCarrierRecordVoList)) {
                saasCreditCarrierRecordService.batchAddSaasCreditCarrierRecord(addSaasCreditCarrierRecordVoList);
            }
        }
    }

    public List<CreditBmpMatchVo> getMatchList(List<SaasCreditBmpDetailVo> creditBmp) {
        List<CreditBmpDetailTagEnum> concernTags = CreditBmpDetailTagEnum.getConcernTags();
        Map<Integer, String> concernTagsMap = new HashMap<>();
        for (CreditBmpDetailTagEnum tagEnum : concernTags) {
            concernTagsMap.put(tagEnum.getType(), tagEnum.getDesc());
        }
        Map<Integer, CreditBmpMatchVo> matchMap = new HashMap<>();
        if (CollectionUtils.isNotEmpty(creditBmp)) {
            for (SaasCreditBmpDetailVo saasCreditBmpDetailVo : creditBmp) {
                if (StringUtils.isNotEmpty(saasCreditBmpDetailVo.getItagIds())) {
                    List<Integer> tags = getTags(saasCreditBmpDetailVo.getItagIds().split(","));
                    for (Integer tagId : tags) {
                        if (concernTagsMap.containsKey(tagId)) {
                            String typeName = concernTagsMap.get(tagId);
                            CreditBmpMatchVo matchVo;
                            if (!matchMap.containsKey(tagId)) {
                                matchVo = new CreditBmpMatchVo(typeName);
                                matchMap.put(tagId, matchVo);
                            } else {
                                matchVo = matchMap.get(tagId);
                            }
                            matchVo.getNumberList().add(saasCreditBmpDetailVo.getTelnum());
                        }
                    }
                }
            }
        }

        List<CreditBmpMatchVo> list = new ArrayList<>();
        for (CreditBmpMatchVo matchVo : matchMap.values()) {
            list.add(matchVo);
        }
        return list;
    }

    private List<Integer> getTags(String[] tags) {
        List<Integer> tagList = new ArrayList<>();
        if (tags.length == 0) {
            return null;
        }
        for (String tag : tags) {
            tagList.add(Integer.valueOf(tag));
        }
        return tagList;
    }

    private String uploadBmpData(String borrowerCode, String content) {
        String userIdTime = borrowerCode + "_" + System.currentTimeMillis();
        String filePath = "phoneData/";
        if (configUtil.isServerTest()) {
            filePath += "test/";
        }
        filePath += (userIdTime + "_" + MD5.md5(userIdTime) + ".json");
        return ossService.uploadFile(filePath, content);
    }

    private BmpTelDataVo convertEffectiveReqStr(String reqStr) {
        BmpTelResultVo resultVo = null;
        try {
            resultVo = JSONUtils.json2pojoAndOffUnknownField(reqStr, BmpTelResultVo.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (resultVo == null || StringUtils.isEmpty(resultVo.getSid()) || resultVo.getTags() == null) {
            return null;
        }
        if (CollectionUtils.isEmpty(resultVo.getTags().keySet())) {
            return null;
        }
        List<BmpTelTagDataVo> list = new ArrayList<>();
        for (String key : resultVo.getTags().keySet()) {
            Object obj = resultVo.getTags().get(key);
            if (obj == null) {
                continue;
            }
            BmpTelTagDataVo tagDataVo = null;
            try {
                tagDataVo = JSONUtils.json2pojoAndOffUnknownField(JSONUtils.obj2json(obj), BmpTelTagDataVo.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (tagDataVo == null) {
                continue;
            }
            if (StringUtils.isNotEmpty(tagDataVo.getId()) || StringUtils.isNotEmpty(tagDataVo.getName()) ||
                    CollectionUtils.isNotEmpty(tagDataVo.getCatnames()) || CollectionUtils.isNotEmpty(tagDataVo.getItagIds()) ||
                    tagDataVo.getFlag() != null) {
                tagDataVo.setTelnum(key);
                list.add(tagDataVo);
            }
        }
        return new BmpTelDataVo(resultVo.getSid(), list);
    }

    private List<SaasCreditBmpDetailVo> convertBmpData(List<BmpTelTagDataVo> bmpTelTagDataVos, Long recordId) {
        List<SaasCreditBmpDetailVo> creditBmpDetailVos = new ArrayList<>(bmpTelTagDataVos.size());
        if (CollectionUtils.isNotEmpty(bmpTelTagDataVos)) {
            for (BmpTelTagDataVo bmpTelTagDataVo : bmpTelTagDataVos) {
                SaasCreditBmpDetailVo detailVo = new SaasCreditBmpDetailVo();
                detailVo.setRecordId(recordId);
                detailVo.setDhbId(bmpTelTagDataVo.getId());
                detailVo.setName(bmpTelTagDataVo.getName());
                detailVo.setTeldesc(bmpTelTagDataVo.getTeldesc());
                detailVo.setTeltype(bmpTelTagDataVo.getTeltype());
                detailVo.setTelnum(bmpTelTagDataVo.getTelnum());
                detailVo.setTelrank(bmpTelTagDataVo.getTelrank());
                detailVo.setCountry(bmpTelTagDataVo.getCountry());
                detailVo.setCityId(bmpTelTagDataVo.getCityid());
                detailVo.setLogo(bmpTelTagDataVo.getLogo());
                detailVo.setSlogan(bmpTelTagDataVo.getSlogan());
                detailVo.setImage(bmpTelTagDataVo.getImage());
                detailVo.setAddr(bmpTelTagDataVo.getAddr());
                detailVo.setWeb(bmpTelTagDataVo.getWeb());
                detailVo.setUrl(bmpTelTagDataVo.getUrl());
                if (bmpTelTagDataVo.getFlag() != null) {
                    detailVo.setFlagType(bmpTelTagDataVo.getFlag().getType());
                    detailVo.setFlagNum(bmpTelTagDataVo.getFlag().getNum());
                    detailVo.setFlagFid(bmpTelTagDataVo.getFlag().getFid());
                }
                if (CollectionUtils.isNotEmpty(bmpTelTagDataVo.getCatnames())) {
                    detailVo.setCatnames(StringUtil.listToString(bmpTelTagDataVo.getCatnames(), ','));
                }
                if (CollectionUtils.isNotEmpty(bmpTelTagDataVo.getItagIds())) {
                    detailVo.setItagIds(StringUtil.listToString(bmpTelTagDataVo.getItagIds(), ','));
                }
                creditBmpDetailVos.add(detailVo);
            }
        }
        return creditBmpDetailVos;
    }

}
