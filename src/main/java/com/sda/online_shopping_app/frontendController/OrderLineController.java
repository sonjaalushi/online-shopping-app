package com.sda.online_shopping_app.frontendController;

import com.sda.online_shopping_app.entity.OrderLine;
import com.sda.online_shopping_app.service.OrderLineService;
import com.sda.online_shopping_app.service.ProductService;
import com.sda.online_shopping_app.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/orderlines")
public class OrderLineController {


    @Autowired
    private OrderLineService orderLineService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @GetMapping
    public String listOrderLines(Model model) {
        List<OrderLine> orderLines = orderLineService.getAll();
        model.addAttribute("orderLines", orderLines);
        return "orderlines/list";
    }

    @GetMapping("/new")
    public String createOrderLineForm(Model model) {
        model.addAttribute("orderLine", new OrderLine());
        model.addAttribute("products", productService.findAll());
        model.addAttribute("orders", orderService.findAll());
        return "orderlines/create";
    }

    @PostMapping
    public String saveOrderLine(@ModelAttribute("orderLine") OrderLine orderLine) {
        orderLineService.save(orderLine);
        return "redirect:/orderlines";
    }

    @GetMapping("/edit/{id}")
    public String editOrderLineForm(@PathVariable("id") Integer id, Model model) {
        Optional<OrderLine> orderLine = orderLineService.findByID(id);
        model.addAttribute("orderLine", orderLine);
        model.addAttribute("products", productService.findAll());
        model.addAttribute("orders", orderService.findAll());
        return "orderlines/edit";
    }

    @PostMapping("/update/{id}")
    public String updateOrderLine(@PathVariable("id") Integer id, @ModelAttribute("orderLine") OrderLine orderLine) {
        orderLine.setId(id);
        orderLineService.save(orderLine);
        return "redirect:/orderlines";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteOrderLine(@PathVariable("id") Integer id) {
        orderLineService.deleteByID(id);
        return "redirect:/orderlines";
    }
}
