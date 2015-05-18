--// alter_manualsell
-- Migration SQL that makes the change goes here.
--// alert_manualsell
-- Migration SQL that makes the change goes here.

--// create_manualsell
-- Migration SQL that makes the change goes here.
DROP TABLE IF EXISTS `manualsell`
/
CREATE TABLE `manualsell` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `lowcalorificvalue` int(10) NOT NULL,
  `deliverystartdate` date NOT NULL,
  `deliveryenddate` date DEFAULT NULL,
  `deliverydistrict` varchar(10) NOT NULL DEFAULT '',
  `deliveryprovince` varchar(10) NOT NULL DEFAULT '',
  `deliveryaddr` varchar(20) NOT NULL DEFAULT '',
  `deliveryotherplace` varchar(20) DEFAULT NULL,
  `deliverymode` varchar(4) NOT NULL DEFAULT '',
  `demandamount` int(11) DEFAULT '0',
  `contactName` varchar(11) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `companyName` varchar(20) DEFAULT NULL,
  `type` tinyint(1) NOT NULL,
  `receivebasissulfur` decimal(4,1) NOT NULL DEFAULT '0.0',
 `airdrybasisvolatile` decimal(4,1) NOT NULL DEFAULT '0.0',
  `coaltype` varchar(10) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;
/

--//@UNDO
-- SQL to undo the change goes here.




--//@UNDO
-- SQL to undo the change goes here.





--//@UNDO
-- SQL to undo the change goes here.


