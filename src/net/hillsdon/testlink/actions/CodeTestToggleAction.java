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
package net.hillsdon.testlink.actions;

import net.hillsdon.testlink.model.IJavaSelection;
import net.hillsdon.testlink.model.ILinker;
import net.hillsdon.testlink.model.Linkage;
import net.hillsdon.testlink.model.impl.DefaultLinker;
import net.hillsdon.testlink.model.impl.JavaSelection;
import net.hillsdon.testlink.model.impl.Searcher;
import net.hillsdon.testlink.plugin.TestlinkPlugin;
import net.hillsdon.testlink.preferences.ITestlinkPreferences;
import net.hillsdon.testlink.preferences.PreferencesMissingException;
import net.hillsdon.testlink.preferences.PreferencesSource;
import net.hillsdon.testlink.ui.IUserInteraction;
import net.hillsdon.testlink.ui.impl.UserInteraction;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.ILog;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

/**
 * Jump to the test for the current type or vice-versa.
 * 
 * Offers to create missing tests.
 *
 * @author mth
 */
public class CodeTestToggleAction extends Action implements IWorkbenchWindowActionDelegate {

  private final IUserInteraction _ui;
  private final ILinker _linker;
  private final IJavaSelection _javaSelection;
  private final ILog _log;
  private final ITestlinkPreferences _preferences;

  /**
   * For Eclipse.  Ugh.
   */
  public CodeTestToggleAction() {
    this(PreferencesSource.getPreferences(), new DefaultLinker(new Searcher(), PreferencesSource.getPreferences()), new JavaSelection(), new UserInteraction(), TestlinkPlugin.getDefault().getLog());
  }

  /**
   * For testing.
   * @param preferences 
   */
  CodeTestToggleAction(final ITestlinkPreferences preferences, final ILinker linker, final IJavaSelection javaSelection, final IUserInteraction ui, final ILog log) {
    _preferences = preferences;
    _linker = linker;
    _javaSelection = javaSelection;
    _ui = ui;
    _log = log;
  }

  public void run(final IAction action) {
    try {
      final IType type = _javaSelection.getSelectedType();
      if (type != null) {
        final Linkage linkage = _linker.getLinkageFor(type);
        final IResource resource = _linker.find(type, linkage);
        if (resource == null) {
          linkage.createOther(_ui, _preferences, type);
        }
        else if (resource instanceof IFile) {
          _ui.openEditor((IFile) resource);
        }
      }
    }
    catch (PreferencesMissingException ex) {
      _ui.showPreferencesMissingDialog();
    }
    catch (CoreException ex) {
      _log.log(ex.getStatus());
    }
  }

  public void selectionChanged(final IAction action, final ISelection selection) {
    _javaSelection.selectionChanged(selection);
    try {
      action.setEnabled(_javaSelection.getSelectedType() != null);
    }
    catch (JavaModelException e) {
      _log.log(e.getStatus());
      action.setEnabled(false);
    }
  }

  public void init(final IWorkbenchWindow window) {
    _ui.setWindow(window);
  }

  public void dispose() {
  }

}