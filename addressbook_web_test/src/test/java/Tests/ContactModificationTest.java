package Tests;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class ContactModificationTest extends TestBase {
    @Test
    void canModifyContact(){
//        app.contacts().openContactPresent();
//        app.contacts().checkIsContact();
        if (app.hbm().getContactCount() == 0) //если количество контактов = 0, то сначала создаем новый, а потом изменяем его параметры
        {
            app.hbm().createContact(new ContactData("", "ejmdnsv", "skmajnss", "plssjnshs", "yrds", "fgfgf@ghg.ty","","", "gdsf", "", "", "+7854121541", "", ""));
        }
        var oldContacts = app.hbm().getContactList();
        var rand = new Random();
        var index = rand.nextInt(oldContacts.size());
        var testData = new ContactData().withFname("modified name");
        app.contacts().modifyContact(oldContacts.get(index), testData);
        var newContacts = app.hbm().getContactList();
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