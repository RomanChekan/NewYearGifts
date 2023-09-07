package NewYearGifts.Actions;

import NewYearGifts.DTO.LocationDto;
import NewYearGifts.DTO.UserDto;

public interface AdminActions {
    UserDto NewEmployee(UserDto user);

    UserDto EditEmployee(UserDto user);

    LocationDto NewLocation(LocationDto shop);
}