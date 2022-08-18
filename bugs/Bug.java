package bugs;

/** Implement the Bug Class */
public class Bug {
  String name;
  int baseHp;
  int baseSteps;
  int level;
  int currentHp;
  int currentSteps;
  int currentFloor = -1;

  public Bug(String name, int baseHp, int baseSteps, int level, int initialSteps) {
    this.name = name;
    this.baseHp = baseHp;
    this.baseSteps = baseSteps;
    this.level = level;
    this.currentSteps = initialSteps;
    this.currentHp = (int) Math.round(baseHp * Math.pow(level, 1.5));
  }

  public Bug(String name, int baseHp, int baseSteps, int level) {
    this.name = name;
    this.baseHp = baseHp;
    this.baseSteps = baseSteps;
    this.level = level;
    this.currentHp = (int) Math.round(baseHp * Math.pow(level, 1.5));
  }

  public int getBaseSteps() {
    return baseSteps;
  }

  public int getLevel() {
    return level;
  }

  public int getCurrentHp() {
    return currentHp;
  }

  public int getCurrentSteps() {
    return currentSteps;
  }

  public int getCurrentFloor() {
    return currentFloor;
  }

  public void move() {
    if (currentSteps > 0) {
      currentSteps = currentSteps - 1;
    } else {
      currentFloor++;
      currentSteps = baseSteps - 1;
    }
  }

  public void damage(int amount) {
    currentHp = currentHp - amount;
    if (currentHp < 0) {
      currentHp = 0;
    }
  }

  public void slowDown(int steps) {
    currentSteps = currentSteps + steps;
  }

  public String getName() {
    return name;
  }
}
