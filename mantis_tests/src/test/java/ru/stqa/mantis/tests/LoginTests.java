package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoginTests extends TestBase {

    @Test
    void canLogin(){
        app.htpp().login("administrator","root");//проверяем, что можно залогиниться с "administrator"/"root"
        Assertions.assertTrue(app.htpp().isLoggedIn());
    }
}
