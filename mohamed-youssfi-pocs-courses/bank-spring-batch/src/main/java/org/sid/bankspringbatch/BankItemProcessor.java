package org.sid.bankspringbatch;

import org.sid.bankspringbatch.dao.BankTransaction;
import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class BankItemProcessor implements ItemProcessor<BankTransaction, BankTransaction> {

    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm");

    @Override
    public BankTransaction process(BankTransaction bankTransaction) throws Exception {
        bankTransaction.setTransactionDate(
                LocalDate.parse(bankTransaction.getStrTransactionDate(),
                        dateTimeFormatter)
        );
        return bankTransaction;
    }
}
