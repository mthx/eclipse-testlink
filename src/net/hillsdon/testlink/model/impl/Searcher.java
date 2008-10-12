package net.hillsdon.testlink.model.impl;

import java.util.Set;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.search.IJavaSearchConstants;
import org.eclipse.jdt.core.search.IJavaSearchScope;
import org.eclipse.jdt.core.search.SearchEngine;
import org.eclipse.jdt.core.search.SearchParticipant;
import org.eclipse.jdt.core.search.SearchPattern;

public class Searcher implements ISearcher {

  public Set<IResource> search(final String typeName) throws CoreException {
    SearchEngine searchEngine = new SearchEngine();
    IJavaSearchScope scope = SearchEngine.createWorkspaceScope();
    ResourceCollector results = new ResourceCollector();
    SearchPattern pattern = SearchPattern.createPattern(typeName, IJavaSearchConstants.TYPE, IJavaSearchConstants.DECLARATIONS, SearchPattern.R_PATTERN_MATCH);
    searchEngine.search(pattern, new SearchParticipant[] {SearchEngine.getDefaultSearchParticipant()}, scope, results, new NullProgressMonitor());
    return results.getCollectedPaths();
  }
  
}
