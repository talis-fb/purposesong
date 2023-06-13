package imd.ufrn.br.purposesong.database.inmemory;

import imd.ufrn.br.purposesong.database.AbstractRepository;
import imd.ufrn.br.purposesong.entity.ModelDatabaseEntity;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public abstract class InMemoryAbstractRepository<Entity extends ModelDatabaseEntity>
        implements AbstractRepository<Entity> {
    // This should return the static value that 'mock' database. All other methods
    // here uses this to iterate with datas
    abstract public List<Entity> findAll();

    @Override
    public Optional<Entity> findById(UUID id) {
        return findAll().stream()
                .filter(user -> user.getId().isPresent() && user.getId().get().equals(id))
                .findFirst();
    }

    @Override
    public Entity create(Entity value) {
        if (value.getId().isEmpty()) {
            System.out.println("[ERRO]: Voce deve criar usuario nao registrados no banco de dados");
        }

        value.setId(UUID.randomUUID());
        this.findAll().add(value);
        return value;
    }

    @Override
    public void update(UUID id, Entity value) {
        for (int i = 0; i < this.findAll().size(); i++) {
            Optional<UUID> user_id = this.findAll().get(i).getId();
            if (user_id.isPresent() && user_id.get().equals(id)) {
                this.findAll().set(i, value);
                break;
            }
        }
    }

    @Override
    public void delete(UUID id) {
        Iterator<Entity> iterator = this.findAll().iterator();
        while (iterator.hasNext()) {
            Entity person = iterator.next();

            if (person.getId().isEmpty()) {
                continue;
            }

            if (person.getId().get().equals(id)) {
                iterator.remove();
            }
        }
    }
}
