package NewYearGifts.DTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class SweetDto extends BaseItemDto {
    private Double sugar;
    private String sweet_type;
    private List<String> components;
    private ItemDataDto item_info;

    public SweetDto(Double weight, String code, Double sugar, String sweet_type, List<String> components, ItemDataDto item_info) {
        super(weight, code);
        this.sugar = sugar;
        this.sweet_type = sweet_type;
        this.components = components;
        this.item_info = item_info;
    }
}
