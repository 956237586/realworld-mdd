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
		//email check begin
		ValueUtils.notEmpty(user.getEmail(), "empty userLogin.user.email");
		//password check begin
		ValueUtils.notEmpty(user.getPassword(), "empty userLogin.user.password");
		ValueUtils.checkLength(user.getPassword(), 8L, 16L, "userLogin.user.password length error");
		ValueUtils.checkRegex(user.getPassword(), ".+", "userLogin.user.password regex check error");
	}

	public UserLoginRequestDto getUser() {
	    return user;
	}

	public void setUser(UserLoginRequestDto user) {
	    this.user = user;
	}

}
