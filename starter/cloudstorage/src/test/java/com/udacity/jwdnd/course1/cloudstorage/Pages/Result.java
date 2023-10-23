package com.udacity.jwdnd.course1.cloudstorage.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Result {
    @FindBy(id="success")
    private WebElement success;

    @FindBy(id="error")
    private WebElement error;

    public Result(WebElement success, WebElement error) {
        this.success = success;
        this.error = error;
    }

    public Result() {
    }

    public WebElement getSuccess() {
        return success;
    }

    public void setSuccess(WebElement success) {
        this.success = success;
    }

    public WebElement getError() {
        return error;
    }

    public void setError(WebElement error) {
        this.error = error;
    }
}
