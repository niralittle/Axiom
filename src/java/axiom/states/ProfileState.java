package axiom.states;

/**
 *
 * @author Nira
 */
public enum ProfileState {
    READONLY(1),
    ACTIVE(2),
    BLOCKED(3);

    private final int state;

    ProfileState(int state) {
        this.state = state;
    }
    /**
     * Returns state id
     * @return id
     */
    public int toInt() {
        return state;
    }
}
