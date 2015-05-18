--// alter_rolemenus
-- Migration SQL that makes the change goes here.

INSERT INTO `rolesmenus` (`id`, `roleid`, `menuid`)
VALUES
	(230, 3, 17),
	(231, 3, 58),
	(232, 3, 59),
	(233, 3, 60)
	/

--//@UNDO
-- SQL to undo the change goes here.


