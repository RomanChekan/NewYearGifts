package NewYearGifts.Entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class SweetEntity extends BaseItemEntity {
    private double sugar;
    private String type_of_sweet;
    private List<String> components;

    public SweetEntity(int id, double weight, String code, ItemInfoEntity item_info,
                       double sugar, String type_of_sweet, List<String> components) {
        super(id, weight, code, item_info);
        this.sugar = sugar;
        this.type_of_sweet = type_of_sweet;
        this.components = components;
    }
}
