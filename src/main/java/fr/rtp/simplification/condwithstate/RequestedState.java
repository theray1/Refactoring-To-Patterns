package fr.rtp.simplification.condwithstate;

public class RequestedState extends PermissionState{
    public RequestedState(String state) {
        super(state);
    }

    void claimedBy(SystemAdmin admin, SystemPermission systemPermission) {
      systemPermission.willBeHandledBy(admin);
      systemPermission.setState(CLAIMED);
    }

    void deniedBy(SystemAdmin admin, SystemPermission systemPermission) {
    }

    void grantedBy(SystemAdmin admin, SystemPermission systemPermission) {

    }
}
