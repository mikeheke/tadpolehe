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
  
  
  
  
  
  
