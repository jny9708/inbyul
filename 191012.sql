-- MySQL dump 10.13  Distrib 5.7.23, for Win64 (x86_64)
--
-- Host: localhost    Database: inbyul
-- ------------------------------------------------------
-- Server version	5.7.23

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
-- Table structure for table `board`
--

DROP TABLE IF EXISTS `board`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `board` (
  `bno` int(11) NOT NULL AUTO_INCREMENT,
  `bcontent` text,
  `uno` int(11) NOT NULL,
  `b_reg_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `b_edit_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `likecnt` int(11) DEFAULT '0',
  `commentcnt` int(11) DEFAULT '0',
  PRIMARY KEY (`bno`),
  KEY `uno` (`uno`),
  CONSTRAINT `board_ibfk_1` FOREIGN KEY (`uno`) REFERENCES `user` (`uno`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `board`
--

LOCK TABLES `board` WRITE;
/*!40000 ALTER TABLE `board` DISABLE KEYS */;
INSERT INTO `board` VALUES (14,'test',11,'2019-10-01 16:00:15','2019-10-01 16:00:15',3,15),(15,'ㅅㄷㄴㅅ',11,'2019-10-06 18:44:26','2019-10-06 18:44:26',0,0),(16,'solar',11,'2019-10-12 15:35:02','2019-10-12 15:35:02',1,0);
/*!40000 ALTER TABLE `board` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `cno` int(11) NOT NULL AUTO_INCREMENT,
  `bno` int(11) NOT NULL,
  `uno` int(11) NOT NULL,
  `ccontent` text NOT NULL,
  `c_reg_dt` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`cno`),
  KEY `bno` (`bno`),
  KEY `uno` (`uno`),
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`bno`) REFERENCES `board` (`bno`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`uno`) REFERENCES `user` (`uno`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (2,14,11,'dds','2019-10-01 20:16:45'),(5,14,11,'ㅁㄴㅇㅁㄴ','2019-10-01 21:20:34'),(12,14,11,'sdfe','2019-10-02 00:24:14'),(14,14,11,'asdasdwq','2019-10-02 00:26:59'),(15,14,11,'test1','2019-10-02 00:27:04'),(16,14,11,'test2','2019-10-02 00:27:08'),(17,14,11,'test3','2019-10-02 00:27:11'),(19,14,11,'ASTEST1','2019-10-02 03:22:33'),(20,14,11,'test2','2019-10-02 03:27:51'),(21,14,11,'test3','2019-10-02 03:28:17'),(22,14,11,'test4','2019-10-02 03:29:55'),(23,14,11,'ㅁ1','2019-10-02 03:39:56'),(24,14,11,'ㅁ2','2019-10-02 03:44:25'),(25,14,16,'안녕하세요','2019-10-08 18:32:58'),(47,14,17,'test','2019-10-11 22:58:01');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emailauth`
--

DROP TABLE IF EXISTS `emailauth`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `emailauth` (
  `eno` int(11) NOT NULL AUTO_INCREMENT,
  `uno` int(11) DEFAULT NULL,
  `authkey` varchar(300) NOT NULL,
  PRIMARY KEY (`eno`),
  KEY `uno` (`uno`),
  CONSTRAINT `emailauth_ibfk_1` FOREIGN KEY (`uno`) REFERENCES `user` (`uno`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emailauth`
--

LOCK TABLES `emailauth` WRITE;
/*!40000 ALTER TABLE `emailauth` DISABLE KEYS */;
/*!40000 ALTER TABLE `emailauth` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `follow`
--

DROP TABLE IF EXISTS `follow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `follow` (
  `fno` int(11) NOT NULL AUTO_INCREMENT,
  `follower_no` int(11) NOT NULL,
  `following_no` int(11) NOT NULL,
  PRIMARY KEY (`fno`),
  KEY `follower_no` (`follower_no`),
  KEY `following_no` (`following_no`),
  CONSTRAINT `follow_ibfk_1` FOREIGN KEY (`follower_no`) REFERENCES `user` (`uno`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `follow_ibfk_2` FOREIGN KEY (`following_no`) REFERENCES `user` (`uno`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `follow`
--

LOCK TABLES `follow` WRITE;
/*!40000 ALTER TABLE `follow` DISABLE KEYS */;
INSERT INTO `follow` VALUES (21,11,16),(37,16,17),(38,11,17);
/*!40000 ALTER TABLE `follow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `postimages`
--

DROP TABLE IF EXISTS `postimages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `postimages` (
  `pno` int(11) NOT NULL AUTO_INCREMENT,
  `bno` int(11) NOT NULL,
  `file_path` varchar(130) NOT NULL,
  PRIMARY KEY (`pno`),
  KEY `bno` (`bno`),
  CONSTRAINT `postimages_ibfk_1` FOREIGN KEY (`bno`) REFERENCES `board` (`bno`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `postimages`
--

LOCK TABLES `postimages` WRITE;
/*!40000 ALTER TABLE `postimages` DISABLE KEYS */;
INSERT INTO `postimages` VALUES (16,14,'/resources/images/postimages/2623d338-2f18-40a4-a8a1-b01734f8bc422019100116016592.jpg'),(17,15,'/resources/images/postimages/080bb96a-8c2f-4e5e-9d08-5859e5e5506820191006184426642.jpg'),(19,16,'/resources/images/postimages/4b870fb4-0726-45c7-a90c-f5b389bfccb6201910121535344.jpg'),(23,16,'/resources/images/postimages/0c1ce4b0-388a-44ea-9031-0e768659970720191012173751960.jpg');
/*!40000 ALTER TABLE `postimages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `rno` int(11) NOT NULL AUTO_INCREMENT,
  `rolename` varchar(50) NOT NULL,
  `uno` int(11) NOT NULL,
  PRIMARY KEY (`rno`),
  KEY `uno` (`uno`),
  CONSTRAINT `role_ibfk_1` FOREIGN KEY (`uno`) REFERENCES `user` (`uno`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'USER',11),(5,'USER',16),(6,'USER',17);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_like`
--

DROP TABLE IF EXISTS `tbl_like`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_like` (
  `lno` int(11) NOT NULL AUTO_INCREMENT,
  `bno` int(11) NOT NULL,
  `uno` int(11) NOT NULL,
  PRIMARY KEY (`lno`),
  KEY `bno` (`bno`),
  KEY `uno` (`uno`),
  CONSTRAINT `tbl_like_ibfk_1` FOREIGN KEY (`bno`) REFERENCES `board` (`bno`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tbl_like_ibfk_2` FOREIGN KEY (`uno`) REFERENCES `user` (`uno`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_like`
--

LOCK TABLES `tbl_like` WRITE;
/*!40000 ALTER TABLE `tbl_like` DISABLE KEYS */;
INSERT INTO `tbl_like` VALUES (14,14,11),(15,14,16),(19,14,17),(20,16,11);
/*!40000 ALTER TABLE `tbl_like` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_notice`
--

DROP TABLE IF EXISTS `tbl_notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_notice` (
  `ntc_no` int(11) NOT NULL AUTO_INCREMENT,
  `cmd` varchar(20) NOT NULL,
  `sender` varchar(80) NOT NULL,
  `recipient` varchar(80) NOT NULL,
  `target` int(11) DEFAULT NULL,
  `ntc_read` tinyint(1) DEFAULT '0',
  `ntc_reg_dt` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ntc_no`),
  KEY `sender` (`sender`),
  KEY `recipient` (`recipient`),
  CONSTRAINT `tbl_notice_ibfk_1` FOREIGN KEY (`sender`) REFERENCES `user` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tbl_notice_ibfk_2` FOREIGN KEY (`recipient`) REFERENCES `user` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_notice`
--

LOCK TABLES `tbl_notice` WRITE;
/*!40000 ALTER TABLE `tbl_notice` DISABLE KEYS */;
INSERT INTO `tbl_notice` VALUES (2,'comment','test5','skdud5606',14,0,'2019-10-11 16:57:03'),(3,'comment','test5','skdud5606',14,0,'2019-10-11 17:35:01'),(4,'comment','test5','skdud5606',14,0,'2019-10-11 17:39:56'),(5,'comment','test5','skdud5606',14,0,'2019-10-11 17:40:38'),(6,'comment','test5','skdud5606',14,0,'2019-10-11 17:40:58'),(7,'comment','test5','skdud5606',14,0,'2019-10-11 17:55:54'),(8,'comment','test5','skdud5606',14,0,'2019-10-11 22:38:29'),(9,'comment','test5','skdud5606',14,0,'2019-10-11 22:41:10'),(10,'comment','test5','skdud5606',14,0,'2019-10-11 22:43:23'),(11,'comment','test5','skdud5606',14,0,'2019-10-11 22:45:55'),(12,'comment','test5','skdud5606',14,0,'2019-10-11 22:46:13'),(13,'comment','test5','skdud5606',14,0,'2019-10-11 22:46:28'),(14,'comment','test5','skdud5606',14,0,'2019-10-11 22:46:47'),(15,'comment','test5','skdud5606',14,0,'2019-10-11 22:49:35'),(18,'follow','test5','skdud5606',0,0,'2019-10-12 00:07:03'),(19,'follow','test5','skdud5606',0,0,'2019-10-12 00:07:39'),(20,'follow','test5','skdud5606',0,0,'2019-10-12 00:10:24'),(21,'follow','test5','skdud5606',0,0,'2019-10-12 00:14:19'),(22,'like','test5','skdud5606',14,0,'2019-10-12 00:14:34'),(23,'like','test5','skdud5606',14,0,'2019-10-12 00:14:50'),(24,'like','test5','skdud5606',14,0,'2019-10-12 00:14:58'),(25,'like','test5','skdud5606',14,0,'2019-10-12 00:20:26'),(26,'like','skdud5606','skdud5606',16,0,'2019-10-12 15:35:28');
/*!40000 ALTER TABLE `tbl_notice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `uno` int(11) NOT NULL AUTO_INCREMENT,
  `uid` varchar(80) NOT NULL,
  `upw` varchar(500) NOT NULL,
  `uemail` varchar(80) NOT NULL,
  `uphone` varchar(20) NOT NULL,
  `uname` varchar(50) NOT NULL,
  `uicon` varchar(60) NOT NULL DEFAULT 'profile.jpg',
  `followercnt` int(11) DEFAULT '0',
  `followingcnt` int(11) DEFAULT '0',
  PRIMARY KEY (`uno`),
  UNIQUE KEY `UNIQUEKEY` (`uemail`,`uphone`),
  UNIQUE KEY `uid` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (11,'skdud5606','$2a$10$OxhmioV1M2Tcd7okO2zYneLRu/PB8JMY20xNexjJ9K3b4hu43QAHe','skdud5606@naver.com','01064718216','정나영','profile.jpg',2,0),(16,'test4','$2a$10$6xZ7VDaUcdtAVBEYRTUmU.gJruhtpj351RFMztoYsPmcPZRfDgwfy','test4@ruu.kr','01064718216','곽두팔넷','profile.jpg',1,1),(17,'test5','$2a$10$K8c11iwJ3k5SIDIACB9E5uuqmq9.YKms232ubJUgLLZ70wIOmvswe','test5@ruu.kr','01064718216','곽두팔오','profile.jpg',0,2);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-10-12 19:45:38
