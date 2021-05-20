package com.muc.Models;

public class DataBaseModel {
    private static String url = "jdbc:mysql://127.0.0.1:3306/myDatabase?autoReconnect=true&useSSL=false";
    private static String login = "root";
    private static String password = "oknaa";

    public static void SetUrl(String URL){ url = URL;}
    public static void SetLogin(String LOGIN){ login = LOGIN;}
    public static void SetPassword(String PASSWORD){ password = PASSWORD;}

    public static String GetUrl(){ return url;}
    public static String GetLogin(){ return login;}
    public static String GetPassword(){ return password;}
}
