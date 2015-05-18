--// create_charts
-- Migration SQL that makes the change goes here.

CREATE TABLE `charts` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `type` varchar(30) NOT NULL DEFAULT '' COMMENT '图表类型',
  `tradedate` date NOT NULL COMMENT '交易日期',
  `averageprice` decimal(4,1) NOT NULL COMMENT '平均价格',
  `createtime` datetime NOT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
/

alter table demands drop column demanduuid
/

--//@UNDO
-- SQL to undo the change goes here.


