package NewYearGifts.Actions;

import NewYearGifts.DAO.UserDao;
import NewYearGifts.DTO.UserDto;

public class LogIn implements LogInAction {
    private UserDao user;

    public LogIn() {
        user = new UserDao();
    }

    @Override
    public UserDto LogIn(UserDto dto) {
        var user = this.user.Get(dto.getEmail());
        if (user.isPresent()) {
            var entity = user.get();
            return new UserDto(entity.getEmail(), entity.getPassword(), entity.getIdentity().getShop_id(),
                    entity.getRole(), entity.getIdentity().getStatus());
        }
        return null;
    }
}
