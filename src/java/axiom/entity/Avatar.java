package axiom.entity;

/**
 *
 * @author user
 */
public class Avatar {

    int id;
    String fileName;

    public Avatar(int id, String fileName) {
        this.id = id;
        this.fileName = fileName;
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

}
