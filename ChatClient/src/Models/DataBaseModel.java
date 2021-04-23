package Models;

public class DataBaseModel {
    private static final String dbName = "chatapp";
    private static String login = "root";
    private static String password = "";
    private static String url = "jdbc:mysql://localhost:3306/"+ dbName +"?autoReconnect=true&useSSL=false";

    public static void SetUrl(String URL){ url = URL;}
    public static void SetLogin(String LOGIN){ login = LOGIN;}
    public static void SetPassword(String PASSWORD){ password = PASSWORD;}

    public static String GetUrl(){ return url;}
    public static String GetLogin(){ return login;}
    public static String GetPassword(){ return password;}
}
