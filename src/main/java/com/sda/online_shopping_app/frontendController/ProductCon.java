package com.sda.online_shopping_app.frontendController;

import java.util.Optional;
import com.sda.online_shopping_app.entity.Order;
import com.sda.online_shopping_app.entity.Product;
import com.sda.online_shopping_app.entity.User;
import com.sda.online_shopping_app.service.CategoryService;
import com.sda.online_shopping_app.service.OrderService;
import com.sda.online_shopping_app.service.ProductService;
import com.sda.online_shopping_app.service.UserService;
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
    private UserService userService;

    @Autowired
    private OrderService orderService;

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
        return "productForm";
    }

    @PostMapping("/order")
    public String placeOrder(@RequestParam("productId") Integer productId, @RequestParam("userId") Integer userId, Model model) {
        Product product = productService.findById(productId);
        Optional<User> userOptional = userService.getById(userId);

        if (product != null && userOptional.isPresent()) {
            User user = userOptional.get();
            Order order = new Order();
            order.setUser(user);
            order.setProduct(product);
            orderService.save(order);
            model.addAttribute("message", "Order placed successfully!");
        } else {
            model.addAttribute("message", "Failed to place order. Invalid product or user.");
        }
        return "orderConfirmation"; // This should be the name of your confirmation page template
    }


    @PostMapping("/save")
    public String saveProduct(@ModelAttribute Product product) {
        productService.save(product);
        return "redirect:/product" + product.getId();
    }

//    @GetMapping("online")
//    public String getAllProducts(Model model) {
//        try {
//            List<Product> products = productService.findAll();
//            model.addAttribute("products", products);
//        } catch (Exception e) {
//            // Log the exception or handle it as needed
//            e.printStackTrace();
//            model.addAttribute("error", "An error occurred while fetching products.");
//        }
//        return "products";
//    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Integer id) {
        productService.deleteByid(id);
        return "redirect:/product";
    }

}
