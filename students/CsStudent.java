package students;

import bugs.Bug;
import building.Building;

public class CsStudent implements Student {
  private final int baseAttack = 6;
  private final int baseDelay = 6;
  private final int _upgradeCost;
  int knowledgePoints;
  Bug[] bugs;
  private int level;
  private int patternCount = 0; //number of attacks
  private final String name = "CsStudent";

  public CsStudent(int level) {
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

  /** Implement the defence() method for CSStudent which does 4 times the normal attack on a bug */
  @Override
  public int defence(Building building) {
    patternCount++;
    knowledgePoints = 0;
    bugs = building.getAllBugs();
    int normalAttack = (int) Math.round(baseAttack * Math.pow(level, 1.2));
    if ((patternCount % baseDelay) == 0) {
      bugs[0].damage(4 * normalAttack);
    } else {
      bugs[0].damage(normalAttack);
    }
    if (bugs[0].getCurrentHp() == 0) {
      building.removeBug(bugs[0]);
      knowledgePoints += bugs[0].getLevel() * 20;
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
