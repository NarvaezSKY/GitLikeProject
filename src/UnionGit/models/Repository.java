package UnionGit.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Repository {
    private final String name;
    private final String creationTime;
    private final List<Branch> branches = new ArrayList<>();

    public Repository(String name) {
        this.name = name;
        this.creationTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        branches.add(new Branch("main"));
    }

    public void addBranch(String branchName) {
        branches.add(new Branch(branchName));
    }

    public List<Branch> getBranches() {
        return branches;
    }

    public String getName() {
        return name;
    }

    public String getCreationTime() {
        return creationTime;
    }
}
