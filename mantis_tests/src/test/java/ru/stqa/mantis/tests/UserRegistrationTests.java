package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.mantis.common.CommonFunctions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class UserRegistrationTests extends TestBase {



   @Test
    void canRegisterUser() {

       var username = CommonFunctions.randomString(6);
       var email1 = String.format("%s@localhost", username);
       app.jamesCli().addUser(email1,"password");
       app.registrHelper().signupNewAccount(username, email1);
       var messages=app.mail().receive(email1,"password", Duration.ofSeconds(50));
       app.mail().drain(email1,"password");
       var text = messages.get(0).content();
       var pattern = Pattern.compile("http://\\S*");
       var matcher = pattern.matcher(text);
       if (matcher.find()){
           var url= text.substring(matcher.start(),matcher.end());
           System.out.println(url);
           app.registrHelper.confirmReg(url,username);
       }else {
           throw  new RuntimeException("No mail");
       }
       app.htpp().login(username,"password");
       Assertions.assertTrue(app.htpp().isLoggedIn());
        }
    }

