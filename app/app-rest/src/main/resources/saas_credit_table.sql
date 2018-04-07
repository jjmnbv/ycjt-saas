DROP TABLE IF EXISTS `saas_credit_tongdun`;
CREATE TABLE `saas_credit_tongdun` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '表ID',
  `merchant_code` varchar(32) NOT NULL COMMENT '机构码',
  `borrower_code` varchar(32) NOT NULL COMMENT '用户码',
  `mobile` varchar(15) NOT NULL COMMENT '用户手机号',
  `identity_code` varchar(32) NOT NULL COMMENT '用户身份证号码',
  `report_id` varchar(40) NOT NULL COMMENT ' 同盾流水编号',
  `success` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否查询成功',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_merchant_code` (`merchant_code`),
  KEY `idy_borrower_code` (`borrower_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='SAAS同盾信用记录查询表';

DROP TABLE IF EXISTS `saas_credit_tongdun_detail`;
CREATE TABLE `saas_credit_tongdun_detail` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '表ID',
  `record_id` bigint(20) unsigned NOT NULL COMMENT '同盾信用记录查询表ID',
  `report_id` varchar(40) NOT NULL COMMENT ' 同盾流水编号',
  `tongdun_report` text COMMENT '同盾报告',
  `final_score` varchar(11) DEFAULT NULL COMMENT '同盾分',
  `final_decision` varchar(11) DEFAULT NULL COMMENT '同盾结论',
  `item_name` text COMMENT '报告条目',
  `report_time` datetime DEFAULT NULL COMMENT '报告时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_report_id` (`report_id`)
) ENGINE=InnoDB AUTO_INCREMENT=353 DEFAULT CHARSET=utf8mb4 COMMENT='同盾信用记录详情表';

