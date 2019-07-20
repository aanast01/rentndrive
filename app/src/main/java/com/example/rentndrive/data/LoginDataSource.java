package com.example.rentndrive.data;

import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.rentndrive.data.model.LoggedInUser;
import com.example.rentndrive.ui.login.LoginActivity;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {
    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

    Connection conexionMySQL;

    public Result<LoggedInUser> login(String username, String password) {

        try {
            // TODO: handle loggedInUser authentication
            LoggedInUser fakeUser =
                    new LoggedInUser(
                            java.util.UUID.randomUUID().toString(),
                            "Jane Doe");


            StrictMode.setThreadPolicy(policy);
            connectBDMySQL();

            return new Result.Success<>(fakeUser);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }

    public void connectBDMySQL()
    {
        String user="root";
        String password="";
        String ip="192.168.10.88";
        String port="3306";
        String db="rentndrive";

        if (conexionMySQL == null)
        {
            String urlConexionMySQL = "";
            if (db!= "")
                urlConexionMySQL = "jdbc:mysql://" + ip + ":" + port+ "/" + db;
            else
                urlConexionMySQL = "jdbc:mysql://" + ip + ":" + port;
            if (user!= "" & password!= "" & ip != "" & port!= "")
            {
                try
                {
                    Class.forName("com.mysql.jdbc.Driver");
                    conexionMySQL = DriverManager.getConnection(urlConexionMySQL,
                            user, password);
                }
                catch (ClassNotFoundException e)
                {
                    Toast.makeText(LoginActivity.context,
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
                catch (SQLException e)
                {
                    Toast.makeText(LoginActivity.context,
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
