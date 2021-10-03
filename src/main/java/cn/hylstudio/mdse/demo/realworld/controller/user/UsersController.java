package cn.hylstudio.mdse.demo.realworld.controller.user;

import cn.hylstudio.mdse.demo.realworld.controller.BaseController;
import cn.hylstudio.mdse.demo.realworld.model.request.user.UserLoginRequestPayload;
import cn.hylstudio.mdse.demo.realworld.model.response.user.UserLoginResponseResult;
import cn.hylstudio.mdse.demo.realworld.service.login.IBizLoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UsersController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UsersController.class);
    @Autowired
    private IBizLoginService loginService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public UserLoginResponseResult login(@RequestBody UserLoginRequestPayload payload) {
        LOGGER.info("login, payload = [{}]", payload);
        UserLoginResponseResult result = loginService.login(payload);
        return result;
    }
}
