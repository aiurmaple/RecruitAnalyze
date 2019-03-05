package indi.aiurmaple.recruitanalyze.datadisplay.dao;

import indi.aiurmaple.recruitanalyze.datadisplay.entity.UserEntity;

public interface UserDao {
    Integer register(UserEntity user);

    UserEntity getUserInfoByName(String userName);

    String login(UserEntity user);
}
