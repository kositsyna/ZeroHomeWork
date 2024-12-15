package Tests;
import model.ContactData;
import org.junit.jupiter.api.Test;


public class CanDeleteContacts extends TestBase {

  @Test
  public void canDeleteContacts() {

    if (!app.contacts().isContactPresent())
    {
      app.contacts().createContact(new ContactData());
    }
    app.contacts().removeContact();

  }
}