DROP TABLE IF EXISTS `saas_credit_dunning`;
CREATE TABLE `saas_credit_dunning` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '表ID',
  `merchant_code` varchar(32) NOT NULL COMMENT '机构码',
  `borrower_code` varchar(32) NOT NULL COMMENT '用户码',
  `carrier_id` bigint(20) unsigned NOT NULL COMMENT '运营商数据ID',
  `sid` varchar(64) NOT NULL COMMENT '电话邦催收数据查询唯一标识',
  `mobile` varchar(15) NOT NULL COMMENT '手机号',
  `total_num` int(11) unsigned NOT NULL COMMENT '总记录数',
  `effective_num` int(11) unsigned NOT NULL COMMENT '有效数据数',
  `url` varchar(128) NOT NULL COMMENT '催收数据存储地址',
  `success` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否查询成功',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_sid` (`sid`),
  KEY `idx_merchant_code` (`merchant_code`),
  KEY `idx_borrower_code` (`borrower_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户催收数据查询表';

DROP TABLE IF EXISTS `saas_credit_dunning_detail`;
CREATE TABLE `saas_credit_dunning_detail` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '表ID',
  `record_id` bigint(20) unsigned NOT NULL COMMENT '用户催收数据查询表ID',
  `sid` varchar(64) NOT NULL COMMENT '电话邦催收数据查询唯一标识',
  `call_tel_total_nums` int(11) unsigned NOT NULL COMMENT '总通话号码数',
  `call_total_times` int(11) unsigned NOT NULL COMMENT '总通话次数',
  `call_out_times` int(11) unsigned NOT NULL COMMENT '主叫次数',
  `call_in_times` int(11) unsigned NOT NULL COMMENT '被叫次数',
  `call_total_duration` int(11) unsigned NOT NULL COMMENT '通话总时长',
  `call_avg_duration` decimal(10,2) NOT NULL COMMENT '通话平均时长',
  `call_out_duration` int(11) unsigned NOT NULL COMMENT '主叫总时长',
  `call_in_duration` int(11) unsigned NOT NULL COMMENT '被叫总时长',
  `call_duration_below15` int(11) unsigned NOT NULL COMMENT '通话时长低于15秒的次数',
  `call_duration_between15and30` int(11) unsigned NOT NULL COMMENT '通话时长15-30秒的次数',
  `call_duration_above60` int(11) unsigned NOT NULL COMMENT '通话时长60秒以上的次数',
  `first_call_time` varchar(64) DEFAULT NULL COMMENT '首次通话时间',
  `last_call_time` varchar(64) DEFAULT NULL COMMENT '最近一次通话时间',
  `type` tinyint(4) NOT NULL COMMENT '类型:10-总览催收,11-总览疑似催收,20-近一周催收,21-近一周疑似催收,30-近两周催收,31-近两周疑似催收,40-近三周催收,41-近三周疑似催收,50-近30天催收,51-近30天疑似催收,60-近30至60天催收,61-近30至60',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_record_id` (`record_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户催收数据详情表';

DROP TABLE IF EXISTS `saas_credit_bmp`;
CREATE TABLE `saas_credit_bmp` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '表ID',
  `merchant_code` varchar(32) NOT NULL COMMENT '机构码',
  `borrower_code` varchar(32) NOT NULL COMMENT '用户码',
  `sid` varchar(64) NOT NULL COMMENT '电话邦催收数据查询唯一标识',
  `url` varchar(128) NOT NULL COMMENT '电话邦匹配数据存储地址',
  `success` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否查询成功',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_merchant_code` (`merchant_code`),
  KEY `idy_borrower_code` (`borrower_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='电话邦匹配数据查询表';

DROP TABLE IF EXISTS `saas_credit_bmp_detail`;
CREATE TABLE `saas_credit_bmp_detail` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '表ID',
  `record_id` bigint(20) unsigned NOT NULL COMMENT '电话邦匹配数据查询表ID',
  `dhb_id` varchar(64) DEFAULT NULL COMMENT '匹配号码电话邦的唯一ID',
  `name` varchar(64) DEFAULT NULL COMMENT '商户名称',
  `teldesc` varchar(64) DEFAULT NULL COMMENT '电话描述',
  `teltype` tinyint(4) DEFAULT NULL COMMENT '电话类型 - 1: 手机 2: 传真 3:电话 4:热线 9:隐藏（反查）',
  `telnum` varchar(32) NOT NULL COMMENT '电话号码',
  `telrank` tinyint(4) DEFAULT NULL COMMENT '电话等级 - 1: 认证 2: 普通',
  `country` int(11) DEFAULT NULL COMMENT '国家码',
  `city_id` int(11) DEFAULT NULL COMMENT '城市ID',
  `logo` varchar(512) DEFAULT NULL COMMENT '商户logo下载地址',
  `slogan` varchar(512) DEFAULT NULL COMMENT '商户宣传语',
  `image` varchar(512) DEFAULT NULL COMMENT '商户的标志性图片的下载地址',
  `addr` varchar(512) DEFAULT NULL COMMENT '商户地址',
  `web` varchar(512) DEFAULT NULL COMMENT '企业官方网址',
  `url` varchar(512) DEFAULT NULL COMMENT '电话邦的详情页地址',
  `flag_type` varchar(64) DEFAULT NULL COMMENT '标记说明',
  `flag_num` int(11) DEFAULT NULL COMMENT '标记的用户数',
  `flag_fid` int(11) DEFAULT NULL COMMENT '标记的ID',
  `catnames` varchar(256) DEFAULT NULL COMMENT '电话类别名称',
  `itag_ids` varchar(64) DEFAULT NULL COMMENT '金融标签',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_record_id` (`record_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='电话邦匹配数据详情表';

DROP TABLE IF EXISTS `saas_credit_carrier`;
CREATE TABLE `saas_credit_carrier` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '表ID',
  `merchant_code` varchar(32) NOT NULL COMMENT '机构码',
  `borrower_code` varchar(32) NOT NULL COMMENT '用户码',
  `type` tinyint(4) NOT NULL COMMENT '运营商记录获取来源 1 : 51运营商 2 : 同盾运营商',
  `url` varchar(128) NOT NULL COMMENT '运营商数据存储地址',
  `success` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否查询成功',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_merchant_code` (`merchant_code`),
  KEY `idy_borrower_code` (`borrower_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='运营商报告查询表';

DROP TABLE IF EXISTS `saas_credit_carrier_base`;
CREATE TABLE `saas_credit_carrier_base` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '表ID',
  `record_id` bigint(20) unsigned NOT NULL COMMENT '运营商报告查询表ID',
  `carrier_type` varchar(32) DEFAULT NULL COMMENT '运营商名称',
  `mobile` varchar(15) DEFAULT NULL COMMENT '手机号',
  `name` varchar(64) DEFAULT NULL COMMENT '姓名',
  `identity_no` varchar(32) DEFAULT NULL COMMENT '身份证号码',
  `balance` decimal(10,2) DEFAULT NULL COMMENT '余额',
  `province` varchar(64) DEFAULT NULL COMMENT '归属省份',
  `plan_name` varchar(128) DEFAULT NULL COMMENT '套餐名称',
  `register_time` datetime DEFAULT NULL COMMENT '入网时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_record_id` (`record_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='运营商报告基本资料表';

DROP TABLE IF EXISTS `saas_credit_carrier_bill`;
CREATE TABLE `saas_credit_carrier_bill` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '表ID',
  `record_id` bigint(20) unsigned NOT NULL COMMENT '运营商报告查询表ID',
  `bill_date` date NOT NULL COMMENT '账单时间',
  `base_fee` decimal(10,2) DEFAULT NULL COMMENT '月基本费',
  `total_fee` decimal(10,2) DEFAULT NULL COMMENT '月消费',
  `calling_time` int(11) DEFAULT NULL COMMENT '呼出次数',
  `called_time` int(11) DEFAULT NULL COMMENT '呼入次数',
  `calling_duration` int(11) DEFAULT NULL COMMENT '呼出时长（分）',
  `called_duration` int(11) DEFAULT NULL COMMENT '呼入时长（分）',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_record_id` (`record_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='运营商报告账单详情表';

DROP TABLE IF EXISTS `saas_credit_carrier_ext`;
CREATE TABLE `saas_credit_carrier_ext` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '表ID',
  `record_id` bigint(20) unsigned NOT NULL COMMENT '运营商报告查询表ID',
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
  KEY `idx_record_id` (`record_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='运营商报告扩充资料表';

DROP TABLE IF EXISTS `saas_credit_carrier_record`;
CREATE TABLE `saas_credit_carrier_record` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '表ID',
  `record_id` bigint(20) unsigned NOT NULL COMMENT '运营商报告查询表ID',
  `phone` varchar(32) DEFAULT NULL COMMENT '号码',
  `merchant` varchar(64) DEFAULT NULL COMMENT '商户名',
  `location` varchar(64) NOT NULL DEFAULT '' COMMENT '地址',
  `calling_time` int(11) DEFAULT NULL COMMENT '呼出次数',
  `called_time` int(11) DEFAULT NULL COMMENT '呼入次数',
  `calling_duration` int(11) DEFAULT NULL COMMENT '呼出时长（秒）',
  `called_duration` int(11) DEFAULT NULL COMMENT '呼入时长（秒）',
  `total_duration` int(11) DEFAULT NULL COMMENT '同通话时长（秒）',
  `type` tinyint(4) unsigned NOT NULL COMMENT '记录类型，1：高频联系人记录，2：商户通话记录，3：通话活跃记录',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_record_id` (`record_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='运营商报告通话详情表';