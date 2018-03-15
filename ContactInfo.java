package com.tabian.firebasereaddata;

/**
 * Created by swati on 3/15/2018.
 */

public class ContactInfo {
    private String name;
    private Long mobile;

    public ContactInfo(String name, Long mobile) {
        this.name = name;
        this.mobile = mobile;
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
}
