package com.study.springstudy.springmvc.chap03.dto;

import com.study.springstudy.springmvc.chap03.entity.Score;
import lombok.Getter;

@Getter
public class ScoreListResponseDto {

    private long stuNum;
    private String maskingName; // 첫 글자 제외하고 모두 * 처리
    private double average;
    private String grade;

    public ScoreListResponseDto(Score s) {
        this.stuNum = s.getStuNum();
        this.maskingName = makeMaskingName(s.getStuName());
        this.average = s.getAverage();
        this.grade = s.getGrade().toString();
    }

    private String makeMaskingName(String stuName) {

        // 첫 글자 가져오기
        char firstLetter = stuName.charAt(0);
        String maskedName = "" + firstLetter;
        for (int i = 0; i < stuName.length() - 1; i++) {
            maskedName += "*";
        }
        return maskedName;
    }
}
