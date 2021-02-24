package org.sid.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonIgnore // similar as @JsonIgnore
    @DBRef
    private Societie societie;
}
