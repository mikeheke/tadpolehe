
create table MSTB_APPLICATION
(
  APPLICATION_ID           varchar(45) not null,
  APPLICATION_CODE         varchar(128) not null,
  APPLICATION_NAME         varchar(256) not null,
  FAULT_HANDLER_EMP_NUMBER varchar(32),
  IS_CHECK_CODE            int not null,
  APPLICATION_LAYOUT       int not null,
  FIX_WAY                  varchar(256),
  FAIL_NUM                 int,
  DEPLOY_SERVER            varchar(256),
  PORT                     varchar(256),
  CONTEXT                  varchar(256),
  ORDER_NO                 int,
  LANGUANGE                varchar(10) not null,
  REMARK                   varchar(2048),
  RECORD_STATE             int not null,
  CREATED_USER_ID          varchar(32),
  CREATED_TIME             datetime,
  UPDATED_USER_ID          varchar(32),
  UPDATED_TIME             datetime,
  STATE                    int,
  TIME_ZONE                varchar(10),
  SESSION_TIME_OUT         varchar(256),
  DEPARTMENT_CODE          varchar(20),
  DEFAULT_PAGE             varchar(1000),
  APPLICATION_STYLE        varchar(256),
  AUTHENTICATE_TYPE        int,
  INNER_MATCH_USER         varchar(32),
  EJB_AUTH_STATE_LESS      varchar(30)
);
alter table MSTB_APPLICATION
  add constraint MSPK_APPLICATION primary key (APPLICATION_ID);  


create table MSTB_APPLICATION_PLUS
(
  APPLICATION_PLUS_ID varchar(45) not null,
  APPLICATION_ID      varchar(45) not null,
  PARAMETER_CODE      varchar(256),
  PARAMETER_NAME      varchar(512),
  PARAMETER_VALUE     varchar(512),
  REMARK              varchar(2048),
  STATE               int not null,
  RECORD_STATE        int not null,
  CREATED_USER_ID     varchar(32),
  CREATED_TIME        datetime,
  UPDATED_USER_ID     varchar(32),
  UPDATED_TIME        datetime
);
alter table MSTB_APPLICATION_PLUS
  add constraint MSPK_APPLICATION_PLUS primary key (APPLICATION_PLUS_ID);
alter table MSTB_APPLICATION_PLUS
  add constraint FKE30FD94D3784850 foreign key (APPLICATION_ID)
  references MSTB_APPLICATION (APPLICATION_ID);




create table MSTB_USERPROFILE
(
  USERPROFILE_ID   varchar(45) not null,
  EMP_ID           varchar(30),
  PASSWORD         varchar(60),
  ACCOUNT_TYPE     int,
  CHINESE_NAME     varchar(100),
  DATE_HIRED       datetime,
  DATE_TERMINATED  datetime,
  GENDER           varchar(12),
  ENGLISH_NAME     varchar(100),
  DATE_BIRTHDAY    datetime,
  PROVINCIAL_ADDR  varchar(200),
  NATIVE_PLACE     varchar(300),
  WORK_CITY        varchar(100),
  GRADE_CODE       varchar(3),
  CHINESE_POSITION varchar(100),
  ENGLISH_POSITION varchar(100),
  WORK_PROVINCE    varchar(100),
  ORG_UNIT_CODE    varchar(30),
  EMP_NUMBER       varchar(30) not null,
  EMAIL_ADDR       varchar(200),
  JOB_GRADE        varchar(15),
  PHONE            varchar(100),
  REPORT_LINE      varchar(30),
  LOCATION         varchar(100),
  REMARK           varchar(2048),
  STATE            int not null,
  RECORD_STATE     int not null,
  CREATED_USER_ID  varchar(32),
  CREATED_TIME     datetime,
  UPDATED_USER_ID  varchar(32),
  UPDATED_TIME     datetime,
  SESSION_ID       varchar(100),
  LAST_LOGIN_TIME  datetime,
  LAST_LOGIN_IP    varchar(20),
  FAIL_NUM         int,
  LOGIN_IP         varchar(20),
  LOGIN_NUM        int,
  LOGIN_TIME       datetime
);
alter table MSTB_USERPROFILE
  add constraint MSPK_USERPROFILE primary key (USERPROFILE_ID);
create index I_USERPROFILE_EMPNUMBER on MSTB_USERPROFILE (EMP_NUMBER);



create table MSTB_BLACKLIST
(
  BLACKLIST_ID    varchar(45) not null,
  APPLICATION_ID  varchar(45),
  USERPROFILE_ID  varchar(45),
  BLACKLIST_TIME  datetime,
  REMARK          varchar(2048),
  STATE           int not null,
  RECORD_STATE    int not null,
  CREATED_USER_ID varchar(32),
  CREATED_TIME    datetime,
  UPDATED_USER_ID varchar(32),
  UPDATED_TIME    datetime
);
alter table MSTB_BLACKLIST
  add constraint MSPK_BLACKLIST primary key (BLACKLIST_ID);
alter table MSTB_BLACKLIST
  add constraint FKCA3E4D12138B0970 foreign key (USERPROFILE_ID)
  references MSTB_USERPROFILE (USERPROFILE_ID);
