package NewYearGifts.Entities;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class PresentEntity extends BaseItemEntity {
    private double height;
    private double width;
    private double length;
    private String color;
    private int color_code;

    public PresentEntity(int id, double weight, String code, ItemInfoEntity item_info,
                         int color_code, double height, double width, double length, String color) {
        super(id, weight, code, item_info);
        this.height = height;
        this.width = width;
        this.length = length;
        this.color = color;
        this.color_code = color_code;
    }
}
