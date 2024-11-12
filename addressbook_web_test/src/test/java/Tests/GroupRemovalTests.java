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

}
