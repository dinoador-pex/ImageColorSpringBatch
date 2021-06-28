# ImageColorSpringBatch

### Summary

Unlike [ImageColorBatch](https://github.com/dinoador-pex/ImageColorBatch) which uses core Java, I thought it would be fun to use a framework like Spring Batch. Spring Batch is a lightweight, comprehensive Java framework to facilitate development of robust batch applications.  It also provides more advanced technical services and features that support extremely high volume and high performance batch jobs through its optimization and partitioning techniques.

![Image of Spring Batch Architecture](https://terasoluna-batch.github.io/guideline/5.0.0.RELEASE/en/images/ch02/SpringBatchArchitecture/Ch02_SpringBatchArchitecture_Architecture_ProcessFlow.png)

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
