package org.sid.bankspringbatch;

import org.sid.bankspringbatch.dao.BankTransaction;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
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

    public BatchConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory,
                       ItemReader<BankTransaction> itemReader, ItemWriter<BankTransaction> itemWriter,
                       ItemProcessor<BankTransaction, BankTransaction> itemProcessor) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.itemReader = itemReader;
        this.itemWriter = itemWriter;
        this.itemProcessor = itemProcessor;
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
        return flatFileItemReader;
    }
}
