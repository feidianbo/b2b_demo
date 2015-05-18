--// alter_data
-- Migration SQL that makes the change goes here.

update areaports set name='广西' where name='广西自治区'
/

update demands set deliveryprovince='广西' where deliveryprovince='广西自治区'
/

update sellinfo set deliveryprovince='广西' where deliveryprovince='广西自治区'
/

update orders set deliveryprovince='广西' where deliveryprovince='广西自治区'
/

update manualsell set deliveryprovince='广西' where deliveryprovince='广西自治区'
/

update dealers set deliveryprovince='广西' where deliveryprovince='广西自治区'
/

--//@UNDO
-- SQL to undo the change goes here.


