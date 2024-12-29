package Tests;

import io.qameta.allure.Allure;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GroupRemovalTests extends TestBase {


    @Test
    public void canRemoveGroup() {
        Allure.step("Check precondition", stepContext -> {
        if (app.hbm().getGroupCount() == 0) //Если количество групп = 0, то сначала создаем новую
        {
            app.hbm().createGroup(new GroupData("", "", "", ""));
        }} );

        var oldGroups= app.hbm().getGroupList(); //получаем список групп перед удалением объекта
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());
        app.groups().removeGroup(oldGroups.get(index));
        var newGroups= app.hbm().getGroupList();  //получаем новый список групп после удаления объекта
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.remove(index);
        Allure.step("Validation", stepContext -> {Assertions.assertEquals(newGroups,expectedList);});
    }

    @Test
    public void canRemoveAllGroups(){//тест удаления всех групп одновременно
        if (app.hbm().getGroupCount() == 0) //Если количество групп = 0, то сначала создаем новую
        {
            app.hbm().createGroup(new GroupData("", "", "", ""));
        }
        app.groups().removeAllGroups();
        Assertions.assertEquals(0,app.hbm().getGroupCount()); //Сравниваем результат после удаления, с количеством групп

    }

}
