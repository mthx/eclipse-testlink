package net.hillsdon.testlink.model.conventions;

import junit.framework.TestCase;
import net.hillsdon.testlink.model.IFileNamingConvention;

/**
 * Test for {@link TemplateBasedFileNamingConvention}.
 *
 * @author mth
 */
public class TestTemplateBasedFileNamingConvention extends TestCase {

  public void testPrefixAndSuffix() {
    IFileNamingConvention convention = new TemplateBasedFileNamingConvention("Test%sFunctionally");
    assertEquals("CheeseCutter", convention.getImplClassName("TestCheeseCutterFunctionally"));
    assertEquals("TestCheeseCutterFunctionally", convention.getTestClassName("CheeseCutter"));
  }

  public void testInputMissingPrefixOrSuffixJustStripsWhateverWeHave() {
    IFileNamingConvention convention = new TemplateBasedFileNamingConvention("Test%sFunctionally");
    assertEquals("CheeseCutter", convention.getImplClassName("TestCheeseCutter"));
    assertEquals("CheeseCutter", convention.getImplClassName("CheeseCutterFunctionally"));
    assertEquals("DontTestCheeseCutter", convention.getImplClassName("DontTestCheeseCutter"));
  }

  public void testTemplateMissingPrefixOrSuffix() {
    IFileNamingConvention suffixOnly = new TemplateBasedFileNamingConvention("%sTest");
    assertEquals("BarTest", suffixOnly.getTestClassName("Bar"));
    assertEquals("Bar", suffixOnly.getImplClassName("BarTest"));
    assertEquals("CheeseCutter", suffixOnly.getImplClassName("CheeseCutterTest"));
    assertEquals("", suffixOnly.getImplClassName("Test"));

    IFileNamingConvention prefixOnly = new TemplateBasedFileNamingConvention("Test%s");
    assertEquals("CheeseCutter", prefixOnly.getImplClassName("TestCheeseCutter"));
    assertEquals("", prefixOnly.getImplClassName("Test"));
  }

}

