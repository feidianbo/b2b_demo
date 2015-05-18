--// create_validator
-- Migration SQL that makes the change goes here.

CREATE TABLE `emailvalidators` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `expiretime` datetime NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `code` varchar(100) NOT NULL DEFAULT '',
  `userid` int(11) unsigned DEFAULT NULL,
  `contextjson` text,
  `validatetime` datetime DEFAULT NULL,
  `validated` tinyint(1) NOT NULL DEFAULT '0',
  `validatetype` varchar(50) DEFAULT 'reg',
  PRIMARY KEY (`id`),
  KEY `expiretime` (`expiretime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
/

CREATE TABLE `phonevalidators` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `expiretime` datetime NOT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `code` varchar(100) NOT NULL DEFAULT '',
  `userid` int(11) unsigned DEFAULT NULL,
  `contextjson` text,
  `validatetime` datetime DEFAULT NULL,
  `validated` tinyint(1) NOT NULL DEFAULT '0',
  `validatetype` varchar(50) DEFAULT 'reg',
  PRIMARY KEY (`id`),
  KEY `expiretime` (`expiretime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
/

--//@UNDO
-- SQL to undo the change goes here.


