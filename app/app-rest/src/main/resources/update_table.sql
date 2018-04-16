alter table `saas_order_application` drop column term_url;
alter table `saas_order_application` Add column borrower_authorized_sign_loan bit(1) NOT NULL DEFAULT b'0' COMMENT '借款人是否授权签署借款合同' AFTER `repayment_dt`;
alter table `saas_order` Add column borrower_authorized_sign_loan bit(1) NOT NULL DEFAULT b'0' COMMENT '借款人是否授权签署借款合同' AFTER `term_url`;