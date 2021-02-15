package org.sid.billingservice.models;

import lombok.Data;

@Data
public class Product {

    private Long id;
    private String name;
    private Double price;
    private Double quantite;
}
