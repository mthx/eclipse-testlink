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
package net.hillsdon.testlink.ui;

import org.eclipse.core.resources.IFile;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PartInitException;

/**
 * Allows an action to interact with the user.
 *
 * @author mth
 */
public interface IUserInteraction {

  /**
   * Must call this before the other methods.
   *
   * @param window The window to use.
   * @see IWorkbenchWindowActionDelegate#init(IWorkbenchWindow)
   */
  void setWindow(IWorkbenchWindow window);

  /**
   * Open the default editor for the given file.
   *
   * @param file The file, never null.
   * @throws PartInitException If we fail.
   */
  void openEditor(IFile file) throws PartInitException;

  /**
   * @param underTest The type under test.
   * @param testName Name of proposed test.
   * @param packageName The package name.
   * @param sourceFolder The target source folder.
   */
  void createTest(IType underTest, String testName, String packageName, IPackageFragmentRoot sourceFolder);

  /**
   * Complain that we're missing preferences.
   */
  void showPreferencesMissingDialog();

}
