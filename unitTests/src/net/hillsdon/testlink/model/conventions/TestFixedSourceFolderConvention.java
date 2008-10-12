package net.hillsdon.testlink.model.conventions;

import junit.framework.TestCase;

import org.easymock.EasyMock;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;

public class TestFixedSourceFolderConvention extends TestCase {

  public void test() throws Exception {
    // Ick.
    IJavaProject project = createMock(IJavaProject.class);
    IResource srcFolderResource = createMock(IResource.class);
    expect(srcFolderResource.getProjectRelativePath()).andReturn(new Path("unitTests/src"));
    IPackageFragmentRoot srcFolder = createMock(IPackageFragmentRoot.class);
    expect(srcFolder.getResource()).andReturn(srcFolderResource);
    expect(project.getPackageFragmentRoots()).andReturn(new IPackageFragmentRoot[] {srcFolder});
    IPackageFragment packageFragment = createMock(IPackageFragment.class);
    expect(packageFragment.getElementName()).andReturn("org.example");
    EasyMock.replay(packageFragment, project, srcFolder, srcFolderResource);

    FixedSourceFolderConvention convention = new FixedSourceFolderConvention("unitTests/src");
    assertSame(srcFolder, convention.getSourceFolder(project));
  }
  
}
