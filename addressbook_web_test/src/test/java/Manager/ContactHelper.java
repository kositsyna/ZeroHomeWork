package Manager;
import model.ContactData;

public class ContactHelper extends HelperBase {

    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }
    public void createContact(ContactData group) {
        initContact;
        fillContactform(group);
        submitGroupCreation();
        returnToGroupsPage();
    }
}
