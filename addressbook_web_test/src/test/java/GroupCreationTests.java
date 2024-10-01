import model.GroupData;
import org.openqa.selenium.By;

public class GroupCreationTests extends TestBase {


    @org.junit.jupiter.api.Test
    public void canCreateGroup() {
        openGroupsPage();
        createGroup(new GroupData("group name", "group header", "group footer"));

    }

    @org.junit.jupiter.api.Test
    public void canCreateGroupWithEmptyName() {
        openGroupsPage();
        createGroup(new GroupData());
    }
    @org.junit.jupiter.api.Test
    public void canCreateGroupWithOnlyName() {
        openGroupsPage();
        var emptyGroup = new GroupData();
        var groupWithName = emptyGroup.withName("some name");
        createGroup(groupWithName);
    }
}
