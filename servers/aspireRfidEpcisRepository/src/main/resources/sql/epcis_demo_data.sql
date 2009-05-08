-- Copyright (c) 2008-2010, Aspire
--
-- Aspire is free software; you can redistribute it and/or modify it under the
-- terms of the GNU Lesser General Public License version 2.1 as published by
-- the Free Software Foundation (the "LGPL").
--
-- You should have received a copy of the GNU Lesser General Public License
-- along with this library in the file COPYING-LGPL-2.1; if not, write to the
-- Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
-- 02110-1301 USA.
--
-- This software is distributed on an "AS IS" basis, WITHOUT WARRANTY OF ANY
-- KIND, either express or implied. See the GNU Lesser General Public License
-- for the specific language governing rights and limitations.



/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

--
-- Dumping data for table `epcis`.`voc_BizLoc`
--

/*!40000 ALTER TABLE `voc_BizLoc` DISABLE KEYS */;
INSERT INTO `voc_BizLoc` (`id`,`uri`) VALUES 
 (3,'urn:epcglobal:fmcg:loc:greece:pireus:mainacme'),
 (4,'urn:epcglobal:fmcg:loc:greece:pireus:mainacme,urn:epcglobal:fmcg:loc:acme:warehouse1'),
 (5,'urn:epcglobal:fmcg:loc:greece:pireus:mainacme,urn:epcglobal:fmcg:loc:acme:warehouse2'),
 (7,'urn:epcglobal:fmcg:loc:greece:pireus:mainacme,urn:epcglobal:fmcg:loc:acme:warehouse3');
/*!40000 ALTER TABLE `voc_BizLoc` ENABLE KEYS */;


--
-- Dumping data for table `epcis`.`voc_BizLoc_attr`
--

/*!40000 ALTER TABLE `voc_BizLoc_attr` DISABLE KEYS */;
INSERT INTO `voc_BizLoc_attr` (`id`,`attribute`,`value`) VALUES 
 (3,'urn:epcglobal:epcis:mda:Name','Acme'),
 (3,'urn:epcglobal:epcis:mda:Address','Akadimias 3'),
 (3,'urn:epcglobal:epcis:mda:City','Pireus'),
 (3,'urn:epcglobal:epcis:mda:Country','Greece'),
 (3,'urn:epcglobal:epcis:mda:Read Point','urn:epcglobal:fmcg:loc:45632.Warehouse1DocDoor_urn:epcglobal:fmcg:loc:06141.Warehouse2DocDoor_'),
 (4,'urn:epcglobal:epcis:mda:Name','AcmeWarehouse1'),
 (4,'urn:epcglobal:epcis:mda:Read Point','urn:epcglobal:fmcg:loc:45632.Warehouse1DocDoor_'),
 (5,'urn:epcglobal:epcis:mda:Name','AcmeWarehouse2'),
 (5,'urn:epcglobal:epcis:mda:Read Point','urn:epcglobal:fmcg:loc:06141.Warehouse2DocDoor'),
 (7,'urn:epcglobal:epcis:mda:Name','AcmeWarehouse3'),
 (7,'urn:epcglobal:epcis:mda:Read Point','urn:epcglobal:fmcg:loc:rp:warehouse3docdoor_');
/*!40000 ALTER TABLE `voc_BizLoc_attr` ENABLE KEYS */;


--
-- Dumping data for table `epcis`.`voc_BizStep`
--

/*!40000 ALTER TABLE `voc_BizStep` DISABLE KEYS */;
INSERT INTO `voc_BizStep` (`id`,`uri`) VALUES 
 (1,'urn:epcglobal:fmcg:bizstep:receiving'),
 (2,'urn:epcglobal:fmcg:bizstep:picking'),
 (3,'urn:epcglobal:fmcg:bizstep:shipping'),
 (4,'urn:fosstrak:demo:bizstep:fmcg:shipment'),
 (5,'urn:fosstrak:demo:bizstep:fmcg:production'),
 (6,'urn:fosstrak:demo:bizstep:fmcg:accepting'),
 (7,'urn:fosstrak:demo:bizstep:fmcg:inspecting'),
 (8,'urn:fosstrak:demo:bizstep:fmcg:storing'),
 (9,'urn:fosstrak:demo:bizstep:fmcg:packing'),
 (10,'urn:fosstrak:demo:bizstep:fmcg:loading'),
 (11,'urn:fosstrak:demo:bizstep:fmcg:commissioning'),
 (12,'urn:fosstrak:demo:bizstep:fmcg:decommissioning'),
 (13,'urn:fosstrak:demo:bizstep:fmcg:destroying');
/*!40000 ALTER TABLE `voc_BizStep` ENABLE KEYS */;



-- Dumping data for table `epcis`.`voc_BizStep_attr`
--

