package spring_crud.service;

import spring_crud.domain.Member;
import spring_crud.dto.MemberDto;

public interface MemberService {

    Member saveEntity(Member member);
    Member saveDto(MemberDto memberDto);

    Member findByUsername(String username);
}
