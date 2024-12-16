package Tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;


public class CanDeleteContacts extends TestBase {

    @Test
    public void canRemoveContact() {
        app.contacts().openContactPresent();
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(new ContactData("", "lastname1", "firstname1"));//вызов метода создания контакта
        }
        var oldContacts = app.contacts().getList();
        var rand = new Random();
        var index = rand.nextInt(oldContacts.size());
        app.contacts().removeContact(oldContacts.get(index));
        var newContacts = app.contacts().getList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.remove(index);
        Assertions.assertEquals(newContacts, expectedList);
    }
}
