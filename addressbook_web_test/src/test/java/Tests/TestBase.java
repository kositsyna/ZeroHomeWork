package Tests;

import Manager.ApplicationManager;
import org.junit.jupiter.api.BeforeEach;
import java.io.File;
import java.nio.file.Paths;
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

    public static String randomFile(String dir){
        var fileNames = new File(dir).list();
        var rnd = new Random();
        var index = rnd.nextInt(fileNames.length);
        return Paths.get(dir,fileNames[index]).toString();
    }

}
