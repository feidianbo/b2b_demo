--// create_chartconfine
-- Migration SQL that makes the change goes here.

CREATE TABLE `chartconfine` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `type` varchar(30) NOT NULL DEFAULT '' COMMENT '图表类型',
  `highlimit` int(11) NOT NULL DEFAULT '0' COMMENT '上限',
  `lowlimit` int(11) NOT NULL DEFAULT '0' COMMENT '下限',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
/

--//@UNDO
-- SQL to undo the change goes here.


