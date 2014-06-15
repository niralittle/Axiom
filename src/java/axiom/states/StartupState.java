package axiom.states;

/**
 *
 * @author Nira
 */
public enum StartupState {
    PLANNED(1),
    ACTIVE(2),
    FINISHED(3),
    FROZEN(4);

    private final int state;

    StartupState(int state) {
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

