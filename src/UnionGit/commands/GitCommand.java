package UnionGit.commands;

import UnionGit.core.GitHubLikeSystem;

public interface GitCommand {
    void execute(GitHubLikeSystem system, String[] args);
}
