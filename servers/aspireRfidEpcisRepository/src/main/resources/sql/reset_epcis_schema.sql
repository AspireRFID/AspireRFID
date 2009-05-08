-- Copyright (c) 2008-2010, Aspire
--
-- This file contains the source code of the Accada library by ETH Zurich (www.accada.org),
-- licensed under the terms of the GNU Lesser General Public License version 2.1 in 2007
-- and modified for the needs of the Aspire project.
--
-- Aspire is free software; you can redistribute it and/or 
-- modify it under  the terms of the GNU Lesser General Public 
-- License version 2.1 as published by the Free Software Foundation (the 
-- "LGPL"). 
--
-- You should have received a copy of the GNU Lesser General Public 
-- License along with this library in the file COPYING-LGPL-2.1; if not, write to the Free Software 
-- Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301 USA. 
--
-- This software is distributed on an "AS IS" basis, WITHOUT WARRANTY 
-- OF ANY KIND, either express or implied. See the GNU Lesser General Public 
-- License for the specific language governing rights and limitations. 




/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema epcis
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ epcis;
USE epcis;

--
-- Table structure for table `epcis`.`BizTransaction`
--

DROP TABLE IF EXISTS `BizTransaction`;
CREATE TABLE `BizTransaction` (
  `id` bigint(20) NOT NULL auto_increment,
  `bizTrans` bigint(20) NOT NULL,
  `type` bigint(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `epcis`.`BizTransaction`
--

/*!40000 ALTER TABLE `BizTransaction` DISABLE KEYS */;
/*!40000 ALTER TABLE `BizTransaction` ENABLE KEYS */;


--
-- Table structure for table `epcis`.`event_AggregationEvent`
--

DROP TABLE IF EXISTS `event_AggregationEvent`;
CREATE TABLE `event_AggregationEvent` (
  `id` bigint(20) NOT NULL auto_increment,
  `eventTime` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `recordTime` timestamp NOT NULL default '0000-00-00 00:00:00',
  `eventTimeZoneOffset` varchar(8) NOT NULL,
  `parentID` varchar(1023) default NULL,
  `action` varchar(8) NOT NULL,
  `bizStep` bigint(20) default NULL,
  `disposition` bigint(20) default NULL,
  `readPoint` bigint(20) default NULL,
  `bizLocation` bigint(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `epcis`.`event_AggregationEvent`
--

/*!40000 ALTER TABLE `event_AggregationEvent` DISABLE KEYS */;
/*!40000 ALTER TABLE `event_AggregationEvent` ENABLE KEYS */;


--
-- Table structure for table `epcis`.`event_AggregationEvent_BizTrans`
--

DROP TABLE IF EXISTS `event_AggregationEvent_BizTrans`;
CREATE TABLE `event_AggregationEvent_BizTrans` (
  `event_id` bigint(20) NOT NULL,
  `bizTrans_id` bigint(20) NOT NULL,
  `idx` int(11) NOT NULL,
  KEY `event_id` (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `epcis`.`event_AggregationEvent_BizTrans`
--

/*!40000 ALTER TABLE `event_AggregationEvent_BizTrans` DISABLE KEYS */;
/*!40000 ALTER TABLE `event_AggregationEvent_BizTrans` ENABLE KEYS */;


--
-- Table structure for table `epcis`.`event_AggregationEvent_EPCs`
--

DROP TABLE IF EXISTS `event_AggregationEvent_EPCs`;
CREATE TABLE `event_AggregationEvent_EPCs` (
  `event_id` bigint(20) NOT NULL,
  `epc` varchar(1023) NOT NULL,
  `idx` int(11) NOT NULL,
  KEY `event_id` (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `epcis`.`event_AggregationEvent_EPCs`
--

/*!40000 ALTER TABLE `event_AggregationEvent_EPCs` DISABLE KEYS */;
/*!40000 ALTER TABLE `event_AggregationEvent_EPCs` ENABLE KEYS */;


--
-- Table structure for table `epcis`.`event_AggregationEvent_extensions`
--

DROP TABLE IF EXISTS `event_AggregationEvent_extensions`;
CREATE TABLE `event_AggregationEvent_extensions` (
  `id` bigint(20) NOT NULL auto_increment,
  `event_id` bigint(20) NOT NULL,
  `fieldname` varchar(128) NOT NULL,
  `prefix` varchar(32) NOT NULL,
  `intValue` int(11) default NULL,
  `floatValue` float default NULL,
  `dateValue` timestamp NULL default NULL,
  `strValue` varchar(1024) default NULL,
  PRIMARY KEY  (`id`),
  KEY `event_id` (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `epcis`.`event_AggregationEvent_extensions`
--

/*!40000 ALTER TABLE `event_AggregationEvent_extensions` DISABLE KEYS */;
/*!40000 ALTER TABLE `event_AggregationEvent_extensions` ENABLE KEYS */;


--
-- Table structure for table `epcis`.`event_ObjectEvent`
--

DROP TABLE IF EXISTS `event_ObjectEvent`;
CREATE TABLE `event_ObjectEvent` (
  `id` bigint(20) NOT NULL auto_increment,
  `eventTime` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `recordTime` timestamp NOT NULL default '0000-00-00 00:00:00',
  `eventTimeZoneOffset` varchar(8) NOT NULL,
  `action` varchar(8) NOT NULL,
  `bizStep` bigint(20) default NULL,
  `disposition` bigint(20) default NULL,
  `readPoint` bigint(20) default NULL,
  `bizLocation` bigint(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `epcis`.`event_ObjectEvent`
--

/*!40000 ALTER TABLE `event_ObjectEvent` DISABLE KEYS */;
/*!40000 ALTER TABLE `event_ObjectEvent` ENABLE KEYS */;


--
-- Table structure for table `epcis`.`event_ObjectEvent_BizTrans`
--

DROP TABLE IF EXISTS `event_ObjectEvent_BizTrans`;
CREATE TABLE `event_ObjectEvent_BizTrans` (
  `event_id` bigint(20) NOT NULL,
  `bizTrans_id` bigint(20) NOT NULL,
  `idx` int(11) NOT NULL,
  KEY `event_id` (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `epcis`.`event_ObjectEvent_BizTrans`
--

/*!40000 ALTER TABLE `event_ObjectEvent_BizTrans` DISABLE KEYS */;
/*!40000 ALTER TABLE `event_ObjectEvent_BizTrans` ENABLE KEYS */;


--
-- Table structure for table `epcis`.`event_ObjectEvent_EPCs`
--

DROP TABLE IF EXISTS `event_ObjectEvent_EPCs`;
CREATE TABLE `event_ObjectEvent_EPCs` (
  `event_id` bigint(20) NOT NULL,
  `epc` varchar(1023) NOT NULL,
  `idx` int(11) NOT NULL,
  KEY `event_id` (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `epcis`.`event_ObjectEvent_EPCs`
--

/*!40000 ALTER TABLE `event_ObjectEvent_EPCs` DISABLE KEYS */;
/*!40000 ALTER TABLE `event_ObjectEvent_EPCs` ENABLE KEYS */;


--
-- Table structure for table `epcis`.`event_ObjectEvent_extensions`
--

DROP TABLE IF EXISTS `event_ObjectEvent_extensions`;
CREATE TABLE `event_ObjectEvent_extensions` (
  `id` bigint(20) NOT NULL auto_increment,
  `event_id` bigint(20) NOT NULL,
  `fieldname` varchar(128) NOT NULL,
  `prefix` varchar(32) NOT NULL,
  `intValue` int(11) default NULL,
  `floatValue` float default NULL,
  `dateValue` timestamp NULL default NULL,
  `strValue` varchar(1024) default NULL,
  PRIMARY KEY  (`id`),
  KEY `event_id` (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `epcis`.`event_ObjectEvent_extensions`
--

/*!40000 ALTER TABLE `event_ObjectEvent_extensions` DISABLE KEYS */;
/*!40000 ALTER TABLE `event_ObjectEvent_extensions` ENABLE KEYS */;


--
-- Table structure for table `epcis`.`event_QuantityEvent`
--

DROP TABLE IF EXISTS `event_QuantityEvent`;
CREATE TABLE `event_QuantityEvent` (
  `id` bigint(20) NOT NULL auto_increment,
  `eventTime` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `recordTime` timestamp NOT NULL default '0000-00-00 00:00:00',
  `eventTimeZoneOffset` varchar(8) NOT NULL,
  `epcClass` bigint(20) NOT NULL,
  `quantity` bigint(20) NOT NULL,
  `bizStep` bigint(20) default NULL,
  `disposition` bigint(20) default NULL,
  `readPoint` bigint(20) default NULL,
  `bizLocation` bigint(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `epcis`.`event_QuantityEvent`
--

/*!40000 ALTER TABLE `event_QuantityEvent` DISABLE KEYS */;
/*!40000 ALTER TABLE `event_QuantityEvent` ENABLE KEYS */;


--
-- Table structure for table `epcis`.`event_QuantityEvent_BizTrans`
--

DROP TABLE IF EXISTS `event_QuantityEvent_BizTrans`;
CREATE TABLE `event_QuantityEvent_BizTrans` (
  `event_id` bigint(20) NOT NULL,
  `bizTrans_id` bigint(20) NOT NULL,
  `idx` int(11) NOT NULL,
  KEY `event_id` (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `epcis`.`event_QuantityEvent_BizTrans`
--

/*!40000 ALTER TABLE `event_QuantityEvent_BizTrans` DISABLE KEYS */;
/*!40000 ALTER TABLE `event_QuantityEvent_BizTrans` ENABLE KEYS */;


--
-- Table structure for table `epcis`.`event_QuantityEvent_extensions`
--

DROP TABLE IF EXISTS `event_QuantityEvent_extensions`;
CREATE TABLE `event_QuantityEvent_extensions` (
  `id` bigint(20) NOT NULL auto_increment,
  `event_id` bigint(20) NOT NULL,
  `fieldname` varchar(128) NOT NULL,
  `prefix` varchar(32) NOT NULL,
  `intValue` int(11) default NULL,
  `floatValue` float default NULL,
  `dateValue` timestamp NULL default NULL,
  `strValue` varchar(1024) default NULL,
  PRIMARY KEY  (`id`),
  KEY `event_id` (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `epcis`.`event_QuantityEvent_extensions`
--

/*!40000 ALTER TABLE `event_QuantityEvent_extensions` DISABLE KEYS */;
/*!40000 ALTER TABLE `event_QuantityEvent_extensions` ENABLE KEYS */;


--
-- Table structure for table `epcis`.`event_TransactionEvent`
--

DROP TABLE IF EXISTS `event_TransactionEvent`;
CREATE TABLE `event_TransactionEvent` (
  `id` bigint(20) NOT NULL auto_increment,
  `eventTime` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `recordTime` timestamp NOT NULL default '0000-00-00 00:00:00',
  `eventTimeZoneOffset` varchar(8) NOT NULL,
  `parentID` varchar(1023) default NULL,
  `action` varchar(8) NOT NULL,
  `bizStep` bigint(20) default NULL,
  `disposition` bigint(20) default NULL,
  `readPoint` bigint(20) default NULL,
  `bizLocation` bigint(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `epcis`.`event_TransactionEvent`
--

/*!40000 ALTER TABLE `event_TransactionEvent` DISABLE KEYS */;
/*!40000 ALTER TABLE `event_TransactionEvent` ENABLE KEYS */;


--
-- Table structure for table `epcis`.`event_TransactionEvent_BizTrans`
--

DROP TABLE IF EXISTS `event_TransactionEvent_BizTrans`;
CREATE TABLE `event_TransactionEvent_BizTrans` (
  `event_id` bigint(20) NOT NULL,
  `bizTrans_id` bigint(20) NOT NULL,
  `idx` int(11) NOT NULL,
  KEY `event_id` (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `epcis`.`event_TransactionEvent_BizTrans`
--

/*!40000 ALTER TABLE `event_TransactionEvent_BizTrans` DISABLE KEYS */;
/*!40000 ALTER TABLE `event_TransactionEvent_BizTrans` ENABLE KEYS */;


--
-- Table structure for table `epcis`.`event_TransactionEvent_EPCs`
--

DROP TABLE IF EXISTS `event_TransactionEvent_EPCs`;
CREATE TABLE `event_TransactionEvent_EPCs` (
  `event_id` bigint(20) NOT NULL,
  `epc` varchar(1023) NOT NULL,
  `idx` int(11) NOT NULL,
  KEY `event_id` (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `epcis`.`event_TransactionEvent_EPCs`
--

/*!40000 ALTER TABLE `event_TransactionEvent_EPCs` DISABLE KEYS */;
/*!40000 ALTER TABLE `event_TransactionEvent_EPCs` ENABLE KEYS */;


--
-- Table structure for table `epcis`.`event_TransactionEvent_extensions`
--

DROP TABLE IF EXISTS `event_TransactionEvent_extensions`;
CREATE TABLE `event_TransactionEvent_extensions` (
  `id` bigint(20) NOT NULL auto_increment,
  `event_id` bigint(20) NOT NULL,
  `fieldname` varchar(128) NOT NULL,
  `prefix` varchar(32) NOT NULL,
  `intValue` int(11) default NULL,
  `floatValue` float default NULL,
  `dateValue` timestamp NULL default NULL,
  `strValue` varchar(1024) default NULL,
  PRIMARY KEY  (`id`),
  KEY `event_id` (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `epcis`.`event_TransactionEvent_extensions`
--

/*!40000 ALTER TABLE `event_TransactionEvent_extensions` DISABLE KEYS */;
/*!40000 ALTER TABLE `event_TransactionEvent_extensions` ENABLE KEYS */;


--
-- Table structure for table `epcis`.`subscription`
--

DROP TABLE IF EXISTS `subscription`;
CREATE TABLE `subscription` (
  `subscriptionid` varchar(767) NOT NULL,
  `params` blob,
  `dest` varchar(1023) default NULL,
  `sched` blob,
  `trigg` varchar(1023) default NULL,
  `initialrecordingtime` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `exportifempty` tinyint(1) default NULL,
  `queryname` varchar(1023) default NULL,
  `lastexecuted` timestamp NOT NULL default '0000-00-00 00:00:00',
  PRIMARY KEY  (`subscriptionid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `epcis`.`subscription`
--

/*!40000 ALTER TABLE `subscription` DISABLE KEYS */;
/*!40000 ALTER TABLE `subscription` ENABLE KEYS */;


--
-- Table structure for table `epcis`.`voc_Any`
--

DROP TABLE IF EXISTS `voc_Any`;
CREATE TABLE `voc_Any` (
  `id` bigint(20) NOT NULL auto_increment,
  `uri` varchar(1023) NOT NULL,
  `vtype` varchar(1023) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `epcis`.`voc_Any`
--

/*!40000 ALTER TABLE `voc_Any` DISABLE KEYS */;
/*!40000 ALTER TABLE `voc_Any` ENABLE KEYS */;


--
-- Table structure for table `epcis`.`voc_Any_attr`
--

DROP TABLE IF EXISTS `voc_Any_attr`;
CREATE TABLE `voc_Any_attr` (
  `id` bigint(20) NOT NULL,
  `attribute` varchar(1023) NOT NULL,
  `value` varchar(1023) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `epcis`.`voc_Any_attr`
--

/*!40000 ALTER TABLE `voc_Any_attr` DISABLE KEYS */;
/*!40000 ALTER TABLE `voc_Any_attr` ENABLE KEYS */;


--
-- Table structure for table `epcis`.`voc_BizLoc`
--

DROP TABLE IF EXISTS `voc_BizLoc`;
CREATE TABLE `voc_BizLoc` (
  `id` bigint(20) NOT NULL auto_increment,
  `uri` varchar(1023) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `epcis`.`voc_BizLoc`
--

/*!40000 ALTER TABLE `voc_BizLoc` DISABLE KEYS */;
/*!40000 ALTER TABLE `voc_BizLoc` ENABLE KEYS */;


--
-- Table structure for table `epcis`.`voc_BizLoc_attr`
--

DROP TABLE IF EXISTS `voc_BizLoc_attr`;
CREATE TABLE `voc_BizLoc_attr` (
  `id` bigint(20) NOT NULL,
  `attribute` varchar(1023) NOT NULL,
  `value` varchar(1023) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `epcis`.`voc_BizLoc_attr`
--

/*!40000 ALTER TABLE `voc_BizLoc_attr` DISABLE KEYS */;
/*!40000 ALTER TABLE `voc_BizLoc_attr` ENABLE KEYS */;


--
-- Table structure for table `epcis`.`voc_BizStep`
--

DROP TABLE IF EXISTS `voc_BizStep`;
CREATE TABLE `voc_BizStep` (
  `id` bigint(20) NOT NULL auto_increment,
  `uri` varchar(1023) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `epcis`.`voc_BizStep`
--

/*!40000 ALTER TABLE `voc_BizStep` DISABLE KEYS */;
/*!40000 ALTER TABLE `voc_BizStep` ENABLE KEYS */;


--
-- Table structure for table `epcis`.`voc_BizStep_attr`
--

DROP TABLE IF EXISTS `voc_BizStep_attr`;
CREATE TABLE `voc_BizStep_attr` (
  `id` bigint(20) NOT NULL,
  `attribute` varchar(1023) NOT NULL,
  `value` varchar(1023) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `epcis`.`voc_BizStep_attr`
--

/*!40000 ALTER TABLE `voc_BizStep_attr` DISABLE KEYS */;
/*!40000 ALTER TABLE `voc_BizStep_attr` ENABLE KEYS */;


--
-- Table structure for table `epcis`.`voc_BizTrans`
--

DROP TABLE IF EXISTS `voc_BizTrans`;
CREATE TABLE `voc_BizTrans` (
  `id` bigint(20) NOT NULL auto_increment,
  `uri` varchar(1023) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `epcis`.`voc_BizTrans`
--

/*!40000 ALTER TABLE `voc_BizTrans` DISABLE KEYS */;
/*!40000 ALTER TABLE `voc_BizTrans` ENABLE KEYS */;


--
-- Table structure for table `epcis`.`voc_BizTrans_attr`
--

DROP TABLE IF EXISTS `voc_BizTrans_attr`;
CREATE TABLE `voc_BizTrans_attr` (
  `id` bigint(20) NOT NULL,
  `attribute` varchar(1023) NOT NULL,
  `value` varchar(1023) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `epcis`.`voc_BizTrans_attr`
--

/*!40000 ALTER TABLE `voc_BizTrans_attr` DISABLE KEYS */;
/*!40000 ALTER TABLE `voc_BizTrans_attr` ENABLE KEYS */;


--
-- Table structure for table `epcis`.`voc_BizTransType`
--

DROP TABLE IF EXISTS `voc_BizTransType`;
CREATE TABLE `voc_BizTransType` (
  `id` bigint(20) NOT NULL auto_increment,
  `uri` varchar(1023) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `epcis`.`voc_BizTransType`
--

/*!40000 ALTER TABLE `voc_BizTransType` DISABLE KEYS */;
/*!40000 ALTER TABLE `voc_BizTransType` ENABLE KEYS */;


--
-- Table structure for table `epcis`.`voc_BizTransType_attr`
--

DROP TABLE IF EXISTS `voc_BizTransType_attr`;
CREATE TABLE `voc_BizTransType_attr` (
  `id` bigint(20) NOT NULL,
  `attribute` varchar(1023) NOT NULL,
  `value` varchar(1023) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `epcis`.`voc_BizTransType_attr`
--

/*!40000 ALTER TABLE `voc_BizTransType_attr` DISABLE KEYS */;
/*!40000 ALTER TABLE `voc_BizTransType_attr` ENABLE KEYS */;


--
-- Table structure for table `epcis`.`voc_Disposition`
--

DROP TABLE IF EXISTS `voc_Disposition`;
CREATE TABLE `voc_Disposition` (
  `id` bigint(20) NOT NULL auto_increment,
  `uri` varchar(1023) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `epcis`.`voc_Disposition`
--

/*!40000 ALTER TABLE `voc_Disposition` DISABLE KEYS */;
/*!40000 ALTER TABLE `voc_Disposition` ENABLE KEYS */;


--
-- Table structure for table `epcis`.`voc_Disposition_attr`
--

DROP TABLE IF EXISTS `voc_Disposition_attr`;
CREATE TABLE `voc_Disposition_attr` (
  `id` bigint(20) NOT NULL,
  `attribute` varchar(1023) NOT NULL,
  `value` varchar(1023) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `epcis`.`voc_Disposition_attr`
--

/*!40000 ALTER TABLE `voc_Disposition_attr` DISABLE KEYS */;
/*!40000 ALTER TABLE `voc_Disposition_attr` ENABLE KEYS */;


--
-- Table structure for table `epcis`.`voc_EPCClass`
--

DROP TABLE IF EXISTS `voc_EPCClass`;
CREATE TABLE `voc_EPCClass` (
  `id` bigint(20) NOT NULL auto_increment,
  `uri` varchar(1023) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `epcis`.`voc_EPCClass`
--

/*!40000 ALTER TABLE `voc_EPCClass` DISABLE KEYS */;
/*!40000 ALTER TABLE `voc_EPCClass` ENABLE KEYS */;


--
-- Table structure for table `epcis`.`voc_EPCClass_attr`
--

DROP TABLE IF EXISTS `voc_EPCClass_attr`;
CREATE TABLE `voc_EPCClass_attr` (
  `id` bigint(20) NOT NULL,
  `attribute` varchar(1023) NOT NULL,
  `value` varchar(1023) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `epcis`.`voc_EPCClass_attr`
--

/*!40000 ALTER TABLE `voc_EPCClass_attr` DISABLE KEYS */;
/*!40000 ALTER TABLE `voc_EPCClass_attr` ENABLE KEYS */;


--
-- Table structure for table `epcis`.`voc_ReadPoint`
--

DROP TABLE IF EXISTS `voc_ReadPoint`;
CREATE TABLE `voc_ReadPoint` (
  `id` bigint(20) NOT NULL auto_increment,
  `uri` varchar(1023) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `epcis`.`voc_ReadPoint`
--

/*!40000 ALTER TABLE `voc_ReadPoint` DISABLE KEYS */;
/*!40000 ALTER TABLE `voc_ReadPoint` ENABLE KEYS */;


--
-- Table structure for table `epcis`.`voc_ReadPoint_attr`
--

DROP TABLE IF EXISTS `voc_ReadPoint_attr`;
CREATE TABLE `voc_ReadPoint_attr` (
  `id` bigint(20) NOT NULL,
  `attribute` varchar(1023) NOT NULL,
  `value` varchar(1023) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `epcis`.`voc_ReadPoint_attr`
--

/*!40000 ALTER TABLE `voc_ReadPoint_attr` DISABLE KEYS */;
/*!40000 ALTER TABLE `voc_ReadPoint_attr` ENABLE KEYS */;


--
-- Table structure for table `epcis`.`vocabularies`
--

DROP TABLE IF EXISTS `vocabularies`;
CREATE TABLE `vocabularies` (
  `id` bigint(20) NOT NULL auto_increment,
  `uri` varchar(1023) NOT NULL,
  `table_name` varchar(128) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `epcis`.`vocabularies`
--

/*!40000 ALTER TABLE `vocabularies` DISABLE KEYS */;
/*!40000 ALTER TABLE `vocabularies` ENABLE KEYS */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;