<<<<<<< HEAD
import model.GroupData;
=======
>>>>>>> origin/main
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class GroupRemovalTests extends TestBase {

    @Test
    public void canRemoveGroup() {
        openGroupsPage();
        if (!isGroupPresent()){
            driver.findElement(By.linkText("groups")).click();
<<<<<<< HEAD
            createGroup(new GroupData("", "", ""));
=======
            createGroup("", "", "");
>>>>>>> origin/main
        }
        removeGroup();
    }

}
