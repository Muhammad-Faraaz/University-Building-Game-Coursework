import java.io.IOException;

public class EcsBuildingDefence {
  /**
   * This is the main method for the EcsBuildingDefence. It takes four arguments, and parses three
   * of them into integers (topFloor, constructionPoints, initialKnowledgePoints) and each one is
   * defined as can be seen. Also, the main method runs the EcsBuildingDefence app which has all the
   * other methods such as parseBug() and run(). If the user does not provide the four necessary
   * parameters the game returns a statement of what it needs.
   *
   * @param args
   * @throws IOException
   */
  public static void main(String[] args) throws IOException {
    if (args.length > 3) {
      int topFloor = Integer.parseInt(args[0]);
      int constructionPoints = Integer.parseInt(args[1]);
      String fileName = args[2];
      int initialKnowlegdePoints = Integer.parseInt(args[3]);

      EcsBuildingDefenceApp app =
          new EcsBuildingDefenceApp(topFloor, constructionPoints, fileName, initialKnowlegdePoints);
      app.run();
    } else {
      System.out.println(
          "Please provide 4 parameters; top floor, construction points, file name, initial knowledge points");
    }
  }
}
