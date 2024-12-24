package Manager;
import model.GroupData;
import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GroupHelper extends HelperBase {

    public GroupHelper(ApplicationManager manager) {
        super(manager);
    }

    public void createGroup(GroupData group) {
        openGroupsPage();
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        returnToGroupsPage();
    }

    public void removeGroup(GroupData group) {
        openGroupsPage();
        selectGroup(group);
        removeSelectedGroup();
        returnToGroupsPage();
    }

    public void modifyGroup(GroupData group, GroupData modifiedGroup) {
        openGroupsPage();
        selectGroup(group);
        initGroupModification();
        fillGroupForm(modifiedGroup);
        submitGroupModification();
        returnToGroupsPage();
    }

    public void openGroupsPage() {
        if (!manager.isElementPresent(By.name("new"))) {
            click(By.linkText("groups"));
        }
    }


    private void submitGroupCreation() {
        click(By.name("submit"));
    }

    private void initGroupCreation() {
        click(By.name("new"));
    }


    private void removeSelectedGroup() {
        click(By.name("delete"));
    }


    private void returnToGroupsPage() {
        click(By.linkText("group page"));
    }

    private void submitGroupModification() {
        click(By.name("update"));

    }

    private void fillGroupForm(GroupData group) {
        type(By.name("group_name"),group.name());
        type(By.name("group_header"),group.header());
        type(By.name("group_footer"),group.footer());
    }

    private void initGroupModification() {
        click(By.name("edit"));

    }

    private void selectGroup(GroupData group) {
        click(By.cssSelector(String.format("input[value='%s']",group.id())));

    }

    public int getCount() {
        openGroupsPage();
        return manager.driver.findElements(By.name("selected[]")).size(); // возвращаем количество групп

    }

    public void removeAllGroups() {
        openGroupsPage();
        selectAllGroups();
        removeSelectedGroup();
    }

    public void selectAllGroups() {
        // Переменная, в которой храним кол-во групп
        //        for (var checkbox : countCheckBox){
//            checkbox.click();
//        }
        manager.driver
                .findElements(By.name("selected[]"))
                .forEach(checkbox -> checkbox.click());
    }

    public List<GroupData> getList() {
        openGroupsPage();//открытие страницы со списком групп
        var spans = manager.driver.findElements(By.cssSelector("span.group"));
        return spans.stream()
                .map(span ->{
                    var name = span.getText();
                    var checkbox = span.findElement(By.name("selected[]"));
                    var id= checkbox.getAttribute("value");
                    return new GroupData().withId(id).withName(name);
                })
                .collect(Collectors.toList());
    }
    
}

