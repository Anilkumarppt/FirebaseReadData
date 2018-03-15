package com.tabian.firebasereaddata;

/**
 * Created by User on 2/8/2017.
 */

public class UserInformation {

    private String name;
    private String email;
    private Long mobile;

    public UserInformation(){

    }
    public UserInformation(String name,String email,Long mobile){
        this.name=name;
        this.email=email;
        this.mobile=mobile;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getMobile() {
        return mobile;
    }

    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "UserInformation{" +
                "mobile=" + mobile +
                '}';
    }
}
