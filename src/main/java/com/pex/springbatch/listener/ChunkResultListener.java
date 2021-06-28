package com.pex.springbatch.listener;

import com.pex.springbatch.PrintUtil;
import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

public class ChunkResultListener implements ChunkListener {

    private final ThreadPoolTaskExecutor taskExecutor;

    public ChunkResultListener(TaskExecutor taskExecutor) {
        this.taskExecutor = (ThreadPoolTaskExecutor)taskExecutor;
    }

    @Override
    public void afterChunk(ChunkContext chunkContext) {
        PrintUtil.printMemory("afterChunk()");
    }

    @Override
    public void afterChunkError(ChunkContext chunkContext) {

    }

    @Override
    public void beforeChunk(ChunkContext chunkContext) {
        PrintUtil.printMemory("beforeChunk()");
        PrintUtil.printPoolSize(taskExecutor.getPoolSize());
    }
}
