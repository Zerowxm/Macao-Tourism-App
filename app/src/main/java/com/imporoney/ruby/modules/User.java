package com.imporoney.ruby.modules;

/**
 * Created by Zero on 2/20/2016.
 */
public class User {
    String phone;
    String password;
    String email;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public User(String phone, String password) {
        this.phone = phone;
        this.password = password;
    }

    public User(String phone, String password, String email) {
        this.phone = phone;
        this.password = password;
        this.email = email;
    }
}
