package NewYearGifts.Controllers;

import NewYearGifts.EnumConstants.Roles;
import NewYearGifts.EnumConstants.UserStatus;
import NewYearGifts.SceneName;
import NewYearGifts.Actions.LogIn;
import NewYearGifts.Actions.LogInAction;
import NewYearGifts.DTO.UserDto;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MenuController {
    private static final LogInAction user = new LogIn();
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;

    @FXML
    public void LogIn() {
        if (UserValidation()) {
            var user = MenuController.user.LogIn(NewUserDto());
            if (user != null) {
                MainController.SetUser(user);
                email.setText("");
                password.setText("");
                switch (user.getRole()) {
                    case ADMIN:
                        MainController.Execute(SceneName.ADMIN.GetValue());
                        break;
                    case EMPLOYEE:
                        MainController.Execute(SceneName.EMPLOYEE.GetValue());
                        break;
                }
            }
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

    private UserDto NewUserDto() {
        return new UserDto(email.getText(), password.getText(), 2, Roles.NOT_IDENTIFIED, UserStatus.ACTIVE);
    }
}
