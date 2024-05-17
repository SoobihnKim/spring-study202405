package com.study.springstudy.database.chap01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.net.http.HttpTimeoutException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
class SpringJdbcTest {

    @Autowired // 테스트 시 필드에 주입. public 붙이지말기.
    SpringJdbc springJdbc;

    // 각 테스트 전에 공통으로 실행할 코드
    @BeforeEach
    void bulkInsert() {
        for (int i = 0; i < 10; i++) {
            Person p = new Person(i = 2000, "테스트맨" + i, 10);
            springJdbc.save(p);
        }
    }

    // 단위 테스트 프레임워크: JUnit5
    // 테스트 == 단언(Assertion)
    // 성공한 테스트는 실행할 때마다 성공해야함(rollback)
    @Test
    @DisplayName("사람의 정보를 입력하면 반드시 데이터베이스에 저장되어야 한다.")
    void saveTest() {
        // gwt 패턴
        // given: 테스트에 주어질 데이터(사람의 정보)
        Person p = new Person(77, "칠칠이", 7);

        // when: 테스트 상황
        int result = springJdbc.save(p);

        // then: 테스트 결과 단언
        assertEquals(1, result);
    }

    @Test
    @DisplayName("아이디가 주어지면 해당 아이디의 사람정보가" + "데이터베이스로부터 삭제되어야 한다.")
    void deleteTest() {
        //given
        long id = 77;
        //when
        boolean flag = springJdbc.delete(id);
        //then
        assertTrue(flag);
    }

    @Test
    @DisplayName("새로운 이름과 나이를 전달하면 사람의 정보가 " +
            "데이터베이스에서 수정된다." )
    void modifyTest() {
        //given
        Person person
                = new Person(300, "호야", 8);
        //when
        boolean flag = springJdbc.update(person);
        //then
        assertTrue(flag);
    }


    @Test
    @DisplayName("사람 정보를 전체조회하면 결과 건수는 4건이며, 첫번째 사람의 이름은 \'메롱이\'다.")
    void findAllTest() {
        //given
        // 없으면 생략
        //when
        List<Person> people = springJdbc.findAll();

        //then
        people.forEach(System.out::println);

        assertEquals(4, people.size());
        assertEquals("메롱이", people.get(0).getPersonName());

    }

    @Test
    @DisplayName("사람 정보를 아이디로 단일 조회 시 아이디가 300인 사람의 이름은 호야고 나이는 8살이다.")
    void findOneTest() {
        //given
        long id = 300;
        //when
        Person person = springJdbc.findOne(id);
        //then
        System.out.println("person = " + person);

        assertNotNull(person);
        assertEquals("호야", person.getPersonName());
        assertEquals(8, person.getPersonAge());
    }

    
}