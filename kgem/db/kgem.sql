create table MSTB_KGEM_KNOWLEDGE
(
	KNOWLEDGE_ID          VARCHAR2(45)     NOT NULL,
	TITLE                 VARCHAR2(200),
	CONTENT               VARCHAR2(2000),
	--
	REMARK 		          VARCHAR2(500),
	RECORD_STATE          NUMBER(2)	    NOT NULL,
	CREATED_USER_ID       VARCHAR2(10),
	CREATED_TIME          TIMESTAMP(6),
	UPDATED_USER_ID       VARCHAR2(10),
	UPDATED_TIME          TIMESTAMP(6)
);
ALTER TABLE MSTB_KGEM_KNOWLEDGE
   ADD CONSTRAINT MSPK_KGEM_KNOWLEDGE PRIMARY KEY (KNOWLEDGE_ID);
   
   
CREATE SEQUENCE MSSQ_KGEM_KNOWLEDGE
      INCREMENT BY 1
      NOMINVALUE
      MAXVALUE 9999999999999999999999999999
      CACHE 20
      NOCYCLE
      NOORDER;

      
create synonym amway.MSTB_KGEM_KNOWLEDGE for BSAAPP.MSTB_KGEM_KNOWLEDGE;      
create synonym amway.MSSQ_KGEM_KNOWLEDGE for BSAAPP.MSSQ_KGEM_KNOWLEDGE;



--MySQL
create table MSTB_KGEM_KNOWLEDGE
(
	KNOWLEDGE_ID          varchar(45)     NOT NULL,
	TITLE                 varchar(200),
	CONTENT               varchar(2000),
	REMARK 		          varchar(500),
	RECORD_STATE          int	    NOT NULL,
	CREATED_USER_ID       varchar(32),
	CREATED_TIME          datetime,
	UPDATED_USER_ID       varchar(32),
	UPDATED_TIME          datetime
);
ALTER TABLE MSTB_KGEM_KNOWLEDGE
   ADD CONSTRAINT MSPK_KGEM_KNOWLEDGE PRIMARY KEY (KNOWLEDGE_ID);





      