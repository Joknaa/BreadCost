package Presenters;

import Models.DataBaseModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static Presenters.OutputPresenter.*;

public class DataBasePresenter {
    private static final String url = DataBaseModel.GetUrl();
    private static final String login = DataBaseModel.GetLogin();
    private static final String password = DataBaseModel.GetPassword();
    private static Connection Session = null;

    //<editor-fold desc="Setting Up Connection">
    public static void SetupDataBaseConnection(){
        try {
            Connect();
            SQL_TestConnectivity();
            Disconnect();
        } catch (SQLException | ClassNotFoundException e) {
            DisplayError("Ops !! You can't connect to the DataBase\n");
            System.exit(1);
        }
    }
    public static void Connect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Session = DriverManager.getConnection(url,login,password) ;
    }
    private static void SQL_TestConnectivity() throws SQLException {
        String query = "SELECT * FROM users;";
        Session.createStatement().executeQuery(query);
    }
    public static void Disconnect() throws SQLException {
        if (Session != null){
            Session.close();
            Session = null;
        }
    }
    //</editor-fold>

    //<editor-fold desc="SignUp">
    public static void SignUp(String login, String password) throws SQLException, ClassNotFoundException, UserAlreadyExistException {
            Connect();
            SQL_Check_LoginAvailable(login);
            SQL_SignUp(login, password);
            RegisterSignIn(login);
            Disconnect();
    }
    private static void SQL_Check_LoginAvailable(String login) throws SQLException, UserAlreadyExistException {
        String query = "SELECT * FROM users WHERE login='" + login + "';";
        ResultSet rt = Session.createStatement().executeQuery(query);
        if (rt.next()) throw new UserAlreadyExistException("login already exist");
    }
    private static void SQL_SignUp(String login, String password) throws SQLException {
        String query = "INSERT INTO users(login, password) VALUES ('" + login + "', '" + password + "');";
        Session.createStatement().executeUpdate(query);
    }
    //</editor-fold>
    //<editor-fold desc="SignIn">
    public static void SignIn(String login, String password) throws SQLException, ClassNotFoundException, UserNotFoundException {
            Connect();
            SQL_Check_UserExist(login, password);
            RegisterSignIn(login);
            Disconnect();
    }
    private static void SQL_Check_UserExist(String login, String password) throws SQLException, UserNotFoundException {
        String query = "SELECT * FROM users WHERE LOGIN='" + login + "' AND Password='" + password + "';";
        ResultSet rt = Session.createStatement().executeQuery(query);
        if (!rt.isBeforeFirst()){
            throw new UserNotFoundException("Login or Password Incorrect");
        }
    }
    private static void RegisterSignIn(String login) { UserPresenter.SignIn(login); }
    //</editor-fold>

    public static void AddMedia(String[] fileData) throws SQLException, ClassNotFoundException{}
    //<editor-fold desc="Get Media List">
    public static String[] GetUsersList() throws SQLException, ClassNotFoundException {
        String[] UsersList;
        Connect();
        UsersList = SQL_GetUsersList();
        Disconnect();
        return UsersList;
    }
    private static String[] SQL_GetUsersList() throws SQLException {
        String query = "SELECT Login FROM users;";
        ResultSet dataSet = Session.createStatement().executeQuery(query);
        return ConvertMediaListToStringArray(dataSet);
    }
    private static String[] ConvertMediaListToStringArray(ResultSet DataSet) throws SQLException {
        List<String> dataList = new ArrayList<String>();
        while (DataSet.next()) { dataList.add(DataSet.getString(1)); }

        String[] dataStringArray = new String[dataList.size()];
        dataList.toArray(dataStringArray);
        return dataStringArray;
    }
    //</editor-fold>


    public static class UserNotFoundException extends Exception { UserNotFoundException(String s){ super(s);}}
    public static class UserAlreadyExistException extends Exception { UserAlreadyExistException(String s){ super(s);}}
    public static class RenamedFileException extends Exception { RenamedFileException(String s){ super(s);}}
}
