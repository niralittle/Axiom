package axiom.entity;

/**
 *
 * @author user
 */
public class Participant {
    int userId;
    int startupId;
    String role;

    public Participant(int userId, int startupId, String role) {
        this.userId = userId;
        this.startupId = startupId;
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getStartupId() {
        return startupId;
    }

    public void setStartupId(int startupId) {
        this.startupId = startupId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }



}
