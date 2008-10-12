package net.hillsdon.testlink.model.impl;

import static java.util.Arrays.asList;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import junit.framework.TestCase;

import org.eclipse.core.resources.IResource;

/**
 * Test for {@link PrioritizeSourceFiles}.
 * 
 * @author mth
 */
public class TestPrioritizeSourceFiles extends TestCase {

  public void testJavaExtensionFirst() {
    final IResource classResource = resource("class");
    final IResource javaResource = resource("java");
    final List<IResource> input = new ArrayList<IResource>(asList(classResource, javaResource, classResource));
    final List<IResource> expected = asList(javaResource, classResource, classResource);
    Collections.sort(input, PrioritizeSourceFiles.INSTANCE);
    assertEquals(expected, input);
  }

  private IResource resource(final String extension) {
    IResource resource = createMock(IResource.class);
    expect(resource.getFileExtension()).andReturn(extension).anyTimes();
    replay(resource);
    return resource;
  }
  
}
