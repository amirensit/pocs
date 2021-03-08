package org.sid.bankspringbatch;

import org.sid.bankspringbatch.dao.BankTransaction;
import org.sid.bankspringbatch.dao.BankTransactionRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final ItemReader<BankTransaction> itemReader;
    private final ItemWriter<BankTransaction> itemWriter;
    private final ItemProcessor<BankTransaction, BankTransaction> itemProcessor;
    private final BankTransactionRepository bankTransactionRepository;

    public BatchConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory,
                       ItemReader<BankTransaction> itemReader, ItemWriter<BankTransaction> itemWriter,
                       ItemProcessor<BankTransaction, BankTransaction> itemProcessor, BankTransactionRepository bankTransactionRepository) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.itemReader = itemReader;
        this.itemWriter = itemWriter;
        this.itemProcessor = itemProcessor;
        this.bankTransactionRepository = bankTransactionRepository;
    }

    public Job BankJob() {
        Step step1 = stepBuilderFactory.get("step-load-data") // name of step
                .<BankTransaction, BankTransaction>chunk(100)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();
        return jobBuilderFactory.get("job-load-data").start(step1).build();
    }

    @Bean
    public FlatFileItemReader<BankTransaction> itemReader(@Value("${inputFile}") Resource inputFile) {
        FlatFileItemReader<BankTransaction> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setName("flat-file-item-reader");
        flatFileItemReader.setLinesToSkip(1);
        flatFileItemReader.setResource(inputFile);
        flatFileItemReader.setLineMapper(lineMapper());
        return flatFileItemReader;
    }

    private LineMapper<BankTransaction> lineMapper() {
        DefaultLineMapper<BankTransaction> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("id", "accountId", "strTransactionDate", "transactionType", "amount");
        lineMapper.setLineTokenizer(lineTokenizer);
        BeanWrapperFieldSetMapper fieldSetMapper = new BeanWrapperFieldSetMapper();
        fieldSetMapper.setTargetType(BankTransaction.class);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;
    }

    @Bean
    public ItemProcessor bankTransactionItemProcessor() {
        return new BankItemProcessor();
    }

    @Bean
    public ItemWriter bankTransactionItemWriter() {
        return new BankItemWriter(bankTransactionRepository);
    }
}