alter table MSTB_BLACKLIST
  add constraint FKCA3E4D12D3784850 foreign key (APPLICATION_ID)
  references MSTB_APPLICATION (APPLICATION_ID);



create table MSTB_PROVINCE
(
  ID          int not null,
  PRV_CODE    varchar(10),
  PRV_NAME_EN varchar(100),
  PRV_NAME_CN varchar(100),
  REGION_CODE varchar(10),
  CREATE_USER varchar(20),
  CREATE_DATE datetime,
  EDIT_USER   varchar(20),
  EDIT_DATE   datetime,
  STATUS      int
);
alter table MSTB_PROVINCE
  add constraint PK_MSTB_PROVINCE primary key (ID);
create index MSFK_PROVINCE_1 on MSTB_PROVINCE (PRV_CODE);
create index MSFK_PROVINCE_2 on MSTB_PROVINCE (REGION_CODE);


create table MSTB_CITY
(
  ID           int not null,
  CITY_CODE    varchar(10),
  CITY_NAME_EN varchar(100),
  CITY_NAME_CN varchar(100),
  PRV_CODE     varchar(10),
  CREATE_USER  varchar(20),
  CREATE_DATE  datetime,
  EDIT_USER    varchar(20),
  EDIT_DATE    datetime,
  STATUS       int
);

alter table MSTB_CITY
  add constraint PK_MSTB_CITY primary key (ID);
create index MSFK_CITY_0 on MSTB_CITY (CITY_CODE);
create index MSFK_CITY_1 on MSTB_CITY (PRV_CODE);



create table MSTB_COM_FA_DEPT
(
  DEPT_ID                    varchar(100) not null,
  DEPT_ADMIN_EMP             varchar(10) not null,
  DEPT_ADMIN_EMP_NAME        varchar(50) not null,
  DEPT_NAME_CN               varchar(200) not null,
  DEPT_FIN_CODE              varchar(10),
  DEPT_TYPE                  varchar(10) not null,
  REGION_CODE                varchar(10) not null,
  REGION_NAME_CN             varchar(200),
  REGION_NAME_EN             varchar(100),
  DEPTHEAD_PROVINCE_EMP_NUM  varchar(10),
  DEPTHEAD_PROVINCE_EMP_NAME varchar(50),
  AD_APPROVER_EMP_NUM        varchar(10),
  AD_APPROVER_EMP_NAME       varchar(50),
  AD_SUPERVISOR_EMP_NUM      varchar(10),
  AD_SUPERVISOR_EMP_NAME     varchar(50),
  CO_ID                      varchar(10),
  CO_NAME                    varchar(200)
);
alter table MSTB_COM_FA_DEPT
  add constraint MSTB_COM_FA_DEPT_PK primary key (DEPT_ID);



create table MSTB_DEPARTMENT
(
  DEPARTMENT_ID    varchar(45) not null,
  UNIT_CODE        varchar(10) not null,
  CITY_CODE        varchar(10),
  DEPT_TYPE        varchar(10),
  Z_DFUNC_DESC_ZHS varchar(100),
  Z_DFUNC_DESC_ENG varchar(100),
  DEPT_NAME_CN     varchar(100),
  DEPT_NAME_EN     varchar(100),
  DEPT_FIN_CODE    varchar(10),
  IS_FUNCTIONAL    varchar(1),
  DEPT_STATUS      varchar(10),
  DEPT_UPDATE_DT   datetime,
  BUSINESS_UNIT    varchar(10),
  EFFDT            datetime,
  MANAGER_ID       varchar(10),
  OFFICER_CD       varchar(1),
  XLATLONGNAME     varchar(30),
  SAL_ADMIN_PLAN   varchar(4),
  PARENT_NODE_NAME varchar(10),
  TREE_LEVEL_NUM   int,
  Z_LOCATION_ID    varchar(5),
  Z_LOCATION_DESCR varchar(100),
  CITY             varchar(10),
  COMPANY          varchar(10),
  LOCATION         varchar(10),
  Z_TAX_CENTER     varchar(10),
  DESCR            varchar(200),
  Z_DEPT_ATTR_ID   varchar(5),
  Z_DEPT_FUNCTION  varchar(3),
  RECORD_STATE     int not null,
  CREATED_USER_ID  varchar(32),
  CREATED_TIME     datetime,
  UPDATED_USER_ID  varchar(32),
  UPDATED_TIME     datetime
);
alter table MSTB_DEPARTMENT
  add constraint MSPK_DEPARTMENT1 primary key (DEPARTMENT_ID);




create table MSTB_EMAIL_TEMPLATES
(
  EMAIL_TEMPLATES_ID      varchar(45) not null,
  TEMPLATES_CODE          varchar(128),
  TEMPLATES_NAME          varchar(256),
  EMAIL_TEMPLATES_SUBJECT varchar(200),
  EMAIL_TEMPLATES_CONTENT varchar(3000),
  APPLICATION_ID          varchar(45),
  ACCESSORY_FLAG          int,
  SEND_FLAG               int,
  REMARK                  varchar(2048),
  STATE                   int not null,
  RECORD_STATE            int not null,
  CREATED_USER_ID         varchar(32),
  CREATED_TIME            datetime,
  UPDATED_USER_ID         varchar(32),
  UPDATED_TIME            datetime
);
alter table MSTB_EMAIL_TEMPLATES
  add constraint MSPK_EMAIL_TEMPLATES primary key (EMAIL_TEMPLATES_ID);
