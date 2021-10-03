package cn.hylstudio.mdse.demo.realworld.model.response.user;
public class UserLoginResponseDto {

	private String username;

	private String bio;

	private String email;

	private String image;

	private String token;

	public UserLoginResponseDto(){
	}

	public String getUsername() {
	    return username;
	}
	
	public void setUsername(String username) {
	    this.username = username;
	}

	public String getBio() {
	    return bio;
	}
	
	public void setBio(String bio) {
	    this.bio = bio;
	}

	public String getEmail() {
	    return email;
	}
	
	public void setEmail(String email) {
	    this.email = email;
	}

	public String getImage() {
	    return image;
	}
	
	public void setImage(String image) {
	    this.image = image;
	}

	public String getToken() {
	    return token;
	}
	
	public void setToken(String token) {
	    this.token = token;
	}


}
