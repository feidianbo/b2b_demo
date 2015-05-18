--// create_tables
-- Migration SQL that makes the change goes here.

CREATE TABLE `roles` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `rolename` varchar(30) NOT NULL DEFAULT '' COMMENT '角色名称',
  `rolecode` varchar(30) NOT NULL DEFAULT '' COMMENT '角色编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
/

CREATE TABLE `usersroles` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `roleid` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
/

CREATE TABLE `menus` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `menuname` varchar(10) NOT NULL DEFAULT '',
  `url` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
/

CREATE TABLE `rolesmenus` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `roleid` int(11) NOT NULL,
  `menuid` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
/

CREATE TABLE `operateauth` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `operatename` varchar(10) NOT NULL DEFAULT '' COMMENT '操作名称',
  `operatecode` varchar(10) NOT NULL DEFAULT '' COMMENT '操作编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
/

CREATE TABLE `rolesoperate` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `roleid` int(11) NOT NULL,
  `operatecode` varchar(10) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
/

--//@UNDO
-- SQL to undo the change goes here.


