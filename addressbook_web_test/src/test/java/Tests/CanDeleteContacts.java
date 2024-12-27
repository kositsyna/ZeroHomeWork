package Tests;
import common.CommonFunctions;
import model.ContactData;
import Tests.TestBase;
import model.GroupData;
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

    @Test
    public void canDelContactFromGroup() {//исключить контакт из группы
        if (app.hbm().getGroupCount()==0) {
            var group = new GroupData()
                    .withName(CommonFunctions.randomString(10))
                    .withHeader(CommonFunctions.randomString(20))
                    .withFooter(CommonFunctions.randomString(30));
            app.groups().createGroup(group);//создаём группу
        }
        if (app.hbm().getContactCount()==0) {
            var contact = new ContactData()
                    .withLname(CommonFunctions.randomString(10))
                    .withFname(CommonFunctions.randomString(10))
                    .withMname(CommonFunctions.randomString(10));
            app.contacts().createContact(contact);//создание контакта
        }
        app.contacts().returnToHomePage();
        var contact = app.contacts().getList().get(0);
        var group = app.groups().getList().get(0);
        app.contacts().returnToHomePage();
        app.contacts().addContactInGroup(contact, group);
        app.contacts().returnToHomePage();
        var oldContacts = app.hbm().getContactsInGroup(group);
        var rnd=new Random();
        var index = rnd.nextInt(oldContacts.size());
        app.contacts().returnToHomePage();
        app.contacts().removeContactFromGroup(oldContacts.get(index), group);
        var newContacts = app.hbm().getContactsInGroup(group);
        Assertions.assertEquals(newContacts.size()+1,oldContacts.size());
    }
}
