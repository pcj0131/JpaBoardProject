package com.spring.board.dto;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.spring.board.entity.Comments;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentsDTO {
	private Long id;					// 댓글번호
	private String content;				// 댓글내용
	private UserDTO user;				// 작성자
	
	@CreatedDate
	private LocalDateTime createddate;	// 작성일
	
	@LastModifiedDate
	private LocalDateTime updatedate;	// 수정일
	
	public CommentsDTO(Comments comments) {
		this.id = comments.getId();
		this.content = comments.getContent();
		this.user = new UserDTO(comments.getUser());
		this.createddate = comments.getCreateddate();
		this.updatedate = comments.getUpdatedate();
	}
	
}
