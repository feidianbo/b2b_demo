--// alter_users
-- Migration SQL that makes the change goes here.

drop table users
/
CREATE TABLE `users` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `securephone` varchar(100) DEFAULT NULL,
  `nickname` varchar(20) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `registertime` datetime DEFAULT NULL,
  `isactive` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`nickname`),
  UNIQUE KEY `securephone` (`securephone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
/

--//@UNDO
-- SQL to undo the change goes here.


