package axiom.entity;

/**
 *
 * @author Nira
 */
public class ProjectType {
    int id;
    String discription;

    public ProjectType(int id, String discription) {
        this.id = id;
        this.discription = discription;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public int getId() {
        return id;
    }


}
