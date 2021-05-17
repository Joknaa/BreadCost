package com.muc.Controllers;

import com.muc.Models.LogStatus;
import com.muc.Models.UserModel;

public class UserController {
    private static LogStatus adminLogStatus = LogStatus.LoggedOut;

    public static void SignIn(String login){
        adminLogStatus = LogStatus.LoggedIn;
        UserModel.SetLogin(login);
    }
    public static String GetCurrentUser(){ return UserModel.GetLogin(); }
    public static void LogOut(){ adminLogStatus = LogStatus.LoggedOut;}
}
