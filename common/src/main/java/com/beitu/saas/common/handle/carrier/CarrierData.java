package com.beitu.saas.common.handle.carrier;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.beitu.saas.common.consts.TimeConsts;
import com.beitu.saas.common.handle.carrier.domain.CarriersBillVo;
import com.beitu.saas.common.handle.carrier.domain.CarriersPhoneCallMonthVo;
import com.beitu.saas.common.handle.carrier.domain.CarriersPhoneCallVo;
import com.beitu.saas.common.handle.carrier.domain.CarriersVo;
import com.beitu.saas.common.handle.carrier.enums.CarrierTypeEnum;
import com.beitu.saas.common.utils.MobileUtil;
import com.beitu.saas.common.utils.excel.model.ExportCarrierInfo;
import com.fqgj.common.utils.CollectionUtils;
import com.fqgj.common.utils.DateUtil;
import com.fqgj.common.utils.PhoneUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author 柳朋朋
 * @Create 2017-04-24 9:08
 */
public class CarrierData implements Serializable {

    /**
     * 得到 通话记录
     *
     * @param jsonObject      运营商JSON数据
     * @param carrierTypeEnum 运营商类型
     * @return 所有的通话记录
     */
    public static List<ExportCarrierInfo> getCallRecords(JSONObject jsonObject, CarrierTypeEnum carrierTypeEnum) {
        switch (carrierTypeEnum) {
            case FIVE_ONE_OPTIMIZE_CARRIER:
                JSONObject records = jsonObject.getJSONObject("phoneCallRecords");
                if (records == null) {
                    return null;
                }
                JSONArray recordArray = records.getJSONArray("records");
                int size = recordArray.size();
                List<ExportCarrierInfo> results = new ArrayList<>(size);
                for (int i = 0; i < size; i++) {
                    JSONObject record = recordArray.getJSONObject(i);
                    String peernumber = getString4JSONObject(record, "peernumber");
                    if (StringUtils.isNotEmpty(peernumber)) {
                        CarriersPhoneCallVo carriersPhoneCallVo = getCarriersPhoneCallVo4FiveOneCarrierCallJSON(record, peernumber);
                        results.add(getExportCarrierInfo4CarriersPhoneCallVo(carriersPhoneCallVo));
                    }
                }
                return results;
            case TONGDUN_CARRIER:
                JSONObject taskData = jsonObject.getJSONObject("task_data");
                if (taskData == null) {
                    return null;
                }
                JSONArray callInfoList = taskData.getJSONArray("call_info");
                List<ExportCarrierInfo> tongDunResults = new ArrayList<>();
                if (null != callInfoList) {
                    for (int i = 0; i < callInfoList.size(); i++) {
                        JSONObject callInfo = (JSONObject) callInfoList.get(i);
                        CarriersPhoneCallMonthVo carriersCallMonth = new CarriersPhoneCallMonthVo();

                        if (callInfo.containsKey("call_cycle")) {
                            String time = callInfo.getString("call_cycle");
                            carriersCallMonth.setMonth(time);
                            JSONArray callRecordList = callInfo.getJSONArray("call_record");
                            carriersCallMonth.setTotal(callRecordList.size());
                            for (int j = 0; j < callRecordList.size(); j++) {
                                JSONObject callRecord = callRecordList.getJSONObject(j);
                                String peernumber = callRecord.containsKey("call_other_number") ? callRecord.getString("call_other_number") : "";
                                if (StringUtils.isNotEmpty(peernumber)) {
                                    CarriersPhoneCallVo carriersCallVo = getCarriersPhoneCallVo4TongDunCarrierCallJSON(callRecord, peernumber);
                                    tongDunResults.add(getExportCarrierInfo4CarriersPhoneCallVo(carriersCallVo));
                                }
                            }
                        }
                    }
                }
                return tongDunResults;
            case RONG_THREE_CARRIER:
                JSONArray telList = jsonObject.getJSONArray("tel");
                if (null != telList) {
                    List<ExportCarrierInfo> rongThreeResults = new ArrayList<>(telList.size() * 100);
                    for (int i = 0; i < telList.size(); i++) {
                        JSONObject callInfo = (JSONObject) telList.get(i);
                        JSONArray teldataList = callInfo.getJSONArray("teldata");
                        if (null != teldataList) {
                            //统计号码主叫被叫情况
                            for (int j = teldataList.size() - 1; j >= 0; j--) {
                                JSONObject teldata = JSONObject.parseObject(teldataList.getString(j));
                                String peernumber = teldata.containsKey("receive_phone") ? MobileUtil.parseMobile(teldata.getString("receive_phone")) : "";
                                if (StringUtils.isNotEmpty(peernumber)) {
                                    CarriersPhoneCallVo carriersCallVo = getCarriersPhoneCallVo4RongThreeCarrierCallJSON(teldata, peernumber);
                                    rongThreeResults.add(getExportCarrierInfo4CarriersPhoneCallVo(carriersCallVo));
                                }
                            }
                        }
                    }
                    return rongThreeResults;
                }
                return null;
            default:
                return null;
        }
    }

    private static ExportCarrierInfo getExportCarrierInfo4CarriersPhoneCallVo(CarriersPhoneCallVo carriersPhoneCallVo) {
        ExportCarrierInfo exportCarrierInfo = new ExportCarrierInfo();
        exportCarrierInfo.setMobile(carriersPhoneCallVo.getPeernumber());
        exportCarrierInfo.setCallTime(carriersPhoneCallVo.getCallTime());
        exportCarrierInfo.setCallingDate(carriersPhoneCallVo.getCreatetime());
        exportCarrierInfo.setDialtype(carriersPhoneCallVo.getDialtype());
        return exportCarrierInfo;
    }

