package com.study.springstudy.springmvc.chap04.common;

import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class Page {

    private int pageNo; // 클라이언트가 요청한 페이지번호
    private int amount; // 클라이언트가 요청한 한 페이지 당 게시물 목록 수

    // 초기값 설정하면 기본 세팅값으로 할 수 있음.
    public Page() {
        this.pageNo = 1;
        this.amount = 6;
    }

    // 음수나 큰 수를 막기위해(파라미터 값 제어)
    public void setPageNo(int pageNo) {
        if(pageNo < 1 || pageNo > Integer.MAX_VALUE) {
            this.pageNo = 1;
            return;
        }
        this.pageNo = pageNo;
    }

    public void setAmount(int amount) {
        if(amount < 6 || amount > 60) {
            this.amount = 6;
            return;
        }
        this.amount = amount;
    }

    /*
             만약에 한 페이지에 게시물을 10개씩 렌더링한다면
             1페이지 -> LIMIT 0, 10
             2페이지 -> LIMIT 10, 10
             3페이지 -> LIMIT 20, 10

             만약에 한 페이지에 게시물을 6개씩 렌더링한다면
             1페이지 -> LIMIT 0, 6
             2페이지 -> LIMIT 6, 6
             3페이지 -> LIMIT 12, 6

             만약에 한 페이지에 게시물을 n개씩 렌더링한다면
             1페이지 -> LIMIT 0, n
             M페이지 -> LIMIT (M - 1) * n, n
             */
    public int getPageStart() {
        return (this.pageNo - 1) * this.amount;
    }


}