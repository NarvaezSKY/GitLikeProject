package createBranch;

import createCommit.Commit;
import java.sql.Date;

public class Rama {
    
    class CommitUno extends Commit{

       public CommitUno(String idCommit, String message, String authorName, Date fecha) {
                   super(idCommit, message, authorName, fecha);
                   System.out.println("XDDDDD");
               }
       
           public String getIdCommit(){
               return idCommit;
        
       }
    }
}

