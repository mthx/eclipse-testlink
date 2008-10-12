package net.hillsdon.testlink.model.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;

import junit.framework.TestCase;
import net.hillsdon.testlink.model.IConventionBundle;
import net.hillsdon.testlink.model.Linkage;
import net.hillsdon.testlink.preferences.ITestlinkPreferences;

import org.easymock.EasyMock;
import org.eclipse.core.resources.IResource;
import org.eclipse.jdt.core.IType;

import static java.util.Arrays.asList;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;

/**
 * Tests for {@link DefaultLinker}.
 * 
 * @author mth
 */
public class TestDefaultLinker extends TestCase {

  private ISearcher _searcher;
  private DefaultLinker _linker;
  private IType _type;
  private IResource _resource;
  private ITestlinkPreferences _preferences;
  private IConventionBundle _conventions;

  public void setUp() throws Exception {
    _searcher = createMock(ISearcher.class);
    _preferences = createMock(ITestlinkPreferences.class);
    _linker = new DefaultLinker(_searcher, _preferences);
    _type = createMock(IType.class);
    _resource = createMock(IResource.class);
    
    _conventions = createMock(IConventionBundle.class);
    expect(_preferences.getConventions()).andReturn(Arrays.asList(_conventions));
  }
  
  public void testPerformsAppropriateSearchForTest() throws Exception {
    expect(_conventions.getTestClassName("FooImpl")).andReturn("TestFooImpl");
    checkPerformsAppropriateSearch(Linkage.IMPL, "FooImpl", "TestFooImpl");
  }
  
  public void testPerformsAppropriateSearchForImpl() throws Exception {
    expect(_conventions.getImplClassName("TestFooImpl")).andReturn("FooImpl");
    checkPerformsAppropriateSearch(Linkage.TEST, "TestFooImpl", "FooImpl");
  }

  private void checkPerformsAppropriateSearch(final Linkage direction, final String inputType, final String expectedSearchType) throws Exception {
    expect(_type.getElementName()).andReturn(inputType).anyTimes();
    expect(_searcher.search(expectedSearchType)).andReturn(new LinkedHashSet<IResource>(asList(_resource)));
    replay();
    assertSame(_resource, _linker.find(_type, direction));
    verify();
  }
  
  public void testReturnsNullIfNothingFound() throws Exception {
    expect(_conventions.getTestClassName("Foo")).andReturn("TestFoo");
    expect(_type.getElementName()).andReturn("Foo").anyTimes();
    expect(_searcher.search("TestFoo")).andReturn(Collections.<IResource>emptySet());
    replay();
    assertNull(_linker.find(_type, Linkage.IMPL));
    verify();
  }

  private void verify() {
    EasyMock.verify(_searcher);
  }

  private void replay() {
    EasyMock.replay(_conventions, _preferences, _type, _resource, _searcher);
  }
  
}
