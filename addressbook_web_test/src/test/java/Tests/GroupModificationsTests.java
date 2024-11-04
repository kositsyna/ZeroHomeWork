package Tests;

import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupModificationsTests extends TestBase{
    @Test
    void canModifyGroup(){
        if (!app.groups().isGroupPresent())
        {
            app.groups().createGroup(new GroupData("", "", ""));
        }
        app.groups().modifyGroup(new GroupData().withName("modified name"));
    }
}
