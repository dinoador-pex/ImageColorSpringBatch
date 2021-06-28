# ImageColorSpringBatch

### Summary

ImageColorSpringBatch uses Spring Batch. Spring Batch is a Java processing framework designed for robust execution of jobs enabling extremely high-volume and high performance batch jobs through optimization and partitioning techniques.

![Image of Spring Batch Architecture](https://docs.spring.io/spring-batch/docs/current/reference/html/images/spring-batch-reference-model.png)

### Installation

Java 8+<br>
https://www.oracle.com/ca-en/java/technologies/javase/javase-jdk8-downloads.html

Maven 3.0+<br>
https://maven.apache.org/download.cgi

### Build with Maven
mvn clean install

### Run with Maven
mvn spring-boot:run

### Notes
- Configured Spring Batch Job, Step, Reader, Writer, Processor, Listeners, etc
- Steps are configured to do chunk-oriented processing  i.e. reading the data one at a time and creating 'chunks' before writing to output file 
- Steps are processed asynchronously using a ThreadPoolTaskExecutor with a max pool size of 5 to limit memory consumption.
