package spring_crud.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import spring_crud.domain.Member;
import spring_crud.dto.LoginDto;
import spring_crud.dto.MemberDto;
import spring_crud.service.MemberServiceImpl;

@Controller
public class MemberController {

    private static final Logger log = LoggerFactory.getLogger(MemberController.class);


    @Autowired
    private MemberServiceImpl memberService;

    @GetMapping("/createMember")
    public String getCreateMember(Model model) {
        model.addAttribute("memberDto", new MemberDto()); // 비어있는 MemberDto 객체를 폼에 전달
        return "member/createMember"; // 회원가입 페이지로 이동
    }

    @PostMapping("/createMember")
    public String processMemberForm(@ModelAttribute MemberDto memberDto) {
        // 회원가입 처리 로직
        memberService.saveDto(memberDto);
        return "redirect:/login"; // 회원가입 후 로그인 페이지로 리디렉션
    }
    @GetMapping("/login")
    public String getLogin(HttpServletRequest request, Model model) {
        String referer = request.getHeader("Referer");
        request.getSession().setAttribute("prevPage", referer);
        log.info("uri={}", referer);
        model.addAttribute("login", new LoginDto());
        return "member/login";
    }

    @PostMapping("/login")
    public String postLogin(@ModelAttribute("login") LoginDto loginDto, HttpServletRequest request, HttpSession session, Model model) {
        boolean login = memberService.login(loginDto);

        if (login) {
            String username = loginDto.getUsername();
            Member member = memberService.findByUsername(username);
            session.setAttribute("loginMember", member);

            String prevPage = (String) request.getSession().getAttribute("prevPage");
            request.getSession().removeAttribute("prevPage");

            return "redirect:" + (prevPage != null ? prevPage : "/");
        }
        model.addAttribute("error", "비밀번호 또는 아이디가 올바르지 않습니다.");
        return "member/login";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("loginMember");
        return "redirect:/";
    }


}
