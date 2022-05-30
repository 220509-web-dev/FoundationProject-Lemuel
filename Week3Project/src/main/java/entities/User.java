package entities;

import utils.LogLevel;
import utils.Logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

public class User {
    private int id;
    private static String username;
    private static String password;

    public User() {
        super();
    }

    public User(int id) {
        super();
        this.id = id;
    }

    public User(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    public User(int id, String username, String password) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static String getUsername() {
        if (User.getUsername().isEmpty()) {
            Logger logger = new Logger();
            logger.log("getPlayerById Test failed. No player first name found.", LogLevel.ERROR);
        } else {
            Logger logger2 = new Logger();
            logger2.log("getPlayerById Test Passed!", LogLevel.DEBUG);
        }
        return username;
}

    public void setUsername(String username) {this.username = username;}

    public static String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(username, user.username) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {return Objects.hash(id, username, password);}

    @Override
    public String toString()
    {
        return "entities.User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
