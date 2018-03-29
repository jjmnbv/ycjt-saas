package com.beitu.saas.app.application.contract;

import com.beitu.saas.common.config.ConfigUtil;
import com.beitu.saas.common.handle.oss.OSSService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @Author linanjun
 * @Create 2017/10/11 下午8:19
 * @Description
 */
@Component
public class ContractApplication {

    @Autowired
    private ConfigUtil configUtil;

    @Autowired
    private ESignUtil eSignUtil;

    @Autowired
    private OSSService ossService;

    public AuthorizationInfoResponse getAuthorizationInfo(String token, Boolean isSensitive) {
        UserAccessTokenVo userAccessTokenVo = userAccessTokenService.getByToken(token);
        if (userAccessTokenVo == null) {
            throw new ApplicationException(RestCodeEnum.TOKEN_NOT_AVAILABLE);
        }
        UserZmCreditVo userZmCreditVo = userZmCreditService.getUserZmCreditByUserId(userAccessTokenVo.getUserId());
        AuthorizationInfoResponse authorizationInfoResponse = new AuthorizationInfoResponse();
        authorizationInfoResponse.setUserName(userZmCreditVo.getName());
        authorizationInfoResponse.setIdentityNo(isSensitive ? userZmCreditVo.getIdentityNo() : SensitiveInfoUtils.idCardNum(userZmCreditVo.getIdentityNo()));
        authorizationInfoResponse.setInscribeDate(new Date());
        return authorizationInfoResponse;
    }

    public OrderTermResponseVo getTermUrl(Long orderId) {
        RequestBasicInfo requestBasicInfo = RequestLocalInfo.getRequestBasicInfo();
        String version = requestBasicInfo.getAppVersion();
        boolean needReviewUrl = com.fqgj.common.utils.StringUtils.isNotEmpty(version) && version.equals(configUtil.getAppVersion()) && com.fqgj.common.utils.StringUtils.isNotEmpty(configUtil.getReviewUrl());
        boolean isReview = StringUtils.isNotEmpty(requestBasicInfo.getHorse()) && needReviewUrl;
        String token = RequestLocalInfo.getCurrentUser().getToken();
        String url = configUtil.getAddressURLPrefix() + (isReview ? TermUrlConsts.borrowTermReviewUrl : TermUrlConsts.borrowTermUrl) + "?token=" + token;
        if (orderId == null) {
            if (configUtil.isServerTest()) {
                url += "&test=1";
            }
            return new OrderTermResponseVo(url);
        }

        url += ("&orderId=" + orderId);
        if (configUtil.isServerTest()) {
            url += "&test=1";
        }
        Order order = (Order) orderService.selectById(orderId);
        if (order != null && StringUtils.isNotEmpty(order.getTermUrl())) {
            url = configUtil.getAddressURLPrefix() + TermUrlConsts.pdfViewUrl + "?pdf=" + configUtil.getAddressURLPrefix() + order.getTermUrl();
        } else {
            threadGeneratePdfTerm(orderId);
        }
        return new OrderTermResponseVo(url);
    }

    private void threadGeneratePdfTerm(Long orderId) {
        List<OrderBillDetail> orderBillDetails = orderBillDetailService.selectByParams(new HashMap<String, Object>(2) {{
            put("orderId", orderId);
        }});
        if (CollectionUtils.isNotEmpty(orderBillDetails)) {
            GeneratePDFContractThread runnable = new GeneratePDFContractThread(eSignUtil, ossService, orderTradeApplication.generateOrderLawTerm(orderBillDetails), orderService, configUtil.isServerTest(), configUtil.getPdfPath(), PDFTypeEnum.CONTRACTMODEL.getType());
            Thread thread = new Thread(runnable);
            thread.start();
        }
    }


}
