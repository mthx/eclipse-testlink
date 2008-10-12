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

import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.viewers.ISelection;

/**
 * Translates the workbench selection into something meaningful.
 *
 * @author mth
 */
public interface IJavaSelection {

  /**
   * Should be called by the owning action.
   *
   * @param selection The new selection.
   */
  void selectionChanged(ISelection selection);

  /**
   * Find the type best associated with the java element.
   *
   * @param javaElement A java element.
   * @return A type, or null if we can't figure one out.
   * @throws JavaModelException If we fail to read from the java model.
   */
  IType getSelectedType() throws JavaModelException;

}