    public static CarriersVo getBaseInfo(JSONObject jsonObject, CarriersVo carriers, CarrierTypeEnum carrierTypeEnum) {
        switch (carrierTypeEnum) {
            case FIVE_ONE_CARRIER:
                if (jsonObject.containsKey("peopleDetail")) {//身份认证
                    JSONObject peopleDetailObj = jsonObject.getJSONObject("peopleDetail");
                    if (peopleDetailObj.containsKey("peopleList")) {
                        JSONArray peopleDetailArrayList = peopleDetailObj.getJSONArray("peopleList");
                        if (null != peopleDetailArrayList) {
                            carriers.setRealName(null == ((Map) peopleDetailArrayList.get(0)).get("realname") ? "" : ((Map) peopleDetailArrayList.get(0)).get("realname").toString());//姓名
                            carriers.setIdNumber(null == ((Map) peopleDetailArrayList.get(0)).get("idcardnumber") ? "" : ((Map) peopleDetailArrayList.get(0)).get("idcardnumber").toString());//身份证
                        }
                    }
                }
                if (jsonObject.containsKey("phoneAccountDetail")) {
                    JSONObject phoneAccountObj = jsonObject.getJSONObject("phoneAccountDetail");
                    if (phoneAccountObj.containsKey("phonenumber")) {//电话
                        carriers.setMobile(phoneAccountObj.getString("phonenumber"));
                    }
                    if (phoneAccountObj.containsKey("availablebalance")) {//余额
                        carriers.setAvailablebalance(phoneAccountObj.getFloat("availablebalance"));
                    }
                    if (phoneAccountObj.containsKey("opentime")) {//入网年份
                        carriers.setOpentime(phoneAccountObj.getString("opentime").replace("T", " "));
                    }
                    if (phoneAccountObj.containsKey("carrier")) {//运营商
                        carriers.setCarrierType(phoneAccountObj.getString("carrier"));
                    }
                    if (phoneAccountObj.containsKey("province")) {//省份
                        carriers.setProvince(phoneAccountObj.getString("province"));
                    }
                    if (phoneAccountObj.containsKey("priceplanname")) {//套餐
                        carriers.setPriceplanname(phoneAccountObj.getString("priceplanname"));
                    }
                }
                break;
            case FIVE_ONE_OPTIMIZE_CARRIER:
                if (jsonObject.containsKey("peopleDetail")) {//身份认证
                    JSONObject peopleDetailObj = jsonObject.getJSONObject("peopleDetail");
                    if (peopleDetailObj.containsKey("phonenumber")) {//电话
                        carriers.setMobile(peopleDetailObj.getString("phonenumber"));
                    }
                    if (peopleDetailObj.containsKey("priceplanname")) {//套餐
                        carriers.setPriceplanname(peopleDetailObj.getString("priceplanname"));
                    }
                    if (peopleDetailObj.containsKey("availablebalance")) {//余额
                        carriers.setAvailablebalance(peopleDetailObj.getFloat("availablebalance"));
                    }
                    if (peopleDetailObj.containsKey("province")) {//省份
                        carriers.setProvince(peopleDetailObj.getString("province"));
                    }
                    if (peopleDetailObj.containsKey("carrier")) {//运营商
                        carriers.setCarrierType(peopleDetailObj.getString("carrier"));
                    }
                    if (peopleDetailObj.containsKey("realname")) {//姓名
                        carriers.setRealName(peopleDetailObj.getString("realname"));
                    }
                    if (peopleDetailObj.containsKey("idcardnumber")) {//身份证
                        carriers.setIdNumber(peopleDetailObj.getString("idcardnumber"));
                    }
                    if (peopleDetailObj.containsKey("opentime")) {//入网年份
                        carriers.setOpentime(peopleDetailObj.getString("opentime").replace("T", " "));
                    }
                }
                break;
            case TONGDUN_CARRIER:
                if (jsonObject.containsKey("channel_src")) {//所属运营商
                    carriers.setCarrierType(jsonObject.getString("channel_src"));
                }
                if (jsonObject.containsKey("channel_attr")) {//省份
                    carriers.setProvince(jsonObject.getString("channel_attr"));
                }
                if (jsonObject.containsKey("user_mobile")) {//电话
                    carriers.setMobile(jsonObject.getString("user_mobile"));
                }
                if (jsonObject.containsKey("real_name")) {//姓名
                    carriers.setRealName(jsonObject.getString("real_name"));
                }
                if (jsonObject.containsKey("identity_code")) {//身份证
                    carriers.setIdNumber(jsonObject.getString("identity_code"));
                }

                JSONObject taskData = jsonObject.getJSONObject("task_data");
                JSONObject accountInfo = taskData.getJSONObject("account_info");
                if (accountInfo.containsKey("account_balance")) {//套餐
                    if (accountInfo.getFloat("account_balance") != null) {
                        carriers.setAvailablebalance(accountInfo.getFloat("account_balance") * 1.0f / 100);
                    }
                }
                if (accountInfo.containsKey("net_time")) {//入网年份
                    carriers.setOpentime(accountInfo.getString("net_time"));
                }
                JSONObject packageInfo = taskData.getJSONObject("package_info");
                if (packageInfo.containsKey("package_detail")) {//套餐
                    JSONArray packageDetailList = packageInfo.getJSONArray("package_detail");
                    for (int i = 0; i < packageDetailList.size(); i++) {
                        Map packageDetail = (Map) packageDetailList.get(i);
                        String category = (String) packageDetail.get("category");
                        if (StringUtils.isNotEmpty(category) && category.contains("套餐")) {
                            carriers.setPriceplanname((String) packageDetail.get("package_name"));
                            break;
                        }
                    }
                }
                break;
            case RONG_THREE_CARRIER:
                if (jsonObject.containsKey("borrower")) {
                    JSONObject user = jsonObject.getJSONObject("borrower");
                    if (user.containsKey("id_card")) {//身份证
                        carriers.setIdNumber(user.getString("id_card"));
                    }
                    if (user.containsKey("real_name")) {//姓名
                        carriers.setRealName(user.getString("real_name"));
                    }
                    if (user.containsKey("phone_remain")) {//余额
                        carriers.setAvailablebalance(user.getFloat("phone_remain"));
                    }
                    if (user.containsKey("phone")) {//电话
                        carriers.setMobile(user.getString("phone"));
                    }
                    if (user.containsKey("reg_time")) {//入网年份
                        carriers.setOpentime(user.getString("reg_time"));
                    }
                    if (user.containsKey("package_name")) {//套餐
                        carriers.setPriceplanname(user.getString("package_name"));
                    }
                }
                break;
        }
        return carriers;
    }

