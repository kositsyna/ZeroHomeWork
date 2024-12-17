package Tests;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static common.CommonFunctions.randomString;


public class ContactCreationTest extends TestBase {

    public static List<ContactData> contactProvider() throws IOException {//возвращает список объектов ContactData
        var result=new ArrayList<ContactData>();//инициализируем создаваемый список соответствующими значениями
        for (var firstname: List.of("","firstname")) {
                for (var lastname: List.of("","lastname")) {
                                result.add(new ContactData().withFname(firstname).withLname(lastname));
                                    }
            }
                for (int i=0; i<5; i++) {
            result.add(new ContactData()
                    .withFname(randomString(i*5))
                    .withLname(randomString(i*5)));//создание контакта
        }
        return result;
//        ObjectMapper mapper = new ObjectMapper();//прочитать данные из файла
//        var value = mapper.readValue(new File("contacts.json"), new TypeReference<List<ContactData>>() {});
//        result.addAll(value);//добавить все значения списка, которые были прочитаны из файла
//        return result;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")//провайдер тестовых данных, который генерирует данные
    public void CanCreateMultipleContacts(ContactData contact) {//создаем несколько контактов со случайным наименованием в адресной книге
        List<ContactData> oldContacts;//класс помощник для получения списка контактов
        oldContacts = app.contacts().getList();
        app.contacts().createContact(contact);//создание контакта
        var newContacts = app.contacts().getList();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));//сравниваем идентификаторы контактов, они не числа, а строки
        };
        newContacts.sort(compareById);
        var expectedList = new ArrayList<>(oldContacts);//строим копию списка oldContacts
        expectedList.add(contact.withId(newContacts.get(newContacts.size()-1).id()));
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts,expectedList);//проверка, которая сравнивает 2 списка ожидаемый и реальный
    }

    public static List<ContactData> negativeContactProvider() {//возвращает список объектов ContactData
        var result=new ArrayList<ContactData>(List.of(
                new ContactData ("", "","FName")));//инициализируем создаваемый список соответствующими значениями
        return result;
    }

    @ParameterizedTest
    @MethodSource ("negativeContactProvider")
    public void CanNotContact(ContactData contact) {
        var oldContacts = app.contacts().getList();
        app.contacts().createContact(contact);
        var newContacts = app.contacts().getList();
        Assertions.assertEquals(newContacts, oldContacts);
    }

}
