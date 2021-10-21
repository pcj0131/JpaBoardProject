package com.spring.board.dto;

import com.spring.board.entity.Board;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardDTO {
	private Long bno;
	private String title;		// 제목
	private String content;		// 내용
	private String writer;		// 작성자
	
	public Board toEntity() {
		return Board.builder()
				.bno(bno)
				.title(title)
				.content(content)
				.writer(writer)
				.build();
	}

	public BoardDTO(Board board) {
		this.bno = board.getBno();
		this.title = board.getTitle();
		this.content = board.getContent();
		this.writer = board.getWriter();
	}
	
	
}
