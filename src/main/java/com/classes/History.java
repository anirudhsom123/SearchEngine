package com.classes;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/History")
public class History {
    protected  void doGet(HttpServletRequest request, HttpServletResponse response){
        Connection connection= DatabaseConnection.getConnection();
        try{
            ResultSet resultSet=connection.createStatement().executeQuery("select * from history;");

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
}
