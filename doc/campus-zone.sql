-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema utf8
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema campus_zues
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema campus_zues
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `campus_zues` DEFAULT CHARACTER SET utf8 ;
USE `campus_zues` ;

-- -----------------------------------------------------
-- Table `campus_zues`.`announcement`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `campus_zues`.`announcement` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(255) NOT NULL,
  `content` TEXT NOT NULL,
  `send_date` DATETIME NOT NULL,
  `status` TINYINT(4) NULL DEFAULT '1' COMMENT '状态：0删除，1待发布，2发布',
  `modified_date` DATETIME NULL DEFAULT NULL,
  `modifier_id` INT(11) NULL DEFAULT NULL,
  `creator_id` INT(11) NULL DEFAULT NULL,
  `create_date` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = '平台公告';


-- -----------------------------------------------------
-- Table `campus_zues`.`canteen`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `campus_zues`.`canteen` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `school_id` INT(11) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `description` TEXT NULL DEFAULT NULL,
  `is_deleted` TINYINT(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = '学校食堂';


-- -----------------------------------------------------
-- Table `campus_zues`.`canteen_activity`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `campus_zues`.`canteen_activity` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `canteent_id` INT(11) NOT NULL,
  `title` VARCHAR(45) NOT NULL,
  `content` TEXT NULL DEFAULT NULL COMMENT 'json结构，包含img数组,description描述文字',
  `valid_date_start` DATETIME NOT NULL COMMENT '有效日期',
  `valid_date_end` DATETIME NOT NULL,
  `state` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0:待发布，1：已发布，-1：删除',
  `publish_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `creator_id` INT(11) NOT NULL,
  `modifier_id` INT(11) NULL DEFAULT NULL,
  `modified_time` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = '食堂活动';


-- -----------------------------------------------------
-- Table `campus_zues`.`cateen_recipes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `campus_zues`.`cateen_recipes` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `canteen_id` INT(11) NOT NULL,
  `title` VARCHAR(255) NOT NULL,
  `img` VARCHAR(255) NOT NULL,
  `type` TINYINT(4) NULL DEFAULT '1' COMMENT '0:荤菜，1:半荤菜，2:素菜',
  `is_deleted` TINYINT(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = '食堂菜谱';


-- -----------------------------------------------------
-- Table `campus_zues`.`geography`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `campus_zues`.`geography` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `type` TINYINT(4) NOT NULL COMMENT '地区类型，0:省,1:市,2:县',
  `father_id` INT(11) NULL DEFAULT NULL COMMENT '上级地区id',
  `name` VARCHAR(45) NOT NULL COMMENT '地区名称',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = '地区表';


-- -----------------------------------------------------
-- Table `campus_zues`.`government`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `campus_zues`.`government` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(63) NOT NULL,
  `full_name` VARCHAR(255) NULL DEFAULT NULL,
  `geography_id` INT(11) NOT NULL,
  `create_time` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = '政府单位';


-- -----------------------------------------------------
-- Table `campus_zues`.`grade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `campus_zues`.`grade` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `enter_year` INT(11) NOT NULL,
  `school_id` INT(11) NULL DEFAULT NULL,
  `is_deleted` VARCHAR(45) NOT NULL DEFAULT '0',
  `person_charge` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = '年级';


-- -----------------------------------------------------
-- Table `campus_zues`.`group`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `campus_zues`.`group` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `grad_id` INT(11) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NULL DEFAULT NULL,
  `person_charge` VARCHAR(45) NULL DEFAULT NULL COMMENT '班主任',
  `is_deleted` TINYINT(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = '班级';


-- -----------------------------------------------------
-- Table `campus_zues`.`parent`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `campus_zues`.`parent` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `wechat_id` VARCHAR(45) NULL DEFAULT NULL,
  `phone_no` VARCHAR(15) NULL DEFAULT NULL,
  `workplace` VARCHAR(63) NULL DEFAULT NULL COMMENT '工作地点',
  `sex` TINYINT(4) NOT NULL,
  `status` TINYINT(4) NULL DEFAULT '1' COMMENT '账号状态：-1删除，1开启，0冻结',
  `children` VARCHAR(45) NOT NULL COMMENT 'json数组存储，家长下的孩子账号id',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `campus_zues`.`school`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `campus_zues`.`school` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `full_name` VARCHAR(255) NULL DEFAULT NULL,
  `geography_id` INT(11) NULL DEFAULT NULL,
  `description` TEXT NULL DEFAULT NULL,
  `person_charge` VARCHAR(45) NULL DEFAULT NULL,
  `type` INT(11) NULL DEFAULT NULL COMMENT '学校类型，0：小学，1：初中，2：高中',
  `address` VARCHAR(255) NULL DEFAULT NULL,
  `postcode` VARCHAR(11) NULL DEFAULT NULL COMMENT '邮编',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `campus_zues`.`shop`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `campus_zues`.`shop` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL COMMENT '商店名称',
  `description` VARCHAR(255) NULL DEFAULT NULL COMMENT '商店描述',
  `shop_workers` TEXT NULL DEFAULT NULL COMMENT 'json数组格式，商店工作人员,包含img,name等字段',
  `certificates` TEXT NULL DEFAULT NULL COMMENT '商店证件，json存储',
  `school_id` INT(11) NOT NULL,
  `is_deleted` TINYINT(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = '学校商店';


-- -----------------------------------------------------
-- Table `campus_zues`.`shop_supplier`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `campus_zues`.`shop_supplier` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `shop_id` INT(11) NOT NULL,
  `supplier_id` INT(11) NOT NULL,
  `supply_types` TEXT NOT NULL COMMENT 'json数组，存储供应商对某个商店供应的商品类型',
  `is_deleted` TINYINT(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `campus_zues`.`student`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `campus_zues`.`student` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `student_no` VARCHAR(45) NULL DEFAULT NULL,
  `name` VARCHAR(45) NOT NULL,
  `group_id` INT(11) NOT NULL,
  `phone_no` VARCHAR(11) NULL DEFAULT NULL,
  `birthday` DATETIME NULL DEFAULT NULL,
  `sex` TINYINT(4) NULL DEFAULT NULL COMMENT '1:男，2:女',
  `wechat_id` VARCHAR(45) NULL DEFAULT NULL COMMENT '微信号',
  `father_id` INT(11) NOT NULL,
  `mother_id` INT(11) NOT NULL,
  `status` TINYINT(4) NOT NULL DEFAULT '1' COMMENT '账号状态：-1删除，1开启，0冻结',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `campus_zues`.`supplier`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `campus_zues`.`supplier` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(63) NOT NULL,
  `description` VARCHAR(45) NULL DEFAULT NULL,
  `register_no` VARCHAR(45) NULL DEFAULT NULL COMMENT '注册号',
  `permit_range` VARCHAR(45) NULL DEFAULT NULL COMMENT '许可范围',
  `create_time` DATETIME NULL DEFAULT NULL,
  `business_deadline` DATETIME NULL DEFAULT NULL COMMENT '营业期限',
  `permit_deadline` DATETIME NULL DEFAULT NULL,
  `register_address` VARCHAR(255) NULL DEFAULT NULL COMMENT '注册地址',
  `contact` VARCHAR(45) NULL DEFAULT NULL COMMENT '联系人',
  `phone_no` VARCHAR(15) NULL DEFAULT NULL,
  `is_deleted` TINYINT(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = '商店供应商';


-- -----------------------------------------------------
-- Table `campus_zues`.`sys_menu`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `campus_zues`.`sys_menu` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `menu_father_id` INT(11) NULL DEFAULT NULL,
  `menuName` VARCHAR(45) NOT NULL,
  `menudescription` VARCHAR(255) NULL DEFAULT NULL,
  `menu_path` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = '系统权限资源';


-- -----------------------------------------------------
-- Table `campus_zues`.`sys_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `campus_zues`.`sys_role` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `role_name` VARCHAR(45) NOT NULL,
  `role_description` VARCHAR(255) NULL DEFAULT NULL,
  `is_deleted` TINYINT(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = '系统角色';


-- -----------------------------------------------------
-- Table `campus_zues`.`sys_role_menu`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `campus_zues`.`sys_role_menu` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `role_id` INT(11) NOT NULL,
  `menu_id` VARCHAR(45) NOT NULL,
  `is_deleted` TINYINT(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = '系统角色资源表';


-- -----------------------------------------------------
-- Table `campus_zues`.`sys_user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `campus_zues`.`sys_user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `sex` TINYINT(4) NOT NULL COMMENT '0:男，1：女',
  `passWord` VARCHAR(255) NOT NULL,
  `phoneNumber` VARCHAR(13) NOT NULL,
  `realName` VARCHAR(45) NULL DEFAULT NULL,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  `school_id` INT NULL DEFAULT NULL COMMENT '用户对应的学校或政府单位id',
  `is_deleted` TINYINT(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = '后台系统用户';


-- -----------------------------------------------------
-- Table `campus_zues`.`sys_user_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `campus_zues`.`sys_user_role` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NOT NULL,
  `role_id` INT(11) NOT NULL,
  `is_deleted` TINYINT(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
