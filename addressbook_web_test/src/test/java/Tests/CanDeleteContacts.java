package Tests;
import model.ContactData;
import Tests.TestBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Random;


public class CanDeleteContacts extends TestBase {

    @Test
    public void canRemoveContact() {
       // app.contacts().openContactPresent();
        if (app.hbm().getContactCount() == 0) //если количество контактов = 0, то сначала создаем новый, а потом изменяем его параметры
        {
            app.hbm().createContact(new ContactData("", "mimim", "lalal", "ninin", "adadad", "erer@sdd.ry","","", "fjsknf", "", "", "+788598745125", "", ""));
        }
        var oldContacts = app.hbm().getContactList();
        var rand = new Random();
        var index = rand.nextInt(oldContacts.size());
        app.contacts().moveToPage();
        app.contacts().removeContact(oldContacts.get(index));
        var newContacts = app.hbm().getContactList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.remove(index);
        Assertions.assertEquals(newContacts, expectedList);
    }
}
