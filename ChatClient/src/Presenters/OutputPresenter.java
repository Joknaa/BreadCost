package Presenters;

import Presenters.Client.ChatClient;
import Views.OutputView;

public class OutputPresenter {
    public static void SetUpGUI(){ OutputView.SetUpGUI(); }
    public static void SendMessage(String message){
        ChatClient.Server_Chat()
    }

    public static String GetCurrentUser() { return UserPresenter.GetCurrentUser(); }
    public static void DisplayError(String error) { OutputView.DisplayError(error);}
}
