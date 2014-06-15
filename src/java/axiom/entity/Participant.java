package axiom.entity;

/**
 *
 * @author user
 */
public class Participant {
    int userId;
    int startupId;
    String role;
    
    //Startup's owner asks user, user asks owner user is already in
    //int state;

    public Participant(int userId, int startupId, String role, int state) {
        this.userId = userId;
        this.startupId = startupId;
        this.role = role;
        //this.state = state;
    }

    public Participant() {
        
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    /*public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }*/


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
