package dk.zealand.hw_racekattekluben.presentation.controllers;

import dk.zealand.hw_racekattekluben.application.service.MemberService;
import dk.zealand.hw_racekattekluben.domain.Member;
import dk.zealand.hw_racekattekluben.presentation.helpers.AuthHelper;
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
    public String getAllMembers(Model model, HttpSession session) {
        Member loggedIn = AuthHelper.getLoggedIn(session);
        model.addAttribute("members", memberService.getAllSortedByLoggedIn(loggedIn.getId()));
        return "members/member-list";
    }

    @GetMapping("/register")
    public String showRegisterForm() {
        return "members/register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String name, @RequestParam String email, @RequestParam String password,
                           @RequestParam(defaultValue = "false") boolean breeder) {
        memberService.create(name, email, password, breeder);
        return "redirect:/login";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model, HttpSession session) {
        if (!AuthHelper.isAdminOrSelf(session, id)) return "redirect:/access-denied";
        model.addAttribute("member", memberService.getById(id));
        return "members/edit-member";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable int id, @ModelAttribute Member member,
                         @RequestParam(defaultValue = "false") boolean breeder,
                         HttpSession session) {
        if (!AuthHelper.isAdminOrSelf(session, id)) return "redirect:/access-denied";
        member.setId(id);
        memberService.update(member, breeder);
        return "redirect:/members";
    }

    @GetMapping("/{id}/delete")
    public String showDeleteConfirm(@PathVariable int id, Model model, HttpSession session) {
        if (!AuthHelper.isAdminOrSelf(session, id)) return "redirect:/access-denied";
        if (memberService.isOnlyAdmin(id)) throw new IllegalArgumentException("Du kan ikke slette den eneste admin");
        Member member = memberService.getById(id);
        model.addAttribute("navn", member.getName());
        model.addAttribute("deleteUrl", "/members/" + id + "/delete");
        model.addAttribute("tilbage", "/members");
        return "delete-confirm";
    }

    @PostMapping("/{id}/delete")
    public String deleteMember(@PathVariable int id, HttpSession session) {
        if (!AuthHelper.isAdminOrSelf(session, id)) return "redirect:/access-denied";
        if (memberService.isOnlyAdmin(id)) throw new IllegalArgumentException("Du kan ikke slette den eneste admin");
        memberService.delete(id);
        if (AuthHelper.getLoggedIn(session).getId() == id) {
            session.invalidate();
            return "redirect:/login";
        }
        return "redirect:/members";
    }
}