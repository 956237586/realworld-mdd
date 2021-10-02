package cn.hylstudio.mdse.demo.realworld.model.request.user;

public class UserLoginRequestPayload {
    private UserLoginRequestDto user;

    public UserLoginRequestPayload() {
    }

    public UserLoginRequestPayload(UserLoginRequestDto user) {
        this.user = user;
    }

    public UserLoginRequestDto getUser() {
        return user;
    }

    public void setUser(UserLoginRequestDto user) {
        this.user = user;
    }
}
