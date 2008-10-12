package net.hillsdon.testlink.model.conventions;

public class Template {
  
  private final String _template;
  
  public Template(final String template) {
    _template = template;
  }
  
  public String unformat(final String text) {
    String result = text;
    String[] parts = splitTemplate();
    if (result.startsWith(parts[0])) {
      result = result.substring(parts[0].length());
    }
    if (result.endsWith(parts[1])) {
      result = result.substring(0, result.length() - parts[1].length());
    }
    return result;
  }
  
  public String format(final String text) {
    return _template.replace("%s", text);
  }

  private String[] splitTemplate() {
    String[] parts = _template.split("%s");
    if (parts.length == 1) {
      parts = new String[] {parts[0], ""};
    }
    return parts;
  }


}
