package com.revature.personalproject.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class ConnectionFactory {

        private static ConnectionFactory connectionFactory;

    public static ConnectionFactory getInstance()
    {
        if(connectionFactory == null) //this block makes it so that connectionfactory can't be instantiated more than once.
        {
            connectionFactory = new ConnectionFactory();
        }
        return connectionFactory;
    }

    static{ //static block will run when ConnectionFactory class gets loaded into memory... not when instantiated, but before that
        try{
            Class.forName("org.postgresql.Driver");
        } catch (Exception e){
            System.err.println("Failed to load PostgreSQL Driver");
            throw new RuntimeException(e);
        }
    }

    private static Properties props = new Properties();

    private ConnectionFactory(){
        try{
            props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties"));
        } catch (Exception e){
            System.err.println("Failed to load database credentials from property file.");
            throw new RuntimeException(e); //fail fast for easier debugging
        }
    }

    public static Connection getConnection() throws SQLException {
        //System.out.println(props.getProperty("db-url"));
        //System.out.println(props.getProperty("db-username"));
        //System.out.println( props.getProperty("db-password"));

        Connection conn = DriverManager.getConnection("jdbc:postgresql://database-1.csdkp74gxjhy.us-east-1.rds.amazonaws.com:5432/?user=postgres&password=!Lpizzaman11&currentSchema=netflix_app");

        if (conn == null)
        {
            throw new RuntimeException("Could not establish a connection to the database.");
        }
        return conn;
    }

}


