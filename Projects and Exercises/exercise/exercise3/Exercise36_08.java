import java.text.*;

public class Exercise36_08 {
  public static void main(String[] args) {
    System.out.println("Cels. Temp.\t\tFahr. Temp.");
    System.out.println("--------------------------");

    NumberFormat numberForm = NumberFormat.getNumberInstance();
    numberForm.setMaximumFractionDigits(2);
    numberForm.setMinimumFractionDigits(2);

    DecimalFormat df = (DecimalFormat)numberForm;
    df.applyPattern("00.00");

    for (double celsius = 40; celsius >= 31; celsius--) {
      System.out.println(df.format(celsius) + "\t\t\t" +
      numberForm.format(celsToFahr(celsius)));
    }
  }

  public static double celsToFahr(double cels) {
    return (9.0 / 5.0) * cels + 32;
  }
}
