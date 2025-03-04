package UnionGit.commands;

import UnionGit.core.GitHubLikeSystem;
import java.util.Scanner;

public class GitInitCommand implements GitCommand {
    @Override
    public void execute(GitHubLikeSystem system, String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("📝 Ingresa el nombre del repositorio: ");
        String repoName = scanner.nextLine().trim();
        system.createRepository(repoName);
    }
}
