-- Add/modify columns 
alter table MSTB_APPLICATION modify CREATED_USER_ID VARCHAR2(32);

--alter table MSTB_APPLICATION modify CREATED_USER_ID VARCHAR2(32);
select 'alter table '||tt.TABLE_NAME||' modify '||tt.COLUMN_NAME||' VARCHAR2(32);'
  from user_tab_columns tt
 where tt.TABLE_NAME like 'MSTB_%'
   and tt.COLUMN_NAME like '%USER_ID'
   

   