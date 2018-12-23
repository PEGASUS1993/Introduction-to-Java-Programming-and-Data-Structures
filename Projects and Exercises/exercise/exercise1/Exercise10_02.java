public class Exercise10_02 {
  public static void main(String[] args) {
    BMI bmi1 = new BMI("John Doe", 18, 145, 5, 10);
    System.out.println("The BMI for " + bmi1.getName() + " is "
      + bmi1.getBMI() + " " + bmi1.getStatus());
    
    BMI bmi2 = new BMI("Peter King", 215, 5, 10);
    System.out.println("The BMI for " + bmi2.getName() + " is "
      + bmi2.getBMI() + " " + bmi2.getStatus());
  }

  static class BMI {
    private String name;
    private int age;
    private double weight; // in pounds
    private double height; // in inches
    public final double KILOGRAMS_PER_POUND = 0.45359237; 
    public final double METERS_PER_INCH = 0.0254;  
    
    /** Construct a BMI with the specified name, age, weight, 
     * feet and inches
     */
    public BMI(String name, int age, double weight, double feet, double inches) {
      this.name = name;
      this.age = age;
      this.weight = weight;
      this.height = feet * 12 + inches;
    }

    public BMI(String name, int age, double weight, double height) {
      this.name = name;
      this.age = age;
      this.weight = weight;
      this.height = height;
    }
    
    public BMI(String name, double weight, double height) {
      this(name, 20, weight, height);
    }
    
    public double getBMI() {
      double bmi = weight * KILOGRAMS_PER_POUND / 
       ((height * METERS_PER_INCH) * (height * METERS_PER_INCH));
      return Math.round(bmi * 100) / 100.0;
    }
    
    public String getStatus() {
      double bmi = getBMI();
      if (bmi < 16)
        return "seriously underweight";
      else if (bmi < 18)
        return "underweight";
      else if (bmi < 24)
        return "normal weight";
      else if (bmi < 29)
        return "over weight";
      else if (bmi < 35)
        return "seriously over weight";
      else
        return "gravely over weight";
    }
    
    public String getName() {
      return name;
    }
    
    public int getAge() {
      return age;
    }
    
    public double getWeight() {
      return weight;
    }
    
    public double getHeight() {
      return height;
    }
  }
}
