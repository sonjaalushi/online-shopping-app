package com.sda.online_shopping_app.frontendController;


import java.util.List;
import com.sda.online_shopping_app.entity.Product;
import com.sda.online_shopping_app.service.CategoryService;
import com.sda.online_shopping_app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductCon {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/{id}")
    public String getProduct(@PathVariable Integer id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.getAlL());
        return "product";
    }

    @GetMapping("/new")
    public String createProductForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.getAlL());
        return "product";
    }

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute Product product) {
        productService.save(product);
        return "redirect:/products/" + product.getId();
    }

    @GetMapping("online")
    public String getAllProducts(Model model) {
        try {
            List<Product> products = productService.findAll();
            model.addAttribute("products", products);
        } catch (Exception e) {
            // Log the exception or handle it as needed
            e.printStackTrace();
            model.addAttribute("error", "An error occurred while fetching products.");
        }
        return "productss";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Integer id) {
        productService.deleteByid(id);
        return "redirect:/products";
    }

}
