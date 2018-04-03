DROP TABLE IF EXISTS `saas_order_application`;
CREATE TABLE `saas_order_application` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '表ID',
  `merchant_code` varchar(32) NOT NULL COMMENT '机构CODE',
  `channel_code` varchar(32) NOT NULL COMMENT '渠道CODE',
  `borrower_code` varchar(32) NOT NULL COMMENT '借款人CODE',
  `order_numb` varchar(32) DEFAULT NULL COMMENT '订单号',
  `real_capital` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '借款金额',
  `total_interest_ratio` decimal(5,4) NOT NULL DEFAULT '0.0000' COMMENT '借款年利率',
  `late_interest_ratio` decimal(5,4) NOT NULL DEFAULT '0.2400' COMMENT '逾期利率',
  `borrow_purpose` varchar(32) DEFAULT NULL COMMENT '借款意图',
  `repayment_dt` date NOT NULL COMMENT '账单应还日',
  `term_url` varchar(256) DEFAULT NULL COMMENT '借款协议URL地址',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_merchant_code` (`merchant_code`),
  KEY `idx_channel_code` (`channel_code`),
  KEY `idy_borrower_code` (`borrower_code`),
  KEY `idy_order_numb` (`order_numb`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='SAAS订单申请表';

DROP TABLE IF EXISTS `saas_order`;
CREATE TABLE `saas_order` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '表ID',
  `order_numb` varchar(32) NOT NULL COMMENT '订单号',
  `merchant_code` varchar(32) NOT NULL COMMENT '机构CODE',
  `channel_code` varchar(32) NOT NULL COMMENT '渠道CODE',
  `borrower_code` varchar(32) NOT NULL COMMENT '借款人CODE',
  `real_capital` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '借款金额',
  `total_interest_ratio` decimal(5,4) NOT NULL DEFAULT '0.0000' COMMENT '借款年利率',
  `total_interest_fee` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '借款总利息',
  `late_interest_ratio` decimal(5,4) NOT NULL DEFAULT '0.2400' COMMENT '逾期利率',
  `borrow_purpose` varchar(32) DEFAULT NULL COMMENT '借款意图',
  `repayment_dt` date NOT NULL COMMENT '账单应还日',
  `created_dt` date NOT NULL COMMENT '订单申请时间',
  `expire_date` datetime NOT NULL COMMENT '订单过期时间',
  `term_url` varchar(256) DEFAULT NULL COMMENT '借款协议URL地址',
  `relation_order_id` bigint(20) unsigned DEFAULT NULL COMMENT '展期关联订单ID',
  `order_status` smallint(4) NOT NULL COMMENT '订单状态',  
  `preliminary_reviewer_code` varchar(32) DEFAULT NULL COMMENT '初审员',
  `final_reviewer_code` varchar(32) DEFAULT NULL COMMENT '复审员',
  `loan_lender_code` varchar(32) DEFAULT NULL COMMENT '放款人',
  `remark` varchar(128) DEFAULT NULL COMMENT '备注',
  `version` bigint(20) NOT NULL DEFAULT '0',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_order_numb` (`order_numb`),
  KEY `idx_merchant_code` (`merchant_code`),
  KEY `idx_channel_code` (`channel_code`),
  KEY `idy_borrower_code` (`borrower_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='SAAS订单表';

DROP TABLE IF EXISTS `saas_order_bill_detail`;
CREATE TABLE `saas_order_bill_detail` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '表ID',
  `order_numb` varchar(32) NOT NULL COMMENT '订单号',
  `merchant_code` varchar(32) NOT NULL COMMENT '机构CODE',
  `channel_code` varchar(32) NOT NULL COMMENT '渠道CODE',
  `borrower_code` varchar(32) NOT NULL COMMENT '借款人CODE',
  `real_capital` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '借款金额',
  `need_pay_interest` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '未结算利息',
  `total_interest_ratio` decimal(5,4) NOT NULL DEFAULT '0.0000' COMMENT '借款年利率',
  `interest` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '利息',
  `created_dt` date NOT NULL COMMENT '账单生成日',
  `repayment_dt` date NOT NULL COMMENT '账单应还日',
  `late_interest_ratio` decimal(5,4) NOT NULL DEFAULT '0.2400' COMMENT '逾期利率',
  `late_interest` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '逾期利息',
  `amount` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '应还金额',
  `relation_order_bill_detail_id` bigint(20) unsigned DEFAULT NULL COMMENT '展期关联账单ID',
  `visible` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否可见',
  `destroy` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否已核销',
  `actual_destroy_date` datetime DEFAULT NULL COMMENT '核销时间',
  `actual_destroy_dt` date DEFAULT NULL COMMENT '核销日期',
  `deleted` bit(1) NOT NULL COMMENT '是否删除',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_order_numb` (`order_numb`),
  KEY `idy_merchant_code` (`merchant_code`),
  KEY `idy_channel_code` (`channel_code`),
  KEY `idz_borrower_code` (`borrower_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='SAAS账单表';

DROP TABLE IF EXISTS `saas_order_status_history`;
CREATE TABLE `saas_order_status_history` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '表ID',
  `order_id` bigint(20) unsigned NOT NULL COMMENT '订单ID',
  `order_numb` varchar(32) NOT NULL COMMENT '订单号',
  `current_order_status` smallint(4) DEFAULT NULL COMMENT '当前订单状态',
  `update_order_status` smallint(4) DEFAULT NULL COMMENT '更新后订单状态',
  `operator_code` varchar(32) NOT NULL COMMENT '操作人CODE',
  `remark` varchar(128) DEFAULT NULL COMMENT '备注',
  `deleted` bit(1) NOT NULL COMMENT '是否删除',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_order_id` (`order_id`),
  KEY `idx_order_numb` (`order_numb`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='SAAS订单状态流水表';

DROP TABLE IF EXISTS `saas_borrower`;
CREATE TABLE `saas_borrower` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '表ID',
  `borrower_code` varchar(32) NOT NULL COMMENT '借款人CODE',
  `merchant_code` varchar(32) NOT NULL COMMENT '机构CODE',
  `channel_code` varchar(32) NOT NULL COMMENT '渠道CODE',
  `mobile` varchar(15) NOT NULL COMMENT '手机号',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_borrower_code` (`borrower_code`),
  KEY `idy_merchant_code` (`merchant_code`),
  KEY `idy_channel_code` (`channel_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='SAAS借款人表';

DROP TABLE IF EXISTS `saas_borrower_login_log`;
CREATE TABLE `saas_borrower_login_log` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '表ID',
  `borrower_code` varchar(32) NOT NULL COMMENT '借款人CODE',
  `merchant_code` varchar(32) NOT NULL COMMENT '机构CODE',
  `channel_code` varchar(32) NOT NULL COMMENT '渠道CODE',
  `phone_system` varchar(32) DEFAULT NULL COMMENT '手机操作系统',
  `login_ip` varchar(16) NOT NULL COMMENT '登录IP',
  `login_ip_address` varchar(32) DEFAULT NULL COMMENT '登录详细地址',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_borrower_code` (`borrower_code`),
  KEY `idy_merchant_code` (`merchant_code`),
  KEY `idy_channel_code` (`channel_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='SAAS借款人登录日志表';

DROP TABLE IF EXISTS `saas_borrower_token`;
CREATE TABLE `saas_borrower_token` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '表ID',
  `borrower_code` varchar(32) NOT NULL COMMENT '借款人CODE',
  `merchant_code` varchar(32) NOT NULL COMMENT '机构CODE',
  `token` varchar(32) NOT NULL COMMENT '授权令牌',
  `expire_date` datetime NOT NULL COMMENT '过期时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_borrower_code` (`borrower_code`),
  KEY `idx_merchant_code` (`merchant_code`),
  KEY `idy_token` (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='SAAS借款人TOKEN表';

DROP TABLE IF EXISTS `saas_borrower_real_info`;
CREATE TABLE `saas_borrower_real_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '表ID',
  `borrower_code` varchar(32) NOT NULL COMMENT '借款人CODE',
  `name` varchar(64) NOT NULL COMMENT '用户实名',
  `identity_code` varchar(32) NOT NULL COMMENT '用户身份证号码',
  `gender` tinyint(4) NOT NULL COMMENT '用户性别',
  `native_place` varchar(32) NOT NULL COMMENT '用户籍贯',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_borrower_code` (`borrower_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='SAAS借款人实名信息表';

DROP TABLE IF EXISTS `saas_borrower_personal_info`;
CREATE TABLE `saas_borrower_personal_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '表ID',
  `borrower_code` varchar(32) NOT NULL COMMENT '借款人CODE',
  `order_numb` varchar(32) DEFAULT NULL COMMENT '订单号',
  `qq` varchar(16) DEFAULT NULL COMMENT 'QQ',
  `education` tinyint(4) DEFAULT NULL COMMENT '学历',
  `address` varchar(64) DEFAULT NULL COMMENT '居住地址',
  `live_duration` varchar(32) DEFAULT NULL COMMENT '居住时长',
  `marital_status` tinyint(4) DEFAULT NULL COMMENT '婚姻状况',
  `zm_credit_score` smallint(8) DEFAULT NULL COMMENT '芝麻分',
  `success` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否成功',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_borrower_code` (`borrower_code`),
  KEY `idy_order_numb` (`order_numb`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='SAAS借款人个人信息表';

DROP TABLE IF EXISTS `saas_borrower_emergent_contact`;
CREATE TABLE `saas_borrower_emergent_contact` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '表ID',
  `borrower_code` varchar(32) NOT NULL COMMENT '借款人CODE',
  `order_numb` varchar(32) DEFAULT NULL COMMENT '订单号',
  `family_type` varchar(16) DEFAULT NULL COMMENT '直系亲属联系人类型',
  `family_name` varchar(32) DEFAULT NULL COMMENT '直系亲属联系人输入姓名',
  `family_mobile` varchar(16) DEFAULT NULL COMMENT '直系亲属联系人手机号',
  `friend_type` varchar(16) DEFAULT NULL COMMENT '同事朋友联系人类型',
  `friend_name` varchar(32) DEFAULT NULL COMMENT '同事朋友联系人输入姓名',
  `friend_mobile` varchar(16) DEFAULT NULL COMMENT '同事朋友联系人手机号',
  `success` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否成功',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_borrower_code` (`borrower_code`),
  KEY `idy_order_numb` (`order_numb`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='SAAS借款人紧急联系人信息表';

DROP TABLE IF EXISTS `saas_borrower_identity_info`;
CREATE TABLE `saas_borrower_identity_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '表ID',
  `borrower_code` varchar(32) NOT NULL COMMENT '借款人CODE',
  `order_numb` varchar(32) DEFAULT NULL COMMENT '订单号',
  `front_url` varchar(255) DEFAULT NULL COMMENT '身份证正面面图片URL',
  `back_url` varchar(255) DEFAULT NULL COMMENT '身份证反面图片URL',
  `hold_url` varchar(255) DEFAULT NULL COMMENT '手持身份证图片URL',
  `success` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否成功',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_borrower_code` (`borrower_code`),
  KEY `idy_order_numb` (`order_numb`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='SAAS借款人身份证信息表';

DROP TABLE IF EXISTS `saas_borrower_work_info`;
CREATE TABLE `saas_borrower_work_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '表ID',
  `borrower_code` varchar(32) NOT NULL COMMENT '借款人CODE',
  `order_numb` varchar(32) DEFAULT NULL COMMENT '订单号',
  `career` varchar(32) DEFAULT NULL COMMENT '职业',
  `salary` smallint(8) DEFAULT NULL COMMENT '月收入',
  `pay_day` tinyint(4) DEFAULT NULL COMMENT '发薪日',
  `company_name` varchar(128) DEFAULT NULL COMMENT '公司名称',
  `company_detail_address` varchar(256) DEFAULT NULL COMMENT '公司详细地址',
  `success` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否成功',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_borrower_code` (`borrower_code`),
  KEY `idy_order_numb` (`order_numb`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='SAAS借款人工作信息表';

DROP TABLE IF EXISTS `saas_borrower_carrier`;
CREATE TABLE `saas_borrower_carrier` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '表ID',
  `borrower_code` varchar(32) NOT NULL COMMENT '借款人CODE',
  `url` varchar(128) NOT NULL COMMENT '运营商数据存储地址',
  `mobile` varchar(16) DEFAULT NULL COMMENT '手机号',
  `expire_date` datetime DEFAULT NULL COMMENT '过期时间',
  `success` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否成功',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_borrower_code` (`borrower_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='SAAS借款人运营商报告信息表';

DROP TABLE IF EXISTS `saas_borrower_carrier_ext`;
CREATE TABLE `saas_borrower_carrier_ext` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '表ID',
  `borrower_code` varchar(32) NOT NULL COMMENT '借款人CODE',
  `activeRegion` varchar(128) NOT NULL COMMENT '活跃地区',
  `registerRegion` varchar(128) NOT NULL COMMENT '入网地区',
  `mostContactRegion` varchar(128) NOT NULL COMMENT '联系人最多地区',
  `interactionCount` int(11) unsigned NOT NULL COMMENT '互通联系人数量',
  `totalDuration` int(11) unsigned NOT NULL COMMENT '总通话时长',
  `nightDuration` int(11) unsigned NOT NULL COMMENT '夜间通话时长',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_borrower_code` (`borrower_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='SAAS借款人运营商报告扩充资料表';

DROP TABLE IF EXISTS `saas_user_esign_authorization`;
CREATE TABLE `saas_user_esign_authorization` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '表ID',
  `user_code` varchar(32) DEFAULT NULL COMMENT '用户码',
  `account_id` varchar(40) NOT NULL COMMENT '用户e签宝账户标识',
  `seal_url` varchar(128) DEFAULT NULL COMMENT 'e签宝生成印章URL地址',
  `authorization_url` varchar(128) DEFAULT NULL COMMENT '签章后授权协议URL地址',
  `authorization_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '用户授权意愿时间',
  `success` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否已成功授权',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_code` (`user_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='SAAS用户e签宝授权信息表';
