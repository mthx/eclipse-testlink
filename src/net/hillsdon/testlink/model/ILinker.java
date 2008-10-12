package net.hillsdon.testlink.model;

import net.hillsdon.testlink.preferences.PreferencesMissingException;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IType;

/**
 * Link types and their tests.
 *
 * @author mth
 */
public interface ILinker {

  /**
   * Determine if the given type is a test, if so return
   * {@link Linkage#TEST}, otherwise {@link Linkage#IMPL}.
   */
  Linkage getLinkageFor(IType type) throws CoreException;

  /**
   * Find test from type / type from test.
   * @throws PreferencesMissingException If preferences are missing.O 
   */
  IResource find(IType type, Linkage direction) throws CoreException, PreferencesMissingException;

}
