--// init_legal_personal
-- Migration SQL that makes the change goes here.

INSERT INTO `rolesmenus` (`id`, `roleid`, `menuid`)
VALUES
	(113, 5, 1),
	(114, 5, 19)
/

--//@UNDO
-- SQL to undo the change goes here.


