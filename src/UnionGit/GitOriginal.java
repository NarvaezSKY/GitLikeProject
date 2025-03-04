package UnionGit;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

interface RepositoryManager {
    void createRepository(String name);
    Repository getRepository(String name);
    Set<String> getRepositoryNames();
}

class Repository {
    private String name;
    private String creationTime;
    private List<Branch> branches = new ArrayList<>();

    public Repository(String name) {
        this.name = name;
        this.creationTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        branches.add(new Branch("main")); // Se crea la rama principal por defecto
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

class Branch {
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

class GitHubLikeSystem implements RepositoryManager {
    private Map<String, Repository> repositories = new HashMap<>();

    @Override
    public void createRepository(String name) {
        if (repositories.containsKey(name)) {
            System.out.println("⚠️ Error: El repositorio '" + name + "' ya existe.");
        } else {
            Repository repo = new Repository(name);
            repositories.put(name, repo);
            System.out.println("✅ Repositorio '" + name + "' creado con éxito a las " + repo.getCreationTime());
        }
    }

    @Override
    public Repository getRepository(String name) {
        return repositories.get(name);
    }

    @Override
    public Set<String> getRepositoryNames() {
        return repositories.keySet();
    }
}

public class GitOriginal {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GitHubLikeSystem system = new GitHubLikeSystem();
        Repository activeRepo = null;

        System.out.println("🖥️ Bienvenido a MiniGit! Escribe un comando (o 'exit' para salir).");

        while (true) {
            System.out.print("\n> ");
            String command = scanner.nextLine().trim().toLowerCase();
            String[] parts = command.split(" ", 2);

            switch (parts[0]) {
                case "git":
                    if (parts.length < 2) {
                        System.out.println("⚠️ Error: Comando incompleto.");
                        break;
                    }
                    switch (parts[1]) {
                        case "init":
                            System.out.print("📝 Ingresa el nombre del repositorio: ");
                            String repoName = scanner.nextLine().trim();
                            system.createRepository(repoName);
                            break;

                        case "list":
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
                            break;

                        case "select":
                            System.out.print("🔍 Nombre del repositorio: ");
                            String selectRepo = scanner.nextLine().trim();
                            Repository repo = system.getRepository(selectRepo);
                            if (repo == null) {
                                System.out.println("⚠️ Error: El repositorio no existe.");
                            } else {
                                activeRepo = repo;
                                System.out.println("📂 Repositorio '" + selectRepo + "' seleccionado.");
                            }
                            break;

                        case "branch":
                            if (activeRepo == null) {
                                System.out.println("⚠️ Error: Selecciona un repositorio primero.");
                                break;
                            }
                            System.out.print("🆕 Ingresa el nombre de la nueva rama: ");
                            String branchName = scanner.nextLine().trim();
                            activeRepo.addBranch(branchName);
                            System.out.println("🌿 Rama '" + branchName + "' creada en '" + activeRepo.getName() + "'.");
                            break;

                        case "commit":
                            if (activeRepo == null) {
                                System.out.println("⚠️ Error: Selecciona un repositorio primero.");
                                break;
                            }
                            System.out.print("💬 Escribe el mensaje del commit: ");
                            String commitMessage = scanner.nextLine().trim();
                            activeRepo.getBranches().get(0).addCommit(commitMessage); // Se agrega a la rama 'main'
                            System.out.println("✅ Commit guardado en '" + activeRepo.getName() + "'.");
                            break;

                        case "log":
                            if (activeRepo == null) {
                                System.out.println("⚠️ Error: Selecciona un repositorio primero.");
                                break;
                            }
                            List<String> commits = activeRepo.getBranches().get(0).getCommits();
                            if (commits.isEmpty()) {
                                System.out.println("⚠️ No hay commits en la rama.");
                            } else {
                                System.out.println("📜 Historial de commits:");
                                commits.forEach(commit -> System.out.println(" - " + commit));
                            }
                            break;

                        default:
                            System.out.println("❌ Comando desconocido.");
                    }
                    break;

                case "exit":
                    System.out.println("👋 ¡Hasta luego!");
                    scanner.close();
                    return;

                default:
                    System.out.println("❌ Comando no reconocido. Usa 'git init', 'git branch', 'git commit', etc.");
            }
        }
    }
}