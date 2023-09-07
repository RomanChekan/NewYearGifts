package NewYearGifts.Controllers;

import NewYearGifts.SceneName;
import javafx.fxml.FXML;

public class EmployeeController {

    @FXML
    public void Present() {
        MainController.Execute(SceneName.PRESENT.GetValue());
    }

    @FXML
    public void Sweet() {
        MainController.Execute(SceneName.SWEETS.GetValue());
    }

    @FXML
    public void Return() {
        MainController.Execute(SceneName.MENU.GetValue());
    }
}
