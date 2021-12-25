package mk.finki.ukim.wpaud.web.controller;

import mk.finki.ukim.wpaud.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/categories")
public class CategoriesController {
    private final CategoryService categoryService;

    public CategoriesController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String getCategoriesPage(@RequestParam(required = false) String error, HttpServletRequest req, Model model){
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasErrors", true);
            model.addAttribute("error", error);
        }

        model.addAttribute("categories", categoryService.listCategories());
        model.addAttribute("bodyContent", "categories");
        return "master-template";
    }

    @GetMapping("/add-category")
    public String getAddCategoryPage(Model model){

        model.addAttribute("bodyContent", "add-category");
        return "master-template";

    }

    @PostMapping("/add")
    public String getAddCategoryPage(@RequestParam String name,
                                     @RequestParam String description,
                                     Model model) {

        categoryService.save(name, description);

        return "redirect:/categories";
    }
}
