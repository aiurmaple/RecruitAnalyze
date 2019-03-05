package indi.aiurmaple.recruitanalyze.datadisplay.util;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class CorsUtil {
    public static void setCors(ServletResponse servletResponse) {
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET,POST,DELETE,PUT,OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Origin, x-requested-with, Content-Type, Accept, X-Cookie, Token");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Max-Age", "864000");
    }
}
