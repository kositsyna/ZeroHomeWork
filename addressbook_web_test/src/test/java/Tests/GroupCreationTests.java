package Tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.CommonFunctions;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {


    public static List<GroupData> groupProvider() throws IOException { //метод возвращает список объектов типа груп дата
        var result = new ArrayList<GroupData>();
//        for(var name:List.of("","group name")){
//            for(var header:List.of("","group header")){
//                for(var footer:List.of("","group footer")){
//                    result.add(new GroupData().withName(name).withFooter(footer).withHeader(header));
//                }
//            }
//        }
        ObjectMapper mapper = new ObjectMapper();
      var value = mapper.readValue(new File("groups.json"),new TypeReference<List<GroupData>>(){});
      result.addAll(value);
        return result;
    }
    public static List<GroupData> singleRandomGroup() { //создание одной группы
        return List.of(new GroupData()
                .withName(CommonFunctions.randomString(10))
                .withHeader(CommonFunctions.randomString(20))
                .withFooter(CommonFunctions.randomString(30)));

    }

    @ParameterizedTest
    @MethodSource("singleRandomGroup")  //генератор тестовых данных для групп
    public void CanCreateGroup(GroupData group) {
        var oldGroups= app.jdbc().getGroupList(); //получаем список групп перед удалением объекта
        app.groups().createGroup(group);
        var newGroups = app.jdbc().getGroupList();
        Comparator<GroupData> compareById = (o1, o2) -> {  //переменная для сортировки списков
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroups.sort(compareById);
        var expectedList = new ArrayList<>(oldGroups); // загружаем список групп после модификации
        var maxId = newGroups.get(newGroups.size()-1).id();
        expectedList.add(group.withId(maxId)); //
        expectedList.sort(compareById);
        Assertions.assertEquals(newGroups,expectedList); //сравнение списков
        
    }

    public static List<GroupData> negativeGroupProvider() { //негативные данные
        var result = new ArrayList<GroupData>(List.of(
                new GroupData("", "group name '","","")
        ));
        return result;
    }
    @ParameterizedTest
    @MethodSource("negativeGroupProvider")  //генератор тестовых данных для групп
    public void canNotCreateGroup(GroupData group) {
        var oldGroups= app.groups().getList(); //получаем список групп перед удалением объекта
        app.groups().createGroup(group);
        var newGroups = app.groups().getList();
        Assertions.assertEquals(newGroups,oldGroups); //сравнение списков
    }
}
