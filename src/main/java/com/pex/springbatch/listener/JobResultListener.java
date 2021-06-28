package com.pex.springbatch.listener;

import com.pex.springbatch.PrintUtil;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

public class JobResultListener implements JobExecutionListener {

    private final ThreadPoolTaskExecutor taskExecutor;

    public JobResultListener(TaskExecutor taskExecutor) {
        this.taskExecutor = (ThreadPoolTaskExecutor)taskExecutor;
    }

    public void beforeJob(JobExecution jobExecution) {
        PrintUtil.printMemory("beforeJob()");
    }

    public void afterJob(JobExecution jobExecution) {
        PrintUtil.printMemory("afterJob()");
        taskExecutor.shutdown();
    }
}