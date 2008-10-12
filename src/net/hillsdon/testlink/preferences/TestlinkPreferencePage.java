package net.hillsdon.testlink.preferences;

import net.hillsdon.testlink.plugin.TestlinkPlugin;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.IWorkbenchPropertyPage;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

/**
 * Preferences for testlink are currently workspace wide.
 *
 * Overriding for projects would be a natural extension.
 *
 * You set a series of patterns:
 *   * Test package patterns - e.g. %s or %s.tests
 *   * Test name patterns - with %s, e.g. Test%s
 *   * Test source folder names - fixed strings.
 *
 * These can be comma separated, whitespace is trimmed.
 *
 * Attempts to be somewhat similar to PropertyAndPreferencePage but we don't bother 
 * with the link from the global to project settings.  
 * 
 * Track https://bugs.eclipse.org/bugs/show_bug.cgi?id=173414
 * and use when it becomes API.
 */
public class TestlinkPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage, IWorkbenchPropertyPage {

  private IProject _project;

  public TestlinkPreferencePage() {
    super(GRID);
    setPreferenceStore(TestlinkPlugin.getDefault().getPreferenceStore());
    setDescription("Determines where tests are created.\nMultiple values can be given separated by commas.\n'%s' refers to the implementation location in patterns.\n");
  }

  @Override
  public void createFieldEditors() {
    addField(new StringFieldEditor(PreferenceConstants.SOURCE_FOLDERS, "Test source folders:", getFieldEditorParent()));
    addField(new StringFieldEditor(PreferenceConstants.PACKAGE_PATTERNS, "Test package patterns:", getFieldEditorParent()));
    addField(new StringFieldEditor(PreferenceConstants.CLASS_PATTERNS, "Test class patterns:", getFieldEditorParent()));
  }

  public void init(final IWorkbench workbench) {
  }

  public IAdaptable getElement() {
    return _project;
  }

  public void setElement(final IAdaptable element) {
    _project = (IProject) element.getAdapter(IResource.class);
    ProjectScope scope = new ProjectScope(_project);
    setPreferenceStore(new ScopedPreferenceStore(scope, TestlinkPlugin.ID));
  }

}