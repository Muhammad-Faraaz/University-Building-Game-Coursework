package building;

import bugs.Bug;
import bugs.ConcurrentModificationBug;
import bugs.NoneTerminationBug;
import bugs.NullPointerBug;

import java.util.ArrayList;
/** Implement the Building Class */
public class Building {
  private final int topFloor;
  private final ArrayList<Bug> bugs;
  private int constructionPoints;

  public Building(int topFloor, int constructionPoints) {
    this.topFloor = topFloor;
    this.constructionPoints = constructionPoints;
    this.bugs = new ArrayList<>();
  }

  public int getTopFloor() {
    return topFloor;
  }

  public int getConstructionPoints() {
    return constructionPoints;
  }

  /**
   * Implement the addBug() method which returns a negative number if the arraylist contains a bug,
   * else it adds a bug and returns the size
   */
  public int addBug(Bug bug) {
    if (bugs.contains(bug)) {
      return -1;
    } else {
      bugs.add(bug);
      return bugs.size();
    }
  }

  public void removeBug(Bug bug) {
    bugs.remove(bug);
  }

  /**
   * In the getAllBugs() method I decided to use streams (functional programming) to meet the
   * coursework requirements. Streams are more terse and concise. This can be seen, as filter and
   * sort make it easier to accomplish the task. The filter method allows one to filter elements
   * that match a given predicate, in this case if the bug hp is greater than 0 and its current
   * floor is not negative 1. Also sort allows one to return a list in a required order.
   */
  public Bug[] getAllBugs() {

    return bugs.stream()
        .filter(bug -> bug.getCurrentHp() > 0 && bug.getCurrentFloor() != -1)
        .sorted(
            (bug1, bug2) ->
                bug1.getCurrentFloor() == bug2.getCurrentFloor()
                    ? Integer.compare(bug1.getCurrentSteps(), bug2.getCurrentSteps())
                    : Integer.compare(bug2.getCurrentFloor(), bug1.getCurrentFloor()))
        .toArray(Bug[]::new);
  }

  public void bugsMove() {
    Bug[] bugsArray = bugs.toArray(Bug[]::new);
    for (Bug bug : bugsArray) {
      bug.move();
      if (bug.getCurrentFloor() == this.getTopFloor()) {
        removeBug(bug);
        constructionPoints -= getDamage(bug);
      }
      if (constructionPoints == 0) {
        break;
      }
    }
  }
  /**
   * I added another method called getDamage() which is used by bugsMove(), since it makes it easier
   * to identify and set damages of individual bugs. I used the instanceof operator which checks
   * whether the object is of a certain type. So for example if the bug is of ConcurrentModification
   * type, it returns 2 damage which is what is says in the coursework as well.
   */
  private int getDamage(
      Bug bug) { // Created another method to attribute specific damage points to their respective
    // bugs
    // which are used in the bugsMove() method.
    if (bug instanceof ConcurrentModificationBug) {
      return 2;
    } else if (bug instanceof NoneTerminationBug) {
      return 4;
    } else if (bug instanceof NullPointerBug) {
      return 1;
    } else {
      return 0;
    }
  }
}
