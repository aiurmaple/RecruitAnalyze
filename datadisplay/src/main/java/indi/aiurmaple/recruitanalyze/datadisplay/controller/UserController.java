package indi.aiurmaple.recruitanalyze.datadisplay.controller;

import indi.aiurmaple.recruitanalyze.datadisplay.entity.UserEntity;
import indi.aiurmaple.recruitanalyze.datadisplay.model.ResponseModel;
import indi.aiurmaple.recruitanalyze.datadisplay.security.RedisTokenService;
import indi.aiurmaple.recruitanalyze.datadisplay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTokenService redisTokenService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseModel<Object> register(UserEntity user) {
        if (user == null || StringUtils.isEmpty(user.getUserName()) || StringUtils.isEmpty(user.getUserPassword())) {
            return new ResponseModel<>(HttpServletResponse.SC_BAD_REQUEST, false,
                    "Input parameter error, Please check your parameter!", null);
        }
        String userName = user.getUserName();
        if (userService.getUserInfoByName(userName) != null) {
            return new ResponseModel<>(HttpServletResponse.SC_FORBIDDEN, false,
                    "Duplicate username entered!", null);
        }

        if (userService.register(user)) {
            return new ResponseModel<>(HttpServletResponse.SC_OK, true,
                    "registration success!", null);
        } else {
            return new ResponseModel<>(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, false,
                    "registration failed!", null);
        }
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public ResponseModel<UserEntity> getUserInfoByName(String userName) {
        if (StringUtils.isEmpty(userName)) {
            return  new ResponseModel<>(HttpServletResponse.SC_BAD_REQUEST, false,
                    "Input parameter error, Please check your parameter!", null);
        }
        if (!redisTokenService.hasToken(userName)) {
            return  new ResponseModel<>(HttpServletResponse.SC_BAD_REQUEST, false,
                    "your token has expired !!", null);
        }

        UserEntity user = userService.getUserInfoByName(userName);
        return new ResponseModel<>(HttpServletResponse.SC_OK, true, "getInfo success!", user);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ResponseModel<Object> logout(String userName) {
        if (StringUtils.isEmpty(userName)) {
            return new ResponseModel<>(HttpServletResponse.SC_BAD_REQUEST, false,
                    "Input parameter error, Please check your parameter!", null);
        }
        if (redisTokenService.removeToken(userName)) {
            return new ResponseModel<>(HttpServletResponse.SC_OK, true, "logout success!", null);
        } else {
            return new ResponseModel<>(HttpServletResponse.SC_BAD_REQUEST, false,
                    "logout Failed!", null);
        }
    }

}
