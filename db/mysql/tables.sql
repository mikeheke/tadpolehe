-- MySQL dump 10.11
--
-- Host: localhost    Database: test
-- ------------------------------------------------------
-- Server version	5.0.85-community-nt

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
-- Table structure for table `hstb_email_send_record_201504`
--

DROP TABLE IF EXISTS `hstb_email_send_record_201504`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hstb_email_send_record_201504` (
  `EMAIL_SEND_RECORD_ID` varchar(45) NOT NULL,
  `HOST` varchar(30) default NULL,
  `PERSONAL_NAME` varchar(30) default NULL,
  `MAIL_FROM_ADD` varchar(30) default NULL,
  `MAIL_TO_ADD` varchar(200) NOT NULL,
  `MAIL_CC_ADD` varchar(200) default NULL,
  `MAIL_BCC_ADD` varchar(200) default NULL,
  `TEMPLATES_ID` varchar(100) default NULL,
  `EMAIL_SUBJECT` varchar(800) default NULL,
  `EMAIL_CONTENT` varchar(3000) default NULL,
  `ACCESSORY` varchar(200) default NULL,
  `SEND_FLAG` int(11) NOT NULL,
  `SEND_NUM` int(11) NOT NULL,
  `SEND_DATE` datetime default NULL,
  `REMARK` varchar(2000) default NULL,
  `STATE` int(11) NOT NULL,
  `RECORD_STATE` int(11) NOT NULL,
  `CREATED_USER_ID` varchar(32) default NULL,
  `CREATED_TIME` datetime default NULL,
  `UPDATED_USER_ID` varchar(32) default NULL,
  `UPDATED_TIME` datetime default NULL,
  PRIMARY KEY  (`EMAIL_SEND_RECORD_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `hstb_log_operation_201504`
--

DROP TABLE IF EXISTS `hstb_log_operation_201504`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hstb_log_operation_201504` (
  `LOG_OPERATION_ID` varchar(45) NOT NULL,
  `EMP_NUMBER` varchar(10) NOT NULL,
  `USER_LOCATION` varchar(100) default NULL,
  `EVENT_TYPE` varchar(2046) default NULL,
  `EVENT_ACTION` varchar(2046) default NULL,
  `LOG_TIME` datetime default NULL,
  `LOG_CONTENT` varchar(1000) default NULL,
  `MODULE_ID` varchar(200) default NULL,
  `REMARK` varchar(2000) default NULL,
  `RECORD_STATE` int(11) NOT NULL,
  `CREATED_USER_ID` varchar(32) default NULL,
  `CREATED_TIME` datetime default NULL,
  `UPDATED_USER_ID` varchar(32) default NULL,
  `UPDATED_TIME` datetime default NULL,
  `APPLICATION_ID` varchar(45) default NULL,
  PRIMARY KEY  (`LOG_OPERATION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `hstb_log_operation_201505`
--

DROP TABLE IF EXISTS `hstb_log_operation_201505`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hstb_log_operation_201505` (
  `LOG_OPERATION_ID` varchar(45) NOT NULL,
  `EMP_NUMBER` varchar(10) NOT NULL,
  `USER_LOCATION` varchar(100) default NULL,
  `EVENT_TYPE` varchar(2046) default NULL,
  `EVENT_ACTION` varchar(2046) default NULL,
  `LOG_TIME` datetime default NULL,
  `LOG_CONTENT` varchar(1000) default NULL,
  `MODULE_ID` varchar(200) default NULL,
  `REMARK` varchar(2000) default NULL,
  `RECORD_STATE` int(11) NOT NULL,
  `CREATED_USER_ID` varchar(32) default NULL,
  `CREATED_TIME` datetime default NULL,
  `UPDATED_USER_ID` varchar(32) default NULL,
  `UPDATED_TIME` datetime default NULL,
  `APPLICATION_ID` varchar(45) default NULL,
  PRIMARY KEY  (`LOG_OPERATION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `hstb_log_performance_201504`
--

DROP TABLE IF EXISTS `hstb_log_performance_201504`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hstb_log_performance_201504` (
  `LOG_PERFORMANCE_ID` varchar(45) NOT NULL,
  `JOB_NAME` varchar(100) default NULL,
  `RUN_TIME` int(11) default NULL,
  `START_TIME` datetime default NULL,
  `END_TIME` datetime default NULL,
  `RUN_FLAG` int(11) default NULL,
  `MODULE_ID` varchar(200) default NULL,
  `REMARK` varchar(2000) default NULL,
  `RECORD_STATE` int(11) NOT NULL,
  `CREATED_USER_ID` varchar(32) default NULL,
  `CREATED_TIME` datetime default NULL,
  `UPDATED_USER_ID` varchar(32) default NULL,
  `UPDATED_TIME` datetime default NULL,
  `APPLICATION_ID` varchar(45) default NULL,
  PRIMARY KEY  (`LOG_PERFORMANCE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `hstb_log_performance_201505`
--

DROP TABLE IF EXISTS `hstb_log_performance_201505`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hstb_log_performance_201505` (
  `LOG_PERFORMANCE_ID` varchar(45) NOT NULL,
  `JOB_NAME` varchar(100) default NULL,
  `RUN_TIME` int(11) default NULL,
  `START_TIME` datetime default NULL,
  `END_TIME` datetime default NULL,
  `RUN_FLAG` int(11) default NULL,
  `MODULE_ID` varchar(200) default NULL,
  `REMARK` varchar(2000) default NULL,
  `RECORD_STATE` int(11) NOT NULL,
  `CREATED_USER_ID` varchar(32) default NULL,
  `CREATED_TIME` datetime default NULL,
  `UPDATED_USER_ID` varchar(32) default NULL,
  `UPDATED_TIME` datetime default NULL,
  `APPLICATION_ID` varchar(45) default NULL,
  PRIMARY KEY  (`LOG_PERFORMANCE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `hstb_sms_send_record_201504`
--

