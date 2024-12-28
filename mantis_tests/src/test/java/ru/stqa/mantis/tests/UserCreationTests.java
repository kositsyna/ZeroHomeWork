package ru.stqa.mantis.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import ru.stqa.mantis.model.DeveloperMailUser;

public class UserCreationTests extends TestBase {

DeveloperMailUser user;

@Test
void canCreateUser() {

    var password = "password";
    user = app.developerMail().addUser();
    var email = String.format("%s@developer.com", user.name());

//    app.jamesApi().addUser(email, password);
//    app.jamesCli().login("administrator", "root");
//    app.jamesCli().openPage(email, user, password);
//    var messages = app.mail().receive(email, password, Duration.ofSeconds(10));
//    var url = CommonFunctions.extractUrl(messages.get(0).content());
//    //app.user().finishCreation(url,password);
//    app.htpp().login(user, password);
//    Thread.sleep(500);
//    Assertions.assertTrue(app.htpp().isLoggedUserIn(user));
}
@AfterEach
    void deleteMailUser(){
    app.developerMail().deleteUser(user);
}
}

