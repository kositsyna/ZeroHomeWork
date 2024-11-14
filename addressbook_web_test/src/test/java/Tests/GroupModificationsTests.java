package Tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class GroupModificationsTests extends TestBase{
    @Test
    void canModifyGroup(){
        if (app.groups().getCount() == 0) //если количество групп = 0, то сначала создаем новую, а потом изменяем ее наименование
        {
            app.groups().createGroup(new GroupData("", "", "", ""));
        }
        var oldGroups= app.groups().getList(); //получаем список групп перед удалением объекта
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size()); //выбираем группу для модификации
        var testData = new GroupData().withName("modified name");
        app.groups().modifyGroup(oldGroups.get(index), testData);
        var newGroups = app.groups().getList();
        var expectedList = new ArrayList<>(oldGroups); // загружаем список групп после модификации
        expectedList.set(index,testData.withId(oldGroups.get(index).id()));
        Comparator<GroupData> compareById = (o1, o2) -> {  //переменная для сортировки списков
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroups.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(newGroups,expectedList); //сравнение списков
    }
}
