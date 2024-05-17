package com.study.springstudy.springmvc.chap04.controller;

import com.study.springstudy.springmvc.chap04.entity.Board;
import com.study.springstudy.springmvc.chap04.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardRepository repository;

    // 1. 목록 조회 요청 (/board/list: GET)
    @GetMapping("/list")
    public String list(Model model) {

        List<Board> boards = repository.findAll();

        model.addAttribute("b", repository.findAll());

        return "board/list";
    }

    // 2. 게시글 쓰기 양식 화면 열기 요청 (/board/write: GET)
    @GetMapping("/write")
    public String write(Model model) {

        model.addAttribute("b", new Board());
        return "board/write";
    }

    // 3. 게시글 등록 요청 (/board/write: POST)
    // => 목록조회 요청 리다이렉션
    @PostMapping("/write")
    public String write(@ModelAttribute Board board) {

        Board b = new Board();
        repository.save(b);

        return "redirect:/board/list";
    }

    // 4. 게시글 삭제 요청 (/board/delete: GET)
    // => 목록조회 요청 리다이렉션
    @GetMapping("/delete")
    public String delete(int boardNo) {

        repository.delete(boardNo);
        return "redirect:/board/list";

    }

    // 5. 게시글 상세 조회 요청 (/board/detail : GET)
    @GetMapping("/detail")
    public String detail(Board board, Model model) {
        model.addAttribute("b", board);
        return "board/detail";

    }


}
