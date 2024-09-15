import java.io.File;

public class Hello {
    public static void main(String[] args) {
        System.out.println("Нулевое домашнее задание");

        var ConfigFile = new File("sandbox/build.gradle");
        System.out.println(ConfigFile.getAbsolutePath());
        System.out.println(ConfigFile.exists());
    }


}
