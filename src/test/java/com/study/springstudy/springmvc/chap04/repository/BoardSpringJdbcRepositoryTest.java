package com.study.springstudy.springmvc.chap04.repository;

import com.study.springstudy.database.chap01.SpringJdbc;
import com.study.springstudy.springmvc.chap04.entity.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
class BoardSpringJdbcRepositoryTest {

    @Autowired
    BoardSpringJdbcRepository springJdbc;

    @BeforeEach
    void setUp() {
        for (int i = 0; i < 10; i++) {
            Board b = new Board("haha" + i, "test" + i, "df" + i);
            springJdbc.save(b);
        }
    }

    @Test
    @DisplayName(" ")
    void saveTest() {
        //given
        Board b = new Board("gk", "hi", "me");
        //when
        boolean result = springJdbc.save(b);
        //then
        assertEquals(true, result);
    }


    @Test
    @DisplayName("")
    void deleteTest() {
        //given
        int boardId = 1;
        //when
        boolean flag = springJdbc.delete(boardId);
        //then
        assertTrue(flag);
    }


}