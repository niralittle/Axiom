package axiom.entity;

import java.sql.Array;
import java.sql.Blob;

/**
 *
 * @author user
 */
public class Avatar {

    int id;
    String fileName;
    Blob image;

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob i) {
        this.image = i;
    }

    public Avatar(int id, String fileName) {
        this.id = id;
        this.fileName = fileName;
    }

    public Avatar() { 
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
