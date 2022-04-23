//package com.jwt;
//
//import cn.hutool.core.util.IdUtil;
//import com.crc.config.ServletUtils;
//import com.crc.config.constant.Constants;
//import com.crc.config.redis.RedisCache;
//import com.crc.config.security.LoginUser;
//import com.crc.util.StringUtils;
//import eu.bitwalker.useragentutils.UserAgent;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.concurrent.TimeUnit;
//
////import com.crc.util.ip.AddressUtils;
////import com.crc.util.ip.IpUtils;
//
///**
// * token验证处理
// * gz
// */
//@Component
//@Slf4j
//public class TokenService
//{
//    // 令牌自定义标识
//    @Value("${token.header}")
//    private String header;
//
//    // 令牌秘钥
//    @Value("${token.secret}")
//    private String secret;
//
//    // 令牌有效期（默认30分钟）
//    @Value("${token.expireTime}")
//    private int expireTime;
//
//    protected static final long MILLIS_SECOND = 1000;
//
//    protected static final long MILLIS_MINUTE = 60 * MILLIS_SECOND;
//
//    private static final Long MILLIS_MINUTE_TEN = 20 * 60 * 1000L;
//
//    @Autowired
//    private RedisCache redisCache;
//
//    /**
//     * 获取用户身份信息
//     *
//     * @return 用户信息
//     */
//    public LoginUser getLoginUser(HttpServletRequest request)
//    {
//        // 获取请求携带的令牌
//        String token = getToken(request);
//        if (StringUtils.isNotEmpty(token))
//        {
//            Claims claims = parseToken(token);
//            // 解析对应的权限以及用户信息
//            String loginKey = (String) claims.get(Constants.LOGIN_USER_KEY);
//            LoginUser loginUser = redisCache.getCacheObject(loginKey);
//            return loginUser;
//        }
//        return null;
//    }
//
//    /**
//     * 设置用户身份信息
//     */
//    public void setLoginUser(LoginUser loginUser)
//    {
//        if (StringUtils.isNotNull(loginUser) && StringUtils.isNotEmpty(loginUser.getToken()))
//        {
//            refreshToken(loginUser);
//        }
//    }
//
//    /**
//     * 删除用户身份信息
//     */
//    public void delLoginUser(String token)
//    {
//        if (StringUtils.isNotEmpty(token))
//        {
//            String userKey = getTokenKey(token);
//            redisCache.deleteObject(userKey);
//        }
//    }
//
//    /**
//     * 创建令牌
//     *
//     * @param loginUser 用户信息
//     * @return 令牌
//     */
////    public String createToken(LoginUser loginUser)
////    {
//////        String token = IdUtils.fastUUID();
//////        loginUser.setToken(token);
////        setUserAgent(loginUser);
////        refreshToken(loginUser);
////        String token = loginUser.getToken();
////        Claims claims = parseToken(token);
//////        return loginUser.getToken();
////        return createToken(claims);
////
////    }
//    /**
//     * 创建令牌
//     *
//     * @param loginUser 用户信息
//     * @return 令牌
//     */
//    public String createToken(LoginUser loginUser)
//    {
//        setUserAgent(loginUser);
//        String uuid = IdUtil.simpleUUID();
//        loginUser.setUuid(uuid);
//        refreshToken(loginUser);
//        //允许多地点登录
//        //String userKey = getTokenKey(uuid);
//        //单点登录
//        String userKey = getTokenKey(loginUser.getUser().getId()+":"+uuid);
//        Map<String, Object> claims = new HashMap<>();
////        claims.put(Constants.LOGIN_USER_KEY, loginUser);
//        claims.put(Constants.LOGIN_USER_KEY, userKey);
//        return createToken(claims);
//    }
//
//    /**
//     * 验证令牌有效期，相差不足20分钟，自动刷新缓存
//     *
//     * @param token 令牌
//     * @return 令牌
//     */
//    public void verifyToken(LoginUser loginUser)
//    {
//        long expireTime = loginUser.getExpireTime();
//        long currentTime = System.currentTimeMillis();
//        if (expireTime - currentTime <= MILLIS_MINUTE_TEN)
//        {
//            refreshToken(loginUser);
//        }
//    }
//
//    /**
//     * 刷新令牌有效期
//     *
//     * @param loginUser 登录信息
//     */
//    public void refreshToken(LoginUser loginUser)
//    {
//        loginUser.setLoginTime(System.currentTimeMillis());
//        loginUser.setExpireTime(loginUser.getLoginTime() + expireTime * MILLIS_MINUTE);
//        // 根据uuid将loginUser缓存  如果想要单点 就需要加上一个用户的唯一标识（比如uuid） 然后后面新增的时候先把之前的删除了 重新增
////        String userKey = getTokenKey(loginUser.getUuid());
////        redisCache.setCacheObject(userKey, loginUser, expireTime, TimeUnit.MINUTES);
//        //多点登录
//        String userKey = getTokenKey(loginUser.getUser().getId()+":"+loginUser.getUuid());
//        Collection<String> keys = redisCache.keys(Constants.LOGIN_USER_KEY +":"+ loginUser.getUser().getId() + "*");
//        long l = redisCache.deleteObject(keys);
//        log.info("删除历史登录信息个数：{}",l);
//        redisCache.setCacheObject(userKey, loginUser, expireTime, TimeUnit.MINUTES);
//    }
//
//    /**
//     * 设置用户代理信息
//     *
//     * @param loginUser 登录信息
//     */
//    public void setUserAgent(LoginUser loginUser)
//    {
//        UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
//        //String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
//        //loginUser.setIpaddr(ip);
//        //loginUser.setLoginLocation(AddressUtils.getRealAddressByIP(ip));
//        loginUser.setBrowser(userAgent.getBrowser().getName());
//        loginUser.setOs(userAgent.getOperatingSystem().getName());
//    }
//
//    /**
//     * 从数据声明生成令牌
//     *
//     * @param claims 数据声明
//     * @return 令牌
//     */
//    private String createToken(Map<String, Object> claims)
//    {
//        String token = Jwts.builder()
//                .setClaims(claims)
//                .signWith(SignatureAlgorithm.HS512, secret).compact();
//        return token;
//    }
//
//    /**
//     * 从令牌中获取数据声明
//     *
//     * @param token 令牌
//     * @return 数据声明
//     */
//    private Claims parseToken(String token)
//    {
////        String key = Base64.getEncoder().encodeToString(CommonConstant.SIGN_KEY.getBytes());
////        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
//        return Jwts.parser()
//                .setSigningKey(secret)
//                .parseClaimsJws(token)
//                .getBody();
//    }
//
//    /**
//     * 从令牌中获取用户名
//     *
//     * @param token 令牌
//     * @return 用户名
//     */
//    public String getUsernameFromToken(String token)
//    {
//        Claims claims = parseToken(token);
//        return claims.getSubject();
//    }
//
//    /**
//     * 获取请求token
//     *
//     * @param request
//     * @return token
//     */
//    private String getToken(HttpServletRequest request)
//    {
//        String token = request.getHeader(header);
//        if (StringUtils.isNotEmpty(token) && token.startsWith(Constants.TOKEN_PREFIX))
//        {
//            token = token.replace(Constants.TOKEN_PREFIX, "");
//        }
//        return token;
//    }
//
//    private String getTokenKey(String uuid)
//    {
//        return Constants.LOGIN_USER_KEY +":"+ uuid;
//    }
//}