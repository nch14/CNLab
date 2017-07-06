package cn.chenhaonee.cnlab.vo;

import java.io.Serializable;

/**
 * Created by chenhaonee on 2017/6/19.
 */

public class Creator implements Serializable {
    private int id;
    private String username;
    private String name;
    private String type;
    private String avatar;
    private String gender;
    private String email;
    private int schoolId;

    public Creator() {
    }

    public Creator(int id, String username, String name, String type, String avatar, String gender, String email, int schoolId) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.type = type;
        this.avatar = avatar;
        this.gender = gender;
        this.email = email;
        this.schoolId = schoolId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }
}