package spring_crud.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring_crud.domain.Member;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {
    private Long id;

    private String title;

    private String writer;
    private String password;

    private LocalDateTime dateTime;
    private String content;

    private Integer views;
    private Integer count;

    private MemberDto memberDto;
}
