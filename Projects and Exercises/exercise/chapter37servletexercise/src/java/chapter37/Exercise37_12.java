package chapter37;

import java.io.*;
import java.net.*;

import javax.servlet.*;
import javax.servlet.http.*;

import java.util.*;

public class Exercise37_12 extends HttpServlet {
    static boolean stringToken = false;
    static String inputFileName;
                
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
      PrintWriter output = response.getWriter();
      String javaSourceText = request.getParameter("program");
      
      output.format("%s\r\n", "<html>");
      output.format("%s\r\n", "<head>");
      output.format("%s\r\n",
                    "<title>Syntax-Highlighted Code</title>");
      output.format("%s\r\n",
                    "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=windows-1252\">");
      output.format("%s\r\n", "<style type = \"text/css\">");
      output.format("%s\r\n",
                    "body {font-family: \"Courier New\", sans-serif; font-size: 100%; color: black}");

      output.format("%s\r\n", ".keyword {color: #000080; font-weight: bold}");
      output.format("%s\r\n", ".comment {color: #008000}");
      output.format("%s\r\n", ".literal {color: #0000ff}");
      output.format("%s\r\n", "</style>");

      output.format("%s\r\n", "</head>");
      output.format("%s\r\n", "<body>");
      output.format("%s\r\n", "<pre>");

      javaSourceText = javaSourceText.replaceAll(">", "&gt;");
      javaSourceText = javaSourceText.replaceAll("<", "&lt;");
      translateToHTML(javaSourceText, output);

      output.format("%s\r\n", "</pre>");
      output.format("%s\r\n", "</body>");
      output.format("%s\r\n", "</html>");
  }
    
      /** Translate Java source code to HTML */
  private void translateToHTML(String text, PrintWriter output) {
    // Array of all Java keywords + true + false + null
    String[] keywordString = {"abstract", "assert", "boolean",
                "break", "byte", "case", "catch", "char", "class", "const",
                "continue", "default", "do", "double", "else", "enum",
                "extends", "for", "final", "finally", "float", "goto", "if",
                "implements", "import", "instanceof", "int", "interface",
                "long", "native", "new", "package", "private", "protected",
                "public", "return", "short", "static", "strictfp", "super",
                "switch", "synchronized", "this", "throw", "throws",
                "transient", "try", "void", "volatile", "while",
                "true", "false", "null"};
                
    Set keywordSet = new HashSet(Arrays.asList(keywordString));

    text = text.replaceAll("//", "LINECOMMENT");
    text = text.replaceAll("/\\*", "BLOCKCOMMENT");

    String token;

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
  }
}
