package ru.stqa.mantis.manager;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;


public class RegistrHelper extends HelperBase {

    public RegistrHelper(ApplicationManager manager) {
        super(manager);
    }


    public void signupNewAccount(String user, String email) {

        click(By.cssSelector("a[href='signup_page.php']"));
        click(By.name("username"));
        type(By.name("username"),user);
        click(By.name("email"));
        type(By.name("email"),email);
        click(By.cssSelector("input[type='submit']"));

    }

        public void confirmReg(String link,String user) {

           // manager.driver = new ChromeDriver();
            manager.driver.get(link);
            click(By.name("realname"));
            type(By.name("realname"),user);
            click(By.name("password"));
            type(By.name("password"),"password");
            click(By.name("password_confirm"));
            type(By.name("password_confirm"),"password");
            click(By.cssSelector("button[type='submit']"));
            manager.driver().close();
    }
}