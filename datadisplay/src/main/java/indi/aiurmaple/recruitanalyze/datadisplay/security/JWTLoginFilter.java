package indi.aiurmaple.recruitanalyze.datadisplay.security;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import indi.aiurmaple.recruitanalyze.datadisplay.model.ExceptionModel;
import indi.aiurmaple.recruitanalyze.datadisplay.model.ResponseModel;
import indi.aiurmaple.recruitanalyze.datadisplay.security.model.AccountCredentials;
import indi.aiurmaple.recruitanalyze.datadisplay.util.CorsUtil;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {
    private static final Gson GSON = new Gson();
    private TokenAuthenticationService tokenAuthenticationService;

    public JWTLoginFilter(String url, String method, AuthenticationManager authManager, TokenAuthenticationService tokenAuthenticationService) {
        super(new AntPathRequestMatcher(url, method));
        setAuthenticationManager(authManager);
        this.tokenAuthenticationService = tokenAuthenticationService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws AuthenticationException, IOException, ServletException {
        CorsUtil.setCors(httpServletResponse);
        // JSON反序列化成 AccountCredentials
        BufferedReader reader = new BufferedReader(new InputStreamReader(httpServletRequest.getInputStream()));
        AccountCredentials creds = GSON.fromJson(IOUtils.toString(reader), AccountCredentials.class);

        if (creds == null) {
            throw new AuthenticationCredentialsNotFoundException("The account entered is empty, Please check your input");
        }
        // 返回一个验证令牌
        return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                        creds.getUsername(),
                        creds.getPassword()
                )
        );
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
            Authentication auth) throws IOException, ServletException {
        tokenAuthenticationService.addAuthentication(res, auth.getName(), auth.getAuthorities());
    }


    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed)
            throws IOException, ServletException {
            ResponseModel<ExceptionModel> responseModel = new ResponseModel<>(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, false,
                    "Authentication failed", new ExceptionModel(request.getRequestURI(), failed.getMessage()));
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpServletResponse.SC_OK);
            PrintWriter out = response.getWriter();
            out.println(JSON.toJSONString(responseModel));
    }
}
