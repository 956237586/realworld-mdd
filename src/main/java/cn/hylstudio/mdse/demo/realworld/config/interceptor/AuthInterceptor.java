package cn.hylstudio.mdse.demo.realworld.config.interceptor;

import cn.hylstudio.mdse.demo.realworld.Constants;
import cn.hylstudio.mdse.demo.realworld.ContextHolder;
import cn.hylstudio.mdse.demo.realworld.entity.mysql.RealWorldUser;
import cn.hylstudio.mdse.demo.realworld.exception.AuthException;
import cn.hylstudio.mdse.demo.realworld.service.login.IBizLoginService;
import io.netty.util.internal.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthInterceptor.class);
    @Autowired
    private IBizLoginService bizLoginService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        ContextHolder.setCurrentUser(null);
        ContextHolder.setCurrentUserJwt("");
        String authHeader = request.getHeader(Constants.AUTH_HEADER);
        RealWorldUser user = bizLoginService.getCurrentUserFromHeader(authHeader);
        ContextHolder.setCurrentUser(user);
        ContextHolder.setCurrentUserJwt(authHeader);
        return true;
    }
}
