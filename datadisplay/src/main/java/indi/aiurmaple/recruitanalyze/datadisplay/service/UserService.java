package indi.aiurmaple.recruitanalyze.datadisplay.service;

import indi.aiurmaple.recruitanalyze.datadisplay.entity.UserEntity;

public interface UserService {
    Boolean register(UserEntity user);

    UserEntity getUserInfoByName(String userName);

    Boolean login(UserEntity user);
}
