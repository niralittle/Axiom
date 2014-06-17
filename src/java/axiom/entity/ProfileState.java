package axiom.entity;

/**
 *
 * @author user
 */
public class ProfileState {
    int id;
    String description;

    public ProfileState(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public ProfileState() {
        
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }





}
