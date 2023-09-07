package NewYearGifts.Entities;

import NewYearGifts.EnumConstants.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class UserEntity extends BaseEntity {
    private String email;
    private String password;
    private boolean verification;
    private Roles role;
    private UserIdentityEntity identity;


    public UserEntity(int id, boolean verification, String email, String password, Roles role,
                      int shop_id, UserStatus status) {
        super(id);
        this.email = email;
        this.password = password;
        this.verification = verification;
        this.role = role;
        this.identity = new UserIdentityEntity(id, shop_id, status);
    }
}
