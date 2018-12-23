<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import = "java.net.*" %>
<%@ page import = "java.io.*" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Display Current Largest One Hundred Prime Numbers</title>
  </head>
  <body>
    
    <center>Display Current Largest One Hundred Prime Numbers</center>
    <%= new java.util.Date() %>
    
    <h1>Y. Daniel Liang</h1>
    <%= getLast100Numbers() %>
    
    <%!
    public static String getLast100Numbers() throws Exception {
      // Create a socket to connect to the server
      // Socket socket = new Socket("localhost", 8000);
      // Socket socket = new Socket("130.254.204.36", 8000);
       Socket socket = new Socket("drake.Armstrong.edu", 8000);
      
      // Create an input stream to receive data from the server
      ObjectInputStream fromServer = new ObjectInputStream(
        socket.getInputStream());
      
      // Create an output stream to send data to the server
      java.util.ArrayList<Long> numbers
        = (java.util.ArrayList<Long>)(fromServer.readObject());
      
      String result = "";
      for (int i = 0; i < numbers.size(); i++) {
        result += numbers.get(i) + " ";
      }
      
      return result;
    }
    %>
  </body>
</html>
