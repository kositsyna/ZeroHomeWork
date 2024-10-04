<<<<<<< HEAD
import model.GroupData;
=======
>>>>>>> origin/main
import org.openqa.selenium.By;

public class GroupCreationTests extends TestBase {


    @org.junit.jupiter.api.Test
    public void canCreateGroup() {
        openGroupsPage();
<<<<<<< HEAD
        createGroup(new GroupData("group name", "group header", "group footer"));
=======
        createGroup("group name", "group header", "group footer");
>>>>>>> origin/main

    }

    @org.junit.jupiter.api.Test
    public void canCreateGroupWithEmptyName() {
        openGroupsPage();
<<<<<<< HEAD
        createGroup(new GroupData());
    }
    @org.junit.jupiter.api.Test
    public void canCreateGroupWithOnlyName() {
        openGroupsPage();
        var emptyGroup = new GroupData();
        var groupWithName = emptyGroup.withName("some name");
        createGroup(groupWithName);
=======
        driver.findElement(By.linkText("groups")).click();
        createGroup("", "", "");

>>>>>>> origin/main
    }
}
