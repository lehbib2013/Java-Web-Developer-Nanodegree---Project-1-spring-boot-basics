package com.udacity.jwdnd.course1.cloudstorage.model;

public class Credential {
    private Integer credentialId;
    private String url;
    private String username;
    private String key;
   private String  password;
   private Integer userid;
    private String  shownPassword;

    public Credential(Integer credentialId, String url, String username, String key, String password, Integer userid) {
        this.credentialId = credentialId;
        this.url = url;
        this.username = username;
        this.key = key;
        this.password = password;
        this.userid = userid;
 }

    public Credential() {
    }

    public Credential(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public Credential(String url, String username, String password, Integer userid) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.userid = userid;
    }

    public Credential(String url, String username, String key, String password, Integer userid, String shownPassword) {
        this.url = url;
        this.username = username;
        this.key = key;
        this.password = password;
        this.userid = userid;
        this.shownPassword = shownPassword;
    }

    public Credential(Integer credentialId, String url, String username, String key, String password, Integer userid, String shownPassword) {
        this.credentialId = credentialId;
        this.url = url;
        this.username = username;
        this.key = key;
        this.password = password;
        this.userid = userid;
        this.shownPassword = shownPassword;
    }

    public Credential(String url, String username, String key, String password, Integer userid) {
        this.url = url;
        this.username = username;
        this.key = key;
        this.password = password;
        this.userid = userid;
    }

    public Integer getCredentialId() {
        return credentialId;
    }

    public String getShownPassword() {
        return shownPassword;
    }

    public void setShownPassword(String shownPassword) {
        this.shownPassword = shownPassword;
    }

    public void setCredentialId(Integer credentialId) {
        this.credentialId = credentialId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }



}
