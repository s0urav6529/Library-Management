-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               6.0.9-alpha-community - MySQL Community Server (GPL)
-- Server OS:                    Win32
-- HeidiSQL version:             7.0.0.4053
-- Date/time:                    2018-04-12 19:03:10
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;

-- Dumping database structure for librarymanagement
CREATE DATABASE IF NOT EXISTS `librarymanagement` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `librarymanagement`;


-- Dumping structure for table librarymanagement.tbbooksinfo
CREATE TABLE IF NOT EXISTS `tbbooksinfo` (
  `autoid` int(100) NOT NULL AUTO_INCREMENT,
  `Bookid` varchar(100) NOT NULL DEFAULT '',
  `subcatagoryid` varchar(100) DEFAULT NULL,
  `subcatagoryname` varchar(100) DEFAULT NULL,
  `catagoryid` varchar(100) DEFAULT NULL,
  `catagoryname` varchar(100) DEFAULT NULL,
  `bookname` varchar(100) DEFAULT NULL,
  `bookdescription` varchar(100) DEFAULT NULL,
  `unit` double DEFAULT NULL,
  `dealerprice` double DEFAULT NULL,
  `tradeprice` double DEFAULT NULL,
  `profitperunit` double DEFAULT NULL,
  `Supplierid` varchar(100) DEFAULT NULL,
  `suppliername` varchar(100) DEFAULT NULL,
  `username` varchar(100) DEFAULT NULL,
  `entrytime` datetime DEFAULT NULL,
  `userip` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Bookid`),
  UNIQUE KEY `autoid` (`autoid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- Dumping data for table librarymanagement.tbbooksinfo: ~4 rows (approximately)
/*!40000 ALTER TABLE `tbbooksinfo` DISABLE KEYS */;
INSERT INTO `tbbooksinfo` (`autoid`, `Bookid`, `subcatagoryid`, `subcatagoryname`, `catagoryid`, `catagoryname`, `bookname`, `bookdescription`, `unit`, `dealerprice`, `tradeprice`, `profitperunit`, `Supplierid`, `suppliername`, `username`, `entrytime`, `userip`) VALUES
	(2, 'Book-1', 'subcat-5', 'Buddisiam', 'cat-4', 'Religion', 'HInd', 'ww', 3, 432, 455, 12, 'Supllier-2', 'Foyez Ullah Nishan', 'sourav', '2017-11-16 20:01:52', ''),
	(3, 'Book-2', 'subcat-1', 'Bangla-1st', 'cat-1', 'Bangla', 'Shahitto', 'd', 12, 12, 14, 2, 'Supllier-2', 'Foyez Ullah Nishan', 'Sourav', '2017-11-16 20:16:59', ''),
	(4, 'Book-3', 'subcat-7', 'Sonshoptok', 'cat-7', 'Poem', 'Bideshini', '1 column', 5, 70.5, 75, 4.5, 'Supllier-2', 'Foyez Ullah Nishan', 'Sourav', '2018-03-21 21:09:36', ''),
	(5, 'Book-4', 'subcat-6', 'English-1st', 'cat-2', 'English', 'English Comuunication', 'Composition Type', 5, 450, 490, 40, 'Supllier-1', 'Sourav Mojumder', 'sourav', '2018-03-21 21:11:51', '');
/*!40000 ALTER TABLE `tbbooksinfo` ENABLE KEYS */;


-- Dumping structure for table librarymanagement.tbcatagoryinfo
CREATE TABLE IF NOT EXISTS `tbcatagoryinfo` (
  `autoid` int(100) NOT NULL AUTO_INCREMENT,
  `catagoryid` varchar(100) NOT NULL DEFAULT '',
  `catagroyname` varchar(100) DEFAULT NULL,
  `username` varchar(100) DEFAULT NULL,
  `entrytime` datetime DEFAULT NULL,
  `userip` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`catagoryid`),
  UNIQUE KEY `autoid` (`autoid`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

-- Dumping data for table librarymanagement.tbcatagoryinfo: ~7 rows (approximately)
/*!40000 ALTER TABLE `tbcatagoryinfo` DISABLE KEYS */;
INSERT INTO `tbcatagoryinfo` (`autoid`, `catagoryid`, `catagroyname`, `username`, `entrytime`, `userip`) VALUES
	(7, 'cat-1', 'Bangla', 'Sourav', '2017-11-09 23:01:37', ''),
	(10, 'cat-2', 'English', 'Sourav', '2017-11-09 23:05:50', ''),
	(11, 'cat-3', 'Social Science', 'Sourav', '2017-11-09 23:06:04', ''),
	(12, 'cat-4', 'Religion', 'Sourav', '2017-11-09 23:21:33', ''),
	(13, 'cat-5', 'Mathmathics', 'Sourav', '2017-12-21 20:14:44', ''),
	(14, 'cat-6', 'Physics', 'Sourav', '2018-01-31 13:51:12', ''),
	(15, 'cat-7', 'Poem', 'Sourav', '2018-03-21 21:07:59', '');
/*!40000 ALTER TABLE `tbcatagoryinfo` ENABLE KEYS */;


-- Dumping structure for table librarymanagement.tbnewuserinfo
CREATE TABLE IF NOT EXISTS `tbnewuserinfo` (
  `autoid` int(10) NOT NULL AUTO_INCREMENT,
  `newuserid` varchar(100) NOT NULL DEFAULT '',
  `name` varchar(100) DEFAULT NULL,
  `username` varchar(100) DEFAULT NULL,
  `designation` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `passwordhint` varchar(100) DEFAULT NULL,
  `picture` varchar(100) DEFAULT '0',
  `dateofjoin` date DEFAULT NULL,
  `activation` varchar(100) DEFAULT NULL,
  `emailaddress` varchar(100) DEFAULT NULL,
  `nationalid` varchar(100) DEFAULT NULL,
  `usertype` varchar(100) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `mobile` varchar(100) DEFAULT NULL,
  `entrytime` datetime DEFAULT NULL,
  `userip` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`newuserid`),
  UNIQUE KEY `autoid` (`autoid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

-- Dumping data for table librarymanagement.tbnewuserinfo: ~2 rows (approximately)
/*!40000 ALTER TABLE `tbnewuserinfo` DISABLE KEYS */;
INSERT INTO `tbnewuserinfo` (`autoid`, `newuserid`, `name`, `username`, `designation`, `password`, `passwordhint`, `picture`, `dateofjoin`, `activation`, `emailaddress`, `nationalid`, `usertype`, `address`, `mobile`, `entrytime`, `userip`) VALUES
	(5, 'User-1', 'sourav', 'sourav mojumder', 'ads', 'sourav', 'sourav', 'User-1.jpg', '2016-12-03', 'No', 'sourav@gmail.com', '0932084937', 'Admin', 'ctg', '01837885414', '2017-12-21 20:55:24', ''),
	(7, 'User-2', 'utsha roy', 'Uthsha Roy', 'x', 'ut', 'utsha', 'User-2.jpg', '2016-12-03', 'No', 'UTsha@gmail.com', '029910920810', 'Admin', 'Dhaka', '019837488231', '2018-03-21 21:12:40', '');
/*!40000 ALTER TABLE `tbnewuserinfo` ENABLE KEYS */;


-- Dumping structure for table librarymanagement.tbreturnerinfo
CREATE TABLE IF NOT EXISTS `tbreturnerinfo` (
  `autoid` int(100) NOT NULL AUTO_INCREMENT,
  `returnerid` varchar(100) NOT NULL DEFAULT '0',
  `mailaddress` varchar(100) DEFAULT NULL,
  `phonenumber` varchar(100) DEFAULT NULL,
  `borrowedbooksno` varchar(100) DEFAULT NULL,
  `booksdescription` varchar(100) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `referenceby` varchar(100) DEFAULT NULL,
  `username` varchar(100) DEFAULT NULL,
  `entrytime` datetime DEFAULT NULL,
  `userip` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`returnerid`),
  UNIQUE KEY `autoid` (`autoid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table librarymanagement.tbreturnerinfo: ~0 rows (approximately)
/*!40000 ALTER TABLE `tbreturnerinfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbreturnerinfo` ENABLE KEYS */;


-- Dumping structure for table librarymanagement.tbsubcatagoryinfo
CREATE TABLE IF NOT EXISTS `tbsubcatagoryinfo` (
  `autoid` int(100) NOT NULL AUTO_INCREMENT,
  `catagoryid` varchar(100) NOT NULL DEFAULT '',
  `catagoryname` varchar(100) DEFAULT NULL,
  `subcatagoryid` varchar(100) NOT NULL DEFAULT '',
  `subcatagoryname` varchar(100) DEFAULT NULL,
  `username` varchar(100) DEFAULT NULL,
  `entrytime` datetime DEFAULT NULL,
  `userip` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`subcatagoryid`),
  UNIQUE KEY `autoid` (`autoid`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

-- Dumping data for table librarymanagement.tbsubcatagoryinfo: ~7 rows (approximately)
/*!40000 ALTER TABLE `tbsubcatagoryinfo` DISABLE KEYS */;
INSERT INTO `tbsubcatagoryinfo` (`autoid`, `catagoryid`, `catagoryname`, `subcatagoryid`, `subcatagoryname`, `username`, `entrytime`, `userip`) VALUES
	(3, 'cat-1', 'Bangla', 'subcat-1', 'Bangla-1st', 'sourav', '2017-11-09 23:56:46', ''),
	(5, 'cat-1', 'Bangla', 'subcat-2', 'Bangla-2nd', 'sourav', '2017-11-15 21:09:40', ''),
	(10, 'cat-3', 'Social Science', 'subcat-3', 'Social Society', 'Sourav', '2017-11-15 21:14:40', ''),
	(8, 'cat-4', 'Religion', 'subcat-4', 'Hindusiam', 'sourav', '2017-11-15 21:12:02', ''),
	(9, 'cat-4', 'Religion', 'subcat-5', 'Buddisiam', 'Sourav', '2017-11-15 21:12:19', ''),
	(16, 'cat-2', 'English', 'subcat-6', 'English-1st', 'sourav', '2017-11-15 21:23:05', ''),
	(17, 'cat-7', 'Poem', 'subcat-7', 'Sonshoptok', 'sourav', '2018-03-21 21:08:28', '');
/*!40000 ALTER TABLE `tbsubcatagoryinfo` ENABLE KEYS */;


-- Dumping structure for table librarymanagement.tbsupplierinfo
CREATE TABLE IF NOT EXISTS `tbsupplierinfo` (
  `autoid` int(10) NOT NULL AUTO_INCREMENT,
  `supplierid` varchar(50) NOT NULL DEFAULT '',
  `suppliername` varchar(50) NOT NULL DEFAULT '',
  `mailid` varchar(50) DEFAULT NULL,
  `picture` varchar(50) DEFAULT '0',
  `address` varchar(50) DEFAULT NULL,
  `phonenumber` varchar(50) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `userip` varchar(50) DEFAULT NULL,
  `entrytime` datetime DEFAULT NULL,
  PRIMARY KEY (`supplierid`),
  UNIQUE KEY `autoid` (`autoid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- Dumping data for table librarymanagement.tbsupplierinfo: ~2 rows (approximately)
/*!40000 ALTER TABLE `tbsupplierinfo` DISABLE KEYS */;
INSERT INTO `tbsupplierinfo` (`autoid`, `supplierid`, `suppliername`, `mailid`, `picture`, `address`, `phonenumber`, `username`, `userip`, `entrytime`) VALUES
	(3, 'Supllier-1', 'Sourav Mojumder', 'sourav@gmail.com', 'Supllier-1.jpg', 'Ctg', '01987678435', 'Sourav', '', '2017-11-15 23:00:42'),
	(4, 'Supllier-2', 'Foyez Ullah Nishan', 'Foyez@gmail.com', 'Supllier-2.jpg', 'Dhaka', '01897235634', 'Sourav', '', '2017-11-16 19:39:23');
/*!40000 ALTER TABLE `tbsupplierinfo` ENABLE KEYS */;
/*!40014 SET FOREIGN_KEY_CHECKS=1 */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
