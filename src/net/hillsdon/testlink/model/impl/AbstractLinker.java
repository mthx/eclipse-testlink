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
package net.hillsdon.testlink.model.impl;

import static java.util.Arrays.asList;

import java.util.Collection;

import net.hillsdon.testlink.model.ILinker;
import net.hillsdon.testlink.model.Linkage;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.IAnnotation;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.ITypeHierarchy;
import org.eclipse.jdt.core.JavaModelException;

/**
 * Provides an implementation of {@link #getLinkageFor(IType)} based on super-class
 * so it is naming strategy independent.  Perhaps the method doesn't belong here?
 *
 * @author mth
 */
public abstract class AbstractLinker implements ILinker {

  private static final Collection<String> TEST_SUPER_CLASSES = asList("junit.framework.TestCase");

  public Linkage getLinkageFor(final IType type) throws JavaModelException {
    final ITypeHierarchy supertypeHierarchy = type.newSupertypeHierarchy(new NullProgressMonitor());
    // JUnit 4.
    for (IMethod method : type.getMethods()) {
      for (IAnnotation annotation : method.getAnnotations()) {
        final String name = annotation.getElementName();
        if (name != null) {
          final String[][] resolutions = type.resolveType(name);
          if (resolutions != null) {
            for (String[] resolved : resolutions) {
              if ("org.junit".equals(resolved[0])) {
                return Linkage.TEST;
              }
            }
          }
        }
      }
    }

    // JUnit 3.
    for (IType superClass : supertypeHierarchy.getAllSuperclasses(type)) {
      if (TEST_SUPER_CLASSES.contains(superClass.getFullyQualifiedName())) {
        return Linkage.TEST;
      }
    }
    return Linkage.IMPL;
  }

}
