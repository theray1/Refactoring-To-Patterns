package fr.rtp.simplification.condwithstate.refactored;

import fr.rtp.simplification.condwithstate.SystemAdmin;

class PermissionRequested extends  PermissionState {
    public PermissionRequested() {
        super("REQUESTED");
    }

    public void claimedBy(SystemAdmin admin, SystemPermission systemPermission) {
      systemPermission.willBeHandledBy(admin);
      systemPermission.state = CLAIMED;
    }
}
