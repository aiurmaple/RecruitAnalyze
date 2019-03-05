package indi.aiurmaple.recruitanalyze.datadisplay;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@MapperScan("indi.aiurmaple.recruitanalyze.datadisplay.dao")
public class DatadisplayApplication {

    public static void main(String[] args) {
        SpringApplication.run(DatadisplayApplication.class, args);
    }

}

