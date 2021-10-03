package cn.hylstudio.mdse.demo.realworld.model.request.user;

import cn.hylstudio.mdse.demo.realworld.model.request.RequestPayload;

public class UserLoginRequestPayload extends RequestPayload {
	private UserLoginRequestDto user;

	public UserLoginRequestPayload(){
	}

	public UserLoginRequestDto getUser() {
	    return user;
	}
	
	public void setUser(UserLoginRequestDto user) {
	    this.user = user;
	}

}
