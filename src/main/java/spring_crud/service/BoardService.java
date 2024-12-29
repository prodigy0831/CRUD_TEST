package spring_crud.service;

import spring_crud.domain.Board;
import spring_crud.dto.BoardDto;

import java.util.List;

public interface BoardService {
    Board save(BoardDto boardDto);
    List<Board> findAll();
}
