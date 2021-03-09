package org.sid.bankspringbatch;

import org.sid.bankspringbatch.dao.BankTransaction;
import org.sid.bankspringbatch.dao.BankTransactionRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final BankTransactionRepository bankTransactionRepository;
    @Value("${inputFile}")
    private Resource inputFile;

    public BatchConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory,
                       BankTransactionRepository bankTransactionRepository) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.bankTransactionRepository = bankTransactionRepository;
    }

    @Bean
    public Job BankJob() {
        Step step1 = stepBuilderFactory.get("step-load-data") // name of step
                .<BankTransaction, BankTransaction>chunk(100)
                .reader(itemReader(inputFile))
               // .processor(bankTransactionItemProcessor()) // the old one: only one itemProcessor
                .processor(bankCompositeItemProcessor()) // list of itemProcessor
                .writer(bankTransactionItemWriter())
                .build();
        return jobBuilderFactory.get("job-load-data").start(step1).build();
    }

    @Bean
    public FlatFileItemReader<BankTransaction> itemReader(Resource inputFile) {
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
        BeanWrapperFieldSetMapper<BankTransaction> fieldSetMapper = new BeanWrapperFieldSetMapper<BankTransaction>();
        fieldSetMapper.setTargetType(BankTransaction.class);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;
    }

    @Bean
    public BankItemProcessor bankTransactionItemProcessor() {
        return new BankItemProcessor();
    }

    @Bean
    public BankAnalyticItemProcessor bankTransactionAnalyticItemProcessor() {
        return new BankAnalyticItemProcessor();
    }

    @Bean
    public CompositeItemProcessor<BankTransaction, BankTransaction> bankCompositeItemProcessor() {
        List<ItemProcessor<BankTransaction, BankTransaction>> itemProcessors = new ArrayList<>();
        itemProcessors.addAll(Arrays.asList(bankTransactionItemProcessor(), bankTransactionAnalyticItemProcessor()));
        CompositeItemProcessor<BankTransaction, BankTransaction> compositeItemProcessor = new CompositeItemProcessor<>();
        compositeItemProcessor.setDelegates(itemProcessors);
        return compositeItemProcessor;
    }

    @Bean
    public ItemWriter<BankTransaction> bankTransactionItemWriter() {
        return new BankItemWriter(bankTransactionRepository);
    }
}
