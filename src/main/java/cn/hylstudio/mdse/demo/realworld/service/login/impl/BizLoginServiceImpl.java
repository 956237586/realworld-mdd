package cn.hylstudio.mdse.demo.realworld.service.login.impl;

import cn.hylstudio.mdse.demo.realworld.Constants;
import cn.hylstudio.mdse.demo.realworld.controller.user.UsersController;
import cn.hylstudio.mdse.demo.realworld.entity.mysql.RealWorldUser;
import cn.hylstudio.mdse.demo.realworld.exception.AuthException;
import cn.hylstudio.mdse.demo.realworld.exception.BizException;
import cn.hylstudio.mdse.demo.realworld.model.request.user.UserLoginRequestDto;
import cn.hylstudio.mdse.demo.realworld.model.request.user.UserLoginRequestPayload;
import cn.hylstudio.mdse.demo.realworld.model.response.user.UserLoginResponseDto;
import cn.hylstudio.mdse.demo.realworld.model.response.user.UserLoginResponseResult;
import cn.hylstudio.mdse.demo.realworld.service.login.IBizLoginService;
import cn.hylstudio.mdse.demo.realworld.service.user.IBizUserService;
import cn.hylstudio.mdse.demo.realworld.util.ValueUtils;
import com.google.gson.Gson;
import io.netty.util.internal.StringUtil;
import net.bytebuddy.utility.RandomString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.concurrent.TimeUnit;

import static cn.hylstudio.mdse.demo.realworld.Constants.JWT_HEADER_PREFIX;

@Service
public class BizLoginServiceImpl implements IBizLoginService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UsersController.class);
    @Autowired
    private IBizUserService bizUserService;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private Gson gson;
    private static final String USER_SESSION_KEY_PATTERN = "SESSION_%s";

    public RealWorldUser getCurrentUserFromHeader(String authHeader) {
        if (StringUtil.isNullOrEmpty(authHeader)) {
            LOGGER.info("auth failed empty authHeader, authHeader = [{}]", authHeader);
            throw new AuthException("auth error");
        }
        authHeader = authHeader.trim();
        if (!authHeader.startsWith(JWT_HEADER_PREFIX)) {
            LOGGER.info("auth failed not start with Token, authHeader = [{}]", authHeader);
            throw new AuthException("auth error");
        }
        String jwtToken = authHeader.substring(JWT_HEADER_PREFIX.length());
        if (StringUtil.isNullOrEmpty(jwtToken)) {
            LOGGER.info("auth failed empty jwtToken, authHeader = [{}]", authHeader);
            throw new AuthException("auth error");
        }
        RealWorldUser realWorldUser = parseJwtToken(jwtToken);
        if (realWorldUser == null) {
            LOGGER.info("auth failed empty userInfo, authHeader = [{}]", authHeader);
            throw new AuthException("auth error");
        }
        //TODO 再读一次cache,db
        return realWorldUser;
    }

    private RealWorldUser parseJwtToken(String jwtToken) {
        //TODO 用真的jwt
        String key = getSessionKey(jwtToken);
        String userJson = redisTemplate.opsForValue().get(key);
        if (StringUtil.isNullOrEmpty(userJson)) {
            LOGGER.info("parseJwtToken error, empty userJson, key = [{}]", key);
            return null;
        }
        try {
            RealWorldUser realWorldUser = gson.fromJson(userJson, RealWorldUser.class);
            return realWorldUser;
        } catch (Exception e) {
            LOGGER.error("parseJwtToken error, e = [{}]", e.getMessage(), e);
            return null;
        }
    }

    @Override
    public UserLoginResponseResult getUserInfo(RealWorldUser currentUser, String currentUserJwt) {
        UserLoginResponseDto userLoginResponseDto = convertRealWorldUserToUserDto(currentUser, currentUserJwt);
        return convertUserToLoginResponse(userLoginResponseDto);
    }

    private String getSessionKey(String jwtToken) {
        String key = String.format(USER_SESSION_KEY_PATTERN, jwtToken);
        return key;
    }

    public UserLoginResponseResult login(UserLoginRequestPayload payload) {
        checkUserLoginRequestPayload(payload);
        UserLoginRequestDto userLoginDto = convertPayloadToRequestDto(payload);
        UserLoginResponseDto userLoginResponseDto = doLogin(userLoginDto);
        return convertUserToLoginResponse(userLoginResponseDto);
    }

    private void checkUserLoginRequestPayload(UserLoginRequestPayload payload) {
        ValueUtils.nonNull(payload.getUser(), "empty payload.user");;
        UserLoginRequestDto user = payload.getUser();
        ValueUtils.notEmpty(user.getEmail(), "empty user.email");
        ValueUtils.notEmpty(user.getPassword(), "empty user.password");
    }

    private UserLoginRequestDto convertPayloadToRequestDto(UserLoginRequestPayload payload) {
        return payload.getUser();
    }

    private UserLoginResponseDto doLogin(UserLoginRequestDto userLoginDto) {
        String loginEmail = userLoginDto.getEmail();
        String password = userLoginDto.getPassword();
        String passwordHash = DigestUtils.md5DigestAsHex(password.getBytes());
        RealWorldUser user = bizUserService.query(loginEmail, passwordHash);
        if (user == null) {
            throw new BizException("email or password error");
        }
        String jwtToken = genJwtToken(user);
        LOGGER.info("login succ, user = [{}]", user);
        UserLoginResponseDto userLoginResponseDto = convertRealWorldUserToUserDto(user, jwtToken);
        return userLoginResponseDto;
    }

    private String genJwtToken(RealWorldUser user) {
        //TODO 用真的jwt
        String jwtToken = RandomString.make(5);
        String key = getSessionKey(jwtToken);
        redisTemplate.opsForValue().set(key, gson.toJson(user), 1, TimeUnit.DAYS);
        return jwtToken;
    }

    private UserLoginResponseDto convertRealWorldUserToUserDto(RealWorldUser user, String jwtToken) {
        UserLoginResponseDto userDto = new UserLoginResponseDto();
        String email = user.getEmail();
        userDto.setEmail(email);
        userDto.setBio(email);
        userDto.setUsername(email);
        userDto.setToken(jwtToken);
        userDto.setImage(Constants.IMG_MOCK);
        return userDto;
    }

    private UserLoginResponseResult convertUserToLoginResponse(UserLoginResponseDto userDto) {
        UserLoginResponseResult result = new UserLoginResponseResult();
        result.setUser(userDto);
        return result;
    }
}

