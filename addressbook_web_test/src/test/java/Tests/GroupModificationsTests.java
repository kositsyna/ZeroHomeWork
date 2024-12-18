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
        if (app.hbm().getGroupCount() == 0) //если количество групп = 0, то сначала создаем новую, а потом изменяем ее наименование
        {
            app.hbm().createGroup(new GroupData("", "", "", ""));
        }
        var oldGroups= app.hbm().getGroupList(); //получаем список групп перед удалением объекта
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size()); //выбираем группу для модификации
        var testData = new GroupData().withName("modified name");
        app.groups().modifyGroup(oldGroups.get(index), testData);
        var newGroups = app.hbm().getGroupList();
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
