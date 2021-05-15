package com.muc.Controllers;

import com.muc.Views.OutputView;
import javax.swing.*;
import static com.muc.Views.OutputView.GetChatField;
import static com.muc.Views.OutputView.SetChatField;

public class OutputController {
    public static void SetUpGUI(){ OutputView.SetUpGUI(); }
    public static void StoreForPrint(String message){
        JTextArea chatField = GetChatField();
        chatField.append(message);
        SetChatField(chatField);
    }

    public static String GetCurrentUser() { return UserController.GetCurrentUser(); }
    public static void DisplayError(String error) { OutputView.DisplayError(error);}
}
