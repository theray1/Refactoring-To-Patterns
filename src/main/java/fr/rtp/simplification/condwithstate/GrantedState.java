package fr.rtp.simplification.condwithstate;

public class GrantedState extends PermissionState{
    public GrantedState(String state) {
        super(state);
    }

    void claimedBy(SystemAdmin admin, SystemPermission systemPermission) {
    }

    void deniedBy(SystemAdmin admin, SystemPermission systemPermission) {
    }

    void grantedBy(SystemAdmin admin, SystemPermission systemPermission) {

    }
}
