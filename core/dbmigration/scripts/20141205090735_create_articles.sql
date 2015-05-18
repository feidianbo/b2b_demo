--// create_articles
-- Migration SQL that makes the change goes here.

DROP TABLE IF EXISTS `articles`
/
CREATE TABLE `articles` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `type` varchar(10) NOT NULL COMMENT '类型，比如新闻等',
  `typedetail` varchar(10) DEFAULT NULL COMMENT '详细分类，比如煤炭，石油等',
  `title` varchar(100) NOT NULL DEFAULT '' COMMENT '标题',
  `summary` varchar(255) DEFAULT NULL COMMENT '摘要',
  `keywords` varchar(100) DEFAULT NULL COMMENT '关键字',
  `content` text NOT NULL COMMENT '内容',
  `author` varchar(30) DEFAULT NULL COMMENT '编辑',
  `source` varchar(100) DEFAULT NULL COMMENT '来源',
  `createtime` datetime NOT NULL COMMENT '创建时间',
  `lastupdatetime` datetime NOT NULL COMMENT '最后一次改变时间',
  `isdelete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `parentid` int(11) unsigned DEFAULT '0',
  `haschild` tinyint(1) NOT NULL DEFAULT '0',
  `path` varchar(50) DEFAULT NULL COMMENT '路径',
  `aid` varchar(50) DEFAULT NULL COMMENT '文章id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
/

--//@UNDO
-- SQL to undo the change goes here.


