package cn.hylstudio.mdse.demo.realworld.controller.user;

import cn.hylstudio.mdse.demo.realworld.ContextHolder;
import cn.hylstudio.mdse.demo.realworld.controller.BaseController;
import cn.hylstudio.mdse.demo.realworld.entity.mysql.RealWorldUser;
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
@RequestMapping("/api/user")
public class UserController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private IBizLoginService loginService;

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public UserLoginResponseResult getCurrentUser() {
        RealWorldUser currentUser = ContextHolder.getCurrentUser();
        String currentUserJwt = ContextHolder.getCurrentUserJwt();
        LOGGER.info("getCurrentUser, currentUserJwt = [{}]", currentUserJwt);
        UserLoginResponseResult result = loginService.getUserInfo(currentUser, currentUserJwt);
        return result;
    }
}
