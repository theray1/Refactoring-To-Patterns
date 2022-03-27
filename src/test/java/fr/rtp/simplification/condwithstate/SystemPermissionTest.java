package fr.rtp.simplification.condwithstate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SystemPermissionTest {

  private SystemPermission permission;

  @SuppressWarnings("static-access")
  @Test
  public void grantedBy() throws Exception {
    SystemAdmin systemAdmin = new SystemAdmin();
    permission.grantedBy(systemAdmin);
    assertEquals(PermissionState.REQUESTED, permission.state(), "requested");
    assertEquals(false, permission.isGranted(), "not granted");
    permission.claimedBy(systemAdmin);
    permission.grantedBy(systemAdmin);
    assertEquals(PermissionState.GRANTED, permission.state(), "granted");
    assertEquals(true, permission.isGranted(), "granted");
  }

  @SuppressWarnings("static-access")
  @Test
  public void deniedBy() throws Exception {
    SystemAdmin systemAdmin = new SystemAdmin();
    permission.deniedBy(systemAdmin);
    assertEquals(PermissionState.REQUESTED, permission.state(), "requested");
    assertEquals(false, permission.isGranted(), "not granted");
    permission.claimedBy(systemAdmin);
    permission.deniedBy(systemAdmin);
    assertEquals(PermissionState.DENIED, permission.state(), "denied");
    assertEquals(false, permission.isGranted(), "denied");
  }

  @BeforeEach
  public void initBeforeTest() throws Exception {
    SystemUser user = new SystemUser();
    SystemProfile profile = new SystemProfile();
    permission = new SystemPermission(user, profile);
  }
}