alter table MSTB_EMAIL_TEMPLATES
  add constraint MSCS_APP_EMAIL_TEMPLATES foreign key (APPLICATION_ID)
  references MSTB_APPLICATION (APPLICATION_ID);



create table MSTB_EXCEPTION
(
  EXCEPTION_ID    varchar(45) not null,
  EXCEPTION_CODE  varchar(128),
  EXCEPTION_NAME  varchar(256),
  APPLICATION_ID  varchar(45),
  IS_SEND_EMAIL   int,
  EMAIL_USERS     varchar(1000),
  USE_STATE       int,
  REMARK          varchar(2048),
  RECORD_STATE    int not null,
  CREATED_USER_ID varchar(32),
  CREATED_TIME    datetime,
  UPDATED_USER_ID varchar(32),
  UPDATED_TIME    datetime
);
alter table MSTB_EXCEPTION
  add constraint PK_MSTB_EXCEPTION primary key (EXCEPTION_ID);
alter table MSTB_EXCEPTION
  add constraint FK_MSTB_EXC_MSCS_APPL_MSTB_APP foreign key (APPLICATION_ID)
  references MSTB_APPLICATION (APPLICATION_ID);





create table MSTB_MODULE
(
  MODULE_ID         varchar(45) not null,
  MODULE_CODE       varchar(50) not null,
  MODULE_NAME       varchar(100) not null,
  PARENT_MODULE_ID  varchar(45) not null,
  IS_MODULE_OR_MENU int,
  APPLICATION_ID    varchar(45) not null,
  ICO               varchar(100),
  LINK              varchar(1000),
  ORDER_NO          varchar(100),
  IS_SEED           int,
  OPEN_TYPE         int,
  LEVEL_NO          int,
  TARGET            varchar(30),
  REMARK            varchar(2000),
  STATE             int not null,
  RECORD_STATE      int not null,
  CREATED_USER_ID   varchar(32),
  CREATED_TIME      datetime,
  UPDATED_USER_ID   varchar(32),
  UPDATED_TIME      datetime
);
alter table MSTB_MODULE
  add constraint MSPK_MODULE primary key (MODULE_ID);
alter table MSTB_MODULE
  add constraint MSCS_APPLICATION_RIGHT21222211 unique (MODULE_ID, MODULE_CODE, MODULE_NAME);
alter table MSTB_MODULE
  add constraint MSCS_APPLICATION_RIGHT foreign key (APPLICATION_ID)
  references MSTB_APPLICATION (APPLICATION_ID);




create table MSTB_QUERY_INDEX
(
  QUERY_ID        varchar(45) not null,
  QUERY_CODE      varchar(100),
  QUERY_NAME      varchar(200),
  APPLICATION_ID  varchar(45),
  DS_JNDI         varchar(100),
  IS_COL_HEA_FIL  int,
  EXTERNAL_JS     varchar(1000),
  IS_DEFAULT_QRY  int,
  GLOBAL_WHERE    varchar(1000),
  REMARE          varchar(2000),
  USE_STATE       int not null,
  RECORD_STATE    int not null,
  CREATED_USER_ID varchar(32),
  CREATED_TIME    datetime,
  UPDATED_USER_ID varchar(32),
  UPDATED_TIME    datetime,
  IS_REFRESH      int
);
alter table MSTB_QUERY_INDEX
  add constraint MSPK_QUERY_INDEX primary key (QUERY_ID);
alter table MSTB_QUERY_INDEX
  add constraint MSCS_APPLICATION_QUERY foreign key (APPLICATION_ID)
  references MSTB_APPLICATION (APPLICATION_ID);



create table MSTB_QUERY_BUTTON
(
  BUTTON_ID       varchar(45) not null,
  QUERY_ID        varchar(45),
  BUTTON_NO       varchar(100),
  BUTTON_NAME     varchar(200),
  SUB_URL         varchar(500),
  EXECUTE_JS      varchar(2000),
  ORDER_NO        int,
  RECORD_STATE    int not null,
  CREATED_USER_ID varchar(32),
  CREATED_TIME    datetime,
  UPDATED_USER_ID varchar(32),
  UPDATED_TIME    datetime,
  OPEN_TYPE       int
);
alter table MSTB_QUERY_BUTTON
  add constraint MSPK_QUERY_BUTTON primary key (BUTTON_ID);
alter table MSTB_QUERY_BUTTON
  add constraint MSCS_QUERY_BUTTON foreign key (QUERY_ID)
  references MSTB_QUERY_INDEX (QUERY_ID);




create table MSTB_QUERY_FROM
(
  FROM_ID         varchar(45) not null,
  QUERY_ID        varchar(45),
  TABLE_NAME      varchar(100),
  TABLE_ALIAS     varchar(100),
  ORDER_NO        int,
  FROM_RESULT     varchar(200),
  RECORD_STATE    int not null,
  CREATED_USER_ID varchar(32),
  CREATED_TIME    datetime,
  UPDATED_USER_ID varchar(32),
  UPDATED_TIME    datetime
);
alter table MSTB_QUERY_FROM
  add constraint MSPK_QUERY_FROM primary key (FROM_ID);
