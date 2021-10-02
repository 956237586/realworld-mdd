package cn.hylstudio.mdse.demo.realworld.service.login.impl;

import cn.hylstudio.mdse.demo.realworld.controller.user.UsersController;
import cn.hylstudio.mdse.demo.realworld.entity.mysql.RealWorldUser;
import cn.hylstudio.mdse.demo.realworld.exception.BizException;
import cn.hylstudio.mdse.demo.realworld.model.request.user.UserLoginRequestDto;
import cn.hylstudio.mdse.demo.realworld.model.response.user.UserLoginResponsePayload;
import cn.hylstudio.mdse.demo.realworld.model.response.user.UserLoginResponseDto;
import cn.hylstudio.mdse.demo.realworld.model.request.user.UserLoginRequestPayload;
import cn.hylstudio.mdse.demo.realworld.service.login.IBizLoginService;
import io.netty.util.internal.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class BizLoginServiceImpl implements IBizLoginService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UsersController.class);

    public RealWorldUser getCurrentUser(String authHeader) {
        RealWorldUser realWorldUser = new RealWorldUser(authHeader, authHeader);
        return realWorldUser;
    }

    public UserLoginResponsePayload login(UserLoginRequestPayload payload) {
        checkLoginRequestPayload(payload);
        UserLoginRequestDto userLoginDto = convertPayloadToRequestDto(payload);
        RealWorldUser user = doLogin(userLoginDto);
        LOGGER.info("login succ, user = [{}]", user);
        UserLoginResponseDto userLoginResponseDto = convertRealWorldUserToUserDto(user);
        return convertUserToLoginResponse(userLoginResponseDto);
    }

    private void checkLoginRequestPayload(UserLoginRequestPayload payload) {
        if (payload.getUser() == null) {
            throw new BizException("empty user");
        }
        UserLoginRequestDto user = payload.getUser();
        if (StringUtil.isNullOrEmpty(user.getEmail())) {
            throw new BizException("empty email");
        }
        if (StringUtil.isNullOrEmpty(user.getPassword())) {
            throw new BizException("empty password");
        }
    }

    private UserLoginRequestDto convertPayloadToRequestDto(UserLoginRequestPayload payload) {
        return payload.getUser();
    }

    private RealWorldUser doLogin(UserLoginRequestDto userLoginDto) {
        String loginEmail = userLoginDto.getEmail();
        String password = userLoginDto.getPassword();
        //TODO login process
        return new RealWorldUser(loginEmail, password);
    }

    private UserLoginResponseDto convertRealWorldUserToUserDto(RealWorldUser user) {
        UserLoginResponseDto userDto = new UserLoginResponseDto();
        String email = user.getEmail();
        userDto.setEmail(email);
        userDto.setBio(email);
        userDto.setUsername(email);
        userDto.setToken(email);
        userDto.setImage("http://xxx");
        return userDto;
    }

    private UserLoginResponsePayload convertUserToLoginResponse(UserLoginResponseDto userDto) {
        UserLoginResponsePayload result = new UserLoginResponsePayload();
        result.setUser(userDto);
        return result;
    }
}
