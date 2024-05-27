package com.study.springstudy.springmvc.chap05.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

// 클라이언트가 서버한테 데이터 줄때

@Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class ReplyPostDto {

//    @NotBlank : null도 안되고 빈 문자도 안됨
//    @NotNull : null은 허용 안됨
//    @NotEmpty : null은 되는데 빈 문자는 안됨

    @NotBlank
    @Size(min = 1, max = 300)
    private String text; // 댓글 내용
    @NotBlank
    @Size(min = 2, max = 8)
    private String author; // 댓글 작성자
    @NotNull
    private Long bno; // 원본 글번호


}
