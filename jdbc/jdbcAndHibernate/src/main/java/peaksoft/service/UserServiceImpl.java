package peaksoft.service;

import peaksoft.Main;
import peaksoft.dao.UserDaoJdbcImpl;
import peaksoft.model.User;
import peaksoft.util.*;

import java.sql.Connection;
import java.util.List;

public class UserServiceImpl implements UserService {
    UserDaoJdbcImpl u = new UserDaoJdbcImpl();

    public UserServiceImpl(){

    }

    public void createUsersTable() {
        u.createUsersTable();
    }

    public void dropUsersTable() {
        u.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        u.saveUser(name,lastName,age);
    }

    public void removeUserById(long id) {
        u.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return u.getAllUsers();
    }

    public void cleanUsersTable() {
        u.cleanUsersTable();
    }
}
