package ru.stqa.mantis.manager;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Properties;

public class ApplicationManager {

    WebDriver driver;
    private String string;
    private Properties properties;
    private SessionHelper sessionHelper;
    private HttpSessionHelper httpSessionHelper;
    private JamesCLIHelper jamesCLIHelper;
    private MailHelper mailHelper;
    public RegistrHelper registrHelper;
    public JamesAPIHelper jamesApi;
    private DeveloperMailHelper developerMailHelper;
    private UserHelper userHelper;
    private RestApiHelper restApiHelper;




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
            driver.get(properties.getProperty("web.baseURL"));
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

    public HttpSessionHelper htpp() {
        if (httpSessionHelper==null){//ленивая инициализация
            httpSessionHelper = new HttpSessionHelper(this);//менеджер передает ссылку на себя
        }
        return httpSessionHelper;//возвращаем либо созданный объект либо тот, который был создан ранее
    }
    public JamesCLIHelper jamesCli() {
        if (jamesCLIHelper==null){//ленивая инициализация
            jamesCLIHelper = new JamesCLIHelper(this);//менеджер передает ссылку на себя
        }
        return jamesCLIHelper;//возвращаем либо созданный объект либо тот, который был создан ранее
    }
    public JamesAPIHelper jamesApi() {
        if (jamesApi==null){//ленивая инициализация
            jamesApi = new JamesAPIHelper(this);//менеджер передает ссылку на себя
        }
        return jamesApi;//возвращаем либо созданный объект либо тот, который был создан ранее
    }

    public MailHelper mail() {//ленивая инициализация
        if (mailHelper == null) {
            mailHelper = new MailHelper(this);
        }
        return mailHelper;
    }
    public DeveloperMailHelper developerMail() {//ленивая инициализация
        if (developerMailHelper == null) {
            developerMailHelper = new DeveloperMailHelper(this);
        }
        return developerMailHelper;
    }
    public UserHelper user() {//ленивая инициализация
        if (userHelper == null) {//ленивая инициализация
            userHelper = new UserHelper(this);//менеджер передает ссылку на себя
        }
        return userHelper;//возвращаем либо созданный объект либо тот, который был создан ранее
    }

    public RegistrHelper registrHelper() {//ленивая инициализация
        if (registrHelper == null) {
            registrHelper = new RegistrHelper(this);
        }
        return registrHelper;
    }
    public RestApiHelper rest() {
        if(restApiHelper == null) {
            restApiHelper = new RestApiHelper(this);
        }
        return restApiHelper;
    }

    public String property(String name){//вспомогательный метод для обращения к файлу с настройками
        return properties.getProperty(name);
    }

    public void doneRegistr (String url, String username) {
        WebDriver driver = new FirefoxDriver();
        driver.get(url);
        driver.manage().window().setSize(new Dimension(1920, 1040));
        driver.findElement(By.id("realname")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.id("password-confirm")).sendKeys("password");
        driver.findElement((By.cssSelector("span.bigger-110"))).click();
    }

}

