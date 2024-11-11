package Manager;
import model.ContactData;
import org.openqa.selenium.By;

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
        type(By.name("middlename"),contact.middlename());
        type(By.name("lastname"),contact.lastname());
        type(By.name("nickname"),contact.nickname());
    }

    public void submitContactCreation()
    {
        click(By.name("submit"));
    }

    public void returnToMainPage(){
        click(By.linkText("home"));
    }

    }
