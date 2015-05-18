--// create_dateval_next
-- Migration SQL that makes the change goes here.

DROP TABLE IF EXISTS dateseq
/

CREATE TABLE `dateseq` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL DEFAULT '',
  `date` varchar(8) NOT NULL DEFAULT '',
  `cnt` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`,`date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
/


drop function if exists dateseq_next_value
/
create function dateseq_next_value(p_name varchar(30)) returns varchar(200)
begin
 DECLARE cur_val int(11);
 DECLARE cur_date int(11);
  select CURDATE() + 0 into cur_date;
  insert dateseq (`name`,`date`, cnt) values (p_name, cur_date, 1) on DUPLICATE key update cnt=cnt+1;

  SELECT cnt INTO cur_val from dateseq where `name`=p_name and `date`=cur_date limit 1;
  return CONCAT(p_name, cur_date,  cur_val);
end
/

--//@UNDO
-- SQL to undo the change goes here.


