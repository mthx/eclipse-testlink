package net.hillsdon.testlink.model.impl;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.hillsdon.testlink.model.IConventionBundle;
import net.hillsdon.testlink.model.ILinker;
import net.hillsdon.testlink.model.Linkage;
import net.hillsdon.testlink.preferences.ITestlinkPreferences;
import net.hillsdon.testlink.preferences.PreferencesMissingException;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IType;

/**
 * Uses the a searcher and conventions to find tests / impls.
 *
 * @author mth
 */
public class DefaultLinker extends AbstractLinker implements ILinker {

  private final ISearcher _searcher;
  private final ITestlinkPreferences _preferences;

  public DefaultLinker(final ISearcher searcher, final ITestlinkPreferences preferences) {
    _searcher = searcher;
    _preferences = preferences;
  }

  public IResource find(final IType type, final Linkage direction) throws CoreException, PreferencesMissingException {
    final String typeName = type.getElementName();
    final List<IResource> paths = new ArrayList<IResource>();
    for (IConventionBundle bundle : _preferences.getConventions()) {
      final String testClassName = direction.getOtherClassName(bundle, typeName);
      if (testClassName != null && !testClassName.equals(typeName)) {
        paths.addAll(_searcher.search(testClassName));
      }
    }
    Collections.sort(paths, PrioritizeSourceFiles.INSTANCE);
    return paths.isEmpty() ? null : paths.get(0);
  }

}
