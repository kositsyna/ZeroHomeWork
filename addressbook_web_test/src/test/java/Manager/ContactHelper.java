package Manager;

import model.ContactData;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }
    private void fillContactForm(ContactData contact) {//метод для изменения данных контакта
        type(By.name("firstname"), contact.firstname());
        type(By.name("lastname"), contact.lastname());
    }

    public void createContact(ContactData contact) {
        initContactcCreation();
        fillContactForm(contact);
        submitContactCreation();
        returnToMainPage();
    }


    public void removeContact(ContactData contact) {
        selectContact(contact);
        removeSelectedContact();
        returnToMainPage();
    }

    public boolean isContactPresent() {
        return manager.isElementPresent(By.name("selected[]"));
    }

    public void openContactPresent() {
        if (manager.isElementPresent(By.xpath("(//input[@name=\'Delete\'])"))) {
            click(By.linkText("home"));
        }
    }

    public void checkIsContact() { // если на странице нет контактов, то создадим
        if (!manager.isElementPresent(By.name("selected[]"))) {
            createContact(new ContactData("", "lastname99", "firstname88"));//вызов метода создания контакта
        }
    }

    /*public void fillContactform(ContactData contact){
        type(By.name("firstname"),contact.firstname());
        type(By.name("lastname"),contact.lastname());
    }*/


    public void modifyContact(ContactData contact, ContactData modifiedContact) {//метод для модификации контакта
        selectContact(contact);//выбрать контакт (отметить галочкой)
        initContactModification();//нажать кнопку модификации Edit
        fillContactForm(modifiedContact);//заполнить форму данными, которые содержатся в переданном объекте
        submitContactModification();//сохраняем форму  по кнопке Update
        returnToMainPage();//возврат на страницу контактов
    }

   /* public void selectContact() {
        click(By.name("selected[]"));
    }*/

    private void selectContact(ContactData contact) {
        click(By.cssSelector(String.format("input[value='%s']",contact.id())));// выбор контакта
    }

    public void removeSelectedContact() {
        click(By.xpath("//input[@value=\'Delete\']"));
    }

    public void initContactcCreation() {
        click(By.linkText("add new"));
    }

    private void initContactModification() {
        click(By.cssSelector("[title='Edit']"));
    }


    public void submitContactCreation() {
        click(By.name("submit"));
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    ;

    public void returnToMainPage() {
        click(By.linkText("home"));
    }

    public int getCount() {
        returnToMainPage();
        return manager.driver.findElements(By.name("selected[]")).size(); // возвращаем количество групп

    }

    public List<ContactData> getList() {
        var contacts = new ArrayList<ContactData>();// Создаем пустой список для контактов
        var tds = manager.driver.findElements(By.xpath("//table[@class='sortcompletecallback-applyZebra']/tbody/tr"));//получить со страницы список элементов, которые содержат информацию о контактах
        for (var row : tds) {
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
