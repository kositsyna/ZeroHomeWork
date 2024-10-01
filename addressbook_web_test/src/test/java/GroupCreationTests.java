import org.openqa.selenium.By;

public class GroupCreationTests extends TestBase {


    @org.junit.jupiter.api.Test
    public void canCreateGroup() {
        openGroupsPage();
        createGroup("group name", "group header", "group footer");

    }

    @org.junit.jupiter.api.Test
    public void canCreateGroupWithEmptyName() {
        openGroupsPage();
        driver.findElement(By.linkText("groups")).click();
        createGroup("", "", "");

    }
}
