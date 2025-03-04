package UnionGit.commands;

import UnionGit.core.GitHubLikeSystem;
import UnionGit.models.Repository;
import java.util.List;

public class GitLogCommand implements GitCommand {
    @Override
    public void execute(GitHubLikeSystem system, String[] args) {
        Repository activeRepo = system.getActiveRepository();
        if (activeRepo == null) {
            System.out.println("⚠️ Error: No hay un repositorio seleccionado.");
            return;
        }

        List<String> commits = activeRepo.getBranches().get(0).getCommits();
        if (commits.isEmpty()) {
            System.out.println("⚠️ No hay commits en la rama actual.");
        } else {
            System.out.println("📜 Historial de commits:");
            for (String commit : commits) {
                System.out.println(" - " + commit);
            }
        }
    }
}
