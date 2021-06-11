/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AnhTu
 */
public class UserDatabase {
    private Connection conn;
    public final String DATABASE_NAME = "chat_db";
    public final String USERNAME = "root";
    public final String PASSWORD = "oknaa";
    public final String URL_MYSQL = "jdbc:mysql://localhost:3306?" + DATABASE_NAME;
    
    public final String USER_TABLE = "chat_db.user_tb";
    
    private PreparedStatement pst;
    private ResultSet rs;
    private Statement st;

    public Connection connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL_MYSQL, USERNAME, PASSWORD);
            System.out.println("Connect successfull");
        } catch (SQLException ex) {
            Logger.getLogger(UserDatabase.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error connection! Lỗi kết nối nhé!");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
    
    public List<String> GetAllUsers() {
        List<String> usersList = new ArrayList<>();
        try {
            st = conn.createStatement();
            rs = st.executeQuery("SELECT name FROM "+USER_TABLE);
            while(rs.next()) {
                usersList.add(rs.getString(1));
            }
            return usersList;
        } catch (SQLException ex) {
            Logger.getLogger(UserDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usersList;
    }
    
    public int insertUser(User u) {
        System.out.println("Before: name = "+u.name+" - pass = "+u.pass);
        try {
            pst = conn.prepareCall("INSERT INTO "+USER_TABLE+" VALUES ('"+u.name+"', '"+u.pass+"')");
            int kq = pst.executeUpdate();
            if(kq > 0) System.out.println("Insert successful!");
            System.out.println("After: name = "+u.name+" - pass = "+u.pass);
            return kq;


        } catch (SQLException ex) {
            Logger.getLogger(UserDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    public int createUser(User u) {
        try {
            pst = conn.prepareStatement("INSERT INTO "+USER_TABLE+" VALUE(?,?);");
            pst.setString(1, u.name);
            pst.setString(2, u.pass);
            return pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    public int checkUser(String name, String pass) {
        try {
            pst = conn.prepareStatement("SELECT * FROM "+USER_TABLE+" WHERE name = '" + name + "' AND pass = '" + pass +"'");
            rs = pst.executeQuery();
            
            if(rs.next()) {
                return 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDatabase.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return 0;
    }
    
    public void closeConnection() {
        try {
            if(rs!=null) rs.close();
            if(pst!=null) pst.close();
            if(st!=null) st.close();
            if(conn!=null) conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDatabase.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("[UserDatabase.java] Lỗi close connection");
        }
    }

    public void StoreMessage(String sender, String receiver, int idGroup, String message, String fileName) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        java.sql.Connection conn =  DriverManager.getConnection(URL_MYSQL,USERNAME,PASSWORD);
        Statement addh =  conn.createStatement();
        LocalDateTime now = LocalDateTime.now();
        String stadd = "INSERT INTO chat_db.messages(`ID_MESSAGE`, `ID_SENDER`, `ID_RECIEVER`, `ID_GRP`, `MSG_TEXT`, `DATETIME`, `NAME`, `PATH`, `DATA`) " +
                "VALUES (NULL,'"+sender+"', '"+ receiver +"', "+ idGroup +",'"+ message+"' ,'"+now+"', '', '"+ fileName +"', '')";
        addh.executeUpdate(stadd);
    }
}
