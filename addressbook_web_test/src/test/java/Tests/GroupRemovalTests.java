package Tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GroupRemovalTests extends TestBase {


    @Test
    public void canRemoveGroup() {
        if (app.groups().getCount() == 0) //Если количество групп = 0, то сначала создаем новую
        {
            app.groups().createGroup(new GroupData("", "", "", ""));
        }
        var oldGroups= app.groups().getList(); //получаем список групп перед удалением объекта
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());
        app.groups().removeGroup(oldGroups.get(index));
        var newGroups= app.groups().getList();  //получаем новый список групп после удаления объекта
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.remove(index);
        Assertions.assertEquals(newGroups,expectedList);
    }

    @Test
    public void canRemoveAllGroups(){//тест удаления всех групп одновременно
        if (app.groups().getCount() == 0) //Если количество групп = 0, то сначала создаем новую
        {
            app.groups().createGroup(new GroupData("", "", "", ""));
        }
        app.groups().removeAllGroups();
        Assertions.assertEquals(0,app.groups().getCount()); //Сравниваем результат после удаления, с количеством групп

    }

}
