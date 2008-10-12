package net.hillsdon.testlink.model;

/**
 * Encapsulates a particular naming convention for tests and implementations.
 *
 * The returned values are not guaranteed to be valid Java.
 *
 * @author mth
 */
public interface IFileNamingConvention {

  String getImplClassName(String testClassName);
  String getTestClassName(String implClassName);

}
