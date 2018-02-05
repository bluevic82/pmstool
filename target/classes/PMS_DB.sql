-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: project_manager_system
-- ------------------------------------------------------
-- Server version	5.7.20-log

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
-- Table structure for table `anser_question`
--

DROP TABLE IF EXISTS `anser_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `anser_question` (
  `PROJECT_ID` int(4) NOT NULL,
  `Q_A_ID` int(4) NOT NULL AUTO_INCREMENT,
  `Q_A_TITLE` varchar(200) CHARACTER SET utf8 NOT NULL,
  `REFERENCEPOINT` varchar(250) CHARACTER SET utf8 NOT NULL,
  `Q_A_QUESTION_VI` varchar(2000) CHARACTER SET utf8 NOT NULL,
  `Q_A_QUESTION_JP` varchar(2000) CHARACTER SET utf8 DEFAULT NULL,
  `Q_A_ANSWER_VI` varchar(2000) CHARACTER SET utf8 DEFAULT NULL,
  `Q_A_ANSWER_JP` varchar(2000) CHARACTER SET utf8 DEFAULT NULL,
  `MEMBER_PROJECT_ID` int(4) NOT NULL,
  `STATUS_ID` tinyint(2) NOT NULL,
  `MEMBER_FROM` int(4) NOT NULL,
  `Q_A_DEALINE` date NOT NULL,
  PRIMARY KEY (`Q_A_ID`,`PROJECT_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `anser_question`
--

