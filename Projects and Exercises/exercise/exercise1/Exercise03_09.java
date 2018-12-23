import java.util.Scanner;

//public class Exercise03_09 {
//  public static void main(String[] args) {
//    Scanner input = new Scanner(System.in);
//
//    // Prompt the user to enter an integer
//    System.out.print(
//      "Enter the first 9 digits of an ISBN as integer: ");
//    int number = input.nextInt();
//
//    // Calculate checksum (You may write a loop to simplify it in Ch5
//    int checksum = 
//      ((number / 100000000 % 10) * 1 +
//      (number / 10000000 % 10) * 2 +
//      (number / 1000000 % 10) * 3 +
//      (number / 100000 % 10) * 4 +
//      (number / 10000 % 10) * 5 +
//      (number / 1000 % 10) * 6 +
//      (number / 100 % 10) * 7 +
//      (number / 10 % 10) * 8 +
//      (number % 10) * 9) % 11;
//      
//    System.out.print("The ISBN-10 number is ");
//
//    // Display leading zeros, improve the solution using loops in Chapter 5
//    if (number / 100000000 == 0) {
//      System.out.print("0");
//      if (number / 10000000 == 0) {
//        System.out.print("0");
//        if (number / 1000000 == 0) {
//          System.out.print("0");
//          if (number / 100000 == 0) {
//            System.out.print("0");
//            if (number / 10000 == 0) {
//              System.out.print("0");
//              if (number / 1000 == 0) {
//                System.out.print("0");
//                if (number / 100 == 0) {
//                  System.out.print("0");
//                  if (number / 10 == 0) {
//                    System.out.print("0");
//                    if (number == 0) {
//                      System.out.print("0");
//                    }
//                  }
//                }
//              }
//            }
//          }
//        }
//      }
//    }
//
//    System.out.print(number);
//
//    if (checksum == 10)
//      System.out.print("X");
//    else
//      System.out.print(checksum);
//  }
//}

// A better solution is provided by Dr. Hector Javier Fontanez of Texas Wesleyan University 
/*
public class Exercise03_09 
{
  public static void main(String[] args)
  {
    // Prompt the user to enter an integer
    System.out.print("Enter the first 9 digits of an ISBN as integer: ");
    Scanner input = new Scanner(System.in);
    int number = input.nextInt();
    input.close();
    byte d9 = (byte) (number % 10);
    number /= 10;
    byte d8 = (byte) (number % 10);
    number /= 10;
    byte d7 = (byte) (number % 10);
    number /= 10;
    byte d6 = (byte) (number % 10);
    number /= 10;
    byte d5 = (byte) (number % 10);
    number /= 10;
    byte d4 = (byte) (number % 10);
    number /= 10;
    byte d3 = (byte) (number % 10);
    number /= 10;
    byte d2 = (byte) (number % 10);
    byte d1 = (byte) (number /= 10);
    System.out.print("The ISBN-10 number is " + d1 + d2 + d3 + d4 + d5 + d6 + d7 + d8 + d9 );

    // Calculate checksum (You may write a loop to simplify it in Ch4
    int checksum = (d1 * 1 + d2 * 2 + d3 * 3 + d4 * 4 + d5 * 5 + d6 * 6 + d7 * 7 + d8 * 8 + d9 * 9) % 11;
    System.out.print((checksum == 10) ? "X" : checksum );
  }
}
*/

public class Exercise03_09 {
  public static void main(String[] args) {
    // Prompt the user to enter an integer
    System.out.print("Enter the first 9 digits of an ISBN as integer: ");
    Scanner input = new Scanner(System.in);
    int number = input.nextInt();

    int d9 = number % 10;
    number /= 10;
    int d8 = number % 10;
    number /= 10;
    int d7 = number % 10;
    number /= 10;
    int d6 = number % 10;
    number /= 10;
    int d5 = number % 10;
    number /= 10;
    int d4 = number % 10;
    number /= 10;
    int d3 = number % 10;
    number /= 10;
    int d2 = number % 10;
    number /= 10;
    int d1 = number % 10;

    // Calculate checksum (You may write a loop to simplify it in Ch4
    int d10 = (d1 * 1 + d2 * 2 + d3 * 3 + d4 * 4
      + d5 * 5 + d6 * 6 + d7 * 7 + d8 * 8 + d9 * 9) % 11;

    if (d10 == 10) {
      System.out.print("The ISBN-10 number is "
        + d1 + d2 + d3 + d4 + d5 + d6 + d7 + d8 + d9 + "X");
    } 
    else {
      System.out.print("The ISBN-10 number is "
        + d1 + d2 + d3 + d4 + d5 + d6 + d7 + d8 + d9 + d10);
    }
  }
}


