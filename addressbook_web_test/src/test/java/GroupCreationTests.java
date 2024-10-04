<<<<<<< HEAD
import model.GroupData;
=======
<<<<<<< Updated upstream
>>>>>>> origin/main
import org.openqa.selenium.By;
=======
<<<<<<< HEAD
import model.GroupData;
=======
>>>>>>> origin/main
import org.openqa.selenium.By;
>>>>>>> origin/main
>>>>>>> Stashed changes

public class GroupCreationTests extends TestBase {


    @org.junit.jupiter.api.Test
    public void canCreateGroup() {
<<<<<<< Updated upstream
=======
<<<<<<< HEAD
        app.openGroupsPage();
        app.createGroup(new GroupData("group name", "group header", "group footer"), GroupCreationTests.this);
=======
>>>>>>> Stashed changes
        openGroupsPage();
<<<<<<< HEAD
        createGroup(new GroupData("group name", "group header", "group footer"));
=======
        createGroup("group name", "group header", "group footer");
>>>>>>> origin/main

<<<<<<< Updated upstream
=======
>>>>>>> origin/main
>>>>>>> Stashed changes
    }

    @org.junit.jupiter.api.Test
    public void canCreateGroupWithEmptyName() {
<<<<<<< Updated upstream
        openGroupsPage();
<<<<<<< HEAD
        createGroup(new GroupData());
=======
<<<<<<< HEAD
        app.openGroupsPage();
        app.createGroup(new GroupData());
=======
        openGroupsPage();
<<<<<<< HEAD
        createGroup(new GroupData());
>>>>>>> origin/main
>>>>>>> Stashed changes
    }
    @org.junit.jupiter.api.Test
    public void canCreateGroupWithOnlyName() {
        openGroupsPage();
        var emptyGroup = new GroupData();
        var groupWithName = emptyGroup.withName("some name");
<<<<<<< Updated upstream
=======
<<<<<<< HEAD
        app.createGroup(groupWithName);
=======
>>>>>>> Stashed changes
        createGroup(groupWithName);
=======
        driver.findElement(By.linkText("groups")).click();
        createGroup("", "", "");

>>>>>>> origin/main
<<<<<<< Updated upstream
=======
>>>>>>> origin/main
>>>>>>> Stashed changes
    }
}
