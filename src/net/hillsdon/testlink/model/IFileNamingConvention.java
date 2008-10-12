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
package net.hillsdon.testlink.model;

/**
 * Encapsulates a particular naming convention for tests and implementations.
 *
 * The returned values are not guaranteed to be valid Java.
 *
 * @author mth
 */
public interface IFileNamingConvention {

  String getImplClassName(String testClassName);
  String getTestClassName(String implClassName);

}
