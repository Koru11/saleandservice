-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.45-community-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema saleandservice
--

CREATE DATABASE IF NOT EXISTS saleandservice;
USE saleandservice;

--
-- Definition of table `admin`
--

DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `adminid` int(10) unsigned NOT NULL auto_increment,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY  (`adminid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` (`adminid`,`username`,`password`) VALUES 
 (1,'admin123@gmail.com','Admin123!@'),
 (2,'abc','123');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;


--
-- Definition of table `brand`
--

DROP TABLE IF EXISTS `brand`;
CREATE TABLE `brand` (
  `brandid` varchar(15) NOT NULL,
  `brandname` varchar(45) NOT NULL,
  PRIMARY KEY  (`brandid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `brand`
--

/*!40000 ALTER TABLE `brand` DISABLE KEYS */;
INSERT INTO `brand` (`brandid`,`brandname`) VALUES 
 ('BR-0000001','Apple'),
 ('BR-0000002','Samsung'),
 ('BR-0000003','Itel');
/*!40000 ALTER TABLE `brand` ENABLE KEYS */;


--
-- Definition of table `company`
--

DROP TABLE IF EXISTS `company`;
CREATE TABLE `company` (
  `companyid` varchar(15) NOT NULL,
  `companyname` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `phone` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  PRIMARY KEY  USING BTREE (`companyid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `company`
--

/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` (`companyid`,`companyname`,`address`,`phone`,`email`) VALUES 
 ('CP-0000001','KUSH','Myaynigone','09876543211','kush@gmail.com'),
 ('CP-0000002','Royal Smart','15 Street','09451778787','smart@gmail.com');
/*!40000 ALTER TABLE `company` ENABLE KEYS */;


--
-- Definition of table `customer`
--

DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `customerid` varchar(15) NOT NULL,
  `customername` varchar(45) NOT NULL,
  `address` varchar(45) default '-',
  `phone` varchar(45) default '-',
  `email` varchar(45) default '-',
  PRIMARY KEY  (`customerid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer`
--

/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` (`customerid`,`customername`,`address`,`phone`,`email`) VALUES 
 ('Cu-0000001','Thet Htarr Wai','Taunggyi','09451092954','thw@gmail.com'),
 ('CU-0000002','Than Lwin Oo','asdfasdf','09262277587','than@gmail.com'),
 ('CU-0000003','test user ','asdfasdf','0912316141','asdf@gamil.com'),
 ('CU-0000004','Kyaw Htet Thwin','Aung Thapyay Street','09778787878','Kyaw@gmail.com');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;


--
-- Definition of table `customerservicedetail`
--

DROP TABLE IF EXISTS `customerservicedetail`;
CREATE TABLE `customerservicedetail` (
  `customerid` varchar(45) NOT NULL,
  `serviceid` varchar(45) NOT NULL,
  `date` varchar(45) NOT NULL,
  `status` varchar(45) default NULL,
  KEY `FK_customerservicedetail_1` (`customerid`),
  KEY `FK_customerservicedetail_2` (`serviceid`),
  CONSTRAINT `FK_customerservicedetail_1` FOREIGN KEY (`customerid`) REFERENCES `customer` (`customerid`),
  CONSTRAINT `FK_customerservicedetail_2` FOREIGN KEY (`serviceid`) REFERENCES `service` (`serviceid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customerservicedetail`
--

/*!40000 ALTER TABLE `customerservicedetail` DISABLE KEYS */;
INSERT INTO `customerservicedetail` (`customerid`,`serviceid`,`date`,`status`) VALUES 
 ('Cu-0000001','SV-0000001','2024-04-19','-'),
 ('CU-0000003','SV-0000002','2024-04-19','-'),
 ('CU-0000002','SV-0000003','2024-04-19','-'),
 ('Cu-0000001','SV-0000004','2024-04-19','-'),
 ('CU-0000001','SV-0000005','2024-04-21','-');
/*!40000 ALTER TABLE `customerservicedetail` ENABLE KEYS */;


--
-- Definition of table `department`
--

DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `deptid` varchar(15) NOT NULL,
  `deptname` varchar(45) NOT NULL,
  PRIMARY KEY  (`deptid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `department`
--

/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` (`deptid`,`deptname`) VALUES 
 ('D-00000002','Sale'),
 ('DP-0000003','Service'),
 ('DP-0000004','Account');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;


--
-- Definition of table `item`
--

DROP TABLE IF EXISTS `item`;
CREATE TABLE `item` (
  `itemid` int(10) unsigned NOT NULL auto_increment,
  `brandid` varchar(45) NOT NULL,
  `typeid` varchar(45) NOT NULL,
  `itemname` varchar(45) NOT NULL,
  `currentprice` int(10) unsigned NOT NULL,
  `warranty` varchar(45) NOT NULL,
  `remark` varchar(500) NOT NULL,
  `serial` varchar(45) NOT NULL,
  PRIMARY KEY  (`itemid`),
  KEY `FK_item_1` (`brandid`),
  KEY `FK_item_2` (`typeid`),
  CONSTRAINT `FK_item_1` FOREIGN KEY (`brandid`) REFERENCES `brand` (`brandid`),
  CONSTRAINT `FK_item_2` FOREIGN KEY (`typeid`) REFERENCES `type` (`typeid`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `item`
--

/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` (`itemid`,`brandid`,`typeid`,`itemname`,`currentprice`,`warranty`,`remark`,`serial`) VALUES 
 (83,'BR-0000002','TY-0000003','Samsung S24',1000,'1 Year','Samsun Phone update','S2469898SUN'),
 (84,'BR-0000003','TY-0000003','Itel S24',100,'1 Year','This is a test for new function of purchase with new table','-'),
 (85,'BR-0000003','TY-0000003','Itel S24',100,'1 Year','This is a test for new function of purchase with new table','-'),
 (86,'BR-0000003','TY-0000003','Itel S24',100,'1 Year','This is a test for new function of purchase with new table','-'),
 (87,'BR-0000003','TY-0000003','Itel S24',100,'1 Year','This is a test for new function of purchase with new table','-'),
 (88,'BR-0000001','TY-0000002','M2 Laptop',1000,'3 Year','Single test','M2A9897141'),
 (89,'BR-0000001','TY-0000002','GTX 4060',500,'3 years','-','GT19981111'),
 (90,'BR-0000003','Ty-0000001','N1 Kanji Master',100,'1 Year','-','-'),
 (91,'BR-0000001','Ty-0000001','Nay Kyar Si',10,'2 years','-','-'),
 (92,'BR-0000001','Ty-0000001','Nay Kyar Si',10,'2 years','-','-'),
 (93,'BR-0000001','Ty-0000001','Nay Kyar Si',10,'2 years','-','-');
INSERT INTO `item` (`itemid`,`brandid`,`typeid`,`itemname`,`currentprice`,`warranty`,`remark`,`serial`) VALUES 
 (94,'BR-0000001','Ty-0000001','Nay Kyar Si',10,'2 years','-','-'),
 (95,'BR-0000001','Ty-0000001','Nay Kyar Si',10,'2 years','-','-'),
 (96,'BR-0000001','Ty-0000001','Nay Kyar Si',10,'2 years','-','-'),
 (97,'BR-0000001','Ty-0000001','Nay Kyar Si',10,'2 years','-','-'),
 (98,'BR-0000001','Ty-0000001','Nay Kyar Si',10,'2 years','-','-'),
 (99,'BR-0000001','Ty-0000001','Nay Kyar Si',10,'2 years','-','-'),
 (100,'BR-0000001','Ty-0000001','Nay Kyar Si',10,'2 years','-','-');
/*!40000 ALTER TABLE `item` ENABLE KEYS */;


--
-- Definition of table `payment`
--

DROP TABLE IF EXISTS `payment`;
CREATE TABLE `payment` (
  `paymentid` varchar(15) NOT NULL,
  `saleorderid` varchar(15) NOT NULL,
  `staffid` varchar(15) NOT NULL,
  `amount` int(10) unsigned NOT NULL,
  `paymenttype` varchar(45) NOT NULL,
  `remark` varchar(45) NOT NULL,
  PRIMARY KEY  (`paymentid`),
  KEY `FK_payment_2` (`staffid`),
  KEY `FK_payment_1` USING BTREE (`saleorderid`),
  CONSTRAINT `FK_payment_1` FOREIGN KEY (`saleorderid`) REFERENCES `saleorder` (`saleorderid`),
  CONSTRAINT `FK_payment_2` FOREIGN KEY (`staffid`) REFERENCES `staff` (`staffid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `payment`
--

/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` (`paymentid`,`saleorderid`,`staffid`,`amount`,`paymenttype`,`remark`) VALUES 
 ('PA-0000001','SA-0000005','ST-0000003',10,'Installment','installment test'),
 ('PA-0000002','SA-0000005','ST-0000003',120,'Installment','installment test');
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;


--
-- Definition of table `post`
--

DROP TABLE IF EXISTS `post`;
CREATE TABLE `post` (
  `postid` varchar(15) NOT NULL,
  `postname` varchar(45) NOT NULL,
  PRIMARY KEY  (`postid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `post`
--

/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` (`postid`,`postname`) VALUES 
 ('P-00000012','Security Officer'),
 ('P-00000013','General Officer'),
 ('P-00000014','Manager');
/*!40000 ALTER TABLE `post` ENABLE KEYS */;


--
-- Definition of table `purchase`
--

DROP TABLE IF EXISTS `purchase`;
CREATE TABLE `purchase` (
  `purchaseid` varchar(15) NOT NULL,
  `companyid` varchar(15) NOT NULL,
  `purchasedate` varchar(45) NOT NULL,
  PRIMARY KEY  (`purchaseid`),
  KEY `FK_purchase_1` USING BTREE (`companyid`),
  CONSTRAINT `FK_purchase_1` FOREIGN KEY (`companyid`) REFERENCES `company` (`companyid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `purchase`
--

/*!40000 ALTER TABLE `purchase` DISABLE KEYS */;
INSERT INTO `purchase` (`purchaseid`,`companyid`,`purchasedate`) VALUES 
 ('PR-0000001','CP-0000002','2024-04-20'),
 ('PR-0000002','CP-0000002','2024-04-21'),
 ('PR-0000003','CP-0000002','2024-04-22');
/*!40000 ALTER TABLE `purchase` ENABLE KEYS */;


--
-- Definition of table `purchasedetail`
--

DROP TABLE IF EXISTS `purchasedetail`;
CREATE TABLE `purchasedetail` (
  `purchaseid` varchar(15) NOT NULL,
  `itemname` varchar(45) NOT NULL,
  `purchaseprice` int(10) unsigned NOT NULL,
  `purchaseqty` int(10) unsigned NOT NULL,
  `itemid` int(10) unsigned NOT NULL,
  KEY `FK_purchasedetail_1` (`purchaseid`),
  KEY `FK_purchasedetail_2` (`itemid`),
  CONSTRAINT `FK_purchasedetail_2` FOREIGN KEY (`itemid`) REFERENCES `item` (`itemid`),
  CONSTRAINT `FK_purchasedetail_1` FOREIGN KEY (`purchaseid`) REFERENCES `purchase` (`purchaseid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `purchasedetail`
--

/*!40000 ALTER TABLE `purchasedetail` DISABLE KEYS */;
INSERT INTO `purchasedetail` (`purchaseid`,`itemname`,`purchaseprice`,`purchaseqty`,`itemid`) VALUES 
 ('PR-0000001','Itel S24',100,5,83),
 ('PR-0000001','Itel S24',100,5,84),
 ('PR-0000001','Itel S24',100,5,85),
 ('PR-0000001','Itel S24',100,5,86),
 ('PR-0000001','Itel S24',100,5,87),
 ('PR-0000001','M2 Laptop',1000,1,88),
 ('PR-0000002','GTX 4060',500,1,89),
 ('PR-0000003','N1 Kanji Master',100,1,90),
 ('PR-0000003','Nay Kyar Si',10,10,91),
 ('PR-0000003','Nay Kyar Si',10,10,92),
 ('PR-0000003','Nay Kyar Si',10,10,93),
 ('PR-0000003','Nay Kyar Si',10,10,94),
 ('PR-0000003','Nay Kyar Si',10,10,95),
 ('PR-0000003','Nay Kyar Si',10,10,96),
 ('PR-0000003','Nay Kyar Si',10,10,97),
 ('PR-0000003','Nay Kyar Si',10,10,98),
 ('PR-0000003','Nay Kyar Si',10,10,99),
 ('PR-0000003','Nay Kyar Si',10,10,100);
/*!40000 ALTER TABLE `purchasedetail` ENABLE KEYS */;


--
-- Definition of table `saleorder`
--

DROP TABLE IF EXISTS `saleorder`;
CREATE TABLE `saleorder` (
  `saleorderid` varchar(15) NOT NULL,
  `staffid` varchar(15) NOT NULL,
  `type` varchar(15) NOT NULL,
  `date` varchar(20) NOT NULL,
  `amount` int(10) unsigned NOT NULL default '0',
  PRIMARY KEY  (`saleorderid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `saleorder`
--

/*!40000 ALTER TABLE `saleorder` DISABLE KEYS */;
INSERT INTO `saleorder` (`saleorderid`,`staffid`,`type`,`date`,`amount`) VALUES 
 ('PA-0000002','ST-0000006','Full Payment','2024-04-22',0),
 ('SA-0000001','ST-0000005','Full Payment','2024-04-20',0),
 ('SA-0000002','ST-0000001','Full Payment','2024-04-21',0),
 ('SA-0000003','ST-0000005','Full Payment','2024-04-22',300),
 ('SA-0000004','ST-0000001','Full Payment','2024-04-22',520),
 ('SA-0000005','ST-0000001','Installment','2024-04-22',110);
/*!40000 ALTER TABLE `saleorder` ENABLE KEYS */;


--
-- Definition of table `saleorderdetail`
--

DROP TABLE IF EXISTS `saleorderdetail`;
CREATE TABLE `saleorderdetail` (
  `saleorderid` varchar(15) NOT NULL,
  `itemid` int(10) unsigned NOT NULL,
  `customerid` varchar(15) NOT NULL,
  `serial` varchar(45) NOT NULL,
  `price` int(10) unsigned NOT NULL default '0',
  KEY `FK_saleorderdetail_1` (`saleorderid`),
  KEY `FK_saleorderdetail_2` (`itemid`),
  KEY `FK_saleorderdetail_3` (`customerid`),
  CONSTRAINT `FK_saleorderdetail_1` FOREIGN KEY (`saleorderid`) REFERENCES `saleorder` (`saleorderid`),
  CONSTRAINT `FK_saleorderdetail_2` FOREIGN KEY (`itemid`) REFERENCES `item` (`itemid`),
  CONSTRAINT `FK_saleorderdetail_3` FOREIGN KEY (`customerid`) REFERENCES `customer` (`customerid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `saleorderdetail`
--

/*!40000 ALTER TABLE `saleorderdetail` DISABLE KEYS */;
INSERT INTO `saleorderdetail` (`saleorderid`,`itemid`,`customerid`,`serial`,`price`) VALUES 
 ('SA-0000001',88,'CU-0000002','M2A9897141',0),
 ('SA-0000002',83,'Cu-0000001','S2469898SUN',0),
 ('SA-0000003',84,'Cu-0000001','-',100),
 ('SA-0000003',85,'Cu-0000001','-',100),
 ('SA-0000003',86,'Cu-0000001','-',100),
 ('SA-0000004',100,'CU-0000002','-',10),
 ('SA-0000004',99,'CU-0000002','-',10),
 ('SA-0000004',89,'CU-0000002','GT19981111',500),
 ('SA-0000005',98,'CU-0000003','-',10),
 ('SA-0000005',87,'CU-0000003','-',100);
/*!40000 ALTER TABLE `saleorderdetail` ENABLE KEYS */;


--
-- Definition of table `service`
--

DROP TABLE IF EXISTS `service`;
CREATE TABLE `service` (
  `serviceid` varchar(15) NOT NULL,
  `item` varchar(45) NOT NULL,
  `staffid` varchar(15) NOT NULL,
  `price` int(10) unsigned default NULL,
  `bag` varchar(45) default NULL,
  `fault` varchar(45) NOT NULL,
  `charger` varchar(45) default NULL,
  `processor` varchar(45) default NULL,
  `ram` varchar(45) default NULL,
  `harddisk` varchar(45) default NULL,
  `serial` varchar(100) NOT NULL,
  `date` varchar(45) NOT NULL,
  `brand` varchar(45) NOT NULL,
  `warranty` varchar(45) NOT NULL,
  PRIMARY KEY  (`serviceid`),
  KEY `FK_service_1` (`staffid`),
  CONSTRAINT `FK_service_1` FOREIGN KEY (`staffid`) REFERENCES `staff` (`staffid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `service`
--

/*!40000 ALTER TABLE `service` DISABLE KEYS */;
INSERT INTO `service` (`serviceid`,`item`,`staffid`,`price`,`bag`,`fault`,`charger`,`processor`,`ram`,`harddisk`,`serial`,`date`,`brand`,`warranty`) VALUES 
 ('SV-0000001','Itel S23','ST-0000004',100,'just a bag','broken screen','charger 12w','3ghz','8gb','1000gb','SIO986188978','2024-04-19','Itel','No'),
 ('SV-0000002','GameSir t4','ST-0000003',100,'box and bag','x button not working','charing cable','-','-','-','UHIJIOJ-9891678','2024-04-19','GameSir','Yes'),
 ('SV-0000003','CHUNGHOP  aircon remote','ST-0000002',10,'no bag','no power','-','-','-','-','K-1028E','2024-04-19','CHUNGHOP','Yes'),
 ('SV-0000004','Test Item','ST-0000004',100000,'Test Bag','Test Fault','Test Charger','Test Processor','Test Ram','Test Hard Disk','Test Serial','2024-04-19','Test Brand','Yes'),
 ('SV-0000005','asdfasf','ST-0000002',1,'','sdfasdf','','','','','','2024-04-21','','Unknown');
/*!40000 ALTER TABLE `service` ENABLE KEYS */;


--
-- Definition of table `staff`
--

DROP TABLE IF EXISTS `staff`;
CREATE TABLE `staff` (
  `staffid` varchar(15) NOT NULL,
  `staffname` varchar(45) NOT NULL,
  `postid` varchar(15) NOT NULL,
  `deptid` varchar(15) NOT NULL,
  `email` varchar(45) NOT NULL,
  `phone` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  PRIMARY KEY  (`staffid`),
  KEY `FK_staff_1` (`postid`),
  KEY `FK_staff_2` (`deptid`),
  CONSTRAINT `FK_staff_1` FOREIGN KEY (`postid`) REFERENCES `post` (`postid`),
  CONSTRAINT `FK_staff_2` FOREIGN KEY (`deptid`) REFERENCES `department` (`deptid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `staff`
--

/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` (`staffid`,`staffname`,`postid`,`deptid`,`email`,`phone`,`address`) VALUES 
 ('ST-0000001','Hannay','P-00000012','D-00000002','ha@gmail.com','09778215747','Taunggyi'),
 ('ST-0000002','Red Gay','P-00000012','DP-0000003','redgay@gmal.com','0912673697','asdfasdfasdf'),
 ('ST-0000003','Black Gay','P-00000014','DP-0000004','asdf@gmail.com','0912341451','asdfasdfasdf'),
 ('ST-0000004','Actual Serviceman','P-00000012','DP-0000003','sadf@gmail.com','0917647614','sadfasdfasff'),
 ('ST-0000005','Sale Man Test','P-00000012','D-00000002','saleman@gmail.com','0917638761','address of an saleman'),
 ('ST-0000006','Red Gay 1','P-00000012','DP-0000004','asdf@gmail.com','09978126736','asdfasdf');
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;


--
-- Definition of table `type`
--

DROP TABLE IF EXISTS `type`;
CREATE TABLE `type` (
  `typeid` varchar(15) NOT NULL,
  `typename` varchar(45) NOT NULL,
  PRIMARY KEY  (`typeid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `type`
--

/*!40000 ALTER TABLE `type` DISABLE KEYS */;
INSERT INTO `type` (`typeid`,`typename`) VALUES 
 ('Ty-0000001','Mouse'),
 ('TY-0000002','Laptop'),
 ('TY-0000003','Phone'),
 ('TY-0000004','Tablet'),
 ('TY-0000005','Game Controller');
/*!40000 ALTER TABLE `type` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
