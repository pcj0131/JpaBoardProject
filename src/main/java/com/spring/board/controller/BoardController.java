package com.spring.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.board.dto.BoardDTO;
import com.spring.board.dto.UserDTO;
import com.spring.board.entity.Board;
import com.spring.board.service.BoardService;
import com.spring.board.service.CommentsService;
import com.spring.board.service.UserService;

@Controller
@RequestMapping("/board")
public class BoardController {
	private final BoardService boardService;
	private final CommentsService commentsService;
	private final UserService userService;
	
	public BoardController(BoardService boardService, CommentsService commentsService, UserService userService) {
		this.boardService = boardService;
		this.commentsService = commentsService;
		this.userService = userService;
	}
	
	// 게시판 조회
	@GetMapping("/list")
	public String selectBoardList(@PageableDefault Pageable pageable, Model model){
		Page<Board> boardList = boardService.findBoardList(pageable);
		//List<BoardDTO> boardList = boardService.findBoardList();
		model.addAttribute("boardList", boardList);
		return "board/list";
	}
	
	//게시글 작성
	@GetMapping("/post")
	public String insertBoard(Model model, Authentication authentication) {
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		model.addAttribute("username", userDetails.getUsername());
		model.addAttribute("board", new BoardDTO());
		return "board/post";
	}
	
	@PostMapping("/post")
	public String insertBoard(@ModelAttribute BoardDTO boardDto) {
		boardService.save(boardDto);
		return "redirect:/board/list";
	}
	
	// 게시글 상세보기
	@GetMapping("/{bno}")
	public String detailBoard(@PathVariable("bno") Long bno, Model model, Authentication authentication, @PageableDefault Pageable pageable) {
		
		BoardDTO boardDto = boardService.selectBoard(bno);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		model.addAttribute("username", userDetails.getUsername());
		model.addAttribute("board", boardDto);
		model.addAttribute("commentsList", commentsService.findCommentsList(bno, pageable));
		return "board/detail";
	}
	
	// 게시글 수정
	@GetMapping("/update/{bno}")
	public String updateBoard(@PathVariable("bno") Long bno, Model model, Authentication authentication) {
		BoardDTO boardDto = boardService.selectBoard(bno);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		model.addAttribute("username", userDetails.getUsername());
		model.addAttribute("board", boardDto);
		return "board/update";
	}
	
	@PostMapping("/update/{bno}")
	public String updateBoard(BoardDTO boardDto) {
		boardService.save(boardDto);
		return "redirect:/board/list";
	}
	
	// 게시글 삭제
	@PostMapping("/delete/{bno}")
	public String deleteBoard(@PathVariable("bno") Long bno) {
		boardService.deleteBoard(bno);
		return "redirect:/board/list";
	}
	
}
