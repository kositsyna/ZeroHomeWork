package Tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {


    public static List<GroupData> groupProvider() { //метод возвращает список объектов типа груп дата
        var result = new ArrayList<GroupData>();
        for(var name:List.of("","group name")){
            for(var header:List.of("","group header")){
                for(var footer:List.of("","group footer")){
                    result.add(new GroupData().withName(name).withFooter(footer).withHeader(header));
                }
            }
        }
        for (int i = 0 ; i < 5; i++){
            result.add(new GroupData().withName(randomString(i*2))
                                      .withFooter(randomString(i*2))
                                      .withHeader(randomString(i*2)));
        }
        return result;
    }

    @ParameterizedTest
    @MethodSource("groupProvider")  //генератор тестовых данных для групп
    public void CanCreateMultipleGroups(GroupData group) {
        var oldGroups= app.groups().getList(); //получаем список групп перед удалением объекта
        app.groups().createGroup(group);
        var newGroups = app.groups().getList();
        Comparator<GroupData> compareById = (o1, o2) -> {  //переменная для сортировки списков
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroups.sort(compareById);
        var expectedList = new ArrayList<>(oldGroups); // загружаем список групп после модификации
        expectedList.add(group.withId(newGroups.get(newGroups.size()-1).id()).withHeader("").withFooter("")); //
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
