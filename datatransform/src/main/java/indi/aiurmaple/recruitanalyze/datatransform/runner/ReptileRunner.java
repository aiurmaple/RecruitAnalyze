package indi.aiurmaple.recruitanalyze.datatransform.runner;

import indi.aiurmaple.recruitanalyze.datatransform.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

@Component
public class ReptileRunner implements ApplicationRunner {
    private static final Logger log = LoggerFactory.getLogger(ReptileRunner.class);
    private static final String ARG = "path";

    @Override
    public void run(ApplicationArguments args) {
        File dir = argCheck(args);
        List<File> jsonFiles = FileUtils.filterSuffix(dir, "json");
        jsonFiles.forEach(file -> log.debug("the file is " + file.getName()));
        log.debug("start transform data");

    }

    private File argCheck(ApplicationArguments args) {
        boolean isRequired = args.containsOption(ARG);
        if (!isRequired) {
            log.error("the param path is required to start the program !!!");
            throw new IllegalArgumentException("the param path is required to start the program !!!");
        }
        String filePath = args.getOptionValues(ARG).get(0);
        return new File(filePath);
    }
}
