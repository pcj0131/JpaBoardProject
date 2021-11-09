package com.spring.board.repostitory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.board.entity.Comments;

public interface CommentsRepository extends JpaRepository<Comments, Long> {
	@EntityGraph(attributePaths = "user")
	Page<Comments> findAllByBoardBnoOrderByBoardBnoDesc(Long board_id, Pageable pageable);
}
