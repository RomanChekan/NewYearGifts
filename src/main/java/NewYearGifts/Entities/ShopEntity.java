package NewYearGifts.Entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class ShopEntity extends BaseEntity {
    private String city;
    private String address;
    private String description;

    public ShopEntity(int id, String city, String address, String description) {
        super(id);
        this.city = city;
        this.address = address;
        this.description = description;
    }
}
