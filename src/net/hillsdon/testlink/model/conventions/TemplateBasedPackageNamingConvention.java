package net.hillsdon.testlink.model.conventions;

import net.hillsdon.testlink.model.IPackageNamingConvention;

/**
 * E.g. "%s.tests"
 *
 * @author mth
 */
public class TemplateBasedPackageNamingConvention implements IPackageNamingConvention {

  private final Template _template;

  public TemplateBasedPackageNamingConvention(final String template) {
    _template = new Template(template);
  }

  public String getImplPackageName(final String testPackageName) {
    return _template.unformat(testPackageName);
  }

  public String getTestPackageName(final String implClassName) {
    return _template.format(implClassName);
  }

}
