package indi.aiurmaple.recruitanalyze.datadisplay.security;

import indi.aiurmaple.recruitanalyze.datadisplay.entity.RoleEntity;
import indi.aiurmaple.recruitanalyze.datadisplay.entity.UserEntity;
import indi.aiurmaple.recruitanalyze.datadisplay.security.model.GrantedAuthorityImpl;
import indi.aiurmaple.recruitanalyze.datadisplay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Set;

public class CustomAuthenticationProvider implements AuthenticationProvider {
    private UserService userService;
    private static final String ROLEPREIX = "ROLE_";

    public CustomAuthenticationProvider(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 获取认证的用户名 & 密码
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        // 认证逻辑
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(name);
        userEntity.setUserPassword(password);
        if (userService.login(userEntity)) {
            // 这里设置权限和角色
            UserEntity user = userService.getUserInfoByName(name);
            Set<RoleEntity> roleEntitySet = user.getRoleEntities();
            ArrayList<GrantedAuthority> authorities = new ArrayList<>();
            roleEntitySet.forEach((role) -> authorities.add( new GrantedAuthorityImpl(ROLEPREIX + role.getRoleName()) ));
            // 生成令牌
            return new UsernamePasswordAuthenticationToken(name, password, authorities);
        }else {
            throw new BadCredentialsException("Incorrect account or password when logging in to verify !");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
