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
        model.addAttribute("exhibitions", exhibitionService.getAll());
        return "exhibitions/exhibition-list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("exhibition", new Exhibition());
        return "exhibitions/add-exhibition";
    }

    @PostMapping("/add")
    public String addExhibition(@ModelAttribute Exhibition exhibition) {
        exhibitionService.create(exhibition);
        return "redirect:/exhibitions";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        model.addAttribute("exhibition", exhibitionService.getById(id));
        return "exhibitions/edit-exhibition";
    }

    @PostMapping("/edit/{id}")
    public String updateExhibition(@ModelAttribute Exhibition exhibition) {
        exhibitionService.update(exhibition);
        return "redirect:/exhibitions";
    }

    @PostMapping("/{id}/delete")
    public String deleteExhibition(@PathVariable int id) {
        exhibitionService.delete(id);
        return "redirect:/exhibitions";
    }
}