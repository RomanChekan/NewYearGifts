package NewYearGifts;

public enum SceneName {
    MENU("Menu"),
    ADMIN("Admin"),
    EMPLOYEE("Employee"),
    NEW_EMPLOYEE("NewEmployee"),
    EDIT_EMPLOYEE("EditEmployee"),
    NEW_LOCATION("NewLocation"),
    PRESENT("Present"),
    SWEETS("Sweets");

    private String value;

    SceneName(String value) {
        this.value = value;
    }

    public String GetValue() {
        return value;
    }
}