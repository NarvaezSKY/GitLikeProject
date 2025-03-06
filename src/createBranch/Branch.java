package createBranch;

import java.util.ArrayList;
import java.util.List;

public class Branch {
    private String name;
    private List<String> commits = new ArrayList<>();

    public Branch(String name) {
        this.name = name;
    }

    public void addCommit(String message) {
        commits.add(message);
    }

    public List<String> getCommits() {
        return commits;
    }

    public String getName() {
        return name;
    }
}
