package com.crc.config.security.service;

import com.crc.config.exception.BaseException;
import com.crc.config.redis.RedisCache;
import com.crc.config.security.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录校验方法
 * gz
 */
@Component
public class SysLoginService {
    @Autowired
    private TokenService tokenService;

    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    /**
     * 登录验证
     *
     * @param username 用户名
     * @param password 密码
     * @return 结果
     */
    public Map<String, Object> login(String username, String password) {
        Map<String, Object> resultMap = new HashMap<>();
        // 用户验证
        Authentication authentication = null;
        // 该方法会去调用  UserDetailsServiceImpl.loadUserByUsername
        try {
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (AuthenticationException e) {
            e.printStackTrace();
            throw new BaseException("账号或密码有误");
        }
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        // 生成token
        resultMap.put("token", tokenService.createToken(loginUser));
        resultMap.put("user", loginUser.getUser());
        return resultMap;
    }
}