    public static CarriersVo getBillInfo(JSONObject jsonObject, CarriersVo carriers, CarrierTypeEnum carrierTypeEnum) {
        switch (carrierTypeEnum) {
            case FIVE_ONE_CARRIER:
            case FIVE_ONE_OPTIMIZE_CARRIER:
                if (jsonObject.containsKey("bills")) {
                    List<CarriersBillVo> carriersBillVoList = new ArrayList<CarriersBillVo>();
                    JSONObject carriersBillObj = jsonObject.getJSONObject("bills");//
                    if (carriersBillObj.containsKey("total")) {
                        carriers.setTotalMonths(carriersBillObj.getInteger("total"));//消费月数
                    }
                    if (carriersBillObj.containsKey("phoneBillSummaryList")) {
                        JSONArray carriersBillArrayList = carriersBillObj.getJSONArray("phoneBillSummaryList");
                        if (null != carriersBillArrayList) {
                            Map carrierMap;
                            for (int i = 0; i < carriersBillArrayList.size(); i++) {
                                CarriersBillVo carriersBillVo = new CarriersBillVo();
                                carrierMap = (Map) carriersBillArrayList.get(i);
                                carriersBillVo.setBillDate(carrierMap.get("billDate").toString());
                                carriersBillVo.setBillDate(carriersBillVo.getBillDate().replace("T", " ").replace(".000+08", ""));
                                carriersBillVo.setBaseFee(carrierMap.get("baseFee").toString());
                                carriersBillVo.setTotalFee((Double.valueOf((String) carrierMap.get("totalFee")).compareTo(Double.valueOf((String) carrierMap.get("actualFee"))) == 1) ? (String) carrierMap.get("totalFee") : (String) carrierMap.get("actualFee"));
                                if ("CHINA_UNICOM".equals(carriers.getCarrierType())) {
                                    carriersBillVo.setTotalFee(carrierMap.get("discount").toString());
                                }
                                if ("CHINA_MOBILE".equals(carriers.getCarrierType()) || "CHINA_TELECOM".equals(carriers.getCarrierType())) {
                                    carriersBillVo.setTotalFee(carrierMap.get("actualFee").toString());
                                }
                                carriersBillVoList.add(carriersBillVo);
                            }
                        }
                    }
                    carriers.setCarriersBillVoList(carriersBillVoList);
                }
                break;
            case TONGDUN_CARRIER:
                JSONObject taskData = jsonObject.getJSONObject("task_data");
                if (taskData.containsKey("bill_info")) {
                    List<CarriersBillVo> carriersBillVoList = new ArrayList<CarriersBillVo>();
                    JSONArray billInfoList = taskData.getJSONArray("bill_info");
                    if (billInfoList != null) {
                        for (int i = 0; i < billInfoList.size(); i++) {
                            CarriersBillVo carriersBillVo = new CarriersBillVo();
                            JSONObject billInfo = (JSONObject) billInfoList.get(i);
                            if (billInfo.get("bill_cycle") != null) {
                                carriersBillVo.setBillDate(billInfo.get("bill_cycle").toString());
                            }
                            if (billInfo.get("bill_total") != null) {
                                Float billTotal = billInfo.getFloat("bill_total") / 100;
                                carriersBillVo.setTotalFee(billTotal.toString());
                            }
                            if (billInfo.get("bill_fee") != null) {
                                carriersBillVo.setBaseFee(billInfo.get("bill_fee").toString());
                            }
                            carriersBillVoList.add(carriersBillVo);
                        }
                        carriers.setTotalMonths(billInfoList.size());//消费月数
                    }
                    carriers.setCarriersBillVoList(carriersBillVoList);
                }
                break;
            case RONG_THREE_CARRIER:
                if (jsonObject.containsKey("bill")) {
                    List<CarriersBillVo> carriersBillVoList = new ArrayList<CarriersBillVo>();
                    JSONArray billInfoList = jsonObject.getJSONArray("bill");
                    if (billInfoList != null) {
                        for (int i = 0; i < billInfoList.size(); i++) {
                            carriers.setTotalMonths(carriers.getTotalMonths() + 1);//消费月数
                            CarriersBillVo carriersBillVo = new CarriersBillVo();
                            JSONObject billInfo = (JSONObject) billInfoList.get(i);
                            if (billInfo.get("call_pay") != null) {
                                carriersBillVo.setTotalFee(billInfo.getString("call_pay"));
                                if (billInfo.get("month") != null) {
                                    String mouth = billInfo.get("month").toString();
                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
                                    SimpleDateFormat sdfTemp = new SimpleDateFormat("yyyy-MM");
                                    try {
                                        Date time = sdf.parse(mouth);
                                        carriersBillVo.setBillDate(sdfTemp.format(time));
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }

                                }
                            }
                            carriersBillVoList.add(carriersBillVo);
                        }
                    }
                    carriers.setCarriersBillVoList(carriersBillVoList);
                }
                break;
        }
        return carriers;
    }

    private static String getString4JSONObject(JSONObject jsonObject, String key) {
        return jsonObject.containsKey(key) ? jsonObject.getString(key) : "";
    }

    private static CarriersPhoneCallVo getCarriersPhoneCallVo4FiveOneCarrierCallJSON(JSONObject carrierCallJson, String peernumber) {
        //单条记录存储
        CarriersPhoneCallVo carriersCall = new CarriersPhoneCallVo();
        carriersCall.setPeernumber(peernumber);
        carriersCall.setDialtype(getString4JSONObject(carrierCallJson, "dialtype"));
        carriersCall.setLocation(getString4JSONObject(carrierCallJson, "location"));
        carriersCall.setCreatetime(getString4JSONObject(carrierCallJson, "time"));
        carriersCall.setCreatetime(carriersCall.getCreatetime().replace("T", " ").replace(".000+08", ""));
        if (carriersCall.getCreatetime().indexOf(".") > 0) {
            carriersCall.setCreatetime(carriersCall.getCreatetime().substring(0, carriersCall.getCreatetime().indexOf(".")));
        }
        carriersCall.setCallTime(carrierCallJson.containsKey("durationsec") ? carrierCallJson.getString("durationsec") : "0");
        carriersCall.setLocationType(carrierCallJson.containsKey("locationtype") ? carrierCallJson.getString("locationtype") : "");
        return carriersCall;
    }

    private static CarriersPhoneCallVo getCarriersPhoneCallVo4TongDunCarrierCallJSON(JSONObject carrierCallJson, String peernumber) {
        //单条记录存储
        CarriersPhoneCallVo carriersCall = new CarriersPhoneCallVo();
        carriersCall.setPeernumber(peernumber);
        carriersCall.setDialtype(getString4JSONObject(carrierCallJson, "call_type_name"));
        carriersCall.setLocation(getString4JSONObject(carrierCallJson, "call_address"));
        carriersCall.setCreatetime(getString4JSONObject(carrierCallJson, "call_start_time"));
        carriersCall.setCallTime(getString4JSONObject(carrierCallJson, "call_time"));
        return carriersCall;
    }

    private static CarriersPhoneCallVo getCarriersPhoneCallVo4RongThreeCarrierCallJSON(JSONObject carrierCallJson, String peernumber) {
        //单条记录存储
        CarriersPhoneCallVo carriersCall = new CarriersPhoneCallVo();
        carriersCall.setPeernumber(peernumber);
        carriersCall.setDialtype(getString4JSONObject(carrierCallJson, "call_type"));
        carriersCall.setLocation(getString4JSONObject(carrierCallJson, "trade_addr"));
        carriersCall.setCreatetime(getString4JSONObject(carrierCallJson, "call_time"));
        carriersCall.setCallTime(getString4JSONObject(carrierCallJson, "trade_time"));
        return carriersCall;
    }

