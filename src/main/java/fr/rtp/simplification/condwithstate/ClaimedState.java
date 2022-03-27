package fr.rtp.simplification.condwithstate;

public class ClaimedState extends PermissionState{
    public ClaimedState(String state) {
        super(state);
    }

    void claimedBy(SystemAdmin admin, SystemPermission systemPermission) {
    }

    void deniedBy(SystemAdmin admin, SystemPermission systemPermission) {
      if (!admin.equals(systemPermission.getAdmin())) {
        return;
      }
      systemPermission.setGranted(false);
      systemPermission.setState(DENIED);
      systemPermission.notifyUserOfPermissionRequestResult();
    }

    void grantedBy(SystemAdmin admin, SystemPermission systemPermission) {
        if (!admin.equals(systemPermission.getAdmin())) {
            return;
        }
        systemPermission.setState(GRANTED);
        systemPermission.setGranted(true);
        systemPermission.notifyUserOfPermissionRequestResult();
    }
}
