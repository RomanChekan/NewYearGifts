package NewYearGifts.Actions;

import NewYearGifts.DAO.SweetDao;
import NewYearGifts.DTO.PresentDto;
import NewYearGifts.Entities.SweetEntity;
import NewYearGifts.Entities.ItemInfoEntity;
import NewYearGifts.DTO.SweetDto;
import NewYearGifts.DTO.ItemDataDto;

import java.util.ArrayList;
import java.util.List;

public class Sweet implements ItemActions<SweetDto> {
    private SweetDao sweet_dao;

    public Sweet() {
        this.sweet_dao = new SweetDao();
    }

    public void setSweet_dao(SweetDao sweet_dao) {
        this.sweet_dao = sweet_dao;
    }

    @Override
    public SweetDto New(SweetDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Incorrect sweet DTO.");
        }
        var chocolate = new SweetEntity();
        chocolate.setWeight(dto.getWeight());
        chocolate.setCode(dto.getCode());
        var itemDto = dto.getItem_info();
        chocolate.setItem_info(new ItemInfoEntity(0, 1, itemDto.getPrice(),
                itemDto.isAvailable(), itemDto.getCategory(), itemDto.getLocation(),
                itemDto.getLabel(), itemDto.getDescription()));
        chocolate.setSugar(dto.getSugar());
        chocolate.setType_of_sweet(dto.getSweet_type());
        chocolate.setComponents(dto.getComponents());
        sweet_dao.Save(chocolate);
        return dto;
    }

    @Override
    public SweetDto Edit(SweetDto dto) {
        var list = sweet_dao.LoadAll();
        for (SweetEntity entity : list) {
            if (entity.getCode().equals(dto.getCode())) {
                sweet_dao.Delete(entity);
                return New(dto);
            }
        }
        return null;
    }

    @Override
    public void Delete(SweetDto dto) {
        var list = sweet_dao.LoadAll();
        for (SweetEntity entity : list) {
            if (entity.getCode().equals(dto.getCode())) {
                sweet_dao.Delete(entity);
            }
        }
    }

    @Override
    public List<SweetDto> Show() {
        var sweets = sweet_dao.LoadAll();
        var list = new ArrayList<SweetDto>(sweets.size());
        for (SweetEntity entity : sweets) {
            var item_info = entity.getItem_info();
            var sweet_dto = new SweetDto(entity.getWeight(), entity.getCode(), entity.getSugar(), entity.getType_of_sweet(), entity.getComponents(),
                    new ItemDataDto(item_info.getPrice(), item_info.isAvailable(), item_info.getCategory(), item_info.getBrand(), item_info.getLabel(), item_info.getDescription()));
            list.add(sweet_dto);
        }
        return list;
    }

    @Override
    public String toString(List<SweetDto> sweets) {
        String result = "";
        for(SweetDto present: sweets) {
            result += present.toString() + "\n";
        }
        return result;
    }
}
