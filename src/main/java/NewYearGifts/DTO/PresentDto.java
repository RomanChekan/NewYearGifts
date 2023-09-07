package NewYearGifts.DTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PresentDto extends BaseItemDto {
    private Integer color_code;
    private Double height;
    private Double width;
    private Double length;
    private String color;
    private ItemDataDto item_info;

    public PresentDto(Double weight, String code, Integer color_code, Double height, Double width, Double length, String color,
                      ItemDataDto item_info) {
        super(weight, code);
        this.color_code = color_code;
        this.height = height;
        this.width = width;
        this.length = length;
        this.color = color;
        this.item_info = item_info;
    }
}
