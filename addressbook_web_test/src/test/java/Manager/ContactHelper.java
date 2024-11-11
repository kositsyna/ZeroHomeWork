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

    //public void modifyContact(ContactData modifiedContact){
        //selectContact();
        //initContactModification();
      //  fillContactform(contact);
      //  submitContactModification();
      //  returnToMainPage();
   // }

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
        click(By.linkText("home page"));
    }


    }
