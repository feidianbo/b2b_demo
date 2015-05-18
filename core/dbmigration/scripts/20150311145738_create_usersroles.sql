--// create_usersroles
-- Migration SQL that makes the change goes here.

DROP TABLE IF EXISTS `usersroles`
/
CREATE TABLE `usersroles` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `roleid` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
/

insert into usersroles(userid, roleid) values(1, 8)
/

--//@UNDO
-- SQL to undo the change goes here.


