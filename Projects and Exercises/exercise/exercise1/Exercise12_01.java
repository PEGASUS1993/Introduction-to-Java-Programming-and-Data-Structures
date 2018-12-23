public class Exercise12_01 {
  public static void main (String[] args) {
    int num1, num2, result = 0;

    if (args.length != 3) {
      System.out.println(
        "please use java Exercise12_01 operand1 operator operand2");
      System.exit(1);
    }
    
    try {
      num1 = Integer.parseInt(args[0]);
    }
    catch (NumberFormatException ex) {
      System.out.println("Wrong Input: " + args[0]);
      return;
    }

    try {
      num2 = Integer.parseInt(args[2]);
    }
    catch (Exception ex) {
      System.out.println("Wrong Input: " + args[2]);
      return;
    }

    switch (args[1].charAt(0)) {
      case '+': result = num1 + num2;
	        break;
      case '-': result = num1 - num2;;
                break;
      case '.': result = num1 * num2;;
	        break;
      case '/': result = num1 / num2;;
    }

    System.out.println(args[0] + " " + args[1] + " " + args[2] + " = " + result);
  }
}
