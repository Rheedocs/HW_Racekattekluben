package dk.zealand.hw_racekattekluben.presentation;

import dk.zealand.hw_racekattekluben.application.service.MemberService;
import dk.zealand.hw_racekattekluben.domain.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainMenuController {

    private final MemberService memberService;

    public MainMenuController(MemberService memberService) {
        this.memberService = memberService;
    }
    @GetMapping("/")
    public String mainMenu() {
        return "index";
    }
}

