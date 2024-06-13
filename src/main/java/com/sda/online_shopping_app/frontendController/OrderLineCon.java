package com.sda.online_shopping_app.frontendController;

import com.sda.online_shopping_app.entity.OrderLine;
import com.sda.online_shopping_app.service.OrderLineService;
import com.sda.online_shopping_app.service.OrderService;
import com.sda.online_shopping_app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/orderLines")
public class OrderLineCon {

    @Autowired
    private OrderLineService orderLineService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/{id}")
    public String getOrderLine(@PathVariable Integer id, Model model) {
        Optional<OrderLine> orderLine = orderLineService.findByID(id);
        if (orderLine.isPresent()) {
            model.addAttribute("orderLine", orderLine);
            model.addAttribute("products", productService.findAll());
            model.addAttribute("orders", orderService.findAll());
            return "orderLine";
        }else {
            return "redirect:/error";
        }
    }

    @GetMapping("/new")
    public String createOrderLineForm(Model model) {
        model.addAttribute("orderLine", new OrderLine());
        model.addAttribute("products", productService.findAll());
        model.addAttribute("orders", orderService.findAll());
        return "orderLine";
    }

    @PostMapping("/save")
    public String saveOrderLine(@ModelAttribute OrderLine orderLine) {
        orderLineService.save(orderLine);
        return "redirect:/orderLines/" + orderLine.getId();
    }

    @GetMapping
    public String getAllOrderLines(Model model) {
        model.addAttribute("orderLines", orderLineService.getAll());
        return "orderLines";
    }

    @GetMapping("/delete/{id}")
    public String deleteOrderLine(@PathVariable Integer id) {
        orderLineService.deleteByID(id);
        return "redirect:/orderLines";
    }

}
