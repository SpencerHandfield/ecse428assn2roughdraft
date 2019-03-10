package StepDefinition;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.en.And;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.net.MalformedURLException;

public class Steps {

    WebDriver driver;

    //defining values
    String email = "imagesofcucumbers@gmail.com";
    String email_password = "fourtwoeight";



    /*==================================Login, compose===============================================*/

    @Given("^I am logged into a gmail account$")
    public void given_i_am_logged_in() throws Throwable
    {
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://mail.google.com/mail/u/0/#inbox");
        logIn();
    }

    @And("^I click the compose button$")
    public void click_the_compose_button() throws Throwable
    {
        clickCompose();

    }

    @Then("^I enter a recipient email and subject$")
    public void enter_recipient_email_and_subject() throws Throwable
    {
        Thread.sleep(2000);
        addContents();

    }

    @Then("^I attach an image file$")
    public void attach_image() throws Throwable
    {
       attachImage();
       Thread.sleep(5000); //wait for image to upload
    }

    @Then("^The image will be attached$")
    public void check_if_image_attached() throws Throwable
    {
        Assert.assertTrue(checkIfImageAttached());
        if(checkIfImageAttached())
            System.out.println("There is an image attached");
    }

    @Then("^I click send with the image attached$")
    public void send_the_email() throws Throwable
    {
        sendEmail();
    }


    /*=======================HELPER FUNCTIONS==============================*/

    //logging in automatically

    public void logIn()
    {
        //find the email field and send the address to it
        //set up a waiting time, then find the next button and click it
        driver.findElement(By.className("whsOnd")).sendKeys(email);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement next = wait.until(ExpectedConditions.elementToBeClickable(By.className("qhFLie")));
        next.click();

        //wait to ensure page loads properly
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //repeat above process but with the password
        //find the next button after waiting and click
        driver.findElement(By.className("whsOnd")).sendKeys(email_password);
        wait = new WebDriverWait(driver, 10);
        next = wait.until(ExpectedConditions.elementToBeClickable(By.className("qhFLie")));
        next.click();
    }

    public boolean checkIfImageAttached()
    {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement existingAttachment = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("vI")));
        if (existingAttachment.getText().equals(""))
        {
            return false; //there is no attachment
        }else { //there is an attachment
            return true;
        }
    }

    public void clickCompose()
    {
        //wait until find the compose button, then click it
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement compose = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("z0")));
        compose.click();
    }
    public void addContents()
    {
        //send the email to the email (just use the same one)
        driver.findElement(By.className("vO")).sendKeys(email);
        driver.findElement(By.className("aoT")).sendKeys("testsubject");
        //driver.findElement(By.className("???")).sendKeys("testbody");
    }
    public void attachImage(){
        //wait until find the attach button, then click it
        String imageLocation = System.getProperty("user.dir") + "/Images/lion.jpg";
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys(imageLocation);  // Upload image
        System.out.println("Uploading a file.. ");

    }

    public void sendEmail(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement send = wait.until(ExpectedConditions.elementToBeClickable(By.className("gU")));
        send.click(); // Click send button
    }

}