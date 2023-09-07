package NewYearGifts.Actions;

import NewYearGifts.DTO.PresentDto;

import java.util.List;

public interface ItemActions<D> {
    D New(D dto);

    D Edit(D dto);

    void Delete(D dto);

    List<D> Show();

    String toString(List<D> list);
}
