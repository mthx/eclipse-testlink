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
