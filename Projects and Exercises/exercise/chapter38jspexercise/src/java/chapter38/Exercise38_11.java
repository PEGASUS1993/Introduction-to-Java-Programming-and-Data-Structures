package chapter38;

import java.util.*;

public class Exercise38_11 {
    boolean stringToken = false;
    
    // Array of all Java keywords + true + false + null
    final static String[] keywordString = {"abstract", "assert", "boolean",
                "break", "byte", "case", "catch", "char", "class", "const",
                "continue", "default", "do", "double", "else", "enum",
                "extends", "for", "final", "finally", "float", "goto", "if",
                "implements", "import", "instanceof", "int", "interface",
                "long", "native", "new", "package", "private", "protected",
                "public", "return", "short", "static", "strictfp", "super",
                "switch", "synchronized", "this", "throw", "throws",
                "transient", "try", "void", "volatile", "while",
                "true", "false", "null"};
                
    final static Set<String> keywordSet = new HashSet<String>(Arrays.asList(keywordString));

  /** Translate Java source code to HTML */
  public String translateToHTML(String text) {
    text = text.replaceAll("//", "LINECOMMENT");
    text = text.replaceAll("/\\*", "BLOCKCOMMENT");

    String token;
    
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.setLength(0);
    Formatter output = new Formatter(stringBuilder);
      
    while (text != null && text.length() > 0) {
      // * and / are in conflict with /* and //
      String[] parts = text.split("[%\\+\\-\\*/\r\n\t \\[\\].;(){},]", 2);

      token = parts[0];

      if (token.length() > 1 && token.startsWith("LINECOMMENT")) {
        output.format("%s", "<span class = \"comment\">");
        parts = text.split("\r\n", 2);
        text = parts[1];
        output.format("%s", parts[0].replaceAll("LINECOMMENT", "//"));
        output.format("%s", "</span>\r\n");
        continue;
      }
      else if (token.length() > 1 && token.startsWith("BLOCKCOMMENT")) {
        output.format("%s", "<span class = \"comment\">");
        parts = text.split("\\*/", 2);
        text = parts[1];

        output.format("%s", parts[0].replaceAll("BLOCKCOMMENT", "/*") + "*/");
        output.format("%s", "</span>");
        continue;
      }
      else if (token.length() > 1 && token.matches("'\\w'*")) {
        output.format("%s", "<span class = \"literal\">");
        output.format("%s", token);
        output.format("%s", "</span>");
      }
      else if (token.startsWith("\"") && token.endsWith("\"") &&
               (token.length() > 1)) {
        output.format("%s", "<span class = \"literal\">" + token
                      + "</span>");
      }
      else if (token.startsWith("\"") && token.endsWith("\"") &&
               (token.length() == 1)) {
        if (stringToken) {
          output.format("%s", token + "</span>");
          stringToken = false;
        }
        else {
          output.format("%s", "<span class = \"literal\">" + token);
          stringToken = true;
        }
      }
      else if (token.startsWith("\"")) {
        output.format("%s", "<span class = \"literal\">" + token);
        stringToken = true;
      }
      else if (token.endsWith("\"") && (!token.endsWith("\\\""))) {
        output.format("%s", token);
        output.format("%s", "</span>");
        stringToken = false;
      }
      else if (token.matches("\\d*")) { // Check if numeric
        output.format("%s", "<span class = \"literal\">" + token +
                      "</span>");
      }
      else if (!stringToken && keywordSet.contains(token)) {
        output.format("%s", "<span class = \"keyword\">" + token +
                      "</span>");
      }
      else {
        output.format("%s", token);
      }

      if (token.length() < text.length()) {
        output.format("%s", text.charAt(token.length()));
      }

      if (parts.length == 2) {
        text = parts[1];
      }
    }
    
    return stringBuilder.toString();
  }
}