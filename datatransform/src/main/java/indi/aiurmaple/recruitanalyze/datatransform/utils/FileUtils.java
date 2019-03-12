package indi.aiurmaple.recruitanalyze.datatransform.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class FileUtils {
    private static final Logger log = LoggerFactory.getLogger(FileUtils.class);
    private static final String ENCODING = "utf8";


    public static List<File> filterSuffix(File dir, String suffix) {
        //判断传入路径是不是一个目录 不是则抛出警告异常
        if (!dir.isDirectory()) {
            log.error("the param dir is not a directory !!!");
            throw new IllegalArgumentException("the param dir is not a directory !!!");
        }
        StringBuilder realSuffix = new StringBuilder().append(".").append(suffix);
        List<File> filterFiles = new LinkedList<>();
        //listFiles是获取该目录下所有文件和目录的绝对路径
        File[] files = dir.listFiles();
        if (files != null && files.length != 0) {
            for (File file : files) {
                //endsWith这个方法将帮助你确定一个字符串是否在另一个字符串的末尾，这个方法是大小写敏感的。
                if (file.isFile() && file.getName().endsWith(realSuffix.toString())) {
                    filterFiles.add(file);
                }
            }
        }
        return filterFiles;
    }

    public static String readFile(File file) {
        Long length =  file.length();
        String fileStr = null;
        byte[] fileContent = new byte[length.intValue()];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(fileContent);
            in.close();
            fileStr = new String(fileContent, ENCODING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileStr;
    }
}
