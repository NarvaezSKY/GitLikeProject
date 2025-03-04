package UnionGit.commands;

import UnionGit.core.GitHubLikeSystem;
import UnionGit.models.Repository;
import java.util.Set;

public class GitListCommand implements GitCommand {
    @Override
    public void execute(GitHubLikeSystem system, String[] args) {
        Set<String> repos = system.getRepositoryNames();
        if (repos.isEmpty()) {
            System.out.println("⚠️ No hay repositorios creados.");
        } else {
            System.out.println("📂 Repositorios existentes:");
            repos.forEach(repo -> {
                Repository r = system.getRepository(repo);
                System.out.println(" - " + repo + " (creado el " + r.getCreationTime() + ")");
            });
        }
    }
}
