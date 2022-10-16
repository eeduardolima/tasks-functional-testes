package br.com.wcaquino.functional;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class TasksTest {

    public WebDriver acessarAplicacao() throws MalformedURLException {
        ChromeOptions chromeOptions = new ChromeOptions();
        WebDriver driver = new RemoteWebDriver(new URL("http://192.168.1.17:4444"), chromeOptions);
        driver.navigate().to("http://localhost:8001/tasks/");
        return driver;
    }

    @Test
    public void deveSalvarTarefaComSucesso() throws InterruptedException, MalformedURLException {
        WebDriver driver = acessarAplicacao();
        driver.findElement(By.id("addTodo")).click();
        driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
        driver.findElement(By.id("dueDate")).sendKeys("20/10/2022");
        driver.findElement(By.id("saveButton")).click();
        Assert.assertEquals(driver.findElement(By.id("message")).getText(),"Success!");
        driver.quit();
    }

    @Test
    public void naoDeveSalvarTarefaSemDescricao() throws InterruptedException, MalformedURLException {
        WebDriver driver = acessarAplicacao();
        driver.findElement(By.id("addTodo")).click();
        driver.findElement(By.id("dueDate")).sendKeys("20/10/2022");
        driver.findElement(By.id("saveButton")).click();
        Assert.assertEquals(driver.findElement(By.id("message")).getText(),"Fill the task description");
        driver.quit();
    }

    @Test
    public void naoDeveSalvarTarefaSemData() throws InterruptedException, MalformedURLException {
        WebDriver driver = acessarAplicacao();
        driver.findElement(By.id("addTodo")).click();
        driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
        driver.findElement(By.id("saveButton")).click();
        Assert.assertEquals(driver.findElement(By.id("message")).getText(),"Fill the due date");
        driver.quit();
    }

    @Test
    public void deveSalvarTarefaComDataPassada() throws InterruptedException, MalformedURLException {
        WebDriver driver = acessarAplicacao();
        driver.navigate().to("http://localhost:8001/tasks/");
        driver.findElement(By.id("addTodo")).click();
        driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
        driver.findElement(By.id("dueDate")).sendKeys("20/10/2012");
        driver.findElement(By.id("saveButton")).click();
        Assert.assertEquals(driver.findElement(By.id("message")).getText(),"Due date must not be in past");
        driver.quit();
    }
}
