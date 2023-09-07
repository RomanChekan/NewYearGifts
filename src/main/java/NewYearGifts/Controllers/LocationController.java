package NewYearGifts.Controllers;

import NewYearGifts.SceneName;
import NewYearGifts.Actions.Admin;
import NewYearGifts.Actions.AdminActions;
import NewYearGifts.DTO.LocationDto;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class LocationController {
    private static final AdminActions admin = new Admin();
    @FXML
    private TextField shop;
    @FXML
    private TextArea information;

    @FXML
    public void NewLocation() {
        if (LocationValidation()) {
            var location = new LocationDto(0, this.shop.getText(), information.getText());
            admin.NewLocation(location);
        }
    }

    private boolean LocationValidation() {
        if (shop == null || information == null) {
            return false;
        } else if("".equals(shop.getText()) && !"".equals(information.getText())) {
            return false;
        }
        return true;
    }

    @FXML
    public void Return() {
        MainController.Execute(SceneName.ADMIN.GetValue());
    }
}
