package org.sid.billingservice.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.sid.billingservice.models.Product;

import javax.persistence.*;

@Entity
@Data
public class ProductItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double quantity;
    private Double price;
    private Long productId;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // used to avoid recursive infinite loop
    private Bill bill;
    @Transient
    private Product product;
}
