package ru.stqa.mantis.manager;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Properties;

public class ApplicationManager {

    private WebDriver driver;
    private String string;
    private Properties properties;
    private SessionHelper sessionHelper;

    public void init(String browser, Properties properties) {
        this.string = browser;
        this.properties = properties;
           }

    public WebDriver driver() { //метод инициализации ленивой
        if (driver == null) {
            if  ("firefox".equals(string)) {
                driver = new FirefoxDriver();
            } else if ("chrome".equals(string)) {
                driver = new ChromeDriver();
            } else {
                throw new IllegalArgumentException(String.format("Unknown browser", string));
            }
            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
            driver.get(properties.getProperty("web.baseUrl"));
            driver.manage().window().setSize(new Dimension(1386, 742));
        }
        return driver;
    }

    public SessionHelper session() {//метод, который выполняет ленивую инициализацию. Возвращаемое значение иммет тип SessionHelper
        if (sessionHelper==null){
            sessionHelper = new SessionHelper(this);//менеджер передает ссылку на себя
        }
        return sessionHelper;//возвращаем либо созданный объект, либо тот, который был создан ранее
    }

}