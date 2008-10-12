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
package net.hillsdon.testlink.model.impl;

import java.util.Set;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;

public interface ISearcher {

  /**
   * Search.
   *
   * @param typeName Wildcarded type name.
   * @return matches, possibly empty never null.
   * @throws CoreException if the search fails.
   */
  Set<IResource> search(final String typeName) throws CoreException;

}
