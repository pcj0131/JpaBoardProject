package com.spring.board.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.spring.board.dto.BoardDTO;
import com.spring.board.entity.Board;
import com.spring.board.repostitory.BoardRepository;



@Service
public class BoardService {
	private final BoardRepository boardRepository;
	
	public BoardService(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}
	
	/**
	 * 게시판 조회
	 * sort객체를 통해 order by bno desc
	 * stream을 통해 BoardList entity를 BoardDto 형식으로 변경
	 */
//	public List<BoardDTO> findBoardList(){
//		Sort sort = Sort.by(Direction.DESC, "bno");
//		List<Board> boardList = boardRepository.findAll(sort);
//		return boardList.stream().map(BoardDTO::new).collect(Collectors.toList());
//	}
	
//	public List<BoardDTO> findBoardList(Pageable pageable){
//		int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
//		pageable = PageRequest.of(page, 5, Sort.by(Sort.Direction.DESC, "bno"));
//		Page<Board> boardList = boardRepository.findAll(pageable);
//		
//		return boardList.stream().map(BoardDTO::new).collect(Collectors.toList());
//	}
	
	public Page<Board> findBoardList(Pageable pageable){
		int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
		pageable = PageRequest.of(page, 5, Sort.by(Sort.Direction.DESC, "bno"));
		return boardRepository.findAll(pageable);
	}
	
	// 글 상세보기
	public BoardDTO selectBoard(Long bno) {
		Board board = boardRepository.findById(bno).get();
		return new BoardDTO(board);
	}
	
	//글번호 가져오기
	public Board findEntityByBno(Long bno) {
		return boardRepository.findById(bno).get();
	}
	
	// 글 작성, 수정
	public Long save(BoardDTO boardDto) {
		return boardRepository.save(boardDto.toEntity()).getBno();
	}
	
	// 글 삭제
	public void deleteBoard(Long bno) {
		boardRepository.deleteById(bno);
	}

}
