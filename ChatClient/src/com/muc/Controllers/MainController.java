package com.muc.Controllers;

import static com.muc.Controllers.DataBaseController.*;
import static com.muc.Controllers.OutputController.*;

public class MainController {
    public static void start() {
        SetupDataBaseConnection();
        SetUpGUI();
    }
}
