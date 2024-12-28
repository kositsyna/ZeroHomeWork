package ru.stqa.mantis.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.mantis.model.DeveloperMailUser;

import java.time.Duration;

public class UserCreationTests extends TestBase {

DeveloperMailUser user;

@Test
void canCreateUser() {

    var password = "password";
    user = app.developerMail().addUser();//обращаемся в сервису developerMail и добавляем пользователя.
    // В качестве ответа получаем информацию о пользователе
    var email = String.format("%s@developermail.com",user.name());
    //app.jamesCli().login("administrator","root");//проверяем что можно залогинится с "administrator"/"root"
    //app.jamesCli().openPage(email, user, password); //открываем браузер и заполняем форму создания и отправляем (в браузере, создать класс помощник) Письмо уходит
    app.user().startCreation(user.name(),email);
    var message = app.developerMail().receive(user, Duration.ofSeconds(10));
    var url = CommonFunctions.extractUrl(message);

    app.user().finishCreation(url, user.name(), password);
    //UserHelper.checkUser(url, user, password);

    app.htpp().login(user.name(), password);
    Thread.sleep(500);
    Assertions.assertTrue(app.htpp().isLoggedUserIn(user.name()));
}
@AfterEach
    void deleteMailUser(){
    app.developerMail().deleteUser(user);
}
}

