package org.esteban.checkoutservice.service;

import org.esteban.checkoutservice.model.Checkout;

import java.util.List;

public interface ICheckoutService {
    public Checkout buildCheckout(List<String> products_Id);
}
