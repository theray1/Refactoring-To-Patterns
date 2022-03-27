package fr.rtp.simplification.condwithstate;

public class SystemPermission {

  private SystemProfile profile;
  private SystemUser requestor;
  private SystemAdmin admin;
  private boolean isGranted;
  private PermissionState state;

  public SystemPermission(SystemUser requestor, SystemProfile profile) {
    this.requestor = requestor;
    this.profile = profile;
    state = PermissionState.REQUESTED;
    isGranted = false;
    notifyAdminOfPermissionRequest();
  }

  public void claimedBy(SystemAdmin admin) {
    if (!state.equals(PermissionState.REQUESTED)) {
      return;
    }
    willBeHandledBy(admin);
    state = PermissionState.CLAIMED;
  }

  public void deniedBy(SystemAdmin admin) {
    if (!state.equals(PermissionState.CLAIMED)) {
      return;
    }
    if (!admin.equals(this.admin)) {
      return;
    }
    isGranted = false;
    state = PermissionState.DENIED;
    notifyUserOfPermissionRequestResult();
  }

  public void grantedBy(SystemAdmin admin) {
    if (!state.equals(PermissionState.CLAIMED)) {
      return;
    }
    if (!admin.equals(this.admin)) {
      return;
    }
    state = PermissionState.GRANTED;
    isGranted = true;
    notifyUserOfPermissionRequestResult();
  }

  private void willBeHandledBy(SystemAdmin admin) {
    this.admin = admin;
  }

  private void notifyUserOfPermissionRequestResult() {
  }

  private void notifyAdminOfPermissionRequest() {
  }

  public PermissionState state() {
    return state;
  }

  public boolean isGranted() {
    return isGranted;
  }

}
