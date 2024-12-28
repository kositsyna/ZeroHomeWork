package ru.stqa.mantis.manager;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.stqa.mantis.model.MailMessage;

import java.util.List;
import java.util.regex.Pattern;


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

        public void confirmReg(String link,String user,String password) {

            manager.driver.get(link);
           // click(By.name("realname"));
            type(By.name("realname"), user);
            type(By.name("password"), password);
            type(By.name("password_confirm"), password);
            click(By.cssSelector("button[type = 'submit']"));
    }

    public String takeUrl(List<MailMessage> messages) {
        var text = messages.get(0).content();
        var pattern = Pattern.compile("http://\\S*");
        var matcher = pattern.matcher(text);
        String url = "";
        if (matcher.find()) {
            url = text.substring(matcher.start(), matcher.end());
        }
        return url;
    }
}