package indi.aiurmaple.recruitanalyze.datadisplay.security;

import com.alibaba.fastjson.JSON;
import indi.aiurmaple.recruitanalyze.datadisplay.model.ExceptionModel;
import indi.aiurmaple.recruitanalyze.datadisplay.model.ResponseModel;
import indi.aiurmaple.recruitanalyze.datadisplay.util.CorsUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class JWTAuthenticationFilter extends GenericFilterBean {
    private List<String> filterUrls;

    public JWTAuthenticationFilter(List<String> filterUrls) {
        this.filterUrls = filterUrls;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        CorsUtil.setCors(response);
        if(!ignoreUrl(request, response, filterUrls)) {
            try {
                Authentication authentication = TokenAuthenticationService
                        .getAuthentication((HttpServletRequest)request);
                SecurityContextHolder.getContext()
                        .setAuthentication(authentication);
                filterChain.doFilter(request, response);
            } catch (Exception e) {
                ResponseModel<ExceptionModel> responseModel = new ResponseModel<>(HttpServletResponse.SC_FORBIDDEN,
                        false, "Token verification failed,Please check your token format!",
                        new ExceptionModel(((HttpServletRequest) request).getRequestURI(), e.getMessage()));
                HttpServletResponse httpServletResponse = (HttpServletResponse) response;
                httpServletResponse.setContentType("application/json");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
                PrintWriter out = httpServletResponse.getWriter();
                out.println(JSON.toJSONString(responseModel));
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }

    private Boolean ignoreUrl(ServletRequest request, ServletResponse response, List<String> filterUrls) {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        if (servletRequest.getMethod().equals("OPTIONS")) {
            return true;
        }
        String path = servletRequest.getRequestURI();
        for (String url : filterUrls) {
            if (url.equals(path)) {
                return true;
            }
        }
        return false;
    }
}
