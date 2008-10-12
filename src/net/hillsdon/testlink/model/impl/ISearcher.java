package net.hillsdon.testlink.model.impl;

import java.util.Set;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;

public interface ISearcher {

  /**
   * Search.
   *
   * @param typeName Wildcarded type name.
   * @return matches, possibly empty never null.
   * @throws CoreException if the search fails.
   */
  Set<IResource> search(final String typeName) throws CoreException;

}
