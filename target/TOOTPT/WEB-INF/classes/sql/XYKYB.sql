-- 数据库初始化脚本

-- 创建数据库
CREATE DATABASE XYKYB;
-- 使用数据库
USE XYKYB;

-- 创建医保用户表
CREATE TABLE YBUser(
    `user_id` BIGINT NOT NULL COMMENT '用户id,注意不是主键',
    `name` VARCHAR(50) NOT NULL COMMENT '姓名',
    `sex` INT(1) NOT NULL COMMENT '1:男 2：女',
    `age` INT(3) NOT NULL  COMMENT '年龄',
    `YLZH` VARCHAR(100) NOT NULL COMMENT '医疗证号',
    `DNH` VARCHAR(100) COMMENT '电脑号',
    `SFZH` VARCHAR(100) COMMENT '身份证号',
    `CSSJ` VARCHAR(50) COMMENT '出生时间',
    `DWBM` VARCHAR(100) COMMENT '单位编码',
    `DWMC` VARCHAR(100) COMMENT '单位名称',
    `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '用户创建时间',
    PRIMARY KEY (`name`, `YLZH`),/*联合主键 姓名和医疗证号联合为一个主键*/
	  KEY `ybuser_create_time` (`create_time`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='医保用户表';

-- 创建门诊挂号表
CREATE TABLE YBMZGH(
    `ghid` BIGINT NOT NULL AUTO_INCREMENT COMMENT '挂号id',
    `MZLSH` VARCHAR(30) NOT NULL COMMENT '门诊流水号',
    `YLZH` VARCHAR(100) NOT NULL COMMENT '医疗证号',
    `BRLX` INT(2) COMMENT '病人类型',
    `KSBM` VARCHAR(3) COMMENT '科室编码',
    `KSMC` VARCHAR(50) COMMENT '科室名称',
    `GHLB` INT(2) COMMENT '挂号类别',
    `GHF` DOUBLE COMMENT '挂号费',
    `ZJZJ` DOUBLE COMMENT '专家诊金',
    `GHHJ` DOUBLE COMMENT '挂号合计',
    `SSJ` DOUBLE COMMENT '实收金',
    `ZSJ` DOUBLE COMMENT '找赎金',
    `MZGHJSDetail` VARCHAR(500) COMMENT '门诊挂号结算详情（医保返回结果）',
    `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_name` VARCHAR(20) COMMENT '创建人',
    PRIMARY KEY (`ghid`),
    KEY `ybmzgh_mzlsh` (`MZLSH`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='门诊挂号表';

-- 创建门诊登记表
CREATE TABLE YBMZDJ(
    `djid` BIGINT NOT NULL AUTO_INCREMENT COMMENT '登记id',
    `MZLSH` VARCHAR(30) NOT NULL COMMENT '门诊流水号',
    `MZLB` INT(2) COMMENT '门诊类别（代码：普通、特检、特病）',
    `TBLB` INT(2) COMMENT '特病类别',
    `TJLB` INT(2) COMMENT '特检类别',
    `ZD`   VARCHAR(50) COMMENT '诊断',
    `ZDSM` VARCHAR(500) COMMENT '诊断说明',
    `CFZS` INT(2) COMMENT '处方张数',
    `YSBM` VARCHAR(3) COMMENT '医生编码',
    `YSXM` VARCHAR(20) COMMENT '医生姓名',
    `YSDH` VARCHAR(20) COMMENT '医生电话',
    `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_name` VARCHAR(20) COMMENT '创建人',
    PRIMARY KEY (`djid`),
    KEY `ybmzdj_mzlsh` (`MZLSH`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='门诊登记表';

-- 创建门诊费用表
CREATE TABLE YBMZFY(
    `fyid` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `MZLSH` VARCHAR(30) NOT NULL COMMENT '门诊流水号',
    `DJH` VARCHAR(30) NOT NULL COMMENT '支付单据号',
    `JE` DOUBLE COMMENT '金额',
    `SSJ` DOUBLE COMMENT '实收金',
    `ZSJ` DOUBLE COMMENT '找赎金',
    `DJH2` VARCHAR(30)  COMMENT '退费单据号',
    `status` INT(1) NOT NULL DEFAULT 0 COMMENT '0 未支付 1 已支付 2 已退费',
    `ZFMZJSJG` VARCHAR(500) COMMENT '支付门诊结算结果（医保返回结果）',
    `TFMZJSJG` VARCHAR(500) COMMENT '退费门诊结算结果（医保返回结果）',
    `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_name` VARCHAR(20) COMMENT '创建人',
    `pay_time` TIMESTAMP NULL COMMENT '支付时间',
    `pay_name` VARCHAR(20) COMMENT '支付操作人',
    `refund_time` TIMESTAMP NULL COMMENT '退费时间',
    `refund_name` VARCHAR(20) COMMENT '退费操作人',
    PRIMARY KEY (`fyid`),
    KEY `ybmzfy_mzlsh` (`MZLSH`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='门诊费用表';

-- 创建门诊费用明细表
CREATE TABLE YBMZFYDetail(
    `detailId` BIGINT NOT NULL AUTO_INCREMENT COMMENT '费用明细ID',
    `fyid` BIGINT NOT NULL COMMENT '门诊费用表ID',
    `NO` INT(3) NOT NULL COMMENT '序号',
    `DJH` VARCHAR(30) NOT NULL COMMENT '支付单据号',
    `YLJGNBBM` VARCHAR(30) COMMENT '医疗机构内部药品或诊疗项目编码',
    `TYBM`  VARCHAR(30) COMMENT '社保统一药品或诊疗项目编码',
    `JSXM`  VARCHAR(30) COMMENT '医保结算项目（代码“医保结算项目”）',
    `MC`  VARCHAR(100) COMMENT '医疗机构内部药品或诊疗项目名称',
    `GG` VARCHAR(30) COMMENT '规格',
    `DW` VARCHAR(30) COMMENT '单位',
    `DJ` DOUBLE COMMENT '单价',
    `SL` DOUBLE COMMENT '数量',
    `HJJE` DOUBLE COMMENT '合计金额',
    `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_name` VARCHAR(20) COMMENT '创建人',
    PRIMARY KEY (`detailId`),
    KEY `ybmzfyDetail_fyid` (`fyid`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='门诊费用明细表';
