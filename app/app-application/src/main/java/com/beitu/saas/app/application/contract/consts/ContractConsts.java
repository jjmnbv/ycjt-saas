package com.beitu.saas.app.application.contract.consts;

/**
 * @author linanjun
 * @create 2018/4/2 下午9:49
 * @description
 */
public class ContractConsts {

    public static final String AUTHORIZATION_PDF_PATH = ContractConsts.class.getClassLoader().getResource("").getPath() + "authorization.pdf";

    public static final String LOAN_PDF_PATH = ContractConsts.class.getClassLoader().getResource("").getPath() + "borrowTerm.pdf";

    public static final String EXTEND_PDF_PATH = ContractConsts.class.getClassLoader().getResource("").getPath() + "extendTerm.pdf";

    public static final String CONTRACT_INSCRIBE_DATE_PATTERN = "yyyy年MM月dd日";

    public static final String DEFAULT_DO_LICENSE_CONTRACT_SIGN_OPERATOR_NAME = "SYSTEM";

    public static final String TEST_PDF_PATH = "test.pdf";

    public static final String SEAL_BASE64_DATA_PREFIX = "data:image/png;base64,";

}
