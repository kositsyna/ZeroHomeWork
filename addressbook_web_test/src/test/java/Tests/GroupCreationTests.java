package Tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GroupCreationTests extends TestBase {


    @Test
    public void CanCreateGroup() {
        int groupCount = app.groups().getCount(); // считаем количество уже созданных групп
        app.groups().createGroup(new GroupData("group name", "group header", "group footer"));
        int newgroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount + 1,newgroupCount);
    }

    @Test
    public void CanCreateGroupWithEmptyName() {
        app.groups().createGroup(new GroupData());
    }

    @Test
    public void CanCreateGroupWithNameOnly() {
        var emptyGroup = new GroupData();
        var groupWithName = emptyGroup.withName("some name");
        app.groups().createGroup(groupWithName); //метод, который из объекта делает объект с другим имененем
    }

    @Test
    public void CanCreateMultipleGroups() {
        int n = 5; //кол-во создаваемых групп
        int groupCount = app.groups().getCount(); // считаем количество уже созданных групп
        for (int i = 0 ; i < n; i++){
            app.groups().createGroup(new GroupData(randomString(i*2), "group header", "group footer"));
        }
        int newgroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount + n,newgroupCount);
    }
}
