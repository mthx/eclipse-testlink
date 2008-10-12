package net.hillsdon.testlink.model;

import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaModelException;

public interface ISourceFolderConvention {

   IPackageFragmentRoot getSourceFolder(IJavaProject project) throws JavaModelException;
  
}
