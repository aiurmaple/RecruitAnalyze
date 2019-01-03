package indi.aiurmaple.recruitanalyze.datatransform.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ReptileRunner implements ApplicationRunner {
    private static final Logger log = LoggerFactory.getLogger(ReptileRunner.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.debug("Hello world!");
    }
}
