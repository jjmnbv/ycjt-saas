package com.beitu.saas.app.application.contract.thread;

import com.beitu.saas.app.application.contract.ContractApplication;
import com.beitu.saas.app.application.contract.enums.ContractTypeEnum;
import com.beitu.saas.order.client.SaasOrderService;
import com.beitu.saas.order.entity.SaasOrder;
import com.fqgj.common.utils.StringUtils;

/**
 * @author linanjun
 * @create 2018/4/5 下午8:35
 * @description
 */
public class GenerateContractThread implements Runnable {

    private ContractApplication contractApplication;

    private SaasOrderService saasOrderService;

    private String userCode;

    private Long orderId;

    private ContractTypeEnum contractTypeEnum;

    public GenerateContractThread(ContractApplication contractApplication, SaasOrderService saasOrderService, String userCode, Long orderId, ContractTypeEnum contractTypeEnum) {
        this.contractApplication = contractApplication;
        this.saasOrderService = saasOrderService;
        this.userCode = userCode;
        this.orderId = orderId;
        this.contractTypeEnum = contractTypeEnum;
    }

    @Override
    public void run() {
        switch (contractTypeEnum) {
            case BORROWER_DO_LOAN_CONTRACT_SIGN:
                contractApplication.doLoanContractSign(orderId);
                break;
            case LENDER_DO_LOAN_CONTRACT_SIGN:
                contractApplication.doLoanContractSign(orderId);
                break;
            case BORROWER_DO_EXTEND_CONTRACT_SIGN:
                contractApplication.doExtendContractSign(orderId);
                break;
            case LENDER_DO_EXTEND_CONTRACT_SIGN:
                contractApplication.doExtendContractSign(orderId);
                break;
            case BORROWER_DO_AUTHORIZATION_CONTRACT_SIGN:
                contractApplication.borrowerDoLicenseContractSign(userCode);
                break;
            case LENDER_DO_AUTHORIZATION_CONTRACT_SIGN:
                contractApplication.lenderDoLicenseContractSign(userCode);
                break;
            case BORROWER_DO_AUTHORIZATION_CONTRACT_AND_LOAN_CONTRACT_SIGN:
                contractApplication.borrowerDoLicenseContractSign(userCode);
                contractApplication.doLoanContractSign(orderId);
                break;
        }
    }

}
