
CREATE TABLE tbl_score (
                           stu_num INT(8) PRIMARY KEY AUTO_INCREMENT,
                           stu_name VARCHAR(255) NOT NULL,
                           kor INT(3) NOT NULL,
                           eng INT(3) NOT NULL,
                           math INT(3) NOT NULL,
                           total INT(3),
                           average FLOAT(5, 2),
                           grade CHAR(1)
);

SELECT * FROM tbl_score;

select stu_num, stu_name, kor, eng, total, A.rank, A.cnt
from (select *,
             RANK() OVER (order by average desc) as rank,
 COUNT(*) OVER() AS cnt
      from tbl_score) A
where stu_num = 2;

SELECT A.stu_num, A.rank, A.cnt
FROM (SELECT *,
             RANK() OVER (ORDER BY average DESC) AS "rank",
              COUNT(*) OVER() AS cnt
      FROM tbl_score) A
WHERE A.stu_num = 1;

SELECT *,
       RANK() OVER (ORDER BY average DESC) AS "rank",
       COUNT(*) OVER() AS cnt
FROM tbl_score
WHERE stu_num = 8;

SELECT * FROM tbl_score;

SELECT * ,
       RANK() OVER (ORDER BY average DESC) AS "rank"
FROM tbl_score;

SELECT COUNT(*)
FROM tbl_score;

SELECT COUNT(*)
FROM tbl_score
GROUP BY stu_num;


