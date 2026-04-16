package dk.zealand.hw_racekattekluben.presentation.controllers;

import dk.zealand.hw_racekattekluben.application.service.CatService;
import dk.zealand.hw_racekattekluben.domain.Cat;
import dk.zealand.hw_racekattekluben.presentation.helpers.AuthHelper;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cats")
public class CatController {

    private final CatService catService;

    public CatController(CatService catService) {
        this.catService = catService;
    }

    @GetMapping("/cat-list")
    public String getAllCats(Model model) {
        model.addAttribute("cats", catService.getAll());
        return "cats/cat-list";
    }

    @GetMapping("/members/{memberId}")
    public String getCatsByMember(@PathVariable int memberId, Model model) {
        model.addAttribute("cats", catService.getByMemberId(memberId));
        model.addAttribute("memberId", memberId);
        return "cats/cat-list";
    }

    @GetMapping("/members/{memberId}/add-cat")
    public String showAddForm(@PathVariable int memberId, Model model, HttpSession session) {
        if (!AuthHelper.isAdminOrSelf(session, memberId)) return "redirect:/access-denied";
        model.addAttribute("cat", new Cat("", null, "", "", memberId));
        model.addAttribute("allCats", catService.getAll());
        return "cats/add-cat";
    }

    @PostMapping("/add-cat")
    public String addCat(@ModelAttribute Cat cat,
                         @RequestParam(required = false) Integer motherId,
                         @RequestParam(required = false) Integer fatherId,
                         HttpSession session) {
        if (!AuthHelper.isAdminOrSelf(session, cat.getMemberId())) return "redirect:/access-denied";
        cat.setParents(motherId, fatherId);
        String memberName = AuthHelper.getLoggedIn(session).getName();
        catService.create(cat, memberName);
        return "redirect:/cats/members/" + cat.getMemberId();
    }

    @GetMapping("/{id}/detail")
    public String showDetail(@PathVariable int id, Model model) {
        model.addAttribute("cat", catService.getById(id));
        return "cats/cat-detail";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable int id, Model model, HttpSession session) {
        Cat cat = catService.getById(id);
        if (!AuthHelper.isAdminOrSelf(session, cat.getMemberId())) return "redirect:/access-denied";
        model.addAttribute("cat", cat);
        model.addAttribute("allCats", catService.getAll());
        return "cats/edit-cat";
    }

    @PostMapping("/{id}/edit")
    public String updateCat(@PathVariable int id,
                            @ModelAttribute Cat cat,
                            @RequestParam(required = false) Integer motherId,
                            @RequestParam(required = false) Integer fatherId,
                            HttpSession session) {
        if (!AuthHelper.isAdminOrSelf(session, cat.getMemberId())) return "redirect:/access-denied";
        cat.setId(id);
        cat.setParents(motherId, fatherId);
        catService.update(cat);
        return "redirect:/cats/members/" + cat.getMemberId();
    }

    @GetMapping("/{id}/delete")
    public String showDeleteConfirm(@PathVariable int id, Model model, HttpSession session) {
        Cat cat = catService.getById(id);
        if (!AuthHelper.isAdminOrSelf(session, cat.getMemberId())) return "redirect:/access-denied";
        model.addAttribute("navn", cat.getName());
        model.addAttribute("deleteUrl", "/cats/" + id + "/delete");
        model.addAttribute("tilbage", "/cats/members/" + cat.getMemberId());
        return "delete-confirm";
    }

    @PostMapping("/{id}/delete")
    public String deleteCat(@PathVariable int id, HttpSession session) {
        Cat cat = catService.getById(id);
        if (!AuthHelper.isAdminOrSelf(session, cat.getMemberId())) return "redirect:/access-denied";
        catService.delete(id);
        return "redirect:/cats/members/" + cat.getMemberId();
    }
}