    public static CarriersVo getPhoneRecordInfo(JSONObject jsonObject, CarriersVo carriers, CarrierTypeEnum carrierTypeEnum) {
        List<CarriersPhoneCallVo> carriersPhoneCallVoList = new ArrayList<CarriersPhoneCallVo>();
        LinkedHashMap<String, CarriersPhoneCallVo> carriersCallMap = new LinkedHashMap<String, CarriersPhoneCallVo>();//每个号码 对应数据统计
        LinkedHashMap<String, CarriersPhoneCallMonthVo> carrierMap = new LinkedHashMap<String, CarriersPhoneCallMonthVo>();//月份   每月统计
        List<CarriersPhoneCallVo> carriersCallVoList = new ArrayList<>();//全部单条记录

        switch (carrierTypeEnum) {
            case FIVE_ONE_CARRIER:
            case FIVE_ONE_OPTIMIZE_CARRIER:
                if (jsonObject.containsKey("phoneCallRecords")) {
                    JSONObject phoneCallRecordsObj = jsonObject.getJSONObject("phoneCallRecords");
                    if (phoneCallRecordsObj.containsKey("total")) {
                        carriers.setTotalPhoneRecords(phoneCallRecordsObj.getInteger("total"));
                    }
                    if (phoneCallRecordsObj.containsKey("records")) {//电话

                        JSONArray phoneCallRecordsArrayList = phoneCallRecordsObj.getJSONArray("records");
                        if (null != phoneCallRecordsArrayList) {
                            //统计号码主叫被叫情况
                            CarriersPhoneCallVo carriersPhoneCallVo;
                            CarriersPhoneCallMonthVo carriersCallMonth = new CarriersPhoneCallMonthVo();
                            JSONObject carrierCallJson;
                            String month = "";

                            for (int i = phoneCallRecordsArrayList.size() - 1; i >= 0; i--) {
                                carrierCallJson = JSONObject.parseObject(phoneCallRecordsArrayList.getString(i));
                                String peerNumber = getString4JSONObject(carrierCallJson, "peernumber");
                                if (StringUtils.isNotEmpty(peerNumber)) {
                                    //单条记录存储
                                    carriersCallVoList.add(getCarriersPhoneCallVo4FiveOneCarrierCallJSON(carrierCallJson, peerNumber));

                                    //月数据统计
                                    if (carrierCallJson.containsKey("time")) {
                                        //有时间
                                        if (month != "" && (!carrierCallJson.getString("time").contains(month))) {
                                            carriersCallMonth.setMonth(month);
                                            carrierMap.put(carriersCallMonth.getMonth(), carriersCallMonth);
                                            carriersCallMonth = new CarriersPhoneCallMonthVo();
                                        }
                                        month = carrierCallJson.getString("time").substring(0, 7);
                                    }
                                    carriersCallMonth.setTotal(carriersCallMonth.getTotal() + 1);

                                    //设置 carriersCallMap
                                    if (carriersCallMap.containsKey(peerNumber)) {
                                        carriersPhoneCallVo = carriersCallMap.get(peerNumber);
                                        if (carrierCallJson.containsKey("dialtype") && carrierCallJson.getString("dialtype").contains("主叫")) {
                                            carriersPhoneCallVo.setCalling(carriersPhoneCallVo.getCalling() + 1);
                                            carriersCallMonth.setCalling(carriersCallMonth.getCalling() + 1);
                                            if (carrierCallJson.containsKey("durationsec")) {
                                                int callTime = Integer.valueOf(StringUtils.isNotEmpty(carriersPhoneCallVo.getCallingTime()) ? carriersPhoneCallVo.getCallingTime() : "0");
                                                callTime += Integer.valueOf(carrierCallJson.getString("durationsec"));
                                                carriersPhoneCallVo.setCallingTime(String.valueOf(callTime));

                                                int mouthCallTime = Integer.valueOf(StringUtils.isNotEmpty(carriersCallMonth.getCallingTime()) ? carriersCallMonth.getCallingTime() : "0");
                                                mouthCallTime += Integer.valueOf(carrierCallJson.getString("durationsec"));
                                                carriersCallMonth.setCallingTime(String.valueOf(mouthCallTime));
                                            }
                                        } else {
                                            carriersPhoneCallVo.setCalled(carriersPhoneCallVo.getCalled() + 1);
                                            carriersCallMonth.setCalled(carriersCallMonth.getCalled() + 1);
                                            if (carrierCallJson.containsKey("durationsec")) {
                                                int callTime = Integer.valueOf(StringUtils.isNotEmpty(carriersPhoneCallVo.getCalledTime()) ? carriersPhoneCallVo.getCalledTime() : "0");
                                                callTime += Integer.valueOf(carrierCallJson.getString("durationsec"));
                                                carriersPhoneCallVo.setCalledTime(String.valueOf(callTime));

                                                int mouthCallTime = Integer.valueOf(StringUtils.isNotEmpty(carriersCallMonth.getCalledTime()) ? carriersCallMonth.getCalledTime() : "0");
                                                mouthCallTime += Integer.valueOf(carrierCallJson.getString("durationsec"));
                                                carriersCallMonth.setCalledTime(String.valueOf(mouthCallTime));
                                            }
                                        }

                                        if (carrierCallJson.containsKey("durationsec")) {
                                            int callTime = Integer.valueOf(StringUtils.isNotEmpty(carriersPhoneCallVo.getCallTime()) ? carriersPhoneCallVo.getCallTime() : "0");
                                            callTime += Integer.valueOf(carrierCallJson.getString("durationsec"));
                                            carriersPhoneCallVo.setCallTime(String.valueOf(callTime));
                                        }
                                        carriersCallMap.put(peerNumber, carriersPhoneCallVo);

                                    } else {//新号码设置
                                        carriersPhoneCallVo = new CarriersPhoneCallVo();
                                        carriersPhoneCallVo.setPeernumber(peerNumber);
                                        carriersPhoneCallVo.setDialtype(carrierCallJson.containsKey("dialtype") ? carrierCallJson.getString("dialtype") : "");
                                        carriersPhoneCallVo.setLocation(carrierCallJson.containsKey("location") ? carrierCallJson.getString("location") : "");
                                        carriersPhoneCallVo.setPeercarrier(carrierCallJson.containsKey("peercarrier") ? carrierCallJson.getString("peercarrier") : "");
                                        carriersPhoneCallVo.setCreatetime(carrierCallJson.containsKey("time") ? carrierCallJson.getString("time") : "");
                                        carriersPhoneCallVo.setCreatetime(carriersPhoneCallVo.getCreatetime().replace("T", " ").replace(".000+08", ""));
                                        carriersPhoneCallVo.setCallTime(carrierCallJson.containsKey("durationsec") ? carrierCallJson.getString("durationsec") : "0");
                                        carriersPhoneCallVo.setCallingTime("0");
                                        carriersPhoneCallVo.setCalledTime("0");
                                        if (carrierCallJson.containsKey("dialtype") && carrierCallJson.getString("dialtype").contains("主叫")) {
                                            carriersPhoneCallVo.setCalling(carriersPhoneCallVo.getCalling() + 1);
                                            carriersCallMonth.setCalling(carriersCallMonth.getCalling() + 1);
                                            carriersPhoneCallVo.setCallingTime(carrierCallJson.containsKey("durationsec") ? carrierCallJson.getString("durationsec") : "0");
                                            carriersCallMonth.setCallingTime(carrierCallJson.containsKey("durationsec") ? carrierCallJson.getString("durationsec") : "0");
                                        } else {
                                            carriersPhoneCallVo.setCalled(carriersPhoneCallVo.getCalled() + 1);
                                            carriersCallMonth.setCalled(carriersCallMonth.getCalled() + 1);
                                            carriersPhoneCallVo.setCalledTime(carrierCallJson.containsKey("durationsec") ? carrierCallJson.getString("durationsec") : "0");
                                            carriersCallMonth.setCalledTime(carrierCallJson.containsKey("durationsec") ? carrierCallJson.getString("durationsec") : "0");
                                        }
                                        carriersCallMap.put(peerNumber, carriersPhoneCallVo);
                                    }
                                }
                                if (i == 0) {
                                    carriersCallMonth.setMonth(month);
                                    carrierMap.put(month, carriersCallMonth);
                                }

                            }

                            for (String key : carriersCallMap.keySet()) {
                                carriersPhoneCallVoList.add(carriersCallMap.get(key));
                            }
                            carriers.setCarriersPhoneCallMap(carriersCallMap);
                            carriers.setCarrierMap(carrierMap);
                            carriers.setCarriersCallVoList(carriersCallVoList);
                            carriers.setCarriersPhoneCallVoList(carriersPhoneCallVoList);
                        }
                    }
                }
                break;
            case TONGDUN_CARRIER:
                JSONObject taskData = jsonObject.getJSONObject("task_data");
                JSONArray callInfoList = taskData.getJSONArray("call_info");
                if (null != callInfoList) {
                    for (int i = 0; i < callInfoList.size(); i++) {
                        JSONObject callInfo = (JSONObject) callInfoList.get(i);
                        CarriersPhoneCallMonthVo carriersCallMonth = new CarriersPhoneCallMonthVo();

                        if (callInfo.containsKey("call_cycle")) {
                            String time = callInfo.getString("call_cycle");
                            carriersCallMonth.setMonth(time);
                            JSONArray callRecordList = callInfo.getJSONArray("call_record");
                            carriersCallMonth.setTotal(callRecordList.size());
                            for (int j = 0; j < callRecordList.size(); j++) {
                                JSONObject callRecord = callRecordList.getJSONObject(j);
                                String peernumber = getString4JSONObject(callRecord, "call_other_number");
                                if (StringUtils.isNotEmpty(peernumber)) {
                                    CarriersPhoneCallVo carriersCallVo = getCarriersPhoneCallVo4TongDunCarrierCallJSON(callRecord, peernumber);
                                    carriersCallVoList.add(carriersCallVo);
                                    String dialtype = carriersCallVo.getDialtype();
                                    String callTime = carriersCallVo.getCallTime();
                                    if (carriersCallMap.containsKey(peernumber)) {
                                        CarriersPhoneCallVo carriersPhoneCallVo = carriersCallMap.get(peernumber);
                                        if (StringUtils.isNotEmpty(dialtype)) {
                                            if (dialtype.contains("主叫")) {
                                                carriersPhoneCallVo.setCalling(carriersPhoneCallVo.getCalling() + 1);
                                                carriersCallMonth.setCalling(carriersCallMonth.getCalling() + 1);
                                                if (StringUtils.isNotEmpty(callTime)) {
                                                    Integer callTimeTotal = Integer.valueOf(StringUtils.isNotEmpty(carriersPhoneCallVo.getCallingTime()) ? carriersPhoneCallVo.getCallingTime() : "0");
                                                    callTimeTotal += Integer.valueOf(callTime);
                                                    carriersPhoneCallVo.setCallingTime(String.valueOf(callTimeTotal));

                                                    Integer mouthCallTimeTotal = Integer.valueOf(StringUtils.isNotEmpty(carriersCallMonth.getCallingTime()) ? carriersCallMonth.getCallingTime() : "0");
                                                    mouthCallTimeTotal += Integer.valueOf(callTime);
                                                    carriersCallMonth.setCallingTime(String.valueOf(mouthCallTimeTotal));
                                                }
                                            } else {
                                                carriersPhoneCallVo.setCalled(carriersPhoneCallVo.getCalled() + 1);
                                                carriersCallMonth.setCalled(carriersCallMonth.getCalled() + 1);
                                                if (StringUtils.isNotEmpty(callTime)) {
                                                    Integer callTimeTotal = Integer.valueOf(StringUtils.isNotEmpty(carriersPhoneCallVo.getCalledTime()) ? carriersPhoneCallVo.getCalledTime() : "0");
                                                    callTimeTotal += Integer.valueOf(callTime);
                                                    carriersPhoneCallVo.setCalledTime(String.valueOf(callTimeTotal));

                                                    Integer mouthCallTimeTotal = Integer.valueOf(StringUtils.isNotEmpty(carriersCallMonth.getCalledTime()) ? carriersCallMonth.getCalledTime() : "0");
                                                    mouthCallTimeTotal += Integer.valueOf(callTime);
                                                    carriersCallMonth.setCalledTime(String.valueOf(mouthCallTimeTotal));
                                                }
                                            }
                                        }
                                        if (StringUtils.isNotEmpty(callTime)) {
                                            Integer callTimeTotal = Integer.valueOf(StringUtils.isNotEmpty(carriersPhoneCallVo.getCallTime()) ? carriersPhoneCallVo.getCallTime() : "0");
                                            callTimeTotal += Integer.valueOf(callTime);
                                            carriersPhoneCallVo.setCallTime(String.valueOf(callTimeTotal));
                                        }
                                        carriersCallMap.put(peernumber, carriersPhoneCallVo);

                                    } else {//新号码设置
                                        carriersCallVo.setCallingTime("0");
                                        carriersCallVo.setCalledTime("0");
                                        if (StringUtils.isNotEmpty(dialtype)) {
                                            if (dialtype.contains("主叫")) {
                                                carriersCallVo.setCalling(carriersCallVo.getCalling() + 1);
                                                carriersCallMonth.setCalling(carriersCallMonth.getCalling() + 1);

                                                carriersCallVo.setCallingTime(callTime);
                                                carriersCallMonth.setCallingTime(callTime);
                                            } else {
                                                carriersCallVo.setCalled(carriersCallVo.getCalled() + 1);
                                                carriersCallMonth.setCalled(carriersCallMonth.getCalled() + 1);
                                                carriersCallVo.setCalledTime(callTime);
                                                carriersCallMonth.setCalledTime(callTime);
                                            }
                                        }

                                        carriersCallMap.put(peernumber, carriersCallVo);
                                    }
                                }
                            }
                            carrierMap.put(carriersCallMonth.getMonth(), carriersCallMonth);
                        }

                    }
                    for (String key : carriersCallMap.keySet()) {
                        carriersPhoneCallVoList.add(carriersCallMap.get(key));
                    }
                    carriers.setCarriersPhoneCallMap(carriersCallMap);
                    carriers.setTotalPhoneRecords(carriersCallVoList.size());
                    carriers.setCarrierMap(carrierMap);
                    carriers.setCarriersCallVoList(carriersCallVoList);
                    carriers.setCarriersPhoneCallVoList(carriersPhoneCallVoList);
                }
                break;
            case RONG_THREE_CARRIER:
                JSONArray telList = jsonObject.getJSONArray("tel");
                if (null != telList) {
                    CarriersPhoneCallMonthVo carriersCallMonth = new CarriersPhoneCallMonthVo();
                    String month = "";
                    for (int i = 0; i < telList.size(); i++) {
                        JSONObject callInfo = (JSONObject) telList.get(i);
                        JSONArray teldataList = callInfo.getJSONArray("teldata");
                        if (null != teldataList) {
                            //统计号码主叫被叫情况
                            for (int j = teldataList.size() - 1; j >= 0; j--) {
                                JSONObject teldata = JSONObject.parseObject(teldataList.getString(j));
                                String peernumber = MobileUtil.parseMobile(getString4JSONObject(teldata, "receive_phone"));

                                if (StringUtils.isNotEmpty(peernumber)) {
                                    //单条记录存储
                                    CarriersPhoneCallVo carriersPhoneCallVo = getCarriersPhoneCallVo4RongThreeCarrierCallJSON(teldata, peernumber);
                                    carriersCallVoList.add(carriersPhoneCallVo);
                                    String location = carriersPhoneCallVo.getLocation();
                                    String dialtype = carriersPhoneCallVo.getDialtype();
                                    String createtime = carriersPhoneCallVo.getCreatetime();
                                    String callTime = carriersPhoneCallVo.getCallTime();

                                    if (createtime != null) {
                                        //有时间
                                        if (StringUtils.isNotEmpty(month) && (!createtime.contains(month))) {
                                            carriersCallMonth.setMonth(month);
                                            carrierMap.put(carriersCallMonth.getMonth(), carriersCallMonth);

                                            if (carrierMap.containsKey(createtime.substring(0, 7))) {
                                                carriersCallMonth = carrierMap.get(createtime.substring(0, 7));
                                            } else {
                                                carriersCallMonth = new CarriersPhoneCallMonthVo();
                                            }
                                        }
                                        month = createtime.substring(0, 7);
                                    }
                                    //月数据统计
                                    carriersCallMonth.setTotal(carriersCallMonth.getTotal() + 1);

                                    //设置 carriersCallMap
                                    if (carriersCallMap.containsKey(peernumber)) {
                                        carriersPhoneCallVo = carriersCallMap.get(peernumber);
                                        if (dialtype.equals("1")) {//主叫
                                            carriersPhoneCallVo.setCalling(carriersPhoneCallVo.getCalling() + 1);
                                            carriersCallMonth.setCalling(carriersCallMonth.getCalling() + 1);
                                            if (StringUtils.isNotEmpty(callTime)) {
                                                Integer callTimeTotal = Integer.valueOf(StringUtils.isNotEmpty(carriersPhoneCallVo.getCallingTime()) ? carriersPhoneCallVo.getCallingTime() : "0");
                                                callTimeTotal += Integer.valueOf(callTime);
                                                carriersPhoneCallVo.setCallingTime(String.valueOf(callTimeTotal));

                                                Integer mouthCallTimeTotal = Integer.valueOf(StringUtils.isNotEmpty(carriersCallMonth.getCallingTime()) ? carriersCallMonth.getCallingTime() : "0");
                                                mouthCallTimeTotal += Integer.valueOf(callTime);
                                                carriersCallMonth.setCallingTime(String.valueOf(mouthCallTimeTotal));
                                            }
                                        }
                                        if (dialtype.equals("2")) {//被叫
                                            carriersPhoneCallVo.setCalled(carriersPhoneCallVo.getCalled() + 1);
                                            carriersCallMonth.setCalled(carriersCallMonth.getCalled() + 1);
                                            if (StringUtils.isNotEmpty(callTime)) {
                                                Integer callTimeTotal = Integer.valueOf(StringUtils.isNotEmpty(carriersPhoneCallVo.getCalledTime()) ? carriersPhoneCallVo.getCalledTime() : "0");
                                                callTimeTotal += Integer.valueOf(callTime);
                                                carriersPhoneCallVo.setCalledTime(String.valueOf(callTimeTotal));

                                                Integer mouthCallTimeTotal = Integer.valueOf(StringUtils.isNotEmpty(carriersCallMonth.getCalledTime()) ? carriersCallMonth.getCalledTime() : "0");
                                                mouthCallTimeTotal += Integer.valueOf(callTime);
                                                carriersCallMonth.setCalledTime(String.valueOf(mouthCallTimeTotal));
                                            }
                                        }

                                        if (StringUtils.isNotEmpty(callTime)) {
                                            Integer callTimeTotal = Integer.valueOf(StringUtils.isNotEmpty(carriersPhoneCallVo.getCallTime()) ? carriersPhoneCallVo.getCallTime() : "0");
                                            callTimeTotal += Integer.valueOf(callTime);
                                            carriersPhoneCallVo.setCallTime(String.valueOf(callTimeTotal));
                                        }
                                        carriersCallMap.put(peernumber, carriersPhoneCallVo);

                                    } else {//新号码设置
                                        CarriersPhoneCallVo temp = new CarriersPhoneCallVo();
                                        temp.setPeernumber(peernumber);
                                        temp.setDialtype(dialtype);
                                        temp.setLocation(location);
                                        temp.setCreatetime(createtime);
                                        temp.setCallTime(callTime);
                                        if (dialtype.equals("1")) {//主叫
                                            temp.setCalling(temp.getCalling() + 1);
                                            carriersCallMonth.setCalling(carriersCallMonth.getCalling() + 1);

                                            temp.setCallingTime(callTime);
                                            carriersCallMonth.setCallingTime(callTime);
                                        }
                                        if (dialtype.equals("2")) {//被叫
                                            temp.setCalled(temp.getCalled() + 1);
                                            carriersCallMonth.setCalled(carriersCallMonth.getCalled() + 1);
                                            temp.setCalledTime(callTime);
                                            carriersCallMonth.setCalledTime(callTime);
                                        }
                                        carriersCallMap.put(peernumber, temp);
                                    }
                                }
                                if (j == 0) {
                                    carriersCallMonth.setMonth(month);
                                    carrierMap.put(month, carriersCallMonth);
                                }
                            }
                        }
                    }
                    for (String key : carriersCallMap.keySet()) {
                        carriersPhoneCallVoList.add(carriersCallMap.get(key));
                    }
                    carriers.setCarriersPhoneCallMap(carriersCallMap);
                    carriers.setTotalPhoneRecords(carriersCallVoList.size());
                    carriers.setCarrierMap(carrierMap);
                    carriers.setCarriersCallVoList(carriersCallVoList);
                    carriers.setCarriersPhoneCallVoList(carriersPhoneCallVoList);

                }
                break;
        }
        return carriers;
    }

