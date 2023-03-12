package ru.levelp.at.homework3;

import java.time.Duration;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FirstExercise {

    private static final String URL = "https://mail.ru/";

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.navigate().to(URL);
        driver.manage().window().maximize();
    }

    @Test
    @Tag(Tags.Suites)
    void openMailHomePage() {
        driver.navigate().to(URL); //открыть домашнюю страницу
        driver.findElement(By.className("resplash-btn")).click(); // кнопка Войти
        WebElement frameElement = driver.findElement(By.className("ag-popup__frame__layout__iframe"));
        driver.switchTo().frame(frameElement);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")))
            .sendKeys("mailbox_testovich"); //ввод логина
        driver.findElement(By.className("base-0-2-79")).click(); //кнопка "Ввести пароль"
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")))
            .sendKeys("kuarra_555"); //ввод пароля

        driver.findElement(By.xpath("//button[@data-test-id= 'submit-button']")).click(); //кнопка "Войти"
        WebElement mailName = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text() = 'mailbox_testovich@mail.ru']")));
        Assertions.assertThat(mailName.getText()).isEqualTo("mailbox_testovich@mail.ru");

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href = '/compose/']"))).click(); //написать письмо
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class = 'container--H9L5q size_s--3_M-_']")))
            .click(); // поле "Кому"
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class = 'container--H9L5q size_s--3_M-_']")))
            .sendKeys("mailbox_testovich@mail.ru"); // заполнение поля "Кому"
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name = 'Subject']")))
            .sendKeys("Test"); //заполнение темы письма
        wait.until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//div[contains(@class, 'compose-app_fix')]//div[contains(@class, 'editable')]/div/div[2]")))
            .sendKeys( "test body"); //заполнение тела письма
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@data-test-id = 'save']")))
            .click(); //сохранение в черновиках
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class= 'icon--3PdIV']")))
            .click(); //закрытие письма

        //проверить что письмо сохранено в черновиках
        WebElement draft = driver.findElement(By.xpath("//*[@href = '/drafts/?']"));
        draft.click(); // открыть папку черновик
        WebElement correspondent = driver.findElement(By.xpath("//*[@class = 'llct__correspondent llct__correspondent_new']"));
        WebElement subject = driver.findElement(By.xpath("//*[@class = 'll-sj__normal']"));
        WebElement body = driver.findElement(By.xpath("//*[@class = 'llct__snippet']"));

        Assertions.assertThat(draft.getText()).isEqualTo("Черновики");
        Assertions.assertThat(correspondent.getText()).isEqualTo("mailbox_testovich@mail.ru");
        Assertions.assertThat(subject.getText()).isEqualTo("Test");
        Assertions.assertThat(body.getText()).isEqualTo("test body -- Test Testovich Отправлено из Почты Mail.ru");

        correspondent.click(); //открыть письмо в черновиках
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@data-test-id = 'send']")))
            .click();
       // wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@data-test-id,'send')]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class= 'ico ico_16-close ico_size_s']"))).click(); //закрыть окно

        WebElement send = driver.findElement(By.xpath("//*[@href = '/sent/?']"));
        send.click(); // открыть папку отправленные
        WebElement correspondent1 = driver.findElement(By.xpath("//*[@class = 'llct__correspondent llct__correspondent_new']"));
        WebElement subject1 = driver.findElement(By.xpath("//*[@class = 'll-sj__normal']"));
        WebElement body1 = driver.findElement(By.xpath("//*[@class = 'llct__snippet']"));

        Assertions.assertThat(send.getText()).isEqualTo("Отправленные");
        //Assertions.assertThat(correspondent1.getText()).isEqualTo("mailbox_testovich@mail.ru");
        Assertions.assertThat(subject1.getText()).isEqualTo("Self: Test");
        Assertions.assertThat(body1.getText()).isEqualTo("test body -- Test Testovich Отправлено из Почты Mail.ru");

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@data-testid = 'whiteline-account']"))).click(); //нажать на почту
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@data-testid = 'whiteline-account-exit']"))).click(); //Выход


    }


}

// @AfterEach
// void tearDown() {
//driver.quit();
//}


