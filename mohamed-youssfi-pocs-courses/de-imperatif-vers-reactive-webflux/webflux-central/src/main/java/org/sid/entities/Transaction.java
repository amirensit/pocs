package org.sid.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Transaction {

    @Id
    private String id;
    private Instant instant;
    private double price;
    private String societieId;
    @Transient
    private Societie societie;
}
