--// menu_init
-- Migration SQL that makes the change goes here.

INSERT INTO `usersroles` (`id`, `userid`, `roleid`)
VALUES(1, 1, 1)
/
INSERT INTO `roles` (`id`, `rolename`, `rolecode`)
VALUES (1, '超级管理员', 'SUPER001')
/
INSERT INTO `rolesmenus` (`id`, `roleid`, `menuid`)
VALUES (1, 1, 1)
/
INSERT INTO `menus` (`id`, `menuname`, `url`, `parentid`, `sequence`)
VALUES (1, '菜单管理', '/#/menu', 0, 100)
/

--//@UNDO
-- SQL to undo the change goes here.


