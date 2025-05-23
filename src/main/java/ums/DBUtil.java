package com.example.uams;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBUnit{
  private static final String URL= "jdbc:postgresql://localhost:5432/uams";
  private static final String USER="your_username";
  private static final String PASSWORD="your_password";
  static{
    try{
      Class.forName("org.postgresql.Driver");
    }catch(ClassNotFoundexception e){
      e.printStackTrace();
    }
  }
  public static Connection getConnection() throws
  Exception{
    return DriverManager.getConnection(URL, USER, PASSWORD);
  }
}
