package cn.hylstudio.mdse.demo.realworld.model.response.user;

public class UserLoginResponseDto {
    private String email;
    private String token;
    private String username;
    private String bio;
    private String image;

    public UserLoginResponseDto(){
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
//"email": "jake@jake.jake",
//"token": "jwt.token.here",
//"username": "jake",
//"bio": "I work at statefarm",
//"image": null
}
