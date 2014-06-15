package axiom.entity;

/**
 *
 * @author Nira
 */

public class Startup {

    int id;
    String name;
    int ownerId;
    int projectType; // IT, socialStudy, small bussiness, marketing, charity
    String description;
    int startupStateID;

    public Startup(int id, String name, int ownerId, int projectType, String description, int startupStateID) {
        this.id = id;
        this.name = name;
        this.ownerId = ownerId;
        this.projectType = projectType;
        this.description = description;
        this.startupStateID = startupStateID;
    }

    public Startup() { 
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public int getProjectType() {
        return projectType;
    }

    public void setProjectType(int projectType) {
        this.projectType = projectType;
    }

    public int getStartupStateID() {
        return startupStateID;
    }

    public void setStartupStateID(int startupStateID) {
        this.startupStateID = startupStateID;
    }

    public void setId(int aInt) {
        this.id = id;
    }
}
