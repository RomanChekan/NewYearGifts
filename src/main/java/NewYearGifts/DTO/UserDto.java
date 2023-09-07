package NewYearGifts.DTO;

import NewYearGifts.EnumConstants.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {
    private String email;
    private String password;
    private Integer location_id;
    private Roles role;
    private UserStatus status;

    public boolean Access() {
        if (role == null || role == Roles.NOT_IDENTIFIED) {
            return false;
        } else if (StringValidation(email)) {
            return false;
        } else if (status == null || status == UserStatus.BANNED) {
            return false;
        }
        return true;
    }

    private boolean StringValidation(String string) {
        return string == null || string.isEmpty() || string.isBlank();
    }
}