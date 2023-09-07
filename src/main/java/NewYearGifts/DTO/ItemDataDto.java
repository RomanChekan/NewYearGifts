package NewYearGifts.DTO;

import NewYearGifts.EnumConstants.ItemCategory;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemDataDto {
    private double price;
    private boolean available;
    private ItemCategory category;
    private String location;
    private String label;
    private String description;
}
