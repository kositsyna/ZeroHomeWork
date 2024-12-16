package Tests;
import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class ContactModificationTest extends TestBase {
    @Test
    void canModifyContact(){
        app.contacts().openContactPresent();
        app.contacts().checkIsContact();
        var oldContacts = app.contacts().getList();
        var rand = new Random();
        var index = rand.nextInt(oldContacts.size());
        var testData = new ContactData().withFname("modified name");
        app.contacts().modifyContact(oldContacts.get(index), testData);
        var newContacts = app.contacts().getList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.set(index, testData.withId(oldContacts.get(index).id()));
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts,expectedList);

    }
}