drop table users
/

CREATE TABLE `users` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `securephone` varchar(100) DEFAULT NULL,
  `nickname` varchar(20) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `registertime` datetime DEFAULT NULL,
  `isactive` tinyint(1) NOT NULL DEFAULT '1',
  `verifystatus` varchar(10) DEFAULT '待完善信息' COMMENT '公司信息审核状态',
  `qq` varchar(15) DEFAULT NULL COMMENT 'QQ号',
  `telephone` varchar(20) DEFAULT NULL COMMENT '固定电话',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`nickname`),
  UNIQUE KEY `securephone` (`securephone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
/