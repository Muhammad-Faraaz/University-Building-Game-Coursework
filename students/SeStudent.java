package students;

import bugs.Bug;
import building.Building;

public class SeStudent implements Student {
  private final int baseAttack = 5;
  private final int baseDelay = 6;
  private final int _upgradeCost;
  private final String name = "SeStudent";
  int knowledgePoints;
  Bug[] bugs;
  private int level;
  private int patternCount = 0; // number of attacks

  public SeStudent(int level) {
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
   * Implement the defence() method for SeStudent, its special ability is it slows down the first 5
   * bugs by 2 steps.
   */
  @Override
  public int defence(Building building) {
    patternCount++;
    knowledgePoints = 0;
    bugs = building.getAllBugs();
    if ((patternCount % baseDelay) == 0) {
      for (int i = 0; i < 5; i++) {
        bugs[i].slowDown(2);
      }
    } else {
      int normalAttack = (int) Math.round(baseAttack * Math.pow(level, 1.2));
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
