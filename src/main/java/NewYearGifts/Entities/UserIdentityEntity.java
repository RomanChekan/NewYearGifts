package NewYearGifts.Entities;

import NewYearGifts.EnumConstants.UserStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class UserIdentityEntity extends BaseEntity {
    private int shop_id;
    private UserStatus status;

    
    public UserIdentityEntity(int id, int shop_id, UserStatus status) {
        super(id);
        this.shop_id = shop_id;
        this.status = status;
    }
}