LOCK TABLES `anser_question` WRITE;
/*!40000 ALTER TABLE `anser_question` DISABLE KEYS */;
INSERT INTO `anser_question` VALUES (2,15,'don\'t undersatand ','8888','aaa bbb ccc xxx yyy zzz?5','???h????? ?','ccc xxx yyy zzz!','kkkk',1,0,0,'2017-12-26'),(1,16,'Screen than more size','sss','ss','ss','ss','sss',1,9,0,'2017-12-27'),(4,17,'How to learn uk language','SSS','SS','SS','SS','SSS',19,0,0,'2017-12-27');
/*!40000 ALTER TABLE `anser_question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `CATEGORY_ID` tinyint(2) NOT NULL AUTO_INCREMENT,
  `CATEGORY_NAME` varchar(20) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`CATEGORY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'UI'),(2,'Design'),(3,'UT'),(4,'IT'),(5,'Logic'),(6,'Environment'),(7,'CR'),(8,'Other');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detail_timesheet`
--

DROP TABLE IF EXISTS `detail_timesheet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `detail_timesheet` (
  `DETAIL_TIMESHEET_ID` int(6) NOT NULL AUTO_INCREMENT,
  `DETAIL_TIMESHEET_DATE` date NOT NULL,
  `HOUR` decimal(2,1) NOT NULL,
  `PRE_DEFINED_ID` tinyint(2) NOT NULL,
  `PROCESS_ID` tinyint(2) NOT NULL,
  `TYPE_ID` tinyint(2) NOT NULL,
  `TASK_ID` int(6) DEFAULT NULL,
  `WORKCONTENT` varchar(500) CHARACTER SET utf8 DEFAULT NULL,
  `TS_ID` int(6) NOT NULL,
  PRIMARY KEY (`DETAIL_TIMESHEET_ID`,`TS_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detail_timesheet`
--

LOCK TABLES `detail_timesheet` WRITE;
/*!40000 ALTER TABLE `detail_timesheet` DISABLE KEYS */;
INSERT INTO `detail_timesheet` VALUES (1,'2017-10-10',8.0,1,3,8,1,'code',1),(2,'2017-10-11',8.0,3,3,10,2,'review code',1),(3,'2018-01-03',9.0,2,2,6,1,'code',3),(4,'2018-01-04',9.0,4,4,8,3,'code',2),(5,'2018-01-04',9.0,2,4,9,84,'code',2);
/*!40000 ALTER TABLE `detail_timesheet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member_project`
--

DROP TABLE IF EXISTS `member_project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `member_project` (
  `MEMBER_PROJECT_ID` int(4) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(4) NOT NULL,
  `MEMBER_PROJECT_NAME` varchar(50) CHARACTER SET utf8 NOT NULL,
  `ROLE_ID` tinyint(2) NOT NULL,
  `MEMBER_PROJECT_EFFORT` int(3) NOT NULL DEFAULT '100',
  `PROJECT_ID` int(4) NOT NULL,
  PRIMARY KEY (`MEMBER_PROJECT_ID`,`USER_ID`,`PROJECT_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_project`
--

LOCK TABLES `member_project` WRITE;
/*!40000 ALTER TABLE `member_project` DISABLE KEYS */;
INSERT INTO `member_project` VALUES (1,2,'To Thanh Luong',1,100,1),(1,23,'aaaaaaaaaa',3,100,1),(2,5,'Vu Van Chinh',2,100,1),(3,6,'Pham My Linh',2,100,1),(4,7,'Phan Danh Hùng',2,100,1),(5,3,'Nguyen Thi Thanh Nguyen Thi Thanh Nguyen Thi Thanh',1,100,2),(6,5,'Vu Thi Y Vi',2,100,2),(7,6,'Nguyen Thi Ngoc',2,100,2),(8,7,'Tran Thi Tuoi',2,100,2),(9,8,'Vu Thi Trang',2,100,2),(10,9,'Truong My Hoa',2,100,2),(11,14,'Nguyen Thi Doan',3,100,2),(12,12,'chu quang dai',1,100,2),(12,12,'le thi thao',2,100,4),(12,15,'Doan Ngoc Hai',3,100,2),(13,18,'Nhu Quynh',4,100,2),(14,2,'Bui Thi Mua',1,100,3),(15,8,'Nguyen Xuan Bac',2,100,3),(16,9,'Vu Tu Long',2,100,3),(17,10,'Tran Binh Trong',2,100,3),(18,11,'Nguyen Khac Cuong',2,100,3),(19,12,'Nguyen Huu Binh',2,100,3),(20,16,'Lai Van Sam',3,100,3),(21,17,'Thomas Edison',3,100,3),(22,18,'Dinh La Thang',4,100,3),(23,19,'Tran Dai Quang',4,100,3),(24,2,'Nguyen Phu Trong',1,100,4),(25,5,'Nguyen Thien Nhan',2,100,4),(26,15,'Dinh Ngoc Minh',3,100,4),(27,18,'Nguyen Ngoc Phan',4,100,4),(28,2,'Dam Vinh Hung',1,100,5),(29,6,'Nguyen Thanh Tung',2,100,5),(30,15,'Nguyen Tuan Vu',3,100,5);
/*!40000 ALTER TABLE `member_project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `milestone_info`
--

DROP TABLE IF EXISTS `milestone_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `milestone_info` (
  `PROJECT_ID` int(4) NOT NULL,
  `MILESTONE_DATE` date NOT NULL,
  `MILESTONE_DESCRIPTION` varchar(1000) CHARACTER SET utf8 DEFAULT NULL,
  `MILESTONE_ID` int(4) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`MILESTONE_ID`,`PROJECT_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `milestone_info`
--

LOCK TABLES `milestone_info` WRITE;
/*!40000 ALTER TABLE `milestone_info` DISABLE KEYS */;
INSERT INTO `milestone_info` VALUES (1,'2017-02-02','try',1),(3,'2017-12-19','222,,',6),(1,'2017-12-06',NULL,7),(1,'2017-12-13','bbbbb',8),(2,'2017-12-26','7777',9),(3,'2018-01-06','kkyukyui',10),(2,'2018-01-06','',11),(3,'2018-01-06','g5r',12),(3,'2018-01-03','fv',13);
/*!40000 ALTER TABLE `milestone_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pre_defined`
--

DROP TABLE IF EXISTS `pre_defined`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pre_defined` (
  `PRE_DEFINED_ID` tinyint(2) NOT NULL AUTO_INCREMENT,
  `PRE_DEFINED_NAME` varchar(50) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`PRE_DEFINED_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pre_defined`
--

LOCK TABLES `pre_defined` WRITE;
/*!40000 ALTER TABLE `pre_defined` DISABLE KEYS */;
INSERT INTO `pre_defined` VALUES (1,'Coding'),(2,'Fixbug'),(3,'Code Review'),(4,'Create UT'),(5,'Execute UT'),(6,'Meeting');
/*!40000 ALTER TABLE `pre_defined` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `process`
--

DROP TABLE IF EXISTS `process`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `process` (
  `PROCESS_ID` tinyint(2) NOT NULL AUTO_INCREMENT,
  `PROCESS_NAME` varchar(200) CHARACTER SET utf8 NOT NULL,
  `PROCESS_TYPE` varchar(50) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`PROCESS_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `process`
--

LOCK TABLES `process` WRITE;
/*!40000 ALTER TABLE `process` DISABLE KEYS */;
INSERT INTO `process` VALUES (1,'Requirement','Process'),(2,'Design','Process'),(3,'Coding','Process'),(4,'Test','Process'),(5,'Project Management','Process');
/*!40000 ALTER TABLE `process` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project_info`
--

DROP TABLE IF EXISTS `project_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project_info` (
  `PROJECT_ID` int(4) NOT NULL AUTO_INCREMENT,
  `PROJECT_NAME` varchar(50) CHARACTER SET utf8 NOT NULL,
  `PROJECT_FROM` date NOT NULL,
  `PROJECT_TO` date NOT NULL,
  `PROJECT_CHARGE_COST` int(3) NOT NULL,
  `STATUS_ID` tinyint(2) NOT NULL,
  `TYPE_ID` tinyint(2) NOT NULL,
  `PROJECT_DESCRIPTION` varchar(1000) CHARACTER SET utf8 DEFAULT NULL,
  `PROJECT_TECHNICAL` varchar(100) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`PROJECT_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project_info`
--

LOCK TABLES `project_info` WRITE;
/*!40000 ALTER TABLE `project_info` DISABLE KEYS */;
INSERT INTO `project_info` VALUES (2,'WMS - Website Manager Student','2017-09-02','2018-06-02',9,1,1,'quan ly sinh vien Demo2','IOS'),(3,'TonTon','2017-09-03','2017-11-11',10,1,1,'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa','PHP'),(4,'Library  Manager','2017-09-04','2018-05-25',4,1,2,NULL,'Android'),(5,'Manager Expense Person','2017-09-05','2018-05-26',3,3,2,NULL,'IOS'),(6,'Thi Quoc Gia','2017-12-15','2017-12-30',10,1,1,'hi','PHP'),(7,'asd','2017-12-26','2017-12-14',1,1,1,'sadasda','PHP'),(8,'aaaaaaaaaaa','2017-11-27','2017-12-22',11,1,1,'ssaa','PHP'),(9,'as','2017-12-05','2017-12-14',76,1,1,'das','PHP'),(10,'asa','2017-12-04','2017-12-21',12,1,2,'saasasa','PHP'),(11,'adaadkfjdk','2017-12-04','2017-12-28',0,1,1,'&#7845;dasdfadsfsdafasdfads','PHP'),(12,'dddddddddd','2017-12-31','2018-02-07',20,1,1,'Exxxxxxxxxxxxxxxxxxxxxxxx','PHP'),(13,'khk','2018-01-01','2018-02-21',70,1,1,'hkkkkkkkkkkkkkkkkkkkkkkkk','PHP'),(14,'kkkkkkkkkkkkkkkkkkkkkkkkkkkk','2017-12-31','2018-02-10',2,1,1,'asdfasdf adfasf dsadfasdjfhas asdfa à adfad &#273;&#7841;i','PHP'),(15,'ddddddddddddddddddddddddddd','2018-01-08','2018-01-30',50,1,1,'','PHP'),(16,'daikchu','2017-12-31','2018-02-10',10,1,1,'Demo','Java'),(17,'fds','2017-12-31','2018-01-23',12,1,1,'','PHP');
/*!40000 ALTER TABLE `project_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `ROLE_ID` tinyint(2) NOT NULL AUTO_INCREMENT,
  `ROLE_NAME` varchar(50) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'Admin'),(2,'Manager'),(3,'Developer'),(4,'Tester'),(5,'Reporter');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scope_info`
--

DROP TABLE IF EXISTS `scope_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `scope_info` (
  `SCOPE_ID` tinyint(2) NOT NULL AUTO_INCREMENT,
  `SCOPE_NAME` varchar(20) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`SCOPE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scope_info`
--

LOCK TABLES `scope_info` WRITE;
/*!40000 ALTER TABLE `scope_info` DISABLE KEYS */;
INSERT INTO `scope_info` VALUES (1,'BD'),(2,'DD'),(3,'Coding'),(4,'UT'),(5,'IT'),(6,'ST');
/*!40000 ALTER TABLE `scope_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scope_project`
--

DROP TABLE IF EXISTS `scope_project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `scope_project` (
  `SCOPE_PROECT_ID` int(3) NOT NULL AUTO_INCREMENT,
  `PROJECT_ID` varchar(20) CHARACTER SET utf8 NOT NULL,
  `SCOPE_ID` tinyint(2) NOT NULL,
  PRIMARY KEY (`SCOPE_PROECT_ID`,`PROJECT_ID`,`SCOPE_ID`),
  KEY `FK_ID_idx` (`SCOPE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scope_project`
--

LOCK TABLES `scope_project` WRITE;
/*!40000 ALTER TABLE `scope_project` DISABLE KEYS */;
INSERT INTO `scope_project` VALUES (1,'1',1),(5,'2',1),(2,'1',2),(3,'1',3),(4,'1',4),(6,'2',5);
/*!40000 ALTER TABLE `scope_project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status_info`
--

DROP TABLE IF EXISTS `status_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `status_info` (
  `STATUS_ID` tinyint(2) NOT NULL AUTO_INCREMENT,
  `STATUS_TYPE` varchar(20) CHARACTER SET utf8 NOT NULL,
  `STATUS_NAME` varchar(30) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`STATUS_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status_info`
--

LOCK TABLES `status_info` WRITE;
/*!40000 ALTER TABLE `status_info` DISABLE KEYS */;
INSERT INTO `status_info` VALUES (1,'Open','project'),(2,'Close','project'),(3,'Pending','project'),(4,'Open','task'),(5,'On-going','task'),(6,'Close','task'),(7,'Cancel','task'),(8,'Open','QA'),(9,'External','QA'),(10,'Answer','QA'),(11,'Close','QA'),(12,'Cancel','QA'),(13,'Request','timesheet'),(14,'Approved','timesheet'),(15,'Reject','timesheet');
/*!40000 ALTER TABLE `status_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task_info`
--

DROP TABLE IF EXISTS `task_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `task_info` (
  `TASK_ID` int(6) NOT NULL AUTO_INCREMENT,
  `TASK_SUBJECT` varchar(200) CHARACTER SET utf8 NOT NULL,
  `TYPE_ID` tinyint(2) NOT NULL,
  `STATUS_ID` tinyint(2) NOT NULL,
  `TASK_DONE` int(3) DEFAULT NULL,
  `TASK_FROM` date NOT NULL,
  `TASK_TO` date NOT NULL,
  `TASK_Solution` varchar(1000) CHARACTER SET utf8 DEFAULT NULL,
  `TASK_DESCRIPTION` varchar(1000) CHARACTER SET utf8 DEFAULT NULL,
  `MEMBER_PROJECT_ID` int(4) NOT NULL,
  `CATEGORY_ID` tinyint(2) NOT NULL,
  `TASK_PRIORITY` varchar(20) CHARACTER SET utf8 NOT NULL DEFAULT 'medium',
  `PROJECT_ID` int(2) NOT NULL,
  PRIMARY KEY (`TASK_ID`,`PROJECT_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=85 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task_info`
--

LOCK TABLES `task_info` WRITE;
/*!40000 ALTER TABLE `task_info` DISABLE KEYS */;
INSERT INTO `task_info` VALUES (2,'vvvvv',1,4,90,'2017-11-11','2018-11-17','aa','bbbaaaaaaaaaavvvvvvvvvvv',1,1,'Highest',0),(3,'manh xau trai vl',1,4,10,'2017-11-11','2018-11-17','aa','bbbdddddddd&#273;dvdvdvdvf',1,1,'Highest',0),(4,'[UT] bug ttt',3,4,50,'2017-10-11','2017-12-15','55','55',1,1,'Highest',3),(5,'[UT] bug ttt',3,4,50,'2017-10-11','2017-12-15','55','55',1,1,'Highest',3),(6,'[UT] bug ttt',3,4,50,'2017-10-11','2017-12-15','55','55',1,1,'Highest',3),(7,'[UT] bug ttt',3,4,50,'2017-10-11','2017-12-15','55','55',1,1,'Highest',3),(8,'[UT] bug ttt',3,4,50,'2017-10-11','2017-12-15','55','55',1,1,'Highest',3),(9,'[UT] bug ttt',3,4,50,'2017-10-11','2017-12-15','55','55',1,1,'Highest',3),(10,'[UT] bug ttt',3,4,50,'2017-10-11','2017-12-15','55','55',1,1,'Highest',3),(11,'[UT] bug ttt',3,4,50,'2017-10-11','2017-12-15','55','55',1,1,'Highest',3),(12,'[UT] bug ttt',3,4,50,'2017-10-11','2017-12-15','55','55',1,7,'Highest',3),(13,'[UT] bug ttt',3,4,50,'2017-10-11','2017-12-15','55','55',1,1,'Highest',3),(14,'[UT] bug ttt',3,4,50,'2017-10-11','2017-12-15','55','55',1,7,'Highest',3),(15,'[UT] bug ttt',3,4,50,'2017-10-11','2017-12-15','55','55',1,1,'Highest',3),(16,'[UT] bug ttt',3,4,50,'2017-10-11','2017-12-15','55','55',1,1,'Highest',3),(17,'[UT] bug ttt',3,4,50,'2017-10-11','2017-12-15','55','55',1,7,'Highest',3),(18,'[UT] bug ttt',3,4,50,'2017-10-11','2017-12-15','55','55',1,1,'Highest',3),(19,'[UT] bug ttt',3,4,50,'2017-10-11','2017-12-15','55','55',1,1,'Highest',3),(20,'[UT] bug ttt',3,4,50,'2017-10-11','2017-12-15','55','55',1,1,'Highest',3),(83,'[UT] bug ttt',3,4,50,'2017-10-11','2017-12-15','55','55',1,1,'Highest',3),(84,'[UT] Screen Noel',1,1,12,'2017-12-21','2017-12-21','','picture',1,7,'Highest',2);
/*!40000 ALTER TABLE `task_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `timesheet_info`
--

DROP TABLE IF EXISTS `timesheet_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `timesheet_info` (
  `TS_ID` int(6) NOT NULL AUTO_INCREMENT,
  `PROJECT_ID` int(4) NOT NULL,
  `MEMBER_PROECT_ID` int(4) NOT NULL,
  PRIMARY KEY (`TS_ID`,`PROJECT_ID`,`MEMBER_PROECT_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `timesheet_info`
--

LOCK TABLES `timesheet_info` WRITE;
/*!40000 ALTER TABLE `timesheet_info` DISABLE KEYS */;
INSERT INTO `timesheet_info` VALUES (1,3,3),(2,4,3),(3,2,2),(3,3,2);
/*!40000 ALTER TABLE `timesheet_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type`
--

DROP TABLE IF EXISTS `type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `type` (
  `TYPE_ID` tinyint(2) NOT NULL AUTO_INCREMENT,
  `TYPE_NAME` varchar(30) CHARACTER SET utf8mb4 NOT NULL,
  `TYPE_TYPE` varchar(20) CHARACTER SET utf8mb4 NOT NULL,
  PRIMARY KEY (`TYPE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type`
--

LOCK TABLES `type` WRITE;
/*!40000 ALTER TABLE `type` DISABLE KEYS */;
INSERT INTO `type` VALUES (1,'Development','project'),(2,'Maintain','project'),(3,'Spec','task'),(4,'Task','task'),(5,'Bug','bug'),(6,'Issue','task'),(7,'Study','timesheet'),(8,'Create','timesheet'),(9,'Correct','timesheet'),(10,'Review','timesheet');
/*!40000 ALTER TABLE `type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_info`
--

DROP TABLE IF EXISTS `user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_info` (
  `USER_ID` int(4) NOT NULL AUTO_INCREMENT,
  `USER_FULLNAME` varchar(50) CHARACTER SET utf8 NOT NULL,
  `USER_MAIL` varchar(50) CHARACTER SET utf8 NOT NULL,
  `USER_PASSWORD` varchar(100) CHARACTER SET utf8 NOT NULL,
  `ROLE_ID` tinyint(2) NOT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_info`
--

LOCK TABLES `user_info` WRITE;
/*!40000 ALTER TABLE `user_info` DISABLE KEYS */;
INSERT INTO `user_info` VALUES (1,'A','a@tinhvan.com','123456Aa@abc',5),(5,'E','e@tinhvan.com','123456Aa@abc',2),(6,'F','f@tinhvan.com','123456Aa@abc',2),(7,'G','g@tinhvan.com','123456Aa@abc',2),(8,'H','h@tinhvan.com','123456Aa@abc',2),(9,'I','i@tinhvan.com','123456Aa@abc',2),(10,'J','j@tinhvan.com','123456Aa@abc',2),(11,'K','k@tinhvan.com','123456Aa@abc',2),(12,'L','l@tinhvan.com','123456Aa@abc',2),(13,'M','m@tinhvan.com','123456Aa@abc',2),(14,'N','n@tinhvan.com','123456Aa@abc',3),(15,'O','o@tinhvan.com','123456Aa@abc',3),(16,'P','p@tinhvan.com','123456Aa@abc',3),(17,'Q','q@tinhvan.com','123456Aa@abc',3),(18,'R','r@tinhvan.com','123456Aa@abc',4),(19,'S','s@tinhvan.com','123456Aa@abc',4),(20,'T','123@gmail.com','123456',4),(21,'NguyenVanManh','Manhnv@gmail.com','123456',1),(23,'thangnn','thangnn1@tinhvan.com','111222',1),(24,'vi','vi@gmail.com','123',1),(25,'1','1','1',5);
/*!40000 ALTER TABLE `user_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-01-25 10:43:25
