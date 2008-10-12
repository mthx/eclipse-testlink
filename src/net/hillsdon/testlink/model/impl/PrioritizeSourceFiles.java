package net.hillsdon.testlink.model.impl;

import java.util.Comparator;

import org.eclipse.core.resources.IResource;

/**
 * We prioritize .java resources, .class files are generally less interesting.
 */
class PrioritizeSourceFiles implements Comparator<IResource> {
  
  public static final Comparator<IResource> INSTANCE = new PrioritizeSourceFiles();

  public int compare(final IResource left, final IResource right) {
    return prioritizeJavaExtension(left) - prioritizeJavaExtension(right);
  }

  /**
   * @return 0 if has java extension, 1 if not.
   */
  private int prioritizeJavaExtension(final IResource resource) {
    return !hasJavaExtension(resource) ? 1 : 0;
  }

  private boolean hasJavaExtension(final IResource resource) {
    final String extension = resource.getFileExtension();
    return extension != null && "java".equals(extension.toLowerCase());
  }
  
}