package ru.stqa.mantis.manager;

import okhttp3.*;
import org.openqa.selenium.io.CircularOutputStream;
import org.openqa.selenium.os.CommandLine;

import java.io.IOException;
import java.net.CookieManager;

public class JamesAPIHelper extends HelperBase{
    public static final MediaType JSON = MediaType.get("application/json");
    OkHttpClient client;

    public JamesAPIHelper(ApplicationManager manager) {

        super(manager);
        client = new OkHttpClient.Builder().cookieJar(new JavaNetCookieJar(new CookieManager())).build();
    }

    public void addUser(String email, String password){ //метод создания пользователя через Api
        RequestBody body = RequestBody.create(
                String.format("{\"password\":\"%s\"}",password),JSON);
        Request request = new Request.Builder()
                .url(String.format("%s/users/%s", manager.property("james.apiBaseUrl"), email))
                .put (body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new RuntimeException("Unexpected code " + response);//проверка того что ответ был успешным
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
