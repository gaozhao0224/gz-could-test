package com.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: 生成token
 * @Description:
 * @Author: gz
 * @Date 2021/11/13
 */
public class Test {
    private static String secret = "abcdefg";

    /**
    * 生成token
    * */
    public static String createToken(Map<String, Object> claims){
        String token = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret).compact();
        return token;
    }
    /**
    * 解token
    * */
    public static Claims decodeToken(String token){
        Claims obj = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
        return obj;
    }
    public static void main(String[] args) {
        /**
         * redis 存放token解出来的纸当key
         * userCode:id:uuid 作为redis 的key 存放登陆人信息
         * uuid保证每次的token不一样
         * 第二次登陆删除上个token对应的redis的key时  通过模糊删除   删除key为 userCode:id*开头的key
         * 因为id唯一 所以删的就是当前登陆的
         * */
        HashMap<String, Object> map = new HashMap<>();
        map.put("key","userCode:id:uuid");
        String token = createToken(map);
        System.out.println(token);
        Claims claims = decodeToken(token);
        System.out.println(claims);
    }


}
//eyJhbGciOiJIUzUxMiJ9.eyJrZXkiOiJpZDp1dWlkIn0.oif_4uY4t3i16lgTr8fdyQS1nCHkJmydGPJmZrfiBfes5dwDjBwvK9X0A989MMFFPeVnDY0QClB0BMrmSBKY6g
//eyJhbGciOiJIUzUxMiJ9.eyJrZXkiOiJpZDp1dWlkIn0.oif_4uY4t3i16lgTr8fdyQS1nCHkJmydGPJmZrfiBfes5dwDjBwvK9X0A989MMFFPeVnDY0QClB0BMrmSBKY6g