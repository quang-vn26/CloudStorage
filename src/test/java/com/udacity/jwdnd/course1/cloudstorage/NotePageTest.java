package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NotePageTest {

    @FindBy(id = "nav-notes-tab")
    private WebElement noteTab;

    @FindBy(id = "btn-new-note")
    private WebElement addNoteBtn;

    @FindBy(id = "id-edit-note")
    private WebElement editNoteBtn;
    @FindBy(id = "id-delete-note")
    private WebElement deleteNoteBtn;
    @FindBy(id = "id-submit")
    private WebElement submitNoteBtn;

    @FindBy(id = "note-title")
    private WebElement noteTitle;

    @FindBy(id = "note-description")
    private WebElement noteDescription;

    private WebDriver driver;

    public NotePageTest(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void addNoteTest(String title, String description) throws InterruptedException {
        navigateNote();
        WebDriverWait webDriverWait = new WebDriverWait(driver, 9);
        webDriverWait.until(ExpectedConditions.visibilityOf(this.addNoteBtn));
        this.addNoteBtn.click();
        webDriverWait.until(ExpectedConditions.visibilityOf(this.noteTitle));
        this.noteTitle.sendKeys(title);
        this.noteDescription.sendKeys(description);
        this.submitNoteBtn.click();
    }
    public void editNoteTest(String title, String description){
        navigateNote();
        WebDriverWait webDriverWait = new WebDriverWait(driver, 9);
        webDriverWait.until(ExpectedConditions.visibilityOf(this.editNoteBtn));
        editNoteBtn.click();
        webDriverWait.until(ExpectedConditions.visibilityOf(this.noteTitle));
        this.noteTitle.clear();
        this.noteTitle.sendKeys(title);
        this.noteDescription.clear();
        this.noteDescription.sendKeys(description);
        this.submitNoteBtn.click();

    }
    public void deleteNoteTest(){
        navigateNote();
        WebDriverWait webDriverWait = new WebDriverWait(driver, 9);
        webDriverWait.until(ExpectedConditions.visibilityOf(this.deleteNoteBtn));
        deleteNoteBtn.click();
    }
    public  void navigateNote(){
        this.noteTab.click();
    }





}
