package net.hillsdon.testlink.model.impl;

import java.util.Collection;

import net.hillsdon.testlink.model.ILinker;
import net.hillsdon.testlink.model.Linkage;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.ITypeHierarchy;
import org.eclipse.jdt.core.JavaModelException;

import static java.util.Arrays.asList;

/**
 * Provides an implementation of {@link #getLinkageFor(IType)} based on super-class
 * so it is naming strategy independent.  Perhaps the method doesn't belong here?
 *
 * @author mth
 */
public abstract class AbstractLinker implements ILinker {

  private static final Collection<String> TEST_SUPER_CLASSES = asList("junit.framework.TestCase");

  public Linkage getLinkageFor(final IType type) throws JavaModelException {
    ITypeHierarchy supertypeHierarchy = type.newSupertypeHierarchy(new NullProgressMonitor());
    for (IType superClass : supertypeHierarchy.getAllSuperclasses(type)) {
      if (TEST_SUPER_CLASSES.contains(superClass.getFullyQualifiedName())) {
        return Linkage.TEST;
      }
      // Will need to inspect annotations for JUnit4.
    }
    return Linkage.IMPL;
  }

}
