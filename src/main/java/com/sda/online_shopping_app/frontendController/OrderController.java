package com.sda.online_shopping_app.frontendController;


import com.sda.online_shopping_app.entity.Order;
import com.sda.online_shopping_app.service.OrderService;
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

    @GetMapping
    public String listOrders(Model model) {
        List<Order> orders = orderService.findAll();
        model.addAttribute("orders", orders);
        return "orders/list";
    }

    @GetMapping("/new")
    public String createOrderForm(Model model) {
        model.addAttribute("order", new Order());
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

//    @GetMapping("/delete/{id}")
//    public String deleteOrder(@PathVariable("id") Integer id) {
//        orderService.deleteOrder(id);
//        return "redirect:/orders";
//    }
}
