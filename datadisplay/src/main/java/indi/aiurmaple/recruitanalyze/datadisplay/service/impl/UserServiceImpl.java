package indi.aiurmaple.recruitanalyze.datadisplay.service.impl;

import indi.aiurmaple.recruitanalyze.datadisplay.dao.UserDao;
import indi.aiurmaple.recruitanalyze.datadisplay.entity.UserEntity;
import indi.aiurmaple.recruitanalyze.datadisplay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public Boolean register(UserEntity user) {
        String encryptPassword = BCrypt.hashpw(user.getUserPassword(), BCrypt.gensalt());
        user.setUserPassword(encryptPassword);
        Integer id = userDao.register(user);
        return id > 0;
    }

    @Override
    public UserEntity getUserInfoByName(String userName) {
        return userDao.getUserInfoByName(userName);
    }

    @Override
    public Boolean login(UserEntity user) {
        if (user == null) {
            return false;
        }
        String password = userDao.login(user);
        return !StringUtils.isEmpty(password) && BCrypt.checkpw(user.getUserPassword(), password);
    }
}
