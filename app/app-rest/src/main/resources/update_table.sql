alter table `saas_order_application` drop column term_url;
alter table `saas_order_application` Add column borrower_authorized_sign_loan bit(1) NOT NULL DEFAULT b'0' COMMENT '借款人是否授权签署借款合同' AFTER `repayment_dt`;
alter table `saas_order` Add column borrower_authorized_sign_loan bit(1) NOT NULL DEFAULT b'0' COMMENT '借款人是否授权签署借款合同' AFTER `term_url`;


alter table `saas_order` Add column service_fee decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '手续费' AFTER `remark`;
alter table `saas_order_bill_detail` Add column service_fee decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '手续费' AFTER `amount`;
alter table `saas_order_lend_remark` Add column lend_certificate varchar(512) DEFAULT NULL COMMENT '下款凭证' AFTER `lend_way`;