alter table MSTB_QUERY_FROM
  add constraint MSCS_QUERY_FROM foreign key (QUERY_ID)
  references MSTB_QUERY_INDEX (QUERY_ID);



create table MSTB_QUERY_GROUPBY
(
  GROUPBY_ID      varchar(45) not null,
  QUERY_ID        varchar(45),
  TABLE_NAME      varchar(100),
  FIELD_NAME      varchar(100),
  ORDER_NO        int,
  RECORD_STATE    int not null,
  CREATED_USER_ID varchar(32),
  CREATED_TIME    datetime,
  UPDATED_USER_ID varchar(32),
  UPDATED_TIME    datetime
);
alter table MSTB_QUERY_GROUPBY
  add constraint MSPK_QUERY_GROUPBY primary key (GROUPBY_ID);
alter table MSTB_QUERY_GROUPBY
  add constraint MSCS_QUERY_GROUP foreign key (QUERY_ID)
  references MSTB_QUERY_INDEX (QUERY_ID);



create table MSTB_QUERY_ORDERBY
(
  ORDERBY_ID      varchar(45) not null,
  QUERY_ID        varchar(45),
  TABLE_NAME      varchar(100),
  FIELD_NAME      varchar(100),
  ORDER_BY        int,
  ORDER_NO        int,
  RECORD_STATE    int not null,
  CREATED_USER_ID varchar(32),
  CREATED_TIME    datetime,
  UPDATED_USER_ID varchar(32),
  UPDATED_TIME    datetime
);
alter table MSTB_QUERY_ORDERBY
  add constraint MSPK_QUERY_ORDERBY primary key (ORDERBY_ID);
alter table MSTB_QUERY_ORDERBY
  add constraint MSCS_QUERY_ORDERBY foreign key (QUERY_ID)
  references MSTB_QUERY_INDEX (QUERY_ID);



create table MSTB_QUERY_SELECT
(
  SELECT_ID       varchar(45) not null,
  QUERY_ID        varchar(45),
  TABLE_NAME      varchar(100),
  FIELD_NAME      varchar(100),
  FIELD_ALIAS     varchar(100),
  DATA_TYPE       varchar(100),
  DES             varchar(200),
  ORDER_NO        int,
  DATA_CODING     varchar(100),
  OUT_FORMAT      varchar(100),
  BTN_TYPE        int,
  IS_HIDDEN       int,
  DATA_INPUT      varchar(500),
  OPEN_TYPE       int,
  LINK_URL        varchar(500),
  SELECT_RESULT   varchar(500),
  COL_WIDTH       varchar(50),
  RECORD_STATE    int not null,
  CREATED_USER_ID varchar(32),
  CREATED_TIME    datetime,
  UPDATED_USER_ID varchar(32),
  UPDATED_TIME    datetime
);
alter table MSTB_QUERY_SELECT
  add constraint MSPK_QUERY_SELECT primary key (SELECT_ID);
alter table MSTB_QUERY_SELECT
  add constraint MSCS_QUERY_SELECT foreign key (QUERY_ID)
  references MSTB_QUERY_INDEX (QUERY_ID);


create table MSTB_QUERY_WHERE
(
  WHERE_ID        varchar(45) not null,
  QUERY_ID        varchar(45),
  WHERE_CODE      varchar(100),
  TABLE_NAME      varchar(100),
  FIELD_NAME      varchar(100),
  WHERE_RESULT    varchar(400),
  DATA_TYPE       varchar(100),
  DES             varchar(200),
  ORDER_NO        int,
  IS_USER_IN      int,
  IS_INNER_LINK   int,
  CONTROL_TYPE    int,
  DATA_CODING     varchar(100),
  OPERATOR        int,
  DEFAULT_VALUE   varchar(200),
  LINK_TABLE      varchar(100),
  LINK_FIELD      varchar(100),
  FILTER_FIELD    varchar(100),
  IS_REQUIRED     int,
  REG_EXP         varchar(500),
  PAR_TAG_ID      varchar(100),
  IS_IGNORE_CASE  int,
  RECORD_STATE    int not null,
  CREATED_USER_ID varchar(32),
  CREATED_TIME    datetime,
  UPDATED_USER_ID varchar(32),
  UPDATED_TIME    datetime
);
alter table MSTB_QUERY_WHERE
  add constraint MSPK_QUERY_WHERE primary key (WHERE_ID);
alter table MSTB_QUERY_WHERE
  add constraint MSCS_QUERY_WHERE foreign key (QUERY_ID)
  references MSTB_QUERY_INDEX (QUERY_ID);


create table MSTB_REGION
(
  ID               int not null,
  REGION_CODE      varchar(10),
  REGION_NAME_EN   varchar(100),
  REGION_NAME_CN   varchar(100),
  REGION_NAME_ABBR varchar(10),
  IS_COUNTRY       CHAR(1),
  CREATE_USER      varchar(20),
  CREATE_DATE      datetime,
  EDIT_USER        varchar(20),
  EDIT_DATE        datetime,
  STATUS           int
);
alter table MSTB_REGION
  add constraint PK_MSTB_REGION primary key (ID);
create index MSFK_REGION_1 on MSTB_REGION (REGION_CODE);



