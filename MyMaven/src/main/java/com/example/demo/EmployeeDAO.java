package com.example.demo;

import java.sql.*;
import java.sql.PreparedStatement;

public class EmployeeDAO {
    private String m_conn; // connection string

    public EmployeeDAO(String m_conn) {
        this.m_conn = m_conn;
    }

    public void creatDatabase(String name) {
        String url = "jdbc:sqlite:C:/SQLITE/" + name ;
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createTable(String tableName, String DbName){
        String sql = "CREATE TABLE IF NOT EXISTS " +tableName+ "(\n"
                + "	ID integer PRIMARY KEY,\n"
                + "	NAME text NOT NULL,\n"
                + "	AGE integer,\n"
                + "	ADDRESS text NOT NULL,\n"
                + "	SALARY double NOT NULL\n"
                + ");";

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:C:/SQLITE/" + DbName);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        //select("create table " + tableName + "(ID integer, NAME string, ADDRESS string, SALARY double, AGE integer)");
    }

    public void select(String query) {

        // try to connect to db
        try (Connection conn = DriverManager.getConnection(m_conn)) {
            // check if connection succeed
            if (conn != null) {

                // prepare query string
                String sql = query;

                // prepare statement
                Statement stmt = conn.createStatement();

                // fire query
                ResultSet rs = stmt.executeQuery(sql);

                // read results
                while (rs.next()) {
                    System.out.println(rs.getInt("id") + "\t" +
                            rs.getString("name") + "\t" +
                            rs.getInt("age") + "\t" +
                            rs.getString("address") + "\t" +
                            rs.getDouble("salary")
                    );
                }
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(String query) {

        // try to connect to db
        try (Connection conn = DriverManager.getConnection(m_conn)) {
            // check if connection succeed
            if (conn != null) {

                // prepare query
                String sql = query;

                // prepare statement
                Statement stmt = conn.createStatement();

                // fire query
                int result = stmt.executeUpdate(sql); // not expect result
                System.out.println(result + " record updated.");
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insert(String name, int age, String address, int salary) {
        String sql = "INSERT INTO employees(name, Age, address, salary) VALUES(?,?,?,?)";

        // try to connect to db
        try (Connection conn = DriverManager.getConnection(m_conn)) {
            // check if connection succeed
            if (conn != null) {

                // prepare statement
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, name);
                stmt.setInt(2, age);
                stmt.setString(3, address);
                stmt.setInt(4, salary);
                // fire query
               stmt.executeUpdate();
                System.out.println("query inserted.");
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(int id){
        String sql = "DELETE FROM employees WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(m_conn)) {
            // check if connection succeed
            if (conn != null) {
                // prepare statement
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, id);
                // fire query
                stmt.executeUpdate();
                System.out.println("query deleted.");
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
