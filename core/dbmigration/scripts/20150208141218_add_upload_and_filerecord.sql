--// add upload and filerecord
-- Migration SQL that makes the change goes here.

CREATE TABLE `filerecords` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `filepath` varchar(50) NOT NULL DEFAULT '',
  `tablename` varchar(50) NOT NULL DEFAULT '',
  `tableid` int(11) NOT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
/
CREATE TABLE `uploadfilebyuser` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `filepath` varchar(50) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
/
CREATE TABLE `uploadfilebyadmin` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `adminid` int(11) DEFAULT NULL,
  `filepath` varchar(50) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
/

--//@UNDO
-- SQL to undo the change goes here.


