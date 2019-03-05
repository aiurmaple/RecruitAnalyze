package indi.aiurmaple.recruitanalyze.datadisplay.security.model;

import lombok.Data;

@Data
public class Token {
    private String token;

    public Token(String token) {
        this.token = token;
    }
}
