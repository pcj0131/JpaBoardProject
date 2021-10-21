package com.spring.board.repostitory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.board.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
