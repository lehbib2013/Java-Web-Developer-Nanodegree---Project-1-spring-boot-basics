package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.Pages.Home;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.io.File;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CloudStorageApplicationTests {

    @LocalServerPort
    private int port;

    private WebDriver driver;
    private WebDriverWait driverWait;

    @BeforeAll
    static void beforeAll() {
        // TO CLEAR WRONG VERSION FROM CACHE .clearDriverCache()
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void beforeEach() {
        this.driver = new ChromeDriver();
        this.driverWait = new WebDriverWait(this.driver, 10);
    }

    @AfterEach
    public void afterEach() {
        if (this.driver != null) {
            driver.quit();
        }
    }

    @Test
    public void getLoginPage() {
        driver.get("http://localhost:" + this.port + "/login");
        Assertions.assertEquals("Login", driver.getTitle());
    }

    /**
     * PLEASE DO NOT DELETE THIS method.
     * Helper method for Udacity-supplied sanity checks.
     **/
    private void doMockSignUp(String firstName, String lastName, String userName, String password) {
        // Create a dummy account for logging in later.

        // Visit the sign-up page.
       // WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
        driver.get("http://localhost:" + this.port + "/signup");
        driverWait.until(ExpectedConditions.titleContains("Sign Up"));

        // Fill out credentials
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputFirstName")));
        WebElement inputFirstName = driver.findElement(By.id("inputFirstName"));
        inputFirstName.click();
        inputFirstName.sendKeys(firstName);

        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputLastName")));
        WebElement inputLastName = driver.findElement(By.id("inputLastName"));
        inputLastName.click();
        inputLastName.sendKeys(lastName);

        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputUsername")));
        WebElement inputUsername = driver.findElement(By.id("inputUsername"));
        inputUsername.click();
        inputUsername.sendKeys(userName);

        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputPassword")));
        WebElement inputPassword = driver.findElement(By.id("inputPassword"));
        inputPassword.click();
        inputPassword.sendKeys(password);

        // Attempt to sign up.
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("buttonSignUp")));
        WebElement buttonSignUp = driver.findElement(By.id("buttonSignUp"));
        buttonSignUp.click();

		/* Check that the sign up was successful.
		// You may have to modify the element "success-msg" and the sign-up
		// success message below depening on the rest of your code.
		*/

        Assertions.assertTrue(driver.findElement(By.id("success-msg")).getText().contains("You successfully signed up! Please continue to the login page."));

    }


    /**
     * PLEASE DO NOT DELETE THIS method.
     * Helper method for Udacity-supplied sanity checks.
     **/
    private void doLogIn(String userName, String password) {
        // Log in to our dummy account.
        driver.get("http://localhost:" + this.port + "/login");
       // WebDriverWait webDriverWait = new WebDriverWait(driver, 2);

        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputUsername")));
        WebElement loginUserName = driver.findElement(By.id("inputUsername"));
        loginUserName.click();
        loginUserName.sendKeys(userName);

        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputPassword")));
        WebElement loginPassword = driver.findElement(By.id("inputPassword"));
        loginPassword.click();
        loginPassword.sendKeys(password);

        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-button")));
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        driverWait.until(ExpectedConditions.titleContains("Home"));

    }

    /**
     * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the
     * rest of your code.
     * This test is provided by Udacity to perform some basic sanity testing of
     * your code to ensure that it meets certain rubric criteria.
     * <p>
     * If this test is failing, please ensure that you are handling redirecting users
     * back to the login page after a succesful sign up.
     * Read more about the requirement in the rubric:
     * https://review.udacity.com/#!/rubrics/2724/view
     */
    @Test
    public void testRedirection() {
       // WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
        // Create a test account
        doMockSignUp("Redirection", "Test", "RT", "123");

        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("link-redirection")));
        WebElement anchorRedirection = driver.findElement(By.id("link-redirection"));
        anchorRedirection.click();

        // Check if we have been redirected to the log in page.
        Assertions.assertEquals("http://localhost:" + this.port + "/login", driver.getCurrentUrl());
    }

    /**
     * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the
     * rest of your code.
     * This test is provided by Udacity to perform some basic sanity testing of
     * your code to ensure that it meets certain rubric criteria.
     * <p>
     * If this test is failing, please ensure that you are handling bad URLs
     * gracefully, for example with a custom error page.
     * <p>
     * Read more about custom error pages at:
     * https://attacomsian.com/blog/spring-boot-custom-error-page#displaying-custom-error-page
     */
    @Test
    public void testBadUrl() {
        // Create a test account
        doMockSignUp("URL", "Test", "UT", "123");
        doLogIn("UT", "123");

        // Try to access a random made-up URL.
        driver.get("http://localhost:" + this.port + "/some-random-page");
        Assertions.assertFalse(driver.getPageSource().contains("Whitelabel Error Page"));
    }


    /**
     * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the
     * rest of your code.
     * This test is provided by Udacity to perform some basic sanity testing of
     * your code to ensure that it meets certain rubric criteria.
     * <p>
     * If this test is failing, please ensure that you are handling uploading large files (>1MB),
     * gracefully in your code.
     * <p>
     * Read more about file size limits here:
     * https://spring.io/guides/gs/uploading-files/ under the "Tuning File Upload Limits" section.
     */
    @Test
    public void testLargeUpload() {
        // Create a test account
        doMockSignUp("Large File", "Test", "LFT", "123");
        doLogIn("LFT", "123");

        // Try to upload an arbitrary large file
       // WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
        String fileName = "upload5m.zip";

        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fileUpload")));
        WebElement fileSelectButton = driver.findElement(By.id("fileUpload"));
        fileSelectButton.sendKeys(new File(fileName).getAbsolutePath());

        WebElement uploadButton = driver.findElement(By.id("uploadButton"));
        uploadButton.click();
        try {
            driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("success")));
        } catch (org.openqa.selenium.TimeoutException e) {
            System.out.println("Large File upload failed");
        }
        Assertions.assertFalse(driver.getPageSource().contains("HTTP Status 403 – Forbidden"));

    }


    // Write a Selenium test that verifies that the home page is not accessible without logging in.
    @Test
    public void homeNotAccessibleForAnonymous(){
        driver.get("http://localhost:" + this.port + "/logout");
        driver.get("http://localhost:" + this.port + "/home");
        Assertions.assertEquals("http://localhost:" + this.port + "/login", driver.getCurrentUrl());

    }

    @Test
    // Write a Selenium test that signs up a new user, logs that user in, verifies that they can access the home page, then logs out and verifies that the home page is no longer accessible.
    public void checkAccessForRegisteredUser() {
        // Write a Selenium test that signs up a new user,
        doMockSignUp("kkkk", "Test", "DDD", "123");
        //logs that user in,
        doLogIn("DDD", "123");
        //verifies that they can access the home page
        Assertions.assertEquals("http://localhost:" + this.port + "/home", driver.getCurrentUrl());
       // then logs out
        driver.get("http://localhost:" + this.port + "/logout");
        // verifies that the home page is no longer accessible.
        Assertions.assertNotEquals("http://localhost:" + this.port + "/home", driver.getCurrentUrl());
    }

   // Write a Selenium test that logs in an existing user, creates a credential and verifies that the credential details are visible in the credential list.
    @Test
    public void testCreateCredential() throws InterruptedException {
        doMockSignUp("create credential", "Test", "NM", "123");
        doLogIn("NM", "123");
        Home home = new Home(driver,driverWait);
        home.createcredential("http://mmm","NM","123");
        //navigate back to credentials tab
        driver.get("http://localhost:" + this.port + "/home");
        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nav-credentials-tab")));
        WebElement refNav= driver.findElement(By.id("nav-credentials-tab"));
        refNav.click();
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.credential-url")));
        WebElement credentialUrl =  driver.findElements(By.cssSelector("span.credential-url")).get(0);
        Assertions.assertEquals("http://mmm", credentialUrl.getText());
    }

    //   // Write a Selenium test that logs in an existing user with existing credentials, clicks the edit credential button on an existing credential, changes the credential data, saves the changes, and verifies that the changes appear in the credential list.

    @Test
    public void testEditCredential() throws InterruptedException {
        // mock Signup process
        doMockSignUp("edit credential", "TestEdit", "NMEdit", "123Edit");
        // login to system
        doLogIn("NMEdit", "123Edit");
        // go to Home Page
        Home home = new Home(driver, driverWait);
        // create credential
        home.createcredential("http://mmm", "NM", "123");
        // go to home
        driver.get("http://localhost:" + this.port + "/home");
        // navigate to credential Tab
        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nav-credentials-tab")));
        WebElement refNav= driver.findElement(By.id("nav-credentials-tab"));
        refNav.click();
        // wait and click on Edit button
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.btnEditCredential")));
        WebElement refBtnEdits= driver.findElements(By.className("btnEditCredential")).get(0);
        refBtnEdits.click();
        // change the value of Url
        driverWait.until(ExpectedConditions.elementToBeClickable((By.id("credential-url"))));
        WebElement refUrlToEdit= driver.findElement(By.cssSelector("input#credential-url"));
        refUrlToEdit.clear();
        refUrlToEdit.sendKeys("http://mmmEdit");
        // click on save button so the changes can take effect
        WebElement btnCredentialSaveSubmitRef = this.driverWait.until(webDriver -> webDriver.findElement(By.id("btnCredentialSaveSubmit")));
        this.driverWait.until(ExpectedConditions.visibilityOf(btnCredentialSaveSubmitRef));
        btnCredentialSaveSubmitRef.click();
        // go to Home
        driver.get("http://localhost:" + this.port + "/home");
        // go to credential Tab
        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nav-credentials-tab")));
        WebElement refCredentialTab= driver.findElement(By.id("nav-credentials-tab"));
        refCredentialTab.click();
        // read the values of credential url td values from the html table
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.credential-url")));
        List<WebElement> credentialUrls=  driver.findElements(By.cssSelector("span.credential-url"));
        // iterate all the values to check if edited value exist
        boolean testPass=false;
        for(WebElement tt:credentialUrls) {
            if(tt.getText().contains("http://mmmEdit")) {
                testPass = true;
            }
        }
        // assert
        Assertions.assertEquals(true, testPass);

    }

    @Test
    public void testDeleteCredential() throws InterruptedException {
        // mock Signup process
        doMockSignUp("edit credential", "TestDelete", "NMDelete", "123Delete");
        // login to system
        doLogIn("NMDelete", "123Delete");
        // go to Home Page
        Home home = new Home(driver, driverWait);
        // create credential
        home.createcredential("http://mmm", "NM", "123");
        // go to home
        driver.get("http://localhost:" + this.port + "/home");
        // navigate to credential Tab
        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nav-credentials-tab")));
        WebElement refNav= driver.findElement(By.id("nav-credentials-tab"));
        refNav.click();
        // wait and click on delete button
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.btnDeleteCredential")));
        WebElement refBtnDelete= driver.findElements(By.className("btnDeleteCredential")).get(0);
        refBtnDelete.click();

        // go to Home
        driver.get("http://localhost:" + this.port + "/home");
        // go to credential Tab
        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nav-credentials-tab")));
        WebElement refCredentialTab= driver.findElement(By.id("nav-credentials-tab"));
        refCredentialTab.click();
        // read the values of credential url td values from the html table
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credential-table")));
        List<WebElement> credentialUrls=  driver.findElements(By.cssSelector("span.credential-url"));
        // iterate all the values to check if edited value exist
        boolean testPass=true;
        for(WebElement tt:credentialUrls) {
            if(tt.getText().contains("http://mmmDelete")) {
                testPass = false;
            }
        }
        // assert
        Assertions.assertEquals(true, testPass);

    }

    @Test
    public void testCreateNote() throws InterruptedException {
        doMockSignUp("create note", "Test", "testuser", "123");
        doLogIn("testuser", "123");
        Home home = new Home(driver,driverWait);
        home.createNote("title","description");
        //navigate back to credentials tab
        driver.get("http://localhost:" + this.port + "/home");
        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nav-notes-tab")));
        WebElement refNav= driver.findElement(By.id("nav-notes-tab"));
        refNav.click();
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.note-title")));
        WebElement noteTitle =  driver.findElements(By.cssSelector("span.note-title")).get(0);
        Assertions.assertEquals("title", noteTitle.getText());
    }

    @Test
    public void testEditNote() throws InterruptedException {
        // mock Signup process
        doMockSignUp("edit note", "TestEditNote", "NMEditNote", "123EditNote");
        // login to system
        doLogIn("NMEditNote", "123EditNote");
        // go to Home Page
        Home home = new Home(driver, driverWait);
        // create credential
        home.createNote("title","description");
        // go to home
        driver.get("http://localhost:" + this.port + "/home");
        // navigate to credential Tab
        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nav-notes-tab")));
        WebElement refNav= driver.findElement(By.id("nav-notes-tab"));
        refNav.click();
        // wait and click on Edit button
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.btnEditNote")));
        WebElement refBtnEdits= driver.findElements(By.className("btnEditNote")).get(0);
        refBtnEdits.click();
        // change the value of Url
        driverWait.until(ExpectedConditions.elementToBeClickable((By.id("note-title"))));
        WebElement refTitleToEdit= driver.findElement(By.cssSelector("input#note-title"));
        refTitleToEdit.clear();
        refTitleToEdit.sendKeys("titleEdit");
        // click on save button so the changes can take effect
        WebElement btnNoteSaveSubmitRef = this.driverWait.until(webDriver -> webDriver.findElement(By.id("btnNoteSaveSubmit")));
        this.driverWait.until(ExpectedConditions.visibilityOf(btnNoteSaveSubmitRef));
        btnNoteSaveSubmitRef.click();
        // go to Home
        driver.get("http://localhost:" + this.port + "/home");
        // go to note Tab
        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nav-notes-tab")));
        WebElement refNotesTab= driver.findElement(By.id("nav-notes-tab"));
        refNotesTab.click();
        // read the values of credential url td values from the html table
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.note-title")));
        List<WebElement> noteTitles=  driver.findElements(By.cssSelector("span.note-title"));
        // iterate all the values to check if edited value exist
        boolean testPass=false;
        for(WebElement note:noteTitles) {
            if(note.getText().contains("titleEdit")) {
                testPass = true;
            }
        }
        // assert
        Assertions.assertEquals(true, testPass);

    }


    @Test
    public void testDeleteNote() throws InterruptedException {
        // mock Signup process
        doMockSignUp("edit note", "TestnoteDelete", "NMnoteDelete", "123noteDelete");
        // login to system
        doLogIn("NMnoteDelete", "123noteDelete");
        // go to Home Page
        Home home = new Home(driver, driverWait);
        // create credential
        home.createNote("titleDelete","descriptionDelee");
        // go to home
        driver.get("http://localhost:" + this.port + "/home");
        // navigate to credential Tab
        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nav-credentials-tab")));
        WebElement refNav= driver.findElement(By.id("nav-notes-tab"));
        refNav.click();
        // wait and click on delete button
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.btnDeleteNote")));
        WebElement refBtnDelete= driver.findElements(By.className("btnDeleteNote")).get(0);
        refBtnDelete.click();

        // go to Home
        driver.get("http://localhost:" + this.port + "/home");
        // go to note Tab
        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("nav-notes-tab")));
        WebElement refNotesTab= driver.findElement(By.id("nav-notes-tab"));
        refNotesTab.click();
        // read the values of credential url td values from the html table
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("note-table")));
        List<WebElement> credentialUrls=  driver.findElements(By.cssSelector("span.note-title"));
        // iterate all the values to check if edited value exist
        boolean testPass=true;
        for(WebElement nte:credentialUrls) {
            if(nte.getText().contains("titleDelete")) {
                testPass = false;
            }
        }
        // assert
        Assertions.assertEquals(true, testPass);

    }
}
