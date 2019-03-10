package StepDefinition;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
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

    @Given("^Open the Firefox and launch the application$")
    public void given_i_am_logged_in() throws Throwable
    {
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://mail.google.com/mail/u/0/#inbox");
        logIn();
    }
//
//    @Given("^Open the Firefox and launch the application$")
//    public void open_the_Firefox_and_launch_the_application() throws Throwable
//    {
//        System.setProperty("webdriver.chrome.driver", "chromedriver");
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
//        driver.get("https://mail.google.com/mail/u/0/#inbox");
//    }

    @When("^Enter the Username and Password$")
    public void enter_the_Username_and_Password() throws Throwable
    {
        driver.findElement(By.name("uid")).sendKeys("username12");
        driver.findElement(By.name("password")).sendKeys("password12");
    }

    @Then("^Reset the credential$")
    public void Reset_the_credential() throws Throwable
    {
        driver.findElement(By.name("btnReset")).click();
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

        //wait for page to load
        try {
            Thread.sleep(3000);
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

}