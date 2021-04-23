package Presenters;

import Models.LogStatus;
import Models.UserModel;

public class UserPresenter {
    private static LogStatus adminLogStatus = LogStatus.LoggedOut;

    public static void SignIn(String login){
        adminLogStatus = LogStatus.LoggedIn;
        UserModel.SetLogin(login);
    }
    public static String GetCurrentUser(){ return UserModel.GetLogin(); }
    public static void LogOut(){ adminLogStatus = LogStatus.LoggedOut;}
}
