package Tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
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
        int groupCount = app.groups().getCount(); // считаем количество уже созданных групп
        app.groups().createGroup(group);
        int newgroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount + 1,newgroupCount);
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
        int groupCount = app.groups().getCount(); // считаем количество уже созданных групп
        app.groups().createGroup(group);
        int newgroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount,newgroupCount);
    }
}
