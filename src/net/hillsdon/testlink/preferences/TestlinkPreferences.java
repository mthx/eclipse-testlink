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
package net.hillsdon.testlink.preferences;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import net.hillsdon.testlink.model.IConventionBundle;
import net.hillsdon.testlink.model.conventions.FixedSourceFolderConvention;
import net.hillsdon.testlink.model.conventions.TemplateBasedFileNamingConvention;
import net.hillsdon.testlink.model.conventions.TemplateBasedPackageNamingConvention;

import org.eclipse.jface.preference.IPreferenceStore;

public class TestlinkPreferences implements ITestlinkPreferences {

  private IPreferenceStore _store;

  public TestlinkPreferences(final IPreferenceStore store) {
    _store = store;
  }
  
  public List<IConventionBundle> getConventions() throws PreferencesMissingException {
    final Iterator<String> classTemplates = splitValue(PreferenceConstants.CLASS_PATTERNS);
    final Iterator<String> packageTemplates = splitValue(PreferenceConstants.PACKAGE_PATTERNS);
    final Iterator<String> sourceFolderTemplates = splitValue(PreferenceConstants.SOURCE_FOLDERS);
    
    final List<IConventionBundle> conventions = new ArrayList<IConventionBundle>();
    String classTemplate = null, packageTemplate = null, sourceFolderTemplate = null;
    do {
      if (classTemplates.hasNext()) {
        classTemplate = classTemplates.next();
      }
      if (packageTemplates.hasNext()) {
        packageTemplate = packageTemplates.next();
      }
      if (sourceFolderTemplates.hasNext()) {
        sourceFolderTemplate = sourceFolderTemplates.next();
      }
      if (classTemplate == null || packageTemplate == null || sourceFolderTemplate == null) {
        break;
      }
      conventions.add(new ConventionBundle(new TemplateBasedFileNamingConvention(classTemplate), new TemplateBasedPackageNamingConvention(packageTemplate), new FixedSourceFolderConvention(sourceFolderTemplate)));
    } 
    while (classTemplates.hasNext() || packageTemplates.hasNext() || sourceFolderTemplates.hasNext());
    
    if (conventions.isEmpty()) {
      throw new PreferencesMissingException(); 
    }
    return Collections.unmodifiableList(conventions);
  }

  private Iterator<String> splitValue(final String preferenceKey) {
    String[] parts = _store.getString(preferenceKey).split(",");
    if (parts.length == 1 && parts[0].length() == 0) {
      return Collections.<String>emptyList().iterator();
    }
    for (int i = 0; i < parts.length; ++i) {
      parts[i] = parts[i].trim();
    }
    return Arrays.asList(parts).iterator();
  }

}
