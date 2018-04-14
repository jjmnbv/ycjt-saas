package com.beitu.saas.app.application.contract;

import com.beitu.saas.app.application.contract.consts.ContractConsts;
import com.beitu.saas.app.application.order.OrderCalculateApplication;
import com.beitu.saas.auth.domain.SaasMerchantVo;
import com.beitu.saas.auth.service.SaasMerchantConfigService;
import com.beitu.saas.auth.service.SaasMerchantService;
import com.beitu.saas.borrower.client.SaasBorrowerRealInfoService;
import com.beitu.saas.borrower.domain.SaasBorrowerRealInfoVo;
import com.beitu.saas.common.config.ConfigUtil;
import com.beitu.saas.common.enums.RestCodeEnum;
import com.beitu.saas.common.utils.DateUtil;
import com.beitu.saas.common.utils.OrderNoUtil;
import com.beitu.saas.common.utils.StringUtil;
import com.beitu.saas.order.client.SaasOrderService;
import com.beitu.saas.order.entity.SaasOrder;
import com.beitu.saas.order.enums.OrderErrorCodeEnum;
import com.fqgj.common.utils.NumberToCNUtil;
import com.fqgj.exception.common.ApplicationException;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author linanjun
 * @create 2018/4/5 下午4:51
 * @description
 */
@Service
public class ContractCreateApplication {

    private static final Log LOGGER = LogFactory.getLog(ContractCreateApplication.class);

    @Autowired
    private SaasBorrowerRealInfoService saasBorrowerRealInfoService;

    @Autowired
    private SaasMerchantService saasMerchantService;

    @Autowired
    private SaasMerchantConfigService saasMerchantConfigService;

    @Autowired
    private SaasOrderService<SaasOrder> saasOrderService;

    @Autowired
    private ConfigUtil configUtil;

    @Autowired
    private OrderCalculateApplication orderCalculateApplication;

    public void createAuthorizationPdf(String userCode, String createFilePath) {
        String name;
        String identityNo = null;
        String creditCode = null;
        SaasBorrowerRealInfoVo saasBorrowerRealInfoVo = saasBorrowerRealInfoService.getBorrowerRealInfoByBorrowerCode(userCode);
        if (saasBorrowerRealInfoVo != null) {
            name = saasBorrowerRealInfoVo.getName();
            identityNo = saasBorrowerRealInfoVo.getIdentityCode();
        } else {
            SaasMerchantVo saasMerchantVo = saasMerchantService.getByMerchantCode(userCode);
            if (saasMerchantVo == null) {
                throw new ApplicationException(RestCodeEnum.USER_NOT_EXIST_ERROR);
            }
            if (saasMerchantConfigService.isCompanyContractByMerchantCode(userCode)) {
                name = saasMerchantVo.getCompanyName();
                creditCode = saasMerchantVo.getCreditCode();
            } else {
                name = saasMerchantVo.getLenderName();
                identityNo = saasMerchantVo.getLenderIdcard();
            }
        }
        Map<String, String> data = new HashMap<>(8);
        data.put("orderNo", userCode);
        data.put("userName", name);
        data.put("identityNo", identityNo);
        data.put("creditCode", creditCode);
        data.put("inscribeDate", DateUtil.getDate(new Date(), ContractConsts.CONTRACT_INSCRIBE_DATE_PATTERN));
        generateContract(ContractConsts.AUTHORIZATION_PDF_PATH, createFilePath, data);
    }

    public void createLoanPdf(Long orderId, String createFilePath) {
        SaasOrder saasOrder = saasOrderService.selectById(orderId);
        if (saasOrder == null) {
            throw new ApplicationException(OrderErrorCodeEnum.ERROR_ORDER_NUMB);
        }
        Map<String, String> data = new HashMap<>(16);
        SaasBorrowerRealInfoVo saasBorrowerRealInfoVo = saasBorrowerRealInfoService.getBorrowerRealInfoByBorrowerCode(saasOrder.getBorrowerCode());
        if (saasBorrowerRealInfoVo == null) {
            throw new ApplicationException(RestCodeEnum.BORROWER_NOT_EXIST_ERROR);
        }
        data.put("borrowUserName", saasBorrowerRealInfoVo.getName());
        data.put("borrowIdentityNo", saasBorrowerRealInfoVo.getIdentityCode());
        SaasMerchantVo saasMerchantVo = saasMerchantService.getByMerchantCode(saasOrder.getMerchantCode());
        if (saasMerchantVo == null) {
            throw new ApplicationException(RestCodeEnum.MERCHANT_NOT_EXIST_ERROR);
        }
        if (saasMerchantConfigService.isCompanyContractByMerchantCode(saasOrder.getMerchantCode())) {
            data.put("lenderUserName", saasMerchantVo.getCompanyName());
            data.put("creditCode", saasMerchantVo.getCreditCode());
        } else {
            data.put("lenderUserName", saasMerchantVo.getLenderName());
            data.put("lenderIdentityNo", saasMerchantVo.getLenderIdcard());
        }

        data.put("orderNo", getContractNumbByOrderId(saasOrder.getId()));
        Date createdDt;
        if (saasOrder.getCreatedDt() == null) {
            createdDt = new Date();
        } else {
            createdDt = saasOrder.getCreatedDt();
        }
        data.put("createdDt", DateUtil.getDate(createdDt));
        data.put("deadline", DateUtil.countDay(saasOrder.getRepaymentDt(), createdDt) + "");
        data.put("realCapital", saasOrder.getRealCapital().toString());
        data.put("realCapitalCN", NumberToCNUtil.number2CNMontrayUnit(saasOrder.getRealCapital()));
        data.put("repaymentDt", DateUtil.getDate(saasOrder.getRepaymentDt()));
        data.put("totalInterestRatio", orderCalculateApplication.getInterestRatio(saasOrder.getTotalInterestRatio()));
        data.put("inscribeDate", DateUtil.getDate(new Date(), ContractConsts.CONTRACT_INSCRIBE_DATE_PATTERN));
        generateContract(ContractConsts.LOAN_PDF_PATH, createFilePath, data);
    }

