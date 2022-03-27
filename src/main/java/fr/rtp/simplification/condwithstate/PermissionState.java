package fr.rtp.simplification.condwithstate;

public class PermissionState {
    public final static PermissionState REQUESTED = new PermissionState("REQUESTED");
    public final static PermissionState CLAIMED = new PermissionState("CLAIMED");
    public final static PermissionState GRANTED = new PermissionState("GRANTED");
    public final static PermissionState DENIED = new PermissionState("DENIED");
    private final String state;

    public PermissionState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PermissionState)) return false;

        PermissionState that = (PermissionState) o;

        return getState() != null ? getState().equals(that.getState()) : that.getState() == null;
    }

    @Override
    public int hashCode() {
        return getState() != null ? getState().hashCode() : 0;
    }
}
