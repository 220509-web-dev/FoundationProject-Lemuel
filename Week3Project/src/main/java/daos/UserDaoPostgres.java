package daos;

import entities.User;
import utils.ConnectionFactoryUtility;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoPostgres {
    public User getByUsername(String username)
    {
        try(Connection conn = ConnectionFactoryUtility.getConnection())
        {
            String sql = "select * from users WHERE username = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next())
            {
                return new User(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password")
                );
            }

        }catch (SQLException e)
        {
            System.out.println("Something went wrong with the database!");
            e.printStackTrace();
        }
        return null;
    }
    public User getById(int id)
    {
        try(Connection conn = ConnectionFactoryUtility.getConnection())
        {
            String sql = "select * from users WHERE id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next())
            {
                return new User(
                  resultSet.getInt("id"),
                  resultSet.getString("username"),
                  resultSet.getString("password")
                );
            }
        } catch (SQLException e)
        {
            System.out.println("Something went wrong with the database!");
            e.printStackTrace();
        }
        return null;
    }
    public List<User> getAllUsers()
    {
        try (Connection conn = ConnectionFactoryUtility.getConnection())
        {
            String sql = "select * from users";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            List<User> userList = new ArrayList<>();

            while(resultSet.next())
            {
                userList.add( new User(
                   resultSet.getInt("id"),
                   resultSet.getString("username"),
                   resultSet.getString("password")
                ));
            }
        } catch (SQLException e)
        {
            System.out.println("Something went wrong obtaining users!");
            e.printStackTrace();
        }
    return null;
    }
    public static int create(User userToBeRegistered) {
        try(Connection conn = ConnectionFactoryUtility.getConnection())
        {
            String sql = "insert into users (username, password)"
                    + "values (?, ?)"
                    + "returning users.id";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1, userToBeRegistered.getUsername());
            preparedStatement.setString(2, userToBeRegistered.getPassword());

            preparedStatement.execute();
            ResultSet resultSet;

        } catch (SQLException e)
        {
            System.out.println("User creation failed");
            e.printStackTrace();
        }

        return userToBeRegistered.getId();
    }

    public static void WriteToFile(String username, String password) throws IOException {

        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter("src/main/resources/user.csv");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            String s = username + " " + password;
            bufferedWriter.write(s);
            bufferedWriter.close();
            System.out.println("Successfully persisted data to file!");
        } catch (IOException e)
        {
            e.printStackTrace();
            System.err.println("Could not get access to file.");
            fileWriter = new FileWriter("src/main/resources/error.csv");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("Could not get access to file!");
        }

    }
}
