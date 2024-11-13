package Manager;
import model.GroupData;
import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

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

    public void modifyGroup(GroupData modifiedGroup) {
        openGroupsPage();
        selectGroup(null);
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
        var countCheckBox = manager.driver.findElements(By.name("selected[]"));// Переменная, в которой храним кол-во групп
        for (var checkbox : countCheckBox){
            checkbox.click();
        }
    }

    public List<GroupData> getList() {
        var groups = new ArrayList<GroupData>();
        var spans = manager.driver.findElements(By.cssSelector("span.group")); // ищем элементы с заданными аттрибутами
        for (var span:spans){                                                 //цикл по найденным элементам
            var name = span.getText();                                        //  получаем название группы
            var checkbox = span.findElement(By.name("selected[]"));          //ищем чекбокс внутри элемента span
            var id = checkbox.getAttribute("value");                  //забираем у чекбокса значение аттрибута велью
            groups.add(new GroupData().withId(id).withName(name));       //в список групп добавляем новый объект с заданным именем и айди
        }
        return groups;
    }
}

