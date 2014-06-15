package axiom.states;

/**
 *
 * @author Nira
 */
public enum ProjectType {
    SOCIAL(1),
    SMALLSUSINESS(2),
    CHARITY(3),
    TECHNOLOGY(4),
    MARKETING(5),
    EDUCATION(6);

    public final int type;

    ProjectType(int type) {
        this.type = type;
    }

    public int toInt() {
        return type;
    }

}
