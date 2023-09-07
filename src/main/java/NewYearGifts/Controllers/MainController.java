package NewYearGifts.Controllers;

import NewYearGifts.DTO.UserDto;
import javafx.scene.*;

import java.util.HashMap;

public class MainController {
    private static final HashMap<String, Parent> map = new HashMap<>();
    private static Scene scene;
    private static UserDto user;


    public static void SetUser(UserDto user) {
        MainController.user = user;
    }

    public static UserDto getUser() {
        return user;
    }

    public static Scene GetScene() {
        return scene;
    }

    public static void AddScene(String name, Parent parent) {
        map.put(name, parent);
    }

    public static void Execute(String name) {
        var parent = map.get(name);
        if (scene == null) {
            scene = new Scene(parent);
        }
        scene.setRoot(parent);
    }
}