    public static CarriersVo getFullInfo(List<CarriersPhoneCallVo> carriersPhoneCallVoList, Map<String, String> phoneBookMap, CarriersVo carriers) {

        if (CollectionUtils.isNotEmpty(carriersPhoneCallVoList)) {
            List<CarriersPhoneCallVo> merchantPhoneCallVoList = new ArrayList<>();
            List<CarriersPhoneCallVo> highFrequencyDeviceContactList = new ArrayList<>();//高频联系人
            List<CarriersPhoneCallVo> activeRegionPhoneCallVoList = new ArrayList<>();
            List<CarriersPhoneCallVo> contactRegionPhoneCallVoList = new ArrayList<>();
            List<CarriersPhoneCallVo> longCallDurationContactVoList = new ArrayList<>();
            Map<String, CarriersPhoneCallVo> activeRegionPhoneCallVoMap = new HashMap<>();//通话活跃区域
            Map<String, CarriersPhoneCallVo> contactRegionMap = new HashMap<>();//联系人区域
            Map<String, Integer> contactRegionCntMap = new HashMap<>();//联系人区域

            for (CarriersPhoneCallVo carrierCallVo : carriersPhoneCallVoList) {
                String phoneNumber = carrierCallVo.getPeernumber();

                if (carrierCallVo.getCalling() > 4 && !StringUtils.isBlank(phoneNumber) && phoneNumber.length() >= 11) {//高频
                    highFrequencyDeviceContactList.add(carrierCallVo);
                }

                //按主叫次数倒排
                if (highFrequencyDeviceContactList.size() > 1) {
                    Collections.sort(highFrequencyDeviceContactList, new Comparator() {
                        @Override
                        public int compare(Object o1, Object o2) {
                            return ((CarriersPhoneCallVo) o2).getCalling().compareTo(((CarriersPhoneCallVo) o1).getCalling());
                        }

                    });
                }
                carriers.setMerchantPhoneCallVoList(merchantPhoneCallVoList);
                carriers.setHighFrequencyDeviceContactList(highFrequencyDeviceContactList);

                if (carrierCallVo.getCalled() > 0 && carrierCallVo.getCalling() > 0) {
                    carriers.setInteractionContactCnt(carriers.getInteractionContactCnt() + 1);
                }

                String location = getLocation(phoneNumber, phoneBookMap);
                if (StringUtils.isNotEmpty(location)) {
                    CarriersPhoneCallVo callVo = contactRegionMap.get(location);
                    if (null == callVo) {
                        CarriersPhoneCallVo phoneCallVo = new CarriersPhoneCallVo();
                        BeanUtils.copyProperties(carrierCallVo, phoneCallVo);
                        phoneCallVo.setLocation(location);
                        contactRegionMap.put(location, phoneCallVo);
                    } else {
                        callVo.setCalling(callVo.getCalling() + carrierCallVo.getCalling())
                                .setCalled(callVo.getCalled() + carrierCallVo.getCalled())
                                .setCallingTime((Long.parseLong(StringUtils.isEmpty(callVo.getCallingTime()) ? "0" : callVo.getCallingTime()) +
                                        Long.parseLong(StringUtils.isEmpty(carrierCallVo.getCallingTime()) ? "0" : carrierCallVo.getCallingTime())) + "")
                                .setCalledTime((Long.parseLong(StringUtils.isEmpty(callVo.getCalledTime()) ? "0" : callVo.getCalledTime()) +
                                        Long.parseLong(StringUtils.isEmpty(carrierCallVo.getCalledTime()) ? "0" : carrierCallVo.getCalledTime())) + "")
                                .setCallTime((Long.parseLong(StringUtils.isEmpty(callVo.getCallTime()) ? "0" : callVo.getCallTime()) +
                                        Long.parseLong(StringUtils.isEmpty(carrierCallVo.getCallTime()) ? "0" : carrierCallVo.getCallTime())) + "");
                    }

                    Integer contactCnt = contactRegionCntMap.get(location);
                    if (null == contactCnt || contactCnt == 0) {
                        contactRegionCntMap.put(location, 1);
                    } else {
                        contactRegionCntMap.put(location, contactCnt + 1);
                    }
                }

                if (StringUtils.isNotEmpty(carrierCallVo.getCallTime()) && Integer.valueOf(carrierCallVo.getCallTime()) >= TimeConsts.TWENTY_MINUTES) {
                    CarriersPhoneCallVo phoneCallVo = new CarriersPhoneCallVo();
                    BeanUtils.copyProperties(carrierCallVo, phoneCallVo);
                    phoneCallVo.setLocation(location);
                    longCallDurationContactVoList.add(phoneCallVo);
                }
                if (longCallDurationContactVoList.size() > 1) {
                    Collections.sort(longCallDurationContactVoList, new Comparator() {
                        @Override
                        public int compare(Object o1, Object o2) {
                            return ((CarriersPhoneCallVo) o2).getCallTime().compareTo(((CarriersPhoneCallVo) o1).getCallTime());
                        }

                    });
                }
                carriers.setLongCallDurationContactVoList(longCallDurationContactVoList);

            }

            for (CarriersPhoneCallVo carrierCallVo : carriers.getCarriersCallVoList()) {
                String location = carrierCallVo.getLocation();
                if (StringUtils.isNotBlank(location)) {
                    CarriersPhoneCallVo callVo = activeRegionPhoneCallVoMap.get(location);
                    if (null == callVo) {
                        CarriersPhoneCallVo phoneCallVo = new CarriersPhoneCallVo();
                        BeanUtils.copyProperties(carrierCallVo, phoneCallVo);
                        phoneCallVo.setCalling(0);
                        phoneCallVo.setCallingTime("0");
                        phoneCallVo.setCalled(0);
                        phoneCallVo.setCalledTime("0");
                        if (StringUtils.isBlank(carrierCallVo.getCallTime())) {
                            continue;
                        }
                        if (carrierCallVo.getDialtype().contains("主叫")) {
                            phoneCallVo.setCalling(1).setCallingTime(carrierCallVo.getCallTime());
                        }
                        if (carrierCallVo.getDialtype().contains("被叫")) {
                            phoneCallVo.setCalled(1).setCalledTime(carrierCallVo.getCallTime());
                        }
                        activeRegionPhoneCallVoMap.put(location, phoneCallVo);
                    } else {
                        if (StringUtils.isBlank(carrierCallVo.getCallTime()) && StringUtils.isBlank(callVo.getCallTime())) {
                            continue;
                        }
                        if (carrierCallVo.getDialtype().contains("主叫") && StringUtils.isNotBlank(callVo.getCallingTime())) {
                            callVo.setCalling(callVo.getCalling() + 1)
                                    .setCallingTime((Long.parseLong(carrierCallVo.getCallTime()) + Long.parseLong(callVo.getCallingTime())) + "")
                                    .setCallTime((Long.parseLong(carrierCallVo.getCallTime()) + Long.parseLong(callVo.getCallTime())) + "");
                        }
                        if (carrierCallVo.getDialtype().contains("被叫") && StringUtils.isNotBlank(callVo.getCalledTime())) {
                            callVo.setCalled(callVo.getCalled() + 1)
                                    .setCalledTime((Long.parseLong(carrierCallVo.getCallTime()) + Long.parseLong(callVo.getCalledTime())) + "")
                                    .setCallTime((Long.parseLong(carrierCallVo.getCallTime()) + Long.parseLong(callVo.getCallTime())) + "");
                        }
                    }
                }

                carriers.setTotalCallDuration(carriers.getTotalCallDuration() + (StringUtils.isEmpty(carrierCallVo.getCallTime()) ? 0 : Integer.valueOf(carrierCallVo.getCallTime())));
                Date createTime = DateUtil.getDate(carrierCallVo.getCreatetime(), "yyyy-MM-dd HH:mm:ss");
                String createHour = DateUtil.getDate(createTime, "HH");
                if (StringUtils.isNotEmpty(createHour) &&
                        (Integer.valueOf(createHour) >= 23 || Integer.valueOf(createHour) < 6)) {
                    carriers.setNightCallDuration(carriers.getNightCallDuration() + (StringUtils.isEmpty(carrierCallVo.getCallTime()) ? 0 : Integer.valueOf(carrierCallVo.getCallTime())));
                }
            }

            if (CollectionUtils.isNotEmpty(activeRegionPhoneCallVoMap.values())) {
                for (CarriersPhoneCallVo carriersPhoneCallVo : activeRegionPhoneCallVoMap.values()) {
                    activeRegionPhoneCallVoList.add(carriersPhoneCallVo);
                }
                if (activeRegionPhoneCallVoList.size() > 1) {
                    Collections.sort(activeRegionPhoneCallVoList, new Comparator() {
                        @Override
                        public int compare(Object o1, Object o2) {
                            return Long.valueOf(((CarriersPhoneCallVo) o2).getCallTime()).compareTo(Long.valueOf(((CarriersPhoneCallVo) o1).getCallTime()));
                        }

                    });
                }
                carriers.setActiveRegionPhoneCallVoList(activeRegionPhoneCallVoList);
                carriers.setActiveRegion(activeRegionPhoneCallVoList.get(0).getLocation());
            }

            if (CollectionUtils.isNotEmpty(contactRegionMap.values())) {
                for (CarriersPhoneCallVo carriersPhoneCallVo : contactRegionMap.values()) {
                    if (carriersPhoneCallVo.getCallTime().equals("")) {
                        continue;
                    }
                    contactRegionPhoneCallVoList.add(carriersPhoneCallVo);
                }
                if (contactRegionPhoneCallVoList.size() > 1) {
                    Collections.sort(contactRegionPhoneCallVoList, new Comparator() {
                        @Override
                        public int compare(Object o1, Object o2) {
                            return Long.valueOf(((CarriersPhoneCallVo) o2).getCallTime()).compareTo(Long.valueOf(((CarriersPhoneCallVo) o1).getCallTime()));
                        }

                    });
                }
                carriers.setContactRegionPhoneCallVoList(contactRegionPhoneCallVoList);
            }

            String region = null;
            Integer mostCnt = 0;
            if (CollectionUtils.isNotEmpty(contactRegionCntMap.values())) {
                for (String key : contactRegionCntMap.keySet()) {
                    Integer cnt = contactRegionCntMap.get(key);
                    if (cnt > mostCnt) {
                        mostCnt = cnt;
                        region = key;
                    }
                }
            }
            carriers.setMostContactRegion(region);
        }
        if (StringUtils.isNotEmpty(carriers.getMobile())) {
            carriers.setRegisterRegion(getLocation(carriers.getMobile(), phoneBookMap));
        }

        return carriers;
    }

    public static String getLocation(String phoneNumber, Map<String, String> phoneBookMap) {
        String location = null;
        if (MobileUtil.isMobile(phoneNumber) && phoneBookMap.containsKey(phoneNumber.substring(0, 7))) {
            location = phoneBookMap.get(phoneNumber.substring(0, 7));
        } else if (phoneNumber.length() > 2 && PhoneUtil.isAreaCode(phoneNumber.substring(0, 3)) && phoneBookMap.containsKey(phoneNumber.substring(0, 3))) {
            location = phoneBookMap.get(phoneNumber.substring(0, 3));
        } else if (phoneNumber.length() > 3 && PhoneUtil.isAreaCode(phoneNumber.substring(0, 4)) && phoneBookMap.containsKey(phoneNumber.substring(0, 4))) {
            location = phoneBookMap.get(phoneNumber.substring(0, 4));
        }
        return location;
    }

}
