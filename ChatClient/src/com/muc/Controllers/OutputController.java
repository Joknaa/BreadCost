package com.muc.Controllers;

import com.muc.NewUI.OutputView;
import java.sql.SQLException;
import static com.muc.Controllers.DataBaseController.*;

public class OutputController {
    public static void SetUpGUI(){ OutputView.SetUpGUI(); }

    public static String GetCurrentUser() { return UserController.GetCurrentUser(); }
    public static void DisplayError(String error) { OutputView.DisplayError(error);}
}