create table MSTB_REPORT_CACHE
(
  REPORT_CACHE_ID varchar(45) not null,
  REPORT_CODE     varchar(100),
  REPORT_PARAS    varchar(2000),
  FILE_PATH       varchar(1000),
  FILE_R_PATH     varchar(1000),
  RECORD_STATE    int not null,
  CREATED_USER_ID varchar(32),
  CREATED_TIME    datetime,
  UPDATED_USER_ID varchar(32),
  UPDATED_TIME    datetime
);
alter table MSTB_REPORT_CACHE
  add constraint PK_MSTB_REPORT_CACHE primary key (REPORT_CACHE_ID);




create table MSTB_REPORT_INFO
(
  REPORT_INFO_ID  varchar(45) not null,
  REPORT_CODE     varchar(100),
  REPORT_NAME     varchar(500),
  APPLICATION_ID  varchar(45) not null,
  REPORT_TYPE     int,
  REPORT_URL      varchar(500),
  REMARK          varchar(2000),
  STATE           int not null,
  RECORD_STATE    int not null,
  CREATED_USER_ID varchar(32),
  CREATED_TIME    datetime,
  UPDATED_USER_ID varchar(32),
  UPDATED_TIME    datetime
);
alter table MSTB_REPORT_INFO
  add constraint MSPK_REPORT_INFO primary key (REPORT_INFO_ID);
alter table MSTB_REPORT_INFO
  add constraint MSCS_APPLICATION_REPORT foreign key (APPLICATION_ID)
  references MSTB_APPLICATION (APPLICATION_ID);



create table MSTB_REPORT_PARA
(
  REPORT_PARA_ID    varchar(45) not null,
  REPORT_INFO_ID    varchar(45),
  DATA_TYPE         varchar(100),
  PARA_DISPLAY_NAME varchar(200),
  DATA_DISPLAY_TYPE varchar(100),
  PARA_NAME         varchar(100),
  DATA_CODING       varchar(200),
  PARA_ORDER        int,
  REMARK            varchar(2000),
  RECORD_STATE      int not null,
  CREATED_USER_ID   varchar(32),
  CREATED_TIME      datetime,
  UPDATED_USER_ID   varchar(32),
  UPDATED_TIME      datetime
);
alter table MSTB_REPORT_PARA
  add constraint MSPK_REPORT_PARA primary key (REPORT_PARA_ID);
alter table MSTB_REPORT_PARA
  add constraint MSCS_REPORT_INFO_PARA foreign key (REPORT_INFO_ID)
  references MSTB_REPORT_INFO (REPORT_INFO_ID);




create table MSTB_REPORT_SQL
(
  REPORT_SQL_ID   varchar(45) not null,
  REPORT_INFO_ID  varchar(45),
  SQL_SELECT      varchar(2000),
  SQL_WHERE       varchar(2000),
  SQL_ORDER       varchar(1000),
  SQL_TYPE        varchar(2),
  MAP_KEY         varchar(100),
  REMARK          varchar(2000),
  RECORD_STATE    int not null,
  CREATED_USER_ID varchar(32),
  CREATED_TIME    datetime,
  UPDATED_USER_ID varchar(32),
  UPDATED_TIME    datetime
);
alter table MSTB_REPORT_SQL
  add constraint MSPK_REPORT_SQL primary key (REPORT_SQL_ID);
alter table MSTB_REPORT_SQL
  add constraint MSCS_REPORT_INFO_SQL foreign key (REPORT_INFO_ID)
  references MSTB_REPORT_INFO (REPORT_INFO_ID);



create table MSTB_ROLE
(
  ROLE_ID         varchar(45) not null,
  ROLE_CODE       varchar(128) not null,
  ROLE_NAME       varchar(256) not null,
  APPLICATION_ID  varchar(45) not null,
  USER_SQL        varchar(1000),
  REMARK          varchar(2048),
  STATE           int not null,
  RECORD_STATE    int not null,
  CREATED_USER_ID varchar(32),
  CREATED_TIME    datetime,
  UPDATED_USER_ID varchar(32),
  UPDATED_TIME    datetime
);
alter table MSTB_ROLE
  add constraint MSPK_ROLE primary key (ROLE_ID);
alter table MSTB_ROLE
  add constraint FK18236B41D3784850 foreign key (APPLICATION_ID)
  references MSTB_APPLICATION (APPLICATION_ID);




create table MSTB_ROLE_RIGHT
(
  ROLE_RIGHT_ID   varchar(45) not null,
  ROLE_ID         varchar(45) not null,
  MODULE_ID       varchar(45) not null,
  WORK_FLOW_TYPE  int,
  RECORD_STATE    int not null,
  CREATED_USER_ID varchar(32),
  CREATED_TIME    datetime,
  UPDATED_USER_ID varchar(32),
  UPDATED_TIME    datetime
);
alter table MSTB_ROLE_RIGHT
  add constraint MSPK_ROLE_RIGHT primary key (ROLE_RIGHT_ID);




