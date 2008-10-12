package net.hillsdon.testlink.preferences;

import net.hillsdon.testlink.plugin.TestlinkPlugin;

/**
 * Sucks the preferences out of thin air.
 * 
 * @author mth
 */
public class PreferencesSource {

  public static ITestlinkPreferences getPreferences() {
    return new TestlinkPreferences(TestlinkPlugin.getDefault().getPreferenceStore());
  }
  
}
