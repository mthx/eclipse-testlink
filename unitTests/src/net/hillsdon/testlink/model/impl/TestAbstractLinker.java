package net.hillsdon.testlink.model.impl;

import junit.framework.TestCase;
import net.hillsdon.testlink.model.Linkage;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.ITypeHierarchy;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.isA;
import static org.easymock.EasyMock.replay;

/**
 * Tests for {@link AbstractLinker}.
 * 
 * @author mth
 */
public class TestAbstractLinker extends TestCase {

  private final AbstractLinker _linker = new AbstractLinker() {
    public IResource find(final IType type, final Linkage direction) throws CoreException {
      throw new UnsupportedOperationException("Don't use me for real!");
    }
  };

  public void testSuperClassIsTestCaseIsType() throws Exception {
    IType type = createType(new String[] {"java.lang.Object", "junit.framework.TestCase"});
    assertEquals(Linkage.TEST, _linker.getLinkageFor(type));
  }
  
  public void testOtherSuperClassesArentTests() throws Exception {
    IType type = createType(new String[] {"java.lang.Object", "junit.framework.TestSuite"});
    assertEquals(Linkage.IMPL, _linker.getLinkageFor(type));
  }

  private IType createType(final String[] superClassNames) throws Exception {
    IType type = createMock(IType.class);
    ITypeHierarchy hierarchy = createMock(ITypeHierarchy.class);
    expect(hierarchy.getAllSuperclasses(type)).andReturn(createTypes(superClassNames)).anyTimes();
    expect(type.newSupertypeHierarchy(isA(NullProgressMonitor.class))).andReturn(hierarchy).anyTimes();
    replay(type, hierarchy);
    return type;
  }

  private IType[] createTypes(final String[] fullyQualifiedNames) {
    IType[] types = new IType[fullyQualifiedNames.length];
    for (int i = 0; i < types.length; i++) {
      types[i] = createMock(IType.class);
      expect(types[i].getFullyQualifiedName()).andReturn(fullyQualifiedNames[i]).anyTimes();
      replay(types[i]);
    }
    return types;
  }
  
}
