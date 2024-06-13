package com.sda.online_shopping_app.frontendController;

import com.sda.online_shopping_app.entity.Category;
import com.sda.online_shopping_app.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
@RequestMapping("/categories")
public class CategoryCon {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/{id}")
    public String getCategory(@PathVariable Integer id, Model model) {
        Optional<Category> category = categoryService.findById(id);

        if (category.isPresent()) {
            Category categoryPresent = category.get();
            model.addAttribute("category", categoryPresent);
            return "category";

        } else

            return "redirect:/error";
    }

    @GetMapping("/new")
    public String createCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "category";
    }

    @PostMapping("/save")
    public String saveCategory(@ModelAttribute Category category) {
        categoryService.saveCategory(category);
        return "redirect:/categories/" + category.getId();
    }

//    @GetMapping("/online")
//    public String getAllCategories(Model model) {
//        model.addAttribute("categories", categoryService.getAlL());
//        return "categories";
//    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Integer id) {
        categoryService.deleteById(id);
        return "redirect:/categories";
    }

}