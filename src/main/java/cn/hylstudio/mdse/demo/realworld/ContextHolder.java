package cn.hylstudio.mdse.demo.realworld;

import cn.hylstudio.mdse.demo.realworld.entity.mysql.RealWorldUser;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

public class ContextHolder {
    private static final String CURRENT_USER_KEY = "CURRENT_USER";
    private static final String CURRENT_USER_JWT = "CURRENT_USER_JWT";

    private ContextHolder() {
    }

    public static RealWorldUser getCurrentUser() {
        return (RealWorldUser) RequestContextHolder.currentRequestAttributes().getAttribute(CURRENT_USER_KEY, RequestAttributes.SCOPE_REQUEST);
    }

    public static void setCurrentUser(RealWorldUser user) {
        RequestContextHolder.currentRequestAttributes().setAttribute(CURRENT_USER_KEY, user, RequestAttributes.SCOPE_REQUEST);
    }
    public static String getCurrentUserJwt() {
        return (String) RequestContextHolder.currentRequestAttributes().getAttribute(CURRENT_USER_JWT, RequestAttributes.SCOPE_REQUEST);
    }

    public static void setCurrentUserJwt(String jwtToken) {
        RequestContextHolder.currentRequestAttributes().setAttribute(CURRENT_USER_JWT, jwtToken, RequestAttributes.SCOPE_REQUEST);
    }
}
