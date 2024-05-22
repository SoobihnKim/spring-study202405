
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

-- 페이징

TRUNCATE TABLE tbl_board;

-- 한 페이지 6개 개수 제한
SELECT * FROM tbl_board
ORDER BY board_no DESC
LIMIT 0, 6 -- 0번(인덱스)부터 6개 가져오기
;
-- 2페이지
SELECT * FROM tbl_board
ORDER BY board_no DESC
LIMIT 6, 6 -- 페이지 별로 다르게하려면 파라미터화 해야함
;