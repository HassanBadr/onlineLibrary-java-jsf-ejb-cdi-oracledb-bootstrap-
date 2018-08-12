-- Create Table: USER_GROUP
--------------------------------------------------------------------------------
CREATE TABLE USER_GROUP
(	ID integer NOT NULL auto_increment
	, USER_ID VARCHAR(64)
	, GROUP_ID INTEGER
    , primary key(ID)
);


-- Create Table: BOOK
--------------------------------------------------------------------------------
CREATE TABLE BOOK
(
	ID integer NOT NULL auto_increment
	,ISBN VARCHAR(64) NOT NULL 
	,TITLE VARCHAR(128) NOT NULL 
	,AUTHOR VARCHAR(128) NOT NULL 
	,PUBLISHER VARCHAR(64) NOT NULL 
	,LANG VARCHAR(64) NOT NULL 
	,CONTENT blob NOT NULL
    ,primary key(ID)
);


-- Create Table: MEGA_USER
--------------------------------------------------------------------------------
CREATE TABLE MEGA_USER
(
	ID VARCHAR(64) NOT NULL
	,FIRST_NAME VARCHAR(32) NOT NULL 
	,LAST_NAME VARCHAR(32) NOT NULL 
	,USER_PASS VARCHAR(64) NOT NULL
    ,primary key(ID)
);

-- Create Table: BOOK_REQUEST
--------------------------------------------------------------------------------
CREATE TABLE BOOK_REQUEST
(
	ID INTEGER NOT NULL auto_increment
	,BOOK_ID INTEGER NOT NULL 
	,USER_ID VARCHAR(64) NOT NULL 
	,REQUEST_TIME BIGINT 
	,RESPONSE_TIME BIGINT 
	,`STATUS` INTEGER NOT NULL
    ,primary key(ID)
);

-- Create Foreign Key: BOOK_REQUEST.USER_ID -> MEGA_USER.ID
ALTER TABLE BOOK_REQUEST ADD CONSTRAINT FK_BOOK_REQUEST_USER_ID_MEGA_USER_ID FOREIGN KEY (USER_ID) REFERENCES MEGA_USER(ID);


-- Create Foreign Key: BOOK_REQUEST.BOOK_ID -> BOOK.ID
ALTER TABLE BOOK_REQUEST ADD CONSTRAINT FK_BOOK_REQUEST_BOOK_ID_BOOK_ID FOREIGN KEY (BOOK_ID) REFERENCES BOOK(ID);

-- Create Foreign Key: USER_GROUP.USER_ID -> MEGA_USER.ID
ALTER TABLE USER_GROUP ADD CONSTRAINT FK_USER_GROUP_USER_ID FOREIGN KEY (USER_ID) REFERENCES MEGA_USER(ID);