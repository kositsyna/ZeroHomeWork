package Tests;

import common.CommonFunctions;
import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTest extends TestBase {
    public static List<ContactData> contactProvider() {//возвращает список объектов ContactData
        var result = new ArrayList<ContactData>();//инициализируем создаваемый список соответствующими значениями
        for (var firstname : List.of("", "firstname")) {//цикл, который перебирает два возможных значения (пустая и НЕ пустая строка)
            for (var lastname : List.of("", "lastname")) {
                for (var photo : List.of("", "photo")) {
                    {//для каждой пары перебираем возможные значения lastname
                        {
                            result.add(new ContactData().withFname(firstname).withLname(lastname).withPhoto(photo));//добавляем значение в список генерируемых объектов. Идентификаторов пока нет
                        }
                    }
                }
                for (int i = 0; i < 5; i++) {
                    result.add(new ContactData()
                            .withFname(CommonFunctions.randomString(i * 10))
                            .withLname(CommonFunctions.randomString(i * 10))
                            .withPhoto(randomFile("src/test/resources/images")));//создание контакта. В качестве наименование будет рандомное randomString длины i*10
                }

            }
        }
        return result;
    }


    @ParameterizedTest
    @MethodSource("contactProvider")
    public void CanCreateMultipleContacts(ContactData contact) {//создаем несколько контактов со случайным наименованием в адресной книге
        var oldContacts = app.contacts().getList();//класс помощник для получения списка контактов
        app.contacts().createContact(contact);//создание контакта. В качестве наименование будет рандомное randomString длины i*10
        var newContacts = app.contacts().getList();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));//сравниваем идентификаторы контактов, они не числа, а строки
        };
        newContacts.sort(compareById);
        var expectedList = new ArrayList<>(oldContacts);//строим копию списка oldContacts
        expectedList.add(contact.withId(newContacts.get(newContacts.size()-1).id()));
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);//проверка, которая сравнивает 2 списка ожидаемый и реальный
    }

 /* public static List<ContactData> contactProvider() { //метод возвращает список объектов типа контакт дата
    var result = new ArrayList<ContactData>();
      for (var first : List.of("", "contact first name")) {
          for (var last : List.of("", "contact last name")) {
              result.add(new ContactData().withFname(first).withLname(last));
                      }
        }
    for (int i = 0 ; i < 5; i++){
      result.add(new ContactData().
              withFname(randomString(i*2)).
              withLname(randomString(i*2)));
    }
    return result;
  }

  @ParameterizedTest
  @MethodSource("contactProvider")//провайдер тестовых данных, который генерирует данные с фикс значениями или сгенерированными
  public void CanCreateMultipleContacts(ContactData contact) {//создаем несколько контактов со случайным наименованием в адресной книге
    var oldContacts = app.contacts().getList();//класс помощник для получения списка контактов
    app.contacts().createContact(contact);//создание контакта. В качестве наименование будет рандомное randomString длины i*10
    var newContacts = app.contacts().getList();
    Comparator<ContactData> compareById = (o1, o2) -> {
      return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));//сравниваем идентификаторы контактов, они не числа, а строки
    };
    newContacts.sort(compareById);
    var expectedList = new ArrayList<>(oldContacts);//строим копию списка oldContacts
    expectedList.add(contact.withId(newContacts.get(newContacts.size()-1).id()).withFname("").withLname(""));
    expectedList.sort(compareById);
    Assertions.assertEquals(newContacts,expectedList);//проверка, которая сравнивает 2 списка ожидаемый и реальный
  }

  /*@ParameterizedTest
  @MethodSource("contactProvider")
  public void canCreateMultiContact(ContactData contact) {
    var oldContacts = app.contacts().getList();
    app.contacts().createContact(contact);
    var newContacts = app.contacts().getList();
    Comparator<ContactData> compareById = (o1, o2) -> {
      return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));//сравниваем идентификаторы контактов, они не числа, а строки
    };
    newContacts.sort(compareById);
    var expectedList = new ArrayList<>(oldContacts);// загружаем список контактов после модификации
    expectedList.add(contact.withId(newContacts.get(newContacts.size()-1).id()).withFname("").withLname(""));
    expectedList.sort(compareById);
    Assertions.assertEquals(newContacts,expectedList); //сравнение списков
  }*/

}

