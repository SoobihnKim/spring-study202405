package com.study.springstudy.springmvc.chap05.api;

import com.study.springstudy.springmvc.chap05.dto.response.ReplyDetailDto;
import com.study.springstudy.springmvc.chap05.entity.Reply;
import com.study.springstudy.springmvc.chap05.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/replies")
@RequiredArgsConstructor
public class ReplyApiController {

    private final ReplyService replyService;

    // 댓글 목록 조회 요청
    // URL : /api/v1/replies/원본 글번호 - GET -> 목록조회
    // @PathVariable: URL에 붙어있는 변수 값을 읽는 아노테이션
    @GetMapping("/{bno}")
    public ResponseEntity<?> list(@PathVariable long bno) {
//        System.out.println("bno = " + bno);

        List<ReplyDetailDto> replies = replyService.getReplies(bno);

        return ResponseEntity
                .ok()
                .body(replies);
    }



}
