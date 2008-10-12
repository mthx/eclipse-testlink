package net.hillsdon.testlink.model.impl;

import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.search.SearchMatch;
import org.eclipse.jdt.core.search.SearchRequestor;

/**
 * Collects the paths that are the result of a type search.
 */
class ResourceCollector extends SearchRequestor {

  private Set<IResource> _collectedPaths = new LinkedHashSet<IResource>();

  public void acceptSearchMatch(final SearchMatch match) throws CoreException {
    _collectedPaths.add(match.getResource());
  }

  public Set<IResource> getCollectedPaths() {
    return _collectedPaths;
  }

}
