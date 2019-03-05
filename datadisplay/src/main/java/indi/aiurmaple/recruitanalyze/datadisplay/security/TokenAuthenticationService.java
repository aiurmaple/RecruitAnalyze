package indi.aiurmaple.recruitanalyze.datadisplay.security;

import com.alibaba.fastjson.JSON;
import indi.aiurmaple.recruitanalyze.datadisplay.exception.TokenVaildationException;
import indi.aiurmaple.recruitanalyze.datadisplay.model.ResponseModel;
import indi.aiurmaple.recruitanalyze.datadisplay.security.model.Token;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
public class TokenAuthenticationService {
    private static final long EXPIRATIONTIME = 86_400_000;     // 1天
    private static final String SECRET = "P@ssw04d";            // JWT密码
    private static final String TOKEN_PREFIX = "Datadisplay";        // Token前缀,对称加密密钥
    private static final String HEADER_STRING = "Token";// 存放Token的Header Key

    @Autowired
    private RedisTokenService redisTokenService;

    // JWT生成方法
    public void addAuthentication(HttpServletResponse response, String username,
                                  Collection<? extends GrantedAuthority> roles) throws IOException {
        //判断缓存是否有token
        if (redisTokenService.hasToken(username)) {
            String token = redisTokenService.getToken(username);
            responseToken(response, token);
        } else {
            StringBuffer rolePattern = new StringBuffer();
            roles.forEach((role) -> {
                rolePattern.append(role.getAuthority()).append(",");
            });
            // 生成JWT
            String JWT = Jwts.builder()
                    // 保存权限（角色）
                    .claim("authorities", rolePattern.substring(0, rolePattern.length() - 1))
                    // 用户名写入标题
                    .setSubject(username)
                    // 有效期设置
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                    // 签名设置
                    .signWith(SignatureAlgorithm.HS512, SECRET)
                    .compact();

            // 将JWT 写入 缓存
            redisTokenService.saveToken(username, JWT);
            // 将 JWT 写入 body
            responseToken(response, JWT);
        }
    }

    // JWT验证方法
    public static Authentication getAuthentication(HttpServletRequest request) throws TokenVaildationException {
        // 从Header中拿到token
        String token = request.getHeader(HEADER_STRING);
        try {
            if (token != null) {
                // 解析 Token
                Claims claims = Jwts.parser()
                        // 验签
                        .setSigningKey(SECRET)
                        // 去掉 Bearer
                        .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                        .getBody();

                // 拿用户名
                String user = claims.getSubject();

                // 得到 权限（角色）
                List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList((String) claims.get("authorities"));

                // 返回验证令牌
                if (user == null) {
                    throw new TokenVaildationException("Token verification failed, the reason is: the token cannot get a user");
                }

                return new UsernamePasswordAuthenticationToken(user, null, authorities);
            } else {
                throw new TokenVaildationException("Token verification failed, the reason is: the token is null");
            }
        } catch (Exception e) {
            throw new TokenVaildationException("Token verification failed, the reason is: " + e.getMessage());
        }
    }

    private void responseToken(HttpServletResponse response, String token) throws IOException {
        ResponseModel<Token> responseModel = new ResponseModel<>(HttpServletResponse.SC_OK, true,
                "The token is generated successfully!", new Token(token));
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getOutputStream().println(JSON.toJSONString(responseModel));
    }


}
