package axiom.entity;

import java.util.ArrayList;
import sun.util.calendar.LocalGregorianCalendar.Date;

/**
 *
 * @author Nira
 */

public class User {

    int id;
    String firstName;
    String secondName;
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

    public User(int id, String firstName, String secondName, Date birthdate,
            int majorID, int facultyID,
            int profileState, String password, Date registrationDate) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthdate = birthdate;
        this.majorID = majorID;
        this.facultyID = facultyID;
        this.profileState = profileState;
        this.password = password;
        this.registrationDate = registrationDate;
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

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
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



    
}
