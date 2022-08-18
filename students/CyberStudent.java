package students;

import bugs.Bug;
import building.Building;

import java.util.Random;

public class CyberStudent implements Student {
  private final int baseAttack = 7;
  private final int baseDelay = 8;
  private final int _upgradeCost;
  int knowledgePoints;
  Bug[] bugs;
  private int level;
  private int patternCount = 0; // number of attacks
  private final String name = "CyberStudent";

  public CyberStudent(int level) {
    this.level = level;
    _upgradeCost = (int) (100 * Math.pow(2, level));
  }

  @Override
  public int getLevel() {
    return level;
  }

  @Override
  public int upgradeCost() {
    return _upgradeCost;
  }

  /**
   * Implement the defence() method for CyberStudent, its special ability is setting up antivirus
   * software with a chance of instantly removing the first bug. The probability of instant removal
   * is level + 20 but it cannot be more than 50. If the student can't remove the bug instantly it
   * does double the normal damage to the bug
   */
  @Override
  public int defence(Building building) {
    patternCount++;
    knowledgePoints = 0;
    bugs = building.getAllBugs();
    int normalAttack = (int) Math.round(baseAttack * Math.pow(level, 1.2));
    Random num = new Random();
    if ((patternCount % baseDelay) == 0) {
      int removalProbability = getLevel() + 20;
      if (removalProbability > 50) {
        removalProbability = 50;
      }
      if (num.nextInt(101) <= removalProbability) {
        building.removeBug(bugs[0]);
        knowledgePoints += bugs[0].getLevel() * 20;
      } else {
        bugs[0].damage(2 * normalAttack);
        if (bugs[0].getCurrentHp() == 0) {
          knowledgePoints = knowledgePoints + (bugs[0].getLevel() * 20);
        }
      }
    } else {
      bugs[0].damage(normalAttack);
      if (bugs[0].getCurrentHp() == 0) {
        building.removeBug(bugs[0]);
        knowledgePoints += bugs[0].getLevel() * 20;
      }
    }
    return knowledgePoints;
  }

  @Override
  public void upgradeLevel() {
    level++;
  }

  @Override
  public String getName() {
    return name;
  }
}
