package Presenters;

import Views.UI.MainPanel;
import static Views.OutputView.*;
import static Presenters.DataBasePresenter.*;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

public class InputPresenter {
    public static void Try_SignUp(String login, String password, String passwordRepeat) {
        try {
            Check_NoEmptyFieldsExist(login, password, passwordRepeat);
            Check_PasswordMatch(password, passwordRepeat);
            SignUp(login, password);
            DisplayInformation("Welcome " + login + " ! You Signed Up Successfully !");
            //OnClick_SwapPanels(new MainPanel(client));
        } catch (ClassNotFoundException | SQLException | EmptyInputFieldException |
                PasswordMismatchException | UserAlreadyExistException | InputTooShortException e ) {
            DisplayError(e.getMessage());
        }
    }
    public static void Check_NoEmptyFieldsExist(String... inputFields) throws EmptyInputFieldException, InputTooShortException {
        for (String field : inputFields ) {
            if (field.isEmpty()) throw new EmptyInputFieldException("Pls fill all the fields");
            if (field.length() <= 3) throw new InputTooShortException("Login and Password should be at least 3 characters");
        }
    }
    public static void Check_PasswordMatch(String password, String passwordRepeat) throws PasswordMismatchException{
        if (!password.equals(passwordRepeat))
            throw new PasswordMismatchException("Password doesnt match");
    }
    public static boolean Try_SignIn(String login, String password) {
        try {
            Check_NoEmptyFieldsExist(login, password);
            SignIn(login, password);
            DisplayInformation("Welcome " + login + " !");
        } catch (EmptyInputFieldException | SQLException | ClassNotFoundException |
                UserNotFoundException | InputTooShortException e) {
            DisplayError(e.getMessage());
            return false;
        }
        return true;
    }

    public static void SendMessage(String message){
        //ChatClient.Server_Chat("", message);
    }
    public static String[] Try_AddMedia(DefaultListModel<String> listModel) {
        String[] mediaData = new String[]{};
        try {
            mediaData = GetDataFromFile(listModel);
            AddMedia(mediaData);
        } catch (SQLException | ClassNotFoundException | NullPointerException | MediaNameAlreadyExistException e){
            DisplayError(e.getMessage());
        }
        return mediaData;
    }
    private static String[] GetDataFromFile(DefaultListModel<String> listModel) throws NullPointerException, MediaNameAlreadyExistException {
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);

        if (chooser.getSelectedFile() == null)
            throw new NullPointerException("No file has been selected");

        if (listModel.contains(chooser.getSelectedFile().getName()))
            throw new MediaNameAlreadyExistException("This file name already exist in the list");

        return new String[] {
                chooser.getSelectedFile().getName(),
                UserPresenter.GetCurrentUser(),
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),
                chooser.getSelectedFile().getAbsolutePath().replaceAll("\\\\", "\\\\\\\\")
        };
    }

    public static class EmptyInputFieldException extends Exception { EmptyInputFieldException(String s){ super(s);}}
    public static class InputTooShortException extends Exception { InputTooShortException(String s){ super(s);}}
    public static class MediaNameAlreadyExistException extends Exception { MediaNameAlreadyExistException(String s){ super(s);}}
    public static class PasswordMismatchException extends Exception { PasswordMismatchException(String s){ super(s);}}
}
