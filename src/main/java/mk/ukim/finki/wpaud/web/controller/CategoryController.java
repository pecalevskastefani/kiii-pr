package mk.ukim.finki.wpaud.web.controller;

import mk.ukim.finki.wpaud.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String getCategoryPage(Model model) {
        model.addAttribute("categories", this.categoryService.listCategories());
        model.addAttribute("bodyContent", "categories");
        return "master-template";
    }
}
