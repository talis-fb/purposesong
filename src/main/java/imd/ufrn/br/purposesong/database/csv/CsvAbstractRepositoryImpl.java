package imd.ufrn.br.purposesong.database.csv;

import imd.ufrn.br.purposesong.database.AbstractRepository;
import imd.ufrn.br.purposesong.entity.ModelDatabaseEntity;
import imd.ufrn.br.purposesong.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public abstract class CsvAbstractRepositoryImpl<Entity extends ModelDatabaseEntity> implements AbstractRepository<Entity> {
    private static final String CSV_FILE_PATH = "users.csv";
    private static final String[] CSV_HEADER = { "id","name","email","password","access" };

    private static final CsvOperator csvOperator = new CsvOperator(CSV_FILE_PATH, CSV_HEADER);

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
        List<Entity> values = readCsvFile();
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



    abstract List<Entity> readCsvFile();
    abstract void writeCsvFile(List<Entity> users);
}
