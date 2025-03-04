package createCommit;

import java.sql.Date;

public class Commit {
    public String idCommit ;
    private String message ;
    private Date fecha;

    public Commit(String idCommit, String message, Date fecha) {
        this.idCommit = idCommit;
        this.message = message;
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

    
}
