package com.spring.board.controller;

import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.board.dto.CommentsDTO;
import com.spring.board.dto.UserDTO;
import com.spring.board.entity.User;
import com.spring.board.repostitory.UserRepository;
import com.spring.board.service.BoardService;
import com.spring.board.service.CommentsService;
import com.spring.board.service.UserDetailServiceImpl;
import com.spring.board.service.UserDetailsImpl;
import com.spring.board.service.UserService;

@RestController
@RequestMapping("/api")
public class CommentController {
	private final CommentsService commentsService;
	private final BoardService boardService;
	private final UserService userService;
	private final UserRepository userRepository;

	public CommentController(CommentsService commentsService, BoardService boardService, UserService userService, UserRepository userRepository) {
		this.commentsService = commentsService;
		this.boardService = boardService;
		this.userService = userService;
		this.userRepository = userRepository;
	}

	@GetMapping("/comments/{board_id}")
	public ResponseEntity<?> insertComment(@PathVariable("board_id") Long bno, @PageableDefault Pageable pageable) {
		return new ResponseEntity<>(commentsService.findCommentsList(bno, pageable), HttpStatus.OK);
	}
	
	@PostMapping("/comments/{board_id}")
	public ResponseEntity<CommentsDTO> insertComment(@PathVariable("board_id") Long bno, @RequestBody CommentsDTO commentsDto, UserDTO userDto, Authentication authentication) {
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		userDto.setId(userRepository.findByUsername(userDetails.getUsername()).getId());
		return ResponseEntity.ok(commentsService.save(commentsDto, boardService.findEntityByBno(bno), userService.toEntity(userDto)));
	}
	
	// 댓글삭제
	@DeleteMapping("/comments/{board_id}/{comment_id}")
	public void commentDelete(@PathVariable("board_id") Long bno, @PathVariable("comment_id") Long id){
		commentsService.deleteComment(id);
	}

}
