package UnionGit.core;

import UnionGit.commands.*;

import java.util.HashMap;
import java.util.Map;

public class GitCommandProcessor {
    private final Map<String, GitCommand> commands = new HashMap<>();

    public GitCommandProcessor() {
        commands.put("init", new GitInitCommand());
        commands.put("list", new GitListCommand());
        commands.put("commit", new GitCommitCommand());
        commands.put("branch", new GitBranchCommand());
        commands.put("log", new GitLogCommand());
    }

    public void execute(String command, GitHubLikeSystem system, String[] args) {
        GitCommand cmd = commands.get(command);
        if (cmd != null) {
            cmd.execute(system, args);
        } else {
            System.out.println("Paila, Comando desconocido. No Sirve");
        }
    }
}
