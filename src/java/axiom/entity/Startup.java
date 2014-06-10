package axiom.entity;
import java.util.Map;

/**
 *
 * @author Nira
 */

public class Startup {

    String name;
    String owner;
    Map<Integer, String> participants; // userID and roles of participants
    int projectType; // IT, socialStudy, small bussiness, marketing, charity
    String projectDescription;
    int startupStateID;

}
