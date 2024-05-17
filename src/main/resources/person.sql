CREATE TABLE tbl_person (
                            id INT(6) PRIMARY KEY,
                            person_name VARCHAR(255) NOT NULL,
                            person_age INT(3)
);

SELECT * FROM tbl_person
ORDER BY id DESC
;

SELECT * FROM tbl_person;