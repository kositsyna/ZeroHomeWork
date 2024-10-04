<<<<<<< HEAD
import model.GroupData;
=======
<<<<<<< Updated upstream
=======
<<<<<<< HEAD
import model.GroupData;
=======
>>>>>>> origin/main
>>>>>>> Stashed changes
>>>>>>> origin/main
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class GroupRemovalTests extends TestBase {

    @Test
    public void canRemoveGroup() {
        openGroupsPage();
        if (!isGroupPresent()){
<<<<<<< Updated upstream
=======
<<<<<<< HEAD
            createGroup
=======
>>>>>>> Stashed changes
            driver.findElement(By.linkText("groups")).click();
<<<<<<< HEAD
            createGroup(new GroupData("", "", ""));
=======
            createGroup("", "", "");
>>>>>>> origin/main
<<<<<<< Updated upstream
=======
>>>>>>> origin/main
>>>>>>> Stashed changes
        }
        removeGroup();
    }

}
