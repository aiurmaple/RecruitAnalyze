package indi.aiurmaple.recruitanalyze.datadisplay.entity;

import lombok.Data;

import java.util.Set;

@Data
public class UserEntity {
    private Long id;
    private String userName;
    private String userPassword;
    private Set<RoleEntity> roleEntities;
}
