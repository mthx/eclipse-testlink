package net.hillsdon.testlink.preferences;

import net.hillsdon.testlink.model.IConventionBundle;
import net.hillsdon.testlink.model.IFileNamingConvention;
import net.hillsdon.testlink.model.IPackageNamingConvention;
import net.hillsdon.testlink.model.ISourceFolderConvention;

import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaModelException;

/**
 * Bundles together all the configuration details needed to create
 * a test from a type. 
 * 
 * @author mth
 */
public class ConventionBundle implements IConventionBundle {
  
  private final IFileNamingConvention _file;
  private final ISourceFolderConvention _source;
  private final IPackageNamingConvention _package;

  public ConventionBundle(IFileNamingConvention fileConvention, IPackageNamingConvention packageConvention, ISourceFolderConvention sourceConvention) {
    _file = fileConvention;
    _package = packageConvention;
    _source = sourceConvention;
  }

  public String getImplClassName(String testClassName) {
    return _file.getImplClassName(testClassName);
  }

  public String getTestClassName(String implClassName) {
    return _file.getTestClassName(implClassName);
  }

  public String getImplPackageName(String testPackageName) {
    return _package.getImplPackageName(testPackageName);
  }

  public String getTestPackageName(String implPackageName) {
    return _package.getTestPackageName(implPackageName);
  }

  public IPackageFragmentRoot getSourceFolder(IJavaProject project) throws JavaModelException {
    return _source.getSourceFolder(project);
  }

  public ISourceFolderConvention getSource() {
    return _source;
  }
  
}
