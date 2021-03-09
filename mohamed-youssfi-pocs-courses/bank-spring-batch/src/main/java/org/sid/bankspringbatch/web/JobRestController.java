package org.sid.bankspringbatch.web;

import lombok.extern.slf4j.Slf4j;
import org.sid.bankspringbatch.BankAnalyticItemProcessor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class JobRestController {

    private final JobLauncher jobLauncher;
    private final Job job;
    private final BankAnalyticItemProcessor bankAnalyticItemProcessor;


    public JobRestController(JobLauncher jobLauncher, Job job, BankAnalyticItemProcessor bankAnalyticItemProcessor) {
        this.jobLauncher = jobLauncher;
        this.job = job;
        this.bankAnalyticItemProcessor = bankAnalyticItemProcessor;
    }

    @GetMapping("/startJob")
    public BatchStatus load()
            throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        log.info("start load method");
        Map<String, JobParameter> params = new HashMap<>();
        params.put("time", new JobParameter(System.currentTimeMillis()));
        JobParameters jobParameters = new JobParameters(params);
        JobExecution jobExecution = jobLauncher.run(job, jobParameters);
        while (jobExecution.isRunning()) {
            log.info("Batch test log");
        }
        return jobExecution.getStatus();
    }

    @GetMapping("/analytics")
    public Map<String, Double> getAnalytics() {
        Map<String, Double> map = new HashMap<>();
        map.put("totalCredit", bankAnalyticItemProcessor.getTotalCredit());
        map.put("totalDebit", bankAnalyticItemProcessor.getTotalDebit());
        return map;
    }
}
