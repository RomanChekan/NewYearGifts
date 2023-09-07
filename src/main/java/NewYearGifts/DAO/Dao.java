package NewYearGifts.DAO;

import java.util.*;

public interface Dao<T, K> {

    Optional<T> Get(K key);

    List<T> LoadAll();

    void Save(T t);

    void Update(T t, String... params);

    void Delete(T t);
}
