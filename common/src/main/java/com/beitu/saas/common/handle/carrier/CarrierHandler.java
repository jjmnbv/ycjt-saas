package com.beitu.saas.common.handle.carrier;

import com.alibaba.fastjson.JSONObject;
import com.beitu.saas.common.handle.carrier.domain.CarriersPhoneCallVo;
import com.beitu.saas.common.handle.carrier.domain.CarriersVo;
import com.beitu.saas.common.handle.carrier.enums.CarrierTypeEnum;
import com.beitu.saas.common.handle.oss.OSSHandler;
import com.fqgj.common.utils.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import java.util.List;
import java.util.Map;

//运营商接入
@Component
public class CarrierHandler {

    private static final Logger logger = LoggerFactory.getLogger(CarrierHandler.class);

    @Autowired
    private OSSHandler ossHandler;

    @Autowired
    private ServletContext servletContext;

    public CarriersVo getCarriersVo(String carrierUrl) {
        JSONObject jsonObject = getCarrierJSONObject4OSSUrl(carrierUrl);
        return generateCarrierVo(jsonObject, carrierUrl);
    }

    public CarriersVo getCarrierVoFromContent(String content, String url) {
        JSONObject jsonObject = getCarrierJSONObject4Content(content);
        return generateCarrierVo(jsonObject, url);
    }

    private CarriersVo generateCarrierVo(JSONObject jsonObject, String carrierUrl) {
        CarriersVo carriers = new CarriersVo();
        if (jsonObject != null) {
            CarrierTypeEnum carrierTypeEnum = getCarrierTypeEnum4JSONObjectAndOSSUrl(jsonObject, carrierUrl);
            if (carrierTypeEnum == null) {
                return null;
            }
            if (carrierTypeEnum.equals(CarrierTypeEnum.TONGDUN_CARRIER)) {
                jsonObject = jsonObject.getJSONObject("data");
            }
            if (carrierTypeEnum.equals(CarrierTypeEnum.RONG_THREE_CARRIER)) {
                if (jsonObject.getJSONObject("data") != null) {
                    if (CollectionUtils.isNotEmpty(jsonObject.getJSONObject("data").getJSONArray("data_list"))) {
                        jsonObject = jsonObject.getJSONObject("data").getJSONArray("data_list").getJSONObject(0);
                    }
                }
            }
            if (carrierTypeEnum != null) {
                carriers = this.getCarriers(jsonObject, carriers, carrierTypeEnum);//51&tongdun运营商数据
            }
        }
        return carriers;
    }


    /**
     * 根据 ossUrl 得到 运营商JSON数据
     *
     * @param ossUrl
     * @return 运营商JSON数据
     */
    private JSONObject getCarrierJSONObject4OSSUrl(String ossUrl) {
        if (StringUtils.isNotEmpty(ossUrl)) {
            String bucketName = "ycjt";
            String carriersContent = ossHandler.getFileContent(bucketName, ossUrl);//运营商数据
            return getCarrierJSONObject4Content(carriersContent);
        }
        return null;
    }

    private JSONObject getCarrierJSONObject4Content(String content) {
        if (content.contains("\"phoneAccountDetail\":,")) {
            content = content.replace("\"phoneAccountDetail\":,", "");
        }
        if (StringUtils.isEmpty(content)) {
            return null;
        }
        return JSONObject.parseObject(content);
    }

    /**
     * 根据 运营商JSON数据 和 ossUrl 得到 运营商类型
     *
     * @param jsonObject 运营商JSON数据
     * @param ossUrl
     * @return 运营商类型
     */
    private CarrierTypeEnum getCarrierTypeEnum4JSONObjectAndOSSUrl(JSONObject jsonObject, String ossUrl) {
        CarrierTypeEnum carrierTypeEnum = CarrierTypeEnum.FIVE_ONE_OPTIMIZE_CARRIER;
        if (ossUrl.contains("tongDunCarrier")) {
            String code = jsonObject.get("code").toString();
            if (!code.equals("0")) {
                return null;
            }
            carrierTypeEnum = CarrierTypeEnum.TONGDUN_CARRIER;
        }
        if (ossUrl.contains("tianjiCarrier")) {
            carrierTypeEnum = CarrierTypeEnum.RONG_THREE_CARRIER;
        }
        return carrierTypeEnum;
    }

    /**
     * 解析 运营商信息
     *
     * @param jsonObject      运营商JSON数据
     * @param carriers        运营商信息接受容器
     * @param carrierTypeEnum 运营商类型
     * @return
     */
    private CarriersVo getCarriers(JSONObject jsonObject, CarriersVo carriers, CarrierTypeEnum carrierTypeEnum) {
        // 运营商基本信息
        carriers = CarrierData.getBaseInfo(jsonObject, carriers, carrierTypeEnum);
        // 月账单
        carriers = CarrierData.getBillInfo(jsonObject, carriers, carrierTypeEnum);
        // 运营商记录数
        carriers = CarrierData.getPhoneRecordInfo(jsonObject, carriers, carrierTypeEnum);
        // 得到 通话统计信息
        List<CarriersPhoneCallVo> carriersPhoneCallVoList = carriers.getCarriersPhoneCallVoList();

        //风控相关数据的统计
        Map<String, String> phoneBookMap = null;
        if (servletContext != null) {
            phoneBookMap = (Map<String, String>) servletContext.getAttribute("phoneBookMap");
        }
        carriers = CarrierData.getFullInfo(carriersPhoneCallVoList, phoneBookMap, carriers);

        return carriers;
    }

}
