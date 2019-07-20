package com.example.rentndrive.data;

import android.os.StrictMode;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.example.rentndrive.ui.login.LoginActivity;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {
    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

    Connection connectionMySQL;

    public Boolean login(String username, String password) {

            // TODO: handle loggedInUser authentication

            boolean connectionClosed=false;

            StrictMode.setThreadPolicy(policy);
            try {
                connectBDMySQL();
                connectionClosed = connectionMySQL.isClosed();
            }catch (ClassNotFoundException e){
                return false;
            }catch (SQLException e){
                return false;
            }catch (RuntimeException e){
                return false;
            }


            if(!connectionClosed){

                return true;
            }
            return false;
    }

    public void logout() {
        // TODO: revoke authentication
    }

    public void connectBDMySQL() throws ClassNotFoundException, SQLException {
        String user="panikos";
        String password="rentndrive";
        String ip="192.168.10.108";
        String port="3306";
        String db="rentndrive";

        if (connectionMySQL == null)
        {
            String urlConexionMySQL = "";

            if (db!= "")
                urlConexionMySQL = "jdbc:mysql://" + ip + ":" + port+ "/" + db;
            else
                urlConexionMySQL = "jdbc:mysql://" + ip + ":" + port;
            if (user!= "" & ip != "" & port!= "")//& password!= ""
            {
                try
                {
                    Class.forName("com.mysql.jdbc.Driver");
                    connectionMySQL = DriverManager.getConnection(urlConexionMySQL,
                            user, password);
                }
                catch (ClassNotFoundException e)
                {
                    Toast.makeText(LoginActivity.context,
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                    throw e;
                }
                catch (SQLException e)
                {
                    Toast.makeText(LoginActivity.context,
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                    throw e;
                }
            }
        }
    }
}
