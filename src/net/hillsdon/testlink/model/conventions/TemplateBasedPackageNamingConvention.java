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

import net.hillsdon.testlink.model.IPackageNamingConvention;

/**
 * E.g. "%s.tests"
 *
 * @author mth
 */
public class TemplateBasedPackageNamingConvention implements IPackageNamingConvention {

  private final Template _template;

  public TemplateBasedPackageNamingConvention(final String template) {
    _template = new Template(template);
  }

  public String getImplPackageName(final String testPackageName) {
    return _template.unformat(testPackageName);
  }

  public String getTestPackageName(final String implClassName) {
    return _template.format(implClassName);
  }

}
