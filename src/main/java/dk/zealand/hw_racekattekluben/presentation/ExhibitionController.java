package dk.zealand.hw_racekattekluben.presentation;

import dk.zealand.hw_racekattekluben.application.service.ExhibitionService;
import dk.zealand.hw_racekattekluben.domain.Exhibition;
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
        // TODO: Hent alle udstillinger og vis exhibition-list
        return "exhibitions/exhibition-list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        // TODO: Tilføj tom Exhibition til model og vis add-exhibition
        return "exhibitions/add-exhibition";
    }

    @PostMapping("/add")
    public String addExhibition(@ModelAttribute Exhibition exhibition) {
        // TODO: Kald exhibitionService.create() og redirect til /exhibitions
        return "redirect:/exhibitions";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        // TODO: Hent exhibition via id og vis edit-exhibition
        return "exhibitions/edit-exhibition";
    }

    @PostMapping("/edit/{id}")
    public String updateExhibition(@PathVariable int id, @ModelAttribute Exhibition exhibition) {
        // TODO: Kald exhibitionService.update() og redirect til /exhibitions
        return "redirect:/exhibitions";
    }

    @PostMapping("/{id}/delete")
    public String deleteExhibition(@PathVariable int id) {
        // TODO: Kald exhibitionService.delete() og redirect til /exhibitions
        return "redirect:/exhibitions";
    }
}