package UnionGit.core;

import UnionGit.models.Repository;
import java.util.Set;

public interface RepositoryManager {
    void createRepository(String name);
    Repository getRepository(String name);
    Set<String> getRepositoryNames();
}
