package org.sid.bankspringbatch;

import lombok.Getter;
import org.sid.bankspringbatch.dao.BankTransaction;
import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class BankAnalyticItemProcessor implements ItemProcessor<BankTransaction, BankTransaction> {
    @Getter
    private Double totalDebit = 0D;
    @Getter
    private Double totalCredit = 0D;

    @Override
    public BankTransaction process(BankTransaction bankTransaction) throws Exception {
        if(bankTransaction.getTransactionType().equals("D"))
            totalDebit += bankTransaction.getAmount();
        if(bankTransaction.getTransactionType().equals("C"))
            totalCredit += bankTransaction.getAmount();
        return bankTransaction;
    }
}
