--// create_indexbanner
-- Migration SQL that makes the change goes here.

CREATE TABLE `indexbanners` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(10) NOT NULL DEFAULT '' COMMENT 'banner图名称',
  `path` varchar(200) NOT NULL DEFAULT '' COMMENT '图片路径',
  `sequence` int(11) NOT NULL COMMENT '顺序',
  `limitnum` int(11) NOT NULL COMMENT '显示数量',
  `createtime` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
/

--//@UNDO
-- SQL to undo the change goes here.


