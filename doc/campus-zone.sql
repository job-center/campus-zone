-- MySQL dump 10.13  Distrib 5.5.44, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: campus_zues
-- ------------------------------------------------------
-- Server version	5.5.44-0ubuntu0.14.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `announcement`
--

DROP TABLE IF EXISTS `announcement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `announcement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `content` text NOT NULL,
  `send_date` datetime NOT NULL,
  `status` tinyint(4) DEFAULT '1' COMMENT '状态：0删除，1待发布，2发布',
  `modified_date` datetime DEFAULT NULL,
  `modifier_id` int(11) DEFAULT NULL,
  `creator_id` int(11) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='平台公告';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `canteen`
--

DROP TABLE IF EXISTS `canteen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `canteen` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `school_id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `description` text,
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学校食堂';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cateen_recipes`
--

DROP TABLE IF EXISTS `cateen_recipes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cateen_recipes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `canteen_id` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `img` varchar(255) NOT NULL,
  `type` tinyint(4) DEFAULT '1' COMMENT '0:荤菜，1:半荤菜，2:素菜',
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='食堂菜谱';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `geography`
--

DROP TABLE IF EXISTS `geography`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `geography` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` tinyint(4) NOT NULL COMMENT '地区类型，0:省,1:市,2:县',
  `father_id` int(11) DEFAULT NULL COMMENT '上级地区id',
  `name` varchar(45) NOT NULL COMMENT '地区名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='地区表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `government`
--

DROP TABLE IF EXISTS `government`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `government` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(63) NOT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `geography_id` int(11) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='政府单位';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `grade`
--

DROP TABLE IF EXISTS `grade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grade` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `enter_year` int(11) NOT NULL,
  `school_id` int(11) DEFAULT NULL,
  `is_deleted` varchar(45) NOT NULL DEFAULT '0',
  `person_charge` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='年级';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `group`
--

DROP TABLE IF EXISTS `group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `grad_id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `description` varchar(45) DEFAULT NULL,
  `person_charge` varchar(45) DEFAULT NULL COMMENT '班主任',
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='班级';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `parent`
--

DROP TABLE IF EXISTS `parent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parent` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `wechat_id` varchar(45) DEFAULT NULL,
  `phone_no` varchar(15) DEFAULT NULL,
  `workplace` varchar(63) DEFAULT NULL COMMENT '工作地点',
  `sex` tinyint(4) NOT NULL,
  `status` tinyint(4) DEFAULT '1' COMMENT '账号状态：-1删除，1开启，0冻结',
  `children` varchar(45) NOT NULL COMMENT 'json数组存储，家长下的孩子账号id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `school`
--

DROP TABLE IF EXISTS `school`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `school` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `geography_id` int(11) DEFAULT NULL,
  `description` text,
  `person_charge` varchar(45) DEFAULT NULL,
  `type` int(11) DEFAULT NULL COMMENT '学校类型，0：小学，1：初中，2：高中',
  `address` varchar(255) DEFAULT NULL,
  `postcode` varchar(11) DEFAULT NULL COMMENT '邮编',
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0',
  `province_id` int(11) DEFAULT NULL,
  `city_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `shop`
--

DROP TABLE IF EXISTS `shop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shop` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL COMMENT '商店名称',
  `description` varchar(255) DEFAULT NULL COMMENT '商店描述',
  `shop_workers` text COMMENT 'json数组格式，商店工作人员,包含img,name等字段',
  `certificates` text COMMENT '商店证件，json存储',
  `school_id` int(11) NOT NULL,
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学校商店';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `shop_supplier`
--

DROP TABLE IF EXISTS `shop_supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shop_supplier` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `shop_id` int(11) NOT NULL,
  `supplier_id` int(11) NOT NULL,
  `supply_types` text NOT NULL COMMENT 'json数组，存储供应商对某个商店供应的商品类型',
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_no` varchar(45) DEFAULT NULL,
  `name` varchar(45) NOT NULL,
  `group_id` int(11) NOT NULL,
  `phone_no` varchar(11) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `sex` tinyint(4) DEFAULT NULL COMMENT '1:男，2:女',
  `wechat_id` varchar(45) DEFAULT NULL COMMENT '微信号',
  `father_id` int(11) NOT NULL,
  `mother_id` int(11) NOT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '账号状态：-1删除，1开启，0冻结',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `supplier` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(63) NOT NULL,
  `description` varchar(45) DEFAULT NULL,
  `register_no` varchar(45) DEFAULT NULL COMMENT '注册号',
  `permit_range` varchar(45) DEFAULT NULL COMMENT '许可范围',
  `create_time` datetime DEFAULT NULL,
  `business_deadline` datetime DEFAULT NULL COMMENT '营业期限',
  `permit_deadline` datetime DEFAULT NULL,
  `register_address` varchar(255) DEFAULT NULL COMMENT '注册地址',
  `contact` varchar(45) DEFAULT NULL COMMENT '联系人',
  `phone_no` varchar(15) DEFAULT NULL,
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商店供应商';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sys_menu`
--

DROP TABLE IF EXISTS `sys_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_father_id` int(11) DEFAULT NULL,
  `menuName` varchar(45) NOT NULL,
  `menudescription` varchar(255) DEFAULT NULL,
  `menu_path` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统权限资源';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(45) NOT NULL,
  `role_description` varchar(255) DEFAULT NULL,
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='系统角色';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sys_role_menu`
--

DROP TABLE IF EXISTS `sys_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `menu_id` varchar(45) NOT NULL,
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统角色资源表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `sex` tinyint(4) NOT NULL COMMENT '0:男，1：女',
  `passWord` varchar(255) NOT NULL,
  `phoneNumber` varchar(13) NOT NULL,
  `realName` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `school_id` int(11) DEFAULT NULL COMMENT '用户对应的学校或政府单位id',
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='后台系统用户';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sys_user_role`
--

DROP TABLE IF EXISTS `sys_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-04-12 23:57:30
