package com.spring.board.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.spring.board.dto.CommentsDTO;
import com.spring.board.entity.Board;
import com.spring.board.entity.Comments;
import com.spring.board.entity.User;
import com.spring.board.repostitory.CommentsRepository;

@Service
public class CommentsService  {
	private final CommentsRepository commentsRepository;
	
	public CommentsService (CommentsRepository commentsRepository) {
		this.commentsRepository = commentsRepository;
	}
	
	public Page<CommentsDTO> findCommentsList(Long board_id, Pageable pageable){
		Page<Comments> commentPage = commentsRepository.findAllByBoardBnoOrderByBoardBnoDesc(board_id, pageable);
		return commentPage.map(CommentsDTO::new);
	}
	
	public CommentsDTO save(CommentsDTO commentsDTO, Board board, User user) {
		return new CommentsDTO(commentsRepository.save(Comments.builder()
				.content(commentsDTO.getContent())
				.board(board)
				.user(user)
				.build()));
	}
	
	public CommentsDTO findCommentsById(Long id) {
		return new CommentsDTO(commentsRepository.findById(id).get());
	}
	
	// 댓글삭제
	public void deleteComment(Long id) {
		commentsRepository.deleteById(id);
	}
}
