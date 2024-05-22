package com.study.springstudy.springmvc.chap04.common;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.checkerframework.checker.index.qual.SearchIndexBottom;

@Getter @Setter @ToString
@EqualsAndHashCode
public class Search extends Page{

    // 검색어, 검색조건
    private String keyword, type;

    public Search() {
        this.keyword = "";
    }


}
