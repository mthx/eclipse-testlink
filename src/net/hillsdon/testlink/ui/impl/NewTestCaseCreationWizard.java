/*******************************************************************************
 * Copyright (c) 2000, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package net.hillsdon.testlink.ui.impl;

import org.eclipse.core.resources.IResource;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.internal.junit.ui.JUnitPlugin;
import org.eclipse.jdt.internal.junit.wizards.JUnitWizard;
import org.eclipse.jdt.internal.junit.wizards.WizardMessages;
import org.eclipse.jdt.junit.wizards.NewTestCaseWizardPageOne;
import org.eclipse.jdt.junit.wizards.NewTestCaseWizardPageTwo;

/**
 * Copied from Eclipse junit project as there's no other way to configure the first page.
 *
 * A wizard for creating test cases.
 */
@SuppressWarnings("restriction")
public class NewTestCaseCreationWizard extends JUnitWizard {

  private final IType _underTest;
  private final String _testName;
  private final String _packageName;
  private final IPackageFragmentRoot _sourceFolder;
  
  private NewTestCaseWizardPageOne _page1;
  private NewTestCaseWizardPageTwo _page2;

  public NewTestCaseCreationWizard(final IType underTest, final String testName, String packageName, IPackageFragmentRoot sourceFolder) {
    super();
    _underTest = underTest;
    _testName = testName;
    _packageName = packageName;
    _sourceFolder = sourceFolder;
    setWindowTitle(WizardMessages.Wizard_title_new_testcase);
    initDialogSettings();
  }

  protected void initializeDefaultPageImageDescriptor() {
    setDefaultPageImageDescriptor(JUnitPlugin.getImageDescriptor("wizban/newtest_wiz.png")); //$NON-NLS-1$
  }

  /*
   * @see Wizard#createPages
   */
  public void addPages() {
    super.addPages();
    _page2= new NewTestCaseWizardPageTwo();
    _page1= new HackedNewTestCaseWizardPageOne(_page2, _underTest.getElementName());
    addPage(_page1);
    _page1.init(getSelection());
    addPage(_page2);

    configurePageOne();
  }

  private void configurePageOne() {
    _page1.setPackageFragmentRoot(_sourceFolder, true);
    _page1.setPackageFragment(_sourceFolder.getPackageFragment(_packageName), true);
    _page1.setClassUnderTest(_underTest.getFullyQualifiedName());
    _page1.setTypeName(_testName, true);
  }

  /*
   * @see Wizard#performFinish
   */
  public boolean performFinish() {
    if (finishPage(_page1.getRunnable())) {
      IType newClass= _page1.getCreatedType();

      IResource resource= newClass.getCompilationUnit().getResource();
      if (resource != null) {
        selectAndReveal(resource);
        openResource(resource);
      }
      return true;
    }
    return false;
  }
}
