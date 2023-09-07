package NewYearGifts.Actions;

import NewYearGifts.DAO.*;
import NewYearGifts.Entities.*;
import NewYearGifts.DTO.*;

public class Admin implements AdminActions {
    private UserDao user;
    private ShopDao location;

    public Admin() {
        user = new UserDao();
        location = new ShopDao();
    }

    @Override
    public UserDto NewEmployee(UserDto user) {
        if (user == null) {
            throw new IllegalArgumentException("Wrong user data.");
        }
        var userEntity = new UserEntity();
        userEntity.setVerification(true);
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(user.getPassword());
        userEntity.setRole(user.getRole());
        var identity = new UserIdentityEntity();
        identity.setShop_id(user.getLocation_id());
        identity.setStatus(user.getStatus());
        userEntity.setIdentity(identity);
        this.user.Save(userEntity);
        return user;
    }

    @Override
    public UserDto EditEmployee(UserDto user) {
        if (user == null) {
            throw new IllegalArgumentException("Incorrect User DTO.");
        }
        var userEntity = new UserEntity();
        userEntity.setVerification(true);
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(user.getPassword());
        userEntity.setRole(user.getRole());
        var identity = new UserIdentityEntity();
        identity.setShop_id(user.getLocation_id());
        identity.setStatus(user.getStatus());
        userEntity.setIdentity(identity);
        this.user.Save(userEntity);
        return user;
    }

    @Override
    public LocationDto NewLocation(LocationDto location) {
        if (location == null) {
            throw new IllegalArgumentException("Incorrect Shop DTO.");
        }
        var entity = new ShopEntity();
        entity.setCity(location.getLocation());
        entity.setDescription(location.getDescription());
        this.location.Save(entity);
        return location;
    }
}
