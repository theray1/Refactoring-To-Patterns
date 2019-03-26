package fr.rtp.simplification.condwithstate.refactored;

import fr.rtp.simplification.condwithstate.SystemAdmin;

public abstract class PermissionState {
    private final String name;

    protected PermissionState(String name) {
        this.name = name;
    }

    public final static PermissionState REQUESTED = new PermissionRequested();
    public final static PermissionState CLAIMED = new PermissionClaimed();
    public final static PermissionState GRANTED = new PermissionGranted();
    public final static PermissionState DENIED = new PermissionDenied();


    @Override
    public String toString() {
        return name;
    }

    public void claimedBy(SystemAdmin admin, SystemPermission systemPermission) {

    }

    public void deniedBy(SystemAdmin admin, SystemPermission systemPermission) {

    }

    public void grantedBy(SystemAdmin admin, SystemPermission systemPermission) {

    }


}
