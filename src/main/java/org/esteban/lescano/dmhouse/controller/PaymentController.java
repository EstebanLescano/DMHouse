package org.esteban.lescano.dmhouse.controller;

import org.springframework.web.bind.annotation.PostMapping;

public class PaymentController {

    @PostMapping("/payment")
    public String payment() {
        return "payment";
    }

    @PostMapping("/payment/confirm")
    public String paymentConfirm() {
        return "paymentConfirm";
    }


}
