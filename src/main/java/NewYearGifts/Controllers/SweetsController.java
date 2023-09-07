package NewYearGifts.Controllers;

import NewYearGifts.SceneName;
import NewYearGifts.Actions.Sweet;
import NewYearGifts.EnumConstants.ItemCategory;
import NewYearGifts.DTO.SweetDto;
import NewYearGifts.DTO.ItemDataDto;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import NewYearGifts.Actions.ItemActions;

import java.util.List;

public class SweetsController {

    private final ItemActions<SweetDto> sweet = new Sweet();

    @FXML
    private TextArea sweets;

    @FXML
    private TextField weight;

    @FXML
    private TextField code;

    @FXML
    private TextField sweet_type;

    @FXML
    private TextField sugar;

    @FXML
    private TextField component;

    @FXML
    private TextField price;

    @FXML
    private TextField shop;

    @FXML
    private TextField label;

    @FXML
    private TextArea description;

    @FXML
    public void SweetsList() {
        sweets.setText(sweet.toString(sweet.Show()));
    }

    @FXML
    public void NewSweet() {
        sweet.New(NewSweetDto());
    }

    private SweetDto NewSweetDto() {
        return new SweetDto(Double.parseDouble(weight.getText()), code.getText(), Double.parseDouble(sugar.getText()),
                sweet_type.getText(), List.of(component.getText()), new ItemDataDto(Double.parseDouble(price.getText()),
                true, ItemCategory.SWEET, shop.getText(), label.getText(), description.getText()));
    }

    @FXML
    public void EditSweet() {
        sweet.Edit(NewSweetDto());
    }

    @FXML
    public void DeleteSweet() {
        sweet.Delete(NewSweetDto());
    }

    @FXML
    public void Return() {
        MainController.Execute(SceneName.EMPLOYEE.GetValue());
    }
}
