package com.study.springstudy.springmvc.chap05.entity;

import jdk.jfr.Enabled;
import lombok.*;

import java.time.LocalDateTime;

/*
-- 회원 관리 테이블
CREATE TABLE tbl_member (
    account VARCHAR(50),
    password VARCHAR(150) NOT NULL,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    auth VARCHAR(20) DEFAULT 'COMMON',
    reg_date DATETIME DEFAULT current_timestamp,
    CONSTRAINT pk_member PRIMARY KEY (account)
);
DB랑 1대1로 맞춰야함(필드명 카멜케이스로)
 */
@Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class Member {

    private String account;

    @Setter
    private String password;

    private String name;
    private String email;
    private Auth auth;
    private LocalDateTime regDate;

    private String sessionId;
    private LocalDateTime limitTime;

    @Setter
    private String profileImg;


}
