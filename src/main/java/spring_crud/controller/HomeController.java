package spring_crud.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import spring_crud.domain.Member;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(Model model, HttpSession session) {
        Member loginMember = (Member) session.getAttribute("loginMember");

        model.addAttribute("loginMember", loginMember);
        return "home";
    }
}
