package net.hillsdon.testlink.preferences;

import java.util.List;

import net.hillsdon.testlink.model.IConventionBundle;

/**
 * The preferences.
 * 
 * @author mth
 */
public interface ITestlinkPreferences {

  /**
   * @return The currently configured set of conventions (snapshot, unmodifiable).
   * @throws PreferencesMissingException On failure. 
   */
  List<IConventionBundle> getConventions() throws PreferencesMissingException;
  
}
