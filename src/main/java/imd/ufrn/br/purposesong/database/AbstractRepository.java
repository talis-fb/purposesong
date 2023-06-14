package imd.ufrn.br.purposesong.database;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AbstractRepository<T> {
    Optional<T> findById(UUID id);

    List<T> findAll();

    void update(UUID id, T value);

    T create(T value);

    void delete(UUID id);
}
