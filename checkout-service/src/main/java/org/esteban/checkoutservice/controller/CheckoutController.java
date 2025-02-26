package org.esteban.checkoutservice.controller;

import org.esteban.checkoutservice.model.Checkout;
import org.esteban.checkoutservice.service.ICheckoutService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/checkout")
public class CheckoutController {
    private ICheckoutService checkoutService;

    public CheckoutController(ICheckoutService checkoutService) {
        super();
        this.checkoutService = checkoutService;
    }

    @RequestMapping("/get")
    public Checkout getCheckout(@RequestParam List<String> products) {
        return checkoutService.buildCheckout(products);
    }
}
