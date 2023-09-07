package NewYearGifts.DAO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import NewYearGifts.DataBaseConnection;
import NewYearGifts.Entities.ShopEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShopDao implements Dao<ShopEntity, Integer> {
    private static final Logger logger = LogManager.getLogger();
    private static final DataBaseConnection database = new DataBaseConnection();
    private static final String get_location = "select * from Shop where ID = ?";
    private static final String save_location = "insert into Shop (City, Address, Description) values (?, ?, ?)";
    private static final String delete_location = "delete from Shop where ID = ?";


    @Override
    public Optional<ShopEntity> Get(Integer key) {
        var location = new ShopEntity();
        try (Connection connection = database.Connect();
             PreparedStatement shop = connection.prepareStatement(get_location)) {
            shop.setInt(1, key);
            var res = shop.executeQuery();
            if (!res.next()) {
                throw new SQLException("System has not found matching location.");
            }
            location.setId(res.getInt("ID"));
            location.setCity(res.getString("City"));
            location.setAddress(res.getString("Address"));
            location.setDescription(res.getString("Description"));
            res.close();
        } catch (SQLException e) {
            logger.error("System cannot get access to location in database.", e);
        }
        return Optional.of(location);
    }

    @Override
    public List<ShopEntity> LoadAll() {
        return new ArrayList<>();
    }

    @Override
    public void Save(ShopEntity shopEntity) {
        try (Connection connection = database.Connect();
             PreparedStatement new_shop = connection.prepareStatement(save_location)) {
            new_shop.setString(1, shopEntity.getCity());
            new_shop.setString(2, shopEntity.getDescription());
            new_shop.executeUpdate();
        } catch (SQLException e) {
            logger.error("System has not saved data about the location to database.", e);
        }
    }

    @Override
    public void Update(ShopEntity shopEntity, String... params) {

    }

    @Override
    public void Delete(ShopEntity shopEntity) {
        try (Connection connection = database.Connect();
             PreparedStatement delete_shop = connection.prepareStatement(delete_location)) {
            delete_shop.setInt(1, shopEntity.getId());
            delete_shop.executeUpdate();
        } catch (SQLException e) {
            logger.error("System has not deleted location from the database.", e);
        }
    }
}
