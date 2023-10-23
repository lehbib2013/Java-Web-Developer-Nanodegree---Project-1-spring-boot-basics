package com.udacity.jwdnd.course1.cloudstorage.Pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Home {

    private WebDriver driver;

    private WebDriverWait driverWait;
    @FindBy(id="logout-btn")
    private WebElement logoutButton;
    @FindBy(id="nav-files-tab")
    private WebElement fileTab;
    @FindBy(id="nav-notes-tab")
    private WebElement noteTab;
    @FindBy(id="btnAddNote")
    private WebElement btnaAddNote;
    @FindBy(id="btnEditNote")
    private WebElement btnEditNote;
    @FindBy(id="btnDeleteNote")
    private WebElement btnDeleteNote;
    @FindBy(id="btnNoteSaveSubmit")
    private WebElement btnNoteSaveSubmit;

    @FindBy(id="btnAddCredential")
    private WebElement btnAddCredential;
    private WebElement noteTitle;

    private WebElement noteDescription;
    @FindBy(id="btnNoteSaveSubmit")
    private WebElement btnNoteSubmit;
    @FindBy(id="nav-credentials-tab")
    private WebElement credentialTab;

    private WebElement credentialURL;

    private WebElement credentailUserName;

    private WebElement credentialPassword;

    @FindBy(id="btnCredentialSaveSubmit")
    private WebElement btnCredentialSaveSubmit;
    @FindBy(id="btnEditCredential")
    private WebElement btnEditCredential;
    @FindBy(id="btnDeleteCredential")
    private WebElement btnDeleteCredential;


    public Home(WebDriver webDriver,WebDriverWait webDriverwait) {
      //  WebDriverWait wait = new WebDriverWait(driver, 10);

        this.driver = webDriver;
        this.driverWait = webDriverwait;
        PageFactory.initElements(this.driver, this);
    }

    public void createNote(String title, String description) {
      //  this.noteTab.click();

        this.noteTab.click();
        WebElement btnAddNoteRef = this.driverWait.until(webDriver -> webDriver.findElement(By.id("btnAddNote")));
        this.driverWait.until(ExpectedConditions.visibilityOf(btnAddNoteRef));
        this.btnaAddNote.click();
        // fill the fields

        WebElement noteTitleRef = this.driverWait.until(webDriver -> webDriver.findElement(By.id("note-title")));
        this.driverWait.until(ExpectedConditions.visibilityOf(noteTitleRef));
        noteTitleRef.sendKeys(title);

        WebElement descriptionRef =  this.driverWait.until(webDriver -> webDriver.findElement(By.id("note-description")));
        descriptionRef.sendKeys(description);


        // click the button to save credential
        WebElement btnNoteSaveSubmitRef = this.driverWait.until(webDriver -> webDriver.findElement(By.id("btnNoteSaveSubmit")));
        this.driverWait.until(ExpectedConditions.visibilityOf(btnNoteSaveSubmitRef));
        btnNoteSaveSubmit.click();

    }

    public void createcredential(String credentialUrl,String username,String password) {

        this.credentialTab.click();
        WebElement btnAddCredentialRef = this.driverWait.until(webDriver -> webDriver.findElement(By.id("btnAddCredential")));
        this.driverWait.until(ExpectedConditions.visibilityOf(btnAddCredentialRef));
        this.btnAddCredential.click();
        // fill the fields

        WebElement credentialUrlRef = this.driverWait.until(webDriver -> webDriver.findElement(By.id("credential-url")));
        this.driverWait.until(ExpectedConditions.visibilityOf(credentialUrlRef));
        credentialUrlRef.sendKeys(credentialUrl);

        WebElement userNameRef =  this.driverWait.until(webDriver -> webDriver.findElement(By.id("credential-username")));
        userNameRef.sendKeys(credentialUrl);

        WebElement passwordRef =  this.driverWait.until(webDriver -> webDriver.findElement(By.id("credential-password")));
        passwordRef.sendKeys(credentialUrl);

        // click the button to save credential
        WebElement btnCredentialSaveSubmitRef = this.driverWait.until(webDriver -> webDriver.findElement(By.id("btnCredentialSaveSubmit")));
        this.driverWait.until(ExpectedConditions.visibilityOf(btnCredentialSaveSubmitRef));
        btnCredentialSaveSubmit.click();

    }

}
