package Manager;

import model.GroupData;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcHelper extends HelperBase {

    public JdbcHelper(ApplicationManager manager) {
        super(manager);
    }

    public List<GroupData> getGroupList() {
        var groups = new ArrayList<GroupData>();
        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook","root","");
             var statement = conn.createStatement(); //Соединяемся с БД
             var result = statement.executeQuery("SELECT group_id, group_name, group_header, group_footer FROM group_list");) //выполняем запрос к таблице
        {
            while (result.next()){ //анализируем запрос
              groups.add(new GroupData()
                      .withId(result.getString("group_id"))
                      .withName(result.getString("group_name"))
                      .withHeader(result.getString("group_header"))
                      .withFooter(result.getString("group_footer")));

          }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return groups;
    }
}
