package NewYearGifts.DAO;

import org.apache.logging.log4j.*;
import NewYearGifts.DataBaseConnection;
import NewYearGifts.Entities.*;
import NewYearGifts.EnumConstants.ItemCategory;

import java.sql.*;
import java.util.*;

public class PresentDao implements Dao<PresentEntity, Integer> {
    private static final Logger logger = LogManager.getLogger();
    private static final DataBaseConnection database = new DataBaseConnection();
    private static final String get_present = "select * from Present p join Item_Data id on id.ID = p.ID_Item_Data where p.ID = ?";
    private static final String load_presents = "select * from Present p join Item_Data id on id.ID = p.ID_Item_Data";
    private static final String get_item_info = "select ID from Item_Data order by ID asc";
    private static final String get_category = "select ID from Category where Name = ?";
    private static final String save_present = "insert into Present (Code, ID_Item_Data, Weight, Height, Length, Width, Color, " +
                                               "color_code) values (?,?,?,?,?,?,?,?)";
    private static final String save_item_info = "insert into Item_Data (ID_Shop, ID_Category, Price, Brand, Label, Description, " +
                                                 "Availability) values (?,?,?,?,?,?,?)";
    private static final String delete_present = "delete from Present where ID = ?";


    @Override
    public Optional<PresentEntity> Get(Integer key) {
        var present = new PresentEntity();
        var item = new ItemInfoEntity();
        try (Connection connection = database.Connect();
             PreparedStatement get_present = connection.prepareStatement(PresentDao.get_present)) {
            get_present.setInt(1, key);
            var result = get_present.executeQuery();
            ResultSetMetaData meta_data = result.getMetaData();
            if (!result.next()) {
                throw new SQLException("System has not found matching present.");
            }
            present.setId(result.getInt("ID"));
            present.setCode(result.getString("Code"));
            item.setId(result.getInt(meta_data.getColumnName(17)));
            item.setShop_id(result.getInt("ID_Shop"));
            item.setCategory(ItemCategory.PRESENT);
            item.setPrice(result.getDouble("Price"));
            item.setBrand(result.getString("Brand"));
            item.setLabel(result.getString("Label"));
            item.setDescription(result.getString("Description"));
            item.setAvailable(result.getBoolean("Availability"));
            present.setItem_info(item);
            present.setWeight(result.getDouble("Weight"));
            present.setHeight(result.getDouble("Height"));
            present.setLength(result.getDouble("Length"));
            present.setWidth(result.getDouble("Width"));
            present.setColor(result.getString("Color"));
            present.setColor_code(Integer.parseInt(result.getString("Color_Code"), 16));

            result.close();
        } catch (SQLException e) {
            logger.error("System cannot get access to present in database.", e);
        }
        return Optional.of(present);
    }

    @Override
    public List<PresentEntity> LoadAll() {
        var list = new LinkedList<PresentEntity>();
        try (Connection connection = database.Connect();
             PreparedStatement get_present = connection.prepareStatement(load_presents)) {
            var result = get_present.executeQuery();
            ResultSetMetaData meta_data = result.getMetaData();
            while (result.next()) {
                var present = new PresentEntity();
                var item_info = new ItemInfoEntity();
                present.setId(result.getInt("ID"));
                present.setCode(result.getString("Code"));
                item_info.setId(result.getInt(meta_data.getColumnName(17)));
                item_info.setShop_id(result.getInt("ID_Shop"));
                item_info.setCategory(ItemCategory.PRESENT);
                item_info.setPrice(result.getDouble("Price"));
                item_info.setBrand(result.getString("Brand"));
                item_info.setLabel(result.getString("Label"));
                item_info.setDescription(result.getString("Description"));
                item_info.setAvailable(result.getBoolean("Availability"));
                present.setItem_info(item_info);
                present.setWeight(result.getDouble("Weight"));
                present.setHeight(result.getDouble("Height"));
                present.setLength(result.getDouble("Length"));
                present.setWidth(result.getDouble("Width"));
                present.setColor(result.getString("Color"));
                present.setColor_code(Integer.parseInt(result.getString("Color_Code"), 16));

                list.add(present);
            }
            result.close();
        } catch (SQLException e) {
            logger.error("System has not loaded presents from database.", e);
        }
        return list;
    }

    @Override
    public void Save(PresentEntity presentEntity) {
        try (Connection connection = database.Connect();
             PreparedStatement save_present = connection.prepareStatement(PresentDao.save_present);
             PreparedStatement save_item = connection.prepareStatement(save_item_info);
             PreparedStatement item_id = connection.prepareStatement(get_item_info);
             PreparedStatement category_id = connection.prepareStatement(get_category)) {
            var item = presentEntity.getItem_info();
            int index = 1;
            save_item.setInt(index++, item.getShop_id());
            category_id.setString(1, item.getCategory().name());
            var category = category_id.executeQuery();
            if(!category.next()) {
                throw new SQLException("Wrong category info.");
            }
            save_item.setInt(index++, category.getInt("ID"));
            save_item.setDouble(index++, item.getPrice());
            save_item.setString(index++, item.getBrand());
            save_item.setString(index++, item.getLabel());
            save_item.setString(index++, item.getDescription());
            save_item.setBoolean(index++, item.isAvailable());
            save_item.executeUpdate();

            var result = item_id.executeQuery();
            if (!result.next()) {
                throw new SQLException("System has not saved the present to database.");
            }
            index = 1;
            save_present.setString(index++, presentEntity.getCode());
            save_present.setInt(index++, result.getInt("ID"));
            save_present.setDouble(index++, presentEntity.getWeight());
            save_present.setDouble(index++, presentEntity.getHeight());
            save_present.setDouble(index++, presentEntity.getLength());
            save_present.setDouble(index++, presentEntity.getWidth());
            save_present.setString(index++, presentEntity.getColor());
            save_present.setString(index++, String.valueOf(Integer.toHexString(presentEntity.getColor_code())));
            save_present.executeUpdate();

            category.close();
            result.close();
        } catch (SQLException e) {
            logger.error("System has not saved the present to database.", e);
        }
    }

    @Override
    public void Update(PresentEntity presentEntity, String... params) {

    }

    @Override
    public void Delete(PresentEntity presentEntity) {
        if (presentEntity == null) {
            throw new IllegalArgumentException("Present is wrong.");
        }
        try (Connection connection = database.Connect();
             PreparedStatement delete_present = connection.prepareStatement(PresentDao.delete_present)) {
            delete_present.setInt(1, presentEntity.getId());
            delete_present.executeUpdate();
        } catch (SQLException e) {
            logger.error("System has not deleted present from database.", e);
        }
    }
}