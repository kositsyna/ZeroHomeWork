package Tests;

import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupModificationsTests extends TestBase{
    @Test
    void canModifyGroup(){
        if (app.groups().getCount() == 0) //если количество групп = 0, то сначала создаем новую, а потом изменяем ее наименование
        {
            app.groups().createGroup(new GroupData("", "", "", ""));
        }
        app.groups().modifyGroup(new GroupData().withName("modified name"));
    }
}
