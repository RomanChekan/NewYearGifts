package NewYearGifts.Controllers;

import NewYearGifts.SceneName;
import javafx.fxml.FXML;

public class AdminController {
    @FXML
    public void NewEmployee() {
        MainController.Execute(SceneName.NEW_EMPLOYEE.GetValue());
    }

    @FXML
    public void EditEmployee() {
        MainController.Execute(SceneName.EDIT_EMPLOYEE.GetValue());
    }

    @FXML
    public void NewLocation() {
        MainController.Execute(SceneName.NEW_LOCATION.GetValue());
    }

    @FXML
    public void LogOut() {
        MainController.Execute(SceneName.MENU.GetValue());
    }
}
