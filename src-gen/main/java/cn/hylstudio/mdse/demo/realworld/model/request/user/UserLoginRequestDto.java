package cn.hylstudio.mdse.demo.realworld.model.request.user;
public class UserLoginRequestDto {

	private String password;

	private String email;

	public UserLoginRequestDto(){
	}

	public String getPassword() {
	    return password;
	}
	
	public void setPassword(String password) {
	    this.password = password;
	}

	public String getEmail() {
	    return email;
	}
	
	public void setEmail(String email) {
	    this.email = email;
	}


}
