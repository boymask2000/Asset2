-- MySQL dump 10.13  Distrib 5.7.26, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: test1
-- ------------------------------------------------------
-- Server version	5.7.26-0ubuntu0.19.04.1

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
-- Table structure for table `asset`
--

DROP TABLE IF EXISTS `asset`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `asset` (
  `family` varchar(50) DEFAULT NULL,
  `assetNum` varchar(50) DEFAULT NULL,
  `changedDate` varchar(10) DEFAULT NULL,
  `description` varchar(150) DEFAULT NULL,
  `longDescription` varchar(250) DEFAULT NULL,
  `masterSystem` varchar(50) DEFAULT NULL,
  `system` varchar(50) DEFAULT NULL,
  `subSystem` varchar(50) DEFAULT NULL,
  `location` varchar(50) DEFAULT NULL,
  `siteId` varchar(50) DEFAULT NULL,
  `workCenter` varchar(50) DEFAULT NULL,
  `assetType` varchar(50) DEFAULT NULL,
  `assetQuantity` varchar(50) DEFAULT NULL,
  `unitOfMeasure` varchar(50) DEFAULT NULL,
  `inventoryCategory` varchar(50) DEFAULT NULL,
  `purchasePrice` varchar(50) DEFAULT NULL,
  `budgetedCost` varchar(50) DEFAULT NULL,
  `replacementCost` varchar(50) DEFAULT NULL,
  `meterGroup` varchar(50) DEFAULT NULL,
  `belongsTo` varchar(50) DEFAULT NULL,
  `contractNumber` varchar(50) DEFAULT NULL,
  `taskDelivOrderNum` varchar(50) DEFAULT NULL,
  `drawingRefId` varchar(50) DEFAULT NULL,
  `warrantyExpDate` varchar(50) DEFAULT NULL,
  `statusDate` varchar(10) DEFAULT NULL,
  `installationDate` varchar(10) DEFAULT NULL,
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `lastStatus` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=88129 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `assetalca`
--

DROP TABLE IF EXISTS `assetalca`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `assetalca` (
  `facNum` varchar(50) DEFAULT NULL,
  `facSystem` varchar(50) DEFAULT NULL,
  `facSubsystem` varchar(50) DEFAULT NULL,
  `assemblyCategory` varchar(50) DEFAULT NULL,
  `nomenclature` varchar(70) DEFAULT NULL,
  `procId` varchar(50) DEFAULT NULL,
  `pmSchedRecipient` varchar(50) DEFAULT NULL,
  `pmSchedSerial` varchar(50) DEFAULT NULL,
  `rpieIdIndividual` varchar(50) DEFAULT NULL,
  `frequency` varchar(50) DEFAULT NULL,
  `schedAssignedOrg` varchar(50) DEFAULT NULL,
  `lastStatus` varchar(10) DEFAULT NULL,
  `id` int(6) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `audiointervento`
--

DROP TABLE IF EXISTS `audiointervento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `audiointervento` (
  `interventoId` int(6) DEFAULT NULL,
  `filename` varchar(60) DEFAULT NULL,
  `timestamp` varchar(30) DEFAULT NULL,
  `id` int(6) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `audit`
--

DROP TABLE IF EXISTS `audit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `audit` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `username` varchar(15) DEFAULT NULL,
  `time` varchar(20) DEFAULT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `assetId` int(6) DEFAULT NULL,
  `azione` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `calendario`
--

DROP TABLE IF EXISTS `calendario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `calendario` (
  `data` varchar(8) NOT NULL,
  `interventi` int(11) DEFAULT NULL,
  `lavorativo` varchar(1) DEFAULT NULL,
  `anno` varchar(4) DEFAULT NULL,
  `mese` varchar(2) DEFAULT NULL,
  `giorno` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`data`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `checklist`
--

DROP TABLE IF EXISTS `checklist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `checklist` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `assetId` int(6) DEFAULT NULL,
  `checkId` int(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `checklistintervento`
--

DROP TABLE IF EXISTS `checklistintervento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `checklistintervento` (
  `interventoId` int(6) DEFAULT NULL,
  `checkId` int(6) DEFAULT NULL,
  `id` int(6) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=105 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `checks`
--

DROP TABLE IF EXISTS `checks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `checks` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(800) DEFAULT NULL,
  `codiceNormativa` varchar(45) DEFAULT NULL,
  `descriptionUS` varchar(400) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `docinterventi`
--

DROP TABLE IF EXISTS `docinterventi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `docinterventi` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `interventoId` int(6) DEFAULT NULL,
  `filename` varchar(45) DEFAULT NULL,
  `descrizione` varchar(200) DEFAULT NULL,
  `ext` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fotointervento`
--

DROP TABLE IF EXISTS `fotointervento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fotointervento` (
  `interventoId` int(6) DEFAULT NULL,
  `filename` varchar(60) DEFAULT NULL,
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `timestamp` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `frequenzealca`
--

DROP TABLE IF EXISTS `frequenzealca`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `frequenzealca` (
  `rpieIdIndividual` varchar(50) DEFAULT NULL,
  `idfrequenza` int(6) DEFAULT NULL,
  `id` int(6) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=197 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `interventi`
--

DROP TABLE IF EXISTS `interventi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `interventi` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `assetId` int(6) DEFAULT NULL,
  `data_teorica` varchar(10) DEFAULT NULL,
  `data_pianificata` varchar(10) DEFAULT NULL,
  `data_effettiva` varchar(10) DEFAULT NULL,
  `esito` varchar(45) DEFAULT NULL,
  `user` varchar(20) DEFAULT NULL,
  `timestamp` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51609 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `manuali`
--

DROP TABLE IF EXISTS `manuali`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `manuali` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `assetId` int(6) DEFAULT NULL,
  `descrizione` varchar(200) DEFAULT NULL,
  `nomeFile` varchar(45) DEFAULT NULL,
  `ext` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `normative`
--

DROP TABLE IF EXISTS `normative`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `normative` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `normativa` varchar(45) DEFAULT 'PJC',
  `codice` varchar(45) DEFAULT NULL,
  `dataInizio` varchar(8) DEFAULT NULL,
  `frequenza` varchar(30) DEFAULT NULL,
  `codFrequenza` int(11) DEFAULT NULL,
  `filename` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ritardi`
--

DROP TABLE IF EXISTS `ritardi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ritardi` (
  `assetId` int(6) DEFAULT NULL,
  `idIntervento` int(6) DEFAULT NULL,
  `dataPianificata` varchar(10) DEFAULT NULL,
  `checklistId` int(6) DEFAULT NULL,
  `codNormativa` varchar(30) DEFAULT NULL,
  `descCheck` varchar(300) DEFAULT NULL,
  `maxritardo` int(5) DEFAULT NULL,
  `currentRitardo` int(5) DEFAULT NULL,
  `id` int(6) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `utenti`
--

DROP TABLE IF EXISTS `utenti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `utenti` (
  `username` varchar(15) NOT NULL,
  `password` varchar(80) DEFAULT NULL,
  `tipo` varchar(1) DEFAULT NULL,
  `descrizione` varchar(45) DEFAULT NULL,
  `email` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-05-02 16:14:19
