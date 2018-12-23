public class Exercise13_06Extra {
  public static void main(String[] args) {
    Pig pig = new Pig();
    Dog dog = new Dog();
    System.out.println(pig.sound());
    System.out.println(pig.howToEat());
    System.out.println(dog.sound());    
  }
}

class Pig extends Animal implements Edible {
  public String sound() {
    return "Gaga";
  }
  
  public String howToEat() {
    return "Roast";
  }
}

class Dog extends Animal {
  public String sound() {
    return "Woof";
  }
}