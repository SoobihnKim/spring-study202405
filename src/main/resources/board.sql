
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

SELECT COUNT(*)
FROM tbl_board;

-- 동적 sql
-- 검색 -> where 절 조건
SELECT * FROM tbl_board
WHERE title LIKE CONCAT('%', '3', '%')
   OR content LIKE '%3%'
ORDER BY board_no DESC
LIMIT 0, 6;

-- 댓글 테이블 생성
CREATE TABLE tbl_reply(
    reply_no INT(8) PRIMARY KEY auto_increment,
    reply_text VARCHAR(1000) NOT NULL,
    reply_writer VARCHAR(100) NOT NULL,
    reply_date DATETIME default current_timestamp,
    board_no INT(8),
    constraint fk_reply
      foreign key (board_no)
      references tbl_board(board_no)
      on delete cascade
);

drop table tbl_reply;

TRUNCATE table tbl_board;

SELECT * FROM tbl_board;
SELECT * FROM tbl_reply;

SELECT * FROM tbl_reply
WHERE board_no = 1;

# 3번 글의 총 댓글 수
SELECT COUNT(*) FROM tbl_reply
WHERE board_no = 3;

SELECT
    B.board_no,
    B.title,
    B.content,
    B.writer,
    B.reg_date_time,
    B.view_count,
    COUNT(R.reply_no) AS reply_count
FROM tbl_board B
JOIN tbl_reply R
on B.board_no = R.board_no
GROUP BY B.board_no;

SELECT * FROM tbl_reply
WHERE reply_no = 2;

SELECT * FROM tbl_reply
WHERE board_no = 101;