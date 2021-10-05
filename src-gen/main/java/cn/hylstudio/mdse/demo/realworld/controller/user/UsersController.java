package cn.hylstudio.mdse.demo.realworld.controller.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cn.hylstudio.mdse.demo.realworld.controller.BaseController;
import cn.hylstudio.mdse.demo.realworld.service.login.IBizLoginService;
import cn.hylstudio.mdse.demo.realworld.model.request.user.UserLoginRequestPayload;
import cn.hylstudio.mdse.demo.realworld.model.response.user.UserLoginResponseResult; 

@RestController
@RequestMapping({"/api/users"})
public class UsersController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UsersController.class);
    @Autowired
    private IBizLoginService loginService;
	@RequestMapping(value = {"/login"}, method = {RequestMethod.POST})
	public UserLoginResponseResult login(@RequestBody UserLoginRequestPayload userLogin) {
	    LOGGER.info("login, userLogin = [{}]", userLogin);
	    UserLoginResponseResult result = loginService.login(userLogin);
	    return result;
	}
}
