package com.beitu.saas.risk.service.platform;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.beitu.saas.common.enums.ProductTypeEnum;
import com.beitu.saas.risk.handler.platform.tongdun.TongDunHandler;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import com.beitu.saas.risk.domain.enums.ErrorCodeEnums;
import com.beitu.saas.risk.domain.enums.triple.TripleServiceTypeEnum;
import com.beitu.saas.risk.domain.exception.BizException;
import com.beitu.saas.risk.domain.platform.TripleServiceBaseInput;
import com.beitu.saas.risk.domain.platform.TripleServiceBaseOutput;
import com.beitu.saas.risk.domain.platform.TripleServiceBaseWatch;
import com.beitu.saas.risk.domain.platform.tongdun.TripleTongDunReportQueryOutput;
import com.beitu.saas.risk.domain.platform.tongdun.TripleTongdunReportIdInput;
import com.beitu.saas.risk.domain.platform.tongdun.TripleTongdunReportIdOutput;
import com.beitu.saas.risk.domain.platform.tongdun.TripleTongdunReportQueryInput;
import com.beitu.saas.risk.helpers.JSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by ykpbean kangping.ying@yuntu-inc.com
 *
 * @description
 * @create 2017-11-15 下午3:02
 */

@Service
public class TongdunSubscriptionService extends TripleServiceBaseWatch {

    private static final Log LOGGER = LogFactory.getLog(TongdunSubscriptionService.class);

    @Autowired
    private TongDunHandler tongDunHandler;

    @Override
    public TripleServiceBaseOutput getResult(ProductTypeEnum productTypeEnum, TripleServiceTypeEnum tripleServiceTypeEnum, TripleServiceBaseInput tripleServiceBaseInput) throws Exception {
        switch (tripleServiceTypeEnum) {
            case TONGDUN_REPORT_ID:
                TripleTongdunReportIdInput tripleTongdunReportIdInput = (TripleTongdunReportIdInput) tripleServiceBaseInput;
                return tongDunSubscription(tripleTongdunReportIdInput.getRealName(), tripleTongdunReportIdInput.getIdCard(), tripleTongdunReportIdInput.getMobile());
            case TONGDUN_REPORT_QUERY:
                TripleTongdunReportQueryInput tripleTongdunReportInput = (TripleTongdunReportQueryInput) tripleServiceBaseInput;
                return tongDunSubscription(tripleTongdunReportInput.getReportId());
        }
        LOGGER.error("三方服务调用异常(未发现服务), productTypeEnum: {}, tripleServiceTypeEnum: {}, tripleServiceBaseInput: {}", productTypeEnum.getName(), tripleServiceTypeEnum.getName(), JSONUtils.obj2jsonNoException(tripleServiceBaseInput));
        throw new BizException(ErrorCodeEnums.PLATFORM_SUBSTRIPTION_ERROR);
    }


    /**
     * 同盾的订阅（内部使用）
     *
     * @return
     */
    public TripleServiceBaseOutput tongDunSubscription(String realName, String idCard, String mobile) {
        try {
            String reportId = getReportId(realName, idCard, mobile);
            TripleTongdunReportIdOutput tripleTongdunOutput = new TripleTongdunReportIdOutput().setReportId(reportId);
            return tripleTongdunOutput;
        } catch (Exception e) {
            LOGGER.info("同盾获取同盾reportId异常，idCard：{}，realName：{},信息为:{}", idCard, realName, e);
            throw new BizException(ErrorCodeEnums.PLATFORM_SUBSTRIPTION_ERROR, e.getMessage());
        }
    }

