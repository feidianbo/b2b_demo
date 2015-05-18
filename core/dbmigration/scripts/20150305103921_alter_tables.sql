--// alter_tables
-- Migration SQL that makes the change goes here.
DROP TABLE IF EXISTS `operateauth`
/
DROP TABLE IF EXISTS `rolesoperate`
/

CREATE TABLE `rolesoperate` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `roleid` int(11) NOT NULL,
  `operatecode` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
/

CREATE TABLE `operateauth` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `operatename` varchar(50) NOT NULL DEFAULT '' COMMENT '操作名称',
  `operatecode` varchar(50) NOT NULL DEFAULT '' COMMENT '操作编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
/


--//@UNDO
-- SQL to undo the change goes here.


