package cn.chenhaonee.cnlab.vo;

/**
 * Created by chenhaonee on 2017/6/18.
 */

public class User {
    private String username;
    private String name;
    private String type;
    private String avatar;
    private String gender;
    private String email;


    private int gitId;
    private String number;

    private String authority;

    public User(String username, String name, String type, String avatar, String gender, String email, String authority) {
        this.username = username;
        this.name = name;
        this.type = type;
        this.avatar = avatar;
        this.gender = gender;
        this.email = email;
        this.authority = authority;
    }

    public User(String username, String name, String type, String avatar, String gender, String email, int gitId, String number) {
        this.username = username;
        this.name = name;
        this.type = type;
        this.avatar = avatar;
        this.gender = gender;
        this.email = email;
        this.gitId = gitId;
        this.number = number;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getGitId() {
        return gitId;
    }

    public void setGitId(int gitId) {
        this.gitId = gitId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
