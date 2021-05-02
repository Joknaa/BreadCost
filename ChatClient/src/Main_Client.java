import Views.OutputView;

import Presenters.DataBasePresenter;

public class Main_Client {
    public static void main(String[] args) {
        DataBasePresenter.SetupDataBaseConnection();
        OutputView.SetUpGUI();
    }
}
