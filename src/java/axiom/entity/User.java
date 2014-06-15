package axiom.entity;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author Nira
 */

public class User {

    int id;
    String firstName;
    String lastName;
    String email;
    Date birthdate;
    int majorID;
    int facultyID;
    String status;
    int profileState;
    String password;
    int avatarID; // ID of the blob storing profile pic of the user
    Date registrationDate;
    Date validSince;
    int score;
    List<Integer> confirmers;

    public User(int id, String email, String firstName, String secondName, Date birthdate,
            int majorID, int facultyID,
            int profileState, String password, Date registrationDate) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = secondName;
        this.birthdate = birthdate;
        this.majorID = majorID;
        this.facultyID = facultyID;
        this.profileState = profileState;
        this.password = password;
        this.registrationDate = registrationDate;
        this.confirmers = new ArrayList<Integer>();
     }

    public User() {
    }

    void addCofirmer(int userID){
        if (this.confirmers.size() < 3) {
            this.confirmers.add(userID);
        }
        else return;
    }
    public void setConfirmers(List<Integer> c) {
        this.confirmers = c;
    }

    public List getConfirmers() {
        return confirmers;
    }

    boolean isValid() {
        return this.confirmers.size() == 3;
    }

    public String getEmail() {
        return  email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAvatarID() {
        return avatarID;
    }

    public void setAvatarID(int avatarID) {
        this.avatarID = avatarID;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public int getFacultyID() {
        return facultyID;
    }

    public void setFacultyID(int facultyID) {
        this.facultyID = facultyID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getId() {
        return id;
    }

    public int getMajorID() {
        return majorID;
    }

    public void setMajorID(int majorID) {
        this.majorID = majorID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getProfileState() {
        return profileState;
    }

    public void setProfileState(int profileState) {
        this.profileState = profileState;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String secondName) {
        this.lastName = secondName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getValidSince() {
        return validSince;
    }

    public void setValidSince(Date validSince) {
        this.validSince = validSince;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
