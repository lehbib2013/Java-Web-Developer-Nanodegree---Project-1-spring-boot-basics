package com.udacity.jwdnd.course1.cloudstorage.model;

public class User {
    private Integer userId;
    private String userName;

   private  String salt;
   private String password;
   private String lastName;
   private String firstName;

    public User(String userName, String salt, String password, String lastName, String firstname) {

        this.userName = userName;
        this.salt = salt;
        this.password = password;
        this.lastName = lastName;
        this.firstName = firstname;
    }

    public User() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userid) {
        this.userId = userid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSalt() {
        return salt;
    }

    public void setSlat(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstname) {
        this.firstName = firstname;
    }
}
