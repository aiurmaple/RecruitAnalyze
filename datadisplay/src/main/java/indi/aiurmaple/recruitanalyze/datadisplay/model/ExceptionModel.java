package indi.aiurmaple.recruitanalyze.datadisplay.model;

import lombok.Data;

@Data
public class ExceptionModel {
    private String path;
    private String errorMsg;

    public ExceptionModel(String path, String errorMsg) {
        this.path = path;
        this.errorMsg = errorMsg;
    }
}
