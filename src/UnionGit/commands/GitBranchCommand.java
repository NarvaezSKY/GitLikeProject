package UnionGit.commands;

import UnionGit.core.GitHubLikeSystem;
import UnionGit.models.Repository;
import java.util.Scanner;

public class GitBranchCommand implements GitCommand {
    @Override
    public void execute(GitHubLikeSystem system, String[] args) {
        Repository activeRepo = system.getActiveRepository();
        if (activeRepo == null) {
            System.out.println("⚠️ Error: Selecciona un repositorio primero.");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.print("🆕 Ingresa el nombre de la nueva rama: ");
        String branchName = scanner.nextLine().trim();
        activeRepo.addBranch(branchName);
        System.out.println("🌿 Rama '" + branchName + "' creada en '" + activeRepo.getName() + "'.");
    }
}
