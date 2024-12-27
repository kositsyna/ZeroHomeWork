package Tests;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.CommonFunctions;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static common.CommonFunctions.randomString;


public class ContactCreationTest extends TestBase {

    public static List<ContactData> contactProvider() throws IOException {//возвращает список объектов ContactData
        var result=new ArrayList<ContactData>();//инициализируем создаваемый список соответствующими значениями
//        for (var firstname: List.of("","firstname")) {
//                for (var lastname: List.of("","lastname")) {
//                                result.add(new ContactData().withFname(firstname).withLname(lastname));
//                                    }
//            }
//                for (int i=0; i<5; i++) {
//            result.add(new ContactData()
//                    .withFname(randomString(i*5))
//                    .withLname(randomString(i*5)));//создание контакта
//        }
//        return result;
        var json = "";
        try (var reader = new FileReader("contacts.json"); //читаем файл построчно
            var breader = new BufferedReader(reader)){
           var line = breader.readLine();
           while (line != null){
               json = json + line;
               line = breader.readLine();
           }

        }
        //var json = Files.readString(Paths.get("contacts.json")); //за один вызов вычитываем весь файл
        ObjectMapper mapper = new ObjectMapper();//прочитать данные из файла
        var value = mapper.readValue(json, new TypeReference<List<ContactData>>() {}); //анализируем содержимое прочитанного файла
        result.addAll(value);//добавить все значения списка, которые были прочитаны из файла
        return result;
    }

    public static List<ContactData> singleRandomContact() {
        return List.of(new ContactData()
                .withMname(CommonFunctions.randomString(20))
                .withFname(CommonFunctions.randomString(10))
                .withLname(CommonFunctions.randomString(12))
                .withNname(CommonFunctions.randomString(18)));
    }

        @ParameterizedTest
    @MethodSource("singleRandomContact")//провайдер тестовых данных, который генерирует данные
    public void CanCreateMultipleContacts(ContactData contact) {//создаем несколько контактов со случайным наименованием в адресной книге
        var oldContacts = app.hbm().getContactList();
        app.contacts().createContact(contact);//создание контакта
        var newContacts = app.hbm().getContactList();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));//сравниваем идентификаторы контактов, они не числа, а строки
        };
        newContacts.sort(compareById);
        var expectedList = new ArrayList<>(oldContacts);//строим копию списка oldContacts
        expectedList.add(contact.withId(newContacts.get(newContacts.size()-1).id()));
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts,expectedList);//проверка, которая сравнивает 2 списка ожидаемый и реальный
    }

    @Test
    void canCreateContact(){
        var contact = new ContactData()
                .withFname(CommonFunctions.randomString(10))
                .withLname(CommonFunctions.randomString(11))
                .withNname(CommonFunctions.randomString(12))
                .withMname(CommonFunctions.randomString(13));
        app.contacts().createContact(contact);
    }
    @Test

    void canCreateContactInGroup(){
        var contact = new ContactData()
                .withFname(CommonFunctions.randomString(10))
                .withLname(CommonFunctions.randomString(11))
                .withNname(CommonFunctions.randomString(12))
                .withMname(CommonFunctions.randomString(13));
        if (app.hbm().getGroupCount() == 0) //Если количество групп = 0, то сначала создаем новую
        {
            app.hbm().createGroup(new GroupData("", "", "", ""));
        }
        var group= app.hbm().getGroupList().get(0);
        var oldRelated = app.hbm().getContactsInGroup(group);
        app.contacts().createContact2(contact,group);
        var newRelated = app.hbm().getContactsInGroup(group);
        Assertions.assertEquals(oldRelated.size()+1,newRelated.size());

    }

    @Test
    public void canAddContactInGroup() {
        if (app.hbm().getContactCount()==0) {
            var contact = new ContactData()//создаем новый контакт
                    .withLname(CommonFunctions.randomString(10))
                    .withFname(CommonFunctions.randomString(10))
                    .withMname(CommonFunctions.randomString(10));
            app.contacts().createContact(contact);
        }
        if (app.hbm().getGroupCount()==0) {
            var group = new GroupData()
                    .withName(CommonFunctions.randomString(10))
                    .withHeader(CommonFunctions.randomString(20))
                    .withFooter(CommonFunctions.randomString(30));
            app.groups().createGroup(group);//создаём новую группу
        }
        app.contacts().returnToHomePage();
        var contact = app.contacts().getList().get(0);  //выбираем контакт
        var group = app.groups().getList().get(0);      // выбираем группу
        var oldRelated = app.hbm().getContactsInGroup(group);
        if (app.contacts().isContactInGroup(oldRelated, contact)) {
            app.contacts().returnToHomePage();
            app.contacts().removeContactFromGroup(contact, group);
            app.groups().openGroupsPage();
            app.contacts().returnToHomePage();
            oldRelated = app.hbm().getContactsInGroup(group);
        }
        app.contacts().returnToHomePage();
        app.contacts().addContactInGroup(contact, group); // добавление контакта в группу
        var newRelated = app.hbm().getContactsInGroup(group);
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newRelated.sort(compareById);
        oldRelated.add(contact);
        oldRelated.sort(compareById);
        Assertions.assertEquals(oldRelated, newRelated);
    }



}
