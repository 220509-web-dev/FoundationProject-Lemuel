package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactoryUtility {
    private static ConnectionFactoryUtility instance;

    private ConnectionFactoryUtility() { super();}

    public static ConnectionFactoryUtility getInstance()
    {
        if(instance == null)
        {
            instance = new ConnectionFactoryUtility();
        }
        return instance;
    }
    public static Connection getConnection() throws SQLException
    {
        try{
            Class.forName("org.postgresql.Driver");
        }catch (ClassNotFoundException e)
        {
            System.out.println("Class wasn't found");
            e.printStackTrace();
        }

        String url = "jdbc:postgresql://localhost/postgres?user=postgres&password=revature";
        String username = "postgres";
        String password = "revature";



        return DriverManager.getConnection(url, username, password);
    }

}
