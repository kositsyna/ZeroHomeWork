package common;

import java.util.Random;

public class CommonFunctions {


    public static String randomString(int n){ //метод генерации рандомной строки
        var rnd = new Random();
        var result = "";
        for (int i = 0; i < n; i++){
            result = result + (char)('a' + rnd.nextInt(26));
        }
        return result;

    }
}
