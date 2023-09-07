package NewYearGifts.Controllers;

import NewYearGifts.SceneName;
import NewYearGifts.Actions.Present;
import NewYearGifts.EnumConstants.ItemCategory;
import NewYearGifts.DTO.PresentDto;
import NewYearGifts.DTO.ItemDataDto;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import NewYearGifts.Actions.ItemActions;

public class PresentController {
    private final ItemActions<PresentDto> present = new Present();

    @FXML
    private TextArea presents;

    @FXML
    private TextField weight;

    @FXML
    private TextField code;

    @FXML
    private TextField code_color;

    @FXML
    private TextField height;

    @FXML
    private TextField width;

    @FXML
    private TextField length;

    @FXML
    private TextField color;

    @FXML
    private TextField price;

    @FXML
    private TextField shop;

    @FXML
    private TextField label;

    @FXML
    private TextArea description;

    @FXML
    public void PresentsList() {
        presents.setText(present.toString(present.Show()));
    }

    @FXML
    public void NewPresent() {
        present.New(NewPresentDto());
    }

    private PresentDto NewPresentDto() {
        return new PresentDto(Double.parseDouble(weight.getText()), code.getText(), Integer.parseInt(code_color.getText(), 16),
                Double.parseDouble(height.getText()), Double.parseDouble(width.getText()), Double.parseDouble(length.getText()), color.getText(),
                new ItemDataDto(Double.parseDouble(price.getText()), true, ItemCategory.PRESENT, shop.getText(),
                        label.getText(), description.getText()));
    }

    @FXML
    public void EditPresent() {
        present.Edit(NewPresentDto());
    }

    @FXML
    public void DeletePresent() {
        present.Delete(NewPresentDto());
    }

    @FXML
    public void Return() {
        MainController.Execute(SceneName.EMPLOYEE.GetValue());
    }
}
