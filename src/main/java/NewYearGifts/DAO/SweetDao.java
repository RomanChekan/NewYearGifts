package NewYearGifts.DAO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import NewYearGifts.DataBaseConnection;
import NewYearGifts.Entities.SweetEntity;
import NewYearGifts.Entities.ItemInfoEntity;
import NewYearGifts.EnumConstants.ItemCategory;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class SweetDao implements Dao<SweetEntity, Integer> {
    private static final Logger logger = LogManager.getLogger();
    private static final DataBaseConnection database = new DataBaseConnection();
    private static final String get_sweet = "select * from Sweet s join Item_Data id on id.ID = s.ID_Item_Data where s.ID = ?";
    private static final String load_sweets = "select * from Sweet s join Item_data id on id.ID = s.ID_Item_Data";
    private static final String get_item_info = "select ID from Item_Data order by ID desc";
    private static final String get_category = "select * from Category where [Name] = ?";
    private static final String get_id_component = "select ID from Components order by ID desc";
    private static final String get_components = "select * from Components where ID = ?";
    private static final String save_sweet = "insert into Sweet (Type, Code, ID_Item_Data, Weight, Sugar_Percentage, ID_Component)" +
                                             " values (?,?,?,?,?,?)";
    private static final String save_components = "insert into Components (Name) values (?)";
    private static final String save_item_info = "insert into Item_Data (ID_Shop, ID_Category, Price, Brand, Label, Description, " +
                                                 "Availability) values (?,?,?,?,?,?,?)";
    private static final String delete_sweet = "delete from Sweet where ID = ?";


    @Override
    public Optional<SweetEntity> Get(Integer key) {
        var sweet = new SweetEntity();
        var item_info = new ItemInfoEntity();
        try (Connection connection = database.Connect();
             PreparedStatement get_sweet = connection.prepareStatement(SweetDao.get_sweet);
             PreparedStatement get_components = connection.prepareStatement(SweetDao.get_components)) {
            get_sweet.setInt(1, key);
            ResultSet result = get_sweet.executeQuery();
            ResultSetMetaData meta_data = result.getMetaData();
            if (!result.next()) {
                throw new SQLException("System has not found matching sweet.");
            }
            sweet.setId(result.getInt("ID"));
            sweet.setType_of_sweet(result.getString("Type"));
            sweet.setCode(result.getString("Code"));
            item_info.setId(result.getInt(meta_data.getColumnName(7)));
            item_info.setShop_id(result.getInt("ID_Shop"));
            item_info.setCategory(ItemCategory.SWEET);
            item_info.setPrice(result.getDouble("Price"));
            item_info.setBrand(result.getString("Brand"));
            item_info.setLabel(result.getString("Label"));
            item_info.setDescription(result.getString("Description"));
            item_info.setAvailable(result.getBoolean("Availability"));
            sweet.setItem_info(item_info);
            sweet.setWeight(result.getDouble("Weight"));
            sweet.setSugar(result.getDouble("Sugar_Percentage"));
            get_components.setInt(1, result.getInt("ID_Component"));
            var component = get_components.executeQuery();
            if(!component.next()) {
                throw new SQLException("System has not found components.");
            }
            sweet.setComponents(List.of(component.getString("Name")));

            component.close();
            result.close();
        } catch (SQLException e) {
            logger.error("System has not got information about sweet.", e);
        }
        return Optional.of(sweet);
    }

    @Override
    public List<SweetEntity> LoadAll() {
        var list = new LinkedList<SweetEntity>();
        try (Connection connection = database.Connect();
             PreparedStatement get_sweet = connection.prepareStatement(load_sweets);
             PreparedStatement get_components = connection.prepareStatement(SweetDao.get_components)) {
            ResultSet result = get_sweet.executeQuery();
            ResultSetMetaData meta_data = result.getMetaData();
            while (result.next()) {
                var sweet = new SweetEntity();
                var item_info = new ItemInfoEntity();
                sweet.setId(result.getInt("ID"));
                sweet.setType_of_sweet(result.getString("Type"));
                sweet.setCode(result.getString("Code"));
                item_info.setId(result.getInt(meta_data.getColumnName(7)));
                item_info.setShop_id(result.getInt("ID_Shop"));
                item_info.setCategory(ItemCategory.SWEET);
                item_info.setPrice(result.getDouble("Price"));
                item_info.setBrand(result.getString("Brand"));
                item_info.setLabel(result.getString("Label"));
                item_info.setDescription(result.getString("Description"));
                item_info.setAvailable(result.getBoolean("Availability"));
                sweet.setItem_info(item_info);
                sweet.setWeight(result.getDouble("Weight"));
                sweet.setSugar(result.getDouble("Sugar_Percentage"));
                get_components.setInt(1, result.getInt("ID_Component"));
                var component = get_components.executeQuery();
                if(!component.next()) {
                    throw new SQLException("System has not found components.");
                }
                sweet.setComponents(List.of(component.getString("Name")));
                list.add(sweet);
                component.close();
            }
            result.close();
        } catch (SQLException e) {
            logger.error("System cannot load sweets from database.", e);
        }
        return list;
    }

    @Override
    public void Save(SweetEntity sweetEntity) {
        try (Connection connection = database.Connect();
             PreparedStatement save_sweet = connection.prepareStatement(SweetDao.save_sweet);
             PreparedStatement save_item = connection.prepareStatement(save_item_info);
             PreparedStatement save_components = connection.prepareStatement(SweetDao.save_components);
             PreparedStatement component_id = connection.prepareStatement(get_id_component);
             PreparedStatement item_id = connection.prepareStatement(get_item_info);
             PreparedStatement category_id = connection.prepareStatement(get_category)) {
            var item = sweetEntity.getItem_info();
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

            save_components.setString(1, sweetEntity.getComponents().get(0));
            var result = item_id.executeQuery();
            if (!result.next()) {
                throw new SQLException("Wrong item info.");
            }
            index = 1;
            save_sweet.setString(index++, sweetEntity.getType_of_sweet());
            save_sweet.setString(index++, sweetEntity.getCode());
            save_sweet.setInt(index++, result.getInt("ID"));
            save_sweet.setDouble(index++, sweetEntity.getWeight());
            save_sweet.setDouble(index++, sweetEntity.getSugar());

            var component = component_id.executeQuery();
            if (!component.next()) {
                throw new SQLException("Wrong component/s.");
            }
            save_sweet.setInt(index++, component.getInt("ID"));
            save_sweet.executeUpdate();

            category.close();
            result.close();
            component.close();
        } catch (SQLException e) {
            logger.error("System has not saved present info into database.", e);
        }
    }

    @Override
    public void Update(SweetEntity sweetEntity, String... params) {

    }

    @Override
    public void Delete(SweetEntity sweetEntity) {
        if (sweetEntity == null) {
            throw new IllegalArgumentException("Wrong item.");
        }
        try (Connection connection = database.Connect();
             PreparedStatement delete_sweet = connection.prepareStatement(SweetDao.delete_sweet)) {
            delete_sweet.setInt(1, sweetEntity.getId());
            delete_sweet.executeUpdate();
        } catch (SQLException e) {
            logger.error("System cannot delete the sweet from database.", e);
        }
    }
}
