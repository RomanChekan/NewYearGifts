package NewYearGifts.Actions;

import NewYearGifts.DAO.PresentDao;
import NewYearGifts.Entities.*;
import NewYearGifts.DTO.*;

import java.util.*;

public class Present implements ItemActions<PresentDto> {
    private PresentDao presentDao;

    public Present() {
        this.presentDao = new PresentDao();
    }

    public void setBoxDao(PresentDao presentDao) {
        this.presentDao = presentDao;
    }

    @Override
    public PresentDto New(PresentDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Incorrect box dto");
        }
        var box = new PresentEntity();
        box.setWeight(dto.getWeight());
        box.setCode(dto.getCode());
        var itemDto = dto.getItem_info();
        box.setItem_info(new ItemInfoEntity(0, 1, itemDto.getPrice(),
                itemDto.isAvailable(), itemDto.getCategory(), itemDto.getLocation(),
                itemDto.getLabel(), itemDto.getDescription()));
        box.setColor_code(dto.getColor_code());
        box.setHeight(dto.getHeight());
        box.setWeight(dto.getWeight());
        box.setLength(dto.getLength());
        box.setColor(dto.getColor());
        presentDao.Save(box);
        return dto;
    }

    @Override
    public PresentDto Edit(PresentDto dto) {
        var list = presentDao.LoadAll();
        for (PresentEntity entity : list) {
            if (entity.getCode().equals(dto.getCode())) {
                presentDao.Delete(entity);
                return New(dto);
            }
        }
        return null;
    }

    @Override
    public void Delete(PresentDto dto) {
        var list = presentDao.LoadAll();
        for (PresentEntity entity : list) {
            if (entity.getCode().equals(dto.getCode())) {
                presentDao.Delete(entity);
            }
        }
    }

    @Override
    public List<PresentDto> Show() {
        var list = presentDao.LoadAll();
        var dtoList = new ArrayList<PresentDto>(list.size());
        for (PresentEntity entity : list) {
            var item = entity.getItem_info();
            var dto = new PresentDto(entity.getWeight(), entity.getCode(), entity.getColor_code(), entity.getHeight(), entity.getWidth(), entity.getLength(), entity.getColor(),
                    new ItemDataDto(item.getPrice(), item.isAvailable(), item.getCategory(), item.getBrand(), item.getLabel(), item.getDescription()));
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    public String toString(List<PresentDto> presents) {
        String result = "";
        for(PresentDto present: presents) {
            result += present.toString() + "\n";
        }
        return result;
    }
}
