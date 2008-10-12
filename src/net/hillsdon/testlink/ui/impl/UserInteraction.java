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
package net.hillsdon.testlink.ui.impl;

import net.hillsdon.testlink.ui.IUserInteraction;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.ui.actions.AbstractOpenWizardAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.ide.IDE;

public class UserInteraction implements IUserInteraction {

  private IWorkbenchWindow _window;

  public void setWindow(final IWorkbenchWindow window) {
    _window = window;
  }

  public void openEditor(final IFile file) throws PartInitException {
    IWorkbenchPage activePage = _window.getActivePage();
    IDE.openEditor(activePage, file);
  }

  public void createTest(final IType underTest, final String testName, final String packageName, final IPackageFragmentRoot sourceFolder) {
    new AbstractOpenWizardAction() {
      protected INewWizard createWizard() throws CoreException {
        return new NewTestCaseCreationWizard(underTest, testName, packageName, sourceFolder);
      }
    }.run();
  }

  public void showPreferencesMissingDialog() {
    MessageDialog.openError(_window.getShell(), "Configuration Problem", "The testlink preferences are incomplete.\n\nPlease fill them in and try again.");
  }

}
