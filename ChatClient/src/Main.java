import Views.OutputView;

import Presenters.DataBasePresenter;

public class Main {
    public static void main(String[] args) {
        DataBasePresenter.SetupDataBaseConnection();
        OutputView.SetUpGUI();
    }
}
