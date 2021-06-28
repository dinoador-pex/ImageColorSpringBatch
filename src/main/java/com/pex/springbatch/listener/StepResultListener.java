package com.pex.springbatch.listener;

import com.pex.springbatch.PrintUtil;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

public class StepResultListener implements StepExecutionListener {

    @Override
    public void beforeStep(StepExecution stepExecution) {
        PrintUtil.printMemory("beforeStep()");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        PrintUtil.printMemory("afterStep()");
        return ExitStatus.COMPLETED;
    }
}