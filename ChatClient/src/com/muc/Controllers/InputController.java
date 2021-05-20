package com.muc.Controllers;

import com.muc.NewUI.OutputView;
import java.sql.SQLException;
import static com.muc.Controllers.DataBaseController.*;
import static com.muc.NewUI.OutputView.DisplayError;
import static com.muc.NewUI.OutputView.DisplayInformation;

public class InputController {
    public static void Try_SignUp(String login, String password, String passwordRepeat) {
        try {
            Check_NoEmptyFieldsExist(login, password, passwordRepeat);
            Check_PasswordMatch(password, passwordRepeat);
            DisplayInformation("Welcome " + login + " ! You Signed Up Successfully !");
            //todo: OnClick_SwapPanels(new MainPanel());
        } catch (EmptyInputFieldException |
                PasswordMismatchException | InputTooShortException e) {
            DisplayError(e.getMessage());
        }
    }

    public static void Check_NoEmptyFieldsExist(String... inputFields) throws EmptyInputFieldException, InputTooShortException {
        for (String field : inputFields) {
            if (field.isEmpty()) throw new EmptyInputFieldException("Pls fill all the fields");
            if (field.length() <= 3)
                throw new InputTooShortException("Login and Password should be at least 3 characters");
        }
    }

    public static void Check_PasswordMatch(String password, String passwordRepeat) throws PasswordMismatchException {
        if (!password.equals(passwordRepeat))
            throw new PasswordMismatchException("Password doesnt match");
    }

    public static void Try_SignIn(String login, String password) {
        try {
            Check_NoEmptyFieldsExist(login, password);
            DisplayInformation("Welcome " + login + " !");
            OutputView.Login(login, password);
            //todo: OnClick_SwapPanels(new MainPanel());

        } catch (EmptyInputFieldException | InputTooShortException e) {
            DisplayError(e.getMessage());
        }
    }

    public static class EmptyInputFieldException extends Exception {
        EmptyInputFieldException(String s) {
            super(s);
        }
    }

    public static class InputTooShortException extends Exception {
        InputTooShortException(String s) {
            super(s);
        }
    }

    public static class PasswordMismatchException extends Exception {
        PasswordMismatchException(String s) {
            super(s);
        }
    }
}
