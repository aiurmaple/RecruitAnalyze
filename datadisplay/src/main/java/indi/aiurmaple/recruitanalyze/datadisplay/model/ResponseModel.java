package indi.aiurmaple.recruitanalyze.datadisplay.model;

import lombok.Data;

@Data
public class ResponseModel<T> {
    private Integer code;
    private String message;
    private Boolean status;
    private T data;

    public ResponseModel(Integer code, Boolean status, String message, T data) {
        this.code = code;
        this.message = message;
        this.status = status;
        this.data = data;
    }
}
