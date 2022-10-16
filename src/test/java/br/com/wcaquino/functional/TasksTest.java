package br.com.wcaquino.functional;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TasksTest {

    WebDriver driver = new ChromeDriver();

    public void esperar(WebElement webElement){
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(webElement));
    }

    @After
    public void rodarApos(){
        driver.quit();
    }

    @Test
    public void testAmbiente(){
        driver.navigate().to("http://www.google.com");
    }

    @Test
    public void deveSalvarTarefaComSucesso() throws InterruptedException {
        driver.navigate().to("http://localhost:8001/tasks/");
        driver.findElement(By.id("addTodo")).click();
        driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
        driver.findElement(By.id("dueDate")).sendKeys("20/10/2022");
        driver.findElement(By.id("saveButton")).click();
        Assert.assertEquals(driver.findElement(By.id("message")).getText(),"Success!");
    }

    @Test
    public void naoDeveSalvarTarefaSemDescricao() throws InterruptedException {
        driver.navigate().to("http://localhost:8001/tasks/");
        driver.findElement(By.id("addTodo")).click();
        driver.findElement(By.id("dueDate")).sendKeys("20/10/2022");
        driver.findElement(By.id("saveButton")).click();
        Assert.assertEquals(driver.findElement(By.id("message")).getText(),"Fill the task description");
    }

    @Test
    public void naoDeveSalvarTarefaSemData() throws InterruptedException {
        driver.navigate().to("http://localhost:8001/tasks/");
        driver.findElement(By.id("addTodo")).click();
        driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
        driver.findElement(By.id("saveButton")).click();
        Assert.assertEquals(driver.findElement(By.id("message")).getText(),"Fill the due date");
    }

    @Test
    public void deveSalvarTarefaComDataPassada() throws InterruptedException {
        driver.navigate().to("http://localhost:8001/tasks/");
        driver.findElement(By.id("addTodo")).click();
        driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
        driver.findElement(By.id("dueDate")).sendKeys("20/10/2012");
        driver.findElement(By.id("saveButton")).click();
        Assert.assertEquals(driver.findElement(By.id("message")).getText(),"Due date must not be in past");
    }
}
