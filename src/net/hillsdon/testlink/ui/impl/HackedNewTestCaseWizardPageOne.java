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

import org.eclipse.jdt.junit.wizards.NewTestCaseWizardPageOne;
import org.eclipse.jdt.junit.wizards.NewTestCaseWizardPageTwo;

/**
 * We have to hack around the code in NewTestCaseWizardPageOne#createControl
 * which defaults the type name at the very last minute overriding whatever
 * we've set it to.
 * 
 * @author mth
 */
class HackedNewTestCaseWizardPageOne extends NewTestCaseWizardPageOne {
  
  private final String _underTestSimpleName;

  HackedNewTestCaseWizardPageOne(NewTestCaseWizardPageTwo page2, String underTestSimpleName) {
    super(page2);
    _underTestSimpleName = underTestSimpleName;
  }

  public void setTypeName(final String name, final boolean canBeModified) {
    if (getTypeName() != null && getTypeName().length() > 0 && (_underTestSimpleName + "Test").equals(name)) {
      return;
    }
    super.setTypeName(name, canBeModified);
  }
  
}