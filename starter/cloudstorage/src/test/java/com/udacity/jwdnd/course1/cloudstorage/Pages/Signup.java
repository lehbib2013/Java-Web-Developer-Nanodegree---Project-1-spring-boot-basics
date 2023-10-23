package com.udacity.jwdnd.course1.cloudstorage.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Signup {
    @FindBy(id="firstname")
    private WebElement firstName;
    @FindBy(id="lastName")
    private WebElement lastName;

    @FindBy(id="userName")
    private WebElement userName;

    @FindBy(id="userPassword")
    private WebElement userPassword;

    @FindBy(id="signupBtn")
    private WebElement signupBtn;
    @FindBy(id="successMsg")
    private WebElement successMsg;
    @FindBy(id="errorDuplicate")
    private WebElement errorDuplicate;

    public Signup() {
    }

    public Signup(WebElement firstName, WebElement lastName, WebElement userName, WebElement userPassword, WebElement signupBtn, WebElement successMsg, WebElement errorDuplicate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.userPassword = userPassword;
        this.signupBtn = signupBtn;
        this.successMsg = successMsg;
        this.errorDuplicate = errorDuplicate;
    }

    public WebElement getFirstName() {
        return firstName;
    }

    public void setFirstName(WebElement firstName) {
        this.firstName = firstName;
    }

    public WebElement getLastName() {
        return lastName;
    }

    public void setLastName(WebElement lastName) {
        this.lastName = lastName;
    }

    public WebElement getUserName() {
        return userName;
    }

    public void setUserName(WebElement userName) {
        this.userName = userName;
    }

    public WebElement getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(WebElement userPassword) {
        this.userPassword = userPassword;
    }

    public WebElement getSignupBtn() {
        return signupBtn;
    }

    public void setSignupBtn(WebElement signupBtn) {
        this.signupBtn = signupBtn;
    }

    public WebElement getSuccessMsg() {
        return successMsg;
    }

    public void setSuccessMsg(WebElement successMsg) {
        this.successMsg = successMsg;
    }

    public WebElement getErrorDuplicate() {
        return errorDuplicate;
    }

    public void setErrorDuplicate(WebElement errorDuplicate) {
        this.errorDuplicate = errorDuplicate;
    }
}
