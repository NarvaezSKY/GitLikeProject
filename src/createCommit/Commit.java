package createCommit;

import java.sql.Date;

public class Commit {
    public String idCommit ;
    private String message ;
    private String authorName;
    private Date fecha;

    public Commit(String idCommit, String message,String authorName, Date fecha) {
        this.idCommit = idCommit;
        this.message = message;
        this.authorName = authorName;
        this.fecha = fecha;
    }
    
    public String getIdCommit() {
        return idCommit;
    }

    public String getMessage() {
        return message;
    }
    
    public Date getFecha() {
        return fecha;
    }

    public String getAuthorName() {
        return authorName;
    }


}
