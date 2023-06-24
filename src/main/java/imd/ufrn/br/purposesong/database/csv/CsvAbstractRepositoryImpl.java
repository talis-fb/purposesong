package imd.ufrn.br.purposesong.database.csv;

import imd.ufrn.br.purposesong.database.AbstractRepository;
import imd.ufrn.br.purposesong.entity.ModelDatabaseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public abstract class CsvAbstractRepositoryImpl<Entity extends ModelDatabaseEntity>
        implements AbstractRepository<Entity> {
    abstract List<Entity> readCsvFile();

    abstract void writeCsvFile(List<Entity> users);

    @Override
    public Optional<Entity> findById(UUID id) {
        List<Entity> values = readCsvFile();
        return values.stream()
                .filter(user -> user.getId().equals(Optional.of(id)))
                .findFirst();
    }

    @Override
    public List<Entity> findAll() {
        return readCsvFile();
    }

    @Override
    public void update(UUID id, Entity value) {
        ArrayList<Entity> values = new ArrayList<>(readCsvFile());
        for (int i = 0; i < values.size(); i++) {
            Entity user = values.get(i);
            if (user.getId().equals(Optional.of(id))) {
                values.set(i, value);
                break;
            }
        }
        writeCsvFile(values);
    }

    @Override
    public Entity create(Entity value) {
        ArrayList<Entity> values = new ArrayList(readCsvFile());
        value.setId(UUID.randomUUID());
        values.add(value);
        writeCsvFile(values);
        return value;
    }

    @Override
    public void delete(UUID id) {
        ArrayList<Entity> values = new ArrayList(readCsvFile());
        values.removeIf(user -> user.getId().equals(Optional.of(id)));
        writeCsvFile(values);
    }
}
