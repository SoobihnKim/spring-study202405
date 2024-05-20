
CREATE TABLE tbl_board (
    board_no INT(8) PRIMARY KEY auto_increment,
    title VARCHAR(200) NOT NULL,
    content TEXT,
    writer VARCHAR(100) NOT NULL,
    view_count INT(8) DEFAULT 0,
    reg_date_time DATETIME DEFAULT current_timestamp
);



SELECT * FROM tbl_board;

-- 복제테이블 생성 CTAS
CREATE TABLE tbl_board_copy
AS SELECT * FROM tbl_board;