create table MSTB_ROLE_USER
(
  ROLE_USER_ID    varchar(32) not null,
  USERPROFILE_ID  varchar(45) not null,
  ROLE_ID         varchar(45) not null,
  RECORD_STATE    int not null,
  CREATED_USER_ID varchar(32),
  CREATED_TIME    datetime,
  UPDATED_USER_ID varchar(32),
  UPDATED_TIME    datetime,
  IS_LOCAL_RIGHT  int default 0
);
alter table MSTB_ROLE_USER
  add constraint MSPK_ROLE_USER primary key (ROLE_USER_ID);



create table MSTB_SHOP_HEADS
(
  UNIT_CODE          varchar(10),
  SHOP_STATUS        varchar(10),
  SHOP_NAME_CN       varchar(100),
  SHOP_NAME_EN       varchar(100),
  SHOP_CODE          varchar(5),
  CITY_CODE          varchar(10),
  CITY_NAME_CN       varchar(100),
  CITY_NAME_EN       varchar(100),
  PRV_CODE           varchar(10),
  PRV_NAME_CN        varchar(100),
  PRV_NAME_EN        varchar(100),
  REGION_CODE        varchar(10),
  REGION_NAME_CN     varchar(100),
  REGION_NAME_EN     varchar(100),
  FIN_CODE           varchar(10),
  SHOP_HEAD          varchar(10),
  SHOP_HEAD_CN       varchar(30),
  SHOP_HEAD_EN       varchar(30),
  SHOP_HEAD_MAIL     varchar(100),
  SHOP_HEAD_MOBILE   varchar(50),
  SHOP_HEAD2         varchar(10),
  SHOP_HEAD2_CN      varchar(30),
  SHOP_HEAD2_EN      varchar(30),
  SHOP_HEAD2_MAIL    varchar(100),
  SHOP_HEAD2_MOBILE  varchar(50),
  CITY_HEAD          varchar(10),
  CITY_HEAD_CN       varchar(30),
  CITY_HEAD_EN       varchar(30),
  CITY_HEAD_MOBILE   varchar(50),
  PRV_HEAD1          varchar(10),
  PRV_HEAD1_CN       varchar(30),
  PRV_HEAD1_EN       varchar(30),
  PRV_HEAD1_MAIL     varchar(100),
  PRV_HEAD1_GRADE    varchar(5),
  PRV_HEAD1_MOBILE   varchar(50),
  PRV_HEAD2          varchar(10),
  PRV_HEAD2_CN       varchar(30),
  PRV_HEAD2_EN       varchar(30),
  PRV_HEAD2_MAIL     varchar(100),
  PRV_HEAD2_GRADE    varchar(5),
  PRV_HEAD2_MOBILE   varchar(50),
  PRV_HEAD3          varchar(10),
  PRV_HEAD3_CN       varchar(30),
  PRV_HEAD3_EN       varchar(30),
  PRV_HEAD3_MAIL     varchar(100),
  PRV_HEAD3_GRADE    varchar(5),
  PRV_HEAD3_MOBILE   varchar(50),
  PRV_HEAD4          varchar(10),
  PRV_HEAD4_CN       varchar(30),
  PRV_HEAD4_EN       varchar(30),
  PRV_HEAD4_MAIL     varchar(100),
  PRV_HEAD4_GRADE    varchar(5),
  PRV_HEAD4_MOBILE   varchar(50),
  REGION_HEAD        varchar(10),
  REGION_HEAD_CN     varchar(30),
  REGION_HEAD_EN     varchar(30),
  REGION_HEAD_EMAIL  varchar(100),
  REGION_HEAD_MOBILE varchar(50)
);



create table MSTB_SHOP_INFO
(
  UNIT_CODE            varchar(10) not null,
  SHOP_STATUS          varchar(10),
  SHOP_NAME_CN         varchar(100),
  SHOP_NAME_EN         varchar(100),
  SHOP_NAME_OLD        varchar(100),
  SHOP_FORMAT          varchar(50),
  SHOP_SETUP_DATE      datetime,
  SHOP_WORK_HOUR_DAY   varchar(50),
  SHOP_WORK_HOUR_MONTH varchar(50),
  SHOP_PHOTO           BLOB,
  SHOP_WAREHOUSE_CODE  varchar(10),
  SHOP_OFFICE_HOUR     varchar(50),
  SHOP_REST_DATE       varchar(50),
  SHOP_ADDRESS         varchar(500),
  SHOP_FAX_NUM         varchar(20),
  SHOP_SPR_TEL_NUM     varchar(50),
  SHOP_SRV_TEL_NUM     varchar(50),
  SHOP_ZIP_CODE        varchar(10),
  SHOP_EMAIL           varchar(100),
  SHOP_CODE            varchar(5)
);
alter table MSTB_SHOP_INFO
  add constraint SQL060209123705750 primary key (UNIT_CODE);



create table MSTB_SHOP_STAFF
(
  SHOP_STAFF_ID  varchar(50),
  INFO_CODE      varchar(20),
  STAFF_TYPE_SEQ varchar(20),
  STAFF_TYPE     varchar(20),
  ENGLISH_NAME   varchar(30),
  CHINESE_NAME   varchar(30),
  EMP_NUMBER     varchar(20)
);




