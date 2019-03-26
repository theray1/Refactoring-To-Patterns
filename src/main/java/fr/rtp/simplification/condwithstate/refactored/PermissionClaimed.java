package fr.rtp.simplification.condwithstate.refactored;

import fr.rtp.simplification.condwithstate.SystemAdmin;

class PermissionClaimed extends PermissionState {
    public PermissionClaimed() {
        super("CLAIMED");
    }

    public void grantedBy(SystemAdmin admin, SystemPermission systemPermission) {
        if (!admin.equals(systemPermission.admin)) {
            return;
        }
        systemPermission.state = GRANTED;
        systemPermission.isGranted = true;
        systemPermission.notifyUserOfPermissionRequestResult();
    }

    public void deniedBy(SystemAdmin admin, SystemPermission systemPermission) {
        if (!admin.equals(systemPermission.admin)) {
            return;
        }
        systemPermission.isGranted = false;
        systemPermission.state = DENIED;
        systemPermission.notifyUserOfPermissionRequestResult();
    }

}
