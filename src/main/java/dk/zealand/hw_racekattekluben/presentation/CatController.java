package dk.zealand.hw_racekattekluben.presentation;

import dk.zealand.hw_racekattekluben.application.service.CatService;
import dk.zealand.hw_racekattekluben.domain.Cat;
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
        return "cats/cat-list";
    }

    @GetMapping("/members/{memberId}/add-cat")
    public String showAddForm(@PathVariable int memberId, Model model) {
        model.addAttribute("cat", new Cat("", null, "", "", memberId));
        return "cats/add-cat";
    }

    @PostMapping("/add-cat")
    public String addCat(@ModelAttribute Cat cat) {
        catService.create(cat);
        return "redirect:/cats/cat-list";
    }

    @PostMapping("/{id}/delete")
    public String deleteCat(@PathVariable int id) {
        catService.delete(id);
        return "redirect:/cats/cat-list";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable int id, Model model) {
        model.addAttribute("cat", catService.getById(id));
        return "cats/edit-cat";
    }

    @PostMapping("/{id}/edit")
    public String updateCat(@PathVariable int id, @ModelAttribute Cat cat) {
        cat.setId(id);
        catService.update(cat);
        return "redirect:/cats/cat-list";
    }
}
