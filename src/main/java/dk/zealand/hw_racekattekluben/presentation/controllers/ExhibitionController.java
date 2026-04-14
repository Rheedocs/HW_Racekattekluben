package dk.zealand.hw_racekattekluben.presentation.controllers;

import dk.zealand.hw_racekattekluben.application.service.ExhibitionService;
import dk.zealand.hw_racekattekluben.domain.Exhibition;
import dk.zealand.hw_racekattekluben.domain.Member;
import dk.zealand.hw_racekattekluben.presentation.helpers.AuthHelper;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/exhibitions")
public class ExhibitionController {

    private final ExhibitionService exhibitionService;

    public ExhibitionController(ExhibitionService exhibitionService) {
        this.exhibitionService = exhibitionService;
    }

    @GetMapping
    public String getAllExhibitions(Model model) {
        model.addAttribute("exhibitions", exhibitionService.getAll());
        return "exhibitions/exhibition-list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model, HttpSession session) {
        if (!AuthHelper.isAdmin(session)) return "redirect:/access-denied";
        model.addAttribute("exhibition", new Exhibition());
        return "exhibitions/add-exhibition";
    }

    @PostMapping("/add")
    public String addExhibition(@ModelAttribute Exhibition exhibition, HttpSession session) {
        if (!AuthHelper.isAdmin(session)) return "redirect:/access-denied";
        exhibitionService.create(exhibition);
        return "redirect:/exhibitions";
    }

    @GetMapping("/{id}")
    public String showDetail(@PathVariable int id, Model model, HttpSession session) {
        Member loggedIn = AuthHelper.getLoggedIn(session);
        int memberId = loggedIn != null ? loggedIn.getId() : 0;
        model.addAttribute("detail", exhibitionService.getDetailView(id, memberId));
        return "exhibitions/exhibition-detail";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model, HttpSession session) {
        if (!AuthHelper.isAdmin(session)) return "redirect:/access-denied";
        model.addAttribute("exhibition", exhibitionService.getById(id));
        return "exhibitions/edit-exhibition";
    }

    @PostMapping("/edit/{id}")
    public String updateExhibition(@ModelAttribute Exhibition exhibition, HttpSession session) {
        if (!AuthHelper.isAdmin(session)) return "redirect:/access-denied";
        exhibitionService.update(exhibition);
        return "redirect:/exhibitions";
    }

    @PostMapping("/{id}/register-cat")
    public String registerCat(@PathVariable int id, @RequestParam int catId, HttpSession session) {
        Member loggedIn = AuthHelper.getLoggedIn(session);
        exhibitionService.registerCat(catId, id, loggedIn.getId());
        return "redirect:/exhibitions/" + id;
    }

    @GetMapping("/{id}/delete")
    public String showDeleteConfirm(@PathVariable int id, Model model, HttpSession session) {
        if (!AuthHelper.isAdmin(session)) return "redirect:/access-denied";
        Exhibition exhibition = exhibitionService.getById(id);
        model.addAttribute("navn", exhibition.getName());
        model.addAttribute("deleteUrl", "/exhibitions/" + id + "/delete");
        model.addAttribute("tilbage", "/exhibitions");
        return "delete-confirm";
    }

    @PostMapping("/{id}/delete")
    public String deleteExhibition(@PathVariable int id, HttpSession session) {
        if (!AuthHelper.isAdmin(session)) return "redirect:/access-denied";
        exhibitionService.delete(id);
        return "redirect:/exhibitions";
    }
}