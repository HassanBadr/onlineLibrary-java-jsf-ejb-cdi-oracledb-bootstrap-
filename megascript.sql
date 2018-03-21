CREATE TABLE BOOK 
(
  ID NUMBER(*, 0) NOT NULL 
, ISBN VARCHAR2(64 BYTE) NOT NULL 
, TITLE VARCHAR2(128 BYTE) NOT NULL 
, AUTHOR VARCHAR2(128 BYTE) NOT NULL 
, PUBLISHER VARCHAR2(64 BYTE) NOT NULL 
, LANG VARCHAR2(64 BYTE) NOT NULL 
, CONTENT BLOB NOT NULL 
, CONSTRAINT SYS_C007314 PRIMARY KEY 
  (
    ID 
  )
  USING INDEX 
  (
      CREATE UNIQUE INDEX SYS_C007314 ON BOOK (ID ASC) 
      LOGGING 
      TABLESPACE SYSTEM 
      PCTFREE 10 
      INITRANS 2 
      STORAGE 
      ( 
        INITIAL 65536 
        NEXT 1048576 
        MINEXTENTS 1 
        MAXEXTENTS UNLIMITED 
        FREELISTS 1 
        FREELIST GROUPS 1 
        BUFFER_POOL DEFAULT 
      ) 
      NOPARALLEL 
  )
  ENABLE 
) 
LOGGING 
TABLESPACE SYSTEM 
PCTFREE 10 
PCTUSED 40 
INITRANS 1 
STORAGE 
( 
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1 
  MAXEXTENTS UNLIMITED 
  FREELISTS 1 
  FREELIST GROUPS 1 
  BUFFER_POOL DEFAULT 
) 
LOB (CONTENT) STORE AS SYS_LOB0000021511C00007$$ 
( 
  ENABLE STORAGE IN ROW 
  CHUNK 8192 
  RETENTION 
  NOCACHE 
  LOGGING 
  TABLESPACE SYSTEM 
  STORAGE 
  ( 
    INITIAL 65536 
    NEXT 1048576 
    MINEXTENTS 1 
    MAXEXTENTS UNLIMITED 
    FREELISTS 1 
    FREELIST GROUPS 1 
    BUFFER_POOL DEFAULT 
  )  
);

CREATE TABLE BOOK_REQUEST 
(
  ID NUMBER(*, 0) NOT NULL 
, BOOK_ID NUMBER(*, 0) NOT NULL 
, USER_ID VARCHAR2(64 BYTE) NOT NULL 
, REQUEST_TIME NUMBER(*, 0) 
, RESPONSE_TIME NUMBER(*, 0) 
, STATUS NUMBER(*, 0) NOT NULL 
, CONSTRAINT SYS_C007324 PRIMARY KEY 
  (
    ID 
  )
  USING INDEX 
  (
      CREATE UNIQUE INDEX SYS_C007324 ON BOOK_REQUEST (ID ASC) 
      LOGGING 
      TABLESPACE SYSTEM 
      PCTFREE 10 
      INITRANS 2 
      STORAGE 
      ( 
        INITIAL 65536 
        NEXT 1048576 
        MINEXTENTS 1 
        MAXEXTENTS UNLIMITED 
        FREELISTS 1 
        FREELIST GROUPS 1 
        BUFFER_POOL DEFAULT 
      ) 
      NOPARALLEL 
  )
  ENABLE 
) 
LOGGING 
TABLESPACE SYSTEM 
PCTFREE 10 
PCTUSED 40 
INITRANS 1 
STORAGE 
( 
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1 
  MAXEXTENTS UNLIMITED 
  FREELISTS 1 
  FREELIST GROUPS 1 
  BUFFER_POOL DEFAULT 
);

CREATE TABLE MEGA_USER 
(
  ID VARCHAR2(64 BYTE) NOT NULL 
, FIRST_NAME VARCHAR2(32 BYTE) NOT NULL 
, LAST_NAME VARCHAR2(32 BYTE) NOT NULL 
, USER_PASS VARCHAR2(64 BYTE) NOT NULL 
, CONSTRAINT SYS_C007319 PRIMARY KEY 
  (
    ID 
  )
  USING INDEX 
  (
      CREATE UNIQUE INDEX SYS_C007319 ON MEGA_USER (ID ASC) 
      LOGGING 
      TABLESPACE SYSTEM 
      PCTFREE 10 
      INITRANS 2 
      STORAGE 
      ( 
        INITIAL 65536 
        NEXT 1048576 
        MINEXTENTS 1 
        MAXEXTENTS UNLIMITED 
        FREELISTS 1 
        FREELIST GROUPS 1 
        BUFFER_POOL DEFAULT 
      ) 
      NOPARALLEL 
  )
  ENABLE 
) 
LOGGING 
TABLESPACE SYSTEM 
PCTFREE 10 
PCTUSED 40 
INITRANS 1 
STORAGE 
( 
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1 
  MAXEXTENTS UNLIMITED 
  FREELISTS 1 
  FREELIST GROUPS 1 
  BUFFER_POOL DEFAULT 
);

CREATE TABLE USER_GROUP 
(
  ID NUMBER(*, 0) NOT NULL 
, USER_ID VARCHAR2(64 BYTE) 
, GROUP_ID NUMBER(*, 0) 
, CONSTRAINT SYS_C007306 PRIMARY KEY 
  (
    ID 
  )
  USING INDEX 
  (
      CREATE UNIQUE INDEX SYS_C007306 ON USER_GROUP (ID ASC) 
      LOGGING 
      TABLESPACE SYSTEM 
      PCTFREE 10 
      INITRANS 2 
      STORAGE 
      ( 
        INITIAL 65536 
        NEXT 1048576 
        MINEXTENTS 1 
        MAXEXTENTS UNLIMITED 
        FREELISTS 1 
        FREELIST GROUPS 1 
        BUFFER_POOL DEFAULT 
      ) 
      NOPARALLEL 
  )
  ENABLE 
) 
LOGGING 
TABLESPACE SYSTEM 
PCTFREE 10 
PCTUSED 40 
INITRANS 1 
STORAGE 
( 
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1 
  MAXEXTENTS UNLIMITED 
  FREELISTS 1 
  FREELIST GROUPS 1 
  BUFFER_POOL DEFAULT 
);

ALTER TABLE BOOK_REQUEST
ADD CONSTRAINT BOOK_REQUEST_FK1 FOREIGN KEY
(
  BOOK_ID 
)
REFERENCES BOOK
(
  ID 
)
ENABLE;

ALTER TABLE BOOK_REQUEST
ADD CONSTRAINT BOOK_REQUEST_FK2 FOREIGN KEY
(
  USER_ID 
)
REFERENCES MEGA_USER
(
  ID 
)
ENABLE;

ALTER TABLE USER_GROUP
ADD CONSTRAINT FK_USER_GROUP_USER_ID FOREIGN KEY
(
  USER_ID 
)
REFERENCES MEGA_USER
(
  ID 
)
ENABLE;

CREATE SEQUENCE BOOK_REQUEST_SEQ INCREMENT BY 1 MAXVALUE 9999999999999999999999999999 MINVALUE 1 CACHE 20;

CREATE SEQUENCE BOOK_SEQ INCREMENT BY 1 MAXVALUE 9999999999999999999999999999 MINVALUE 1 CACHE 20;

CREATE SEQUENCE USER_GROUP_SEQ INCREMENT BY 1 MAXVALUE 9999999999999999999999999999 MINVALUE 1 CACHE 20;