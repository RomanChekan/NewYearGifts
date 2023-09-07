package NewYearGifts.Entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class BaseItemEntity extends BaseEntity {
    private String code;
    private double weight;
    private ItemInfoEntity item_info;

    public BaseItemEntity(int id, double weight, String code, ItemInfoEntity item_info) {
        super(id);
        this.code = code;
        this.item_info = item_info;
        this.weight = weight;
    }
}
