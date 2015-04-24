-- MySQL dump 10.13  Distrib 5.5.24, for Win32 (x86)
--
-- Host: localhost    Database: ib
-- ------------------------------------------------------
-- Server version	5.5.24-log

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
-- Table structure for table `backfill`
--

DROP TABLE IF EXISTS `backfill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `backfill` (
  `backfill_id` int(11) NOT NULL AUTO_INCREMENT,
  `enddate` datetime NOT NULL,
  `description` varchar(10) NOT NULL,
  PRIMARY KEY (`backfill_id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `backfill`
--

LOCK TABLES `backfill` WRITE;
/*!40000 ALTER TABLE `backfill` DISABLE KEYS */;
/*!40000 ALTER TABLE `backfill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contracts`
--

DROP TABLE IF EXISTS `contracts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contracts` (
  `conid` int(9) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contracts`
--

LOCK TABLES `contracts` WRITE;
/*!40000 ALTER TABLE `contracts` DISABLE KEYS */;
/*!40000 ALTER TABLE `contracts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fact_contract_info`
--

DROP TABLE IF EXISTS `fact_contract_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fact_contract_info` (
  `data_key` varchar(70) NOT NULL,
  `conid` varchar(50) NOT NULL,
  `localSymbol` varchar(50) NOT NULL,
  `DateC` date NOT NULL,
  `timeC` varchar(8) NOT NULL,
  `openC` float NOT NULL,
  `highC` float NOT NULL,
  `low` float NOT NULL,
  `closeC` float NOT NULL,
  `volume` float NOT NULL,
  `wap` float NOT NULL,
  UNIQUE KEY `data_key` (`data_key`),
  KEY `conid` (`conid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fact_contract_info`
--

LOCK TABLES `fact_contract_info` WRITE;
/*!40000 ALTER TABLE `fact_contract_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `fact_contract_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fact_contracts`
--

DROP TABLE IF EXISTS `fact_contracts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fact_contracts` (
  `conid` varchar(50) NOT NULL,
  `symbol` varchar(5) DEFAULT NULL,
  `secType` varchar(4) DEFAULT NULL,
  `expiry` int(11) DEFAULT NULL,
  `strike` int(11) DEFAULT NULL,
  `rightOPT` varchar(4) DEFAULT NULL,
  `multiplier` int(11) DEFAULT NULL,
  `exchange` varchar(10) DEFAULT NULL,
  `primaryExch` varchar(4) DEFAULT NULL,
  `currency` varchar(4) DEFAULT NULL,
  `localSymbol` varchar(50) DEFAULT NULL,
  `marketName` varchar(4) DEFAULT NULL,
  `tradingClass` varchar(4) DEFAULT NULL,
  `minTick` int(11) DEFAULT NULL,
  `priceMagnifier` int(11) DEFAULT NULL,
  `orderTypes` varchar(255) DEFAULT NULL,
  `validExchanges` varchar(255) DEFAULT NULL,
  `underConid` int(11) DEFAULT NULL,
  `longName` varchar(50) DEFAULT NULL,
  `contractMonth` int(11) DEFAULT NULL,
  `industry` varchar(50) DEFAULT NULL,
  `category` varchar(50) DEFAULT NULL,
  `subcategory` varchar(50) DEFAULT NULL,
  `timeZoneid` varchar(4) DEFAULT NULL,
  `tradingHours` varchar(50) DEFAULT NULL,
  `liquidHours` varchar(50) DEFAULT NULL,
  `date_added` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`conid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fact_contracts`
--

LOCK TABLES `fact_contracts` WRITE;
/*!40000 ALTER TABLE `fact_contracts` DISABLE KEYS */;
/*!40000 ALTER TABLE `fact_contracts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordertypes`
--

DROP TABLE IF EXISTS `ordertypes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ordertypes` (
  `orderCategory` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `orderType` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `abbreviation` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordertypes`
--

LOCK TABLES `ordertypes` WRITE;
/*!40000 ALTER TABLE `ordertypes` DISABLE KEYS */;
/*!40000 ALTER TABLE `ordertypes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordertypes_description`
--

DROP TABLE IF EXISTS `ordertypes_description`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ordertypes_description` (
  `ordertype` varchar(27) DEFAULT NULL,
  `Description` varchar(466) DEFAULT NULL,
  `Products` varchar(64) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordertypes_description`
--

LOCK TABLES `ordertypes_description` WRITE;
/*!40000 ALTER TABLE `ordertypes_description` DISABLE KEYS */;
/*!40000 ALTER TABLE `ordertypes_description` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordertypes_use`
--

DROP TABLE IF EXISTS `ordertypes_use`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ordertypes_use` (
  `use` varchar(18) DEFAULT NULL,
  `ordertype` varchar(27) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordertypes_use`
--

LOCK TABLES `ordertypes_use` WRITE;
/*!40000 ALTER TABLE `ordertypes_use` DISABLE KEYS */;
/*!40000 ALTER TABLE `ordertypes_use` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tmp_contract_info`
--

DROP TABLE IF EXISTS `tmp_contract_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tmp_contract_info` (
  `priceID` int(11) NOT NULL AUTO_INCREMENT,
  `conid` varchar(50) NOT NULL,
  `localSymbol` varchar(50) NOT NULL,
  `DateC` date NOT NULL,
  `timeC` varchar(8) NOT NULL,
  `openC` float NOT NULL,
  `highC` float NOT NULL,
  `low` float NOT NULL,
  `closeC` float NOT NULL,
  `volume` float NOT NULL,
  `wap` float NOT NULL,
  PRIMARY KEY (`priceID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tmp_contract_info`
--

LOCK TABLES `tmp_contract_info` WRITE;
/*!40000 ALTER TABLE `tmp_contract_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `tmp_contract_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tmp_contract_info_stage`
--

DROP TABLE IF EXISTS `tmp_contract_info_stage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tmp_contract_info_stage` (
  `data_key` varchar(50) NOT NULL,
  `conid` varchar(50) NOT NULL,
  `localSymbol` varchar(50) NOT NULL,
  `DateC` date NOT NULL,
  `timeC` varchar(8) NOT NULL,
  `openC` float NOT NULL,
  `highC` float NOT NULL,
  `low` float NOT NULL,
  `closeC` float NOT NULL,
  `volume` float NOT NULL,
  `wap` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tmp_contract_info_stage`
--

LOCK TABLES `tmp_contract_info_stage` WRITE;
/*!40000 ALTER TABLE `tmp_contract_info_stage` DISABLE KEYS */;
/*!40000 ALTER TABLE `tmp_contract_info_stage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tmp_contracts`
--

DROP TABLE IF EXISTS `tmp_contracts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tmp_contracts` (
  `conid` varchar(50) NOT NULL DEFAULT '',
  `symbol` varchar(5) DEFAULT NULL,
  `secType` varchar(4) DEFAULT NULL,
  `expiry` int(11) DEFAULT NULL,
  `strike` int(11) DEFAULT NULL,
  `rightOPT` varchar(50) DEFAULT NULL,
  `multiplier` int(11) DEFAULT NULL,
  `exchange` varchar(10) DEFAULT NULL,
  `primaryExch` varchar(10) DEFAULT NULL,
  `currency` varchar(4) DEFAULT NULL,
  `localSymbol` varchar(50) DEFAULT NULL,
  `marketName` varchar(50) DEFAULT NULL,
  `tradingClass` varchar(50) DEFAULT NULL,
  `minTick` int(11) DEFAULT NULL,
  `priceMagnifier` int(11) DEFAULT NULL,
  `orderTypes` blob,
  `validExchanges` blob,
  `underConid` varchar(10) DEFAULT NULL,
  `longName` varchar(255) DEFAULT NULL,
  `contractMonth` int(11) DEFAULT NULL,
  `industry` varchar(50) DEFAULT NULL,
  `category` varchar(50) DEFAULT NULL,
  `subcategory` varchar(50) DEFAULT NULL,
  `timeZoneid` varchar(50) DEFAULT NULL,
  `tradingHours` varchar(255) DEFAULT NULL,
  `liquidHours` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`conid`),
  UNIQUE KEY `conid` (`conid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tmp_contracts`
--

LOCK TABLES `tmp_contracts` WRITE;
/*!40000 ALTER TABLE `tmp_contracts` DISABLE KEYS */;
/*!40000 ALTER TABLE `tmp_contracts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tmp_dir`
--

DROP TABLE IF EXISTS `tmp_dir`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tmp_dir` (
  `dirpath` varchar(255) DEFAULT NULL,
  `localCopy` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tmp_dir`
--

LOCK TABLES `tmp_dir` WRITE;
/*!40000 ALTER TABLE `tmp_dir` DISABLE KEYS */;
/*!40000 ALTER TABLE `tmp_dir` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tmp_info`
--

DROP TABLE IF EXISTS `tmp_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tmp_info` (
  `reqId` int(5) DEFAULT NULL,
  `date` varchar(50) DEFAULT NULL,
  `open` double DEFAULT NULL,
  `high` double DEFAULT NULL,
  `low` double DEFAULT NULL,
  `close` double DEFAULT NULL,
  `volume` int(10) DEFAULT NULL,
  `WAP` double DEFAULT NULL,
  `hasGaps` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tmp_info`
--

LOCK TABLES `tmp_info` WRITE;
/*!40000 ALTER TABLE `tmp_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `tmp_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-04-17 19:55:39
