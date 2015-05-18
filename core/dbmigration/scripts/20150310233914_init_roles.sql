--// init_roles
-- Migration SQL that makes the change goes here.

DELETE FROM roles
/
INSERT INTO `roles` (`id`, `rolename`, `rolecode`)
VALUES
	(1, '交易员', 'Trader'),
	(2, '交易员助理', 'TraderAssistant'),
	(3, '网编', 'NetworkEditor'),
	(4, '财务', 'Finance'),
	(5, '法务', 'LegalPersonnel'),
	(6, '运营', 'Operation'),
	(7, '后台管理员', 'BackgroundSupporter'),
	(8, '超级管理员', 'Admin')
	/


--//@UNDO
-- SQL to undo the change goes here.


