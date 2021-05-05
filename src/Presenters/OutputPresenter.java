package Presenters;

import Views.OutputView;
import javax.swing.*;
import static Views.OutputView.GetChatField;
import static Views.OutputView.SetChatField;

public class OutputPresenter {
    public static void SetUpGUI(){ OutputView.SetUpGUI(); }
    public static void StoreForPrint(String message){
        JTextArea chatField = GetChatField();
        chatField.append(message);
        SetChatField(chatField);
    }

    public static String GetCurrentUser() { return UserPresenter.GetCurrentUser(); }
    public static void DisplayError(String error) { OutputView.DisplayError(error);}
}
