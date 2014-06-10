package axiom.entity;

/**
 *
 * @author Nira
 */
public class StartupState {

    int id;
    String description;

    public StartupState(int id, String description) {
        this.id = id;
        this.description = description;
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


}
