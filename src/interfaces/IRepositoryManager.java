package interfaces;

import java.util.Set;
import createRepository.Repository;

public interface IRepositoryManager {
    void createRepository(String name);

    Repository getRepository(String name);

    Set<String> getRepositoryNames();
}