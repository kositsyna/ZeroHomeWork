package ru.stqa.mantis.common;

import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommonFunctions {


    public static String randomString(int n){ //метод генерации рандомной строки
        var rnd = new Random();
        Supplier<Integer> randomNumbers = () -> rnd.nextInt(20);
        var result = Stream.generate(randomNumbers)
                .limit(n)
                .map(i ->'a'+i)
                .map(Character::toString)
                .collect(Collectors.joining());
        return result;

    }
}
