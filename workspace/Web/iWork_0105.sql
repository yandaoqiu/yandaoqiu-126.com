-- MySQL Script generated by MySQL Workbench
-- Sun Jan  7 19:59:41 2018
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema iWork
-- -----------------------------------------------------
-- iWork主数据库

-- -----------------------------------------------------
-- Schema iWork
--
-- iWork主数据库
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `iWork` DEFAULT CHARACTER SET utf8 ;
USE `iWork` ;

-- -----------------------------------------------------
-- Table `iWork`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `iWork`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(64) NOT NULL COMMENT '用户id',
  `tel` VARCHAR(18) NULL COMMENT '电话',
  `level_job` INT NULL DEFAULT 1 COMMENT '发布人的等级',
  `level_worker` INT NULL DEFAULT 1 COMMENT '工人的等级',
  `device_id` VARCHAR(45) NOT NULL COMMENT '设备id',
  `token` VARCHAR(64) NOT NULL,
  `gender` INT NULL COMMENT '性别',
  `age` INT NULL COMMENT '年龄',
  `card_number` VARCHAR(32) NULL,
  `thumb` VARCHAR(128) NULL COMMENT '缩略图',
  `hd` VARCHAR(128) NULL COMMENT '高清图',
  `city` VARCHAR(45) NULL COMMENT '城市',
  `nick_name` VARCHAR(45) NOT NULL DEFAULT '游客' COMMENT '用户名',
  `type` VARCHAR(45) NULL COMMENT '会的技能',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin
COMMENT = '用户表';


-- -----------------------------------------------------
-- Table `iWork`.`follow`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `iWork`.`follow` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(64) NOT NULL,
  `f_user_id` VARCHAR(64) NULL COMMENT '关注的用户id',
  `f_type_id` VARCHAR(32) NULL COMMENT '关注的类型id',
  `f_job_id` VARCHAR(32) NULL COMMENT '关注的工作id',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = '关注表';


-- -----------------------------------------------------
-- Table `iWork`.`request_history`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `iWork`.`request_history` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `ip` VARCHAR(16) NOT NULL,
  `date_time` VARCHAR(45) NULL,
  `user_id` VARCHAR(64) NULL,
  `device_id` VARCHAR(45) NULL,
  `action` VARCHAR(45) NULL,
  `token` VARCHAR(64) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = '请求记录表';


-- -----------------------------------------------------
-- Table `iWork`.`job`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `iWork`.`job` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `job_id` VARCHAR(64) NOT NULL COMMENT '工作id',
  `title` VARCHAR(64) NOT NULL COMMENT '工作标题',
  `subc` VARCHAR(256) NOT NULL COMMENT '工作描述',
  `location` VARCHAR(32) NOT NULL COMMENT '发布的经纬度',
  `status` INT NOT NULL DEFAULT 0 COMMENT '工作的状态,0 审核，1显示，2封了。',
  `address` VARCHAR(126) NULL COMMENT '工作地址',
  `price` VARCHAR(45) NOT NULL DEFAULT 0 COMMENT '价钱',
  `unit` VARCHAR(15) NULL DEFAULT '天' COMMENT '单位 \nXX元/天',
  `type` INT NOT NULL DEFAULT 999 COMMENT '工作类型，见工作类型表',
  `date_time` VARCHAR(45) NULL COMMENT '发布时间',
  `need_people` INT NULL COMMENT '需要的人数，按类型自动填充，默认系统 不展示。',
  `level` INT NULL DEFAULT 1 COMMENT '着急程度 0 不着急 1 正常 2 很急',
  `eff_day` INT NULL DEFAULT 15 COMMENT '有效时间，默认15天',
  `age_down` INT NULL DEFAULT 18 COMMENT '最低年龄',
  `age_up` INT NULL DEFAULT 60 COMMENT '最高年龄',
  `gender` INT NULL COMMENT '性别',
  `reson` VARCHAR(126) NULL COMMENT '原因（正常是为封这条job的解释）',
  `pic` VARCHAR(126) NULL COMMENT '图片说明，id用,分开',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `job_id_UNIQUE` (`job_id` ASC))
ENGINE = InnoDB
COMMENT = '工作';


-- -----------------------------------------------------
-- Table `iWork`.`dic_type_item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `iWork`.`dic_type_item` (
  `type_id` INT NOT NULL COMMENT '类型ID',
  `type_name` VARCHAR(15) NOT NULL COMMENT '类型描述',
  PRIMARY KEY (`type_id`),
  UNIQUE INDEX `type_id_UNIQUE` (`type_id` ASC))
ENGINE = InnoDB
COMMENT = '发布的工作、技能类型表';


-- -----------------------------------------------------
-- Table `iWork`.`job_comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `iWork`.`job_comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `job_id` VARCHAR(64) NOT NULL,
  `comment` VARCHAR(126) NOT NULL,
  `job_comment` VARCHAR(45) NULL,
  `date_time` VARCHAR(45) NOT NULL COMMENT '备注时间',
  `user_id` VARCHAR(64) NULL,
  `action` INT NULL DEFAULT 0 COMMENT '0 默认是评论，1是转发',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = 'job 关联信息 留言';


-- -----------------------------------------------------
-- Table `iWork`.`job_history`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `iWork`.`job_history` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(64) NULL,
  `job_id` VARCHAR(64) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `iWork`.`job_score`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `iWork`.`job_score` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `job_id` VARCHAR(64) NOT NULL,
  `start0` INT NULL COMMENT '星级， 态度、效率、等等',
  `start1` INT NULL,
  `start2` INT NULL,
  `start3` INT NULL,
  `comment` VARCHAR(60) NULL COMMENT '评语',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = '完成的工作的评分+评语';


-- -----------------------------------------------------
-- Table `iWork`.`appeal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `iWork`.`appeal` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `appeal_id` VARCHAR(64) NULL,
  `job_id` VARCHAR(64) NULL COMMENT '如果是job 该id不能为空',
  `user_id` VARCHAR(64) NOT NULL,
  `pic` VARCHAR(45) NULL COMMENT '截图',
  `status` INT NULL DEFAULT 0 COMMENT '状态',
  `comment` VARCHAR(120) NULL,
  `answer` VARCHAR(120) NULL,
  `level` INT NULL DEFAULT 0 COMMENT '紧急程度',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `appeal_id_UNIQUE` (`appeal_id` ASC))
ENGINE = InnoDB
COMMENT = '申诉';


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
