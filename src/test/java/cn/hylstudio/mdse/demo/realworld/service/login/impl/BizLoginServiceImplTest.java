package cn.hylstudio.mdse.demo.realworld.service.login.impl;

import cn.hylstudio.mdse.demo.realworld.Constants;
import cn.hylstudio.mdse.demo.realworld.RealworldApplicationTests;
import cn.hylstudio.mdse.demo.realworld.entity.mysql.RealWorldUser;
import cn.hylstudio.mdse.demo.realworld.model.request.user.UserLoginRequestDto;
import cn.hylstudio.mdse.demo.realworld.model.request.user.UserLoginRequestPayload;
import cn.hylstudio.mdse.demo.realworld.model.response.user.UserLoginResponseDto;
import cn.hylstudio.mdse.demo.realworld.model.response.user.UserLoginResponseResult;
import cn.hylstudio.mdse.demo.realworld.service.login.IBizLoginService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

class BizLoginServiceImplTest extends RealworldApplicationTests {
    @Autowired
    private IBizLoginService bizLoginService;

    @Test
    void login() {
        UserLoginRequestPayload payload = new UserLoginRequestPayload();
        UserLoginRequestDto user = new UserLoginRequestDto();
        user.setEmail("aaa@abc.com");
        user.setPassword("bbb");//08f8e0260c64418510cefb2b06eee5cd
        payload.setUser(user);
        UserLoginResponseResult result = bizLoginService.login(payload);
        LOGGER.info("result = [{}]", result);
        Assert.notNull(result, "response result must not null");
        Assert.notNull(result.getUser(), "response result user field must not null");
        UserLoginResponseDto responseUserDto = result.getUser();
        Assert.notNull(responseUserDto.getEmail(), "response result responseUserDto.email field must not null");
        Assert.isTrue("aaa@abc.com".equals(responseUserDto.getEmail()), "response result responseUserDto.email should aaa@abc.com");
        String token = responseUserDto.getToken();
        Assert.hasText(token, "response result responseUserDto.token shoud has text");
        //jwt check
        RealWorldUser currentUser = bizLoginService.getCurrentUserFromHeader(Constants.JWT_HEADER_PREFIX + token);
        Assert.notNull(currentUser, "currentUser must not null");
        Assert.isTrue("aaa@abc.com".equals(currentUser.getEmail()), "currentUser must be aaa@abc.com");

    }

}