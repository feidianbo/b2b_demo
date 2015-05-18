--// role_init
-- Migration SQL that makes the change goes here.

delete from roles
/

insert into roles(rolename, rolecode) values('交易员', 'Trader')
/
insert into roles(rolename, rolecode) values('交易员助理', 'TraderAssistant')
/
insert into roles(rolename, rolecode) values('网编', 'NetworkEditor')
/
insert into roles(rolename, rolecode) values('财务', 'Finance')
/
insert into roles(rolename, rolecode) values('法务', 'LegalPersonnel')
/
insert into roles(rolename, rolecode) values('运营', 'Operation')
/
insert into roles(rolename, rolecode) values('后台管理员', 'BackgroundSupporter')
/
insert into roles(rolename, rolecode) values('超级管理员', 'Admin')
/

--//@UNDO
-- SQL to undo the change goes here.


