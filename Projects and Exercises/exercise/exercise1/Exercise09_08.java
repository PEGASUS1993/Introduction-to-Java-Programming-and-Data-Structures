public class Exercise09_08 {
  public static void main(String[] args) {
    Fan1 fan1 = new Fan1();
    fan1.setSpeed(Fan1.FAST);
    fan1.setRadius(10);
    fan1.setColor("yellow");
    fan1.setOn(true);
    System.out.println(fan1.toString());

    Fan1 fan2 = new Fan1();
    fan2.setSpeed(Fan1.MEDIUM);
    fan2.setRadius(5);
    fan2.setColor("blue");
    fan2.setOn(false);
    System.out.println(fan2.toString());
  }
}

class Fan1 {
  public static int SLOW = 1;
  public static int MEDIUM = 2;
  public static int FAST = 3;

  private int speed = SLOW;
  private boolean on = false;
  private double radius = 5;
  private String color = "white";

  public Fan1() {
  }

  public int getSpeed() {
    return speed;
  }

  public void setSpeed(int newSpeed) {
    speed = newSpeed;
  }

  public boolean isOn() {
    return on;
  }

  public void setOn(boolean trueOrFalse) {
    this.on = trueOrFalse;
  }

  public double getRadius() {
    return radius;
  }

  public void setRadius(double newRadius) {
    radius = newRadius;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String newColor) {
    color = newColor;
  }

  @Override
  public String toString() {
    return "speed " + speed + "\n"
      + "color " + color + "\n"
      + "radius " + radius + "\n"
      + ((on) ? "fan is on" : " fan is off");
  }
}
