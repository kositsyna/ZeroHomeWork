package Manager;

import Manager.ApplicationManager;
import Manager.HelperBase;
import model.ContactData;
import model.GroupData;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(ApplicationManager manager) {
     super(manager);
    }

    public void createGroup(ContactData contactData) {
    }

    public void openContactPage() {//открыть страницу создания нового контакта
        if (!manager.isElementPresent(By.xpath("(//input[@name=\'submit\'])"))) {//если на странице есть кнопка submit (ENTER), то никакой переход делать не требуется
            initContactCreation();
        }
    }

    public void openContactPresent() {//открыть страницу со списком контактов
        if (manager.isElementPresent(By.xpath("(//input[@name=\'Delete\'])"))) { //если на странице есть кнопка Delete, то никакой переход делать не требуется
            click(By.linkText("home"));
        }
    }

    public void createContact(ContactData contact) {//метод для создания контакта
        openContactPage();//открыть страницу с контактами
        initContactCreation();//открыть форму с новым контактом
        fillContactForm(contact);//изменить данные по контакту
        submitContactCreation();//сохранение данных по контакту
        returnToHomePage();//вернуться на станицу с контактами
    }

    public void createContact2(ContactData contact,GroupData group) {//метод для создания контакта
        openContactPage();//открыть страницу с контактами
        initContactCreation();//открыть форму с новым контактом
        fillContactForm(contact);//изменить данные по контакту
        selectGroup(group);
        submitContactCreation();//сохранение данных по контакту
        returnToHomePage();//вернуться на станицу с контактами
    }

    private void selectGroup(GroupData group) {
        new Select(manager.driver.findElement(By.name("new_group"))).selectByValue(group.id());
    }


    public void modifyContact(ContactData contact, ContactData modifiedContact) {//метод для модификации контакта
        selectContact(contact);//выбрать
        initContactModification(contact);//нажать кнопку модификации
        fillContactForm(modifiedContact);//заполнить форму данными, которые содержатся в переданном объекте
        submitContactModification();//сохраняем форму
        returnToHomePage();//возврат на страницу контактов
    }

    public void removeContact(ContactData contact) {//метод по удалению контакта
        selectContact(contact);//выбираем контакт
        removeSelectedContacts();//удаляем контакт
        returnToHomePage();//возвращаемся на страницу с контактами
    }

    private void submitContactCreation() {//сохранение данных по контакту
        click(By.xpath("(//input[@name=\'submit\'])"));
    }

    private void initContactCreation() {//метод по открытию формы с новым контактом
        click(By.linkText("add new"));
    }

    private void removeSelectedContacts() {//удаление контакта
        click(By.xpath("//input[@value=\'Delete\']"));
    }

    public void checkIsContact() { // если на странице нет контактов, то создадим
        if (!manager.isElementPresent(By.name("selected[]"))) {
            createContact(new ContactData("", "mn", "ln", "nn", "addr", "ema@fff.si","","", "fn", "", "", "+441121211", "", "")); //вызов метода создания контакта
        }
    }
    private void selectContact(ContactData contact) {
        click(By.cssSelector(String.format("input[value='%s']",contact.id())));// выбор контакта
    }
    private void initContactModification(ContactData contact) {
        click(By.cssSelector(String.format("[href='edit.php?id=%s']",contact.id())));
    }

    private void fillContactForm(ContactData contact) {//метод для изменения данных контакта
        type(By.name("firstname"), contact.firstname());
        type(By.name("lastname"), contact.lastname());
        type(By.name("middlename"), contact.middlename());
        type(By.name("nickname"), contact.nickname());

    }

    private void submitContactModification() {
        click(By.name("update"));
    }
    private void returnToHomePage() {
        click(By.linkText("home"));
    }

    public int getCount() {//метод для подсчета элементов
        openContactPresent();
        return manager.driver.findElements(By.name("selected[]")).size();     }


    public void removeAllContacts() {//метод для удаления всх контактов
        openContactPresent();
        selectAllContacts();//отметить галочкой все контакты на странице контактов
        removeSelectedContacts();
    }

    private void selectAllContacts() {
        var checkboxes=manager.driver.findElements(By.name("selected[]")); //метод, который находит много элеметов. Возвращает список. size()- возвращает размер списка
        for (var checkbox : checkboxes) {//цикл, который перебирает все элементы коллекции checkboxes
            checkbox.click();
        }
    }

    public List<ContactData> getList() {
        openContactPresent();//почему-то без неё тоже работает
        var contacts = new ArrayList<ContactData>();//цикл, который читает данные из ИБ, анализирует их и строит список. Создаем пустой список, в который будем складовать контакты
//        var tds = manager.driver.findElements(By.cssSelector("table.sortcompletecallback-applyZebra"));//получить со страницы список элементов, которые содержат информацию о контактах
        var tds = manager.driver.findElements(By.xpath("//table[@class='sortcompletecallback-applyZebra']/tbody/tr"));//получить со страницы список элементов, которые содержат информацию о контактах
        for (var row: tds) {//в цикле перебираем строки
            var cells = row.findElements(By.tagName("td"));//разбиваем строку на ячейки
            if (!cells.isEmpty()) {// только если вернули не пустой список ячеек td
                var firstname = cells.get(2).getText();//название контакта это текст, поэтому его получаем с помощью getText
                var lastname = cells.get(1).getText();
                var checkbox = cells.get(0).findElement(By.name("selected[]")); //найдем чекбокс, который находится внутри элемента td
                var id = checkbox.getAttribute("value");//получаем идентификатор
                contacts.add(new ContactData().withId(id).withFname(firstname).withLname(lastname));// в список контактов добавляем новый объект
            }
        }
        return contacts;
    }

    public void moveToPage() {
        click(By.linkText("home"));
    }

    public String getPhones(ContactData contact) {
        return manager.driver.findElement(By.xpath(
                String.format("//input[@id='%s']/../../td[6]",contact.id()))).getText();
    }

    public String getaddress(ContactData contact) {
        return manager.driver.findElement(By.xpath( //возвращаем текст
                String.format("//input[@id='%s']/../../td[4]",contact.id()))).getText();
    }

    public String getemail(ContactData contact) {
        return manager.driver.findElement(By.xpath( //возвращаем текст
                String.format("//input[@id='%s']/../../td[5]",contact.id()))).getText();
    }
}
