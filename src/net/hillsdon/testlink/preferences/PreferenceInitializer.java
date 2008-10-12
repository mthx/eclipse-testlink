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

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * Class used to initialize default preference values.
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

  public void initializeDefaultPreferences() {
    final IPreferenceStore store = TestlinkPlugin.getDefault().getPreferenceStore();
    store.setDefault(PreferenceConstants.CLASS_PATTERNS, "Test%s, Test%sFunctionally");
    store.setDefault(PreferenceConstants.PACKAGE_PATTERNS, "%s");
    store.setDefault(PreferenceConstants.SOURCE_FOLDERS, "unitTests/src, functionalTests/src");
  }

}
