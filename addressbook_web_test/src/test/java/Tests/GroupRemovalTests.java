package Tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GroupRemovalTests extends TestBase {


    @Test
    public void canRemoveGroup() {
        if (app.groups().getCount() == 0) //Если количество групп = 0, то сначала создаем новую
        {
            app.groups().createGroup(new GroupData("", "", ""));
        }
        int groupCount = app.groups().getCount();
        app.groups().removeGroup();
        int newgroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount - 1,newgroupCount);
    }

    @Test
    public void canRemoveAllGroups(){//тест удаления всех групп одновременно
        if (app.groups().getCount() == 0) //Если количество групп = 0, то сначала создаем новую
        {
            app.groups().createGroup(new GroupData("", "", ""));
        }
        app.groups().removeAllGroups();
        Assertions.assertEquals(0,app.groups().getCount()); //Сравниваем результат после удаления, с количеством групп

    }

}
