package spring_crud.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring_crud.domain.Board;
import spring_crud.domain.Member;
import spring_crud.dto.BoardDto;
import spring_crud.repository.BoardRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final MemberService memberService;

    @Override
    public Board save(BoardDto boardDto) {
        String username = boardDto.getMemberDto().getUsername();
        Member byUsername = memberService.findByUsername(username);
        Board board = Board.builder()
                .title(boardDto.getTitle())
                .dateTime(LocalDateTime.now())
                .writer(byUsername.getUsername())
                .password(boardDto.getPassword())
                .content(boardDto.getContent())
                .count(0)
                .member(byUsername)
                .views(0)
                .build();

        Board save = boardRepository.save(board);
        return save;
    }

    @Override
    public List<Board> findAll() {
        return boardRepository.findAll();
    }
}
