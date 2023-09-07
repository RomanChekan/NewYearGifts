package NewYearGifts;

import NewYearGifts.Controllers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.apache.logging.log4j.*;

import java.io.*;

public class NewYearGifts extends Application {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        logger.info("Successful launch of program)");

        stage.setTitle("Sweets Store");
        stage.setWidth(1080);
        stage.setHeight(720);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("image_resources/gift_icon.png")));
        ControllersInitialization();
        stage.setScene(MainController.GetScene());
        stage.show();
    }

    private void ControllersInitialization() {
        try {
            MainController.AddScene(SceneName.MENU.GetValue(),
                    FXMLLoader.load(getClass().getResource(SceneName.MENU.GetValue() + ".fxml")));
            MainController.AddScene(SceneName.ADMIN.GetValue(),
                    FXMLLoader.load(getClass().getResource(SceneName.ADMIN.GetValue() + ".fxml")));
            MainController.AddScene(SceneName.EMPLOYEE.GetValue(),
                    FXMLLoader.load(getClass().getResource(SceneName.EMPLOYEE.GetValue() + ".fxml")));
            MainController.AddScene(SceneName.NEW_EMPLOYEE.GetValue(),
                    FXMLLoader.load(getClass().getResource(SceneName.NEW_EMPLOYEE.GetValue() + ".fxml")));
            MainController.AddScene(SceneName.EDIT_EMPLOYEE.GetValue(),
                    FXMLLoader.load(getClass().getResource(SceneName.EDIT_EMPLOYEE.GetValue() + ".fxml")));
            MainController.AddScene(SceneName.NEW_LOCATION.GetValue(),
                    FXMLLoader.load(getClass().getResource(SceneName.NEW_LOCATION.GetValue() + ".fxml")));
            MainController.AddScene(SceneName.PRESENT.GetValue(),
                    FXMLLoader.load(getClass().getResource(SceneName.PRESENT.GetValue() + ".fxml")));
            MainController.AddScene(SceneName.SWEETS.GetValue(),
                    FXMLLoader.load(getClass().getResource(SceneName.SWEETS.GetValue() + ".fxml")));
            MainController.Execute(SceneName.MENU.GetValue());
        } catch (IOException e) {
            System.out.println("System has not initialized controllers(");
            e.printStackTrace();
        }
    }
}