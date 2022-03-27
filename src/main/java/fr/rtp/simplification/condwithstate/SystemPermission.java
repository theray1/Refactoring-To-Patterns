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
    setState(PermissionState.REQUESTED);
    setGranted(false);
    notifyAdminOfPermissionRequest();
  }

  public void claimedBy(SystemAdmin admin) {
    state.claimedBy(admin, this);
  }

  public void deniedBy(SystemAdmin admin) {
    state.deniedBy(admin, this);
  }

  public void grantedBy(SystemAdmin admin) {
    state.grantedBy(admin, this);
  }

  protected void willBeHandledBy(SystemAdmin admin) {
    this.setAdmin(admin);
  }

  protected void notifyUserOfPermissionRequestResult() {
  }

  protected void notifyAdminOfPermissionRequest() {
  }

  public PermissionState state() {
    return getState();
  }

  public boolean isGranted() {
    return isGranted;
  }

  PermissionState getState() {
    return state;
  }

  void setState(PermissionState state) {
    this.state = state;
  }

  void setGranted(boolean granted) {
    isGranted = granted;
  }

  SystemAdmin getAdmin() {
    return admin;
  }

  void setAdmin(SystemAdmin admin) {
    this.admin = admin;
  }
}
