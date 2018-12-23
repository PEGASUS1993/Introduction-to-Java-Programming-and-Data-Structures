public class Exercise04_03 {
  public static void main(String[] args) {
    double x1 = Math.toRadians(35.2270869), y1 = Math.toRadians(-80.8431267); // Charlotte
    double x2 = Math.toRadians(32.0835407), y2 = Math.toRadians(-81.0998342); // Savannah
    double x3 = Math.toRadians(28.5383355), y3 = Math.toRadians(-81.3792365); // Orlando
    double x4 = Math.toRadians(33.7489954), y4 = Math.toRadians(-84.3879824); // Atlanta

//    or
//    double x1 = 35.2270869 * Math.PI / 180, y1 = -80.8431267 * Math.PI / 180; // Charlotte
//    double x2 = 32.0835407 * Math.PI / 180, y2 = -81.0998342 * Math.PI / 180; // Savannah
//    double x3 = 28.5383355 * Math.PI / 180, y3 = -81.3792365 * Math.PI / 180; // Orlando
//    double x4 = 33.7489954 * Math.PI / 180, y4 = -84.3879824 * Math.PI / 180; // Atlanta

    double d1 = 6371.01 * Math.acos(Math.sin(x1) * Math.sin(x2) +
        Math.cos(x1) * Math.cos(x2) * Math.cos(y1 - y2));

    double d2 = 6371.01 * Math.acos(Math.sin(x3) * Math.sin(x2 ) +
        Math.cos(x3) * Math.cos(x2) * Math.cos(y3 - y2));

    double d3 = 6371.01 * Math.acos(Math.sin(x3) * Math.sin(x4 ) +
        Math.cos(x3) * Math.cos(x4) * Math.cos(y3 - y4));

    double d4 = 6371.01 * Math.acos(Math.sin(x1) * Math.sin(x4 ) +
        Math.cos(x1) * Math.cos(x4) * Math.cos(y1 - y4));

    double d5 = 6371.01 * Math.acos(Math.sin(x4) * Math.sin(x2 ) +
        Math.cos(x4) * Math.cos(x2) * Math.cos(y4 - y2));

    double s = (d1 + d4 + d5) / 2;
    double area1 = Math.sqrt(s * (s - d1) * (s - d4) * (s - d5));

    s = (d2 + d3 + d5) / 2;
    double area2 = Math.sqrt(s * (s - d2) * (s - d3) * (s - d5));

    System.out.println("The area is " + (area1 + area2) + " square kilometers");
  }
}