    public void createExtendPdf(Long orderId, String createFilePath) {
        SaasOrder saasOrder = saasOrderService.selectById(orderId);
        if (saasOrder == null) {
            throw new ApplicationException(OrderErrorCodeEnum.ERROR_ORDER_NUMB);
        }
        Map<String, String> data = new HashMap<>(16);
        SaasBorrowerRealInfoVo saasBorrowerRealInfoVo = saasBorrowerRealInfoService.getBorrowerRealInfoByBorrowerCode(saasOrder.getBorrowerCode());
        if (saasBorrowerRealInfoVo == null) {
            throw new ApplicationException(RestCodeEnum.BORROWER_NOT_EXIST_ERROR);
        }
        data.put("borrowUserName", saasBorrowerRealInfoVo.getName());
        data.put("borrowIdentityNo", saasBorrowerRealInfoVo.getIdentityCode());
        SaasMerchantVo saasMerchantVo = saasMerchantService.getByMerchantCode(saasOrder.getMerchantCode());
        if (saasMerchantVo == null) {
            throw new ApplicationException(RestCodeEnum.MERCHANT_NOT_EXIST_ERROR);
        }
        if (saasMerchantConfigService.isCompanyContractByMerchantCode(saasOrder.getMerchantCode())) {
            data.put("lenderUserName", saasMerchantVo.getCompanyName());
            data.put("creditCode", saasMerchantVo.getCreditCode());
        } else {
            data.put("lenderUserName", saasMerchantVo.getLenderName());
            data.put("lenderIdentityNo", saasMerchantVo.getLenderIdcard());
        }

        data.put("orderNo", getContractNumbByOrderId(saasOrder.getId()));
        data.put("firstOrderNo", getContractNumbByOrderId(saasOrder.getRelationOrderId()));
        Date createdDt;
        if (saasOrder.getCreatedDt() == null) {
            createdDt = DateUtil.addDate(new Date(), 1);
        } else {
            createdDt = saasOrder.getCreatedDt();
        }
        data.put("createdDt", DateUtil.getDate(createdDt, ContractConsts.CONTRACT_INSCRIBE_DATE_PATTERN));
        data.put("deadline", (DateUtil.countDay(saasOrder.getRepaymentDt(), createdDt) + 1) + "");
        data.put("realCapital", saasOrder.getRealCapital().toString());
        data.put("realCapitalCN", NumberToCNUtil.number2CNMontrayUnit(saasOrder.getRealCapital()));
        data.put("repaymentDt", DateUtil.getDate(saasOrder.getRepaymentDt(), ContractConsts.CONTRACT_INSCRIBE_DATE_PATTERN));
        data.put("totalInterestRatio", orderCalculateApplication.getInterestRatio(saasOrder.getTotalInterestRatio()));
        data.put("inscribeDate", DateUtil.getDate(new Date(), ContractConsts.CONTRACT_INSCRIBE_DATE_PATTERN));
        generateContract(ContractConsts.EXTEND_PDF_PATH, createFilePath, data);
    }

    private void generateContract(String srcPdfFilePath, String createFilePath, Map<String, String> data) {
        ByteArrayOutputStream bos = null;
        OutputStream fos = null;
        try {
            PdfReader reader = new PdfReader(srcPdfFilePath);
            bos = new ByteArrayOutputStream();
            PdfStamper ps = new PdfStamper(reader, bos);
            AcroFields fields = ps.getAcroFields();
            BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            for (String key : data.keySet()) {
                String dataValue = data.get(key);
                if (StringUtils.isNotEmpty(dataValue)) {
                    String value = data.get(key).toString();
                    fields.setFieldProperty(key, "textfont", bf, null);
                    fields.setField(key, value);
                }
            }
            ps.setFormFlattening(true);
            ps.close();
            fos = new FileOutputStream(createFilePath);
            fos.write(bos.toByteArray());
        } catch (Exception e) {
            LOGGER.error("合同生成失败，失败原因：{}；文件路径：{}", e.getMessage(), srcPdfFilePath);
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
                if (bos != null) {
                    bos.close();
                }
            } catch (Exception e) {

            }
        }
    }

    private String getContractNumbByOrderId(Long orderId) {
        return String.format("%0" + configUtil.getContractNumbLength() + "d", orderId);
    }

}
