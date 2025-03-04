package MainPrueba;

import createCommit.Commit;
import java.sql.Date;

public class Main {

    public static void main(String[] args) {
        Commit commitUno = new Commit(
            "Esto es un commit de prueba.",
            "1234567890abcdef",
            "John Doe",
            new Date(System.currentTimeMillis())
        );
        System.out.println(commitUno.getAuthorName());
        System.out.println(commitUno.getMessage());
    }

}
