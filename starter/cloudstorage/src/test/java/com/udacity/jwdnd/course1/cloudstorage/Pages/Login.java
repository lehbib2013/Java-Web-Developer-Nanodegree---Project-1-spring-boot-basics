package com.udacity.jwdnd.course1.cloudstorage.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login {
    @FindBy(id="username")
    private WebElement username;
    @FindBy(id="password")
    private WebElement password;

    @FindBy(id="login-button")
    private WebElement loginbutton;
    @FindBy(id="logout-msg")
    private WebElement logoutmsg;

    @FindBy(id="success-msg")
    private WebElement successmsg;


    public Login(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }

    public WebElement getUsername() {
        return username;
    }

    public WebElement getPassword() {
        return password;
    }


    public WebElement getLoginbutton() {
        return loginbutton;
    }


    public WebElement getSuccessmsg() {
        return successmsg;
    }

    public WebElement getLogoutmsg() {
        return logoutmsg;
    }

    public void clickLogin(){
        loginbutton.click();
    }

  }
