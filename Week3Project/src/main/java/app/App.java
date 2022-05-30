package app;

import entities.User;
import daos.UserDaoPostgres;
import utils.ConnectionFactoryUtility;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws SQLException, IOException {
        System.out.println(ConnectionFactoryUtility.getConnection());
        Connection conn = ConnectionFactoryUtility.getConnection();

        //creates a user
        User user1 = new User(2, "randomguy", "randompass");
        UserDaoPostgres.create(user1);
        UserDaoPostgres.WriteToFile("randomguy", "randompass");
        //
    }
}
