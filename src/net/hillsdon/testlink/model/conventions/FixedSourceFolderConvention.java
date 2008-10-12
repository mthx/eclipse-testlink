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
package net.hillsdon.testlink.model.conventions;

import net.hillsdon.testlink.model.ISourceFolderConvention;

import org.eclipse.core.resources.IResource;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaModelException;

public class FixedSourceFolderConvention implements ISourceFolderConvention {

  private final String _projectRelativePath;

  /**
   * @param projectRelativePath No leading '/', '/' separated.
   */
  public FixedSourceFolderConvention(final String projectRelativePath) {
    _projectRelativePath = projectRelativePath;
  }
  
  public IPackageFragmentRoot getSourceFolder(final IJavaProject project) throws JavaModelException {
    for (IPackageFragmentRoot root : project.getPackageFragmentRoots()) {
      final IResource resource = root.getResource();
      if (resource != null && _projectRelativePath.equals(resource.getProjectRelativePath().toString())) {
        return root;
      }
    }
    return null;
  }

  public String getProjectRelativePath() {
    return _projectRelativePath;
  }

}
