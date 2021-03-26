package peaksoft.dao;

import peaksoft.Main;
import peaksoft.model.User;
import peaksoft.util.*;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class UserDaoJdbcImpl implements UserDao {


    Util utl = new Util();
    Connection connection = utl.connect();
    List<User> list = new ArrayList<>();


    public UserDaoJdbcImpl() {
    }

    public void createUsersTable() {

        final String SQLdef = "CREATE TABLE IF NOT EXISTS users(id SERIAL, name VARCHAR(100), lastName VARCHAR(100), age INT )";
        try (
                Statement stm = connection.createStatement();
                ) {
                    stm.execute(SQLdef);
                    System.out.println("table has been created");
        }
        catch(SQLException e) {
            System.out.println("oh no");
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {

        final String SQLdef = "DROP TABLE IF EXISTS users";
        try (
                Statement stm = connection.createStatement();
        ) {
            stm.execute(SQLdef);
            System.out.println("table has been deleted");
            list.clear();
        }
        catch(SQLException e) {
            System.out.println("oh no");
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        String SQLmanipulation = "INSERT INTO users(name,lastName,age) VALUES(?,?,?)";
        try (
                PreparedStatement stm = connection.prepareStatement(SQLmanipulation);
                ){

            stm.setString(1, name);
            stm.setString(2, lastName);
            stm.setByte(3, age);
            stm.executeUpdate();
            System.out.println("user added: " + name + " " + lastName);
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String SQLmanipulation = "DELETE FROM users WHERE users.id = ?";

        try (
                PreparedStatement stm = connection.prepareStatement(SQLmanipulation);
                ){
            stm.setLong(1,id);
            stm.executeUpdate();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {

        String SQLquery = "SELECT * FROM users";

        try(
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(SQLquery);
        ){
            while(rs.next()){
                long id = rs.getLong("id");
                String name = rs.getString("name");
                String lastName = rs.getString("lastName");
                byte age = rs.getByte("age");
                User user = new User(name, lastName, age);
                user.setId(id);
                list.add(user);
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {
        String SQLmanipulation = "DELETE FROM users";

        try (
                PreparedStatement stm = connection.prepareStatement(SQLmanipulation);
        ){
            stm.executeUpdate();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }
}