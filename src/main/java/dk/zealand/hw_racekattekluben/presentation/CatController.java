package dk.zealand.hw_racekattekluben.presentation;

import dk.zealand.hw_racekattekluben.application.service.CatService;
import dk.zealand.hw_racekattekluben.domain.Cat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("")
public class CatController {

    private final CatService catService;

    public CatController(CatService catService){
        this.catService = catService;

    }

    @GetMapping
    public String getAllCats(Model model) {
        List<Cat> cats = catService.getAll();
        model.addAttribute("cats", cats);
        return "cats";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("cat", new Cat(null, null, null, null, 0));
        return "add-cat";
    }

    @PostMapping("/delete/{id}")
    public String deleteCat(@PathVariable int id) {
        catService.delete(id);
        return "redirect:/cats";
    }
}
