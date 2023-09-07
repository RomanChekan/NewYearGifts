package NewYearGifts.Controllers;

import NewYearGifts.SceneName;
import NewYearGifts.Actions.*;
import NewYearGifts.EnumConstants.*;
import NewYearGifts.DTO.UserDto;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class UserController {
    private static final AdminActions admin = new Admin();
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private CheckBox isAdmin;


    @FXML
    public void SignUp() {
        if (UserValidation()) {
            var user = new UserDto(email.getText(), password.getText(), 1,
                    isAdmin.isSelected() ? Roles.ADMIN : Roles.EMPLOYEE, UserStatus.INACTIVE);
            admin.NewEmployee(user);
        }
    }

    private boolean UserValidation() {
        if (email == null || password == null) {
            return false;
        } else if ("".equals(email.getText()) || "".equals(password.getText())) {
            return false;
        } else if (!email.getText().matches("[A-Za-z][A-Za-z0-9].+@[a-z].+[.][a-z].+")) {
            return false;
        }
        return true;
    }

    @FXML
    public void Return() {
        MainController.Execute(SceneName.ADMIN.GetValue());
    }

    @FXML
    public void Edit() {
        if (UserValidation()) {
            var user = new UserDto(email.getText(), password.getText(), 1,
                    isAdmin.isSelected() ? Roles.ADMIN : Roles.EMPLOYEE, UserStatus.INACTIVE);
            admin.EditEmployee(user);
        }
    }
}
