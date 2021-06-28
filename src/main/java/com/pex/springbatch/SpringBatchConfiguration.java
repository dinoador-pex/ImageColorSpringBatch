package com.pex.springbatch;

import com.pex.springbatch.listener.ChunkResultListener;
import com.pex.springbatch.listener.JobResultListener;
import com.pex.springbatch.model.CustomPojo;
import com.pex.springbatch.writer.CSVQuotingBeanWrapperFieldExtractor;
import com.pex.springbatch.writer.CustomDelimitedAggregator;
import com.pex.springbatch.writer.CustomFlatFileItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfiguration {

    /* ********************************************
     * READERS
     * ********************************************
     */

    @Bean
    public ItemReader<CustomPojo> reader() {
        // Create reader instance
        FlatFileItemReader<CustomPojo> reader = new FlatFileItemReader<CustomPojo>();

        // Set input file location
        reader.setResource(new ClassPathResource("input.txt"));

        // Configure how each line will be parsed and mapped to different values
        reader.setLineMapper(new DefaultLineMapper<CustomPojo>() {
            {
                // Columns in each row
                setLineTokenizer(new DelimitedLineTokenizer() {
                    {
                        setNames(new String[] {"url"});
                        setDelimiter(" ");
                    }
                });
                // Set values in CustomPojo
                setFieldSetMapper(new BeanWrapperFieldSetMapper<CustomPojo>() {
                    {
                        setTargetType(CustomPojo.class);
                    }
                });
            }
        });
        return reader;
    }

    /* ********************************************
     * PROCESSORS
     * ********************************************
     */

    @Bean
    public ItemProcessor<CustomPojo, CustomPojo> processor() {
        return new CustomItemProcessor();
    }

    /* ********************************************
     * WRITERS
     * ********************************************
     */

    @Bean
    public ItemWriter<CustomPojo> writer() {
        FlatFileItemWriter<CustomPojo> writer = new CustomFlatFileItemWriter();
        writer.setResource(new ClassPathResource("output.csv"));

        // Get array of values.
        //BeanWrapperFieldExtractor<CustomPojo> fieldExtractor = new CustomFieldExtractor();
        CSVQuotingBeanWrapperFieldExtractor<CustomPojo> fieldExtractor =
            new CSVQuotingBeanWrapperFieldExtractor<>();
        fieldExtractor.setNames(new String[] {"url", "color1", "color2", "color3"});

        // Converts object to string form, with field values separated by comma.
        DelimitedLineAggregator<CustomPojo> delLineAgg = new CustomDelimitedAggregator();
        delLineAgg.setDelimiter(",");
        delLineAgg.setFieldExtractor(fieldExtractor);

        // Set the LineAggregator
        writer.setLineAggregator(delLineAgg);
        return writer;
    }

    /* ********************************************
     * Jobs / Steps
     * ********************************************
     */

    @Bean
    public Job imageColorJob(JobBuilderFactory jobs, Step imageColorStep) {
        return jobs
            .get("imageColorJob")
            .incrementer(new RunIdIncrementer())
            .listener(jobResultListener(taskExecutor()))
            .flow(imageColorStep)
            .end()
            .build();
    }

    @Bean
    public Step imageColorStep(StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory
            .get("imageColorStep")
            .<CustomPojo, CustomPojo> chunk(5)
            .reader(reader())
            .processor(processor())
            .writer(writer())
            .listener(chunkResultListener(taskExecutor()))
            .taskExecutor(taskExecutor())
            .build();
    }

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(5);
        executor.setQueueCapacity(Integer.MAX_VALUE);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setThreadNamePrefix("MultiThreaded-");
        return executor;
    }

    /* ********************************************
     * Listeners
     * ********************************************
     */

    @Bean
    public JobResultListener jobResultListener(TaskExecutor taskExecutor) {
        return new JobResultListener(taskExecutor);
    }

    @Bean
    public ChunkResultListener chunkResultListener(TaskExecutor taskExecutor) {
        return new ChunkResultListener(taskExecutor);
    }

    /* ********************************************
     * Test
     * ********************************************
     */

    @Bean
    public JobLauncherTestUtils jobLauncherTestUtils() {
        return new JobLauncherTestUtils();
    }

}