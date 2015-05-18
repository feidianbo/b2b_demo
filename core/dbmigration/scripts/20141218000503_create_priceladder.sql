--// create_priceladder
-- Migration SQL that makes the change goes here.

DROP TABLE IF EXISTS priceladder
/
CREATE TABLE `priceladder` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `sellinfoid` int(11) unsigned NOT NULL COMMENT 'sellinfo对应的id',
  `pid` varchar(30) DEFAULT NULL COMMENT '产品id',
  `squence` int(2) NOT NULL COMMENT '次序，第几个阶梯价',
  `price` int(4) unsigned NOT NULL COMMENT '价格瓶/元',
  `amount1` int(6) unsigned NOT NULL COMMENT '左边数量',
  `amount2` int(6) unsigned NOT NULL COMMENT '右边数量',
  `createtime` DATETIME DEFAULT NULL COMMENT '创建时间',
  `lastupdatetime` TIMESTAMP COMMENT '最后一次更新时间',
  `userid` int(11) unsigned NOT NULL COMMENT 'userid',
  `username` varchar(20) DEFAULT NULL COMMENT 'username',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
/

--//@UNDO
-- SQL to undo the change goes here.


