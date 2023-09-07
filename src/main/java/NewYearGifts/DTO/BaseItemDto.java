package NewYearGifts.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class BaseItemDto {
    private Double weight;
    private String code;
}
