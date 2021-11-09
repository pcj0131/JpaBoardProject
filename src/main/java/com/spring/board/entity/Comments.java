package com.spring.board.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Comments {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;											// 글번호
	
	private String content;										//댓글내용
	
	@ManyToOne
	@JoinColumn(name = "board_id")
	private Board board;										// 글번호 정보
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;											// 작성자 정보

	@Column(updatable = false)
	private LocalDateTime createddate  = LocalDateTime.now();	// 작성일
	
	private LocalDateTime updatedate;							// 수정일
	@Builder
	public Comments(Long id, String content, Board board, User user) {
		this.id = id;
		this.content = content;
		this.board = board;
		this.user = user;
	}
	
	
	
	
	
}
