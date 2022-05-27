package config;


import java.sql.*;


public class DBconfig {
    //clasa singleton

    private static final String DB_URL = "jdbc:mysql://localhost:3306/fooddeliverydb";
    public static final String USER = "root";
    public static final String PASS = "root";

    public static Connection DBConnection;

    private DBconfig() {}

    public static synchronized Connection getDBConnection() {
        try {
            if (DBConnection == null || DBConnection.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                DBConnection = DriverManager.getConnection(DB_URL, USER, PASS);
            }

        }catch (SQLException | ClassNotFoundException ex) {
            System.out.println("DB open error!");
        }
        return DBConnection;
    }

    public static void closeDBConnection() {
        try{
            if(DBConnection != null && !DBConnection.isClosed()) {
                DBConnection.close();
            }
        }catch (SQLException ex) {
            System.out.println("DB close error!");
        }

    }

}
