--// create_userlogins
-- Migration SQL that makes the change goes here.

CREATE TABLE `userlogins` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `userid` int(11) unsigned NOT NULL,
  `logintime` datetime NOT NULL,
  `loginip` varchar(40) DEFAULT NULL,
  `loginby` varchar(14) DEFAULT NULL,
  `useragent` varchar(1000) DEFAULT NULL,
  `acceptlanguage` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userid` (`userid`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8
/

--//@UNDO
-- SQL to undo the change goes here.


