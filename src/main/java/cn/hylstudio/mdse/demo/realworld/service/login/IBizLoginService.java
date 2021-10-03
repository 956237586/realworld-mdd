package cn.hylstudio.mdse.demo.realworld.service.login;

import cn.hylstudio.mdse.demo.realworld.entity.mysql.RealWorldUser;
import cn.hylstudio.mdse.demo.realworld.model.request.user.UserLoginRequestPayload;
import cn.hylstudio.mdse.demo.realworld.model.response.user.UserLoginResponseResult;

public interface IBizLoginService {

    UserLoginResponseResult login(UserLoginRequestPayload payload);

    RealWorldUser getCurrentUser(String authHeader);
}
