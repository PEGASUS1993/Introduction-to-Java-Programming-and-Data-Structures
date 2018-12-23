import java.util.*;

public class Exercise20_03 {
  public static void main(String[] args) {
    String[][] statecapital = {
      {"Alabama", "Montgomery"},
      {"Alaska", "Juneau"},
      {"Arizona", "Phoenix"},
      {"Arkansas", "Little Rock"},
      {"California", "Sacramento"},
      {"Colorado", "Denver"},
      {"Connecticut", "Hartford"},
      {"Delaware", "Dover"},
      {"Florida", "Tallahassee"},
      {"Georgia", "Atlanta"},
      {"Hawaii", "Honolulu"},
      {"Idaho", "Boise"},
      {"Illinois", "Springfield"},
      {"Maryland", "Annapolis"},
      {"Minnesota", "Saint Paul"},
      {"Iowa", "Des Moines"},
      {"Maine", "Augusta"},
      {"Kentucky", "Frankfort"},
      {"Indiana", "Indianapolis"},
      {"Kansas", "Topeka"},
      {"Louisiana", "Baton Rouge"},
      {"Oregon", "Salem"},
      {"Oklahoma", "Oklahoma City"},
      {"Ohio", "Columbus"},
      {"North Dakota", "Bismark"},
      {"New York", "Albany"},
      {"New Mexico", "Santa Fe"},
      {"New Jersey", "Trenton"},
      {"New Hampshire", "Concord"},
      {"Nevada", "Carson City"},
      {"Nebraska", "Lincoln"},
      {"Montana", "Helena"},
      {"Missouri", "Jefferson City"},
      {"Mississippi", "Jackson"},
      {"Massachusettes", "Boston"},
      {"Michigan", "Lansing"},
      {"Pennslyvania", "Harrisburg"},
      {"Rhode Island", "Providence"},
      {"South Carolina", "Columbia"},
      {"South Dakota", "Pierre"},
      {"Tennessee", "Nashville"},
      {"Texas", "Austin"},
      {"Utah", "Salt Lake City"},
      {"Vermont", "Montpelier"},
      {"Virginia", "Richmond"},
      {"Washington", "Olympia"},
      {"West Virginia", "Charleston"},
      {"Wisconsin", "Madison"},
      {"Wyoming", "Cheyenne"}
    };

    List<String[]> list = Arrays.asList(statecapital);
    Collections.shuffle(list);

    int correctCount = 0;

    Scanner input = new Scanner(System.in);
    
    for (int i = 0; i < list.size(); i++) {
      // Prompt the user with a question
      System.out.print("What is the capital of " +
          ((String[])(list.get(i)))[0] + "? ");
      String capital = input.nextLine();

      if (capital.equals(((String[])(list.get(i)))[1])) {
        System.out.println("Your anwswer is correct");
        correctCount++;
      }
      else
        System.out.println(
          "The correct answer should be " + ((String[])(list.get(i)))[1]);
    }

    System.out.println("The correct count is " + correctCount);
  }
}
