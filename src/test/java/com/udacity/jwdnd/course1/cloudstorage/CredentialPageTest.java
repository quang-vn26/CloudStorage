package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CredentialPageTest {
    @FindBy(id = "nav-credentials-tab")
    private WebElement credentialTab;

    @FindBy(id = "btn-new-credential")
    private WebElement newCredentialBtn;

    @FindBy(id = "id-edit-credential")
    private WebElement editCredentialBtn;

    @FindBy(id = "id-delete-credential")
    private WebElement deleteCredentialBtn;

    @FindBy(id = "id-btn-credential")
    private WebElement credentialSubmit;
    @FindBy(id = "credential-url")
    private WebElement urlCredential;

    @FindBy(id = "credential-username")
    private WebElement credentialUsername;

    @FindBy(id = "credential-password")
    private WebElement credentialPassword;
    private WebDriver driver;
    public CredentialPageTest(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void navigateCredentialTab(){
        credentialTab.click();
    }
    public void addCredential(String urlCredential, String credentialUsername, String credentialPassword) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 9);
        navigateCredentialTab();
        wait.until(ExpectedConditions.elementToBeClickable(this.newCredentialBtn));
        this.newCredentialBtn.click();
        wait.until(ExpectedConditions.visibilityOf(this.urlCredential));
        this.urlCredential.sendKeys(urlCredential);
        this.credentialUsername.sendKeys(credentialUsername);
        this.credentialPassword.sendKeys(credentialPassword);
        wait.until(ExpectedConditions.elementToBeClickable(this.credentialSubmit));
        this.credentialSubmit.click();
    }
    public void viewCredential(String urlCredential, String credentialUsername, String credentialPassword) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 9);
        navigateCredentialTab();
        wait.until(ExpectedConditions.elementToBeClickable(this.editCredentialBtn));
        this.editCredentialBtn.click();
    }


    public void editCredential(String urlCredential, String credentialusername, String credentialpassword) throws InterruptedException {
        this.urlCredential.clear();
        this.urlCredential.sendKeys(urlCredential);
        credentialUsername.clear();
        credentialUsername.sendKeys(credentialusername);
        credentialPassword.clear();
        credentialPassword.sendKeys(credentialpassword);
        this.credentialSubmit.click();
    }
    public void deleteCredential(){
        navigateCredentialTab();
        WebDriverWait webDriverWait = new WebDriverWait(driver, 9);
        webDriverWait.until(ExpectedConditions.visibilityOf(this.deleteCredentialBtn));
        deleteCredentialBtn.click();
    }

}