/*!40000 ALTER TABLE `voc_BizStep_attr` DISABLE KEYS */;
INSERT INTO `voc_BizStep_attr` (`id`,`attribute`,`value`) VALUES 
 (1,'urn:epcglobal:epcis:mda:Name','receiving'),
 (2,'urn:epcglobal:epcis:mda:Name','picking'),
 (3,'urn:epcglobal:epcis:mda:Name','shipping'),
 (4,'urn:epcglobal:epcis:mda:Name','shipment'),
 (5,'urn:epcglobal:epcis:mda:Name','production'),
 (6,'urn:epcglobal:epcis:mda:Name','accepting'),
 (7,'urn:epcglobal:epcis:mda:Name','inspecting'),
 (8,'urn:epcglobal:epcis:mda:Name','storing'),
 (9,'urn:epcglobal:epcis:mda:Name','packing'),
 (10,'urn:epcglobal:epcis:mda:Name','loading'),
 (11,'urn:epcglobal:epcis:mda:Name','commissioning'),
 (12,'urn:epcglobal:epcis:mda:Name','decommissioning'),
 (13,'urn:epcglobal:epcis:mda:Name','destroying');
/*!40000 ALTER TABLE `voc_BizStep_attr` ENABLE KEYS */;



--
-- Dumping data for table `epcis`.`voc_BizTrans`
--

/*!40000 ALTER TABLE `voc_BizTrans` DISABLE KEYS */;
INSERT INTO `voc_BizTrans` (`id`,`uri`) VALUES 
 (5,'urn:epcglobal:fmcg:bti:acmesupplying'),
 (6,'urn:epcglobal:fmcg:bte:acmewarehouse1receive'),
 (7,'urn:epcglobal:fmcg:bte:acmewarehouse2ship');
/*!40000 ALTER TABLE `voc_BizTrans` ENABLE KEYS */;



--
-- Dumping data for table `epcis`.`voc_BizTrans_attr`
--

/*!40000 ALTER TABLE `voc_BizTrans_attr` DISABLE KEYS */;
INSERT INTO `voc_BizTrans_attr` (`id`,`attribute`,`value`) VALUES 
 (5,'urn:epcglobal:epcis:mda:Name','AcmeSupplyingTrans'),
 (6,'urn:epcglobal:epcis:mda:ecreport_names','bizTransactionIDs_1234,transactionItems_1234'),
 (5,'urn:epcglobal:epcis:mda:Children','urn:epcglobal:fmcg:bte:acmewarehouse1receive,urn:epcglobal:fmcg:bte:acmewarehouse2ship'),
 (6,'urn:epcglobal:epcis:mda:event_name','Warehouse1DocDoorReceive'),
 (6,'urn:epcglobal:epcis:mda:event_type','ObjectEvent'),
 (6,'urn:epcglobal:epcis:mda:business_step','urn:epcglobal:fmcg:bizstep:receiving'),
 (6,'urn:epcglobal:epcis:mda:business_location','urn:epcglobal:fmcg:loc:acme:warehouse1'),
 (6,'urn:epcglobal:epcis:mda:disposition','urn:epcglobal:fmcg:disp:in_progress'),
 (6,'urn:epcglobal:epcis:mda:ecspec_name','ECSpecObjectEventFiltering'),
 (6,'urn:epcglobal:epcis:mda:read_point','urn:epcglobal:fmcg:loc:45632.Warehouse1DocDoor'),
 (6,'urn:epcglobal:epcis:mda:transaction_type','urn:epcglobal:fmcg:btt:receiving'),
 (6,'urn:epcglobal:epcis:mda:action','ADD'),
 (7,'urn:epcglobal:epcis:mda:ecreport_names','transactionItems_6734,bizTransactionIDs_6734');
INSERT INTO `voc_BizTrans_attr` (`id`,`attribute`,`value`) VALUES
 (7,'urn:epcglobal:epcis:mda:event_name','Warehouse2DocDoorShipping'),
 (7,'urn:epcglobal:epcis:mda:event_type','ObjectEvent'),
 (7,'urn:epcglobal:epcis:mda:business_step','urn:epcglobal:fmcg:bizstep:shipping'),
 (7,'urn:epcglobal:epcis:mda:business_location','urn:epcglobal:fmcg:loc:acme:warehouse2'),
 (7,'urn:epcglobal:epcis:mda:disposition','urn:epcglobal:fmcg:disp:in_transit'),
 (7,'urn:epcglobal:epcis:mda:ecspec_name','ECSpecShipping'),
 (7,'urn:epcglobal:epcis:mda:read_point','urn:epcglobal:fmcg:loc:06141.Warehouse2DocDoor'),
 (7,'urn:epcglobal:epcis:mda:transaction_type','urn:epcglobal:fmcg:btt:shipping'),
 (7,'urn:epcglobal:epcis:mda:action','ADD');
/*!40000 ALTER TABLE `voc_BizTrans_attr` ENABLE KEYS */;



--
-- Dumping data for table `epcis`.`voc_BizTransType`
--

