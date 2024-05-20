package com.study.springstudy.springmvc.chap03.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter @Setter
public class ScoreModifyRequestDto {

    private long stuNum;
    private int kor, eng, math;

}
