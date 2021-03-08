package org.sid.bankspringbatch;

import org.sid.bankspringbatch.dao.BankTransaction;
import org.sid.bankspringbatch.dao.BankTransactionRepository;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class BankItemWriter implements ItemWriter<BankTransaction> {

    private final BankTransactionRepository bankTransactionRepository;

    public BankItemWriter(BankTransactionRepository bankTransactionRepository) {
        this.bankTransactionRepository = bankTransactionRepository;
    }

    @Override
    public void write(List<? extends BankTransaction> items) throws Exception {
        bankTransactionRepository.saveAll(items);
    }
}
