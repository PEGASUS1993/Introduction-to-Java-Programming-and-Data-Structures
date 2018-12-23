public class Exercise13_07 {
  public static void main(String[] args) {
    GeometricObject[] objects = {new Square(2), new Circle(5), new Square(5), new Rectangle(3, 4), new Square(4.5)};
    
    for (int i = 0; i < objects.length; i++) {
      System.out.println("Area is " + objects[i].getArea());
      if (objects[i] instanceof Colorable)
        ((Colorable)objects[i]).howToColor();
    }
  }
}

interface Colorable {
  void howToColor();
}

class Square extends GeometricObject implements Colorable {
  private double side;

  public Square(double side) {
    this.side = side;
  }

  @Override
  public void howToColor() {
    System.out.println("Color all four sides");
  }

  @Override
  public double getArea() {
    return side * side;
  }

  @Override
  public double getPerimeter() {
    return 4 * side;
  }
}
