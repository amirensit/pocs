package org.sid.bankspringbatch.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
public class BankTransaction {

    @Id
    private Long id;
    private Long accountId;
    private LocalDate transactionDate;
    @Transient
    private String strTransactionDate;
    private String transactionType;
    private Double amount;

}
