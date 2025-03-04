package MainPrueba;

import createCommit.Commit;
import java.sql.Date;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String comm;

        try (Scanner scanner = new Scanner(System.in)) {

            int menu = 0;
            System.out.println("1. Crear commit");
            System.out.println("2. Salir");
            System.out.print("Ingresa una opción: ");

            menu = scanner.nextInt();

            if (menu == 1) {
                System.out.print("Ingresa el commit: ");
                comm = scanner.nextLine();
            
                Commit commitUno = new Commit(
                        "1234567890abcdef",
                        comm,
                        "John Doe",
                        new Date(System.currentTimeMillis())
                );

                System.out.println(commitUno.getAuthorName());
                System.out.println(commitUno.getMessage());
                System.out.println(commitUno.getIdCommit());
                System.out.println(commitUno.getFecha());

            } else {
                System.out.println("Adios");
                return;
            }
		
        }
    }

}
