package fr.rtp.simplification.condwithstate.refactored;

import fr.rtp.simplification.condwithstate.SystemAdmin;
import fr.rtp.simplification.condwithstate.SystemProfile;
import fr.rtp.simplification.condwithstate.SystemUser;

/**
 * Chapter 7 - Simplification
 * Replace state-altering conditionals with state
 * 
 * http://www.industriallogic.com/xp/refactoring/alteringConditionalsWithState.
 * html
 * http://www.informit.com/articles/article.aspx?p=1398607&seqNum=4
 */
public class SystemPermission {

  private SystemProfile profile;
  private SystemUser requestor;
  protected SystemAdmin admin;
  protected boolean isGranted;
  protected PermissionState state;


  public SystemPermission(SystemUser requestor, SystemProfile profile) {
    this.requestor = requestor;
    this.profile = profile;
    state = PermissionState.REQUESTED;
    isGranted = false;
    notifyAdminOfPermissionRequest();
  }

  public void claimedBy(SystemAdmin admin) {
    this.state.claimedBy(admin, this);
  }

  public void deniedBy(SystemAdmin admin) {
    this.state.deniedBy(admin, this);
  }

  public void grantedBy(SystemAdmin admin) {
    this.state.grantedBy(admin, this);
  }

  protected void willBeHandledBy(SystemAdmin admin) {
    this.admin = admin;
  }

  protected void notifyUserOfPermissionRequestResult() {
  }

  private void notifyAdminOfPermissionRequest() {
  }

  public String state() {
    return state.toString();
  }

  public boolean isGranted() {
    return isGranted;
  }

}
