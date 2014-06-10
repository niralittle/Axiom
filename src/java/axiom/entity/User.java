package axiom.entity;

import java.util.ArrayList;
import sun.util.calendar.LocalGregorianCalendar.Date;

/**
 *
 * @author Nira
 */

public class User {

    String firstName;
    String secondName;
    Date birthday;
    int majorID;
    int facultyID;
    ArrayList<String> skills;
    String status;
    int profileStateID;
    String password;
    ArrayList<String> startups; //IDs of startups user has something to do with
    int avatarID; // ID of the blob storing profile pic of the user
    Date registrationDate;
    Date validSince;
    int score;
    
}
