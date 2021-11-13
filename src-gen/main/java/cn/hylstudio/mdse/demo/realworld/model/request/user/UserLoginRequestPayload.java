package cn.hylstudio.mdse.demo.realworld.model.request.user;

import cn.hylstudio.mdse.demo.realworld.model.request.RequestPayload;
import cn.hylstudio.mdse.demo.realworld.util.ValueUtils;

public class UserLoginRequestPayload extends RequestPayload {
	private UserLoginRequestDto user;

	public UserLoginRequestPayload(){
	}

	public void checkUserLoginRequestPayload(UserLoginRequestPayload userLogin) {
		ValueUtils.nonNull(userLogin.getUser(), "empty userLogin.user");
		
		UserLoginRequestDto user = userLogin.getUser();
		ValueUtils.notEmpty(user.getPassword(), "empty userLogin.user.password");
		ValueUtils.notEmpty(user.getEmail(), "empty userLogin.user.email");
	}

	public UserLoginRequestDto getUser() {
	    return user;
	}

	public void setUser(UserLoginRequestDto user) {
	    this.user = user;
	}

}