DROP TABLE IF EXISTS `hstb_sms_send_record_201504`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hstb_sms_send_record_201504` (
  `SMS_SEND_RECORD_ID` varchar(45) NOT NULL,
  `DMP` varchar(2000) default NULL,
  `MSG` varchar(4000) default NULL,
  `CREATETIME` datetime default NULL,
  `STATE` int(11) default NULL,
  `USERID` varchar(100) default NULL,
  PRIMARY KEY  (`SMS_SEND_RECORD_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mstb_application`
--

DROP TABLE IF EXISTS `mstb_application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mstb_application` (
  `APPLICATION_ID` varchar(45) NOT NULL,
  `APPLICATION_CODE` varchar(128) NOT NULL,
  `APPLICATION_NAME` varchar(256) NOT NULL,
  `FAULT_HANDLER_EMP_NUMBER` varchar(32) default NULL,
  `IS_CHECK_CODE` int(11) NOT NULL,
  `APPLICATION_LAYOUT` int(11) NOT NULL,
  `FIX_WAY` varchar(256) default NULL,
  `FAIL_NUM` int(11) default NULL,
  `DEPLOY_SERVER` varchar(256) default NULL,
  `PORT` varchar(256) default NULL,
  `CONTEXT` varchar(256) default NULL,
  `ORDER_NO` int(11) default NULL,
  `LANGUANGE` varchar(10) NOT NULL,
  `REMARK` varchar(2048) default NULL,
  `RECORD_STATE` int(11) NOT NULL,
  `CREATED_USER_ID` varchar(32) default NULL,
  `CREATED_TIME` datetime default NULL,
  `UPDATED_USER_ID` varchar(32) default NULL,
  `UPDATED_TIME` datetime default NULL,
  `STATE` int(11) default NULL,
  `TIME_ZONE` varchar(10) default NULL,
  `SESSION_TIME_OUT` varchar(256) default NULL,
  `DEPARTMENT_CODE` varchar(20) default NULL,
  `DEFAULT_PAGE` varchar(1000) default NULL,
  `APPLICATION_STYLE` varchar(256) default NULL,
  `AUTHENTICATE_TYPE` int(11) default NULL,
  `INNER_MATCH_USER` varchar(32) default NULL,
  `EJB_AUTH_STATE_LESS` varchar(30) default NULL,
  PRIMARY KEY  (`APPLICATION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mstb_application_plus`
--

DROP TABLE IF EXISTS `mstb_application_plus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mstb_application_plus` (
  `APPLICATION_PLUS_ID` varchar(45) NOT NULL,
  `APPLICATION_ID` varchar(45) NOT NULL,
  `PARAMETER_CODE` varchar(256) default NULL,
  `PARAMETER_NAME` varchar(512) default NULL,
  `PARAMETER_VALUE` varchar(512) default NULL,
  `REMARK` varchar(2048) default NULL,
  `STATE` int(11) NOT NULL,
  `RECORD_STATE` int(11) NOT NULL,
  `CREATED_USER_ID` varchar(32) default NULL,
  `CREATED_TIME` datetime default NULL,
  `UPDATED_USER_ID` varchar(32) default NULL,
  `UPDATED_TIME` datetime default NULL,
  PRIMARY KEY  (`APPLICATION_PLUS_ID`),
  KEY `FKE30FD94D3784850` (`APPLICATION_ID`),
  CONSTRAINT `FKE30FD94D3784850` FOREIGN KEY (`APPLICATION_ID`) REFERENCES `mstb_application` (`APPLICATION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mstb_bds_schemainfor`
--

DROP TABLE IF EXISTS `mstb_bds_schemainfor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mstb_bds_schemainfor` (
  `BDS_SCHEMAINFOR_ID` varchar(45) NOT NULL,
  `BDS_SCHEMAINFOR_CODE` varchar(40) default NULL,
  `BDS_SCHEMAINFOR_NAME_ENG` varchar(30) default NULL,
  `BDS_SCHEMAINFOR_NAME_CNA` varchar(100) NOT NULL,
  `APPLICATION_ID` varchar(45) NOT NULL,
  `BDS_SCHEMAINFOR_TYPE` varchar(20) default NULL,
  `BDS_SQL` varchar(2000) default NULL,
  `JNDI` varchar(200) default NULL,
  `WEBSERVICE_ADDRESS` varchar(200) default NULL,
  `WEBSERVICE_FUNCTION` varchar(200) default NULL,
  `WEBSERVICE_USER` varchar(20) default NULL,
  `WEBSERVICE_PWD` varchar(20) default NULL,
  `DATA_STRUCTURE_XML` text,
  `REMARK` varchar(2000) default NULL,
  `STATE` int(11) NOT NULL,
  `RECORD_STATE` int(11) NOT NULL,
  `CREATED_USER_ID` varchar(32) default NULL,
  `CREATED_TIME` datetime default NULL,
  `UPDATED_USER_ID` varchar(32) default NULL,
  `UPDATED_TIME` datetime default NULL,
  `CACHE_TIMEOUT` int(11) default NULL,
  `WEBSERVICE_NAMESPACE` varchar(200) default NULL,
  `WEBSERVICE_REQ_ROOT` varchar(50) default NULL,
  `WEBSERVICE_RES_ROOT` varchar(50) default NULL,
  `DATA_STRUCTURE_XML_STR` text,
  PRIMARY KEY  (`BDS_SCHEMAINFOR_ID`),
  KEY `MSCS_APP_BDS_SCHEMAINFOR` (`APPLICATION_ID`),
  CONSTRAINT `MSCS_APP_BDS_SCHEMAINFOR` FOREIGN KEY (`APPLICATION_ID`) REFERENCES `mstb_application` (`APPLICATION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mstb_bds_xml_data`
--

DROP TABLE IF EXISTS `mstb_bds_xml_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mstb_bds_xml_data` (
  `BDS_XML_DATA_ID` varchar(45) NOT NULL,
  `BDS_SCHEMAINFOR_ID` varchar(45) default NULL,
  `CODE` varchar(100) default NULL,
  `DISPLAYNAME` varchar(100) default NULL,
  `DISPLAYNAME_EN` varchar(100) default NULL,
  `DISPLAYNAME_TC` varchar(100) default NULL,
  `BDS_DATA` text,
  `REMARK` varchar(2000) default NULL,
  `STATE` int(11) NOT NULL,
  `RECORD_STATE` int(11) NOT NULL,
  `CREATED_USER_ID` varchar(32) default NULL,
  `CREATED_TIME` datetime default NULL,
  `UPDATED_USER_ID` varchar(32) default NULL,
  `UPDATED_TIME` datetime default NULL,
  `BDS_DATA_STR` text,
  PRIMARY KEY  (`BDS_XML_DATA_ID`),
  KEY `MSCS_SCHEMAINFOR_XML_DATA` (`BDS_SCHEMAINFOR_ID`),
  CONSTRAINT `MSCS_SCHEMAINFOR_XML_DATA` FOREIGN KEY (`BDS_SCHEMAINFOR_ID`) REFERENCES `mstb_bds_schemainfor` (`BDS_SCHEMAINFOR_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mstb_blacklist`
--

DROP TABLE IF EXISTS `mstb_blacklist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mstb_blacklist` (
  `BLACKLIST_ID` varchar(45) NOT NULL,
  `APPLICATION_ID` varchar(45) default NULL,
  `USERPROFILE_ID` varchar(45) default NULL,
  `BLACKLIST_TIME` datetime default NULL,
  `REMARK` varchar(2048) default NULL,
  `STATE` int(11) NOT NULL,
  `RECORD_STATE` int(11) NOT NULL,
  `CREATED_USER_ID` varchar(32) default NULL,
  `CREATED_TIME` datetime default NULL,
  `UPDATED_USER_ID` varchar(32) default NULL,
  `UPDATED_TIME` datetime default NULL,
  PRIMARY KEY  (`BLACKLIST_ID`),
  KEY `FKCA3E4D12138B0970` (`USERPROFILE_ID`),
  KEY `FKCA3E4D12D3784850` (`APPLICATION_ID`),
  CONSTRAINT `FKCA3E4D12138B0970` FOREIGN KEY (`USERPROFILE_ID`) REFERENCES `mstb_userprofile` (`USERPROFILE_ID`),
  CONSTRAINT `FKCA3E4D12D3784850` FOREIGN KEY (`APPLICATION_ID`) REFERENCES `mstb_application` (`APPLICATION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mstb_city`
--

DROP TABLE IF EXISTS `mstb_city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mstb_city` (
  `ID` int(11) NOT NULL,
  `CITY_CODE` varchar(10) default NULL,
  `CITY_NAME_EN` varchar(100) default NULL,
  `CITY_NAME_CN` varchar(100) default NULL,
  `PRV_CODE` varchar(10) default NULL,
  `CREATE_USER` varchar(20) default NULL,
  `CREATE_DATE` datetime default NULL,
  `EDIT_USER` varchar(20) default NULL,
  `EDIT_DATE` datetime default NULL,
  `STATUS` int(11) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `MSFK_CITY_0` (`CITY_CODE`),
  KEY `MSFK_CITY_1` (`PRV_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mstb_com_fa_dept`
--

DROP TABLE IF EXISTS `mstb_com_fa_dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mstb_com_fa_dept` (
  `DEPT_ID` varchar(100) NOT NULL,
  `DEPT_ADMIN_EMP` varchar(10) NOT NULL,
  `DEPT_ADMIN_EMP_NAME` varchar(50) NOT NULL,
  `DEPT_NAME_CN` varchar(200) NOT NULL,
  `DEPT_FIN_CODE` varchar(10) default NULL,
  `DEPT_TYPE` varchar(10) NOT NULL,
  `REGION_CODE` varchar(10) NOT NULL,
  `REGION_NAME_CN` varchar(200) default NULL,
  `REGION_NAME_EN` varchar(100) default NULL,
  `DEPTHEAD_PROVINCE_EMP_NUM` varchar(10) default NULL,
  `DEPTHEAD_PROVINCE_EMP_NAME` varchar(50) default NULL,
  `AD_APPROVER_EMP_NUM` varchar(10) default NULL,
  `AD_APPROVER_EMP_NAME` varchar(50) default NULL,
  `AD_SUPERVISOR_EMP_NUM` varchar(10) default NULL,
  `AD_SUPERVISOR_EMP_NAME` varchar(50) default NULL,
  `CO_ID` varchar(10) default NULL,
  `CO_NAME` varchar(200) default NULL,
  PRIMARY KEY  (`DEPT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mstb_department`
--

DROP TABLE IF EXISTS `mstb_department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mstb_department` (
  `DEPARTMENT_ID` varchar(45) NOT NULL,
  `UNIT_CODE` varchar(10) NOT NULL,
  `CITY_CODE` varchar(10) default NULL,
  `DEPT_TYPE` varchar(10) default NULL,
  `Z_DFUNC_DESC_ZHS` varchar(100) default NULL,
  `Z_DFUNC_DESC_ENG` varchar(100) default NULL,
  `DEPT_NAME_CN` varchar(100) default NULL,
  `DEPT_NAME_EN` varchar(100) default NULL,
  `DEPT_FIN_CODE` varchar(10) default NULL,
  `IS_FUNCTIONAL` varchar(1) default NULL,
  `DEPT_STATUS` varchar(10) default NULL,
  `DEPT_UPDATE_DT` datetime default NULL,
  `BUSINESS_UNIT` varchar(10) default NULL,
  `EFFDT` datetime default NULL,
  `MANAGER_ID` varchar(10) default NULL,
  `OFFICER_CD` varchar(1) default NULL,
  `XLATLONGNAME` varchar(30) default NULL,
  `SAL_ADMIN_PLAN` varchar(4) default NULL,
  `PARENT_NODE_NAME` varchar(10) default NULL,
  `TREE_LEVEL_NUM` int(11) default NULL,
  `Z_LOCATION_ID` varchar(5) default NULL,
  `Z_LOCATION_DESCR` varchar(100) default NULL,
  `CITY` varchar(10) default NULL,
  `COMPANY` varchar(10) default NULL,
  `LOCATION` varchar(10) default NULL,
  `Z_TAX_CENTER` varchar(10) default NULL,
  `DESCR` varchar(200) default NULL,
  `Z_DEPT_ATTR_ID` varchar(5) default NULL,
  `Z_DEPT_FUNCTION` varchar(3) default NULL,
  `RECORD_STATE` int(11) NOT NULL,
  `CREATED_USER_ID` varchar(32) default NULL,
  `CREATED_TIME` datetime default NULL,
  `UPDATED_USER_ID` varchar(32) default NULL,
  `UPDATED_TIME` datetime default NULL,
  PRIMARY KEY  (`DEPARTMENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mstb_email_asyn_record`
--

DROP TABLE IF EXISTS `mstb_email_asyn_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mstb_email_asyn_record` (
  `EMAIL_SEND_RECORD_ID` varchar(45) NOT NULL,
  `HOST` varchar(30) default NULL,
  `PERSONAL_NAME` varchar(200) default NULL,
  `MAIL_FROM_ADD` varchar(30) default NULL,
  `MAIL_TO_ADD` varchar(200) NOT NULL,
  `MAIL_CC_ADD` varchar(200) default NULL,
  `MAIL_BCC_ADD` varchar(200) default NULL,
  `TEMPLATES_ID` varchar(100) default NULL,
  `EMAIL_SUBJECT` varchar(800) default NULL,
  `EMAIL_CONTENT` varchar(3000) default NULL,
  `ACCESSORY` varchar(200) default NULL,
  `SEND_FLAG` int(11) NOT NULL,
  `SEND_NUM` int(11) NOT NULL,
  `SEND_DATE` datetime default NULL,
  `REMARK` varchar(2000) default NULL,
  `STATE` int(11) NOT NULL,
  `RECORD_STATE` int(11) NOT NULL,
  `CREATED_USER_ID` varchar(32) default NULL,
  `CREATED_TIME` datetime default NULL,
  `UPDATED_USER_ID` varchar(32) default NULL,
  `UPDATED_TIME` datetime default NULL,
  PRIMARY KEY  (`EMAIL_SEND_RECORD_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mstb_email_templates`
--

DROP TABLE IF EXISTS `mstb_email_templates`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mstb_email_templates` (
  `EMAIL_TEMPLATES_ID` varchar(45) NOT NULL,
  `TEMPLATES_CODE` varchar(128) default NULL,
  `TEMPLATES_NAME` varchar(256) default NULL,
  `EMAIL_TEMPLATES_SUBJECT` varchar(200) default NULL,
  `EMAIL_TEMPLATES_CONTENT` varchar(3000) default NULL,
  `APPLICATION_ID` varchar(45) default NULL,
  `ACCESSORY_FLAG` int(11) default NULL,
  `SEND_FLAG` int(11) default NULL,
  `REMARK` varchar(2048) default NULL,
  `STATE` int(11) NOT NULL,
  `RECORD_STATE` int(11) NOT NULL,
  `CREATED_USER_ID` varchar(32) default NULL,
  `CREATED_TIME` datetime default NULL,
  `UPDATED_USER_ID` varchar(32) default NULL,
  `UPDATED_TIME` datetime default NULL,
  PRIMARY KEY  (`EMAIL_TEMPLATES_ID`),
  KEY `MSCS_APP_EMAIL_TEMPLATES` (`APPLICATION_ID`),
  CONSTRAINT `MSCS_APP_EMAIL_TEMPLATES` FOREIGN KEY (`APPLICATION_ID`) REFERENCES `mstb_application` (`APPLICATION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mstb_exception`
--

DROP TABLE IF EXISTS `mstb_exception`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mstb_exception` (
  `EXCEPTION_ID` varchar(45) NOT NULL,
  `EXCEPTION_CODE` varchar(128) default NULL,
  `EXCEPTION_NAME` varchar(256) default NULL,
  `APPLICATION_ID` varchar(45) default NULL,
  `IS_SEND_EMAIL` int(11) default NULL,
  `EMAIL_USERS` varchar(1000) default NULL,
  `USE_STATE` int(11) default NULL,
  `REMARK` varchar(2048) default NULL,
  `RECORD_STATE` int(11) NOT NULL,
  `CREATED_USER_ID` varchar(32) default NULL,
  `CREATED_TIME` datetime default NULL,
  `UPDATED_USER_ID` varchar(32) default NULL,
  `UPDATED_TIME` datetime default NULL,
  PRIMARY KEY  (`EXCEPTION_ID`),
  KEY `FK_MSTB_EXC_MSCS_APPL_MSTB_APP` (`APPLICATION_ID`),
  CONSTRAINT `FK_MSTB_EXC_MSCS_APPL_MSTB_APP` FOREIGN KEY (`APPLICATION_ID`) REFERENCES `mstb_application` (`APPLICATION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mstb_kgem_knowledge`
--

DROP TABLE IF EXISTS `mstb_kgem_knowledge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mstb_kgem_knowledge` (
  `KNOWLEDGE_ID` varchar(45) NOT NULL,
  `TITLE` varchar(200) default NULL,
  `CONTENT` varchar(2000) default NULL,
  `REMARK` varchar(500) default NULL,
  `RECORD_STATE` int(11) NOT NULL,
  `CREATED_USER_ID` varchar(32) default NULL,
  `CREATED_TIME` datetime default NULL,
  `UPDATED_USER_ID` varchar(32) default NULL,
  `UPDATED_TIME` datetime default NULL,
  PRIMARY KEY  (`KNOWLEDGE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mstb_module`
--

DROP TABLE IF EXISTS `mstb_module`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mstb_module` (
  `MODULE_ID` varchar(45) NOT NULL,
  `MODULE_CODE` varchar(50) NOT NULL,
  `MODULE_NAME` varchar(100) NOT NULL,
  `PARENT_MODULE_ID` varchar(45) NOT NULL,
  `IS_MODULE_OR_MENU` int(11) default NULL,
  `APPLICATION_ID` varchar(45) NOT NULL,
  `ICO` varchar(100) default NULL,
  `LINK` varchar(1000) default NULL,
  `ORDER_NO` varchar(100) default NULL,
  `IS_SEED` int(11) default NULL,
  `OPEN_TYPE` int(11) default NULL,
  `LEVEL_NO` int(11) default NULL,
  `TARGET` varchar(30) default NULL,
  `REMARK` varchar(2000) default NULL,
  `STATE` int(11) NOT NULL,
  `RECORD_STATE` int(11) NOT NULL,
  `CREATED_USER_ID` varchar(32) default NULL,
  `CREATED_TIME` datetime default NULL,
  `UPDATED_USER_ID` varchar(32) default NULL,
  `UPDATED_TIME` datetime default NULL,
  PRIMARY KEY  (`MODULE_ID`),
  UNIQUE KEY `MSCS_APPLICATION_RIGHT21222211` (`MODULE_ID`,`MODULE_CODE`,`MODULE_NAME`),
  KEY `MSCS_APPLICATION_RIGHT` (`APPLICATION_ID`),
  CONSTRAINT `MSCS_APPLICATION_RIGHT` FOREIGN KEY (`APPLICATION_ID`) REFERENCES `mstb_application` (`APPLICATION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mstb_province`
--

DROP TABLE IF EXISTS `mstb_province`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mstb_province` (
  `ID` int(11) NOT NULL,
  `PRV_CODE` varchar(10) default NULL,
  `PRV_NAME_EN` varchar(100) default NULL,
  `PRV_NAME_CN` varchar(100) default NULL,
  `REGION_CODE` varchar(10) default NULL,
  `CREATE_USER` varchar(20) default NULL,
  `CREATE_DATE` datetime default NULL,
  `EDIT_USER` varchar(20) default NULL,
  `EDIT_DATE` datetime default NULL,
  `STATUS` int(11) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `MSFK_PROVINCE_1` (`PRV_CODE`),
  KEY `MSFK_PROVINCE_2` (`REGION_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mstb_query_button`
--

DROP TABLE IF EXISTS `mstb_query_button`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mstb_query_button` (
  `BUTTON_ID` varchar(45) NOT NULL,
  `QUERY_ID` varchar(45) default NULL,
  `BUTTON_NO` varchar(100) default NULL,
  `BUTTON_NAME` varchar(200) default NULL,
  `SUB_URL` varchar(500) default NULL,
  `EXECUTE_JS` varchar(2000) default NULL,
  `ORDER_NO` int(11) default NULL,
  `RECORD_STATE` int(11) NOT NULL,
  `CREATED_USER_ID` varchar(32) default NULL,
  `CREATED_TIME` datetime default NULL,
  `UPDATED_USER_ID` varchar(32) default NULL,
  `UPDATED_TIME` datetime default NULL,
  `OPEN_TYPE` int(11) default NULL,
  PRIMARY KEY  (`BUTTON_ID`),
  KEY `MSCS_QUERY_BUTTON` (`QUERY_ID`),
  CONSTRAINT `MSCS_QUERY_BUTTON` FOREIGN KEY (`QUERY_ID`) REFERENCES `mstb_query_index` (`QUERY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mstb_query_from`
--

DROP TABLE IF EXISTS `mstb_query_from`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mstb_query_from` (
  `FROM_ID` varchar(45) NOT NULL,
  `QUERY_ID` varchar(45) default NULL,
  `TABLE_NAME` varchar(100) default NULL,
  `TABLE_ALIAS` varchar(100) default NULL,
  `ORDER_NO` int(11) default NULL,
  `FROM_RESULT` varchar(200) default NULL,
  `RECORD_STATE` int(11) NOT NULL,
  `CREATED_USER_ID` varchar(32) default NULL,
  `CREATED_TIME` datetime default NULL,
  `UPDATED_USER_ID` varchar(32) default NULL,
  `UPDATED_TIME` datetime default NULL,
  PRIMARY KEY  (`FROM_ID`),
  KEY `MSCS_QUERY_FROM` (`QUERY_ID`),
  CONSTRAINT `MSCS_QUERY_FROM` FOREIGN KEY (`QUERY_ID`) REFERENCES `mstb_query_index` (`QUERY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mstb_query_groupby`
--

DROP TABLE IF EXISTS `mstb_query_groupby`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mstb_query_groupby` (
  `GROUPBY_ID` varchar(45) NOT NULL,
  `QUERY_ID` varchar(45) default NULL,
  `TABLE_NAME` varchar(100) default NULL,
  `FIELD_NAME` varchar(100) default NULL,
  `ORDER_NO` int(11) default NULL,
  `RECORD_STATE` int(11) NOT NULL,
  `CREATED_USER_ID` varchar(32) default NULL,
  `CREATED_TIME` datetime default NULL,
  `UPDATED_USER_ID` varchar(32) default NULL,
  `UPDATED_TIME` datetime default NULL,
  PRIMARY KEY  (`GROUPBY_ID`),
  KEY `MSCS_QUERY_GROUP` (`QUERY_ID`),
  CONSTRAINT `MSCS_QUERY_GROUP` FOREIGN KEY (`QUERY_ID`) REFERENCES `mstb_query_index` (`QUERY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mstb_query_index`
--

DROP TABLE IF EXISTS `mstb_query_index`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mstb_query_index` (
  `QUERY_ID` varchar(45) NOT NULL,
  `QUERY_CODE` varchar(100) default NULL,
  `QUERY_NAME` varchar(200) default NULL,
  `APPLICATION_ID` varchar(45) default NULL,
  `DS_JNDI` varchar(100) default NULL,
  `IS_COL_HEA_FIL` int(11) default NULL,
  `EXTERNAL_JS` varchar(1000) default NULL,
  `IS_DEFAULT_QRY` int(11) default NULL,
  `GLOBAL_WHERE` varchar(1000) default NULL,
  `REMARE` varchar(2000) default NULL,
  `USE_STATE` int(11) NOT NULL,
  `RECORD_STATE` int(11) NOT NULL,
  `CREATED_USER_ID` varchar(32) default NULL,
  `CREATED_TIME` datetime default NULL,
  `UPDATED_USER_ID` varchar(32) default NULL,
  `UPDATED_TIME` datetime default NULL,
  `IS_REFRESH` int(11) default NULL,
  PRIMARY KEY  (`QUERY_ID`),
  KEY `MSCS_APPLICATION_QUERY` (`APPLICATION_ID`),
  CONSTRAINT `MSCS_APPLICATION_QUERY` FOREIGN KEY (`APPLICATION_ID`) REFERENCES `mstb_application` (`APPLICATION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mstb_query_orderby`
--

DROP TABLE IF EXISTS `mstb_query_orderby`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mstb_query_orderby` (
  `ORDERBY_ID` varchar(45) NOT NULL,
  `QUERY_ID` varchar(45) default NULL,
  `TABLE_NAME` varchar(100) default NULL,
  `FIELD_NAME` varchar(100) default NULL,
  `ORDER_BY` int(11) default NULL,
  `ORDER_NO` int(11) default NULL,
  `RECORD_STATE` int(11) NOT NULL,
  `CREATED_USER_ID` varchar(32) default NULL,
  `CREATED_TIME` datetime default NULL,
  `UPDATED_USER_ID` varchar(32) default NULL,
  `UPDATED_TIME` datetime default NULL,
  PRIMARY KEY  (`ORDERBY_ID`),
  KEY `MSCS_QUERY_ORDERBY` (`QUERY_ID`),
  CONSTRAINT `MSCS_QUERY_ORDERBY` FOREIGN KEY (`QUERY_ID`) REFERENCES `mstb_query_index` (`QUERY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mstb_query_select`
--

DROP TABLE IF EXISTS `mstb_query_select`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mstb_query_select` (
  `SELECT_ID` varchar(45) NOT NULL,
  `QUERY_ID` varchar(45) default NULL,
  `TABLE_NAME` varchar(100) default NULL,
  `FIELD_NAME` varchar(100) default NULL,
  `FIELD_ALIAS` varchar(100) default NULL,
  `DATA_TYPE` varchar(100) default NULL,
  `DES` varchar(200) default NULL,
  `ORDER_NO` int(11) default NULL,
  `DATA_CODING` varchar(100) default NULL,
  `OUT_FORMAT` varchar(100) default NULL,
  `BTN_TYPE` int(11) default NULL,
  `IS_HIDDEN` int(11) default NULL,
  `DATA_INPUT` varchar(500) default NULL,
  `OPEN_TYPE` int(11) default NULL,
  `LINK_URL` varchar(500) default NULL,
  `SELECT_RESULT` varchar(500) default NULL,
  `COL_WIDTH` varchar(50) default NULL,
  `RECORD_STATE` int(11) NOT NULL,
  `CREATED_USER_ID` varchar(32) default NULL,
  `CREATED_TIME` datetime default NULL,
  `UPDATED_USER_ID` varchar(32) default NULL,
  `UPDATED_TIME` datetime default NULL,
  PRIMARY KEY  (`SELECT_ID`),
  KEY `MSCS_QUERY_SELECT` (`QUERY_ID`),
  CONSTRAINT `MSCS_QUERY_SELECT` FOREIGN KEY (`QUERY_ID`) REFERENCES `mstb_query_index` (`QUERY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mstb_query_where`
--

DROP TABLE IF EXISTS `mstb_query_where`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mstb_query_where` (
  `WHERE_ID` varchar(45) NOT NULL,
  `QUERY_ID` varchar(45) default NULL,
  `WHERE_CODE` varchar(100) default NULL,
  `TABLE_NAME` varchar(100) default NULL,
  `FIELD_NAME` varchar(100) default NULL,
  `WHERE_RESULT` varchar(400) default NULL,
  `DATA_TYPE` varchar(100) default NULL,
  `DES` varchar(200) default NULL,
  `ORDER_NO` int(11) default NULL,
  `IS_USER_IN` int(11) default NULL,
  `IS_INNER_LINK` int(11) default NULL,
  `CONTROL_TYPE` int(11) default NULL,
  `DATA_CODING` varchar(100) default NULL,
  `OPERATOR` int(11) default NULL,
  `DEFAULT_VALUE` varchar(200) default NULL,
  `LINK_TABLE` varchar(100) default NULL,
  `LINK_FIELD` varchar(100) default NULL,
  `FILTER_FIELD` varchar(100) default NULL,
  `IS_REQUIRED` int(11) default NULL,
  `REG_EXP` varchar(500) default NULL,
  `PAR_TAG_ID` varchar(100) default NULL,
  `IS_IGNORE_CASE` int(11) default NULL,
  `RECORD_STATE` int(11) NOT NULL,
  `CREATED_USER_ID` varchar(32) default NULL,
  `CREATED_TIME` datetime default NULL,
  `UPDATED_USER_ID` varchar(32) default NULL,
  `UPDATED_TIME` datetime default NULL,
  PRIMARY KEY  (`WHERE_ID`),
  KEY `MSCS_QUERY_WHERE` (`QUERY_ID`),
  CONSTRAINT `MSCS_QUERY_WHERE` FOREIGN KEY (`QUERY_ID`) REFERENCES `mstb_query_index` (`QUERY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mstb_region`
--

DROP TABLE IF EXISTS `mstb_region`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mstb_region` (
  `ID` int(11) NOT NULL,
  `REGION_CODE` varchar(10) default NULL,
  `REGION_NAME_EN` varchar(100) default NULL,
  `REGION_NAME_CN` varchar(100) default NULL,
  `REGION_NAME_ABBR` varchar(10) default NULL,
  `IS_COUNTRY` char(1) default NULL,
  `CREATE_USER` varchar(20) default NULL,
  `CREATE_DATE` datetime default NULL,
  `EDIT_USER` varchar(20) default NULL,
  `EDIT_DATE` datetime default NULL,
  `STATUS` int(11) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `MSFK_REGION_1` (`REGION_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mstb_report_cache`
--

DROP TABLE IF EXISTS `mstb_report_cache`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mstb_report_cache` (
  `REPORT_CACHE_ID` varchar(45) NOT NULL,
  `REPORT_CODE` varchar(100) default NULL,
  `REPORT_PARAS` varchar(2000) default NULL,
  `FILE_PATH` varchar(1000) default NULL,
  `FILE_R_PATH` varchar(1000) default NULL,
  `RECORD_STATE` int(11) NOT NULL,
  `CREATED_USER_ID` varchar(32) default NULL,
  `CREATED_TIME` datetime default NULL,
  `UPDATED_USER_ID` varchar(32) default NULL,
  `UPDATED_TIME` datetime default NULL,
  PRIMARY KEY  (`REPORT_CACHE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mstb_report_info`
--

DROP TABLE IF EXISTS `mstb_report_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mstb_report_info` (
  `REPORT_INFO_ID` varchar(45) NOT NULL,
  `REPORT_CODE` varchar(100) default NULL,
  `REPORT_NAME` varchar(500) default NULL,
  `APPLICATION_ID` varchar(45) NOT NULL,
  `REPORT_TYPE` int(11) default NULL,
  `REPORT_URL` varchar(500) default NULL,
  `REMARK` varchar(2000) default NULL,
  `STATE` int(11) NOT NULL,
  `RECORD_STATE` int(11) NOT NULL,
  `CREATED_USER_ID` varchar(32) default NULL,
  `CREATED_TIME` datetime default NULL,
  `UPDATED_USER_ID` varchar(32) default NULL,
  `UPDATED_TIME` datetime default NULL,
  PRIMARY KEY  (`REPORT_INFO_ID`),
  KEY `MSCS_APPLICATION_REPORT` (`APPLICATION_ID`),
  CONSTRAINT `MSCS_APPLICATION_REPORT` FOREIGN KEY (`APPLICATION_ID`) REFERENCES `mstb_application` (`APPLICATION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mstb_report_para`
--

DROP TABLE IF EXISTS `mstb_report_para`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mstb_report_para` (
  `REPORT_PARA_ID` varchar(45) NOT NULL,
  `REPORT_INFO_ID` varchar(45) default NULL,
  `DATA_TYPE` varchar(100) default NULL,
  `PARA_DISPLAY_NAME` varchar(200) default NULL,
  `DATA_DISPLAY_TYPE` varchar(100) default NULL,
  `PARA_NAME` varchar(100) default NULL,
  `DATA_CODING` varchar(200) default NULL,
  `PARA_ORDER` int(11) default NULL,
  `REMARK` varchar(2000) default NULL,
  `RECORD_STATE` int(11) NOT NULL,
  `CREATED_USER_ID` varchar(32) default NULL,
  `CREATED_TIME` datetime default NULL,
  `UPDATED_USER_ID` varchar(32) default NULL,
  `UPDATED_TIME` datetime default NULL,
  PRIMARY KEY  (`REPORT_PARA_ID`),
  KEY `MSCS_REPORT_INFO_PARA` (`REPORT_INFO_ID`),
  CONSTRAINT `MSCS_REPORT_INFO_PARA` FOREIGN KEY (`REPORT_INFO_ID`) REFERENCES `mstb_report_info` (`REPORT_INFO_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mstb_report_sql`
--

DROP TABLE IF EXISTS `mstb_report_sql`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mstb_report_sql` (
  `REPORT_SQL_ID` varchar(45) NOT NULL,
  `REPORT_INFO_ID` varchar(45) default NULL,
  `SQL_SELECT` varchar(2000) default NULL,
  `SQL_WHERE` varchar(2000) default NULL,
  `SQL_ORDER` varchar(1000) default NULL,
  `SQL_TYPE` varchar(2) default NULL,
  `MAP_KEY` varchar(100) default NULL,
  `REMARK` varchar(2000) default NULL,
  `RECORD_STATE` int(11) NOT NULL,
  `CREATED_USER_ID` varchar(32) default NULL,
  `CREATED_TIME` datetime default NULL,
  `UPDATED_USER_ID` varchar(32) default NULL,
  `UPDATED_TIME` datetime default NULL,
  PRIMARY KEY  (`REPORT_SQL_ID`),
  KEY `MSCS_REPORT_INFO_SQL` (`REPORT_INFO_ID`),
  CONSTRAINT `MSCS_REPORT_INFO_SQL` FOREIGN KEY (`REPORT_INFO_ID`) REFERENCES `mstb_report_info` (`REPORT_INFO_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mstb_role`
--

DROP TABLE IF EXISTS `mstb_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mstb_role` (
  `ROLE_ID` varchar(45) NOT NULL,
  `ROLE_CODE` varchar(128) NOT NULL,
  `ROLE_NAME` varchar(256) NOT NULL,
  `APPLICATION_ID` varchar(45) NOT NULL,
  `USER_SQL` varchar(1000) default NULL,
  `REMARK` varchar(2048) default NULL,
  `STATE` int(11) NOT NULL,
  `RECORD_STATE` int(11) NOT NULL,
  `CREATED_USER_ID` varchar(32) default NULL,
  `CREATED_TIME` datetime default NULL,
  `UPDATED_USER_ID` varchar(32) default NULL,
  `UPDATED_TIME` datetime default NULL,
  PRIMARY KEY  (`ROLE_ID`),
  KEY `FK18236B41D3784850` (`APPLICATION_ID`),
  CONSTRAINT `FK18236B41D3784850` FOREIGN KEY (`APPLICATION_ID`) REFERENCES `mstb_application` (`APPLICATION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mstb_role_right`
--

DROP TABLE IF EXISTS `mstb_role_right`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mstb_role_right` (
  `ROLE_RIGHT_ID` varchar(45) NOT NULL,
  `ROLE_ID` varchar(45) NOT NULL,
  `MODULE_ID` varchar(45) NOT NULL,
  `WORK_FLOW_TYPE` int(11) default NULL,
  `RECORD_STATE` int(11) NOT NULL,
  `CREATED_USER_ID` varchar(32) default NULL,
  `CREATED_TIME` datetime default NULL,
  `UPDATED_USER_ID` varchar(32) default NULL,
  `UPDATED_TIME` datetime default NULL,
  PRIMARY KEY  (`ROLE_RIGHT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mstb_role_user`
--

DROP TABLE IF EXISTS `mstb_role_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mstb_role_user` (
  `ROLE_USER_ID` varchar(32) NOT NULL,
  `USERPROFILE_ID` varchar(45) NOT NULL,
  `ROLE_ID` varchar(45) NOT NULL,
  `RECORD_STATE` int(11) NOT NULL,
  `CREATED_USER_ID` varchar(32) default NULL,
  `CREATED_TIME` datetime default NULL,
  `UPDATED_USER_ID` varchar(32) default NULL,
  `UPDATED_TIME` datetime default NULL,
  `IS_LOCAL_RIGHT` int(11) default '0',
  PRIMARY KEY  (`ROLE_USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mstb_shop_heads`
--

DROP TABLE IF EXISTS `mstb_shop_heads`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mstb_shop_heads` (
  `UNIT_CODE` varchar(10) default NULL,
  `SHOP_STATUS` varchar(10) default NULL,
  `SHOP_NAME_CN` varchar(100) default NULL,
  `SHOP_NAME_EN` varchar(100) default NULL,
  `SHOP_CODE` varchar(5) default NULL,
  `CITY_CODE` varchar(10) default NULL,
  `CITY_NAME_CN` varchar(100) default NULL,
  `CITY_NAME_EN` varchar(100) default NULL,
  `PRV_CODE` varchar(10) default NULL,
  `PRV_NAME_CN` varchar(100) default NULL,
  `PRV_NAME_EN` varchar(100) default NULL,
  `REGION_CODE` varchar(10) default NULL,
  `REGION_NAME_CN` varchar(100) default NULL,
  `REGION_NAME_EN` varchar(100) default NULL,
  `FIN_CODE` varchar(10) default NULL,
  `SHOP_HEAD` varchar(10) default NULL,
  `SHOP_HEAD_CN` varchar(30) default NULL,
  `SHOP_HEAD_EN` varchar(30) default NULL,
  `SHOP_HEAD_MAIL` varchar(100) default NULL,
  `SHOP_HEAD_MOBILE` varchar(50) default NULL,
  `SHOP_HEAD2` varchar(10) default NULL,
  `SHOP_HEAD2_CN` varchar(30) default NULL,
  `SHOP_HEAD2_EN` varchar(30) default NULL,
  `SHOP_HEAD2_MAIL` varchar(100) default NULL,
  `SHOP_HEAD2_MOBILE` varchar(50) default NULL,
  `CITY_HEAD` varchar(10) default NULL,
  `CITY_HEAD_CN` varchar(30) default NULL,
  `CITY_HEAD_EN` varchar(30) default NULL,
  `CITY_HEAD_MOBILE` varchar(50) default NULL,
  `PRV_HEAD1` varchar(10) default NULL,
  `PRV_HEAD1_CN` varchar(30) default NULL,
  `PRV_HEAD1_EN` varchar(30) default NULL,
  `PRV_HEAD1_MAIL` varchar(100) default NULL,
  `PRV_HEAD1_GRADE` varchar(5) default NULL,
  `PRV_HEAD1_MOBILE` varchar(50) default NULL,
  `PRV_HEAD2` varchar(10) default NULL,
  `PRV_HEAD2_CN` varchar(30) default NULL,
  `PRV_HEAD2_EN` varchar(30) default NULL,
  `PRV_HEAD2_MAIL` varchar(100) default NULL,
  `PRV_HEAD2_GRADE` varchar(5) default NULL,
  `PRV_HEAD2_MOBILE` varchar(50) default NULL,
  `PRV_HEAD3` varchar(10) default NULL,
  `PRV_HEAD3_CN` varchar(30) default NULL,
  `PRV_HEAD3_EN` varchar(30) default NULL,
  `PRV_HEAD3_MAIL` varchar(100) default NULL,
  `PRV_HEAD3_GRADE` varchar(5) default NULL,
  `PRV_HEAD3_MOBILE` varchar(50) default NULL,
  `PRV_HEAD4` varchar(10) default NULL,
  `PRV_HEAD4_CN` varchar(30) default NULL,
  `PRV_HEAD4_EN` varchar(30) default NULL,
  `PRV_HEAD4_MAIL` varchar(100) default NULL,
  `PRV_HEAD4_GRADE` varchar(5) default NULL,
  `PRV_HEAD4_MOBILE` varchar(50) default NULL,
  `REGION_HEAD` varchar(10) default NULL,
  `REGION_HEAD_CN` varchar(30) default NULL,
  `REGION_HEAD_EN` varchar(30) default NULL,
  `REGION_HEAD_EMAIL` varchar(100) default NULL,
  `REGION_HEAD_MOBILE` varchar(50) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mstb_shop_info`
--

DROP TABLE IF EXISTS `mstb_shop_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mstb_shop_info` (
  `UNIT_CODE` varchar(10) NOT NULL,
  `SHOP_STATUS` varchar(10) default NULL,
  `SHOP_NAME_CN` varchar(100) default NULL,
  `SHOP_NAME_EN` varchar(100) default NULL,
  `SHOP_NAME_OLD` varchar(100) default NULL,
  `SHOP_FORMAT` varchar(50) default NULL,
  `SHOP_SETUP_DATE` datetime default NULL,
  `SHOP_WORK_HOUR_DAY` varchar(50) default NULL,
  `SHOP_WORK_HOUR_MONTH` varchar(50) default NULL,
  `SHOP_PHOTO` blob,
  `SHOP_WAREHOUSE_CODE` varchar(10) default NULL,
  `SHOP_OFFICE_HOUR` varchar(50) default NULL,
  `SHOP_REST_DATE` varchar(50) default NULL,
  `SHOP_ADDRESS` varchar(500) default NULL,
  `SHOP_FAX_NUM` varchar(20) default NULL,
  `SHOP_SPR_TEL_NUM` varchar(50) default NULL,
  `SHOP_SRV_TEL_NUM` varchar(50) default NULL,
  `SHOP_ZIP_CODE` varchar(10) default NULL,
  `SHOP_EMAIL` varchar(100) default NULL,
  `SHOP_CODE` varchar(5) default NULL,
  PRIMARY KEY  (`UNIT_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mstb_shop_staff`
--

DROP TABLE IF EXISTS `mstb_shop_staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mstb_shop_staff` (
  `SHOP_STAFF_ID` varchar(50) default NULL,
  `INFO_CODE` varchar(20) default NULL,
  `STAFF_TYPE_SEQ` varchar(20) default NULL,
  `STAFF_TYPE` varchar(20) default NULL,
  `ENGLISH_NAME` varchar(30) default NULL,
  `CHINESE_NAME` varchar(30) default NULL,
  `EMP_NUMBER` varchar(20) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mstb_sms_templates`
--

DROP TABLE IF EXISTS `mstb_sms_templates`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mstb_sms_templates` (
  `SMS_TEMPLATES_ID` varchar(45) NOT NULL,
  `TEMPLATES_CODE` varchar(128) default NULL,
  `SMS_TEMPLATES_CONTENT` varchar(1000) default NULL,
  `APPLICATION_ID` varchar(45) default NULL,
  `REMARK` varchar(2048) default NULL,
  `STATE` int(11) NOT NULL,
  `RECORD_STATE` int(11) NOT NULL,
  `CREATED_USER_ID` varchar(32) default NULL,
  `CREATED_TIME` datetime default NULL,
  `UPDATED_USER_ID` varchar(32) default NULL,
  `UPDATED_TIME` datetime default NULL,
  PRIMARY KEY  (`SMS_TEMPLATES_ID`),
  KEY `MSCS_APPLICATION_SMS_TEMPLATES` (`APPLICATION_ID`),
  CONSTRAINT `MSCS_APPLICATION_SMS_TEMPLATES` FOREIGN KEY (`APPLICATION_ID`) REFERENCES `mstb_application` (`APPLICATION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mstb_tag_fileupload`
--

DROP TABLE IF EXISTS `mstb_tag_fileupload`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mstb_tag_fileupload` (
  `UUID` varchar(100) NOT NULL,
  `BIZ_ID` int(11) default NULL,
  `FILE_ENCRYPT_NAME` varchar(100) default NULL,
  `CN_NAME` varchar(200) default NULL,
  `SAVE_PATH` varchar(200) NOT NULL,
  `MODULE_ID` varchar(50) NOT NULL,
  `APPLICATION_ID` varchar(45) NOT NULL,
  `UPLOAD_USER` varchar(100) NOT NULL,
  `FILE_TYPE` varchar(100) default NULL,
  `REMARK` varchar(2000) default NULL,
  `STATE` int(11) NOT NULL,
  `RECORD_STATE` int(11) NOT NULL,
  `CREATED_USER_ID` varchar(32) default NULL,
  `CREATED_TIME` datetime default NULL,
  `UPDATED_USER_ID` varchar(32) default NULL,
  `UPDATED_TIME` datetime default NULL,
  PRIMARY KEY  (`UUID`),
  KEY `MSCS_APPLICATION_FILE` (`APPLICATION_ID`),
  KEY `MSPK_BIZ_ID` (`BIZ_ID`),
  CONSTRAINT `MSCS_APPLICATION_FILE` FOREIGN KEY (`APPLICATION_ID`) REFERENCES `mstb_application` (`APPLICATION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mstb_timeing_job`
--

DROP TABLE IF EXISTS `mstb_timeing_job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mstb_timeing_job` (
  `TIMEING_JOB_ID` varchar(45) NOT NULL,
  `TIMEING_JOB_CODE` varchar(100) default NULL,
  `TIMEING_JOB_NAME` varchar(200) default NULL,
  `APPLICATION_ID` varchar(45) default NULL,
  `CLASS_NAME` varchar(100) default NULL,
  `METHOD_NAME` varchar(100) default NULL,
  `START_TIME` datetime default NULL,
  `END_TIME` datetime default NULL,
  `CYCLE` int(11) default NULL,
  `CYCLE_UNIT` int(11) default NULL,
  `CRON_JOB_BUNCH` varchar(500) default NULL,
  `FAIL_NUM` int(11) default NULL,
  `LAST_START_TIME` datetime default NULL,
  `LAST_END_TIME` datetime default NULL,
  `RUN_TIME` int(11) default NULL,
  `LAST_FAIL_NUM` int(11) default NULL,
  `RUN_STATE` int(11) default NULL,
  `REMARK` varchar(2000) default NULL,
  `STATE` int(11) NOT NULL,
  `RECORD_STATE` int(11) NOT NULL,
  `CREATED_USER_ID` varchar(32) default NULL,
  `CREATED_TIME` datetime default NULL,
  `UPDATED_USER_ID` varchar(32) default NULL,
  `UPDATED_TIME` datetime default NULL,
  PRIMARY KEY  (`TIMEING_JOB_ID`),
  KEY `MSCS_APPLICATION_JOB` (`APPLICATION_ID`),
  CONSTRAINT `MSCS_APPLICATION_JOB` FOREIGN KEY (`APPLICATION_ID`) REFERENCES `mstb_application` (`APPLICATION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mstb_userprofile`
--

DROP TABLE IF EXISTS `mstb_userprofile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mstb_userprofile` (
  `USERPROFILE_ID` varchar(45) NOT NULL,
  `EMP_ID` varchar(30) default NULL,
  `PASSWORD` varchar(60) default NULL,
  `ACCOUNT_TYPE` int(11) default NULL,
  `CHINESE_NAME` varchar(100) default NULL,
  `DATE_HIRED` datetime default NULL,
  `DATE_TERMINATED` datetime default NULL,
  `GENDER` varchar(12) default NULL,
  `ENGLISH_NAME` varchar(100) default NULL,
  `DATE_BIRTHDAY` datetime default NULL,
  `PROVINCIAL_ADDR` varchar(200) default NULL,
  `NATIVE_PLACE` varchar(300) default NULL,
  `WORK_CITY` varchar(100) default NULL,
  `GRADE_CODE` varchar(3) default NULL,
  `CHINESE_POSITION` varchar(100) default NULL,
  `ENGLISH_POSITION` varchar(100) default NULL,
  `WORK_PROVINCE` varchar(100) default NULL,
  `ORG_UNIT_CODE` varchar(30) default NULL,
  `EMP_NUMBER` varchar(30) NOT NULL,
  `EMAIL_ADDR` varchar(200) default NULL,
  `JOB_GRADE` varchar(15) default NULL,
  `PHONE` varchar(100) default NULL,
  `REPORT_LINE` varchar(30) default NULL,
  `LOCATION` varchar(100) default NULL,
  `REMARK` varchar(2048) default NULL,
  `STATE` int(11) NOT NULL,
  `RECORD_STATE` int(11) NOT NULL,
  `CREATED_USER_ID` varchar(32) default NULL,
  `CREATED_TIME` datetime default NULL,
  `UPDATED_USER_ID` varchar(32) default NULL,
  `UPDATED_TIME` datetime default NULL,
  `SESSION_ID` varchar(100) default NULL,
  `LAST_LOGIN_TIME` datetime default NULL,
  `LAST_LOGIN_IP` varchar(20) default NULL,
  `FAIL_NUM` int(11) default NULL,
  `LOGIN_IP` varchar(20) default NULL,
  `LOGIN_NUM` int(11) default NULL,
  `LOGIN_TIME` datetime default NULL,
  PRIMARY KEY  (`USERPROFILE_ID`),
  KEY `I_USERPROFILE_EMPNUMBER` (`EMP_NUMBER`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_inf_model`
--

DROP TABLE IF EXISTS `t_inf_model`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_inf_model` (
  `CLIENT_REQ_URL` varchar(100) NOT NULL,
  `LOG_REQ` char(1) NOT NULL,
  `LOG_RES` char(1) NOT NULL,
  PRIMARY KEY  (`CLIENT_REQ_URL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-05-11 19:45:43
