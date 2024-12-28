package ru.stqa.mantis.tests;

import java.time.Duration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.mantis.common.CommonFunctions;
import ru.stqa.mantis.model.UserData;

import java.util.regex.Pattern;
import java.util.stream.Stream;

public class UserApiCreationTest extends TestBase {

    public static Stream<String> randomUser () {
        return Stream.of(CommonFunctions.randomString(8));
    }

    @ParameterizedTest
    @MethodSource("randomUser")
    void canCreateUser(String user) {
        var email = String.format("%s@localhost", user);
        var password = "password";
        app.jamesApi().addUser(email, password);
        app.rest().createUser(new UserData()
                .withUsername(user)
                .withPassword(password)
                .withEmail(email));
        var messages = app.mail().receive(email, password, Duration.ofSeconds(60));
        System.out.println(messages);
        var url = app.registrHelper.takeUrl(messages);
        app.registrHelper().confirmReg(url, user, password);
        app.htpp().login(user, password);
        Assertions.assertTrue(app.htpp().isLoggedIn());
    }
}
