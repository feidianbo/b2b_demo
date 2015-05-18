--// create_payment
-- Migration SQL that makes the change goes here.

DROP TABLE IF EXISTS `payment`
/
CREATE TABLE `payment` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `orderid` int(11) unsigned NOT NULL COMMENT 'orders表对应的id',
  `oid` varchar(30) NOT NULL DEFAULT '' COMMENT '订单编号orderid',
  `squence` int(2) NOT NULL COMMENT '次序，第几个支付凭证',
  `issuccess` tinyint(1) NOT NULL DEFAULT '0' COMMENT '支付凭证是否审核成功',
  `money` decimal(20,2) DEFAULT NULL COMMENT '此支付凭证上的货款',
  `verifyman` varchar(20) DEFAULT NULL COMMENT '审核人',
  `verifymanid` int(11) unsigned DEFAULT NULL COMMENT '审核人id',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `verifytime` datetime DEFAULT NULL COMMENT '验证时间',
  `lastupdatetime` timestamp COMMENT '最后一次更新时间',
  `userid` int(11) unsigned NOT NULL COMMENT '上传人id',
  `username` varchar(20) NOT NULL DEFAULT '' COMMENT '上传人name',
  `isverified` tinyint(1) NOT NULL COMMENT '此凭证是否已经验证',
  `pictureurl` VARCHAR(255) NOT NULL COMMENT '支付凭证图片存储路径',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
/

--//@UNDO
-- SQL to undo the change goes here.


