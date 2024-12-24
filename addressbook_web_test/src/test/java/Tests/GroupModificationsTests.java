package Tests;

import common.CommonFunctions;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.Set;

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
        var testData = new GroupData().withName(CommonFunctions.randomString(10));
        app.groups().modifyGroup(oldGroups.get(index), testData);
        var newGroups = app.hbm().getGroupList();
        var expectedList = new ArrayList<>(oldGroups); // загружаем список групп после модификации
        expectedList.set(index,testData.withId(oldGroups.get(index).id()));
        Assertions.assertEquals(Set.copyOf(newGroups), Set.copyOf(expectedList));
    }
}
