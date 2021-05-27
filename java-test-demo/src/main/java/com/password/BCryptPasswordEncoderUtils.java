package com.password;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Set;

public class BCryptPasswordEncoderUtils {
    private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    public static String encodePassword(String password){
        return bCryptPasswordEncoder.encode(password);
    }
 
    public static void main(String[] args) {
        String password = "qwe123";
        String pwd = encodePassword(password);
        System.out.println(pwd);
    }
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        HttpServletResponse httpResponse = (HttpServletResponse) response;
//        String token = request.getHeader("token");
//        JWTResult result = JWTUtils.checkToken(token);
//        Long userId = result.getUserId();
//        HttpServletRequestWrapper requestWrapper = new HttpServletRequestWrapper(httpRequest) {
//            @Override
//            public String[] getParameterValues(String name) {
//                if (name.equals("loginUserId")) {
//                    return new String[] { userId .toString() };
//                }
//                return super.getParameterValues(name);
//            }
//            @Override
//            public Enumeration<String> getParameterNames() {
//                Set<String> paramNames = new LinkedHashSet<>();
//                paramNames.add("loginUserId");
//                Enumeration<String> names =  super.getParameterNames();
//                while(names.hasMoreElements()) {
//                    paramNames.add(names.nextElement());
//                }
//                return Collections.enumeration(paramNames);
//            }
//        };
//        chain.doFilter(requestWrapper, httpResponse);
//    }
}