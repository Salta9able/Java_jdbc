package peaksoft;

import peaksoft.dao.UserDaoJdbcImpl;
import peaksoft.model.User;
import peaksoft.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;


public class Main {


    public static void main(String[] args) {
        UserServiceImpl u = new UserServiceImpl();
        u.dropUsersTable();

        //User-лерге таблица тузуу
        u.createUsersTable();

        //4 user-ди базага кошуу. Ар бир user базага кошулгандан кийин
        //анын аты - базага кошулду деп консольго чыгуусу керек
        u.saveUser("Dow", "Jones", (byte) 55);
        u.saveUser("Fannie", "Mae", (byte) 60);
        u.saveUser("Freddie", "Mac", (byte) 30);
        u.saveUser("Roaring", "Kittie", (byte) 1);

        //Бардык user-лерди алуу жана консольго чыгаруу, toString
        //методун override кылышыныз керек
        List<User> l = u.getAllUsers();
        for (User user: l) {
            System.out.println(user.toString());
        }

        //Таблицанын маалыматтарын очуруу
        u.cleanUsersTable();

        //Таблицаны очуруу
        u.dropUsersTable();
    }

}
