package Tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase {

    @Test
    void testPhones() {
        var contacts = app.hbm().getContactList();
        var contact = contacts.get(0);
        var phones = app.contacts().getPhones(contact);
        var expected = Stream.of(contact.home(), contact.mobile(), contact.work(), contact.phone2()) //Склеиваем строчки, пустые пропускаеи
                .filter(s -> s != null && !"".equals(s))
                .collect(Collectors.joining("\n"));
        Assertions.assertEquals(expected, phones);
    }

    @Test
    void testAddress() {
        var contacts = app.hbm().getContactList();
        var contact = contacts.get(0);
        var address = app.contacts().getaddress(contact);
        var expected = contact.address();
        Assertions.assertEquals(expected, address);

    }

    @Test
    void testEmail() {
        var contacts = app.hbm().getContactList();
        var contact = contacts.get(0);
        var email = app.contacts().getemail(contact);
        var expected = Stream.of(contact.email(), contact.email2(), contact.email3()) //Склеиваем строчки, пустые пропускаеи
                .filter(s -> s != null && !"".equals(s))
                .collect(Collectors.joining("\n"));
        Assertions.assertEquals(expected, email);
    }
    @Test
    void allInOne(){//проверяем телефоны/адреса/email
        var contacts = app.hbm().getContactList();
        var contact = contacts.get(0);
        var phones = app.contacts().getPhones(contact);
        var address = app.contacts().getaddress(contact);
        var email = app.contacts().getemail(contact);

        var expectedPh = Stream.of(contact.home(),contact.mobile(),contact.work(),contact.phone2())
                .filter(s -> s != null && ! "".equals(s))
                .collect(Collectors.joining("\n"));

        var  expectedAdr = Stream.of(contact.address())
                .collect(Collectors.joining("\n"));

        var  expectedEm = Stream.of(contact.email(),contact.email2(),contact.email3())
                .filter(s -> s != null && ! "".equals(s))
                .collect(Collectors.joining("\n"));

//        var expected = Stream.of(contact.home(),
//                        contact.mobile(),
//                        contact.work(),
//                        contact.phone2(),
//                        contact.email(),
//                        contact.email2(),
//                        contact.email3(),
//                        contact.address().replace("\r", ""))
//                .filter(s->s !=null && !"".equals(s))
//                .collect(Collectors.joining("\n"));
//        var actual = Stream.of(phones, email, address)
//                .filter(s->s !=null && !"".equals(s))
//                .collect(Collectors.joining("\n"));
        Assertions.assertEquals(expectedPh,phones);
        Assertions.assertEquals(expectedAdr,address);
        Assertions.assertEquals(expectedEm,email);
    }

}

