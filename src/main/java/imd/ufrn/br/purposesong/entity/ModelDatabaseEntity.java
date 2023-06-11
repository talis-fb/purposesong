package imd.ufrn.br.purposesong.entity;

import java.util.Optional;
import java.util.UUID;

public abstract class ModelDatabaseEntity {
    Optional<UUID> id = Optional.empty();

    public Optional<UUID> getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = Optional.of(id);
    }
}
