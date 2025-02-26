package org.esteban.checkoutservice.serviceImpl;

import org.esteban.checkoutservice.model.Checkout;
import org.esteban.checkoutservice.service.ICheckoutService;
import org.esteban.checkoutservice.service.IProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckoutService implements ICheckoutService {
    private IProductService productService;

    public CheckoutService(IProductService productService) {
        super();
        this.productService = productService;
    }

    @Override
    public Checkout buildCheckout(List<String> products_Id) {
        return new Checkout("1", "url", "status", List.of("method1", "method2"));
    }
}
