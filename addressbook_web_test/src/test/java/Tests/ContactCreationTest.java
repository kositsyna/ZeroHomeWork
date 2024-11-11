package Tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactCreationTest extends TestBase {

  @Test
  public void canCreateContact() {
    app.contacts().createContact(new ContactData("first","middle","last","nick"));
  }
}
