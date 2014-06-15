package axiom.entity;

/**
 *
 * @author user
 */
public class Faculty {

    int id;
    String name;

    public Faculty(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Faculty() {
        
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }


}
