package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.sql.*;

/**
 * Created by kuanysh on 11.04.16.
 */
public class DbConnectionTest {

    @Test
    public void testDbConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?user=root&password=");

            //создаем объект для извлечения данных из БД
            Statement st = conn.createStatement();

            // извлекаем сет строк таблицы
            ResultSet resultSet = st.executeQuery("select group_id, group_name, group_header, group_footer from group_list");

            Groups groups = new Groups();

            // пробегаем по множеству результатов, каждый шаг resultSet - это указатель на одну строку таблицы
            while (resultSet.next()) {
                groups.add(new GroupData().withId(resultSet.getInt("group_id")).withName(resultSet.getString("group_name")).
                        withHeader(resultSet.getString("group_header")).withFooter(resultSet.getString("group_footer")));
            }
            resultSet.close();
            st.close();
            conn.close();

            System.out.println(groups);
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
