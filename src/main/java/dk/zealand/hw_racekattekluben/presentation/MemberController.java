package dk.zealand.hw_racekattekluben.presentation;

import dk.zealand.hw_racekattekluben.application.service.MemberService;
import dk.zealand.hw_racekattekluben.domain.Member;
import dk.zealand.hw_racekattekluben.domain.enums.Role;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public String getAllMembers(Model model) {
        model.addAttribute("members", memberService.getAll());
        return "members/member-list";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("member", new Member("", "", "", Role.USER, false));
        return "members/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute Member member) {
        memberService.create(member);
        return "redirect:/login";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        model.addAttribute("member", memberService.getById(id));
        return "members/edit-member";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable int id, @ModelAttribute Member member) {
        member.setId(id);
        memberService.update(member);
        return "redirect:/members";
    }
}