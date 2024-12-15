package Manager;
import model.ContactData;
import model.GroupData;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public void createContact(ContactData contact) {
        initContactcCreation();
        fillContactform(contact);
        submitContactCreation();
        returnToMainPage();
    }
    public void removeContact() {
        selectContact();
        removeSelectedContact();
        returnToMainPage();
    }
    public boolean isContactPresent() {
        return manager.isElementPresent(By.name("selected[]"));
    }

    public void selectContact(){
        click(By.name("selected[]"));
    }

    public void removeSelectedContact(){
        click(By.xpath("//input[@value=\'Delete\']"));
    }

    public void initContactcCreation(){
        click(By.linkText("add new"));
    }

    public void fillContactform(ContactData contact){
        type(By.name("firstname"),contact.firstname());
        type(By.name("lastname"),contact.lastname());
    }

    public void submitContactCreation()
    {
        click(By.name("submit"));
    }

    public void returnToMainPage(){
        click(By.linkText("home"));
    }
    public int getCount() {
        returnToMainPage();
        return manager.driver.findElements(By.name("selected[]")).size(); // возвращаем количество групп

    }
    public List<ContactData> getList() {
        var contacts = new ArrayList<ContactData>();// Создаем пустой список для контактов
        var tds = manager.driver.findElements(By.xpath("//table[@class='sortcompletecallback-applyZebra']/tbody/tr"));//получить со страницы список элементов, которые содержат информацию о контактах
        for (var row: tds) {
            var cells = row.findElements(By.tagName("td"));
            if (!cells.isEmpty()) {
                var firstname = cells.get(2).getText();
                var lastname = cells.get(1).getText();
                var checkbox = cells.get(0).findElement(By.name("selected[]"));
                var id = checkbox.getAttribute("value");
                contacts.add(new ContactData().withId(id).withFname(firstname).withLname(lastname));// в список контактов добавляем новый объект
            }
        }
        return contacts;
    }

    }
