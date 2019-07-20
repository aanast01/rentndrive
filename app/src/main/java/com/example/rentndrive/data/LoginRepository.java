package com.example.rentndrive.data;

import com.example.rentndrive.data.model.LoggedInUser;

import java.sql.SQLException;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of com.example.rentndrive.login status and user credentials information.
 */
public class LoginRepository {

    private static volatile LoginRepository instance;

    private LoginDataSource dataSource;

    // If user credentials will be cached in local storage, it is recommended it be encrypted
    // @see https://developer.android.com/training/articles/keystore
    private LoggedInUser user = null;

    // private constructor : singleton access
    private LoginRepository(LoginDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static LoginRepository getInstance(LoginDataSource dataSource) {
        if (instance == null) {
            instance = new LoginRepository(dataSource);
        }
        return instance;
    }

    public boolean isLoggedIn() {
        return user != null;
    }

    public void logout() {
        user = null;
        try {
            dataSource.logout();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setLoggedInUser(LoggedInUser user) {
        this.user = user;
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }

    public boolean login(String username, String password) {
        // handle com.example.rentndrive.login
        Boolean result = dataSource.login(username, password);
        if (result == true) {
            setLoggedInUser(new LoggedInUser(username, LoginDataSource.fullName));
            return true;
        }
        return false;
    }
}
