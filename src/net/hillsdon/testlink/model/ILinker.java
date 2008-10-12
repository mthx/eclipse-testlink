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
package net.hillsdon.testlink.model;

import net.hillsdon.testlink.preferences.PreferencesMissingException;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IType;

/**
 * Link types and their tests.
 *
 * @author mth
 */
public interface ILinker {

  /**
   * Determine if the given type is a test, if so return
   * {@link Linkage#TEST}, otherwise {@link Linkage#IMPL}.
   */
  Linkage getLinkageFor(IType type) throws CoreException;

  /**
   * Find test from type / type from test.
   * @throws PreferencesMissingException If preferences are missing.O 
   */
  IResource find(IType type, Linkage direction) throws CoreException, PreferencesMissingException;

}
