import java.util.Scanner;
import java.util.ArrayList;

public class Exercise11_15 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter the number of points: ");
    int n = input.nextInt();

    System.out.print("Enter the coordinates of the points: ");
    ArrayList<Double> x = new ArrayList<>();
    ArrayList<Double> y = new ArrayList<>();

    for (int i = 0; i < n; i++) {
      x.add(input.nextDouble());
      y.add(input.nextDouble());
    }
      
    double total = 0;
    for (int i = 1; i < n - 1; i++) 
      total += getArea(x.get(0), y.get(0), x.get(i), y.get(i), x.get(i + 1), y.get(i + 1));
      
    System.out.print("The total area is " + total);
  }
  
  public static double getArea(double x1, double y1, double x2, double y2, double x3, double y3) {
    double s1 = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    double s2 = Math.sqrt((x1 - x3) * (x1 - x3) + (y1 - y3) * (y1 - y3));
    double s3 = Math.sqrt((x3 - x2) * (x3 - x2) + (y3 - y2) * (y3 - y2));
      
    double s = (s1 + s2 + s3) / 2;
    return Math.sqrt(s * (s - s1) * (s - s2) * (s - s3));
  }
}

// Second solution using the formula from For the formula for computing the area of a polygon, see http://www.mathwords.com/a/area_convex_polygon.htm.
/*
import java.util.*;

public class Exercise11_15 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		System.out.print("Enter the number of points: ");
		int points = in.nextInt();
		
		System.out.println("Enter the coordinates of the points: ");
		ArrayList<Double> xcoordinates = new ArrayList<Double>();
		ArrayList<Double> ycoordinates = new ArrayList<Double>();
		for (int i = 0; i < points; i++) {
			xcoordinates.add(in.nextDouble());
			ycoordinates.add(in.nextDouble());
		}

		xcoordinates = reverseArrayList(xcoordinates);
		ycoordinates = reverseArrayList(ycoordinates);
		
		xcoordinates.add(xcoordinates.get(0));
		ycoordinates.add(ycoordinates.get(0));
		
		double area = calculateArea(xcoordinates, ycoordinates);
		
		System.out.println("The total area is " + area);
		
		in.close();
	}
	
	public static ArrayList<Double> reverseArrayList(ArrayList<Double> list) {
		ArrayList<Double> newList = new ArrayList<Double>();
		
		for (int i = list.size() - 1; i >= 0; i--)
			newList.add(list.get(i));
		
		return newList;
	}
	
	public static double calculateArea(ArrayList<Double> list1, ArrayList<Double> list2) {
		double sum1 = 0, sum2 = 0, area = 0;
		
		for (int i = 0; i < list1.size() - 1; i++)
			sum1 += list1.get(i) * list2.get(i + 1);
		for (int i = 0; i < list2.size() - 1; i++)
			sum2 += list2.get(i) * list1.get(i + 1);
		
		area = 0.5 * (sum1 - sum2);
		
		return area;
	}
}
*/