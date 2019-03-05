package indi.aiurmaple.recruitanalyze.datadisplay.controller.exception;

import indi.aiurmaple.recruitanalyze.datadisplay.model.ExceptionModel;
import indi.aiurmaple.recruitanalyze.datadisplay.model.ResponseModel;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseModel<ExceptionModel> exceptionHandler(HttpServletRequest req, Exception e){
        return new ResponseModel<>(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, false,
                "Internal Server Error!", new ExceptionModel(req.getRequestURI(), e.getMessage()));
    }
}
