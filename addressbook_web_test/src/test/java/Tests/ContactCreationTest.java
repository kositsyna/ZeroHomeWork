package Tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.List;

public class ContactCreationTest extends TestBase {

  public static List<ContactData> contactProvider() { //метод возвращает список объектов типа контакт дата
    var result = new ArrayList<ContactData>();
    for(var first:List.of("","conatct first name")){
      for(var middle:List.of("","contact middle name")){
        for(var last:List.of("","contact last name")){
          for (var nick:List.of("","contact nickname")) {
            result.add(new ContactData(first,middle,last,nick));
          }
        }
      }
    }
    for (int i = 0 ; i < 5; i++){
      result.add(new ContactData(randomString(i*2),randomString(i*2),randomString(i*2)));
    }
    return result;
  }

  @ParameterizedTest
  @MethodSource("contactProvider")
  public void canCreateMultiContact(ContactData contact) {
    int contactCount = app.contacts().getCount();
    app.contacts().createContact(contact);
    int newContactCount = app.contacts().getCount();
    Assertions.assertEquals(contactCount +1, newContactCount);
  }

  }
