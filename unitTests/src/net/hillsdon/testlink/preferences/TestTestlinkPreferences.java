package net.hillsdon.testlink.preferences;

import java.util.List;

import junit.framework.TestCase;
import net.hillsdon.testlink.model.IConventionBundle;
import net.hillsdon.testlink.model.conventions.FixedSourceFolderConvention;

import org.easymock.EasyMock;
import org.eclipse.jface.preference.IPreferenceStore;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;

public class TestTestlinkPreferences extends TestCase {

  private IPreferenceStore _store;
  private TestlinkPreferences _preferences;

  @Override
  protected void setUp() throws Exception {
    _store = EasyMock.createMock(IPreferenceStore.class);
    _preferences = new TestlinkPreferences(_store);
  }
  
  public void testThrowsIfMissingData() {
    setupPreferences("", "", "");
    try {
      _preferences.getConventions();
      fail();
    }
    catch (PreferencesMissingException expected) {
    }
  }
  
  public void testUsesLastValueToFillInBlanks() throws PreferencesMissingException {
    setupPreferences("Test%s, FTest%s", "%s, %s.tests", "tests/src");
    List<IConventionBundle> conventions = _preferences.getConventions();
    assertEquals(2, conventions.size());
    ConventionBundle first = (ConventionBundle) conventions.get(0);
    assertEquals("TestFoo", first.getTestClassName("Foo"));
    assertEquals("foo", first.getTestPackageName("foo"));
    assertEquals("tests/src", ((FixedSourceFolderConvention) first.getSource()).getProjectRelativePath());
    
    ConventionBundle second = (ConventionBundle) conventions.get(1);
    assertEquals("FTestFoo", second.getTestClassName("Foo"));
    assertEquals("foo.tests", second.getTestPackageName("foo"));
    assertEquals("tests/src", ((FixedSourceFolderConvention) second.getSource()).getProjectRelativePath());
  }

  private void setupPreferences(String classPatterns, String packagePatterns, String sourceFolderPatterns) {
    expect(_store.getString(PreferenceConstants.CLASS_PATTERNS)).andReturn(classPatterns);
    expect(_store.getString(PreferenceConstants.PACKAGE_PATTERNS)).andReturn(packagePatterns);
    expect(_store.getString(PreferenceConstants.SOURCE_FOLDERS)).andReturn(sourceFolderPatterns);
    replay(_store);
  }
  
}
