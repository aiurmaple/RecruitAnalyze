package indi.aiurmaple.recruitanalyze.datatransform.utils;

import indi.aiurmaple.recruitanalyze.datatransform.runner.ReptileRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class FileUtils {
    private static final Logger log = LoggerFactory.getLogger(FileUtils.class);

    public static List<File> filterSuffix(File dir, String suffix) {
        if (!dir.isDirectory()) {
            log.error("the param dir is not a directory !!!");
            throw new IllegalArgumentException("the param dir is not a directory !!!");
        }
        StringBuilder realSuffix = new StringBuilder().append(".").append(suffix);
        List<File> filterFiles = new LinkedList<>();
        File[] files = dir.listFiles();
        if (files != null && files.length != 0) {
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(realSuffix.toString())) {
                    filterFiles.add(file);
                }
            }
        }
        return filterFiles;
    }
}
