package NewYearGifts.Entities;

import NewYearGifts.EnumConstants.ItemCategory;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class ItemInfoEntity extends BaseEntity {
    private int shop_id;
    private double price;
    private boolean available;
    private ItemCategory category;
    private String brand;
    private String label;
    private String description;

    public ItemInfoEntity(int id, int shop_id, double price, boolean available,
                          ItemCategory category, String brand, String label, String description) {
        super(id);
        this.shop_id = shop_id;
        this.price = price;
        this.available = available;
        this.category = category;
        this.brand = brand;
        this.label = label;
        this.description = description;
    }
}
