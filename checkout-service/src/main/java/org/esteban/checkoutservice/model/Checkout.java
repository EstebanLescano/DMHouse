package org.esteban.checkoutservice.model;

import lombok.Data;

import java.util.List;

@Data
public class Checkout {
    private String id;
    private String url;
    private String status;
    private List<String> availableMethods;

    public Checkout() {
    }
    public Checkout(String id, String url, String status, List<String> availableMethods) {
        this.id = id;
        this.url = url;
        this.status = status;
        this.availableMethods = availableMethods;
    }
}
