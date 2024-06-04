package com.study.springstudy.springmvc.util;

import com.study.springstudy.springmvc.chap05.dto.response.LoginUserInfoDto;
import com.study.springstudy.springmvc.chap05.entity.Auth;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginUtil {

    public static final String LOGIN = "login";
    public static final String AUTO_LOGIN_COOKIE = "auto";

    // 로그인 여부 확인
    // 현재 세션에 사용자가 로그인되어 있는지 여부를 나타내며,
    // 로그인되어 있으면 true를, 그렇지 않으면 false를 반환
    public static boolean isLoggedIn(HttpSession session) {
        return session.getAttribute(LOGIN) != null;
    }

    // 로그인한 회원의 계정명 얻기
    public static String getLoggedInUserAccount(HttpSession session) {
        LoginUserInfoDto loggedInUser = getLoggedInUser(session);
        return loggedInUser != null ? loggedInUser.getAccount() : null;
    }

    public static LoginUserInfoDto getLoggedInUser(HttpSession session) {
        return (LoginUserInfoDto) session.getAttribute(LOGIN);
    }

    // 관리자 여부 확인
    public static boolean isAdmin(HttpSession session) {
        LoginUserInfoDto loggedInUser = getLoggedInUser(session);
        Auth auth = null;
        if (loggedInUser != null) {
            auth = Auth.valueOf(loggedInUser.getAuth());
        }
        return auth == Auth.ADMIN;
    }

    public static boolean isMine(String boardAccount, String loggedInUserAccount) {
        return boardAccount.equals(loggedInUserAccount);
    }

    // 자동로그인 상태인지 확인
    public static boolean isAutoLogin(HttpServletRequest request) {
        Cookie autoLoginCookie = WebUtils.getCookie(request, AUTO_LOGIN_COOKIE);
        return autoLoginCookie != null;
    }
}
