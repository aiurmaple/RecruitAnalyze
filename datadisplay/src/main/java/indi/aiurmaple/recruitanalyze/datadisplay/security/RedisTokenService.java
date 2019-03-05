package indi.aiurmaple.recruitanalyze.datadisplay.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisTokenService {
    private static final long EXPIRETIME = 1L;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void saveToken(String userName, String token) {
        stringRedisTemplate.opsForValue().set(userName, token);
        stringRedisTemplate.expire(userName, EXPIRETIME, TimeUnit.DAYS);
    }

    public String getToken(String userName) {
        return stringRedisTemplate.opsForValue().get(userName);
    }

    public Boolean hasToken(String userName) {
        return stringRedisTemplate.hasKey(userName);
    }

    public Boolean removeToken(String userName) {
        return stringRedisTemplate.delete(userName);
    }



}
