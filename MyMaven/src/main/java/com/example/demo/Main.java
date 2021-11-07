package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main
{
    public static void main(String[] args) throws ClassNotFoundException
    {
 /*       // load the sqlite-JDBC driver using the current class loader
       // Class.forName("org.sqlite.JDBC"); // after java 6 it is not necessary

        Connection connection = null;
        try
        {
            // create a database connection - open a JDBC Connection
            connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            statement.executeUpdate("drop table if exists person");
            statement.executeUpdate("create table person (id integer, name string)");
            statement.executeUpdate("insert into person values(1, 'David')");
            statement.executeUpdate("insert into person values(2, 'Lia')");

            ResultSet rs = statement.executeQuery("select * from person");
            while(rs.next())
            {
                // read the result set
                System.out.println("name = " + rs.getString("name"));
                System.out.println("id = " + rs.getInt("id"));
            }
        }
        catch(SQLException e)
        {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }
        finally
        {
            try
            {
                if(connection != null)
                    connection.close();
            }
            catch(SQLException e)
            {
                // connection close failed.
                System.err.println(e);
            }
        }*/

        EmployeeDAO empDAO = new EmployeeDAO( "jdbc:sqlite:C:/SQLITE/2203.db");

        //Create Table EMPLOYEES:
        //empDAO.createTable("EMPLOYEES","2203.db" );

        //insert into table EMPLOYEES:
        //empDAO.insert("Dan", 30, "Milan", 3000);
        //empDAO.insert("Natalya", 20, "Paris", 6000);
        //empDAO.insert("Rob", 40, "London", 9000);
        //empDAO.insert("Samuel", 70, "New_York", 5000);

        //Select statements:
        System.out.println("==========* from EMPLOYEES===========");
        empDAO.select("Select * from EMPLOYEES");
        System.out.println("==========AGE > 30===========");
        empDAO.select("SELECT * FROM EMPLOYEES WHERE AGE > 30");

        //Update statements:
        System.out.println("==========After Update===========");
        empDAO.update("UPDATE EMPLOYEES SET AGE = 12 WHERE NAME LIKE 'D%'");
        empDAO.select("Select * from EMPLOYEES");

        //Delete statements:
        System.out.println("==========After Delete===========");
        empDAO.delete(5);
        empDAO.delete(6);
        empDAO.delete(7);
        empDAO.delete(8);

    }
}