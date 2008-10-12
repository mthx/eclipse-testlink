package net.hillsdon.testlink.model;

/**
 * Encapsulates a particular naming convention for tests and implementations.
 * 
 * @author mth
 */
public interface IPackageNamingConvention {
  
  String getImplPackageName(String testPackageName);
  String getTestPackageName(String implPackageName);
  
}