    public TripleServiceBaseOutput tongDunSubscription(String reportId) {
        try {
            JSONObject tundunMap = tongDunHandler.getTongDunReportMap(reportId);
            Integer finalScore = null;
            String finalDecision = "";
            Date date = new Date();
            List<String> itemStrList = new ArrayList<>();

            if (null == tundunMap) {
                throw new BizException(ErrorCodeEnums.PLATFORM_SUBSTRIPTION_RESULT_ERROR);
            }
            if (null != tundunMap.get("final_score")) {
                finalScore = tundunMap.getInteger("final_score");
            }
            if (null != tundunMap.get("final_decision")) {
                finalDecision = tundunMap.getString("final_decision");
            }
            if (null != tundunMap.get("report_time")) {
                date.setTime(Long.parseLong(tundunMap.get("report_time").toString()));
            }
            List<TripleTongDunReportQueryOutput.Item> itemList = new ArrayList<>();
            if (null != tundunMap.getJSONArray("risk_items")) {
                JSONArray jsonArrayList = tundunMap.getJSONArray("risk_items");
                if (null != jsonArrayList) {
                    for (int i = 0; i < jsonArrayList.size(); i++) {
                        TripleTongDunReportQueryOutput.Item item = new TripleTongDunReportQueryOutput.Item();
                        List<TripleTongDunReportQueryOutput.Info> infoList = new ArrayList<>();

                        String itemName = ((Map) jsonArrayList.get(i)).get("item_name").toString();
                        item.setItemName(itemName);
                        Boolean haveItemInfo = false;

                        if (null != ((Map) jsonArrayList.get(i)).get("item_detail")) {
                            JSONObject daMap = JSONObject.parseObject(((Map) jsonArrayList.get(i)).get("item_detail").toString());
                            if (null != daMap.getString("frequency_detail")) {
                                String frequencyDetail = daMap.getString("frequency_detail");
                                itemStrList.add(itemName + ":" + frequencyDetail);
                                haveItemInfo = true;
                                if (frequencyDetail.contains("[")) {
                                    String[] childrens = frequencyDetail.substring(frequencyDetail.indexOf("[") + 1, frequencyDetail.length() - 1).split(",");
                                    for (String child : childrens) {
                                        String[] c = child.split("：");
                                        TripleTongDunReportQueryOutput.Info info = new TripleTongDunReportQueryOutput.Info();
                                        info.setKey(c[0].substring(1)).setValue(c[1].substring(0, c[1].length() - 1));
                                        infoList.add(info);
                                    }
                                }

                            }
                            if (null != daMap.getString("platform_detail")) {
                                String platformDetail = daMap.getString("platform_detail");
                                itemStrList.add(itemName + ":" + platformDetail);
                                haveItemInfo = true;
                                if (platformDetail.contains("[")) {
                                    String[] childrens = platformDetail.substring(platformDetail.indexOf("[") + 1, platformDetail.length() - 1).split(",");
                                    for (String child : childrens) {
                                        String[] c = child.split(":");
                                        TripleTongDunReportQueryOutput.Info info = new TripleTongDunReportQueryOutput.Info();
                                        info.setKey(c[0].substring(1)).setValue(c[1].substring(0, c[1].length() - 1));
                                        infoList.add(info);
                                    }
                                }
                            }
                        }
                        if (!haveItemInfo) {
                            itemStrList.add(itemName);
                        }
                        item.setInfoList(infoList);
                        itemList.add(item);
                    }
                }
            }
            TripleTongDunReportQueryOutput tripleTongDunReportQueryOutput = new TripleTongDunReportQueryOutput();
            tripleTongDunReportQueryOutput.setFinalDecision(finalDecision)
                    .setFinalScore(finalScore == null ? null : finalScore.toString()).setItemStrList(itemStrList)
                    .setItemList(itemList).setReportId(reportId);
            return tripleTongDunReportQueryOutput;
        } catch (
                Exception e)

        {
            LOGGER.info("同盾查询report异常，reportId:{},信息为：{}", reportId, e);
            throw new BizException(ErrorCodeEnums.PLATFORM_SUBSTRIPTION_ERROR, e.getMessage());
        }

    }

    private String getReportId(String realName, String idCard, String mobile) throws Exception {
        Map<String, String> tongDunMap = new HashMap<String, String>();
        tongDunMap.put("real_name", realName);
        tongDunMap.put("identityNo", idCard);
        tongDunMap.put("mobile", mobile);
        return tongDunHandler.getTongDunReportId(tongDunMap);
    }

}
