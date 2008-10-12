/*******************************************************************************
 * Copyright (c) 2008 Matthew Hillsdon.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Matthew Hillsdon <matt@hillsdon.net>
 *******************************************************************************/
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
