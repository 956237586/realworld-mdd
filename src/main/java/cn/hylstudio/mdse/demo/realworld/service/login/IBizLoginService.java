package cn.hylstudio.mdse.demo.realworld.service.login;

import cn.hylstudio.mdse.demo.realworld.entity.mysql.RealWorldUser;
import cn.hylstudio.mdse.demo.realworld.model.response.user.UserLoginResponsePayload;
import cn.hylstudio.mdse.demo.realworld.model.request.user.UserLoginRequestPayload;

public interface IBizLoginService {

    UserLoginResponsePayload login(UserLoginRequestPayload payload);

    RealWorldUser getCurrentUser(String authHeader);
}
