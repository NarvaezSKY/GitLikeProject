package UnionGit;

import UnionGit.core.GitCommandProcessor;
import UnionGit.core.GitHubLikeSystem;

import java.util.Scanner;

public class GitCLI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GitHubLikeSystem system = new GitHubLikeSystem();
        GitCommandProcessor commandProcessor = new GitCommandProcessor();

        System.out.println("🖥️ Bienvenido a MiniGit!");

        while (true) {
            System.out.print("\n> ");
            String command = scanner.nextLine().trim();
            if (command.equals("exit")) break;
            commandProcessor.execute(command, system, command.split(" "));
        }

        scanner.close();
        System.out.println("👋 ¡Hasta luego mi hermano!");
    }
}
