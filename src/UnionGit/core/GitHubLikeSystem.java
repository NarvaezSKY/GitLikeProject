package UnionGit.core;

import UnionGit.models.Repository;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class GitHubLikeSystem implements RepositoryManager {
    private final Map<String, Repository> repositories = new HashMap<>();
    private Repository activeRepo = null;

    @Override
    public void createRepository(String name) {
        if (repositories.containsKey(name)) {
            System.out.println("⚠️ Error: El repositorio '" + name + "' ya existe.");
        } else {
            Repository repo = new Repository(name);
            repositories.put(name, repo);
            System.out.println("✅ Repositorio '" + name + "' creado con éxito a las " + repo.getCreationTime());
        }
    }

    @Override
    public Repository getRepository(String name) {
        return repositories.get(name);
    }

    @Override
    public Set<String> getRepositoryNames() {
        return repositories.keySet();
    }

    public Repository getActiveRepository() {
        return activeRepo;
    }

    public void setActiveRepository(String name) {
        activeRepo = repositories.get(name);
    }
}
