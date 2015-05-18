--// alter_data
-- Migration SQL that makes the change goes here.

update areaports set name=replace(name,'省','')
/
update areaports set name=replace(name,'市','')
/
update areaports set name='扬州海昌码头' where name='海昌码头(扬州)'
/
update areaports set name='西坝码头' where name='西坝码头(南京)'
/
update areaports set name='福建东吴港' where name='莆田港(东吴)'
/
update areaports set name='广州新沙港' where name='广州港(新沙)'
/
update areaports set name='广州西基码头' where name='广州港(西基)'
/
update areaports set name='广东东莞海昌码头' where name='海昌码头(东莞)'
/
update areaports set isdelete=1 where name='广州港(黄埔)'
/


update demands set deliveryprovince=replace(deliveryprovince,'省','')
/
update demands set deliveryprovince=replace(deliveryprovince,'市','')
/

update sellinfo set deliveryprovince=replace(deliveryprovince,'省','')
/
update sellinfo set deliveryprovince=replace(deliveryprovince,'市','')
/
update sellinfo set deliveryplace='广州新沙港' where deliveryplace='广州港(新沙)'
/

update orders set deliveryprovince=replace(deliveryprovince,'省','')
/
update orders set deliveryprovince=replace(deliveryprovince,'市','')
/
update orders set deliveryplace='广州新沙港' where deliveryplace='广州港(新沙)'
/

update manualsell set deliveryprovince=replace(deliveryprovince,'省','')
/
update manualsell set deliveryprovince=replace(deliveryprovince,'市','')
/
update manualsell set deliveryaddr='广州新沙港' where deliveryaddr='广州港(新沙)'
/

update dealers set deliveryprovince=replace(deliveryprovince,'省','')
/
update dealers set deliveryprovince=replace(deliveryprovince,'市','')
/
update dealers set deliveryplace='扬州海昌码头' where deliveryplace='海昌码头(扬州)'
/
update dealers set deliveryplace='西坝码头' where deliveryplace='西坝码头(南京)'
/
update dealers set deliveryplace='福建东吴港' where deliveryplace='莆田港(东吴)'
/
update dealers set deliveryplace='广州新沙港' where deliveryplace='广州港(新沙)'
/
update dealers set deliveryplace='广州西基码头' where deliveryplace='广州港(西基)'
/
update dealers set deliveryplace='广东东莞海昌码头' where deliveryplace='海昌码头(东莞)'
/

--//@UNDO
-- SQL to undo the change goes here.


