package MainPrueba;

import createCommit.Commit;
import java.sql.Date;

public class Main {

    public static void main(String[] args) {
        Commit commitUno = new Commit(
            "1234567890abcdef",
            "Esto es un commit de prueba.",
            "John Doe",
            new Date(System.currentTimeMillis())
        );
        System.out.println(commitUno.getAuthorName());
        System.out.println(commitUno.getMessage());
        System.out.println(commitUno.getIdCommit());
        System.out.println(commitUno.getFecha());
    }

}
