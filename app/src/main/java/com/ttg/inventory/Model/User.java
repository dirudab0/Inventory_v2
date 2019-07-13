package com.ttg.inventory.Model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class User{
    private String uid;
    private String fullname;
    private String username;
    private String password;
    private Date sessionExpiryDate;

    public User(){}

    public User(String uid, String fullname, String username, String password) {
        this.uid = uid;
        this.fullname = fullname;
        this.username = username;
        this.password = password;
    }

    public User(String fullname, String username, String password) {
        this.fullname = fullname;
        this.username = username;
        this.password = password;
    }

    public String getUid() {return uid;}

    public void setUid(String uid) {this.uid = uid;}

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getSessionExpiryDate() {
        return sessionExpiryDate;
    }

    public void setSessionExpiryDate(Date sessionExpiryDate) {
        this.sessionExpiryDate = sessionExpiryDate;
    }







    public Map<String, Object> toMap() {

        HashMap<String, Object> result = new HashMap<>();
        result.put("fullName", this.fullname);
        result.put("username", this.username);
        result.put("password", this.password);

        return result;
    }


}
