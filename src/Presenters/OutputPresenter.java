package Presenters;

import Views.OutputView;
import java.sql.SQLException;
import static Presenters.DataBasePresenter.*;

public class OutputPresenter {
    public static void SetUpGUI(){ OutputView.SetUpGUI(); }

    public static String GetCurrentUser() { return UserPresenter.GetCurrentUser(); }
    public static void DisplayError(String error) { OutputView.DisplayError(error);}
}
