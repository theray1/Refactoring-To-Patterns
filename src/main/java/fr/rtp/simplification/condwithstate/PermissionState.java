package fr.rtp.simplification.condwithstate;

public abstract class PermissionState {
    public final static PermissionState REQUESTED = new RequestedState("REQUESTED");
    public final static PermissionState CLAIMED = new ClaimedState("CLAIMED");
    public final static PermissionState GRANTED = new GrantedState("GRANTED");
    public final static PermissionState DENIED = new DeniedState("DENIED");
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

    abstract void claimedBy(SystemAdmin admin, SystemPermission systemPermission);
    abstract void deniedBy(SystemAdmin admin, SystemPermission systemPermission);
    abstract void grantedBy(SystemAdmin admin, SystemPermission systemPermission);
}
