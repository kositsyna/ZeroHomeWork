package Tests;

import Manager.ApplicationManager;
import org.junit.jupiter.api.BeforeEach;

import java.util.Random;

public class TestBase {
    protected static ApplicationManager app;

    @BeforeEach
    public void setUp() {
        if (app == null){
            app = new ApplicationManager();
        }
        app.init("chrome");
    }

    public String randomString(int n){ //метод генерации рандомной строки
        var rnd = new Random();
        var result = "";
        for (int i = 0; i < n; i++){
            result = result + (char)('a' + rnd.nextInt(26));
        }
        return result;

    }

}
