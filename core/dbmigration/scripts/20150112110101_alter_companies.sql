alter table companies drop column contact
/
ALTER TABLE companies ADD  legalperosnname varchar(30) NOT NULL DEFAULT '' COMMENT '法人姓名'
/
ALTER TABLE companies ADD  account varchar(30) NOT NULL DEFAULT '' COMMENT '银行账号'
/
ALTER TABLE companies ADD  openingbank varchar(20) NOT NULL DEFAULT '' COMMENT '开户银行'
/