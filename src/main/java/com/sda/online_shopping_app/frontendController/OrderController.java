package com.sda.online_shopping_app.frontendController;

import com.sda.online_shopping_app.entity.Order;
import com.sda.online_shopping_app.entity.Product;
import com.sda.online_shopping_app.service.OrderService;
import com.sda.online_shopping_app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

//
//    @GetMapping("/new/{productId}")
//    public String showOrderForm(@PathVariable Integer productId, Model model) {
//        model.addAttribute("productId", productId);
//        return "orderForm";
//    }
//
//    @PostMapping
//    public String createOrder(@RequestParam Integer productId,
//                              @RequestParam String userName,
//                              @RequestParam String deliveryaddress,
//                              @RequestParam String userAddress) {
//        Product product = productService.findById(productId);
//        Order order = new Order();
//        order.setProduct(product);
//        order.setUserName(userName);
//        order.setDeliveryaddress(deliveryaddress);
//        order.setUserAddress(userAddress);
//        order.setDateOfSubmission(LocalDate.now());
//        order.setStatus(Status.ACTIVE);
//        orderService.save(order);
//        return "redirect:/products";
//    }



    @GetMapping("/new/{productId}")
    public String createOrderForm(@PathVariable("productId") Integer productId, Model model) {
        Product product = productService.findById(productId);
        Order order = new Order();
        order.setProduct(product);
        model.addAttribute("order", order);
        return "orders/create";
    }



    @GetMapping
    public String listOrders(Model model) {
        List<Order> orders = orderService.findAll();
        model.addAttribute("orders", orders);
        return "orders/list";
    }

    @GetMapping("/new")
    public String createOrderForm(Model model) {

        model.addAttribute("order", new Order());
        model.addAttribute("products", productService.findAll());

        return "orders/create";
    }

    @PostMapping
    public String saveOrder(@ModelAttribute("order") Order order) {
        orderService.save(order);
        return "redirect:/orders";
    }

    @GetMapping("/edit/{id}")
    public String editOrderForm(@PathVariable("id") Integer id, Model model) {
        Order order = orderService.findById(id);
        model.addAttribute("order", order);
        return "orders/edit";
    }

    @PostMapping("/update/{id}")
    public String updateOrder(@PathVariable("id") Integer id, @ModelAttribute("order") Order order) {
        order.setId(id);
        orderService.save(order);
        return "redirect:/orders";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteOrder(@PathVariable("id") Integer id) {
        orderService.delete(id);
        return "redirect:/orders";
    }


}