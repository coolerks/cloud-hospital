package top.integer.yygh.common.utils;

import top.integer.yygh.common.helper.JwtHelper;

import javax.servlet.http.HttpServletRequest;

public class AuthContextHolder {
    public static long getUserId(HttpServletRequest request) {
        String token = request.getHeader("token");
        return JwtHelper.getUserId(token);
    }

    public static String getUsername(HttpServletRequest request) {
        String token = request.getHeader("token");
        return JwtHelper.getUsername(token);
    }
}
