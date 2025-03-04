package UnionGit.commands;

import UnionGit.core.GitHubLikeSystem;
import UnionGit.models.Repository;
import java.util.Scanner;

public class GitCommitCommand implements GitCommand {
    @Override
    public void execute(GitHubLikeSystem system, String[] args) {
        Repository activeRepo = system.getActiveRepository();
        if (activeRepo == null) {
            System.out.println("⚠️ Error: Selecciona un repositorio primero.");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.print("💬 Escribe el mensaje del commit: ");
        String commitMessage = scanner.nextLine().trim();
        activeRepo.getBranches().get(0).addCommit(commitMessage);
        System.out.println("✅ Commit guardado en '" + activeRepo.getName() + "'.");
    }
}