/*!40000 ALTER TABLE `voc_BizTransType` DISABLE KEYS */;
INSERT INTO `voc_BizTransType` (`id`,`uri`) VALUES 
 (2,'urn:epcglobal:fmcg:btt:shipping'),
 (3,'urn:epcglobal:fmcg:btt:receiving');
/*!40000 ALTER TABLE `voc_BizTransType` ENABLE KEYS */;



--
-- Dumping data for table `epcis`.`voc_BizTransType_attr`
--

/*!40000 ALTER TABLE `voc_BizTransType_attr` DISABLE KEYS */;
INSERT INTO `voc_BizTransType_attr` (`id`,`attribute`,`value`) VALUES 
 (2,'urn:epcglobal:epcis:mda:Name','shipping'),
 (3,'urn:epcglobal:epcis:mda:Name','receiving');
/*!40000 ALTER TABLE `voc_BizTransType_attr` ENABLE KEYS */;



--
-- Dumping data for table `epcis`.`voc_Disposition`
--

/*!40000 ALTER TABLE `voc_Disposition` DISABLE KEYS */;
INSERT INTO `voc_Disposition` (`id`,`uri`) VALUES 
 (1,'urn:epcglobal:fmcg:disp:active'),
 (2,'urn:epcglobal:fmcg:disp:inactive'),
 (3,'urn:epcglobal:fmcg:disp:reserved'),
 (4,'urn:epcglobal:fmcg:disp:encoded'),
 (5,'urn:epcglobal:fmcg:disp:in_transit'),
 (6,'urn:epcglobal:fmcg:disp:non_sellable'),
 (7,'urn:epcglobal:fmcg:disp:in_progress'),
 (8,'urn:epcglobal:fmcg:disp:sold');
/*!40000 ALTER TABLE `voc_Disposition` ENABLE KEYS */;



--
-- Dumping data for table `epcis`.`voc_Disposition_attr`
--

/*!40000 ALTER TABLE `voc_Disposition_attr` DISABLE KEYS */;
INSERT INTO `voc_Disposition_attr` (`id`,`attribute`,`value`) VALUES 
 (1,'urn:epcglobal:epcis:mda:Name','active'),
 (2,'urn:epcglobal:epcis:mda:Name','inactive'),
 (3,'urn:epcglobal:epcis:mda:Name','reserved'),
 (4,'urn:epcglobal:epcis:mda:Name','encoded'),
 (5,'urn:epcglobal:epcis:mda:Name','in_transit'),
 (6,'urn:epcglobal:epcis:mda:Name','non_sellable'),
 (7,'urn:epcglobal:epcis:mda:Name','in_progress'),
 (8,'urn:epcglobal:epcis:mda:Name','sold');
/*!40000 ALTER TABLE `voc_Disposition_attr` ENABLE KEYS */;





--
-- Dumping data for table `epcis`.`voc_ReadPoint`
--

/*!40000 ALTER TABLE `voc_ReadPoint` DISABLE KEYS */;
INSERT INTO `voc_ReadPoint` (`id`,`uri`) VALUES 
 (1,'urn:epcglobal:fmcg:loc:45632.Warehouse1DocDoor'),
 (2,'urn:epcglobal:fmcg:loc:06141.Warehouse2DocDoor'),
 (3,'urn:epcglobal:fmcg:loc:rp:warehouse3docdoor');
/*!40000 ALTER TABLE `voc_ReadPoint` ENABLE KEYS */;



--
-- Dumping data for table `epcis`.`voc_ReadPoint_attr`
--

/*!40000 ALTER TABLE `voc_ReadPoint_attr` DISABLE KEYS */;
INSERT INTO `voc_ReadPoint_attr` (`id`,`attribute`,`value`) VALUES 
 (1,'urn:epcglobal:epcis:mda:Name','Warehouse1DocDoor'),
 (2,'urn:epcglobal:epcis:mda:Name','Warehouse2DocDoor'),
 (3,'urn:epcglobal:epcis:mda:Name','Warehouse3DocDoor');
/*!40000 ALTER TABLE `voc_ReadPoint_attr` ENABLE KEYS */;



--
-- Dumping data for table `epcis`.`vocabularies`
--

/*!40000 ALTER TABLE `vocabularies` DISABLE KEYS */;
INSERT INTO `vocabularies` (`id`,`uri`,`table_name`) VALUES 
 (1,'urn:epcglobal:epcis:vtype:BusinessStep','BizStep'),
 (2,'urn:epcglobal:epcis:vtype:BusinessTransaction','BizTrans'),
 (3,'urn:epcglobal:epcis:vtype:Disposition','Disposition'),
 (4,'urn:epcglobal:epcis:vtype:ReadPoint','ReadPoint'),
 (5,'urn:epcglobal:epcis:vtype:BusinessLocation','BizLoc');
/*!40000 ALTER TABLE `vocabularies` ENABLE KEYS */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;