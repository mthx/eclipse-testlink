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

import net.hillsdon.testlink.model.IFileNamingConvention;

/**
 * E.g. "Test%sFunctionally"
 *
 * Substitute for %s to get the test name from the class name.
 * Remove either side of %s to get class name.
 *
 * @author mth
 */
public class TemplateBasedFileNamingConvention implements IFileNamingConvention {

  private final Template _template;

  public TemplateBasedFileNamingConvention(final String template) {
    _template = new Template(template);
  }

  public String getImplClassName(final String testClassName) {
    return _template.unformat(testClassName);
  }

  public String getTestClassName(final String implClassName) {
    return _template.format(implClassName);
  }

}
