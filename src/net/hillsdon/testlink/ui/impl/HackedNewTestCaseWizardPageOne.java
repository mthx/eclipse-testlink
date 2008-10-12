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