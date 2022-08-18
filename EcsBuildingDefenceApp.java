import bugs.Bug;
import bugs.ConcurrentModificationBug;
import bugs.NoneTerminationBug;
import bugs.NullPointerBug;
import building.Building;
import students.Team;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EcsBuildingDefenceApp {
  private final String fileName;
  private final Building building;
  private final Battle battle;

  public EcsBuildingDefenceApp(
      int topFloor, int constructionPoints, String fileName, int initialKnowledgePoints) {
    building = new Building(topFloor, constructionPoints);
    Team team = new Team(initialKnowledgePoints);
    battle = new Battle(team, building);
    this.fileName = fileName;
  }

  /**
   * The parseBugs() method uses patterns and matchers to extract information from the text file. It
   * uses patterns to capture character classes, greedy quantifiers, and a logical operator in order
   * to get the desired output. The pattern thus allows me to create a matcher object which matches
   * the character sequences to the regular expression defined. So for example in the pattern it
   * first captures 1 or more characters which could be lower case or upper case, and then when the
   * matcher recognizes this within the text file it sets to String name.
   *
   * @param line
   * @return
   * @throws IOException
   */
  private ArrayList<Bug> parseBugs(String line) throws IOException {
    ArrayList<Bug> bugs = new ArrayList<>();
    Pattern pattern = Pattern.compile("([a-zA-Z]+)\\(([A-Z]+),(\\d+),(\\d+)\\)");
    String[] tokens = line.split(";");
    for (String token : tokens) {
      Matcher matcher = pattern.matcher(token);
      if (matcher.matches() && matcher.groupCount() == 4) {
        String name = matcher.group(1);
        String type = matcher.group(2);
        int level = Integer.parseInt(matcher.group(3));
        int steps = Integer.parseInt(matcher.group(4));
        Bug bug = createBug(name, type, level, steps);
        bugs.add(bug);
      }
    }
    return bugs;
  }

  /**
   * This createBug() method is implemented so that the information used in the parseBugs() method
   * is used to create a bug. The parameters for the bug are name, type, level and steps. Also, I
   * used a switch statement, which is like an if statement, that uses the acronyms of the bug, so
   * if it receives one like "CMB" it recognises that it is a ConcurrentModificationBug.
   *
   * @param name
   * @param type
   * @param level
   * @param steps
   * @return
   */
  private Bug createBug(String name, String type, int level, int steps) {
    switch (type) {
      case "NPB":
        return new NullPointerBug(name, level, steps);
      case "CMB":
        return new ConcurrentModificationBug(name, level, steps);
      case "NTB":
        return new NoneTerminationBug(name, level, steps);
      default:
        return null;
    }
  }

  /**
   * I created a run() method that uses the FileStream class to read the contents of the file, as
   * well as Scanner to obtain the primitive types in the file. The method also returns an IO error
   * which occurs if there is an error in reading or searching a file. The run() method also adds
   * the bugs to the building and for each wave of bugs, the battle should run 8*topFloor steps
   * before the next wave of bugs arrive.
   *
   * @throws IOException
   */
  public void run() throws IOException {
    FileInputStream inputStream = null;
    Scanner sc = null;
    try {
      inputStream = new FileInputStream(fileName);
      sc = new Scanner(inputStream, StandardCharsets.UTF_8);
      while (sc.hasNextLine()) {
        parseBugs(sc.nextLine()).stream().forEach(bug -> building.addBug(bug));

        for (int i = 0; i < 8 * building.getTopFloor(); i++) {
          battle.step();
        }
      }
      // note that Scanner suppresses exceptions
      if (sc.ioException() != null) {
        throw sc.ioException();
      }
    } finally {
      if (inputStream != null) {
        inputStream.close();
      }
      if (sc != null) {
        sc.close();
      }
    }
  }
}
