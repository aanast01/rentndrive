package com.example.rentndrive.data;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.widget.Toast;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.rentndrive.ui.login.LoginActivity;

/**
 * Class that handles authentication w/ com.example.rentndrive.login credentials and retrieves user information.
 */
public class LoginDataSource {
    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

    public static Connection connectionMySQL;
    public static String fullName="";
    public static Blob userBlob=null;
    public static Bitmap userPic=null;

    public static int getClientPhone() {
        return clientPhone;
    }

    public static int clientPhone;


    public Boolean login(String username, String password) {

            // TODO: handle loggedInUser authentication

            boolean connectionClosed=false;
            boolean success=false;

            StrictMode.setThreadPolicy(policy);
            try {

                success = connectBDMySQL(username, password);
                connectionClosed = connectionMySQL.isClosed();
            }catch (ClassNotFoundException e){
                return false;
            }catch (SQLException e){
                return false;
            }catch (RuntimeException e){
                return false;
            }


            if(success){
                return true;
            }
            return false;
    }

    public static void logout() throws SQLException {
        fullName="";
        userBlob=null;
        userPic=null;
        connectionMySQL.close();
        connectionMySQL=null;
    }

    public Boolean connectBDMySQL(String email, String userPass) throws ClassNotFoundException, SQLException {
        String user="panikos";
        String password="rentndrive";
        String ip="MinasPC";
        String port="3306";
        String db="rentndrive";

        if (connectionMySQL == null)
        {
            String urlConexionMySQL = "";
            Boolean flag=false;

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

                    if (connectionMySQL ==null){
                        Toast.makeText(LoginActivity.context, "Connection Error", Toast.LENGTH_LONG);
                        return false;
                    }
                    String name="";
                    Statement stmt = connectionMySQL.createStatement();
                    ResultSet rs = stmt.executeQuery("select * from clients where email = '" + email + "' and password = '" + userPass + "';");
                    while(rs.next()) {
                        fullName = rs.getString(1);
                        fullName += " " + rs.getString(2);
                        userBlob  = rs.getBlob(4);
                        clientPhone = rs.getInt(3);
                        int blobLength = (int) userBlob.length();
                        byte[] blobAsBytes = userBlob.getBytes(1, blobLength);
                        userPic = BitmapFactory.decodeByteArray(blobAsBytes,0,blobAsBytes.length);
                        flag = true;
                    }
                    return flag;

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
        return false;
    }
}
