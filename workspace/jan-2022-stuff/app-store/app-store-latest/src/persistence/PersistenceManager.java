package persistence;

public interface PersistenceManager {

    void load() throws Exception;
    void store() throws Exception;

}
