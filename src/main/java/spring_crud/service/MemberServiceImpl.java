package spring_crud.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spring_crud.domain.Member;
import spring_crud.dto.LoginDto;
import spring_crud.dto.MemberDto;
import spring_crud.repository.MemberRepository;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public Member saveEntity(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public Member saveDto(MemberDto memberDto) {
        Member member = Member.builder()
                .username(memberDto.getUsername())
                .password(memberDto.getPassword())
                .build();
        return saveEntity(member);
    }

    @Override
    public Member findByUsername(String username) {
        return memberRepository.findByUsername(username);  // Repository에서 직접 찾기
    }

    public boolean login(LoginDto loginDto) {
        String username = loginDto.getUsername();
        String password = loginDto.getPassword();
        Member byUsername = findByUsername(username);  // memberService 대신 메서드 호출
        if (byUsername != null) {
            if (byUsername.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}
