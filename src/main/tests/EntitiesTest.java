import NewYearGifts.Entities.*;
import NewYearGifts.EnumConstants.ItemCategory;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

public class EntitiesTest {
    private BaseItemEntity base_item;
    private ItemInfoEntity item_info;

    @BeforeEach
    public void SetUp() {
        item_info = new ItemInfoEntity(1, 1, 11.0, true, ItemCategory.PRESENT, "brand", "label", "desc");
        base_item = new BaseItemEntity(1, 10.0, "code", item_info);
    }

    @AfterEach
    public void Teardown() {
        LOG.
    }

    @Test
    public void BaseItemTest() {
        Assertions.assertEquals(1, base_item.getId());
        Assertions.assertEquals(10.0, base_item.getWeight(), "Everything`s good)");
        Assertions.assertEquals("code", base_item.getCode());
        Assertions.assertEquals(item_info, base_item.getItem_info());
    }

    @Test
    public void ItemInfoTest() {
        Assertions.assertEquals(1, item_info.getId());
        Assertions.assertEquals(1, item_info.getShop_id());
        Assertions.assertEquals(11.0, item_info.getPrice());
        Assertions.assertTrue(item_info.isAvailable());
        Assertions.assertEquals(ItemCategory.PRESENT, item_info.getCategory());
        Assertions.assertEquals("brand", item_info.getBrand());
        Assertions.assertEquals("label", item_info.getLabel());
        Assertions.assertEquals("desc", item_info.getDescription());
    }

    @Test
    public void PresentTest() {
        var present = new PresentEntity(1, 11.0, "code", item_info, 11, 11, 11, 11, "color");

        Assertions.assertEquals(11.0, present.getWeight());
        Assertions.assertEquals("code", present.getCode());
        Assertions.assertEquals(item_info, present.getItem_info());
        Assertions.assertEquals(11, present.getColor_code());
        Assertions.assertEquals(11, present.getHeight());
        Assertions.assertEquals(11, present.getWidth());
        Assertions.assertEquals(11, present.getLength());
        Assertions.assertEquals("color", present.getColor());
    }

    @Test
    public void ShopTest() {
        var shop = new ShopEntity(1, "city", "addr", "desc");

        Assertions.assertEquals("city", shop.getCity());
        Assertions.assertEquals("addr", shop.getAddress());
        Assertions.assertEquals("desc", shop.getDescription());
    }

    @Test
    public void SweetTest() {
        List<> list = new ArrayList<>();
        var sweet = new SweetEntity(1, 11.0, "code", item_info, 11.0, "type", );

        Assertions.assertEquals(11.0, sweet.getWeight());
        Assertions.assertEquals("code", sweet.getCode());
        Assertions.assertEquals(item_info, sweet.getItem_info());
        Assertions.assertEquals(11, sweet.getColor_code());
        Assertions.assertEquals(11, sweet.getHeight());
        Assertions.assertEquals(11, sweet.getWidth());
        Assertions.assertEquals(11, sweet.getLength());
        Assertions.assertEquals("color", sweet.getColor());
    }
}
