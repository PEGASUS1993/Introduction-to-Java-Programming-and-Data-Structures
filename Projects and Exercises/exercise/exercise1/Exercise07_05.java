/*
Strategy:

use an array numbers to store the distinct values.

read each value from input, check if it is already in the array numbers, 
if not, add it into it.
 
// Suggested template:
public class Exercise07_05 {
  public static void main(String[] args) {
    // numbers array will store distinct values, maximum is 10
    int[] numbers = new int[10]; 

    // How many distinct numbers are in the array
    int numberOfDistinctValues = 0; 
    java.util.Scanner input = new java.util.Scanner(System.in);
    System.out.print("Enter ten numbers: ");

    for (int i = 0; i < numbers.length; i++) {
      // Read an input
      int value = input.nextInt();

      Step 1: Check if value is in numbers

      Step 2: If value is not in numbers, add it into numbers

      Step 3: Increment numberOfDistinctValues 

     }

    Step 4: Display output
  }
}
*/
// Version 1
public class Exercise07_05 {
  public static void main(String[] args) {
    int[] numbers = new int[10]; // numbers array will store distinct values, maximum is 10
    int numberOfDistinctValues = 0; // How many distinct numbers are in the
                                    // array
    java.util.Scanner input = new java.util.Scanner(System.in);
    System.out.print("Enter " + numbers.length + " numbers: ");

    for (int i = 0; i < numbers.length; i++) {
      // Read an input
      int value = input.nextInt();

      // Step 1: Check if value is in numbers
      boolean isInTheArray = false;
      for (int j = 0; j < numberOfDistinctValues; j++) {
      	if (value == numbers[j]) {
          isInTheArray = true;
      	}
      }
      
      // Step 2: If value is not in numbers, add it into numbers
      if (!isInTheArray) {
      	numbers[numberOfDistinctValues] = value;

      	// Step 3: Increment numberOfDistinctValues       
      	numberOfDistinctValues++;
      }
    }

    System.out.println("The number of distinct integers is " 
    	+ numberOfDistinctValues);
    System.out.print("The distinct integers are ");
    for (int i = 0; i < numberOfDistinctValues; i++)
      System.out.print(numbers[i] + " ");
  }
}

//Version 2 
/*
public class Exercise07_05 {
  public static void main(String[] args) {
    int[] numbers = new int[10]; // numbers array will store distinct values, maximum is 10
    int numberOfDistinctValues = 0; // How many distinct numbers are in the
                                    // array
    java.util.Scanner input = new java.util.Scanner(System.in);
    System.out.print("Enter " + numbers.length + " numbers: ");

    for (int i = 0; i < numbers.length; i++) {
      // Read an input
      int value = input.nextInt();

      // Step 1: Check if value is in numbers
      boolean isInTheArray = false;
      for (int j = 0; j < numberOfDistinctValues; j++) {
      	if (value == numbers[j]) {
          isInTheArray = true;
          break;
      	}
      }
      
      // Step 2: If value is not in numbers, add it into numbers
      if (!isInTheArray) {
      	numbers[numberOfDistinctValues] = value;

      	// Step 3: Increment numberOfDistinctValues       
      	numberOfDistinctValues++;
      }
    }

    System.out.println("The number of distinct values is " 
    	+ numberOfDistinctValues);
    for (int i = 0; i < numberOfDistinctValues; i++)
      System.out.print(numbers[i] + " ");
  }
}
*/

/** Version 3
public class Exercise07_05 {
  public static void main(String[] args) {
    int[] numbers = new int[10]; // numbers array will store distinct values, maximum is 10
    int numberOfDistinctValues = 0; // How many distinct numbers are in the
                                    // array
    java.util.Scanner input = new java.util.Scanner(System.in);
    System.out.print("Enter ten numbers: ");

    for (int i = 0; i < numbers.length; i++) {
      // Read an input
      int value = input.nextInt();

      int j = 0;
      for (; j < numberOfDistinctValues; j++)
        if (numbers[j] == value) {
          break; // It is in the numbers array, skip
        }

      // j == numberOfDistinctValues indicates that value is not in numbers array, add it to it
      if (j == numberOfDistinctValues) {
        numbers[numberOfDistinctValues] = value; // value is not in numbers array yet, store it 
        numberOfDistinctValues++;
      }
    }

    System.out.println("The number of distinct values is " + numberOfDistinctValues);
    for (int i = 0; i < numberOfDistinctValues; i++)
      System.out.print(numbers[i] + " ");
  }
}
*/
