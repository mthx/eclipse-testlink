package net.hillsdon.testlink.model;

import net.hillsdon.testlink.preferences.ITestlinkPreferences;
import net.hillsdon.testlink.preferences.PreferencesMissingException;
import net.hillsdon.testlink.ui.IUserInteraction;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;


/**
 * Helps to write methods that work in both directions.
 *
 * @author mth
 */
public interface Linkage {

  /**
   * 'Other' is the impl.
   */
  Linkage TEST = new Linkage() {
    
    public String getOtherClassName(final IFileNamingConvention convention, final String input) {
      return convention.getImplClassName(input);
    }
    
    public void createOther(final IUserInteraction ui, ITestlinkPreferences preferences, IType type) {
      // Nothing as yet, probably not worth it.
    }
    
  };

  /**
   * 'Other' is the test.
   */
  Linkage IMPL = new Linkage() {
    
    public String getOtherClassName(final IFileNamingConvention convention, final String input) {
      return convention.getTestClassName(input);
    }

    public void createOther(final IUserInteraction ui, final ITestlinkPreferences preferences, final IType type) throws PreferencesMissingException, JavaModelException {
      final IConventionBundle convention = preferences.getConventions().get(0);
      final String testName = convention.getTestClassName(type.getElementName());
      final String packageName = convention.getTestPackageName(type.getPackageFragment().getElementName());
      IPackageFragmentRoot sourceFolder = convention.getSourceFolder(type.getJavaProject());
      if (sourceFolder == null) {
        sourceFolder = (IPackageFragmentRoot) type.getAncestor(IJavaElement.PACKAGE_FRAGMENT_ROOT);
      }
      ui.createTest(type, testName, packageName, sourceFolder);
    }
    
  };

  String getOtherClassName(IFileNamingConvention convention, String input) throws CoreException;

  void createOther(IUserInteraction ui, ITestlinkPreferences preferences, IType type) throws PreferencesMissingException, JavaModelException;

}
