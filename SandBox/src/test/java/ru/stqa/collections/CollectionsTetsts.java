package ru.stqa.collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CollectionsTetsts {

    @Test
    void arrayTests(){
        //var array = new String[]{"a","b","c"};  //создаем массив строк
        var array = new String[3]; // создание массива с пустыми значениями
        Assertions.assertEquals(3,array.length); // проверяем, что длинна массива = 3
        array[0]="a";
        Assertions.assertEquals("a",array[0]);//проверяем, что строка а- первый элемент массива
        array[0] = "d"; //присваиваем первому элементу другое значение
        Assertions.assertEquals("d",array[0]); //проверяем, что строка d- первый элемент массива
    }

    @Test
    void listTest(){
        //var list = List.of("a","b","c"); // функция инициализации НЕ МОДИФИЦИРУЕМОГО списка с определенными элементами
       // var list = new ArrayList<String>(); //создание списка для хранения строк
        var list = new ArrayList<>(List.of("a","b","c")); //Создание списка
        Assertions.assertEquals(3,list.size()); //размер списка = 0
        //list.add("a"); // добавляем элементы в список
        Assertions.assertEquals(3,list.size()); //размер списка = 3
        Assertions.assertEquals("a",list.get(0));//проверяем, что строка а- первый элемент списка

        list.set(0,"d"); //присваиваем первому элементу списка другое значение
        Assertions.assertEquals("d",list.get(0));//проверяем, что строка а- первый элемент списка


    }
}
