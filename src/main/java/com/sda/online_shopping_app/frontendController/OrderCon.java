package com.sda.online_shopping_app.frontendController;

import com.sda.online_shopping_app.entity.Order;
import com.sda.online_shopping_app.entity.OrderLine;
import com.sda.online_shopping_app.entity.User;
import com.sda.online_shopping_app.service.OrderService;
import com.sda.online_shopping_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.Optional;

@Controller
@RequestMapping("/orders")
public class OrderCon {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;





    @GetMapping("/{id}")
    public String getOrder(@PathVariable Integer id, Model model) {
        Order order = orderService.findById(id);
        model.addAttribute("order", order);
        model.addAttribute("users", userService.getAll());
        return "order";
    }

//    @PostMapping("/save")
//    public String saveOrder(@ModelAttribute Order order) {
//        orderService.save(order);
//        return "redirect:/orders/" + order.getId();
//    }


    @PostMapping("/save")
    public String saveOrder(@ModelAttribute Order order) {
        // Ensure user is set correctly before saving
        if (order.getUser() != null && order.getUser().getId() != null) {
            Optional<User> optionalUser = userService.getById(order.getUser().getId());
            if (optionalUser.isPresent()) {
                order.setUser(optionalUser.get());
            } else {
                // handle user not found case
                return "error"; // or redirect to an error page
            }
        }

        // Set bidirectional relationship for order lines
        if (order.getOrderLine_id() != null) {
            order.getOrderLine_id().forEach(orderLine -> orderLine.setOrder(order));
        }

        orderService.save(order);
        return "redirect:/orders/" + order.getId();
    }



}
