package net.hillsdon.testlink.model;

import java.util.Arrays;

import junit.framework.TestCase;
import net.hillsdon.testlink.preferences.ITestlinkPreferences;
import net.hillsdon.testlink.ui.IUserInteraction;

import org.easymock.EasyMock;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;

public class TestLinkage extends TestCase {

  private IUserInteraction _ui;
  private ITestlinkPreferences _preferences;

  @Override
  protected void setUp() throws Exception {
    _preferences = createMock(ITestlinkPreferences.class);
    _ui = createMock(IUserInteraction.class);
  }

  public void testImplCreateOtherCreatesTest() throws Exception {
    IConventionBundle conventions = createMock(IConventionBundle.class);
    expect(_preferences.getConventions()).andReturn(Arrays.asList(conventions));
    
    IType type = createMock(IType.class);
    IJavaProject project = createMock(IJavaProject.class);
    IPackageFragmentRoot srcFolder = createMock(IPackageFragmentRoot.class);
    expect(type.getJavaProject()).andReturn(project);
    IPackageFragment packageFragment = createMock(IPackageFragment.class);
    expect(packageFragment.getElementName()).andReturn("org.example");
    expect(type.getPackageFragment()).andReturn(packageFragment);
    expect(type.getElementName()).andReturn("NewImpl").anyTimes();
    
    expect(conventions.getTestClassName("NewImpl")).andReturn("TestNewImpl");
    expect(conventions.getTestPackageName("org.example")).andReturn("org.example.tests");
    expect(conventions.getSourceFolder(project)).andReturn(srcFolder);
    _ui.createTest(type, "TestNewImpl", "org.example.tests", srcFolder);
    expectLastCall().once();
    
    EasyMock.replay(_preferences, type, packageFragment, project, srcFolder, conventions, _ui);
    Linkage.IMPL.createOther(_ui, _preferences, type);
    EasyMock.verify(_ui);
  }

}
