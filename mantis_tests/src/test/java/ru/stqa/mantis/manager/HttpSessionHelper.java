package ru.stqa.mantis.manager;

import okhttp3.*;
import java.io.IOException;
import java.net.CookieManager;


public class HttpSessionHelper extends HelperBase {

    OkHttpClient client;
    public HttpSessionHelper(ApplicationManager manager) {
        super(manager);
        client = new OkHttpClient.Builder().cookieJar(new JavaNetCookieJar(new CookieManager())).build();
    }


public void login(String username, String password) {
    RequestBody formBody = new FormBody.Builder()
            .add("username",username)//имя пользователя
            .add("password",password)//пароль
            .build();
    Request request = new Request.Builder()
            .url(String.format("%s/login.php",manager.property("web.baseUrl")))//тк в пропертис для web.baseUrl указано http://localhost/mantisbt-2.26.3/
            .post(formBody)
            .build();
    try (Response response = client.newCall(request).execute()) { // отправляем запрос
        if (!response.isSuccessful()) throw new RuntimeException("Unexpected code " + response);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}

public boolean isLoggedIn() {//проверяет сейчас прям выполнен логин или нет, отправляем GET запрос
    Request request = new Request.Builder()
            .url(manager.property("web.baseUrl"))//для GET запроса тип указывать НЕ обязательно
            .build();
    try (Response response = client.newCall(request).execute()) {
        if (!response.isSuccessful()) throw new RuntimeException("Unexpected code " + response);
        String body = response.body().string();//сохраняем ответ, чтобы его анализировать. body() возвращает объект
        return body.contains("<span class=\"user-info\">administrator</span>");//проверяем в ответе блок, в котором логин действительно выполнился
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}

}