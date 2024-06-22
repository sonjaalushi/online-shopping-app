package com.sda.online_shopping_app.frontendController;

import com.sda.online_shopping_app.entity.Category;
import com.sda.online_shopping_app.entity.Product;
import com.sda.online_shopping_app.service.CategoryService;
import com.sda.online_shopping_app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Controller
@RequestMapping("/products")
public class ProductController {

//    private static final String UPLOADED_FOLDER = "uploads/";

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;


    @GetMapping
    public String listProducts(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "products/list";
    }

//
//    @PostMapping("/{id}/upload")
//    public ResponseEntity<?> uploadFile(@PathVariable Integer id, @RequestParam("file") MultipartFile file) {
//        if (file.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please select a file to upload");
//        }
//        try {
//            byte[] bytes = file.getBytes();
//            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
//            Files.write(path, bytes);
//
//
//            Product product = productService.findById(id);
//            if (product != null) {
//                product.setUrl(path.toString());
//                productService.save(product);
//                return ResponseEntity.status(HttpStatus.OK).body("Successfully uploaded - " + file.getOriginalFilename());
//            } else {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
//            }
//        } catch (IOException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file - " + e.getMessage());
//        }
//    }


    @GetMapping("/new")
    public String createProductForm(Model model) {

        model.addAttribute("product", new Product());

        List<Category> categories = categoryService.getAlL();

        model.addAttribute("categories", categories);

        return "products/create";
    }

    @PostMapping
    public String saveProduct(@ModelAttribute Product product) {
        productService.save(product);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String editProductForm(@PathVariable("id") Integer id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "products/edit";
    }

    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable("id") Integer id, @ModelAttribute("product") Product product) {
        product.setId(id);
        productService.save(product);
        return "redirect:/products";
    }


    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Integer id) {
        productService.deleteByid(id);
        return "redirect:/products";
    }
}
