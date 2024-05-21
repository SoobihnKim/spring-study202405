package com.study.springstudy.springmvc.chap04.dto;

import com.study.springstudy.springmvc.chap04.entity.Board;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// 서버에서 조회한 데이터 중 화면에 필요한 데이터만 모아놓은 클래스
@Getter
public class BoardListResponseDto {

    /*
    자바스크립트로 들어가니 클라이언트와 소통해야함(필드명)
     {
        shortTitle: ""
     }
     */

    private int bno; // 원본 게시물 번호
    private String shortTitle; // 5글자 이상 줄임처리된 제목
    private String shortContent; // 30자 이상 줄임처리된 글 내용
    private String date; // 포맷팅된 날짜문자열
    private int view; // 조회 수
    private boolean hit; // HIT 게시물 여부
    private boolean newArticle; // 새 게시물(5분 이내)인가?

    // 엔터티를 DTO로 변환하는 생성자
    public BoardListResponseDto(Board b) {
        this.bno = b.getBoardNo();
        this.shortTitle = makeShortTitle(b.getTitle());
        this.shortContent = makeShortContent(b.getContent());
        this.date = dateFormatting(b.getRegDateTime());
        this.view = b.getViewCount();
        this.hit = this.view > 5;
        this.newArticle = LocalDateTime.now().isBefore(b.getRegDateTime().plusMinutes(5));
    }

    private String dateFormatting(LocalDateTime regDateTime) {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return pattern.format(regDateTime);
    }

    private String makeShortContent(String content) {
        return(content.length() > 30) ? content.substring(0, 30)  + "..." : content;
    }

    // 5글자보다 크면 자르고 아니면 그냥 리턴하는 함수
    private String makeShortTitle(String title) {
        return(title.length() > 5) ? title.substring(0, 5) + "..." : title;
    }

}
