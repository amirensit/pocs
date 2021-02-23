package org.sid.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document
@AllArgsConstructor @NoArgsConstructor @Data
public class Transaction {

    @Id
    private String id;
    private Instant instant;
    private double price;
    @DBRef
    private Societie societie;
}
