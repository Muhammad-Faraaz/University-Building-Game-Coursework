package students;

import bugs.Bug;
import building.Building;

import java.util.Arrays;

public class AiStudent implements Student {
  private final int baseAttack = 7;
  private final int baseDelay = 7;
  private final int _upgradeCost;
  private final int normalAttack;
  private final String name = "AiStudent";
  private int level;
  private int knowledgePoints;
  private int patternCount = 0; // number of attacks

  public AiStudent(int level) {
    this.level = level;
    normalAttack = (int) Math.round(baseAttack * Math.pow(level, 1.2));
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
   * Implement the defence system of the AiStudent I created a boolean called isSpecialAttack which
   * is equal and true if the remainder of the patternCount divided by baseDelay is 0. This is used
   * so that the AI Student implements its attack on its 7th attempt (baseDelay).
   */
  @Override
  public int defence(Building building) {
    patternCount++;
    Bug[] bugs = building.getAllBugs();
    boolean isSpecialAttack = (patternCount % baseDelay) == 0;

    return Arrays.stream(bugs)
        .limit(isSpecialAttack ? 3 : 1) // ternary operator
        .mapToInt(bug -> doDefence(building, bug))
        .sum();
  }

  @Override
  public void upgradeLevel() {
    level++;
  }

  @Override
  public String getName() {
    return name;
  }

  /**
   * Implement doDefence method for AiStudent which removes bug if it has 0 hp otherwise it returns
   * the amount of points the student gained.
   */
  private int doDefence(Building building, Bug bug) {
    bug.damage(normalAttack);
    if (bug.getCurrentHp() == 0) {
      building.removeBug(bug);
      return bug.getLevel() * 20;
    }
    return 0;
  }
}