create table MSTB_SMS_TEMPLATES
(
  SMS_TEMPLATES_ID      varchar(45) not null,
  TEMPLATES_CODE        varchar(128),
  SMS_TEMPLATES_CONTENT varchar(1000),
  APPLICATION_ID        varchar(45),
  REMARK                varchar(2048),
  STATE                 int not null,
  RECORD_STATE          int not null,
  CREATED_USER_ID       varchar(32),
  CREATED_TIME          datetime,
  UPDATED_USER_ID       varchar(32),
  UPDATED_TIME          datetime
);
alter table MSTB_SMS_TEMPLATES
  add constraint MSPK_SMS_TEMPLATES primary key (SMS_TEMPLATES_ID);
alter table MSTB_SMS_TEMPLATES
  add constraint MSCS_APPLICATION_SMS_TEMPLATES foreign key (APPLICATION_ID)
  references MSTB_APPLICATION (APPLICATION_ID);




create table MSTB_TAG_FILEUPLOAD
(
  UUID              varchar(100) not null,
  BIZ_ID            int,
  FILE_ENCRYPT_NAME varchar(100),
  CN_NAME           varchar(200),
  SAVE_PATH         varchar(200) not null,
  MODULE_ID         varchar(50) not null,
  APPLICATION_ID    varchar(45) not null,
  UPLOAD_USER       varchar(100) not null,
  FILE_TYPE         varchar(100),
  REMARK            varchar(2000),
  STATE             int not null,
  RECORD_STATE      int not null,
  CREATED_USER_ID   varchar(32),
  CREATED_TIME      datetime,
  UPDATED_USER_ID   varchar(32),
  UPDATED_TIME      datetime
);
alter table MSTB_TAG_FILEUPLOAD
  add constraint MSPK_TAG_FILEUPLOAD primary key (UUID);
alter table MSTB_TAG_FILEUPLOAD
  add constraint MSCS_APPLICATION_FILE foreign key (APPLICATION_ID)
  references MSTB_APPLICATION (APPLICATION_ID);
create index MSPK_BIZ_ID on MSTB_TAG_FILEUPLOAD (BIZ_ID);




create table MSTB_TIMEING_JOB
(
  TIMEING_JOB_ID   varchar(45) not null,
  TIMEING_JOB_CODE varchar(100),
  TIMEING_JOB_NAME varchar(200),
  APPLICATION_ID   varchar(45),
  CLASS_NAME       varchar(100),
  METHOD_NAME      varchar(100),
  START_TIME       datetime,
  END_TIME         datetime,
  CYCLE            int,
  CYCLE_UNIT       int,
  CRON_JOB_BUNCH   varchar(500),
  FAIL_NUM         int,
  LAST_START_TIME  datetime,
  LAST_END_TIME    datetime,
  RUN_TIME         int,
  LAST_FAIL_NUM    int,
  RUN_STATE        int,
  REMARK           varchar(2000),
  STATE            int not null,
  RECORD_STATE     int not null,
  CREATED_USER_ID  varchar(32),
  CREATED_TIME     datetime,
  UPDATED_USER_ID  varchar(32),
  UPDATED_TIME     datetime
);


alter table MSTB_TIMEING_JOB
  add constraint MSPK_TIMEING_JOB primary key (TIMEING_JOB_ID);
alter table MSTB_TIMEING_JOB
  add constraint MSCS_APPLICATION_JOB foreign key (APPLICATION_ID)
  references MSTB_APPLICATION (APPLICATION_ID);






create table MSTB_EMAIL_ASYN_RECORD
(
  EMAIL_SEND_RECORD_ID varchar(45) not null,
  HOST                 varchar(30),
  PERSONAL_NAME        varchar(200),
  MAIL_FROM_ADD        varchar(30),
  MAIL_TO_ADD          varchar(200) not null,
  MAIL_CC_ADD          varchar(200),
  MAIL_BCC_ADD         varchar(200),
  TEMPLATES_ID         varchar(100),
  EMAIL_SUBJECT        varchar(800),
  EMAIL_CONTENT        varchar(3000),
  ACCESSORY            varchar(200),
  SEND_FLAG            int not null,
  SEND_NUM             int not null,
  SEND_DATE            datetime,
  REMARK               varchar(2000),
  STATE                int not null,
  RECORD_STATE         int not null,
  CREATED_USER_ID      varchar(32),
  CREATED_TIME         datetime,
  UPDATED_USER_ID      varchar(32),

  UPDATED_TIME         datetime
);
alter table MSTB_EMAIL_ASYN_RECORD
  add constraint MSPK_EMAIL_ASYN_RECORD primary key (EMAIL_SEND_RECORD_ID);




create table HSTB_EMAIL_SEND_RECORD_201504
(
  EMAIL_SEND_RECORD_ID varchar(45) not null,
  HOST                 varchar(30),
  PERSONAL_NAME        varchar(30),
  MAIL_FROM_ADD        varchar(30),
  MAIL_TO_ADD          varchar(200) not null,
  MAIL_CC_ADD          varchar(200),
  MAIL_BCC_ADD         varchar(200),
  TEMPLATES_ID         varchar(100),
  EMAIL_SUBJECT        varchar(800),
  EMAIL_CONTENT        varchar(3000),
  ACCESSORY            varchar(200),
  SEND_FLAG            int not null,
  SEND_NUM             int not null,
  SEND_DATE            datetime,
  REMARK               varchar(2000),
  STATE                int not null,
  RECORD_STATE         int not null,
  CREATED_USER_ID      varchar(32),
  CREATED_TIME         datetime,
  UPDATED_USER_ID      varchar(32),
  UPDATED_TIME         datetime
);
alter table HSTB_EMAIL_SEND_RECORD_201504
  add primary key (EMAIL_SEND_RECORD_ID);

  
