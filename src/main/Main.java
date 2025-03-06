package main;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

import createRepository.Repository;
import gitSystem.GitSystem;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GitSystem system = new GitSystem();
        Repository activeRepo = null;

        System.out.println("Bienvenido a GitLike");

        showMenu();
        while (true) {
            System.out.print("\n> ");
            String command = scanner.nextLine().trim().toLowerCase();
            String[] commandSegments = command.split(" ", 2);

            switch (commandSegments[0]) {
                case "git":
                    if (commandSegments.length < 2) {
                        System.out.println("Error: Comando incompleto.");
                        break;

                    }

                    switch (commandSegments[1]) {
                        case "init":
                            System.out.print("Ingresa el nombre del repositorio: ");
                            String repoName = scanner.nextLine().trim();
                            system.createRepository(repoName);
                            break;

                        case "list":
                            Set<String> repos = system.getRepositoryNames();
                            if (repos.isEmpty()) {
                                System.out.println("No hay repositorios creados.");
                            } else {
                                System.out.println("Repositorios existentes:");
                                repos.forEach(repo -> {
                                    Repository r = system.getRepository(repo);
                                    System.out.println(" - " + repo + " (creado el " + r.getCreationTime() + ")");
                                });
                            }
                            break;

                        case "select":
                            System.out.print("Nombre del repositorio: ");
                            String selectRepo = scanner.nextLine().trim();
                            Repository repo = system.getRepository(selectRepo);
                            if (repo == null) {
                                System.out.println("Error: El repositorio no existe.");
                            } else {
                                activeRepo = repo;
                                System.out.println("Repositorio '" + selectRepo + "' seleccionado.");
                            }
                            break;

                        case "branch":
                            if (activeRepo == null) {
                                System.out.println("Error: Selecciona un repositorio primero.");
                                break;
                            }
                            System.out.print("Ingresa el nombre de la nueva rama: ");
                            String branchName = scanner.nextLine().trim();
                            activeRepo.addBranch(branchName);
                            System.out.println("Rama '" + branchName + "' creada en '" + activeRepo.getName() + "'.");
                            break;

                        case "commit":
                            if (activeRepo == null) {
                                System.out.println("Error: Selecciona un repositorio primero.");
                                break;
                            }
                            System.out.print("Escribe el mensaje del commit: ");
                            String commitMessage = scanner.nextLine().trim();
                            activeRepo.getBranches().get(0).addCommit(commitMessage);
                            System.out.println("Commit guardado en '" + activeRepo.getName() + "'.");
                            break;

                        case "log":
                            if (activeRepo == null) {
                                System.out.println("Error: Selecciona un repositorio primero.");
                                break;
                            }
                            List<String> commits = activeRepo.getBranches().get(0).getCommits();
                            if (commits.isEmpty()) {
                                System.out.println("No hay commits en la rama.");
                            } else {
                                System.out.println("Historial de commits:");
                                commits.forEach(commit -> System.out.println(" - " + commit));
                            }
                            break;

                        default:
                            System.out.println("Comando desconocido.");
                    }
                    break;

                case "menu":
                    showMenu();
                    break;

                case "exit":
                    System.out.println("¡Hasta luego!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Comando no reconocido. Usa 'git init', 'git branch', 'git commit', etc.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("\nMenú de comandos:");
        System.out.println(" - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println(" - COMANDOS GIT");
        System.out.println(" - git init: Crear un nuevo repositorio");
        System.out.println(" - git list: Listar todos los repositorios");
        System.out.println(" - git select: Seleccionar un repositorio");
        System.out.println(" - git branch: Crear una nueva rama en el repositorio seleccionado");
        System.out.println(" - git commit: Hacer un commit en la rama principal del repositorio seleccionado");
        System.out.println(" - git log: Ver el historial de commits de la rama principal del repositorio seleccionado");
        System.out.println(" - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println(" - COMANDOS APP");
        System.out.println(" - menu: Mostrar el menú de comandos");
        System.out.println(" - exit: Salir del programa");
    }
}