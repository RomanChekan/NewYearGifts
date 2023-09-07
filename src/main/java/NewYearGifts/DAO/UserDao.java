package NewYearGifts.DAO;

import NewYearGifts.Entities.*;
import NewYearGifts.EnumConstants.*;
import org.apache.logging.log4j.*;
import NewYearGifts.DataBaseConnection;

import java.sql.*;
import java.util.*;

public class UserDao implements Dao<UserEntity, String> {
    private static final Logger logger = LogManager.getLogger();
    private static final DataBaseConnection database = new DataBaseConnection();
    private static final String load_user = "select * from [User] u join Access a on a.ID = u.Role_ID join User_Data ud " +
                                            "on u.ID = ud.ID where Email = ?";
    private static final String load_users = "select * from [User] u join Access a on a.ID = u.Role_ID join User_Data ud on u.ID = us.ID";
    private static final String save_user = "insert into [User] (Email, Password, Verification, Role_ID) values (?, ?, ?, ?)";
    private static final String save_user_identity = "insert into User_Data (ID_Shop, Status) values (?,?)";
    private static final String delete_user = "delete from [User] u where u.ID = ?";


    @Override
    public Optional<UserEntity> Get(String key) {
        var user = new UserEntity();
        var identity = new UserIdentityEntity();
        int index = 1;
        try (Connection connection = database.Connect();
             PreparedStatement get_user = connection.prepareStatement(load_user)) {
            get_user.setString(1, key);
            var result = get_user.executeQuery();
            if (!result.next()) {
                throw new SQLException("User has not been found");
            }
            user.setId(result.getInt(index++));
            user.setEmail(result.getString(index++));
            user.setPassword(result.getString(index++));
            user.setVerification(result.getBoolean(index++));
            user.setRole(Roles.valueOf(result.getString("Role")));
            identity.setId(user.getId());
            identity.setShop_id(result.getInt("ID_Shop"));
            identity.setStatus(UserStatus.valueOf(result.getString("Status")));
            user.setIdentity(identity);
            result.close();
        } catch (SQLException e) {
            logger.error("System cannot find matching user in database.", e);
        }
        return Optional.of(user);
    }

    @Override
    public List<UserEntity> LoadAll() {
        var list = new ArrayList<UserEntity>();
        try (Connection connection = database.Connect();
             PreparedStatement get_user = connection.prepareStatement(load_users)) {
            var result = get_user.executeQuery();
            while (result.next()) {
                var user = new UserEntity();
                var identity = new UserIdentityEntity();
                int index = 1;
                user.setId(result.getInt(index++));
                user.setEmail(result.getString(index++));
                user.setPassword(result.getString(index++));
                user.setVerification(result.getBoolean(index++));
                user.setRole(Roles.valueOf(result.getString("Role")));
                identity.setId(user.getId());
                identity.setShop_id(result.getInt("ID_Shop"));
                identity.setStatus(UserStatus.valueOf(result.getString("Status")));
                user.setIdentity(identity);
                list.add(user);
            }
            result.close();
        } catch (SQLException e) {
            logger.error("System cannot load users from database", e);
        }
        return list;
    }

    @Override
    public void Save(UserEntity userEntity) {
        if (userEntity == null) {
            throw new NoSuchElementException("User is incorrect.");
        }
        try (Connection connection = database.Connect();
             PreparedStatement role = connection.prepareStatement("select ID from Access where Access.Role = ?");
             PreparedStatement save_user = connection.prepareStatement(UserDao.save_user);
             PreparedStatement save_user_identity = connection.prepareStatement(UserDao.save_user_identity)) {
            int index = 1;
            save_user.setString(index++, userEntity.getEmail());
            save_user.setString(index++, userEntity.getPassword());
            save_user.setBoolean(index++, true);
            role.setString(1, userEntity.getRole().name());
            var result = role.executeQuery();
            if(!result.next()) {
                throw new SQLException("Wrong role info.");
            }
            save_user.setInt(index++, result.getInt("ID"));
            index = 1;
            save_user_identity.setInt(index++, userEntity.getIdentity().getShop_id());
            save_user_identity.setString(index++, userEntity.getIdentity().getStatus().name());

            save_user_identity.executeUpdate();
            save_user.executeUpdate();

            result.close();
        } catch (SQLException ex) {
            logger.error("System cannot delete user from database.", ex);
        }
    }

    @Override
    public void Update(UserEntity userEntity, String... params) {

    }

    @Override
    public void Delete(UserEntity userEntity) {
        if (userEntity == null) {
            throw new NoSuchElementException("User data is incorrect");
        }
        try (Connection connection = database.Connect();
             PreparedStatement delete_user = connection.prepareStatement(UserDao.delete_user)) {
            delete_user.setInt(1, userEntity.getId());
            delete_user.executeUpdate();
        } catch (SQLException ex) {
            logger.error("Cannot delete user from database", ex);
        }
    }
}
