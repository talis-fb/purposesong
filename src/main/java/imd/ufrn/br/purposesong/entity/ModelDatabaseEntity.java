package imd.ufrn.br.purposesong.entity;

import java.util.Optional;

public abstract class ModelDatabaseEntity {
    Optional<String> id;

    public Optional<String> getId() {
        return id;
    }

    public void setId(String id) {
        this.id = Optional.of(id);
    }
}