create table HSTB_LOG_OPERATION_201504
(
  LOG_OPERATION_ID varchar(45) not null,
  EMP_NUMBER       varchar(10) not null,
  USER_LOCATION    varchar(100),
  EVENT_TYPE       varchar(2046),
  EVENT_ACTION     varchar(2046),
  LOG_TIME         datetime,
  LOG_CONTENT      varchar(1000),
  MODULE_ID        varchar(200),
  REMARK           varchar(2000),
  RECORD_STATE     int not null,
  CREATED_USER_ID  varchar(32),
  CREATED_TIME     datetime,
  UPDATED_USER_ID  varchar(32),
  UPDATED_TIME     datetime,
  APPLICATION_ID   varchar(45)
);
alter table HSTB_LOG_OPERATION_201504
  add primary key (LOG_OPERATION_ID);


create table HSTB_LOG_PERFORMANCE_201504
(
  LOG_PERFORMANCE_ID varchar(45) not null,
  JOB_NAME           varchar(100),
  RUN_TIME           int,
  START_TIME         datetime,
  END_TIME           datetime,
  RUN_FLAG           int,
  MODULE_ID          varchar(200),
  REMARK             varchar(2000),
  RECORD_STATE       int not null,
  CREATED_USER_ID    varchar(32),
  CREATED_TIME       datetime,
  UPDATED_USER_ID    varchar(32),
  UPDATED_TIME       datetime,
  APPLICATION_ID     varchar(45)
);
alter table HSTB_LOG_PERFORMANCE_201504
  add primary key (LOG_PERFORMANCE_ID);

  
create table HSTB_SMS_SEND_RECORD_201504
(
  SMS_SEND_RECORD_ID varchar(45) not null,
  DMP                varchar(2000),
  MSG                varchar(4000),
  CREATETIME         datetime,
  STATE              int,
  USERID             varchar(100)
);
alter table HSTB_SMS_SEND_RECORD_201504
  add primary key (SMS_SEND_RECORD_ID);  
















----------------------------------------------------------------------BDS Table
create table MSTB_BDS_SCHEMAINFOR
(
  BDS_SCHEMAINFOR_ID       varchar(45) not null,
  BDS_SCHEMAINFOR_CODE     varchar(40),
  BDS_SCHEMAINFOR_NAME_ENG varchar(30),
  BDS_SCHEMAINFOR_NAME_CNA varchar(100) not null,
  APPLICATION_ID           varchar(45) not null,
  BDS_SCHEMAINFOR_TYPE     varchar(20),
  BDS_SQL                  varchar(2000),
  JNDI                     varchar(200),
  WEBSERVICE_ADDRESS       varchar(200),
  WEBSERVICE_FUNCTION      varchar(200),
  WEBSERVICE_USER          varchar(20),
  WEBSERVICE_PWD           varchar(20),
  DATA_STRUCTURE_XML       text,
  REMARK                   varchar(2000),
  STATE                    int not null,
  RECORD_STATE             int not null,
  CREATED_USER_ID          varchar(32),
  CREATED_TIME             datetime,
  UPDATED_USER_ID          varchar(32),
  UPDATED_TIME             datetime,
  CACHE_TIMEOUT            int,
  WEBSERVICE_NAMESPACE     varchar(200),
  WEBSERVICE_REQ_ROOT      varchar(50),
  WEBSERVICE_RES_ROOT      varchar(50),
  DATA_STRUCTURE_XML_STR   text
);
alter table MSTB_BDS_SCHEMAINFOR
  add constraint MSPK_BDS_SCHEMAINFOR primary key (BDS_SCHEMAINFOR_ID);
alter table MSTB_BDS_SCHEMAINFOR
  add constraint MSCS_APP_BDS_SCHEMAINFOR foreign key (APPLICATION_ID)
  references MSTB_APPLICATION (APPLICATION_ID);


create table MSTB_BDS_XML_DATA
(
  BDS_XML_DATA_ID    varchar(45) not null,
  BDS_SCHEMAINFOR_ID varchar(45),
  CODE               varchar(100),
  DISPLAYNAME        varchar(100),
  DISPLAYNAME_EN     varchar(100),
  DISPLAYNAME_TC     varchar(100),
  BDS_DATA           text,
  REMARK             varchar(2000),
  STATE              int not null,
  RECORD_STATE       int not null,
  CREATED_USER_ID    varchar(32),
  CREATED_TIME       datetime,
  UPDATED_USER_ID    varchar(32),
  UPDATED_TIME       datetime,
  BDS_DATA_STR       text
);
alter table MSTB_BDS_XML_DATA
  add constraint MSPK_BDS_XML_DATA primary key (BDS_XML_DATA_ID);
alter table MSTB_BDS_XML_DATA
  add constraint MSCS_SCHEMAINFOR_XML_DATA foreign key (BDS_SCHEMAINFOR_ID)
  references MSTB_BDS_SCHEMAINFOR (BDS_SCHEMAINFOR_ID);





  
  
