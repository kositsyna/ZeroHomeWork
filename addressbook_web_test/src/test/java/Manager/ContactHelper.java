//package Manager;
//import model.ContactData;
//import org.openqa.selenium.By;
//import java.util.ArrayList;
//import java.util.List;
//
//
//public class ContactHelper extends HelperBase {
//
//    public ContactHelper(ApplicationManager manager) {
//        super(manager);
//    }
//    private void fillContactForm(ContactData contact) {//метод для изменения данных контакта
//        type(By.name("firstname"), contact.firstname());
//        type(By.name("lastname"), contact.lastname());
//        attach(By.name("photo"), contact.photo());
//    }
//
//    public void createContact(ContactData contact) {
//        openContactPage();
//        initContactCreation();
//        fillContactForm(contact);
//        submitContactCreation();
//        returnToMainPage();
//    }
//
//    public void removeContact(ContactData contact) {
//        selectContact();
//        removeSelectedContact();
//        returnToMainPage();
//    }
//
//    public boolean isContactPresent() {
//        return manager.isElementPresent(By.name("selected[]"));
//    }
//
//    public void openContactPresent() {
//        if (manager.isElementPresent(By.xpath("(//input[@name=\'Delete\'])"))) {
//            click(By.linkText("home"));
//        }
//    }
//
//    public void openContactPage() {//открыть страницу создания нового контакта
//        if (!manager.isElementPresent(By.xpath("(//input[@name=\'submit\'])"))) {//если на странице есть кнопка submit (ENTER), то никакой переход делать не требуется
//            initContactCreation();
//        }
//    }
//
//    public void checkIsContact() { // если на странице нет контактов, то создадим
//        if (!manager.isElementPresent(By.name("selected[]"))) {
//            createContact(new ContactData("", "lastname99", "firstname88",""));//вызов метода создания контакта
//        }
//    }
//
//    /*public void fillContactform(ContactData contact){
//        type(By.name("firstname"),contact.firstname());
//        type(By.name("lastname"),contact.lastname());
//    }*/
//
//
//    public void modifyContact(ContactData contact, ContactData modifiedContact) {//метод для модификации контакта
//        selectContact();//выбрать контакт (отметить галочкой)
//        initContactModification(contact);//нажать кнопку модификации Edit
//        fillContactForm(modifiedContact);//заполнить форму данными, которые содержатся в переданном объекте
//        submitContactModification();//сохраняем форму  по кнопке Update
//        returnToMainPage();//возврат на страницу контактов
//    }
//
//   public void selectContact() {
//        click(By.name("selected[]"));
//    }
//
////    private void selectContact(ContactData contact) {
////        click(By.cssSelector(String.format("input[value='%s']",contact.id())));// выбор контакта
////    }
//
//    public void removeSelectedContact() {
//        click(By.xpath("//input[@value=\'Delete\']"));
//    }
//
//    public void initContactCreation() {
//        click(By.linkText("add new"));
//    }
//
//    private void initContactModification(ContactData contact) {
//        click(By.cssSelector(String.format("edit.php",contact.id())));
//    }
//
//
//    public void submitContactCreation() {
//        click(By.name("submit"));
//    }
//
//    public void submitContactModification() {
//        click(By.name("update"));
//    }
//
//    public void returnToMainPage() {
//        click(By.linkText("home page")); // <a href="index.php">home page</a>
//    }
//
//    public int getCount() {
//        returnToMainPage();
//        return manager.driver.findElements(By.name("selected[]")).size(); // возвращаем количество групп
//
//    }
//
//    public List<ContactData> getList() {
//        var contacts = new ArrayList<ContactData>();// Создаем пустой список для контактов
//        var tds = manager.driver.findElements(By.xpath("//table[@class='sortcompletecallback-applyZebra']/tbody/tr"));//получить со страницы список элементов, которые содержат информацию о контактах
//        for (var row : tds) {
//            var cells = row.findElements(By.tagName("td"));
//            if (!cells.isEmpty()) {
//                var firstname = cells.get(2).getText();
//                var lastname = cells.get(1).getText();
//                var checkbox = cells.get(0).findElement(By.name("selected[]"));
//                var id = checkbox.getAttribute("value");
//                contacts.add(new ContactData().withId(id).withFname(firstname).withLname(lastname));// в список контактов добавляем новый объект
//            }
//        }
//        return contacts;
//    }
//
//}


package manager;

import Manager.ApplicationManager;
import Manager.HelperBase;
import model.ContactData;
import model.GroupData;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

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

    public void modifyContact(ContactData contact, ContactData modifiedContact) {//метод для модификации контакта
        selectContact(contact);//выбрать контакт (отметить галочкой)
        initContactModification(contact);//нажать кнопку модификации Edit
        fillContactForm(modifiedContact);//заполнить форму данными, которые содержатся в переданном объекте
        submitContactModification();//сохраняем форму  по кнопке Update
        returnToHomePage();//возврат на страницу контактов
    }

    public void removeContact(ContactData contact) {//метод по удалению контакта
        selectContact(contact);//выбираем контакт
        removeSelectedContacts();//удаляем контакт
        returnToHomePage();//возвращаемся на страницу с контактами
    }

    private void submitContactCreation() {//сохранение данных по контакту
        click(By.xpath("(//input[@name=\'submit\'])"));//driver.findElement(By.xpath("(//input[@name=\'submit\'])[2]")).click();
    }

    private void initContactCreation() {//метод по открытию формы с новым контактом
        click(By.linkText("add new"));
    }

    private void removeSelectedContacts() {//удаление контакта
        click(By.xpath("//input[@value=\'Delete\']"));
    }

    public void checkIsContact() { // если на странице нет контактов, то создадим
        if (!manager.isElementPresent(By.name("selected[]"))) {
            createContact(new ContactData("","lastname78", "firstname74"));//вызов метода создания контакта
        }
    }
    private void selectContact(ContactData contact) {
        click(By.cssSelector(String.format("input[value='%s']",contact.id())));// выбор контакта
    }
    private void initContactModification(ContactData contact) {
        click(By.cssSelector(String.format("[href='edit.php?id=%s']",contact.id())));//выбор карандаша для редактирования контакта

    }
    private void fillContactForm(ContactData contact) {//метод для изменения данных контакта
        type(By.name("firstname"), contact.firstname());
        type(By.name("lastname"), contact.lastname());
    }

    private void submitContactModification() {
        click(By.name("update"));
    }
    private void returnToHomePage() {
        click(By.linkText("home"));
    }

    public int getCount() {//метод для подсчета элементов
        openContactPresent();
        return manager.driver.findElements(By.name("selected[]")).size(); //метод, который находит много элеметов. Возвращает список. size()- возвращает размер списка
    }

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
}
