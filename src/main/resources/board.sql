
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
WHERE board_no = 100;


-- 회원 관리 테이블
CREATE TABLE tbl_member (
    account VARCHAR(50),
    password VARCHAR(150) NOT NULL, -- 암호화해야해서 넉넉하게
    name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    auth VARCHAR(20) DEFAULT 'COMMON',
    reg_date DATETIME DEFAULT current_timestamp,
        CONSTRAINT pk_member PRIMARY KEY (account)
);

SELECT * FROM tbl_member;

-- 게시글 테이블과 댓글 테이블에 회원 PK컬럼 추가
ALTER TABLE tbl_board
ADD (account VARCHAR(50));

ALTER TABLE tbl_reply
    ADD (account VARCHAR(50));

SELECT * FROM tbl_board;

UPDATE tbl_member
SET auth = 'ADMIN'
WHERE account = 'admin';

commit;

SELECT * FROM tbl_member;

UPDATE tbl_board
SET account = 'admin'
WHERE account IS NULL;

UPDATE tbl_reply
SET account = 'admin'
WHERE account IS NULL;

commit;

ALTER TABLE tbl_board
ADD CONSTRAINT fk_board_member
FOREIGN KEY (account)
REFERENCES tbl_member (account);

SELECT * FROM tbl_board
ORDER BY board_no DESC ;

SELECT
    board_no, title, writer
     , content, view_count
     , reg_date_time, account
FROM tbl_board
WHERE board_no = 100
ORDER BY board_no DESC
;

SELECT
    B.board_no, B.title, M.name AS writer
     , B.content, B.view_count
     , B.reg_date_time, M.account
FROM tbl_board B
         LEFT OUTER JOIN tbl_member M
                         ON B.account = M.account
WHERE board_no = 100
ORDER BY board_no DESC
;

SELECT
    B.board_no,
    B.title,
    B.content,
    B.writer,
    B.reg_date_time,
    B.view_count,
    COUNT(R.reply_no) AS reply_count,
    B.account
FROM tbl_board B
         LEFT OUTER JOIN tbl_reply R
                         ON B.board_no = R.board_no
GROUP BY B.board_no
ORDER BY board_no DESC
LIMIT 0, 6
;

-- 자동로그인 관련 컬럼 추가
-- 쿠키에 저장한 값, 자동로그인 만료시간
ALTER TABLE tbl_member
ADD (session_id VARCHAR(255) default 'none');

ALTER TABLE tbl_member
    ADD (limit_time DATETIME default current_timestamp);

SELECT * FROM tbl_member;